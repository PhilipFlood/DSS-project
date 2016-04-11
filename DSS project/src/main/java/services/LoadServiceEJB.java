package services;

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

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa.dao.IEventCauseDAO;
import jpa.dao.IEventDAO;
import jpa.dao.IFailedEntryDAO;
import jpa.dao.IFailureClassDAO;
import jpa.dao.IMobileTypeDAO;
import jpa.dao.INetworkDAO;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Event;
import entities.EventCause;
import entities.EventCauseId;
import entities.FailedEntry;
import entities.FailureClass;
import entities.MobileType;
import entities.Network;
import entities.NetworkId;

@Local
@Stateless
public class LoadServiceEJB implements LoadService {
	
	@Inject
	private IEventDAO eventDao;
	@Inject
	private IEventCauseDAO eventCauseDao;
	@Inject
	private IFailedEntryDAO failedEntryDao;
	@Inject
	private IFailureClassDAO failureClassDao;
	@Inject
	private INetworkDAO networkDao;
	@Inject
	private IMobileTypeDAO mobileTypeDao;
	@PersistenceContext
	private static EntityManager em;
	
	public void addAllData(File path) throws IOException, ParseException {
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		path = objectMapper.readValue(path, String.class);
	
		//System.out.println(path + " entered");
		long startTime = System.currentTimeMillis();
		loadFailureClass(path);
		long estimatedTime = System.currentTimeMillis();
		System.out.print("Failure Class Table populated in" ); System.out.println( estimatedTime - startTime);
		
		startTime = System.currentTimeMillis();
		loadEventCause(path);
		estimatedTime = System.currentTimeMillis();
		System.out.print("EventCause Table populated in" ); System.out.println( estimatedTime - startTime);

		startTime = System.currentTimeMillis();
		loadMobileType(path);
		estimatedTime = System.currentTimeMillis();
		System.out.print("MobileType Table populated in" ); System.out.println( estimatedTime - startTime);

		startTime = System.currentTimeMillis();
		loadNetwork(path);
		estimatedTime = System.currentTimeMillis();
		System.out.print("Network Table populated in" ); System.out.println( estimatedTime - startTime);

		 startTime = System.currentTimeMillis();
		// ... do something ...
		
		loadBaseData(path);
		 estimatedTime = System.currentTimeMillis();
		 System.out.println(estimatedTime - startTime);
	}
	
