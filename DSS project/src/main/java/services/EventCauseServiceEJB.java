package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import entities.EventCause;
import entities.MobileType;
import jpa.dao.EventCauseDAO;
import jpa.dao.IEventCauseDAO;

@Local
@Stateless
public class EventCauseServiceEJB implements EventCauseService {
	
	@Inject
	private IEventCauseDAO eventCauseDao;
	
	public List<EventCause> searchCausedCodeAndEventID(int causeCode, int eventId){
		return eventCauseDao.searchCausedCodeAndEventID(causeCode, eventId);
	}

	public IEventCauseDAO getEventCauseDao() {
		return eventCauseDao;
	}

	public void setEventCauseDao(IEventCauseDAO eventCauseDao) {
		this.eventCauseDao = eventCauseDao;
	}

	@Override
	public void addEventCause(ArrayList<EventCause> eventCause) {
		eventCauseDao.addEventCause(eventCause);
		
	}

	@Override
	public Collection<EventCause> getAllEventCauses() {
		
		return eventCauseDao.getAllEventCauses();
	}

}
