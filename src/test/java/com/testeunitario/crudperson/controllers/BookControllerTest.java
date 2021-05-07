package com.testeunitario.crudperson.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*************
 * A classe SpringExtension é fornecida pelo Spring 5 e integra o Spring TestContext Framework ao JUnit 5.
 * A anotação @ExtendWith aceita qualquer classe que implemente a interface Extension .
 * */
@ExtendWith(SpringExtension.class)
/*************
 * Ativa um perfil durante a execução de testes sem especificar a localização real do arquivo,
 * usamos esta anotação para carregar propriedades para esses perfis.
 * */
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	static String BOOK_API = "/api/books";
	
	@Test
	@DisplayName("Deve criar um livro com sucesso")
	public void saveBook() throws Exception {
		// ObjectMapperserializa objetos Java em JSON e desserializar a sequência JSON em objetos Java.
		String json = new ObjectMapper().writeValueAsString(null);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
									.post(null);
	}

}
