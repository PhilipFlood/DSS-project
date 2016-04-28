package jaxrs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jaxb.entities.Array;
import jaxb.entities.Dict;
import jaxb.entities.Key;
import jaxb.entities.Plist;
import jpa.dao.IUserDAO;
import services.LibraryService;
import services.PlaylistService;
import services.PlaylistTrackService;
import services.TrackService;
import services.UserService;
import entities.Library;
import entities.Playlist;
import entities.PlaylistTrack;
import entities.Track;
import entities.User;

@Path("/query")
public class DataQuery {
	@Inject
	private TrackService trackService;
	@Inject
	private PlaylistTrackService playlistTrackService;
	@Inject
	private PlaylistService playlistService;
	@Inject
	private LibraryService libraryService;
	@Inject 
	private UserService userService;

//GETTERS FOR ALL DATA, USING TO TEST
	@GET
	@Path("/allLibraries")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Library> getAllLibraries() {
		List <Library> libraries = new ArrayList<Library>();
		libraries =(List<Library>) libraryService.getAllLibrary();
		return libraries;
	}
	
	@GET
	@Path("/allPlaylists")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Playlist> getAllPlaylists() {
		List <Playlist> playlists = new ArrayList<Playlist>();
		playlists =(List<Playlist>) playlistService.getAllPlaylists();
		return playlists;
	}
	
	@GET
	@Path("/allTracks")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Track> getAllTracks() {
		List <Track> tracks = new ArrayList<Track>();
		tracks = (List<Track>) trackService.getAllTracks();
		return tracks;
	}
	
	@GET
	@Path("/allPlaylistTracks")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PlaylistTrack> getAllPlaylistTracks() {
		List <PlaylistTrack> ptracks = new ArrayList<PlaylistTrack>();
		ptracks  = (List<PlaylistTrack>) playlistTrackService.getAllPlaylistTracks();
		return ptracks ;
	}
	
