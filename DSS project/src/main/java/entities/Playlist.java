package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity @Table(name="Library")

public class Playlist implements Serializable {	
	
	@Id
	@Column(name="libraryID") private int libraryID;
	@Column(name="persistanceID") private String persistanceID;
	
	@ManyToOne 
	@JoinColumn(name="User", referencedColumnName="username", nullable = false)
	private User user;
	
	@OneToMany(mappedBy="library")
	private List <Playlist> playlists = new ArrayList<Playlist>() ;

	public Playlist(){}

	public Playlist(int libraryID, String persistanceID, List<Playlist> playlists) {
		this.libraryID = libraryID;
		this.persistanceID = persistanceID;
	}

	public int getLibraryID() {
		return libraryID;
	}

	public void setLibraryID(int libraryID) {
		this.libraryID = libraryID;
	}

	public String getPersistanceID() {
		return persistanceID;
	}

	public void setPersistanceID(String persistanceID) {
		this.persistanceID = persistanceID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
}
