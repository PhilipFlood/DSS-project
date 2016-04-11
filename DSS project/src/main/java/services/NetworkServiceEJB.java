package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jpa.dao.INetworkDAO;
import entities.Network;

@Local
@Stateless
public class NetworkServiceEJB implements NetworkService{
	
	@Inject
	private INetworkDAO networkDao;
	
	public List<Network> searchMCCAndMNC(int mcc, int mnc){
		return networkDao.searchMCCAndMNC(mcc, mnc);
	}

	public INetworkDAO getNetworkDao() {
		return networkDao;
	}

	public void setNetworkDao(INetworkDAO networkDao) {
		this.networkDao = networkDao;
	}

	@Override
	public void addNetwork(ArrayList<Network> network) {
		Collection<Network>networks = this.getAllNetworks();
		
			networkDao.addNetwork(network);
		
	}

	@Override
	public Collection<Network> getAllNetworks() {
		
		return networkDao.getAllNetworks();
	}

}
