package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Network;

@Local
@Stateless
public interface INetworkDAO {
	
	List<Network> searchMCCAndMNC(int mcc, int mnc);
	abstract void addNetwork(ArrayList<Network> network);
	abstract Collection<Network> getAllNetworks();

}
