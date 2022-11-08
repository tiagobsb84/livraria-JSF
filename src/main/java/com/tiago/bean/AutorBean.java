package com.tiago.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.tiago.dao.DAO;
import com.tiago.model.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	
	private Integer autorId;

	public Autor getAutor() {
		return autor;
	}
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	//Salvar o autor e redirecionar para pagina livro
	public String gravar() {
		System.out.println("Gravando " + this.autor.getNome());
	
		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		
		//para limpar o campo do autor, depois de salvar.
		this.autor = new Autor();
		
		return "livro?faces-redirect=true";
	}
	
	//Lista todos os autores
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	//Carregar Autor
	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.autor = autor;
	}

	//Remover Autor
	public void remover(Autor autor) {
		System.out.println("Removendo autor");
		new DAO<Autor>(Autor.class).remove(autor);
	}
	
	//Carrega Id na URL
	public void carregarAutorPelaId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}
}
