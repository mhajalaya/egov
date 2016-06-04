package gov.esprit.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import gov.esprit.enums.Civilite;
import gov.esprit.enums.Sex;



/**
 * Entity implementation class for Entity: Citoyen
 *
 */
@Entity

public class Citoyen implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String prenom;
	private LocalDateTime dateNaissance;
	private LocalDateTime dateDeces;
	private Sex sex;
	private Civilite civilite;
	private String profession;
	private String adresse;
	private Citoyen pere;
	private Citoyen mere;
	
	private List<Permis> permis;
	private Passeport passeport;
	private String extraitNaissance;
	private List<SanctionPenale> sanctions;
	private List<Vehicule> vehicules;
	private List<Compte>comptes;
	private List<Demande>demandes;
	private List<AbonnementTransport> abonnementTransports;
	
	@Column(unique=true, length=8)
	private String cin;

	private static final long serialVersionUID = 1L;

	public Citoyen() {
		super();
	}  

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public LocalDateTime getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(LocalDateTime dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public LocalDateTime getDateDeces() {
		return dateDeces;
	}
	public void setDateDeces(LocalDateTime dateDeces) {
		this.dateDeces = dateDeces;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Civilite getCivilite() {
		return civilite;
	}
	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	@OneToOne
	public Citoyen getPere() {
		return pere;
	}
	public void setPere(Citoyen pere) {
		this.pere = pere;
	}
	@OneToOne
	public Citoyen getMere() {
		return mere;
	}
	public void setMere(Citoyen mere) {
		this.mere = mere;
	}
	@OneToMany(mappedBy="citoyen")
	public List<AbonnementTransport> getAbonnementTransports() {
		return abonnementTransports;
	}
	public void setAbonnementTransports(List<AbonnementTransport> abonnementTransports) {
		this.abonnementTransports = abonnementTransports;
	}
	@OneToMany(mappedBy="citoyen")
	public List<Permis> getPermis() {
		return permis;
	}
	
	public void setPermis(List<Permis> permis) {
		this.permis = permis;
	}
	@OneToOne
	public Passeport getPasseport() {
		return passeport;
	}
	public void setPasseport(Passeport passeport) {
		this.passeport = passeport;
	}
	public String getExtraitNaissance() {
		return extraitNaissance;
	}
	public void setExtraitNaissance(String extraitNaissance) {
		this.extraitNaissance = extraitNaissance;
	}
	@OneToMany(mappedBy="citoyen")
	public List<SanctionPenale> getSanctions() {
		return sanctions;
	}
	public void setSanctions(List<SanctionPenale> sanctions) {
		this.sanctions = sanctions;
	}
	@OneToMany(mappedBy="proprietaire")
	public List<Vehicule> getVehicules() {
		return vehicules;
	}
	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}
	@OneToMany(mappedBy="proprietaire")
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	@OneToMany(mappedBy="citoyen")
	public List<Demande> getDemandes() {
		return demandes;
	}
	public void setDemandes(List<Demande> demandes) {
		this.demandes = demandes;
	}
	
	/**
	 * @return the cin
	 */
	public String getCin() {
		return cin;
	}

	/**
	 * @param cin the cin to set
	 */
	public void setCin(String cin) {
		this.cin = cin;
	}
}