	//UPLOAD AND PARSE LIBRARY
	@GET
	@Path("/persist/{username}/{filename}")
	@Produces(MediaType.APPLICATION_JSON)
	public void persist(@PathParam("username") String username ,@PathParam("filename")String filename) {
		 Library library = new Library();
		 ArrayList<Playlist> playlists = new ArrayList<Playlist>();
		 ArrayList<Track> tracks = new ArrayList<Track>();
		 ArrayList<PlaylistTrack> allplaylisttrack = new ArrayList<PlaylistTrack>();
		 
		try {
			//for temp
			Dict trackdict = null;
			Array playlistarr = null;
			
			//main funct
			File file = new File("/home/shanu/Desktop/"+filename);  
			JAXBContext jaxbContext = JAXBContext.newInstance(Plist.class);  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			Plist plist = (Plist) jaxbUnmarshaller.unmarshal(file);  			//main data construct
			Dict dict = (Dict) plist.getchildren().get(0);						//every element inside
			
			List<Object> list = dict.getchildren();								//using this for lots of things
			
			String libpersistance;	
			for(int i=0;i<list.size();i++){
				if (list.get(i)  instanceof Key) {
					Key key = (Key) list.get(i);
					if(key.getvalue().matches("Library Persistent ID")){
						libpersistance = (String) list.get(i+1);
						library.setLibraryID(libpersistance);
						//System.out.println("library persistance ID: "+libpersistance);
					}
					if(key.getvalue().matches("Tracks")){
						trackdict = (Dict) list.get(i+1);
						//System.out.println("track dict: "+trackdict);

					}
					if(key.getvalue().matches("Playlists")){
						playlistarr = (Array) list.get(i+1);
						//System.out.println("playlists: "+playlistarr);
					}
				}
			}
			
			list.clear();
			list = trackdict.getchildren();
			//going through all songs dict
			for(int i = 1;i<list.size();i=i+2){	
				Dict song = (Dict) list.get(i);
				List songinfo = song.getchildren();
				
				int trackid = 0;
				String name = null;
				String artist = null;
				String album = null;
				String genre = null;
				//System.out.println("");
				//System.out.println("****** track number: " + i + "******");
				//going through indiv song dict
				for(int j = 0;j<songinfo.size();j++){
					if (songinfo.get(j) instanceof Key) {
						Key key = (Key) songinfo.get(j);
						if(key.getvalue().matches("Track ID")){
							//System.out.println("track id: " + songinfo.get(j+1));
							trackid = (int) songinfo.get(j+1);
						}
						if(key.getvalue().matches("Name")){
							//System.out.println("name: " + songinfo.get(j+1));
							name = (String) songinfo.get(j+1);
						}
						if(key.getvalue().matches("Artist")){
							//System.out.println("artist: " + songinfo.get(j+1));
							artist = (String) songinfo.get(j+1);
						}
						if(key.getvalue().matches("Album")){
							//System.out.println("album: " + songinfo.get(j+1));
							album = (String) songinfo.get(j+1);
						}
						if(key.getvalue().matches("Genre")){
							//System.out.println("genre: " + songinfo.get(j+1));
							genre = (String) songinfo.get(j+1);
						}
					}
				}
				Track track = new Track(trackid, name, artist, album, genre);
				tracks.add(track);
			}
												
			
			list.clear();
			list = playlistarr.getchildren();

//EACH PLAYLIST
			for(int i = 0;i<list.size();i++){
				Dict playlist = (Dict) list.get(i);
				List playlistinfo = playlist.getchildren();			
				
				int playlistID = 0;
				String name = null;
				
				ArrayList<Integer> ptrackIds = new ArrayList<Integer>();
				ArrayList<Track> ptrackobjs = new ArrayList<Track>();
				ArrayList<PlaylistTrack> playlistTrackList = new ArrayList<PlaylistTrack>();
				
				//GET INFRO FROM PLAYLIST
				for(int j = 0; j<playlistinfo.size(); j++){
					if (playlistinfo.get(j) instanceof Key) {
						Key key = (Key) playlistinfo.get(j);
						if(key.getvalue().matches("Name")){
							name = (String) playlistinfo.get(j+1);
						}
						if(key.getvalue().matches("Playlist ID")){
							playlistID = (int) playlistinfo.get(j+1);
						}
					}
					if (playlistinfo.get(j) instanceof Array) {
						Array array = (Array) playlistinfo.get(j);
						List songdicts = array.getchildren();
						//GET SONGS ID
						for(int k=0;k<songdicts.size();k++){					
							Dict plistsong = (Dict) songdicts.get(k);
							int plistsongid = (int) plistsong.getchildren().get(1); 				
							ptrackIds.add(plistsongid);
						}
					}
				}
				
				//GET TRACK OBJECTS BY THEIR COLLECTED ID
				if(!ptrackIds.isEmpty()){
					for(int j = 0;j<ptrackIds.size();j++){
						Track newtrack = getTrack(ptrackIds.get(j), tracks);
						ptrackobjs.add(newtrack);	
					}
				}
				
				Playlist newplaylist = new Playlist(playlistID, name); 
				//GET REFERENCES, GIVE THAT LIST TO PLAYLIST
				for(int j = 0;j<ptrackobjs.size();j++){
					PlaylistTrack playlisttrack = new PlaylistTrack(ptrackobjs.get(j), newplaylist);
					playlistTrackList.add(playlisttrack);
					allplaylisttrack.add(playlisttrack);
				}
				newplaylist.setPlaylistTracks(playlistTrackList);
				newplaylist.setLibrary(library);
				playlists.add(newplaylist);			
			}
			
			//get songs and give references
			for(int i = 0;i<tracks.size();i++){
				ArrayList<PlaylistTrack> trackPlaylistList = new ArrayList<PlaylistTrack>();
				//each playlisttrack
				for(int k = 0;k<playlists.size();k++){
					if(allplaylisttrack.get(k).getTrack().equals(tracks.get(i))){
						trackPlaylistList.add(allplaylisttrack.get(k));
					}
				}
				tracks.get(i).setTrackPlaylists(trackPlaylistList);
			}
		
			
			//User user = new User("Kevin", "123");
			User user = userService.getUserByName(username);
			
			ArrayList<Library> libraries = new ArrayList<Library>();
			library.setUser(user);
			libraries.add(library);
			
			userService.addUser(user);
			user.setLibrarys(libraries);
			libraryService.addLibrary(libraries);
			userService.addUser(user);
			
			
			
			System.out.println("PERSISTING");
							
			playlistService.addPlaylist(playlists);
			libraries.get(0).setPlaylists(playlists);
			libraryService.addLibrary(libraries);
//			
			trackService.addTrack(tracks);
//			
			System.out.println("BEFORE PLAYLIST TRACK REL");
			playlistTrackService.addPlaylistTrack(allplaylisttrack);
			System.out.println("FINISHED");
		} 
		catch (JAXBException e) {  
			e.printStackTrace();  
		}  
		
	}
	public static Track getTrack(int trackID, ArrayList<Track> tracks){
		for(int i=0;i<tracks.size();i++)
			if(tracks.get(i).getTrackID() == trackID){
				Track track = tracks.get(i);
				return track;
			}
		return null;
	}
	
