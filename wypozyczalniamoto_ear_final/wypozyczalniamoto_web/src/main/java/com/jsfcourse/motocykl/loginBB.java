package com.jsfcourse.motocykl;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import com.jsf.dao.uzytkownikDAO;
import com.jsf.entities.Uzytkownik;
@Named 
@RequestScoped
public class loginBB {
	
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String PAGE_MOTOCYKL_LIST = "motocyklList?faces-redirect=true";
	private static final String PAGE_LOGIN = "login?faces-redirect=true";
	
	private String login;
	
	private String haslo;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	
	@EJB
	uzytkownikDAO uzytkownikDAO;
	
	@Inject
	FacesContext fcscontext;
	
	@Inject
	ExternalContext extcontext;
	
	public String logowanie() {
		List<Uzytkownik> list = uzytkownikDAO.getFullList();
		
		for(Uzytkownik u:list) {
			if(u.getHaslo().equals(haslo) && u.getEmail().equals(login)) {
				HttpSession session = (HttpSession) extcontext.getSession(true);
                session.setAttribute("user", u);
				return PAGE_MOTOCYKL_LIST;
			}
		}
		fcscontext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bledne logowanie", null));
		return PAGE_STAY_AT_THE_SAME;
	}
	
	public String wylogowanie() {
		HttpSession session = (HttpSession) extcontext.getSession(true);
        session.invalidate();

        return PAGE_LOGIN;
	}
}
