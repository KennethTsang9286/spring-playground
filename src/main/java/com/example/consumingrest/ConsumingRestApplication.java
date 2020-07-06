package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.example.consumingrest.Object.Quote;

import com.example.consumingrest.Object.Person;
import com.example.consumingrest.Repository.PersonRepository;

@SpringBootApplication
public class ConsumingRestApplication {

	@Autowired
	private PersonRepository personRepository;

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);
	private static final String URL = "http://localhost:8080/greeting";

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			// System.out.println("Let's inspect the beans provided by Spring Boot:");
			// String[] beanNames = ctx.getBeanDefinitionNames();
			// Arrays.sort(beanNames);
			// for (String beanName : beanNames) {
			// System.out.println(beanName);
			// }
		};
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			queryApi(restTemplate);
			setUpMongo();
			log.info(String.format("This controller runs in %s", URL));

		};
	}

	private void queryApi(RestTemplate restTemplate) throws Exception {
		Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		log.info(quote.toString());
	}

	private void setUpMongo() throws Exception {
		personRepository.deleteAll();
		// save a couple of Persons
		personRepository.save(new Person("Alice", "Smith"));
		personRepository.save(new Person("Bob", "Smith"));
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/greeting/cors").allowedOrigins("http://localhost:9000");
			}
		};
	}

}
