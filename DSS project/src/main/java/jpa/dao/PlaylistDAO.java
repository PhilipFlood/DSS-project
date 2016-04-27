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

import entities.Library;
import entities.Playlist;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PlaylistDAO implements IPlaylistDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addPlaylist(ArrayList<Playlist> playlists) {
		for (Playlist playlist: playlists){
			em.merge(playlist);
		}	
	}

	@Override
	public void deletePlaylist(Playlist Playlist) {
		em.remove(em.contains(Playlist) ? Playlist : em.merge(Playlist));
	}

	@Override
	public Collection<Playlist> getAllPlaylists() {
		Query query  = em.createQuery("select playlistID, name, library.libraryID from Playlist");
		List<Playlist> listOfAllPlaylists = query.getResultList();
		return listOfAllPlaylists;
	}
	
	@Override
	public Playlist getPlaylistByName(String Playlistname) {
		Query query  = em.createQuery("from Playlist where name = :Playlistname");
		query.setParameter("Playlistname", Playlistname);

		if(query.getResultList().size()>0){
			Playlist Playlist =  (Playlist) query.getResultList().get(0);
			return Playlist;
		}
		return null;
	}
	
	public List<Playlist> searchPlaylists(String library){
		Query query  = em.createQuery("select playlistID, name from Playlist where library.libraryID = :library");
		query.setParameter("library", library);
		List<Playlist> result = query.getResultList();
		return result;
	}
}
