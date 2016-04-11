package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.FailureClass;

@Local
@Stateless
public interface IFailureClassDAO {
	
	List<FailureClass> searchFailureClass(int failclass);
	
	abstract void addFailureClass(ArrayList<FailureClass> f);
	Collection<FailureClass> getAllFailureClass();

}
