package com.jsfcourse.motocykl;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.rolaDAO;
import com.jsf.dao.uzytkownikDAO;
import com.jsf.entities.Uzytkownik;

@Named
@ViewScoped
public class UzytkownikEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_UZYTKOWNIK_LIST = "uzytkownikList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Uzytkownik uzytkownik = new Uzytkownik();
	private Uzytkownik loaded = null;

	@EJB
	uzytkownikDAO uzytkownikDAO;
	
	@EJB
	rolaDAO rolaDAO;
	
	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}

	public void onLoad() throws IOException {
		// 1. load uzytkownik passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("uzytkownik");

		// 2. load uzytkownik passed through flash
		loaded = (Uzytkownik) flash.get("uzytkownik");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			uzytkownik = loaded;
			// session.removeAttribute("uzytkownik");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("uzytkownikList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			System.out.println("loaded");
			return PAGE_STAY_AT_THE_SAME;
		}else {
			uzytkownik.setRola(rolaDAO.find(1));
		}

		try {
			System.out.println("try");
			if (uzytkownik.getIduzytkownika() == 0) {
				
				// new record
				uzytkownikDAO.create(uzytkownik);
			} else {
				// existing record
				uzytkownikDAO.merge(uzytkownik);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_UZYTKOWNIK_LIST;
	}
}

