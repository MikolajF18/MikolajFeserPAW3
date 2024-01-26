package com.jsfcourse.motocykl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.jsf.dao.motocyklDAO;
import com.jsf.entities.Motocykl;

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
public class MotocyklListBB {
	private static final String PAGE_MOTOCYKL_EDIT = "motocyklEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String model;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	motocyklDAO motocyklDAO;
		
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Motocykl> getFullList(){
		return motocyklDAO.getFullList();
	}
	
	public List<Motocykl> getList(){
		List<Motocykl> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (model != null && model.length() > 0){
			searchParams.put("model", model);
		}
		
		//2. Get list
		list = motocyklDAO.getList(searchParams);
		
		return list;
	}
	
	public String newMotocykl(){
		Motocykl motocykl = new Motocykl();
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("motocykl", motocykl);
		
		return PAGE_MOTOCYKL_EDIT;
	}

	public String editMotocykl(Motocykl motocykl){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("motocykl", motocykl);
		
		return PAGE_MOTOCYKL_EDIT;
	}

	public String deleteMotocykl(Motocykl motocykl){
		motocyklDAO.remove(motocykl);
		return PAGE_STAY_AT_THE_SAME;
	}
}
