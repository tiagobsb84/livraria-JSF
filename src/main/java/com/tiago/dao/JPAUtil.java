package com.tiago.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria");
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close(EntityManager em) {
		em.close();
	}
}
