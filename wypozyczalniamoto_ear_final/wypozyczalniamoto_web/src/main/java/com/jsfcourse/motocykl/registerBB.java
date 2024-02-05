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

import com.jsf.dao.rolaDAO;
import com.jsf.dao.uzytkownikDAO;
import com.jsf.entities.Uzytkownik;
@Named 
@RequestScoped
public class registerBB {
	
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String PAGE_MOTOCYKL_LIST = "motocyklList?faces-redirect=true";
	private static final String PAGE_LOGIN = "login?faces-redirect=true";
	
	private String login;
	
	private String haslo;
	
	private String haslo2;

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
	
	public String getHaslo2() {
		return haslo2;
	}

	public void setHaslo2(String haslo2) {
		this.haslo2 = haslo2;
	}


	@EJB
	rolaDAO rolaDAO;
	
	@EJB
	uzytkownikDAO uzytkownikDAO;
	
	@Inject
	FacesContext fcscontext;
	
	@Inject
	ExternalContext extcontext;
	
	public String rejestracja() {
		if(haslo.equals(haslo2)) {
			List<Uzytkownik> list = uzytkownikDAO.getFullList();
			
			for(Uzytkownik u:list) {
				if(login.equals(u.getEmail())) {
					fcscontext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Osoba z takim email'em juz istnieje", null));
					return PAGE_STAY_AT_THE_SAME;
				}
			}
			Uzytkownik uz = new Uzytkownik();
			uz.setEmail(login);
			uz.setHaslo(haslo);
			uz.setRola(rolaDAO.find(1));
			uzytkownikDAO.create(uz);
			return PAGE_LOGIN;
		} else {
			fcscontext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hasła róznią się", null));
			return PAGE_STAY_AT_THE_SAME;
		}
	}
}

