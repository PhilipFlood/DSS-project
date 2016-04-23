package jpa.dao;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Playlist;

@Local
@Stateless
public interface IPlaylistDAO {
	void addPlaylist(Playlist Playlist);
	void deletePlaylist(Playlist Playlist);
	Collection<Playlist> getAllPlaylists();
	Playlist getPlaylistByName(String Playlistname);
}
