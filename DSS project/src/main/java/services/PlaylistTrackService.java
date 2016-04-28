package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;

import entities.PlaylistTrack;

@Local
public interface PlaylistTrackService {

	abstract void addPlaylistTrack(ArrayList<PlaylistTrack>  PlaylistTrack);	
	abstract void deletePlaylistTrack(PlaylistTrack PlaylistTrack);	
	abstract Collection<PlaylistTrack> getAllPlaylistTracks();
	abstract PlaylistTrack getPlaylistTrackByID(int playlistID, int trackID);	
}
