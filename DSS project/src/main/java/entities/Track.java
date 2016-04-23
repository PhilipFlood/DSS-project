package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="Track")

public class Track implements Serializable {
		
	@Id
	@Column(name="trackID")private int trackID;
	@Column(name="name") private String name;
	@Column(name="artist") private String artist;
	@Column(name="album") private String album;
	@Column(name="genre") private String genre;	
	
	@OneToMany(mappedBy="track")
	//@JsonIgnore
	private List<PlaylistTrack> trackPlaylists = new ArrayList<PlaylistTrack>();
	
	public Track(){}

	public Track(int trackID, String name, String artist, String album,	String genre) {
		super();
		this.trackID = trackID;
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
	}

	public int getTrackID() {
		return trackID;
	}

	public void setTrackID(int trackID) {
		this.trackID = trackID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<PlaylistTrack> getTrackPlaylists() {
		return trackPlaylists;
	}

	public void setTrackPlaylists(List<PlaylistTrack> trackPlaylists) {
		this.trackPlaylists = trackPlaylists;
	}
}
