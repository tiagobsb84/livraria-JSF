package com.tiago.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.tiago.dao.UsuarioDao;
import com.tiago.model.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuarLogin() {
		System.out.println("Fazendo login do usuário " 
				+ this.usuario.getEmail());
		
		boolean existe = new UsuarioDao().existe(this.usuario);
		
		if(existe) {
			return "livro?faces-redirect=true";
		}
			
		return null;
	}
}
