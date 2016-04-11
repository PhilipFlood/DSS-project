package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.MobileType;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MobileTypeDAO implements IMobileTypeDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<MobileType> searchTac(int tac){
		Query query  = em.createQuery("from MobileType where tac = :tac");
		query.setParameter("tac", tac);
		List<MobileType> result = query.getResultList();
		return result;
	}

	@Override
	public Collection<MobileType> getAllMobileTypes() {
		Query query  = em.createQuery("from MobileType ");
		Collection<MobileType> result =query.getResultList();
		return result;
	}

	@Override
	public void addMobileType(ArrayList<MobileType> mobileTypes) {
		
		for(MobileType mobileType:mobileTypes){	
			em.merge(mobileType);
		}
		
		
	}
	
	public Collection<Object[]> getAllMobileModels() {
		Query query  = em.createQuery("select distinct marketingName, manufacturer from MobileType ");
		return query.getResultList();
	}
	

}
