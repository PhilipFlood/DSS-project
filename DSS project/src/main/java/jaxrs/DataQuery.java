package jaxrs;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
	

	
	@GET
	@Path("/persist")
	public void persist() {
		 Library library = new Library();
		 ArrayList<Playlist> playlists = new ArrayList<Playlist>();
		 ArrayList<Track> tracks = new ArrayList<Track>();
		 ArrayList<PlaylistTrack> allplaylisttrack = new ArrayList<PlaylistTrack>();
		 
		try {
			//for temp
			Dict trackdict = null;
			Array playlistarr = null;
			
			//main funct
			File file = new File("/home/shanu/DSS project/DSS project/iTunes Music Library3.xml");  
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
	
			//WRONG DIRECTION?S
//			for(int i= 0;i<playlists.size();i++){
//				playlists.get(i).setLibrary(library);
//			}
		
			
			User user = new User("jake", "123");
			
			ArrayList<Library> libraries = new ArrayList<Library>();
			library.setUser(user);
			libraries.add(library);
			
			userService.addUser(user);
			user.setLibrarys(libraries);
			libraryService.addLibrary(libraries);
			userService.addUser(user);
			
			
			
			System.out.println("PERSISTING");
			
//					
			playlistService.addPlaylist(playlists);
			libraries.get(0).setPlaylists(playlists);
			libraryService.addLibrary(libraries);
//			
			trackService.addTrack(tracks);
//			
			playlistTrackService.addPlaylistTrack(allplaylisttrack);
			
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
	
	
	
//	@GET
//	@Path("/allevents")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<Event> getAllEvents() {
//		return eventService.getAllEvents();
//	}
//	
//	@GET
//	@Path("/allimsi")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<String> getAllImsi() {
//
//		return eventService.getAllImsi();
//	}
//	
//	@GET
//	@Path("/allmodels")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<Object[]> getAllModels() {
//
//		return mobileTypeService.getAllMobileModels();
//	}
//	
//	@GET
//	@Path("/failureClass")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<FailureClass>loadFailureClass(){
//		return failureClassService.getAllFailureClass();
//	}
//	
//	@GET
//	@Path("/failedEntries")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<FailedEntry> failedEntries(){
//		return failedEntryService.getAllEvents();
//	}
//	
//	/*	@GET
//	@Path("/eventCause")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<EventCause> getEventCauseTable(){
//		return eventCauseService.getAllEventCauses();
//	}*/
//	
//	@GET
//	@Path("/searchEventCause/{imsi}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<Object[]> searchEventCauseByIMSI(@PathParam("imsi") String imsi){
//		System.out.println(imsi);
//		return eventService.getEventCauseByIMSI(imsi);
//	}
//	
//	@GET
//	@Path("/searchIMSIByDates/{d1}/{d2}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<Object[]> SearchByDate(@PathParam("d1") String d1 ,@PathParam("d2")String d2) throws ParseException{
//		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
//		System.out.println(d1 +"\t" + d2);
//		Date date1 = dateformat2.parse(d1);
//		Date date2 = dateformat2.parse(d2);
//		return eventService.getIMSIBtwnDates(date1,date2);
//	}
//	
//	@GET
//	@Path("/searchMobileType/{m}/{d1}/{d2}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public int phoneModelFailureCount(@PathParam("m") String mobileType, @PathParam("d1") String d1 ,@PathParam("d2")String d2) throws ParseException{
//		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
//		System.out.println(d1 +"\t" + d2);
//		Date date1 = dateformat2.parse(d1);
//		Date date2 = dateformat2.parse(d2);
//		return eventService.getFailureCountByModel(mobileType,date1,date2);
//	}
//	
//	@GET
//	@Path("/imsifc/{imsi}/{d1}/{d2}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public int IMSIFailureCount(@PathParam("imsi") String imsi, @PathParam("d1") String d1 ,@PathParam("d2")String d2) throws ParseException{
//		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
//		System.out.println(d1 +"\t" + d2);
//		Date date1 = dateformat2.parse(d1);
//		Date date2 = dateformat2.parse(d2);
//		return eventService.getFailureCountByimsi(imsi,date1,date2);
//	}
//	
//	@GET
//	@Path("/10MarketOperatorCell/{d1}/{d2}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<Object[]> top10MarketOperatorCell(@PathParam("d1") String d1 ,@PathParam("d2")String d2)  throws ParseException {
//		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
//		System.out.println(d1 +"\t" + d2);
//		Date date1 = dateformat2.parse(d1);
//		Date date2 = dateformat2.parse(d2);
//		
//		return eventService.get10MarketOperatorCellID(date1, date2);
//	}
//	
//	@GET
//	@Path("/fceibpm/{model}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<Object[]> eventIdCauseCodeByModel(@PathParam("model") String model){
//		
//		return eventService.FailureCodeEventIdByModel(model);
//	}
//
//	@GET
//	@Path("/searchIMSIByCauseCode/{CC}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<String> searchByFailureClass(@PathParam("CC") int cc) throws ParseException {
//		
//		return eventService.searchByFailureClass(cc);
//	}
//	
//	@GET
//	@Path("/top10failuresbyIMSI/{d1}/{d2}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<String> top10failuresbyIMSI(@PathParam("d1") String d1 ,@PathParam("d2")String d2) throws ParseException{
//		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
//		Date date1 = dateformat2.parse(d1);
//		Date date2 = dateformat2.parse(d2);
//		return eventService.top10failuresbyIMSI(date1,date2);
//	}	
//
//	@GET
//	@Path("/searchUniqueCauseCodeByIMSI/{imsi}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<Integer> searchUniqueCauseCodeByIMSI(@PathParam("imsi") String imsi) throws ParseException {
//		
//		return eventService.searchUniqueCauseCodeByIMSI(imsi);
//	}
	
}
