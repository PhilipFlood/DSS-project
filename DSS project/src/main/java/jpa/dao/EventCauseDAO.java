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

import entities.EventCause;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EventCauseDAO implements IEventCauseDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<EventCause> searchCausedCodeAndEventID(int causeCode, int eventId){
		Query query  = em.createQuery("from EventCause where causeCode = :causeCode and eventId = :eventId");
		query.setParameter("causeCode", causeCode);
		query.setParameter("eventId", eventId);
		List<EventCause> result = query.getResultList();
		return result;
	}

	@Override
	public void addEventCause(ArrayList<EventCause> eventCauses) {
		
		for(EventCause eventCause:eventCauses){
			em.merge(eventCause);
		}
		
	}

	@Override
	public Collection<EventCause> getAllEventCauses() {
		Query query  = em.createQuery("from EventCause");
		return query.getResultList();
	}

}
