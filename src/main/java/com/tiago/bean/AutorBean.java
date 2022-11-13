package com.tiago.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.tiago.dao.AutorDao;
import com.tiago.model.Autor;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();

	private Integer autorId;
	
	@Inject
	private AutorDao dao;
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	//Salvar o autor e redirecionar para pagina livro
	public String gravar() {
		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}
		
		//para limpar o campo do autor, depois de salvar.
		this.autor = new Autor();
		
		return "livro?faces-redirect=true";
	}
	
	//Lista todos os autores
	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}
	
	//Carregar Autor
	public void carregar(Autor autor) {
		this.autor = autor;
	}

	//Remover Autor
	public void remover(Autor autor) {
		this.dao.remove(autor);
	}
	
	//Carrega Id na URL
	public void carregarAutorPelaId() {
		this.autor = this.dao.buscaPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
}
