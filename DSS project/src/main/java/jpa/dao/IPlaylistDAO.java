package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Playlist;

@Local
@Stateless
public interface IPlaylistDAO {
	void addPlaylist(ArrayList<Playlist> Playlist);
	void deletePlaylist(Playlist Playlist);
	Collection<Playlist> getAllPlaylists();
	Playlist getPlaylistByID(int playlistID);
	List<Playlist> searchPlaylists(String library);
}
