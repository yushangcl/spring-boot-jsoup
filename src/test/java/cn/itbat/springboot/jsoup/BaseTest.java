package cn.itbat.springboot.jsoup;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huahui.wu.
 *         Created on 2018/1/10.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
@MapperScan("cn.itbat.springboot.jsoup.core.dao")
public class BaseTest {
}
