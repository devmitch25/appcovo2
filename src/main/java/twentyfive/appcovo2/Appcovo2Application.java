package twentyfive.appcovo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Appcovo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Appcovo2Application.class, args);
	}

}
