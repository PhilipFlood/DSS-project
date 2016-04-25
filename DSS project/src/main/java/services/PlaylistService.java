package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Local;

import entities.Playlist;

@Local
public interface PlaylistService {

	abstract void addPlaylist(ArrayList<Playlist>  Playlist);	
	abstract void deletePlaylist(Playlist Playlist);	
	abstract Collection<Playlist> getAllPlaylists();
	abstract Playlist getPlaylistByName(String Playlistname);	
}
