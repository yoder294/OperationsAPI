package operation.api.config.server.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class OperationApiConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperationApiConfigServerApplication.class, args);
	}

}
