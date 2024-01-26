package com.jsf.dao;

import java.util.List;
import java.util.Map;

import com.jsf.entities.Motocykl;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class motocyklDAO {
	
	@PersistenceContext
    EntityManager em;

	public void create(Motocykl motocykl) {
		em.persist(motocykl);
	}

	public Motocykl merge(Motocykl motocykl) {
		return em.merge(motocykl);
	}

	public void remove(Motocykl motocykl) {
		em.remove(em.merge(motocykl));
	}

	public Motocykl find(Object id) {
		return em.find(Motocykl.class, id);
	}

	public List<Motocykl> getFullList() {
		List<Motocykl> list = null;

		Query query = em.createQuery("select p from Motocykl p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Motocykl> getList(Map<String, Object> searchParams) {
		List<Motocykl> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Motocykl p ";
		String where = "";

		// search for surname
		String model = (String) searchParams.get("model");
		if (model != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.model like :model ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where);

		// 3. Set configured parameters
		if (model != null) {
			query.setParameter("model", model+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
