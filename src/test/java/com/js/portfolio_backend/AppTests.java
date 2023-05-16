package com.js.portfolio_backend;
import static org.assertj.core.api.Assertions.assertThat;

import com.js.portfolio_backend.controller.BikeTripController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppTests {

	@Autowired
	private BikeTripController bikeControl;
	@Test
	void contextLoads() throws Exception {
		assertThat(bikeControl).isNotNull();
	}

}
