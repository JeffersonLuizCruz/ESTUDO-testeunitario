package com.tdd.spring.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tdd.spring.controller.CustomerController;
import com.tdd.spring.controller.dto.CustomerRequestDto;
import com.tdd.spring.controller.dto.CustomerResponseDto;
import com.tdd.spring.controller.mapper.CustomerMapper;
import com.tdd.spring.entity.Customer;
import com.tdd.spring.service.CustomerService;

@SpringBootTest
public class CustomerControllerTest {

	private static final int INDEX 					= 0;
	private static final Long ID 					= 1L;
	private static final String EMAIL 				= "jeff.luiz.cruz@gmail.com";
	private static final String PASSWORD 			= "123";
	
	/*
	 * Aqui, a anotação @InjectMocks é usada para injetar os mocks criados
	 * automaticamente em customerController. Isso significa que o
	 * CustomerController real será criado, e os mocks serão injetados em seus
	 * campos anotados com @Mock.
	 */
	@InjectMocks private CustomerController customerController;
	@Mock private CustomerService customerService;
	@Mock private CustomerMapper customerMapper;
	@Mock private Optional<Customer> optionalCustomer;
	@Mock private Customer customer = new Customer();
	@Mock private CustomerRequestDto customerRequestDto = new CustomerRequestDto();
	@Mock private CustomerResponseDto customerResponseDto = new CustomerResponseDto();
	
	@BeforeEach
	void setUp() {
		/*
		 * O MockitoAnnotations.openMocks(this) inicializa todos os campos anotados
		 * com @Mock e injeta os mocks nas dependências anotadas com @InjectMocks da
		 * classe de teste atual (neste caso, CustomerController).
		 */
		MockitoAnnotations.openMocks(this);
		startCustomer();
	}
	
	@Test
	void whenFindByIdThenReturnSuccess() {
		when(customerMapper.toDTO(any())).thenReturn(customerResponseDto);
		when(customerService.findById(anyLong())).thenReturn(customer);
		
		ResponseEntity<CustomerResponseDto> responseCustomer = customerController.findById(ID);
		
		assertNotNull(responseCustomer);
		assertNotNull(responseCustomer.getBody());
		assertEquals(HttpStatus.OK, responseCustomer.getStatusCode());
		assertEquals(ResponseEntity.class, responseCustomer.getClass());
		assertEquals(CustomerResponseDto.class, responseCustomer.getBody().getClass());
		
		assertEquals(ID, responseCustomer.getBody().getId());
		assertEquals(EMAIL, responseCustomer.getBody().getEmail());
		assertEquals(PASSWORD, responseCustomer.getBody().getPassword());
		
	}
	
	@Test
	void whenFindAllThenReturnListOfCustomerResponseDto() {
		when(customerService.findAll()).thenReturn(List.of(this.customer));
		when(customerMapper.toDTO(any())).thenReturn(customerResponseDto);
		
		ResponseEntity<List<CustomerResponseDto>> responseCustomers = customerController.findAll();
		
		assertNotNull(responseCustomers);
		assertNotNull(responseCustomers.getBody());
		assertEquals(HttpStatus.OK, responseCustomers.getStatusCode());
		assertEquals(ResponseEntity.class, responseCustomers.getClass());
		assertEquals(ArrayList.class, responseCustomers.getBody().getClass());
		assertEquals(CustomerResponseDto.class, responseCustomers.getBody().get(INDEX).getClass());
		
		assertEquals(ID, responseCustomers.getBody().get(INDEX).getId());
		assertEquals(EMAIL, responseCustomers.getBody().get(INDEX).getEmail());
		assertEquals(PASSWORD, responseCustomers.getBody().get(INDEX).getPassword());
	}
	
	@Test
	void whenSaveThenReturnCreated() {
		when(customerMapper.toModel(this.customerRequestDto)).thenReturn(this.customer);
		when(customerMapper.toDTO(this.customer)).thenReturn(this.customerResponseDto);
		when(customerService.save(any())).thenReturn(this.customer);
		
		ResponseEntity<CustomerResponseDto> responseCustomer = customerController.save(customerRequestDto);
		
		assertEquals(HttpStatus.CREATED, responseCustomer.getStatusCode());
	}
	
	@Test
	void whenUpdateThenReturnCreated() {
		when(customerMapper.toModel(this.customerRequestDto)).thenReturn(this.customer);
		when(customerMapper.toDTO(this.customer)).thenReturn(this.customerResponseDto);
		when(customerService.update(any(), any())).thenReturn(this.customer);
		
		ResponseEntity<CustomerResponseDto> responseCustomer = customerController.update(ID,customerRequestDto);
		
		assertNotNull(responseCustomer);
		assertNotNull(responseCustomer.getBody());
		assertEquals(HttpStatus.OK, responseCustomer.getStatusCode());
		assertEquals(ResponseEntity.class, responseCustomer.getClass());
		assertEquals(CustomerResponseDto.class, responseCustomer.getBody().getClass());
		
		assertEquals(ID, responseCustomer.getBody().getId());
		assertEquals(EMAIL, responseCustomer.getBody().getEmail());
	}
	
	@Test
	void whenDeleteThenReturnSuccess() {
		doNothing().when(customerService).deleteById(anyLong());
		
		customerController.deleteById(ID);
		
		verify(customerService, times(1)).deleteById(anyLong());
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
		
		this.customerResponseDto = CustomerResponseDto.builder()
		.id(ID)
		.email(EMAIL)
		.password(PASSWORD)
		.build();
		
		this.customerRequestDto = CustomerRequestDto.builder()
				.email(EMAIL)
				.password(PASSWORD)
				.build();
	}
}
