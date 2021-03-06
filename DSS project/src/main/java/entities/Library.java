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
public class Library implements Serializable {	

	@Id
	@Column(name="libraryID") private String libraryID;

	@ManyToOne 
	@JoinColumn(name="username", referencedColumnName="username", nullable = false)
	private User owner;
	
	@OneToMany(mappedBy="library", orphanRemoval=true)
	//@JsonIgnore
	private List<Playlist> playlists = new ArrayList<Playlist>();

	public Library(){}

	public Library(String libraryID) {
		this.libraryID = libraryID;
	}

	public String getLibraryID() {
		return libraryID;
	}

	public void setLibraryID(String libraryID) {
		this.libraryID = libraryID;
	}

	public User getUser() {
		return owner;
	}

	public void setUser(User user) {
		this.owner = user;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
}
