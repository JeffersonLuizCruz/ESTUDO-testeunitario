package com.testeunitario.crudperson;

import java.util.ArrayList;
import java.util.List;

public class CadastroPessoa {
	
	private List<Pessoa> pessoas;
	

	public CadastroPessoa() {
		this.pessoas = new ArrayList<>();
	}



	public List<Pessoa> getPessoas() {
		
		return pessoas;
	}


	public void adicionar(Pessoa pessoa) {
		if( pessoa.getName() == null)
			throw new RuntimeException("Nome não pode ser Vazio");
		this.pessoas.add(pessoa);
		
	}



	public void remover(Pessoa pessoa) {
		this.pessoas.remove(pessoa);
		if(this.pessoas.isEmpty())
			throw new RuntimeException("Erro ao tentar remover pessoa que não existe");
	}

}
