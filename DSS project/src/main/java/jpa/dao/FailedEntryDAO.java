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

import entities.Event;
import entities.FailedEntry;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FailedEntryDAO implements IFailedEntryDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addData(ArrayList<FailedEntry> events) {
		for(FailedEntry e : events){
			em.persist(e);
		}
		
	}

	@Override
	public Collection<FailedEntry> getAllEvents() {
		Query query  = em.createQuery("select id,date,eventId,failureClass,tac,mcc,mnc,cellId,duration,causeCode,neVersion,imsi from FailedEntry");
		List<FailedEntry> result = query.getResultList();
		return result;	
	}
}

