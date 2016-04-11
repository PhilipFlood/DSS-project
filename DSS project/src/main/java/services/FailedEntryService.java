package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;

import entities.FailedEntry;

@Local
public interface FailedEntryService {

	abstract void addToDataset (ArrayList<FailedEntry> failedData);
		
	abstract Collection<FailedEntry> getAllEvents();

}
