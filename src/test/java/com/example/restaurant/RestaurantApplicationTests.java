package com.example.restaurant;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("DB未接続になるため一度スルー")
class RestaurantApplicationTests {

	@Test
	void contextLoads() {
	}

}
