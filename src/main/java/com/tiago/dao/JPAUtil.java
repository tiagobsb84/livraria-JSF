package com.tiago.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria");
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close(@Disposes EntityManager em) {
		em.close();
	}
}
