package wypozyczalniamoto;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the pojemnosc database table.
 * 
 */
@Entity
@NamedQuery(name="Pojemnosc.findAll", query="SELECT p FROM Pojemnosc p")
public class Pojemnosc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpojemnosci;

	private int pojemnosc;

	//bi-directional many-to-one association to Motocykl
	@OneToMany(mappedBy="pojemnosc")
	private List<Motocykl> motocykls;

	public Pojemnosc() {
	}

	public int getIdpojemnosci() {
		return this.idpojemnosci;
	}

	public void setIdpojemnosci(int idpojemnosci) {
		this.idpojemnosci = idpojemnosci;
	}

	public int getPojemnosc() {
		return this.pojemnosc;
	}

	public void setPojemnosc(int pojemnosc) {
		this.pojemnosc = pojemnosc;
	}

	public List<Motocykl> getMotocykls() {
		return this.motocykls;
	}

	public void setMotocykls(List<Motocykl> motocykls) {
		this.motocykls = motocykls;
	}

	public Motocykl addMotocykl(Motocykl motocykl) {
		getMotocykls().add(motocykl);
		motocykl.setPojemnosc(this);

		return motocykl;
	}

	public Motocykl removeMotocykl(Motocykl motocykl) {
		getMotocykls().remove(motocykl);
		motocykl.setPojemnosc(null);

		return motocykl;
	}

}