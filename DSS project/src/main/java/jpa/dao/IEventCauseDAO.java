package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.EventCause;

@Local
@Stateless
public interface IEventCauseDAO {
	
	List<EventCause> searchCausedCodeAndEventID(int causeCode, int eventId);

	void addEventCause(ArrayList<EventCause> eventCause);

	Collection<EventCause> getAllEventCauses();

}
