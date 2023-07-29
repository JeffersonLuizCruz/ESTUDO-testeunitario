package com.tdd.spring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.tdd.spring.entity.Customer;
import com.tdd.spring.repository.CustomerRepository;
import com.tdd.spring.service.impl.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceTest {

	private static final Long ID 			= 1L;
	private static final String EMAIL 		= "jeff.luiz.cruz@gmail.com";
	private static final String PASSWORD 	= "123";
	
	/*
	 * @Mock cria uma simulação. @InjectMocks cria uma instância da classe e injeta os
	 * mocks que são criados com as anotações @Mock(ou @Spy) nesta instância.
	 */
	
	@InjectMocks 
	private CustomerServiceImpl customerServiceImpl;
	@Mock 
	private CustomerRepository customerRepository;
	@Mock 
	private Customer customer;
	@Mock 
	private Optional<Customer> optionalCustomer;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startCustomer();
	}
	
	@Test
	void whenFindByIdThenReturnAnCustomerInstance() {
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(optionalCustomer);
		
		Customer responseEntity = customerServiceImpl.findById(ID);

		assertNotNull(responseEntity);
		assertEquals(ID, responseEntity.getId());
		assertEquals(Customer.class, responseEntity.getClass());
	}
	
	void startCustomer() {
		this.customer = Customer.builder()
		.id(ID)
		.email(EMAIL)
		.password(PASSWORD)
		.build();
		
		this.optionalCustomer = Optional.of(
				Customer.builder()
				.id(ID)
				.email(EMAIL)
				.password(PASSWORD)
				.build()
				);
	}
}
