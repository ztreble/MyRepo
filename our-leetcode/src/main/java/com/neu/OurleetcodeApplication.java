package com.neu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.neu.mapper")
public class OurleetcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurleetcodeApplication.class, args);
	}

}
