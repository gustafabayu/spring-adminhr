package com.api.adminhr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.adminhr.controller.AuthControllerTest;
import com.api.adminhr.controller.UserControllerTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AdminhrApplicationTests {

	@Autowired
	private AuthControllerTest authControllerTest;
	private UserControllerTest userControllerTest;

	@Test
	void contextLoads() {
		assertNotNull(authControllerTest);
		assertNotNull(userControllerTest);
	}

}
