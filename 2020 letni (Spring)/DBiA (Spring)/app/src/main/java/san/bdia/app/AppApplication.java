package san.bdia.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppApplication {

	public static void main(String... args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World")
														String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/currency")
	public Money currency(@RequestParam(value = "amount") long amount,
												@RequestParam(value =   "from") String from,
												@RequestParam(value =     "to") String   to) {

		Currency c1 = Currency.valueOf(from);
		Currency c2 = Currency.valueOf(to);

		Money m1 = new Money(amount, c1);
		Money m2 = m1.in(c2);

		return m2;
	}
}
