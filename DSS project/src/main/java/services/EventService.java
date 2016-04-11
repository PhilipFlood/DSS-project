package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entities.Event;
import entities.EventCause;
import entities.FailureClass;
import entities.MobileType;
import entities.Network;

@Local
public interface  EventService {
	
		abstract void addToDataset (ArrayList<Event> baseData);
		
		abstract Collection<Event> getAllEvents();
		
		abstract Collection<String> getAllImsi();
		
		abstract Collection<Object[]> getIMSIBtwnDates(Date date1, Date date2);

		abstract Collection<Object[]> getEventCauseByIMSI(String imsi);

		abstract int getFailureCountByModel(String mobileType, Date date1, Date date2);

		abstract int getFailureCountByimsi(String imsi, Date date1, Date date2);

		abstract Collection<Object[]>FailureCodeEventIdByModel(String model);

		abstract Collection<Object[]> get10MarketOperatorCellID(Date date1, Date date2);

		abstract Collection<String> searchByFailureClass(int failureClass);
		
		abstract Collection<String> top10failuresbyIMSI(Date date1, Date date2);
	
		abstract Collection<Integer> searchUniqueCauseCodeByIMSI(String imsi);
}
