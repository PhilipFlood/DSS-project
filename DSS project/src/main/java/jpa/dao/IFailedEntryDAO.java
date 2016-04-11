package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.FailedEntry;


@Local
@Stateless
public interface IFailedEntryDAO {

	abstract void addData(ArrayList<FailedEntry> event);
	Collection<FailedEntry> getAllEvents();
}
