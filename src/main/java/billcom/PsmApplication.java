package billcom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"billcom"})
public class PsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsmApplication.class, args);
		System.out.println("psm!!");
	}

}
