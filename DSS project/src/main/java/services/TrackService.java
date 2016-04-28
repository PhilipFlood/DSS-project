package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import entities.Track;

@Local
public interface TrackService {

	abstract void addTrack(ArrayList<Track> Track);	
	abstract void deleteTrack(Track Track);	
	abstract Collection<Track> getAllTracks();
	abstract Track getTrackByID(int trackID);	
	abstract List<Track> searchPlaylistTracks(int playlist);
}
