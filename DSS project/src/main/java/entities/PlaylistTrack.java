//package entities;
//
//import java.io.Serializable;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.*;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@Entity @Table(name="PlaylistTrack")
//
//public class PlaylistTrack implements Serializable {
//		
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int Id;
//	@Column(name="playlistID")private int playlistID;
//	@Column(name="trackID") private String trackID;
//
//	@ManyToOne 
//	@JoinColumn(name="Playlist", referencedColumnName="playlistID", nullable = false)
//	//@JsonIgnore 
//	private Playlist playlist;
//
//	@ManyToOne 
//	@JoinColumn(name="Track", referencedColumnName="trackID", nullable = false)
//	//@JsonIgnore 
//	private Track track;
//	
//	public PlaylistTrack(){}
//
//	public PlaylistTrack(int id, int playlistID, String trackID) {
//		Id = id;
//		this.playlistID = playlistID;
//		this.trackID = trackID;
//	}
//
//	public int getId() {
//		return Id;
//	}
//
//	public void setId(int id) {
//		Id = id;
//	}
//
//	public int getPlaylistID() {
//		return playlistID;
//	}
//
//	public void setPlaylistID(int playlistID) {
//		this.playlistID = playlistID;
//	}
//
//	public String getNametrackID() {
//		return trackID;
//	}
//
//	public void setNametrackID(String nametrackID) {
//		this.trackID = nametrackID;
//	}
//
//	public Playlist getPlaylist() {
//		return playlist;
//	}
//
//	public void setPlaylist(Playlist playlist) {
//		this.playlist = playlist;
//	}
//
//	public Track getTrack() {
//		return track;
//	}
//
//	public void setTrack(Track track) {
//		this.track = track;
//	}
//}
