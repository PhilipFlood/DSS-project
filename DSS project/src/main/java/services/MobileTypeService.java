package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import entities.MobileType;

@Local
public interface MobileTypeService {
	
	abstract List<MobileType> searchTac(int tac);
	abstract Collection<MobileType> getAllMobileTypes();
	abstract void addMobileType(ArrayList<MobileType> mobileTypeTable);
	abstract Collection<Object[]> getAllMobileModels();

}
