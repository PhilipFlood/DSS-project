package services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Track;

@Local
public interface TrackService {

	abstract void addTrack(Track Track);	
	abstract void deleteTrack(Track Track);	
	abstract Collection<Track> getAllTracks();
	abstract Track getTrackByName(String Trackname);	
}
