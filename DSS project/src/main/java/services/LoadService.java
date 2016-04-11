package services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.ejb.Local;

import entities.EventCause;

@Local
public interface LoadService {
	
	abstract void addAllData(File path) throws IOException, ParseException;

}
