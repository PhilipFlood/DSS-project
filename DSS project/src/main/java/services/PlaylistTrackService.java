package services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.PlaylistTrack;

@Local
public interface PlaylistTrackService {

	abstract void addPlaylistTrack(PlaylistTrack PlaylistTrack);	
	abstract void deletePlaylistTrack(PlaylistTrack PlaylistTrack);	
	abstract Collection<PlaylistTrack> getAllPlaylistTracks();
	abstract PlaylistTrack getPlaylistTrackByName(String PlaylistTrackname);	
}
