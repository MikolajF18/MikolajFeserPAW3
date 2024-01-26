package com.jsf.dao;

import java.util.List;
import java.util.Map;

import com.jsf.entities.Uzytkownik;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class uzytkownikDAO {
	
	@PersistenceContext
    EntityManager em;

	public void create(Uzytkownik uzytkownik) {
		em.persist(uzytkownik);
	}

	public Uzytkownik merge(Uzytkownik uzytkownik) {
		return em.merge(uzytkownik);
	}

	public void remove(Uzytkownik uzytkownik) {
		em.remove(em.merge(uzytkownik));
	}

	public Uzytkownik find(Object id) {
		return em.find(Uzytkownik.class, id);
	}

	public List<Uzytkownik> getFullList() {
		List<Uzytkownik> list = null;

		Query query = em.createQuery("select p from Uzytkownik p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Uzytkownik> getList(Map<String, Object> searchParams) {
		List<Uzytkownik> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Uzytkownik p ";
		String where = "";

		// search for surname
		String imie = (String) searchParams.get("imie");
		if (imie != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.imie like :imie ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where);

		// 3. Set configured parameters
		if (imie != null) {
			query.setParameter("imie", imie+"%");
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

