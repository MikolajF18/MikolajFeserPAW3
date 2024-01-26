package com.jsfcourse.motocykl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.jsf.dao.uzytkownikDAO;
import com.jsf.entities.Uzytkownik;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Named 
@RequestScoped
public class UzytkownikListBB {
	private static final String PAGE_UZYTKOWNIK_EDIT = "uzytkownikEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String imie;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	uzytkownikDAO uzytkownikDAO;
		
	public String getModel() {
		return imie;
	}

	public void setModel(String imie) {
		this.imie = imie;
	}

	public List<Uzytkownik> getFullList(){
		return uzytkownikDAO.getFullList();
	}
	
	public List<Uzytkownik> getList(){
		List<Uzytkownik> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (imie != null && imie.length() > 0){
			searchParams.put("imie", imie);
		}
		
		//2. Get list
		list = uzytkownikDAO.getList(searchParams);
		
		return list;
	}
	
	public String newUzytkownik(){
		Uzytkownik uzytkownik = new Uzytkownik();
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("uzytkownik", uzytkownik);
		
		return PAGE_UZYTKOWNIK_EDIT;
	}

	public String editUzytkownik(Uzytkownik uzytkownik){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("uzytkownik", uzytkownik);
		
		return PAGE_UZYTKOWNIK_EDIT;
	}

	public String deleteUzytkownik(Uzytkownik uzytkownik){
		uzytkownikDAO.remove(uzytkownik);
		return PAGE_STAY_AT_THE_SAME;
	}
}

