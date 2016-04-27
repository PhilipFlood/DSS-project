package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import entities.Playlist;

@Local
public interface PlaylistService {

	abstract void addPlaylist(ArrayList<Playlist>  Playlist);	
	abstract void deletePlaylist(Playlist Playlist);	
	abstract Collection<Playlist> getAllPlaylists();
	abstract Playlist getPlaylistByName(String Playlistname);	
	abstract List<Playlist> searchPlaylists(String library);
}
