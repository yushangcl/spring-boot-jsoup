//package cn.itbat.springboot.jsoup.utils;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
///**
// * 全国省市县镇村数据爬取
// *
// * @author liushaofeng
// * @version 1.0.0
// * @date 2015-10-11 上午12:19:39
// */
//public class demo {
//
//
//    private static Map<Integer, String> cssMap = new HashMap<Integer, String>();
//    /**
//     * 上一级AreaCode
//     */
//    private static String upperLevel = null;
//
//    static {
//        // 省
//        cssMap.put(1, "provincetr");
//        // 市
//        cssMap.put(2, "citytr");
//        // 县
//        cssMap.put(3, "countytr");
//        // 镇
//        cssMap.put(4, "towntr");
//        // 村
//        cssMap.put(5, "villagetr");
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        int level = 1;
//        // 获取全国各个省级信息
//        Document connect = connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/");
//        Elements rowProvince = Objects.requireNonNull(connect).select("tr." + cssMap.get(level));
//        // 遍历每一行的省份城市
//        for (Element provinceElement : rowProvince) {
//            Elements select = provinceElement.select("a");
//            // 每一个省份(四川省)
//            for (Element province : select) {
//                parseProvince(province, level);
//                parseNextLevel(province, level + 1);
//            }
//        }
//
//    }
//
//    /**
//     * 处理省份
//     *
//     * @param province 省
//     */
//    private static void parseProvince(Element province, int level) {
//        String url = province.attr("abs:href");
//        int i = url.lastIndexOf(".");
//        upperLevel = url.substring(i - 2, i);
//        String name = province.select("a").text();
//        System.out.println("areaCode:" + upperLevel + " name:" + name + " upperLevel:" + 0);
//    }
//
//    /**
//     * 处理下一级节点
//     *
//     * @param parentElement 下一节点
//     * @param level         层级
//     */
//    private static void parseNextLevel(Element parentElement, int level) {
//        Document doc = connect(parentElement.attr("abs:href"));
//        if (doc != null) {
//            Elements newsHeadlines = doc.select("tr." + cssMap.get(level));
//            // 获取表格的一行数据
//            for (Element element : newsHeadlines) {
//                printInfo(element, level + 1);
//                // 在递归调用的时候，这里是判断是否是村一级的数据，村一级的数据没有a标签
//                Elements select = element.select("a");
//                if (select.size() != 0) {
//                    parseNextLevel(select.last(), level + 1);
//                }
//            }
//        }
//    }
//
//    /**
//     * 写一行数据到数据文件中去
//     *
//     * @param element 爬取到的数据元素
//     * @param level   城市级别
//     */
//    private static void printInfo(Element element, int level) {
//        String areaCode = element.select("td").first().text();
//        String name = element.select("td").last().text();
//        System.out.println("areaCode:" + areaCode + " name:" + name + " upperLevel:" + upperLevel);
//        upperLevel = areaCode;
//    }
//
//    private static Document connect(String url) {
//        try {
//            //睡眠一下，否则可能出现各种错误状态码
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (url == null || url.isEmpty()) {
//            throw new IllegalArgumentException("The input url('" + url + "') is invalid!");
//        }
//        try {
//            return Jsoup.connect(url).timeout(100 * 1000).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}