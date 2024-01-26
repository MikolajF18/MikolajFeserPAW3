package com.jsf.dao;

import java.util.List;
import java.util.Map;

import com.jsf.entities.Rola;
import com.jsf.entities.Uzytkownik;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class rolaDAO {
	
	@PersistenceContext
    EntityManager em;

	public Rola find(Object id) {
		return em.find(Rola.class, id);
	}

	
}


