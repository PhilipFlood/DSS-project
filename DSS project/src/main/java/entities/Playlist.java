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
//@Entity @Table(name="Library")
//
//public class Playlist implements Serializable {	
//	
//	@Id
//	@Column(name="playlistID") private int playlistID;
//	@Column(name="name") private String name;
//	@Column(name="libraryID") private int libraryID;
//	
//	@ManyToOne 
//	@JoinColumn(name="Library", referencedColumnName="libraryID", nullable = false)
//	//@JsonIgnore 
//	private Library library;
//
//	@OneToMany(mappedBy="playlist")
//	//@JsonIgnore
//	private List<PlaylistTrack> playlistTracks = new ArrayList<PlaylistTrack>();
//	
//	public Playlist(){}
//
//	public Playlist(int playlistID, String name, int libraryID) {
//		super();
//		this.playlistID = playlistID;
//		this.name = name;
//		this.libraryID = libraryID;
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
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getLibraryID() {
//		return libraryID;
//	}
//
//	public void setLibraryID(int libraryID) {
//		this.libraryID = libraryID;
//	}
//
//	public Library getLibrary() {
//		return library;
//	}
//
//	public void setLibrary(Library library) {
//		this.library = library;
//	}
//
//	public List<PlaylistTrack> getPlaylistTracks() {
//		return playlistTracks;
//	}
//
//	public void setPlaylistTracks(List<PlaylistTrack> playlistTracks) {
//		this.playlistTracks = playlistTracks;
//	}
//
//	
//}
