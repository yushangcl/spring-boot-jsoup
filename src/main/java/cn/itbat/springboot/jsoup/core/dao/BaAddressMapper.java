package cn.itbat.springboot.jsoup.core.dao;

import cn.itbat.springboot.jsoup.core.entity.BaAddress;
import cn.itbat.springboot.jsoup.core.entity.BaAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaAddressMapper {
    int countByExample(BaAddressExample example);

    int deleteByExample(BaAddressExample example);

    int deleteByPrimaryKey(Long addressUkid);

    int insert(BaAddress record);

    int insertSelective(BaAddress record);

    List<BaAddress> selectByExample(BaAddressExample example);

    BaAddress selectByPrimaryKey(Long addressUkid);

    int updateByExampleSelective(@Param("record") BaAddress record, @Param("example") BaAddressExample example);

    int updateByExample(@Param("record") BaAddress record, @Param("example") BaAddressExample example);

    int updateByPrimaryKeySelective(BaAddress record);

    int updateByPrimaryKey(BaAddress record);
}