package com.example.consumingrest;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumingRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeforeInitialization : " + beanName);
		return bean; // you can return any other object as well
	}
}
