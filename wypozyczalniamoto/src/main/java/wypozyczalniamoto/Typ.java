package wypozyczalniamoto;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the typ database table.
 * 
 */
@Entity
@NamedQuery(name="Typ.findAll", query="SELECT t FROM Typ t")
public class Typ implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtypu;

	private String typ;

	//bi-directional many-to-one association to Motocykl
	@OneToMany(mappedBy="typ")
	private List<Motocykl> motocykls;

	public Typ() {
	}

	public int getIdtypu() {
		return this.idtypu;
	}

	public void setIdtypu(int idtypu) {
		this.idtypu = idtypu;
	}

	public String getTyp() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public List<Motocykl> getMotocykls() {
		return this.motocykls;
	}

	public void setMotocykls(List<Motocykl> motocykls) {
		this.motocykls = motocykls;
	}

	public Motocykl addMotocykl(Motocykl motocykl) {
		getMotocykls().add(motocykl);
		motocykl.setTyp(this);

		return motocykl;
	}

	public Motocykl removeMotocykl(Motocykl motocykl) {
		getMotocykls().remove(motocykl);
		motocykl.setTyp(null);

		return motocykl;
	}

}