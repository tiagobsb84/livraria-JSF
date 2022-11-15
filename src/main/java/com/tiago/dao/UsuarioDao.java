package com.tiago.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.tiago.model.Usuario;

@SuppressWarnings("serial")
public class UsuarioDao implements Serializable {
	
	@Inject
	EntityManager manager;

	public boolean existe(Usuario usuario) {

		
		TypedQuery<Usuario> query = manager.createQuery(
			"select u from Usuario u where u.email = :pEmail and u.senha = :pSenha",
						Usuario.class);

		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());

		try {
			query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

		return true;
	}
}
