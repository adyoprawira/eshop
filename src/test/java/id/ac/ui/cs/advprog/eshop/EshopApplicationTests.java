package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EshopApplicationTests {

	@Test
	void contextLoads() {}

	@Test
	void mainMethodRunsSuccessfully() {
		String[] args = {};
		EshopApplication.main(args);
	}
}