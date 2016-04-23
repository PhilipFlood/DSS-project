///*packageasas entities;
//
//import java.io.Serializable;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.persistence.*;
//
//@Entity @Table(name="Events")
//
//public class Event implements Serializable {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="Id")
//	private int id;
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="Date")
//	private Date date;
//	@Column(name="EventId")
//	private int eventId;
//	@Column(name="FailureClass")
//	private int failureClass;
//	@Column(name="TAC")
//	private int tac;
//	@Column(name="MCC")
//	private int mcc;
//	@Column(name="MNC")
//	private int mnc;
//	@Column(name="CellID")
//	private int cellId;
//	@Column(name="Duration")
//	private int duration;
//	@Column(name="Cause_Code")
//	private int causeCode;
//	@Column(name="NE_Version")
//	private String neVersion;
//	@Column(name="IMSI")
//	private String imsi;
//	@Column(name="HIER3_ID")
//	private String hier3id;
//	@Column(name="HIER32_ID")
//	private String hier32id;
//	@Column(name="HIER321_ID")
//	private String hier321id;
//	
//	public Event(){
//		
//	}
//	
//	public Event(Date date, int eventId, int failureClass, int tac,
//			int mcc, int mnc, int cellId, int duration, int causeCode,
//			String neVersion, String imsi, String hier3id, String hier32id,
//			String hier321id) {
//		this.date = date;
//		this.eventId = eventId;
//		this.failureClass = failureClass;
//		this.tac = tac;
//		this.mcc = mcc;
//		this.mnc = mnc;
//		this.cellId = cellId;
//		this.duration = duration;
//		this.causeCode = causeCode;
//		this.neVersion = neVersion;
//		this.imsi = imsi;
//		this.hier3id = hier3id;
//		this.hier32id = hier32id;
//		this.hier321id = hier321id;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getDate() {
//		DateFormat df = new SimpleDateFormat("yyy-mm-dd HH:MM:SS");
//		
//		return df.format(date);
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//	public int getEventId() {
//		return eventId;
//	}
//
//	public void setEventId(int eventId) {
//		this.eventId = eventId;
//	}
//
//	public int getFailureClass() {
//		return failureClass;
//	}
//
//	public void setFailureClass(int failureClass) {
//		this.failureClass = failureClass;
//	}
//
//	public int getTac() {
//		return tac;
//	}
//
//	public void setTac(int tac) {
//		this.tac = tac;
//	}
//
//	public int getMcc() {
//		return mcc;
//	}
//
//	public void setMcc(int mcc) {
//		this.mcc = mcc;
//	}
//
//	public int getMnc() {
//		return mnc;
//	}
//
//	public void setMnc(int mnc) {
//		this.mnc = mnc;
//	}
//
//	public int getCellId() {
//		return cellId;
//	}
//
//	public void setCellId(int cellId) {
//		this.cellId = cellId;
//	}
//
//	public int getDuration() {
//		return duration;
//	}
//
//	public void setDuration(int duration) {
//		this.duration = duration;
//	}
//
//	public int getCauseCode() {
//		return causeCode;
//	}
//
//	public void setCauseCode(int causeCode) {
//		this.causeCode = causeCode;
//	}
//
//	public String getNeVersion() {
//		return neVersion;
//	}
//
//	public void setNeVersion(String neVersion) {
//		this.neVersion = neVersion;
//	}
//
//	public String getImsi() {
//		return imsi;
//	}
//
//	public void setImsi(String imsi) {
//		this.imsi = imsi;
//	}
//
//	public String getHier3id() {
//		return hier3id;
//	}
//
//	public void setHier3id(String hier3id) {
//		this.hier3id = hier3id;
//	}
//
//	public String getHier32id() {
//		return hier32id;
//	}
//
//	public void setHier32id(String hier32id) {
//		this.hier32id = hier32id;
//	}
//
//	public String getHier321id() {
//		return hier321id;
//	}
//
//	public void setHier321id(String hier321id) {
//		this.hier321id = hier321id;
//	}
//
//}
//*/
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
//@Entity @Table(name="Track")
//
//public class Track implements Serializable {
//		
//	@Id
//	@Column(name="trackID")private int trackID;
//	@Column(name="name") private String name;
//	@Column(name="artist") private String artist;
//	@Column(name="album") private String album;
//	@Column(name="genre") private String genre;	
//	
//	@OneToMany(mappedBy="track")
//	//@JsonIgnore
//	private List<PlaylistTrack> trackPlaylists = new ArrayList<PlaylistTrack>();
//	
//	public Track(){}
//
//	public Track(int trackID, String name, String artist, String album,	String genre) {
//		super();
//		this.trackID = trackID;
//		this.name = name;
//		this.artist = artist;
//		this.album = album;
//		this.genre = genre;
//	}
//
//	public int getTrackID() {
//		return trackID;
//	}
//
//	public void setTrackID(int trackID) {
//		this.trackID = trackID;
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
//	public String getArtist() {
//		return artist;
//	}
//
//	public void setArtist(String artist) {
//		this.artist = artist;
//	}
//
//	public String getAlbum() {
//		return album;
//	}
//
//	public void setAlbum(String album) {
//		this.album = album;
//	}
//
//	public String getGenre() {
//		return genre;
//	}
//
//	public void setGenre(String genre) {
//		this.genre = genre;
//	}
//
//	public List<PlaylistTrack> getTrackPlaylists() {
//		return trackPlaylists;
//	}
//
//	public void setTrackPlaylists(List<PlaylistTrack> trackPlaylists) {
//		this.trackPlaylists = trackPlaylists;
//	}
//}
