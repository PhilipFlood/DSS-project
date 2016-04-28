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

import entities.Playlist;
import entities.Track;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TrackDAO implements ITrackDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addTrack(ArrayList<Track> Tracks) {
		for (Track track: Tracks){
			em.merge(track);
		}		
	}

	@Override
	public void deleteTrack(Track Track) {
		em.remove(em.contains(Track) ? Track : em.merge(Track));
	}

	@Override
	public Collection<Track> getAllTracks() {
		Query query  = em.createQuery("select trackID, name, artist,album, genre from Track");
		List<Track> listOfAllTracks = query.getResultList();
		return listOfAllTracks;
	}
	
	@Override
	public Track getTrackByID(int trackID) {
		Query query  = em.createQuery("from Track where trackID = :trackID");
		query.setParameter("trackID", trackID);

		if(query.getResultList().size()>0){
			Track Track =  (Track) query.getResultList().get(0);
			return Track;
		}
		return null;
	}
	
	@Override
	public List<Track> searchPlaylistTracks(int playlist){
		Query query  = em.createQuery("select track.trackID, track.name, track.artist, track.album, track.genre from PlaylistTrack where playlist.playlistID = :playlist");
		query.setParameter("playlist", playlist);
		List<Track> result = query.getResultList();
		return result;
	}
}
