package com.testeunitario.crudperson.estudo01;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/*
 * A classe SpringExtension é fornecida pelo Spring 5 e integra o Spring TestContext Framework ao JUnit 5.
 * A anotação @ExtendWith aceita qualquer classe que implemente a interface Extension .
 * */
@ExtendWith(SpringExtension.class) //1. @ExtendWith(MockitoJUnitRunner.class) - Depreciado
public class MockitoEstudo01 {
	
	@Mock
	List<String> list;
	
	@Test
	@DisplayName("Entendo como usar o Mockito")
	public void aprendendoSobreMockito() {
	//1. List<String> list = Mockito.mock(ArrayList.class);
		
		Mockito.when(list.size()).thenReturn(20);
		
		int size = list.size();
		
		Assertions.assertThat(size).isEqualTo(20);
	}
	
	@Test
	@DisplayName("Entendo como funciona objeto InOrder")
	public void entendendoInstanciainOrderDoMockito() {
		list.size();
		list.add("");
		// Inorder cuida da ordem das chamadas de método que o mock fará no devido curso de sua ação.
		InOrder inOrder = Mockito.inOrder(list);
		inOrder.verify(list).size();
		inOrder.verify(list).add("");
	}

}
