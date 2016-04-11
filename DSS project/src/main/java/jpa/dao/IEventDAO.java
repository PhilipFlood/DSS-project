package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Stateless;




import entities.Event;
import entities.EventCause;

@Local
@Stateless
public interface IEventDAO {

	void addData(ArrayList<Event> event);
	Collection<Event> getAllEvents();
	Collection<String> getAllImsi();
	Collection<Object[]> getEventCauseByIMSI(String imsi);
	Collection<Object[]> getIMSIBetweenDates(Date date1 , Date date2);
	int getFailureCountByModel(String mobileType, Date date1, Date date2);
	int getFailureCountByimsi(String imsi, Date date1, Date date2);
	Collection<Object[]>failureCodeEventIdByModel(String model);
	Collection<Object[]> get10MarketOperatorCellID(Date date1 , Date date2);
	Collection<String> searchByFailureClass(int causeCode);
	Collection<String> top10failuresbyIMSI(Date date1, Date date2);
	Collection<Integer> searchUniqueCauseCodeByIMSI(String imsi);
	
}
