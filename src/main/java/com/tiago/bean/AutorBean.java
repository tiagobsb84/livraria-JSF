package com.tiago.bean;

import javax.faces.bean.ManagedBean;

import com.tiago.model.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}
	
	public void gravar() {
		System.out.println("Gravando " + this.autor.getNome());
	}
}