	public void loadBaseData(File path) throws IOException, ParseException{
		
		ArrayList<Event>baseData = new ArrayList<Event>();
		ArrayList<FailedEntry>failedData = new ArrayList<FailedEntry>();
		
		FileInputStream file = new FileInputStream(path);

		//Get the workbook instance for XLS file 
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		//Get  sheet from the workbook
		HSSFSheet sheet = workbook.getSheet("Base Data");

		//Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next();
		while(rowIterator.hasNext()) {
			 row = rowIterator.next();

			try{
				
				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();

				Date date = (cellIterator.next().getDateCellValue());

				int eventId =((int)cellIterator.next().getNumericCellValue());

				int failureClass=((int)cellIterator.next().getNumericCellValue());

				int tac =((int)cellIterator.next().getNumericCellValue());

				int mcc =((int)cellIterator.next().getNumericCellValue());

				int mnc=((int)cellIterator.next().getNumericCellValue());

				int cellId=((int)cellIterator.next().getNumericCellValue());

				int duration=((int)cellIterator.next().getNumericCellValue());

				int causeCode=((int)cellIterator.next().getNumericCellValue());

				String neVersion= (cellIterator.next().getStringCellValue());

				Cell cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String imsi =cell.getStringCellValue();		        

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String hier3id=cell.getStringCellValue();

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String hier32id=cell.getStringCellValue();

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String hier321id=cell.getStringCellValue();

				EventCauseId eventCauseId = new EventCauseId(causeCode,eventId);
				NetworkId networkId = new NetworkId(mnc,mcc);
				FailureClass fclass = em.find(FailureClass.class, failureClass);
				EventCause eventCause =em.find(EventCause.class, eventCauseId);
				MobileType mobileType = em.find(MobileType.class, tac);
				Network network = em.find(Network.class, networkId);
							
				if(fclass==null||eventCause==null||mobileType==null||network==null){
					FailedEntry fE = new FailedEntry(date,Integer.toString(eventId),Integer.toString(failureClass), Integer.toString( tac), 
							Integer.toString(mcc), Integer.toString(mnc), 
							Integer.toString (cellId), Integer.toString (duration), Integer.toString (causeCode), 
							neVersion, imsi, hier3id, hier32id, hier321id);
					
					failedData.add(fE);
					
				}else{
					Event e = new Event(date, duration, cellId, neVersion, imsi, hier3id, hier32id, hier321id, 
							            fclass, eventCause, mobileType, network);
					baseData.add(e);
				}
				/*if(checkConsistencyWithDB(event)){
					eventService.addToDataset(event);
				}else{
					failedEntryService.addToDataset(new FailedEntry(event));
				}*/


			}catch(IllegalStateException e){

				Iterator<Cell> cellIterator = row.cellIterator();


				FailedEntry faultEvent = new FailedEntry();

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
				
				Cell cell = cellIterator.next();
				
				Date date = cell.getDateCellValue();
												
				faultEvent.setDate(date);

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);		   
				faultEvent.setEventId(cell.getStringCellValue());

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);	
				faultEvent.setFailureClass(cell.getStringCellValue());

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);	
				faultEvent.setTac(cell.getStringCellValue());

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);	
				faultEvent.setMcc(cell.getStringCellValue());

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);	
				faultEvent.setMnc(cell.getStringCellValue());

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);	
				faultEvent.setCellId(cell.getStringCellValue());

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);	
				faultEvent.setDuration(cell.getStringCellValue());

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);	
				faultEvent.setCauseCode(cell.getStringCellValue());
				
				faultEvent.setNeVersion(cellIterator.next().getStringCellValue());

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);	
				faultEvent.setImsi(cell.getStringCellValue());		        

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				faultEvent.setHier3id(cell.getStringCellValue());

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				faultEvent.setHier32id(cell.getStringCellValue());

				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				faultEvent.setHier321id(cell.getStringCellValue());

				failedData.add(faultEvent);
				//System.out.println("Fault Data");
			}

		}

		file.close();
		long startTime = System.currentTimeMillis();
		failedEntryDao.addData(failedData);
		long finishTime = System.currentTimeMillis();
		System.out.println("Failed Date added to list in " + (int)(finishTime-startTime));
		
		startTime = System.currentTimeMillis();
		eventDao.addData(baseData);
		finishTime = System.currentTimeMillis();
		System.out.println("Base Date added to list in " + (int)(finishTime-startTime));
	}

	public void loadFailureClass(File path) throws IOException{
		
		ArrayList<FailureClass> failureClassTable = new ArrayList<FailureClass>();
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			
		}

		//Get the workbook instance for XLS file 
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		//Get  sheet from the workbook
		HSSFSheet sheet = workbook.getSheet("Failure Class Table");

		//Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next();
		while(rowIterator.hasNext()) {
			 row = rowIterator.next();
			//For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			FailureClass fclass = new FailureClass();
			Cell cell = cellIterator.next();
			fclass.setFailureCode((int)cell.getNumericCellValue());
			
			cell = cellIterator.next();
			fclass.setDescription(cell.getStringCellValue());	
			failureClassTable.add(fclass);
		}
		file.close();
		failureClassDao.addFailureClass(failureClassTable);
	}
	
	public void loadNetwork(File path) throws IOException{
		ArrayList<Network> networkTable = new ArrayList<Network>();
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			
		}

		//Get the workbook instance for XLS file 
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		//Get  sheet from the workbook
		HSSFSheet sheet = workbook.getSheet("MCC - MNC Table");

		//Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next();
		while(rowIterator.hasNext()) {
			 row = rowIterator.next();
			//For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			Network network = new Network();
			Cell cell = cellIterator.next();
			network.setMcc((int)cell.getNumericCellValue());
			
			cell = cellIterator.next();
			network.setMnc((int)cell.getNumericCellValue());
			
			cell = cellIterator.next();
			network.setCountry(cell.getStringCellValue());	
			
			cell = cellIterator.next();
			network.setOperator(cell.getStringCellValue());
			
			networkTable.add(network);
	
		}
		file.close();
		networkDao.addNetwork(networkTable);
	}

	public void loadEventCause(File path) throws IOException{
		ArrayList<EventCause> eventCauseTable = new ArrayList<EventCause>();
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// "File not found ";
		}

		//Get the workbook instance for XLS file 
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		//Get  sheet from the workbook
		HSSFSheet sheet = workbook.getSheet("Event-Cause Table");

		//Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next();
		while(rowIterator.hasNext()) {
			 row = rowIterator.next();
			//For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			EventCause eventCause = new EventCause();
			
			Cell cell = cellIterator.next();
			eventCause.setCauseCode((int)cell.getNumericCellValue());
			
			cell = cellIterator.next();
			eventCause.setEventId((int)cell.getNumericCellValue());
			
			cell = cellIterator.next();
			eventCause.setDescription(cell.getStringCellValue());	

			eventCauseTable.add(eventCause);
	
		}
		file.close();
		eventCauseDao.addEventCause(eventCauseTable);
	}

	public void loadMobileType(File path) throws IOException{
		ArrayList<MobileType> mobileTypeTable = new ArrayList<MobileType>();
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			
		}

		//Get the workbook instance for XLS file 
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		//Get  sheet from the workbook
		HSSFSheet sheet = workbook.getSheet("UE Table");

		//Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next();
		while(rowIterator.hasNext()) {
			 row = rowIterator.next();
			//For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			MobileType mobileType = new MobileType();
			
			Cell cell = cellIterator.next();
			mobileType.setTac((int)cell.getNumericCellValue());
			
			cell = cellIterator.next();
			cell.setCellType(Cell.CELL_TYPE_STRING);
			mobileType.setMarketingName(cell.getStringCellValue());	
			
			cell = cellIterator.next();
			mobileType.setManufacturer(cell.getStringCellValue());	
			
			cell = cellIterator.next();
			mobileType.setAccessCompatibility(cell.getStringCellValue());	
			
			cell = cellIterator.next();
			cell = cellIterator.next();
			
			cell = cellIterator.next();		
			mobileType.setUeType(cell.getStringCellValue());	
			
			cell = cellIterator.next();		
			mobileType.setOs(cell.getStringCellValue());
			
			cell = cellIterator.next();		
			mobileType.setInputType(cell.getStringCellValue());

			mobileTypeTable.add(mobileType);
	
		}
		file.close();
		mobileTypeDao.addMobileType(mobileTypeTable);
	}
	
}