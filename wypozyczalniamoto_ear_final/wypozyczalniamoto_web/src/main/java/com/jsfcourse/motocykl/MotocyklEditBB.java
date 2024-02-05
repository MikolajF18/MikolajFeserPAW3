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

import com.jsf.dao.motocyklDAO;
import com.jsf.entities.Motocykl;

@Named
@ViewScoped
public class MotocyklEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_MOTOCYKL_LIST = "motocyklList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Motocykl motocykl = new Motocykl();
	private Motocykl loaded = null;

	@EJB
	motocyklDAO motocyklDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Motocykl getMotocykl() {
		return motocykl;
	}

	public void onLoad() throws IOException {
		// 1. load motocykl passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("motocykl");

		// 2. load motocykl passed through flash
		loaded = (Motocykl) flash.get("motocykl");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			motocykl = loaded;
			// session.removeAttribute("motocykl");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("motocyklList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			System.out.println("loaded");
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			System.out.println("try");
			if (motocykl.getIdmotocykla() == null) {
				
				// new record
				motocyklDAO.create(motocykl);
			} else {
				// existing record
				motocyklDAO.merge(motocykl);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_MOTOCYKL_LIST;
	}
}

