package jaxrs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import services.LibraryService;
import services.PlaylistService;
import services.PlaylistTrackService;
import services.TrackService;
import services.UserService;
import entities.Library;
import entities.Playlist;
import entities.PlaylistTrack;
import entities.Track;


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
