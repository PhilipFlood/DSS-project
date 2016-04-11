package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import entities.Event;
import entities.EventCause;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EventDAO implements IEventDAO {

	@PersistenceContext
	private EntityManager em;

	public Collection<Event> getAllEvents() {
		Query query  = em.createQuery("from Event");
		List<Event> result = query.getResultList();
		return result;
	}
	
	public Collection<String> getAllImsi() {
		Query query  = em.createQuery("select distinct imsi from Event e");
		List<String> result = query.getResultList();
		return result;
	}
 
	@Override
	public void addData(ArrayList<Event> events) {
		for(Event e: events){
			em.persist(e);	
		}	
	}
	/*
	@Override
	public Collection<EventCause> getEventCauseByIMSI(String imsi) {
		Query query  = em.createQuery("select distinct ec.causeCode , ec.eventId , ec.description from Event e , EventCause ec where e.eventId = ec.eventId and e.causeCode = ec.causeCode and imsi = :imsi");
		query.setParameter("imsi", imsi);
		
		return query.getResultList();
	}

	@Override
	public Collection<Object[]> getIMSIBetweenDates(Date date1, Date date2) {
		Query query  = em.createQuery("select imsi ,sum(duration),count(imsi) from Event e where date between :startDate and :endDate group by imsi");

		query.setParameter("startDate", date1);
		query.setParameter("endDate", date2);
		
		List result = query.getResultList();
		return result;
	}
	

	@Override
	public int getFailureCountByModel(String mobileType, Date date1, Date date2) {
		Query query  = em.createQuery("from Event where tac = (select tac from MobileType where marketingName =:mobileType) and date between :startDate and :endDate");
		query.setParameter("startDate", date1);
		query.setParameter("endDate", date2);
		query.setParameter("mobileType", mobileType);
		return query.getResultList().size();
	}

	@Override
	public int getFailureCountByimsi(String imsi, Date date1, Date date2) {
		Query query  = em.createQuery("from Event where imsi = :imsi and date between :startDate and :endDate");
		query.setParameter("startDate", date1);
		query.setParameter("endDate", date2);
		query.setParameter("imsi", imsi);
		return query.getResultList().size();
	}

	@Override
	public Collection<Object[]> failureCodeEventIdByModel(String model) {
		Query query = em.createQuery("select eventId , causeCode ,count(eventId) from Event e where tac = (select tac from MobileType where marketingName =:model) group by eventId , causeCode");
		query.setParameter("model", model);
		
		return query.getResultList();
	}

	
	
	public Collection<Object[]> get10MarketOperatorCellID(Date date1, Date date2) {
		Query query  = em.createQuery("select mcc, mnc, cellId, count(mcc), (select count(mcc) from Event) as percent from Event where date between :startDate and :endDate group by mcc, mnc, cellID order by count(mcc) DESC").setMaxResults(10);
		
		query.setParameter("startDate", date1);
		query.setParameter("endDate", date2);
		
		List result = query.getResultList();
		;
		return result;
	}
	
	public Collection<String> searchByFailureClass(int failureClass) {
		Query query  = em.createQuery("select distinct imsi from Event where failureClass = :failureClass");
		query.setParameter("failureClass", failureClass);
		List<String> result = query.getResultList();		
		return result;
	}
	

	@Override
	public Collection<String> top10failuresbyIMSI(Date date1, Date date2) {
		Query query  = em.createQuery("select imsi from Event where date between :startDate and :endDate group by imsi order by count(imsi) DESC").setMaxResults(10);
		query.setParameter("startDate", date1);
		query.setParameter("endDate", date2);
		List<String> result = query.getResultList();
		return result;
	}
	
	public Collection<Integer> searchUniqueCauseCodeByIMSI(String imsi) {
		Query query  = em.createQuery("select distinct causeCode from Event where imsi = :imsi");
		query.setParameter("imsi", imsi);
		List<Integer> result = query.getResultList();		
		return result;
	}*/

	@Override
	public Collection<Object[]> getEventCauseByIMSI(String imsi) {
		Query query  = em.createQuery("select distinct eventCause.causeCode , eventCause.eventId , eventCause.description from Event where imsi =:imsi");
		query.setParameter("imsi", imsi);
		
		return query.getResultList();
	}

	@Override
	public Collection<Object[]> getIMSIBetweenDates(Date date1, Date date2) {
		Query query  = em.createQuery("select imsi ,sum(duration),count(imsi) from Event e where date between :startDate and :endDate group by imsi");

		query.setParameter("startDate", date1);
		query.setParameter("endDate", date2);
		
		List result = query.getResultList();
		return result;
	}
	

	@Override
	public int getFailureCountByModel(String model, Date date1, Date date2) {
		Query query  = em.createQuery("from Event where mobileType.marketingName =:model and date between :startDate and :endDate");
		query.setParameter("startDate", date1);
		query.setParameter("endDate", date2);
		query.setParameter("model", model);
		return query.getResultList().size();
	}

	@Override
	public int getFailureCountByimsi(String imsi, Date date1, Date date2) {
		Query query  = em.createQuery("from Event where imsi = :imsi and date between :startDate and :endDate");
		query.setParameter("startDate", date1);
		query.setParameter("endDate", date2);
		query.setParameter("imsi", imsi);
		return query.getResultList().size();
	}

	@Override
	public Collection<Object[]> failureCodeEventIdByModel(String model) {
		Query query = em.createQuery("select eventCause.eventId , eventCause.causeCode ,count(eventCause.eventId) from Event e where mobileType.marketingName =:model group by eventCause.eventId , eventCause.causeCode");
		query.setParameter("model", model);
		
		return query.getResultList();
	}

	public Collection<Object[]> get10MarketOperatorCellID(Date date1, Date date2) {
		Query query  = em.createQuery("select network.mcc, network.mnc, cellId, network.operator, network.country , count(network.mcc), (select count(id) from Event) from Event e where date between :startDate and :endDate group by network.mcc, network.mnc, cellID order by count(network.mcc) DESC").setMaxResults(10);

		query.setParameter("startDate", date1);
		query.setParameter("endDate", date2);
		
		List result = query.getResultList();
		return result;
	}
	
	public Collection<String> searchByFailureClass(int failureClass) {
		Query query  = em.createQuery("select distinct imsi from Event where fclass.failureClass = :failureClass");
		query.setParameter("failureClass", failureClass);
		List<String> result = query.getResultList();		
		return result;
	}
	

	@Override
	public Collection<String> top10failuresbyIMSI(Date date1, Date date2) {
		Query query  = em.createQuery("select imsi from Event where date between :startDate and :endDate group by imsi order by count(imsi) DESC").setMaxResults(10);
		query.setParameter("startDate", date1);
		query.setParameter("endDate", date2);
		List<String> result = query.getResultList();
		return result;
	}
	
	public Collection<Integer> searchUniqueCauseCodeByIMSI(String imsi) {
		Query query  = em.createQuery("select distinct eventCause.causeCode from Event where imsi = :imsi");
		query.setParameter("imsi", imsi);
		List<Integer> result = query.getResultList();		
		return result;
	}

}
