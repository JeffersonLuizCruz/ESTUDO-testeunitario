package com.testeunitario.crudperson.estudo01;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.testeunitario.crudperson.CadastroPessoa;
import com.testeunitario.crudperson.Pessoa;

// Teste de cadastro de Pessa

@SpringBootTest
public class JUnitEstudo02 {
	
	@Test
	public void criarCadastroDePessoa(){
		// Cenário
		CadastroPessoa cadastro = new CadastroPessoa();
		
		// Teste
		Assertions.assertThat(cadastro.getPessoas()).isEmpty();
		
	}
	
	@Test
	public void adicioandoUmaPessoaNoCadastro() {
		// Cenário
		CadastroPessoa cadastro = new CadastroPessoa();
		Pessoa pessoa = new Pessoa();
		// Execução
		cadastro.adicionar(pessoa);
		
		// Teste
		Assertions.assertThat(cadastro.getPessoas())
													.isNotEmpty()
													.hasSize(1)
													.contains(pessoa);
		
	}
	
	@Test
	public void naoDeveAdicionarNomeVazio() {
		// Cenário
		CadastroPessoa cadastro = new CadastroPessoa();
		Pessoa pessoa = new Pessoa();
		pessoa.setName("Hugo");
		
		// Execução
		cadastro.adicionar(pessoa);
		
		Assertions.assertThat(cadastro);
	}
	
	@Test
	public void removerCadastroDePessoa() {
		CadastroPessoa cadastro = new CadastroPessoa();
		Pessoa pessoa = new Pessoa();
		
		cadastro.remover(pessoa);
		
		Assertions.assertThat(cadastro.getPessoas()).isEmpty();
		
	}
	
	public void erroAoLancarPessoaInexistente() {
		CadastroPessoa cadastro = new CadastroPessoa();
		Pessoa pessoa = new Pessoa();
		pessoa.setName("Hugo");
		
		cadastro.remover(pessoa);
	}
	

}
