package tacos.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExamples {

	public static void main(String[] args) {
		SpringApplication.run(RestExamples.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Traverson traverson() {
		Traverson traverson = new Traverson(
				URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
		return traverson;
	}
}
