/*package jaxrs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Event;
import entities.EventCause;
import entities.FailureClass;
import entities.MobileType;
import entities.Network;
import entities.FailedEntry;
import services.EventCauseService;
import services.EventService;
import services.FailedEntryService;
import services.FailureClassService;
import services.LoadService;
import services.MobileTypeService;
import services.NetworkService;


@Path("/load")
public class LoadData {

	@Inject
	private LoadService loadService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void DirectoryPath (String path) throws JsonParseException, JsonMappingException, IOException, ParseException{
		ObjectMapper objectMapper = new ObjectMapper();
		path = objectMapper.readValue(path, String.class);
		loadService.addAllData(new File(path));
	}

//	public void loadBaseData(String path) throws IOException, ParseException{
//
//		FileInputStream file = new FileInputStream(new File(path));
//
//		//Get the workbook instance for XLS file 
//		HSSFWorkbook workbook = new HSSFWorkbook(file);
//
//		//Get  sheet from the workbook
//		HSSFSheet sheet = workbook.getSheet("Base Data");
//
//		//Iterate through each rows from first sheet
//		Iterator<Row> rowIterator = sheet.iterator();
//		Row row = rowIterator.next();
//		while(rowIterator.hasNext()) {
//			 row = rowIterator.next();
//
//			try{
//				Event event = new Event();
//				//For each row, iterate through each columns
//				Iterator<Cell> cellIterator = row.cellIterator();
//
//				event.setDate(cellIterator.next().getDateCellValue());
//
//				event.setEventId((int)cellIterator.next().getNumericCellValue());
//
//				event.setFailureClass((int)cellIterator.next().getNumericCellValue());
//
//				event.setTac((int)cellIterator.next().getNumericCellValue());
//
//				event.setMcc((int)cellIterator.next().getNumericCellValue());
//
//				event.setMnc((int)cellIterator.next().getNumericCellValue());
//
//				event.setCellId((int)cellIterator.next().getNumericCellValue());
//
//				event.setDuration((int)cellIterator.next().getNumericCellValue());
//
//				event.setCauseCode((int)cellIterator.next().getNumericCellValue());
//
//				event.setNeVersion(cellIterator.next().getStringCellValue());
//
//				Cell cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				event.setImsi(cell.getStringCellValue());		        
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				event.setHier3id(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				event.setHier32id(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				event.setHier321id(cell.getStringCellValue());
//
//				if(checkConsistencyWithDB(event)){
//					eventService.addToDataset(event);
//				}else{
//					failedEntryService.addToDataset(new FailedEntry(event));
//				}
//
//
//			}catch(IllegalStateException e){
//
//				Iterator<Cell> cellIterator = row.cellIterator();
//
//
//				FailedEntry faultEvent = new FailedEntry();
//
//				DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
//				
//				Cell cell = cellIterator.next();
//				
//				Date date = cell.getDateCellValue();
//												
//				faultEvent.setDate(date);
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);		   
//				faultEvent.setEventId(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);	
//				faultEvent.setFailureClass(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);	
//				faultEvent.setTac(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);	
//				faultEvent.setMcc(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);	
//				faultEvent.setMnc(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);	
//				faultEvent.setCellId(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);	
//				faultEvent.setDuration(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);	
//				faultEvent.setCauseCode(cell.getStringCellValue());
//				
//				faultEvent.setNeVersion(cellIterator.next().getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);	
//				faultEvent.setImsi(cell.getStringCellValue());		        
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				faultEvent.setHier3id(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				faultEvent.setHier32id(cell.getStringCellValue());
//
//				cell = cellIterator.next();
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				faultEvent.setHier321id(cell.getStringCellValue());
//
//				failedEntryService.addToDataset(faultEvent);
//				System.out.println("Fault Data");
//			}
//
//		}
//		System.out.println("");
//
//		file.close();
//
//	}
//
//	public void loadFailureClass(String path) throws IOException{
//		FileInputStream file = null;
//		try {
//			file = new FileInputStream(new File(path));
//		} catch (FileNotFoundException e) {
//			
//		}
//
//		//Get the workbook instance for XLS file 
//		HSSFWorkbook workbook = new HSSFWorkbook(file);
//
//		//Get  sheet from the workbook
//		HSSFSheet sheet = workbook.getSheet("Failure Class Table");
//
//		//Iterate through each rows from first sheet
//		Iterator<Row> rowIterator = sheet.iterator();
//		Row row = rowIterator.next();
//		while(rowIterator.hasNext()) {
//			 row = rowIterator.next();
//			//For each row, iterate through each columns
//			Iterator<Cell> cellIterator = row.cellIterator();
//			FailureClass fclass = new FailureClass();
//			Cell cell = cellIterator.next();
//			fclass.setFailureCode((int)cell.getNumericCellValue());
//			
//			cell = cellIterator.next();
//			fclass.setDescription(cell.getStringCellValue());	
//			failureClassService.addFailureClass(fclass);
//		}
//		file.close();
//	}
//	
//	public void loadNetwork(String path) throws IOException{
//		FileInputStream file = null;
//		try {
//			file = new FileInputStream(new File(path));
//		} catch (FileNotFoundException e) {
//			
//		}
//
//		//Get the workbook instance for XLS file 
//		HSSFWorkbook workbook = new HSSFWorkbook(file);
//
//		//Get  sheet from the workbook
//		HSSFSheet sheet = workbook.getSheet("MCC - MNC Table");
//
//		//Iterate through each rows from first sheet
//		Iterator<Row> rowIterator = sheet.iterator();
//		Row row = rowIterator.next();
//		while(rowIterator.hasNext()) {
//			 row = rowIterator.next();
//			//For each row, iterate through each columns
//			Iterator<Cell> cellIterator = row.cellIterator();
//			Network network = new Network();
//			Cell cell = cellIterator.next();
//			network.setMcc((int)cell.getNumericCellValue());
//			
//			cell = cellIterator.next();
//			network.setMnc((int)cell.getNumericCellValue());
//			
//			cell = cellIterator.next();
//			network.setCountry(cell.getStringCellValue());	
//			
//			cell = cellIterator.next();
//			network.setOperator(cell.getStringCellValue());
//			
//			networkService.addNetwork(network);
//	
//		}
//		file.close();
//	}
//
//	public void loadEventCause(String path) throws IOException{
//		FileInputStream file = null;
//		try {
//			file = new FileInputStream(new File(path));
//		} catch (FileNotFoundException e) {
//			// "File not found ";
//		}
//
//		//Get the workbook instance for XLS file 
//		HSSFWorkbook workbook = new HSSFWorkbook(file);
//
//		//Get  sheet from the workbook
//		HSSFSheet sheet = workbook.getSheet("Event-Cause Table");
//
//		//Iterate through each rows from first sheet
//		Iterator<Row> rowIterator = sheet.iterator();
//		Row row = rowIterator.next();
//		while(rowIterator.hasNext()) {
//			 row = rowIterator.next();
//			//For each row, iterate through each columns
//			Iterator<Cell> cellIterator = row.cellIterator();
//			EventCause eventCause = new EventCause();
//			
//			Cell cell = cellIterator.next();
//			eventCause.setCauseCode((int)cell.getNumericCellValue());
//			
//			cell = cellIterator.next();
//			eventCause.setEventId((int)cell.getNumericCellValue());
//			
//			cell = cellIterator.next();
//			eventCause.setDescription(cell.getStringCellValue());	
//
//			eventCauseService.addEventCause(eventCause);
//	
//		}
//		file.close();
//	}
//
//	public void loadMobileType(String path) throws IOException{
//		FileInputStream file = null;
//		try {
//			file = new FileInputStream(new File(path));
//		} catch (FileNotFoundException e) {
//			
//		}
//
//		//Get the workbook instance for XLS file 
//		HSSFWorkbook workbook = new HSSFWorkbook(file);
//
//		//Get  sheet from the workbook
//		HSSFSheet sheet = workbook.getSheet("UE Table");
//
//		//Iterate through each rows from first sheet
//		Iterator<Row> rowIterator = sheet.iterator();
//		Row row = rowIterator.next();
//		while(rowIterator.hasNext()) {
//			 row = rowIterator.next();
//			//For each row, iterate through each columns
//			Iterator<Cell> cellIterator = row.cellIterator();
//			MobileType mobileType = new MobileType();
//			
//			Cell cell = cellIterator.next();
//			mobileType.setTac((int)cell.getNumericCellValue());
//			
//			cell = cellIterator.next();
//			cell.setCellType(Cell.CELL_TYPE_STRING);
//			mobileType.setMarketingName(cell.getStringCellValue());	
//			
//			cell = cellIterator.next();
//			mobileType.setManufacturer(cell.getStringCellValue());	
//			
//			cell = cellIterator.next();
//			mobileType.setAccessCompatibility(cell.getStringCellValue());	
//			
//			cell = cellIterator.next();
//			cell = cellIterator.next();
//			
//			cell = cellIterator.next();		
//			mobileType.setUeType(cell.getStringCellValue());	
//			
//			cell = cellIterator.next();		
//			mobileType.setOs(cell.getStringCellValue());
//			
//			cell = cellIterator.next();		
//			mobileType.setInputType(cell.getStringCellValue());
//
//			mobileTypeService.addMobileType(mobileType);
//	
//		}
//		file.close();
//	}
//	
//	public boolean checkConsistencyWithDB(Event event){
//		if(eventCauseService.searchCausedCodeAndEventID(event.getCauseCode(), event.getEventId()).isEmpty()){
//			return false;
//		}
//		if(failureClassService.searchFailureClass(event.getFailureClass()).isEmpty()){
//			return false;
//		}
//		if(networkService.searchMCCAndMNC(event.getMcc(),event.getMnc()).isEmpty()){
//			return false;
//		}
//		if(mobileTypeService.searchTac(event.getTac()).isEmpty()){
//			return false;
//		}
//		return true;
//	}
}
*/
package jaxrs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Event;
import entities.EventCause;
import entities.EventCauseId;
import entities.FailureClass;
import entities.MobileType;
import entities.Network;
import entities.NetworkId;
import entities.FailedEntry;
import services.EventCauseService;
import services.EventService;
import services.FailedEntryService;
import services.FailureClassService;
import services.LoadService;
import services.MobileTypeService;
import services.NetworkService;


@Path("/load")
public class LoadData {

	@Inject
	private LoadService loadService;
	
	@PersistenceContext
	private static EntityManager em;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void DirectoryPath (String path) throws JsonParseException, JsonMappingException, IOException, ParseException{
		ObjectMapper objectMapper = new ObjectMapper();
		path = objectMapper.readValue(path, String.class);
		loadService.addAllData(new File(path));
	}
}
