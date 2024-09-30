package tech.one.place;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
public class PlaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaceApplication.class, args);
	}
}
