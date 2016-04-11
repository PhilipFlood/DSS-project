/*package jaxrs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import entities.Event;

public class DemoXls {

	public static void main(String[] args) throws IOException {
		 FileInputStream file = new FileInputStream(new File("C:/Users/abdul/Desktop/sampleDataSet.xls"));
	     
		    //Get the workbook instance for XLS file 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		 
		    //Get first sheet from the workbook
		    HSSFSheet sheet = workbook.getSheetAt(0);
		     
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    Row row = rowIterator.next();
	while(rowIterator.hasNext()) {
		         row = rowIterator.next();
		        int i=1;
		    try{
		        Event event = new Event();
		        //For each row, iterate through each columns
		        Iterator<Cell> cellIterator = row.cellIterator();
		    
		        event.setDate(cellIterator.next().getDateCellValue());
		        System.out.print(event.getDate().toString() + " <---Date "  +"\t");
		        i++;
		        event.setEventId((int)cellIterator.next().getNumericCellValue());
		        System.out.print(event.getEventId()+ " <---eventID  " + i +"\t");
		        i++;
		        event.setFailureClass((int)cellIterator.next().getNumericCellValue());
		        System.out.print(event.getFailureClass()+ " <----FailueClass "  +"\t");
		        i++;
		        event.setTac((int)cellIterator.next().getNumericCellValue());
		        System.out.print(event.getTac()+ " <-----TAC" + i +"\t");
		        i++;
		        event.setMcc((int)cellIterator.next().getNumericCellValue());
		        System.out.print(event.getMcc()+ "<----MCC " + i +"\t");
		        i++;
		        event.setMnc((int)cellIterator.next().getNumericCellValue());
		        System.out.print(event.getMnc()+ "<----MNC " + i +"\t");
		        i++;
		        event.setCellId((int)cellIterator.next().getNumericCellValue());
		        System.out.print(event.getCellId()+ " <----CELLID " + i +"\t");
		        i++;
		        event.setDuration((int)cellIterator.next().getNumericCellValue());
		        System.out.print(event.getDuration()+ " <---duration  " + i +"\t");
		        i++;
		        event.setCauseCode((int)cellIterator.next().getNumericCellValue());
		        System.out.print(event.getCauseCode()+ "  " + i +"\t");
		        i++;
		        event.setNeVersion(cellIterator.next().getStringCellValue());
		        System.out.print(event.getNeVersion()+ "  " + i +"\t");
		        i++;
		       Cell cell = cellIterator.next();
		       cell.setCellType(Cell.CELL_TYPE_STRING);
		       event.setImsi(cell.getStringCellValue());
		        System.out.print(event.getImsi()+"  " + i +"\t");
		        i++;
		        event.setHier3id(Double.toString(cellIterator.next().getNumericCellValue()));
		        System.out.print(event.getHier3id()+ "  " + i +"\t");
		        i++;
		        event.setHier32id(Double.toString(cellIterator.next().getNumericCellValue()));
		        System.out.print(event.getHier32id() + "  " + i +"\t" );
		        i++;
		        event.setHier321id(Double.toString(cellIterator.next().getNumericCellValue()));
		        System.out.print(event.getHier321id()+ "  " + i +"\t" +"\n" );
		     
		        
		   }catch(IllegalStateException e){
			   Event event = new Event();
			   System.out.println("Fault Data");
		   }

	
	}}
}
*/