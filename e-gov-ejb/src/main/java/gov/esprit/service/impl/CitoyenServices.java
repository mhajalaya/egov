package gov.esprit.service.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gov.esprit.domain.Citoyen;
import gov.esprit.service.interfaces.CitoyenServicesLocal;
import gov.esprit.service.interfaces.CitoyenServicesRemote;

/**
 * Session Bean implementation class CitoyenService
 */
@Stateless
@LocalBean
public class CitoyenServices implements CitoyenServicesRemote, CitoyenServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;
    public CitoyenServices() {
        // TODO Auto-generated constructor stub
    }
    
    
	public void ajouterCitoyen(Citoyen citoyen) {
		entityManager.persist(citoyen);
	}
    

}
