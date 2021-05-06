package com.testeunitario.crudperson.estudo01;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Estudo01 {
	
	@Test
	public void soma(){
		// cenário
		int numberOne = 10;
		int numberTwo = 20;
		
		// execulção
		Calculadora calc = new Calculadora();
		
		calc.soma(numberOne, numberTwo);
		
		// validação
		assertThat(calc).isEqualTo(15);
	}
	
	@Test
	public void testandoResultadoDeUmaDivisao() {
		 // cenário
		int numberOne = 0;
		int NumberTwo = 5;
		
		Calculadora calc = new Calculadora();
		
		calc.divisao(numberOne, NumberTwo);
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
