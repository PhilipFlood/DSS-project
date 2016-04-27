package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Track;

@Local
@Stateless
public interface ITrackDAO {
	void addTrack(ArrayList<Track> Track);
	void deleteTrack(Track Track);
	Collection<Track> getAllTracks();
	Track getTrackByName(String Trackname);
	List<Track> searchPlaylistTracks(int playlist);
}
