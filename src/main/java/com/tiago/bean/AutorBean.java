package com.tiago.bean;

import javax.faces.bean.ManagedBean;

import com.tiago.dao.DAO;
import com.tiago.model.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}
	
	public void gravar() {
		System.out.println("Gravando " + this.autor.getNome());
	
		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		//para limpar o campo do autor, depois de salvar.
		this.autor = new Autor();
	}
}
