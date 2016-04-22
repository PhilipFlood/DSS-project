package entities;

public class Track {
	
	int trackid;
	String name;
	String artist;
	String album;
	String genre;
	
	public Track(int trackid, String name, String artist, String album,String genre) {
		super();
		this.trackid = trackid;
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
	}

	public int getTrackid() {
		return trackid;
	}

	public void setTrackid(int trackid) {
		this.trackid = trackid;
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
}
