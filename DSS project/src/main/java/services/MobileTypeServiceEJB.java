package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import entities.MobileType;
import jpa.dao.IMobileTypeDAO;


@Local
@Stateless
public class MobileTypeServiceEJB implements MobileTypeService{
	
	@Inject
	private IMobileTypeDAO mobileTypeDao;
	
	public List<MobileType> searchTac(int tac){
		return mobileTypeDao.searchTac(tac);
	}

	public IMobileTypeDAO getMobileTypeDao() {
		return mobileTypeDao;
	}

	public void setMobileTypeDao(IMobileTypeDAO mobileTypeDao) {
		this.mobileTypeDao = mobileTypeDao;
	}
	
	public Collection<MobileType> getAllMobileTypes(){
		return mobileTypeDao.getAllMobileTypes();
	}

	@Override
	public void addMobileType(ArrayList<MobileType> mobileType) {
		mobileTypeDao.addMobileType(mobileType);
		
	}
	
	public Collection<Object[]> getAllMobileModels() {
		return mobileTypeDao.getAllMobileModels();
	}

}
