package com.tdd.spring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tdd.spring.entity.Customer;
import com.tdd.spring.repository.CustomerRepository;

@Configuration
@Profile("local")
public class Config {
	
	private @Autowired CustomerRepository customerRepository;
	
	@Bean
	void startDB(){
		Customer customer01 = new Customer(1L, "hugo.luiz.cruz@gmail.com", "123");
		Customer customer02 = new Customer(2L, "jeff.luiz.cruz@gmail.com", "123");
		
		customerRepository.saveAll(Arrays.asList(customer01, customer02));
	}

}
