package com.tiago.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.tiago.dao.DAO;
import com.tiago.model.Autor;
import com.tiago.model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();
	
	private Integer autorId;

	public Livro getLivro() {
		return livro;
	}
	
	
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	//Salvar no banco de dados o livro
	public void gravar() {
		System.out.println("Gravando: " + this.livro.getTitulo());
		
		if (livro.getAutores().isEmpty()) {
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um autor"));
			return;
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}
	
	//Buscando autor pelo ID
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}
	
	//Lista de autores selecionado
	public List<Autor> getAutoresDoLivro() {
        return this.livro.getAutores();
    }
	
	//Validando para comecar com digito 1
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		
		String valor = value.toString();
		
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("O ISBN deveria começar com 1"));
		}
	}
}