	//DELETING OBJECTS
	@GET
	@Path("/deletePlaylist/{playlistID}")
	public void deleteplaylist(@PathParam("playlistID") int playlistID) {
		Playlist newplay = playlistService.getPlaylistByID(playlistID);
		playlistService.deletePlaylist(newplay);
	}
	
	@GET
	@Path("/deleteLibrary/{libraryID}")
	public void deleteLibrary(@PathParam("libraryID") String libraryID) {
		
		Library library = libraryService.getLibraryByID(libraryID);
		libraryService.deleteLibrary(library);
	}

	@GET
	@Path("/deletePlaylistTrack/{playlistID}/{trackID}")
	public void deletePlaylistTrack(@PathParam("playlistID") int playlistID,@PathParam("trackID") int trackID) {
		PlaylistTrack ptrack = playlistTrackService.getPlaylistTrackByID(playlistID, trackID);
		playlistTrackService.deletePlaylistTrack(ptrack);
	}
	
	
	//TABLE QUERIES
	@GET
	@Path("/searchLibrary/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Library> searchUserLibraries(@PathParam("username") String username){
		//System.out.println(username);
		return libraryService.searchLibrary(username);
	}
	
	@GET
	@Path("/searchPlaylists/{library}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Playlist> searchlibraryPlaylists(@PathParam("library") String library){
		//System.out.println(username);
		return playlistService.searchPlaylists(library);
	}
	
	@GET
	@Path("/searchTracks/{playlist}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Track> searchPlaylistsTracks(@PathParam("playlist") int playlist){
		//System.out.println(username);
		return trackService.searchPlaylistTracks(playlist);
	}
	
	//RENAMING OBJECTS
	@GET
	@Path("/renamePlaylist/{id}/{newname}")
	@Produces(MediaType.APPLICATION_JSON)
	public void renamePlaylist(@PathParam("id") int id, @PathParam("newname") String newname){
		
		Playlist playlist = playlistService.getPlaylistByID(id);
		playlist.setName(newname);
		ArrayList<Playlist> newplaylists = new ArrayList<Playlist>();
		newplaylists.add(playlist);
		
		playlistService.addPlaylist(newplaylists);
	}	
	
	//RENAMING OBJECTS
	@GET
	@Path("/renameTrack/{id}/{newname}")
	@Produces(MediaType.APPLICATION_JSON)
	public void renameTrack(@PathParam("id") int id, @PathParam("newname") String newname){
		
		Track track= trackService.getTrackByID(id);
		track.setName(newname);
		ArrayList<Track> newtracks = new ArrayList<Track>();
		newtracks.add(track);
		
		trackService.addTrack(newtracks);
	}	
	
	//MOVING TRACKS
	@GET
	@Path("/moveTrack/{oldPlaylistID}/{newPlaylistID}/{trackID}")
	public void moveTrack(@PathParam("oldPlaylistID") int oldPlaylistID,@PathParam("newPlaylistID") int newPlaylistID, @PathParam("trackID") int trackID) {
		
		PlaylistTrack oldptrack = playlistTrackService.getPlaylistTrackByID(oldPlaylistID, trackID);
		playlistTrackService.deletePlaylistTrack(oldptrack);

		Track track = trackService.getTrackByID(trackID);
		Playlist playlist = playlistService.getPlaylistByID(newPlaylistID);
		PlaylistTrack newptrack = new PlaylistTrack(track, playlist);
		
		ArrayList<PlaylistTrack> ptracks = new ArrayList<PlaylistTrack>();
		ptracks.add(newptrack);
		playlistTrackService.addPlaylistTrack(ptracks);
	}
	
	//COPY TRACKS
		@GET
		@Path("/copyTrack/{oldPlaylistID}/{newPlaylistID}/{trackID}")
		public void copyTrack(@PathParam("oldPlaylistID") int oldPlaylistID,@PathParam("newPlaylistID") int newPlaylistID, @PathParam("trackID") int trackID) {			
			Track track = trackService.getTrackByID(trackID);
			Playlist playlist = playlistService.getPlaylistByID(newPlaylistID);
			PlaylistTrack newptrack = new PlaylistTrack(track, playlist);
			
			ArrayList<PlaylistTrack> ptracks = new ArrayList<PlaylistTrack>();
			ptracks.add(newptrack);
			playlistTrackService.addPlaylistTrack(ptracks);
		}
}
