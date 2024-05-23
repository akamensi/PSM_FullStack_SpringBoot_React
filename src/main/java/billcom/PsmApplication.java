package billcom;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"billcom"})
public class PsmApplication {
	
	public static String uploadDirectory =
			System.getProperty("user.dir")+"/src/main/resources/static/uploads";

	public static void main(String[] args) {
		SpringApplication.run(PsmApplication.class, args);
		
		new File(uploadDirectory).mkdir();
		
		System.out.println("psm!!");
	}

}
