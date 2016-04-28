package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jpa.dao.ITrackDAO;
import entities.Track;


@Local
@Stateless
public class TrackServiceEJB implements TrackService {

	@Inject
	private ITrackDAO TrackDao;
		
	public Track getTrackByID(int trackID) {
		return TrackDao.getTrackByID(trackID);
	}
	
	public Collection<Track> getAllTracks() {
		return TrackDao.getAllTracks();
	}

	public void addTrack(ArrayList<Track> Track) {
		TrackDao.addTrack(Track);		
	}

	public void deleteTrack(Track Track){
		TrackDao.deleteTrack(Track);
	}
	
	public ITrackDAO getTrackDao() {
		return TrackDao;
	}

	public void setTrackDao(ITrackDAO TrackDao) {
		this.TrackDao = TrackDao;
	}	
	
	public List<Track> searchPlaylistTracks(int playlist){
		return TrackDao.searchPlaylistTracks(playlist);
	}
}
