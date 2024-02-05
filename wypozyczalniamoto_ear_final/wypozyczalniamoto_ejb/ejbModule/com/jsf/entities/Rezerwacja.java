package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the rezerwacja database table.
 * 
 */
@Entity
@NamedQuery(name="Rezerwacja.findAll", query="SELECT r FROM Rezerwacja r")
public class Rezerwacja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idrezerwacji;

	@Temporal(TemporalType.DATE)
	private Date datarezerwacji;

	private int nailedni;

	//bi-directional many-to-one association to Motocykl
	@ManyToOne
	private Motocykl motocykl;

	//bi-directional many-to-one association to Uzytkownik
	@ManyToOne
	private Uzytkownik uzytkownik;

	public Rezerwacja() {
	}

	public int getIdrezerwacji() {
		return this.idrezerwacji;
	}

	public void setIdrezerwacji(int idrezerwacji) {
		this.idrezerwacji = idrezerwacji;
	}

	public Date getDatarezerwacji() {
		return this.datarezerwacji;
	}

	public void setDatarezerwacji(Date datarezerwacji) {
		this.datarezerwacji = datarezerwacji;
	}

	public int getNailedni() {
		return this.nailedni;
	}

	public void setNailedni(int nailedni) {
		this.nailedni = nailedni;
	}

	public Motocykl getMotocykl() {
		return this.motocykl;
	}

	public void setMotocykl(Motocykl motocykl) {
		this.motocykl = motocykl;
	}

	public Uzytkownik getUzytkownik() {
		return this.uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

}