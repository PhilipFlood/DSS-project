package jaxrs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Event;
import entities.EventCause;
import entities.FailedEntry;
import entities.FailureClass;
import entities.MobileType;
import entities.User;
import services.EventCauseService;
import services.EventService;
import services.FailedEntryService;
import services.FailureClassService;
import services.MobileTypeService;
import services.NetworkService;
import services.UserService;

@Path("/query")
public class DataQuery {
	@Inject
	private EventService eventService;
	@Inject
	private EventCauseService eventCauseService;
	@Inject
	private FailureClassService failureClassService;
	@Inject
	private NetworkService networkService;
	@Inject
	private MobileTypeService mobileTypeService;
	@Inject 
	private FailedEntryService failedEntryService;
	@Inject 
	private UserService userService;

	@GET
	@Path("/allevents")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Event> getAllEvents() {
		return eventService.getAllEvents();
	}
	
	@GET
	@Path("/allimsi")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> getAllImsi() {

		return eventService.getAllImsi();
	}
	
	@GET
	@Path("/allmodels")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Object[]> getAllModels() {

		return mobileTypeService.getAllMobileModels();
	}
	
	@GET
	@Path("/failureClass")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<FailureClass>loadFailureClass(){
		return failureClassService.getAllFailureClass();
	}
	
	@GET
	@Path("/failedEntries")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<FailedEntry> failedEntries(){
		return failedEntryService.getAllEvents();
	}
	
	/*	@GET
	@Path("/eventCause")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<EventCause> getEventCauseTable(){
		return eventCauseService.getAllEventCauses();
	}*/
	
	@GET
	@Path("/searchEventCause/{imsi}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Object[]> searchEventCauseByIMSI(@PathParam("imsi") String imsi){
		System.out.println(imsi);
		return eventService.getEventCauseByIMSI(imsi);
	}
	
	@GET
	@Path("/searchIMSIByDates/{d1}/{d2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Object[]> SearchByDate(@PathParam("d1") String d1 ,@PathParam("d2")String d2) throws ParseException{
		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		System.out.println(d1 +"\t" + d2);
		Date date1 = dateformat2.parse(d1);
		Date date2 = dateformat2.parse(d2);
		return eventService.getIMSIBtwnDates(date1,date2);
	}
	
	@GET
	@Path("/searchMobileType/{m}/{d1}/{d2}")
	@Produces(MediaType.APPLICATION_JSON)
	public int phoneModelFailureCount(@PathParam("m") String mobileType, @PathParam("d1") String d1 ,@PathParam("d2")String d2) throws ParseException{
		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		System.out.println(d1 +"\t" + d2);
		Date date1 = dateformat2.parse(d1);
		Date date2 = dateformat2.parse(d2);
		return eventService.getFailureCountByModel(mobileType,date1,date2);
	}
	
	@GET
	@Path("/imsifc/{imsi}/{d1}/{d2}")
	@Produces(MediaType.APPLICATION_JSON)
	public int IMSIFailureCount(@PathParam("imsi") String imsi, @PathParam("d1") String d1 ,@PathParam("d2")String d2) throws ParseException{
		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		System.out.println(d1 +"\t" + d2);
		Date date1 = dateformat2.parse(d1);
		Date date2 = dateformat2.parse(d2);
		return eventService.getFailureCountByimsi(imsi,date1,date2);
	}
	
	@GET
	@Path("/10MarketOperatorCell/{d1}/{d2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Object[]> top10MarketOperatorCell(@PathParam("d1") String d1 ,@PathParam("d2")String d2)  throws ParseException {
		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		System.out.println(d1 +"\t" + d2);
		Date date1 = dateformat2.parse(d1);
		Date date2 = dateformat2.parse(d2);
		
		return eventService.get10MarketOperatorCellID(date1, date2);
	}
	
	@GET
	@Path("/fceibpm/{model}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Object[]> eventIdCauseCodeByModel(@PathParam("model") String model){
		
		return eventService.FailureCodeEventIdByModel(model);
	}

	@GET
	@Path("/searchIMSIByCauseCode/{CC}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> searchByFailureClass(@PathParam("CC") int cc) throws ParseException {
		
		return eventService.searchByFailureClass(cc);
	}
	
	@GET
	@Path("/top10failuresbyIMSI/{d1}/{d2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> top10failuresbyIMSI(@PathParam("d1") String d1 ,@PathParam("d2")String d2) throws ParseException{
		SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		Date date1 = dateformat2.parse(d1);
		Date date2 = dateformat2.parse(d2);
		return eventService.top10failuresbyIMSI(date1,date2);
	}	

	@GET
	@Path("/searchUniqueCauseCodeByIMSI/{imsi}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Integer> searchUniqueCauseCodeByIMSI(@PathParam("imsi") String imsi) throws ParseException {
		
		return eventService.searchUniqueCauseCodeByIMSI(imsi);
	}
	
}
