package san.bdia.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void moneyTest() {
		Money m1 = new Money(100, Currency.PLN);
		System.out.println(m1.in(Currency.EUR));
	}

}
