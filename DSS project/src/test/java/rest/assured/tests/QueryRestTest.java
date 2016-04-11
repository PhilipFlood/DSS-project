//package rest.assured.tests;
//
//import static com.jayway.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.*;
//
//import static com.jayway.jsonpath.JsonPath.*;
//
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//import javax.ws.rs.Path;
//
//import org.junit.Test;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import com.jayway.restassured.path.json.JsonPath;
//
//import entities.Event;
//import entities.FailureClass;
//import jpa.dao.IEventDAO;
//import net.minidev.json.JSONArray;
//import net.minidev.json.JSONObject;
//
//public class QueryRestTest {
//
//	@Inject
//	IEventDAO eventDAO;
//
//	@Test
//	public void allEvents() {
//		when().get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/allevents").then().statusCode(200);
//	}
//	
//	@Test
//	public void FailureClass() throws JsonParseException, JsonMappingException, IOException {
//		String response = get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/failureClass").asString();
//
//		ObjectMapper mapper = new ObjectMapper();
//		List<entities.FailureClass> list = new ArrayList<entities.FailureClass>();
//
//		list = mapper.readValue(response,
//				TypeFactory.defaultInstance().constructCollectionType(List.class, entities.FailureClass.class));
//
//		assertEquals(5, list.size());
//
//	}
//	
//	@Test
//	public void failedEntries() {
//		when().get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/failedEntries").then().statusCode(200)
//				.assertThat().body("id[0]", equalTo(1));
//	}
//
//	@Test
//	public void eventSearchByIMSI() {
//		given().pathParam("imsi", "344930000000011").when()
//				.get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/searchEventCause/{imsi}").then()
//				.statusCode(200);
//
//	}
//
//	@Test
//	public void searchIMSIByDate() {
//		given().pathParam("d1", "2013-01-01 00:00:00").pathParam("d2", "2013-12-12 00:00:00").when()
//				.get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/searchIMSIByDates/{d1}/{d2}").then()
//				.statusCode(200);
//	}
//
//	@Test
//	public void imsiFailureCount() {
//		given().pathParam("imsi", "344930000000011").pathParam("d1", "2013-01-01 00:00:00")
//				.pathParam("d2", "2013-12-12 00:00:00").when()
//				.get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/imsifc/{imsi}/{d1}/{d2}").then()
//				.statusCode(200);
//	}
//
//	// @Path("/searchMobileType/{m}/{d1}/{d2}")
//	@Test
//	public void mobileTypeSearch() {
//		given().pathParam("m", "VEA3").pathParam("d1", "2013-01-01 00:00:00").pathParam("d2", "2013-12-12 00:00:00")
//				.when().get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/searchMobileType/{m}/{d1}/{d2}").then()
//				.statusCode(200);
//	}
//
//	@Test
//	public void top10failuresbyIMSI() {
//		given().pathParam("d1", "2013-01-01 00:00:00").pathParam("d2", "2013-12-12 00:00:00")
//				.when().get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/top10failuresbyIMSI/{d1}/{d2}").then()
//				.statusCode(200);
//	}
//
//	@Test
//	public void FailureCountIMIByDates() throws JsonParseException, JsonMappingException, IOException, ParseException {
//		String result = given().pathParam("imsi", "344930000000011").pathParam("d1", "2013-01-01 00:00:00")
//				.pathParam("d2", "2013-12-12 00:00:00").when()
//				.get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/imsifc/{imsi}/{d1}/{d2}")
//				.asString();
//
//		ObjectMapper mapper = new ObjectMapper();
//		DateFormat df = new SimpleDateFormat("yyyy-dd-mm HH:MM:SS");
//		mapper.setDateFormat(df);
//		String response = get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/allevents").asString();
//		List<Event> events = new ArrayList<Event>();
//		events = mapper.readValue(response,
//				TypeFactory.defaultInstance().constructCollectionType(List.class, Event.class));
//
//		Date from = df.parse("2013-01-01 00:00:00");
//		Date to = df.parse("2013-12-12 00:00:00");
//
//		long fromtime = from.getTime();
//		long toTime = to.getTime();
//		long check;
//
//		int count = 0;
//		for (Event e : events) {
//			Date date = df.parse(e.getDate());
//			check = date.getTime();
//			if (e.getImsi().equals("344930000000011") && (check - fromtime) >= 0 && (toTime - check) <= 0) {
//				count++;
//			}
//		}
//		assertEquals(Integer.parseInt(result), count);
//	}
//	
//	@Test
//	public void FailureCodeEventIdByModel(){
//		String model = "VEA3";
//		given().pathParam("model", model).when()
//		.get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/fceibpm/{model}")
//		.then().statusCode(200);
//	}
//
//	@Test
//	public void searchIMSIByCauseCode() {
//		given().pathParam("CC", "0").when()
//				.get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/searchIMSIByCauseCode/{CC}").then()
//				.statusCode(200);
//	}
//	
//	@Test
//	public void searchUniqueCauseCodeByIMSI() {
//		given().pathParam("imsi", "344930000000011").when()
//				.get("http://localhost:8080/EventManager1-0.0.1-SNAPSHOT/rest/query/searchUniqueCauseCodeByIMSI/{imsi}").then()
//				.statusCode(200);
//	}
//	
//}
//
