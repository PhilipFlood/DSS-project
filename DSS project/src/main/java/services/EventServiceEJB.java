package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import entities.*;
import jpa.dao.*;

@Local
@Stateless
public class EventServiceEJB implements EventService{
	
	@Inject
	private IEventDAO eventDao;

	public Collection<Event> getAllEvents() {
		return eventDao.getAllEvents();
	}
	
	public Collection<String> getAllImsi() {
		return eventDao.getAllImsi();
	}

	public void addToDataset(ArrayList<Event> event) {
		eventDao.addData(event);
				
	}

	public IEventDAO getEventDao() {
		return eventDao;
	}

	public void setEventDao(IEventDAO eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public Collection<Object[]> getIMSIBtwnDates(Date date1, Date date2) {
		
		return eventDao.getIMSIBetweenDates(date1, date2);
	}

	@Override
	public Collection<Object[]> getEventCauseByIMSI(String imsi) {
		
		return eventDao.getEventCauseByIMSI(imsi);
	}

	@Override
	public int getFailureCountByModel(String mobileType, Date date1, Date date2) {
		
		return eventDao.getFailureCountByModel(mobileType,date1,date2);
	}

	@Override
	public int getFailureCountByimsi(String imsi, Date date1, Date date2) {
		
		return eventDao.getFailureCountByimsi(imsi,date1,date2);
	}

	@Override
	public Collection<Object[]> FailureCodeEventIdByModel(String model) {
		
		return eventDao.failureCodeEventIdByModel(model);
	}
	
	public Collection<Object[]> get10MarketOperatorCellID(Date date1, Date date2) {
		return eventDao.get10MarketOperatorCellID(date1, date2);
	}
		
	public Collection<String> searchByFailureClass(int failureClass) {	
		return eventDao.searchByFailureClass(failureClass);

	}	
	
	public Collection<String> top10failuresbyIMSI(Date date1, Date date2) {
		return eventDao.top10failuresbyIMSI(date1, date2);
	}
	
	@Override
	public Collection<Integer> searchUniqueCauseCodeByIMSI(String imsi) {
		
		return eventDao.searchUniqueCauseCodeByIMSI(imsi);
	}
}
