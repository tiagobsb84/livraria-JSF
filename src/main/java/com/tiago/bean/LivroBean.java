package com.tiago.bean;

import javax.faces.bean.ManagedBean;

import com.tiago.model.Livro;

@ManagedBean
public class LivroBean {

	private Livro livro = new Livro();

	public Livro getLivro() {
		return livro;
	}
	
	public String gravar() {
		System.out.println("Gravando: " + this.livro.getTitulo());
		return "";
	}
}
