package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import entities.FailureClass;
import jpa.dao.IFailureClassDAO;

@Stateless
public class FailureClassServiceEJB implements FailureClassService{
	
	@Inject
	private IFailureClassDAO failureClassDao;
	
	public List<FailureClass> searchFailureClass(int failclass){
		return failureClassDao.searchFailureClass(failclass);
	}

	public IFailureClassDAO getFailureClassDao() {
		return failureClassDao;
	}

	public void setFailureClassDao(IFailureClassDAO failureClassDao) {
		this.failureClassDao = failureClassDao;
	}

	@Override
	public Collection<FailureClass> getAllFailureClass() {
		
		return failureClassDao.getAllFailureClass();
	}

	@Override
	public void addFailureClass(ArrayList<FailureClass> f) {
		failureClassDao.addFailureClass(f);
		
	}

}
