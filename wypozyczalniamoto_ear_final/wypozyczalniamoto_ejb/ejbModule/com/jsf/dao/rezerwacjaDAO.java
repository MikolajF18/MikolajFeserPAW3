package com.jsf.dao;

import java.util.List;
import java.util.Map;

import com.jsf.entities.Rezerwacja;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class rezerwacjaDAO {
	
	@PersistenceContext
    EntityManager em;

	public void create(Rezerwacja rezerwacja) {
		em.persist(rezerwacja);
	}

	public Rezerwacja merge(Rezerwacja rezerwacja) {
		return em.merge(rezerwacja);
	}

	public void remove(Rezerwacja rezerwacja) {
		em.remove(em.merge(rezerwacja));
	}

	public Rezerwacja find(Object id) {
		return em.find(Rezerwacja.class, id);
	}

	public List<Rezerwacja> getFullList() {
		List<Rezerwacja> list = null;

		Query query = em.createQuery("select p from Rezerwacja p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Rezerwacja> getList(Map<String, Object> searchParams) {
		List<Rezerwacja> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Rezerwacja p ";
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
	
	public List<Rezerwacja> getListForUser(Integer f) {
		List<Rezerwacja> list = null;

        Query query = em.createQuery("select u from Rezerwacja u WHERE u.uzytkownik.iduzytkownika =" + f);

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
	}

}

