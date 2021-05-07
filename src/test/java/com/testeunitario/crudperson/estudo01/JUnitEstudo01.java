package com.testeunitario.crudperson.estudo01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitEstudo01 {
	
	// Cenário 
	int numberOne = 10;
	int numberTwo = 20;
	
	Calculadora calculadora;
	
	@BeforeAll // Anotação que deve ser executado antes de todos os testes na classe de teste atual.
	public void setUp() {
		calculadora = new Calculadora();
	}
	
	@Test
	public void soma(){
		// cenário

		// execulção
		
		calculadora.soma(numberOne, numberTwo);
		
		// validação
		assertThat(calculadora).isEqualTo(15);
	}
	
	@Test
	public void testandoResultadoDeUmaDivisao() {
		 // cenário
		
		calculadora.divisao(numberOne, numberTwo);
		
		assertThat(calculadora).isEqualTo(5);
	}

}
 class Calculadora{
	 
	 int soma(int soma1, int soma2) {
		 if(soma1 < 0 ||  soma2 < 0)
			 throw new RuntimeException("Essa soma não pode ser negativa");
		 
		 return soma1 + soma2;
	 }
	 
	 int divisao(int divisaoOne, int divisaoTwo) {
		 if(divisaoOne == 0)
			 throw new RuntimeException("Operador não pode ser 0");
		 
		 return divisaoOne / divisaoTwo;
	 }
	
}
