package services;

import java.util.Collection;

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
		
	public Playlist getPlaylistByName(String Playlistname) {
		return PlaylistDao.getPlaylistByName(Playlistname);
	}
	
	public Collection<Playlist> getAllPlaylists() {
		return PlaylistDao.getAllPlaylists();
	}

	public void addPlaylist(Playlist Playlist) {
		PlaylistDao.addPlaylist(Playlist);		
	}

	public void deletePlaylist(Playlist Playlist){
		PlaylistDao.deletePlaylist(Playlist);
	}
	
	public IPlaylistDAO getPlaylistDao() {
		return PlaylistDao;
	}

	public void setPlaylistDao(IPlaylistDAO PlaylistDao) {
		this.PlaylistDao = PlaylistDao;
	}	
	
}
