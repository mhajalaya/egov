package gov.esprit.service.poste.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import gov.esprit.domain.Citoyen;
import gov.esprit.domain.Compte;
import gov.esprit.domain.Transaction;
import gov.esprit.enums.TypeTransacrion;
import gov.esprit.exception.EgovErrorCode;
import gov.esprit.exception.EgovException;
import gov.esprit.service.citoyen.CitoyenServiceRemote;
import gov.esprit.service.poste.PosteServiceLocal;
import gov.esprit.service.poste.PosteServiceRemote;
import javafx.collections.ObservableList;


@Stateless
@LocalBean
public class PosteService implements PosteServiceRemote, PosteServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	@EJB
	private CitoyenServiceRemote citoyenService;
	

	public PosteService() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public Compte rechercherCompteParNum(int numeroCompte) throws EgovException {
			
		try {
			
			Query query = entityManager.createQuery(
					"select c from Compte c  where c.id = :numeroCompte "
				);
			query.setParameter("numeroCompte", numeroCompte);
			return (Compte) query.getSingleResult();
			
		} catch (Exception e) {
			
			throw new EgovException(EgovErrorCode.DOES_NOT_EXIST_ITEM,"COMPTE");
		}
		
	}
	
	
	@Override
	public float consulterSolde(int numeroCompte) throws EgovException{
		Compte compte = rechercherCompteParNum(numeroCompte);
		return compte.getSolde();
	}
	@Override
	public void effectuerTransaction(int numeroCompte, float montant, String cin, TypeTransacrion type) throws EgovException{
		
		float solde;
		Date date = new Date();
		Compte compte = rechercherCompteParNum(numeroCompte);
		solde=compte.getSolde();
		Transaction transaction = new Transaction();
		transaction.setMontant(montant);
		transaction.setType(type);
		transaction.setCompte(compte);
		transaction.setDate(date);
		if (cin.equals(compte.getProprietaire().getCin())){
			
			if(type==TypeTransacrion.CREDIT){
				solde = solde+montant;
				compte.setSolde(solde);	
			}else{
				if(solde>=montant){
					solde = solde-montant;
					compte.setSolde(solde);
					
				}else{
					throw new EgovException(EgovErrorCode.SOLDE_INSUFFISANT, ": " + solde);
				}
			}
			entityManager.merge(transaction);
			entityManager.persist(compte);
		}else{
			throw new EgovException(EgovErrorCode.OPERATION_NON_AUTHORISEE,": "+cin);
		}
	}
	
	@Override
	public List<Transaction> extraireReleve(int numeroCompte, String cin) throws EgovException {
		
		Compte compte = rechercherCompteParNum(numeroCompte);
		if(compte.getProprietaire().getCin().equals(cin)){

		Query query = entityManager.createQuery("select t from Transaction t  where t.compte.id = :numeroCompte ");
		query.setParameter("numeroCompte", numeroCompte);
		return (List<Transaction>)query.getResultList();
		}else{
			throw new  EgovException(EgovErrorCode.OPERATION_NON_AUTHORISEE,": "+cin);
			}
		
	}
	@Override
	public int ouvrirCompte(String cin) throws EgovException, NamingException{
		
		Citoyen citoyen = citoyenService.findByCin(cin);
		
		/*if(citoyen==null){
			throw new EgovException(EgovErrorCode.INVALID_ITEM, "_CIN: " + cin);
		}*/
		Compte compte = new Compte();
		compte.setProprietaire(citoyen);
		compte.setSolde(0);
		compte.setDateOuverture(new Date());
		entityManager.persist(compte);
		return compte.getId();
	}
	
	@Override
	public List<Compte> findAll() {
		
		Query query = entityManager.createNativeQuery("select * from Compte");
		return query.getResultList();
	}
}
