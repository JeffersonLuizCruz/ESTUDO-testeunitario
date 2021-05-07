package com.testeunitario.crudperson.estudo01;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
	public void aprendendoSobreMockito() {
	//1. List<String> list = Mockito.mock(ArrayList.class);
		
		Mockito.when(list.size()).thenReturn(20);
		
		int size = list.size();
		
		Assertions.assertThat(size).isEqualTo(20);
	}

}
