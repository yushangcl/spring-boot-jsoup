package cn.itbat.springboot.jsoup.core.service.impl;

import cn.itbat.springboot.jsoup.BaseTest;
import cn.itbat.springboot.jsoup.core.service.BaAddressService;
import net.bytebuddy.asm.Advice;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author log.r   (;￢＿￢)   
 * @date 2018-06-13 下午3:23
 **/
public class BaAddressServiceImplTest extends BaseTest {

    @Resource
    private BaAddressService baAddressService;
    @Test
    public void getAddressInfo() throws Exception {
        baAddressService.getAddressInfo();
    }

}