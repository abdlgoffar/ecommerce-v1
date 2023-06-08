package ecommerce.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ProjectJavaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectJavaApiApplication.class, args);
	}

}
