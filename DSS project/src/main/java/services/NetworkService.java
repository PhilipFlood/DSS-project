package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import entities.Network;

@Local
public interface NetworkService {

	abstract List<Network> searchMCCAndMNC(int mcc, int mnc);
	abstract void addNetwork(ArrayList<Network> networkTable);
	abstract Collection<Network>getAllNetworks();

}
