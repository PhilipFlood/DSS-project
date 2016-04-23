package jpa.dao;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.PlaylistTrack;

@Local
@Stateless
public interface IPlaylistTrackDAO {
	void addPlaylistTrack(PlaylistTrack PlaylistTrack);
	void deletePlaylistTrack(PlaylistTrack PlaylistTrack);
	Collection<PlaylistTrack> getAllPlaylistTracks();
	PlaylistTrack getPlaylistTrackByName(String PlaylistTrackname);
}