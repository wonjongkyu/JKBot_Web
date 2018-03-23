package jk.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@PropertySource("api.properties")
@PropertySource("config.properties")
public class JkFrameworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(JkFrameworkApplication.class, args);
	}
}
