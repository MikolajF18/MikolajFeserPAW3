package wypozyczalniamoto;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the marka database table.
 * 
 */
@Entity
@NamedQuery(name="Marka.findAll", query="SELECT m FROM Marka m")
public class Marka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmarki;

	private String marka;

	//bi-directional many-to-one association to Motocykl
	@OneToMany(mappedBy="marka")
	private List<Motocykl> motocykls;

	public Marka() {
	}

	public int getIdmarki() {
		return this.idmarki;
	}

	public void setIdmarki(int idmarki) {
		this.idmarki = idmarki;
	}

	public String getMarka() {
		return this.marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public List<Motocykl> getMotocykls() {
		return this.motocykls;
	}

	public void setMotocykls(List<Motocykl> motocykls) {
		this.motocykls = motocykls;
	}

	public Motocykl addMotocykl(Motocykl motocykl) {
		getMotocykls().add(motocykl);
		motocykl.setMarka(this);

		return motocykl;
	}

	public Motocykl removeMotocykl(Motocykl motocykl) {
		getMotocykls().remove(motocykl);
		motocykl.setMarka(null);

		return motocykl;
	}

}