package jpa.dao;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Track;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TrackDAO implements ITrackDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addTrack(Track Track) {
		em.merge(Track);		
	}

	@Override
	public void deleteTrack(Track Track) {
		em.remove(em.contains(Track) ? Track : em.merge(Track));
	}

	@Override
	public Collection<Track> getAllTracks() {
		Query query  = em.createQuery("from Track");
		List<Track> listOfAllTracks = query.getResultList();
		return listOfAllTracks;
	}
	
	@Override
	public Track getTrackByName(String Trackname) {
		Query query  = em.createQuery("from Track where Trackname = :Trackname");
		query.setParameter("Trackname", Trackname);

		if(query.getResultList().size()>0){
			Track Track =  (Track) query.getResultList().get(0);
			return Track;
		}
		return null;
	}
}
