/*package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name="FailedEntries")

public class FailedEntry implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Date")
	private Date date;
	@Column(name="EventId")
	private String eventId;
	@Column(name="FailureClass")
	private String failureClass;
	@Column(name="TAC")
	private String tac;
	@Column(name="MCC")
	private String mcc;
	@Column(name="MNC")
	private String mnc;
	@Column(name="CellID")
	private String cellId;
	@Column(name="Duration")
	private String duration;
	@Column(name="Cause_Code")
	private String causeCode;
	@Column(name="NE_Version")
	private String neVersion;
	@Column(name="IMSI")
	private String imsi;
	@Column(name="HIER3_ID")
	private String hier3id;
	@Column(name="HIER32_ID")
	private String hier32id;
	@Column(name="HIER321_ID")
	private String hier321id;
	
	public FailedEntry(){};
	
	public FailedEntry(Event event) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		this.date = df.parse(event.getDate());
		this.eventId = Integer.toString(event.getEventId());
		this.failureClass = Integer.toString(event.getFailureClass());
		this.tac = Integer.toString(event.getTac());
		this.mcc = Integer.toString(event.getMcc());
		this.mnc = Integer.toString(event.getMnc());
		this.cellId = Integer.toString(event.getCellId());
		this.duration = Integer.toString(event.getDuration());
		this.causeCode = Integer.toString(event.getCauseCode());
		this.neVersion = event.getNeVersion();
		this.imsi = event.getImsi();
		this.hier3id = event.getHier3id();
		this.hier32id = event.getHier32id();
		this.hier321id = event.getHier321id();
		
	}
	public FailedEntry(Date date, String eventId, String failureClass,
			String tac, String mcc, String mnc, String cellId, String duration,
			String causeCode, String neVersion, String imsi, String hier3id,
			String hier32id, String hier321id) {
		this.date = date;
		this.eventId = eventId;
		this.failureClass = failureClass;
		this.tac = tac;
		this.mcc = mcc;
		this.mnc = mnc;
		this.cellId = cellId;
		this.duration = duration;
		this.causeCode = causeCode;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3id = hier3id;
		this.hier32id = hier32id;
		this.hier321id = hier321id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");
		
		return df.format(date);
	}
	public void setDate(Date date2) {
		this.date = date2;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getFailureClass() {
		return failureClass;
	}
	public void setFailureClass(String failureClass) {
		this.failureClass = failureClass;
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getCauseCode() {
		return causeCode;
	}
	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}
	public String getNeVersion() {
		return neVersion;
	}
	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getHier3id() {
		return hier3id;
	}
	public void setHier3id(String hier3id) {
		this.hier3id = hier3id;
	}
	public String getHier32id() {
		return hier32id;
	}
	public void setHier32id(String hier32id) {
		this.hier32id = hier32id;
	}
	public String getHier321id() {
		return hier321id;
	}
	public void setHier321id(String hier321id) {
		this.hier321id = hier321id;
	}
	
}
*/
package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name="FailedEntries")

public class FailedEntry implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Date")
	private Date date;
	@Column(name="EventId")
	private String eventId;
	@Column(name="FailureClass")
	private String failureClass;
	@Column(name="TAC")
	private String tac;
	@Column(name="MCC")
	private String mcc;
	@Column(name="MNC")
	private String mnc;
	@Column(name="CellID")
	private String cellId;
	@Column(name="Duration")
	private String duration;
	@Column(name="Cause_Code")
	private String causeCode;
	@Column(name="NE_Version")
	private String neVersion;
	@Column(name="IMSI")
	private String imsi;
	@Column(name="HIER3_ID")
	private String hier3id;
	@Column(name="HIER32_ID")
	private String hier32id;
	@Column(name="HIER321_ID")
	private String hier321id;
	
	public FailedEntry(){};
	
//	public FailedEntry(Event event) throws ParseException{
//		DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//		this.date = df.parse(event.getDate());
//		this.eventId = Integer.toString(event.getEventId());
//		this.failureClass = Integer.toString(event.getFailureClass());
//		this.tac = Integer.toString(event.getTac());
//		this.mcc = Integer.toString(event.getMcc());
//		this.mnc = Integer.toString(event.getMnc());
//		this.cellId = Integer.toString(event.getCellId());
//		this.duration = Integer.toString(event.getDuration());
//		this.causeCode = Integer.toString(event.getCauseCode());
//		this.neVersion = event.getNeVersion();
//		this.imsi = event.getImsi();
//		this.hier3id = event.getHier3id();
//		this.hier32id = event.getHier32id();
//		this.hier321id = event.getHier321id();
//		
//	}

	public FailedEntry(Date date, String eventId, String failureClass,
			String tac, String mcc, String mnc, String cellId, String duration,
			String causeCode, String neVersion, String imsi, String hier3id,
			String hier32id, String hier321id) {
		this.date = date;
		this.eventId = eventId;
		this.failureClass = failureClass;
		this.tac = tac;
		this.mcc = mcc;
		this.mnc = mnc;
		this.cellId = cellId;
		this.duration = duration;
		this.causeCode = causeCode;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3id = hier3id;
		this.hier32id = hier32id;
		this.hier321id = hier321id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");
		
		return df.format(date);
	}
	public void setDate(Date date2) {
		this.date = date2;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getFailureClass() {
		return failureClass;
	}
	public void setFailureClass(String failureClass) {
		this.failureClass = failureClass;
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	public String getCellId() {
		return cellId;
	}
	public void setCellId(String cellId) {
		this.cellId = cellId;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getCauseCode() {
		return causeCode;
	}
	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}
	public String getNeVersion() {
		return neVersion;
	}
	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getHier3id() {
		return hier3id;
	}
	public void setHier3id(String hier3id) {
		this.hier3id = hier3id;
	}
	public String getHier32id() {
		return hier32id;
	}
	public void setHier32id(String hier32id) {
		this.hier32id = hier32id;
	}
	public String getHier321id() {
		return hier321id;
	}
	public void setHier321id(String hier321id) {
		this.hier321id = hier321id;
	}
	
	
	
}
