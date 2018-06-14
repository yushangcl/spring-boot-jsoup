package cn.itbat.springboot.jsoup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJsoupApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(SpringBootJsoupApplication.class);

		logger.info(">>>>> spring-boot 正在启动 <<<<<");
		SpringApplication.run(SpringBootJsoupApplication.class, args);
		logger.info(">>>>> spring-boot 启动完成 <<<<<");
	}
}
