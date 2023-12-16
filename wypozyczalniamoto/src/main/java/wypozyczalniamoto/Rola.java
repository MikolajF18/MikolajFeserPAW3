package wypozyczalniamoto;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the rola database table.
 * 
 */
@Entity
@NamedQuery(name="Rola.findAll", query="SELECT r FROM Rola r")
public class Rola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idroli;

	private String nazwaroli;

	//bi-directional many-to-one association to Uzytkownik
	@OneToMany(mappedBy="rola")
	private List<Uzytkownik> uzytkowniks;

	public Rola() {
	}

	public int getIdroli() {
		return this.idroli;
	}

	public void setIdroli(int idroli) {
		this.idroli = idroli;
	}

	public String getNazwaroli() {
		return this.nazwaroli;
	}

	public void setNazwaroli(String nazwaroli) {
		this.nazwaroli = nazwaroli;
	}

	public List<Uzytkownik> getUzytkowniks() {
		return this.uzytkowniks;
	}

	public void setUzytkowniks(List<Uzytkownik> uzytkowniks) {
		this.uzytkowniks = uzytkowniks;
	}

	public Uzytkownik addUzytkownik(Uzytkownik uzytkownik) {
		getUzytkowniks().add(uzytkownik);
		uzytkownik.setRola(this);

		return uzytkownik;
	}

	public Uzytkownik removeUzytkownik(Uzytkownik uzytkownik) {
		getUzytkowniks().remove(uzytkownik);
		uzytkownik.setRola(null);

		return uzytkownik;
	}

}