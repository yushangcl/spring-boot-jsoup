package cn.itbat.springboot.jsoup.core.service.impl;

import cn.itbat.springboot.jsoup.core.dao.BaAddressMapper;
import cn.itbat.springboot.jsoup.core.entity.BaAddress;
import cn.itbat.springboot.jsoup.core.service.BaAddressService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author log.r   (;￢＿￢)   
 * @date 2018-06-13 下午3:10
 **/
@Service
public class BaAddressServiceImpl implements BaAddressService {
    private static final String ADDRESS_REDIS = "ADDRESS_REDIS";

    @Resource
    private HashOperations<String, String, BaAddress> hashOperations;

    @Resource
    private BaAddressMapper baAddressMapper;

    private static Map<Integer, String> cssMap = new HashMap<Integer, String>();
    /**
     * 上一级AreaCode
     */
    private static String upperLevel_1 = null;
    private static String upperLevel_2 = null;
    private static String upperLevel_3 = null;
    private static String upperLevel_4 = null;

    BaAddress baAddress = new BaAddress();

    static {
        // 省
        cssMap.put(1, "provincetr");
        // 市
        cssMap.put(2, "citytr");
        // 县
        cssMap.put(3, "countytr");
        // 镇
        cssMap.put(4, "towntr");
        // 村
        cssMap.put(5, "villagetr");

    }

    @Override
    public void getAddressInfo() {
        int level = 1;
        // 获取全国各个省级信息
        Document connect = connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/");
        Elements rowProvince = Objects.requireNonNull(connect).select("tr." + cssMap.get(level));
        parseProvince(rowProvince, level);

    }

    /**
     * 处理省份
     *
     * @param rowProvince 省
     */
    private void parseProvince(Elements rowProvince, int level) {
        // 遍历每一行的省份城市
        for (Element provinceElement : rowProvince) {
            Elements select = provinceElement.select("a");
            // 每一个省份(四川省)
            for (Element province : select) {
                String url = province.attr("abs:href");
                int i = url.lastIndexOf(".");
                upperLevel_1 = url.substring(i - 2, i);
                String name = province.select("a").text();
                System.out.println("areaCode:" + upperLevel_1 + " name:" + name + " upperLevel:" + 0);
//        saveToRedis(upperLevel, name, "0", level);
                saveToDataBase(upperLevel_1, name, "0", level);
                parseCity(province, level + 1);
            }
        }
    }

    private void parseCity(Element parentElement, int level) {
        Document doc = connect(parentElement.attr("abs:href"));
        if (doc != null) {
            int i = 1;
            Elements newsHeadlines = doc.select("tr." + cssMap.get(level));
            // 获取表格的一行数据
            for (Element element : newsHeadlines) {
                upperLevel_2 = printInfo(element, level + 1, upperLevel_1);
                Elements select = element.select("a");
                if (select.size() != 0) {
                    parseCounty(select.last(), level + 1);
                }
            }

        }
    }

    private void parseCounty(Element parentElement, int level) {
        Document doc = connect(parentElement.attr("abs:href"));
        if (doc != null) {
            int i = 1;
            Elements newsHeadlines = doc.select("tr." + cssMap.get(level));
            // 获取表格的一行数据
            for (Element element : newsHeadlines) {
                upperLevel_3 = printInfo(element, level + 1, upperLevel_2);
                Elements select = element.select("a");
                if (select.size() != 0) {
                    parseTown(select.last(), level + 1);
                }
            }

        }
    }

    private void parseTown(Element parentElement, int level) {
        Document doc = connect(parentElement.attr("abs:href"));
        if (doc != null) {
            int i = 1;
            Elements newsHeadlines = doc.select("tr." + cssMap.get(level));
            // 获取表格的一行数据
            for (Element element : newsHeadlines) {
                upperLevel_4 = printInfo(element, level + 1, upperLevel_3);
            }

        }
    }


    /**
     * 处理下一级节点
     *
     * @param parentElement 下一节点
     * @param level         层级
     */
//    private void parseNextLevel(Element parentElement, int level) {
//        Document doc = connect(parentElement.attr("abs:href"));
//        if (doc != null) {
//            int i = 1;
//            Elements newsHeadlines = doc.select("tr." + cssMap.get(level));
//            // 获取表格的一行数据
//            for (Element element : newsHeadlines) {
//                String areaCode = printInfo(element, level + 1);
//                // 在递归调用的时候，这里是判断是否是村一级的数据，村一级的数据没有a标签
//                Elements select = element.select("a");
//                if (select.size() != 0) {
//                    parseNextLevel(select.last(), level + 1);
//                }
//            }
//
//        }
//    }

    /**
     * 写一行数据到数据文件中去
     *
     * @param element 爬取到的数据元素
     * @param level   城市级别
     */
    private String printInfo(Element element, int level, String upperLevel) {
        String areaCode = element.select("td").first().text();
        String name = element.select("td").last().text();
        System.out.println("areaCode:" + areaCode + " name:" + name + " upperLevel:" + upperLevel);
//        saveToRedis(areaCode, name, upperLevel, level);
        saveToDataBase(areaCode, name, upperLevel, level - 1);
        return areaCode;
    }

    private Document connect(String url) {
        try {
            //睡眠一下，否则可能出现各种错误状态码
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("The input url('" + url + "') is invalid!");
        }
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("49.69.89.59", 61234));
            return Jsoup.connect(url).timeout(100 * 1000)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36")
                    .proxy(proxy)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveToRedis(String areaCode, String name, String upperLevel, Integer level) {
        baAddress.setAddressCode(areaCode);
        baAddress.setAddressName(name);
        baAddress.setLevel(level);
        baAddress.setParentCode(upperLevel);
        hashOperations.put(ADDRESS_REDIS, baAddress.getAddressCode(), baAddress);
    }

    private void saveToDataBase(String areaCode, String name, String upperLevel, Integer level) {
        baAddress.setAddressCode(areaCode);
        baAddress.setAddressName(name);
        baAddress.setLevel(level);
        baAddress.setParentCode(upperLevel);
//        baAddressMapper.insertSelective(baAddress);
    }
}
