package com.tiago.bean;

import javax.faces.bean.ManagedBean;

import com.tiago.dao.DAO;
import com.tiago.model.Livro;

@ManagedBean
public class LivroBean {

	private Livro livro = new Livro();

	public Livro getLivro() {
		return livro;
	}
	
	public void gravar() {
		System.out.println("Gravando: " + this.livro.getTitulo());
		
		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}
}
