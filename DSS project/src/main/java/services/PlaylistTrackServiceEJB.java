package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jpa.dao.IPlaylistTrackDAO;
import entities.PlaylistTrack;


@Local
@Stateless
public class PlaylistTrackServiceEJB implements PlaylistTrackService {

	@Inject
	private IPlaylistTrackDAO PlaylistTrackDao;
		
	public PlaylistTrack getPlaylistTrackByID(int playlistID, int trackID) {
		return PlaylistTrackDao.getPlaylistTrackByID(playlistID, trackID);
	}
	
	public Collection<PlaylistTrack> getAllPlaylistTracks() {
		return PlaylistTrackDao.getAllPlaylistTracks();
	}

	public void addPlaylistTrack(ArrayList<PlaylistTrack> PlaylistTrack) {
		PlaylistTrackDao.addPlaylistTrack(PlaylistTrack);		
	}

	public void deletePlaylistTrack(PlaylistTrack PlaylistTrack){
		PlaylistTrackDao.deletePlaylistTrack(PlaylistTrack);
	}
	
	public IPlaylistTrackDAO getPlaylistTrackDao() {
		return PlaylistTrackDao;
	}

	public void setPlaylistTrackDao(IPlaylistTrackDAO PlaylistTrackDao) {
		this.PlaylistTrackDao = PlaylistTrackDao;
	}	
	
}
