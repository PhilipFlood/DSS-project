package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jpa.dao.IPlaylistDAO;
import entities.Playlist;


@Local
@Stateless
public class PlaylistServiceEJB implements PlaylistService {

	@Inject
	private IPlaylistDAO PlaylistDao;
		
	public Playlist getPlaylistByID(int playlistID) {
		return PlaylistDao.getPlaylistByID(playlistID);
	}
	
	public Collection<Playlist> getAllPlaylists() {
		return PlaylistDao.getAllPlaylists();
	}

	public void addPlaylist(ArrayList<Playlist>  Playlist) {
		PlaylistDao.addPlaylist(Playlist);		
	}

	public void deletePlaylist(Playlist Playlist){
		PlaylistDao.deletePlaylist(Playlist);
	}
	
	public List<Playlist> searchPlaylists(String library){
		return PlaylistDao.searchPlaylists(library);
	}
	
	public IPlaylistDAO getPlaylistDao() {
		return PlaylistDao;
	}

	public void setPlaylistDao(IPlaylistDAO PlaylistDao) {
		this.PlaylistDao = PlaylistDao;
	}	
	
	
	
}
