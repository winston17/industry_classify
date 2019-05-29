package com.yzc.industry_classify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class IndustryClassifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndustryClassifyApplication.class, args);
	}

}
