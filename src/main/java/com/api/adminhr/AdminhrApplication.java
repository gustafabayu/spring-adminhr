package com.api.adminhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:db.properties")
public class AdminhrApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminhrApplication.class, args);
	}

}
