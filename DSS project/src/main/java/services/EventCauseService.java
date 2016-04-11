package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import entities.EventCause;
import entities.MobileType;

@Local
public interface EventCauseService {
	
	abstract List<EventCause> searchCausedCodeAndEventID(int causeCode, int eventId);

	abstract void addEventCause(ArrayList<EventCause> eventCauseTable);

	abstract Collection<EventCause> getAllEventCauses();

}
