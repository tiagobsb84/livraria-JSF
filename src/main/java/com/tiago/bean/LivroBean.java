package com.tiago.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.tiago.dao.AutorDao;
import com.tiago.dao.LivroDao;
import com.tiago.model.Autor;
import com.tiago.model.Livro;
import com.tiago.tx.Transacional;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Livro livro = new Livro();
	
	private Integer autorId;
	
	private List<Livro> livros;
	
	@Inject
	private LivroDao livroDao;
	
	@Inject
	private AutorDao autorDao;
	
	@Inject
	private FacesContext context;
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	//Lista todos livros
	public List<Livro> getLivros() {
		livroDao.listaTodos();
		
		if(this.livros == null) {
			this.livros = this.livroDao.listaTodos();
		}
		
		return livros; 
	}
	
	//Lista todos autores
	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
	}
	
	//Lista de autores selecionado
	public List<Autor> getAutoresDoLivro() {
        return this.livro.getAutores();
    }
	
	//CarregarLivroPeloId
	public void carregarLivroPeloId() {
		this.livro = this.livroDao.buscaPorId(this.livro.getId());
	}
	
	
	//Buscando autor pelo ID
	public void gravarAutor() {
		Autor autor = this.autorDao.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
	}
	
	//Salvar no banco de dados o livro
	@Transacional
	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor", 
					new FacesMessage("Livro deve ter pelo menos um autor"));
			return;
		}
		
		if(this.livro.getId() == null) {
			livroDao.adiciona(this.livro);
			this.livros = this.livroDao.listaTodos();
			
		} else {
			this.livroDao.atualiza(livro);
		}

		this.livro = new Livro();
	}
	
	//Remover livro
	@Transacional
	public void remover(Livro livro) {
		this.livroDao.remove(livro);
		
		this.livros = this.livroDao.listaTodos();
	}
	
	//remover autor do livro
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	//Alterar livro
	public void carregar(Livro livro) {
		this.livro = livro;
	}
	
	//Link para chamar o formulario autor
	public String formAutor() {
		return "autor?faces-redirect=true";
	}
	
	//Validando para comecar com digito 1
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		
		String valor = value.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("O ISBN deveria começar com 1"));
		}
	}
}
