package wypozyczalniamoto;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the motocykl database table.
 * 
 */
@Entity
@NamedQuery(name="Motocykl.findAll", query="SELECT m FROM Motocykl m")
public class Motocykl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmotocykla;

	private String czydostepny;

	private int moc;

	private String model;

	private int rokprodukcji;

	//bi-directional many-to-one association to Marka
	@ManyToOne
	private Marka marka;

	//bi-directional many-to-one association to Pojemnosc
	@ManyToOne
	private Pojemnosc pojemnosc;

	//bi-directional many-to-one association to Typ
	@ManyToOne
	private Typ typ;

	//bi-directional many-to-one association to Rezerwacja
	@OneToMany(mappedBy="motocykl")
	private List<Rezerwacja> rezerwacjas;

	public Motocykl() {
	}

	public int getIdmotocykla() {
		return this.idmotocykla;
	}

	public void setIdmotocykla(int idmotocykla) {
		this.idmotocykla = idmotocykla;
	}

	public String getCzydostepny() {
		return this.czydostepny;
	}

	public void setCzydostepny(String czydostepny) {
		this.czydostepny = czydostepny;
	}

	public int getMoc() {
		return this.moc;
	}

	public void setMoc(int moc) {
		this.moc = moc;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getRokprodukcji() {
		return this.rokprodukcji;
	}

	public void setRokprodukcji(int rokprodukcji) {
		this.rokprodukcji = rokprodukcji;
	}

	public Marka getMarka() {
		return this.marka;
	}

	public void setMarka(Marka marka) {
		this.marka = marka;
	}

	public Pojemnosc getPojemnosc() {
		return this.pojemnosc;
	}

	public void setPojemnosc(Pojemnosc pojemnosc) {
		this.pojemnosc = pojemnosc;
	}

	public Typ getTyp() {
		return this.typ;
	}

	public void setTyp(Typ typ) {
		this.typ = typ;
	}

	public List<Rezerwacja> getRezerwacjas() {
		return this.rezerwacjas;
	}

	public void setRezerwacjas(List<Rezerwacja> rezerwacjas) {
		this.rezerwacjas = rezerwacjas;
	}

	public Rezerwacja addRezerwacja(Rezerwacja rezerwacja) {
		getRezerwacjas().add(rezerwacja);
		rezerwacja.setMotocykl(this);

		return rezerwacja;
	}

	public Rezerwacja removeRezerwacja(Rezerwacja rezerwacja) {
		getRezerwacjas().remove(rezerwacja);
		rezerwacja.setMotocykl(null);

		return rezerwacja;
	}

}