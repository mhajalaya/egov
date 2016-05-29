package gov.esprit.service.interfaces;

import javax.ejb.Local;

import gov.esprit.domain.Citoyen;

@Local
public interface CitoyenServicesLocal {
	public void ajouterCitoyen(Citoyen citoyen);
}
