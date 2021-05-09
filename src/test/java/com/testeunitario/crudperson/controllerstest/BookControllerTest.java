package com.testeunitario.crudperson.controllerstest;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testeunitario.crudperson.dto.BookDto;
import com.testeunitario.crudperson.entities.Book;
import com.testeunitario.crudperson.services.BookService;

/*************
 * @ExtendWith(SpringExtension.class):
 * A classe SpringExtension é fornecida pelo Spring 5 e integra o Spring TestContext Framework ao JUnit 5.
 * A anotação @ExtendWith aceita qualquer classe que implemente a interface Extension .
 * */

/*************
 * @ActiveProfiles("test"):
 * Ativa um perfil durante a execução de testes sem especificar a localização real do arquivo,
 * usamos esta anotação para carregar propriedades para esses perfis.
 * */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	static final String BOOK_API = "/api/v1/books";
	
	@MockBean
	BookService service;
	
	@Test
	@DisplayName("Deve criar um livro com sucesso")
	public void saveBook() throws Exception {
		
		BookDto dto = BookDto.builder().title("Aprendendo Java").author("Hugo").isbn("123456").build();
		Book saveBook = Book.builder().id(1L).title("Aprendendo Java").author("Hugo").isbn("123456").build();
		
		BDDMockito.given(service.save(Mockito.any(Book.class))).willReturn(saveBook);
		
		// ObjectMapperserializa objetos Java em JSON e desserializar a sequência JSON em objetos Java.
		String json = new ObjectMapper().writeValueAsString(dto);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
									.post(BOOK_API)
									.contentType(MediaType.APPLICATION_JSON)
									.accept(MediaType.APPLICATION_JSON)
									.content(json);
				/* ******content
				* Em requisições, como POST ou PUT, o client diz ao servidor qual o tipo de dado que está, de fato, sendo enviado.
 				*  Sitaxe de um content-type:
 				*  --------------------------------------------------------
 				|   Content-Type:text/html; charset=utf-8				   |
				|	Content-Type: multipart/form-data; boundary=something  |
				|----------------------------------------------------------|
				* ******/
				mvc
					.perform(request)
					.andExpect(MockMvcResultMatchers.status().isCreated())
					.andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
					.andExpect(MockMvcResultMatchers.jsonPath("title").value(dto.getTitle()))
					.andExpect(MockMvcResultMatchers.jsonPath("author").value(dto.getAuthor()))
					.andExpect(MockMvcResultMatchers.jsonPath("isbn").value(dto.getIsbn()));
									
	}

}
