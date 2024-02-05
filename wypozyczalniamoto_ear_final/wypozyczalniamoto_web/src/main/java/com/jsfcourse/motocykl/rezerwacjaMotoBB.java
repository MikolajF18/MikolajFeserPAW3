package com.jsfcourse.motocykl;

import java.io.IOException;
import java.util.Date;

import com.jsf.entities.Motocykl;
import com.jsf.entities.Rezerwacja;
import com.jsf.entities.Uzytkownik;
import com.jsf.dao.rezerwacjaDAO;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;


@Named 
@RequestScoped
public class rezerwacjaMotoBB {
	
	private static final String PAGE_REGISTER = "rezerwacjaMoto?faces-redirect=true";
	private static final String PAGE_MOTOCYKL_LIST = "motocyklList?faces-redirect=true";
	
	private Integer iledni;

	private Date termin;
	
	public Integer getIledni() {
		return iledni;
	}
	
	public void setIledni(Integer iledni) {
		this.iledni = iledni;
	}
	
	public Date getTermin() {
		return termin;
	}

	public void setTermin(Date termin) {
		this.termin = termin;
	}
	
	@Inject
    Flash flash;
	
	@Inject
	FacesContext fcscontext;
	
	@Inject
	ExternalContext extcontext;
	
	@EJB
	rezerwacjaDAO rezerwacjaDAO;

	public void onInputChanged(ValueChangeEvent event) {
	       
    }
	
	public String rezerwacjaSave(Motocykl motocykl) {
		HttpSession session = (HttpSession) extcontext.getSession(true);
        session.setAttribute("motocykl", motocykl);
        return PAGE_REGISTER;
	}
	
	public String rezerwacjaT() {
		
		Uzytkownik uzytkownik = (Uzytkownik) extcontext.getSessionMap().get("user");
		Motocykl motocykl = (Motocykl) extcontext.getSessionMap().get("motocykl");
		
		Rezerwacja r = new Rezerwacja();
		r.setDatarezerwacji(termin);
		r.setNailedni(iledni);
		r.setMotocykl(motocykl);
		r.setUzytkownik(uzytkownik);
		rezerwacjaDAO.create(r);
		
		return PAGE_MOTOCYKL_LIST;
	}
}
