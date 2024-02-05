package com.jsfcourse.motocykl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.jsf.dao.rezerwacjaDAO;
import com.jsf.entities.Rezerwacja;
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
public class rezerwacjeListBB {
	private static final String PAGE_MOTOCYKL_EDIT = "rezerwacjaEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String model;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	rezerwacjaDAO rezerwacjaDAO;
		
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Rezerwacja> getFullList(){
		return rezerwacjaDAO.getFullList();
	}
	
	public List<Rezerwacja> getList(){
		List<Rezerwacja> list = null;
		
		Uzytkownik user = (Uzytkownik) extcontext.getSessionMap().get("user");
		list = rezerwacjaDAO.getListForUser(user.getIduzytkownika());
		
		return list;
	}
	
	public String newRezerwacja(){
		Rezerwacja rezerwacja = new Rezerwacja();
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("rezerwacja", rezerwacja);
		
		return PAGE_MOTOCYKL_EDIT;
	}

	public String editRezerwacja(Rezerwacja rezerwacja){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("rezerwacja", rezerwacja);
		
		return PAGE_MOTOCYKL_EDIT;
	}

	public String deleteRezerwacja(Rezerwacja rezerwacja){
		rezerwacjaDAO.remove(rezerwacja);
		return PAGE_STAY_AT_THE_SAME;
	}
}

