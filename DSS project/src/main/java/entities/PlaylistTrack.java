package entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="PlaylistTrack")

public class PlaylistTrack implements Serializable {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;

	@ManyToOne 
	@JoinColumn(name="playlistID", referencedColumnName="playlistID", nullable = false)
	//@JsonIgnore 
	private Playlist playlist;

	@ManyToOne 
	@JoinColumn(name="trackID", referencedColumnName="trackID", nullable = false)
	//@JsonIgnore 
	private Track track;
	
	public PlaylistTrack(){}

	public PlaylistTrack(int id) {
		Id = id;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}
}
