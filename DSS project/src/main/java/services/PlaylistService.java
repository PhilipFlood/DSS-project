package services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Playlist;

@Local
public interface PlaylistService {

	abstract void addPlaylist(Playlist Playlist);	
	abstract void deletePlaylist(Playlist Playlist);	
	abstract Collection<Playlist> getAllPlaylists();
	abstract Playlist getPlaylistByName(String Playlistname);	
}
