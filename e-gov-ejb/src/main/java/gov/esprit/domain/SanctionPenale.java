package gov.esprit.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Entity implementation class for Entity: SanctionPenale
 *
 */
@Entity

public class SanctionPenale implements Serializable {

	   
	private int id;
	private Citoyen citoyen;
	private LocalDateTime date;
	private int amende;
	private int dureePrison;
	private static final long serialVersionUID = 1L;

	public SanctionPenale() {
		super();
	} 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	@ManyToOne
	@JoinColumn(name="citoyenId",referencedColumnName="id", updatable = true, insertable = true)
	public Citoyen getCitoyen() {
		return this.citoyen;
	}

	public void setCitoyen(Citoyen citoyen) {
		this.citoyen = citoyen;
	}   
	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}   
	public int getAmende() {
		return this.amende;
	}

	public void setAmende(int amende) {
		this.amende = amende;
	}   
	public int getDureePrison() {
		return this.dureePrison;
	}

	public void setDureePrison(int dureePrison) {
		this.dureePrison = dureePrison;
	}
   
}
