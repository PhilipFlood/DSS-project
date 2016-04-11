package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Network;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class NetworkDAO implements INetworkDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Network> searchMCCAndMNC(int mcc, int mnc){
		Query query  = em.createQuery("from Network where mcc = :mcc and mnc = :mnc");
		query.setParameter("mcc", mcc);
		query.setParameter("mnc", mnc);
		List<Network> result = query.getResultList();
		return result;
	}

	@Override
	public void addNetwork(ArrayList<Network> networks) {

		for(Network network:networks){
			em.merge(network);
		}
		
	}

	@Override
	public Collection<Network> getAllNetworks() {
		Query query  = em.createQuery("from Network");
		List<Network> result = query.getResultList();
		return result;
	}

}
