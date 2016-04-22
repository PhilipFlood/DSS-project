package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="PlaylistTrack")

public class PlaylistTrack implements Serializable {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	@Column(name="playlistID")private int trackid;
	@Column(name="trackID") private String name;

	@ManyToOne 
	@JoinColumn(name="Playlist", referencedColumnName="trackid", nullable = false)
	//@JsonIgnore 
	private PlaylistTrack ptrack;

	@ManyToOne 
	@JoinColumn(name="Track", referencedColumnName="trackID", nullable = false)
	//@JsonIgnore 
	private Track track;
	
	
	public PlaylistTrack(){}

}
