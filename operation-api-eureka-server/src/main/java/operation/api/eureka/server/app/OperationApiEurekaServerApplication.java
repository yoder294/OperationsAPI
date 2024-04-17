package operation.api.eureka.server.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class OperationApiEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperationApiEurekaServerApplication.class, args);
	}

}
