package wypozyczalniamoto;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the uzytkownik database table.
 * 
 */
@Entity
@NamedQuery(name="Uzytkownik.findAll", query="SELECT u FROM Uzytkownik u")
public class Uzytkownik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iduzytkownika;

	private String email;

	private String haslo;

	private String imie;

	private String kategorieprawajazdy;

	private String nazwisko;

	private String nrprawajazdy;

	private String nrtelefonu;

	private String pesel;

	//bi-directional many-to-one association to Rezerwacja
	@OneToMany(mappedBy="uzytkownik")
	private List<Rezerwacja> rezerwacjas;

	//bi-directional many-to-one association to Rola
	@ManyToOne
	private Rola rola;

	public Uzytkownik() {
	}

	public int getIduzytkownika() {
		return this.iduzytkownika;
	}

	public void setIduzytkownika(int iduzytkownika) {
		this.iduzytkownika = iduzytkownika;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHaslo() {
		return this.haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getKategorieprawajazdy() {
		return this.kategorieprawajazdy;
	}

	public void setKategorieprawajazdy(String kategorieprawajazdy) {
		this.kategorieprawajazdy = kategorieprawajazdy;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getNrprawajazdy() {
		return this.nrprawajazdy;
	}

	public void setNrprawajazdy(String nrprawajazdy) {
		this.nrprawajazdy = nrprawajazdy;
	}

	public String getNrtelefonu() {
		return this.nrtelefonu;
	}

	public void setNrtelefonu(String nrtelefonu) {
		this.nrtelefonu = nrtelefonu;
	}

	public String getPesel() {
		return this.pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public List<Rezerwacja> getRezerwacjas() {
		return this.rezerwacjas;
	}

	public void setRezerwacjas(List<Rezerwacja> rezerwacjas) {
		this.rezerwacjas = rezerwacjas;
	}

	public Rezerwacja addRezerwacja(Rezerwacja rezerwacja) {
		getRezerwacjas().add(rezerwacja);
		rezerwacja.setUzytkownik(this);

		return rezerwacja;
	}

	public Rezerwacja removeRezerwacja(Rezerwacja rezerwacja) {
		getRezerwacjas().remove(rezerwacja);
		rezerwacja.setUzytkownik(null);

		return rezerwacja;
	}

	public Rola getRola() {
		return this.rola;
	}

	public void setRola(Rola rola) {
		this.rola = rola;
	}

}