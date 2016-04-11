package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import entities.FailureClass;

@Local
public interface FailureClassService {
	
	abstract List<FailureClass> searchFailureClass(int failclass);
	abstract Collection<FailureClass> getAllFailureClass();
	abstract void addFailureClass(ArrayList<FailureClass> failureClassTable);

}
