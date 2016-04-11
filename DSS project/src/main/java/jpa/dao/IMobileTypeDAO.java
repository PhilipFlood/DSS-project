package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.MobileType;

@Local
@Stateless
public interface IMobileTypeDAO {
	
	List<MobileType> searchTac(int tac);

	Collection<MobileType> getAllMobileTypes();
	
	void addMobileType(ArrayList<MobileType> mobileType);
	
	Collection<Object[]> getAllMobileModels();

}
