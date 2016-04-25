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

import entities.PlaylistTrack;
import entities.Track;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PlaylistTrackDAO implements IPlaylistTrackDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addPlaylistTrack(ArrayList<PlaylistTrack> PlaylistTracks) {
		for (PlaylistTrack ptrack: PlaylistTracks){
			em.merge(ptrack);
		}		
	}

	@Override
	public void deletePlaylistTrack(PlaylistTrack PlaylistTrack) {
		em.remove(em.contains(PlaylistTrack) ? PlaylistTrack : em.merge(PlaylistTrack));
	}

	@Override
	public Collection<PlaylistTrack> getAllPlaylistTracks() {
		Query query  = em.createQuery("select Id, playlist.playlistID , track.trackID from PlaylistTrack");
		List<PlaylistTrack> listOfAllPlaylistTracks = query.getResultList();
		return listOfAllPlaylistTracks;
	}
	
	@Override
	public PlaylistTrack getPlaylistTrackByName(String PlaylistTrackname) {
		Query query  = em.createQuery("from PlaylistTrack where PlaylistTrackname = :PlaylistTrackname");
		query.setParameter("PlaylistTrackname", PlaylistTrackname);

		if(query.getResultList().size()>0){
			PlaylistTrack PlaylistTrack =  (PlaylistTrack) query.getResultList().get(0);
			return PlaylistTrack;
		}
		return null;
	}
}
