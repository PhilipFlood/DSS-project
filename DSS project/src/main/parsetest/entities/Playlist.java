package entities;

import java.util.ArrayList;

public class Playlist {

	int playlistID;
	String name;
	ArrayList<Integer> tracks = new ArrayList<Integer>();
	
	public Playlist(int playlistID, String name, ArrayList<Integer> tracks) {
		super();
		this.playlistID = playlistID;
		this.tracks = tracks;
		this.name = name;
	}
	
	public int getPlaylistID() {
		return playlistID;
	}
	public void setPlaylistID(int playlistID) {
		this.playlistID = playlistID;
	}
	public ArrayList<Integer> getTracks() {
		return tracks;
	}
	public void setTracks(ArrayList<Integer> tracks) {
		this.tracks = tracks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
