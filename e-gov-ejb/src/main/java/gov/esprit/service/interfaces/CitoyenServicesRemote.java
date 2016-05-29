package gov.esprit.service.interfaces;

import javax.ejb.Remote;

import gov.esprit.domain.Citoyen;

@Remote
public interface CitoyenServicesRemote {
	public void ajouterCitoyen(Citoyen citoyen);
}
