package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jpa.dao.IFailedEntryDAO;
import entities.FailedEntry;

@Local
@Stateless
public class FailedEntryServiceEJB  implements FailedEntryService{
	
	@Inject 
	IFailedEntryDAO failedEntryDAO;
	
	@Override
	public void addToDataset(ArrayList<FailedEntry> event) {
		failedEntryDAO.addData(event);
	}

	@Override
	public Collection<FailedEntry> getAllEvents() {
		
		return failedEntryDAO.getAllEvents();
	}

}
