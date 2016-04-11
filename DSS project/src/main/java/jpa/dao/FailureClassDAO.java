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

import entities.FailureClass;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FailureClassDAO implements IFailureClassDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<FailureClass> searchFailureClass(int failclass){
		Query query  = em.createQuery("from FailureClass where failureClass = :failureClass");
		query.setParameter("failureClass", failclass);
		List<FailureClass> result = query.getResultList();
		return result;
	}

	@Override
	public Collection<FailureClass> getAllFailureClass() {
		Query query  = em.createQuery("from FailureClass");
		List<FailureClass> result = query.getResultList();
		return result;
	}

	@Override
	public void addFailureClass(ArrayList<FailureClass> failureClasses) {
		for (FailureClass failureClass : failureClasses){
			em.merge(failureClass);
		}
		
	}
}
