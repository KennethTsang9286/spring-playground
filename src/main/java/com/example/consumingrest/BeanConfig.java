package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.consumingrest.Object.Person;
import com.example.consumingrest.Repository.PersonRepository;
import com.example.consumingrest.Service.QuoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {
    @Autowired
    private QuoteService quoteService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private Logger log;
    private static final String URL = "http://localhost:8080";

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            setUpMongo();
            log.info(quoteService.getQuote().toString());
            log.info(String.format("This controller runs in %s", URL));
        };
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService executor() {
        return Executors.newFixedThreadPool(4);
    }

    @Bean
    public Logger log() {
        return LoggerFactory.getLogger(BeanConfig.class);
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

    private void setUpMongo() throws Exception {
        personRepository.deleteAll();
        personRepository.save(new Person("Alice", "Smith"));
        personRepository.save(new Person("Bob", "Smith"));
    }
}