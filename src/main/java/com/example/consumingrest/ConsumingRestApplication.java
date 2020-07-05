package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

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
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
			log.info(String.format("This controller runs in %s", URL));

			personRepository.deleteAll();

			// save a couple of Persons
			personRepository.save(new Person("Alice", "Smith"));
			personRepository.save(new Person("Bob", "Smith"));

		};
	}

}
