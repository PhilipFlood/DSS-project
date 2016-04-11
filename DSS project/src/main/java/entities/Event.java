/*packageasas entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

@Entity @Table(name="Events")

public class Event implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Date")
	private Date date;
	@Column(name="EventId")
	private int eventId;
	@Column(name="FailureClass")
	private int failureClass;
	@Column(name="TAC")
	private int tac;
	@Column(name="MCC")
	private int mcc;
	@Column(name="MNC")
	private int mnc;
	@Column(name="CellID")
	private int cellId;
	@Column(name="Duration")
	private int duration;
	@Column(name="Cause_Code")
	private int causeCode;
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
	
	public Event(){
		
	}
	
	public Event(Date date, int eventId, int failureClass, int tac,
			int mcc, int mnc, int cellId, int duration, int causeCode,
			String neVersion, String imsi, String hier3id, String hier32id,
			String hier321id) {
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
		DateFormat df = new SimpleDateFormat("yyy-mm-dd HH:MM:SS");
		
		return df.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	public int getTac() {
		return tac;
	}

	public void setTac(int tac) {
		this.tac = tac;
	}

	public int getMcc() {
		return mcc;
	}

	public void setMcc(int mcc) {
		this.mcc = mcc;
	}

	public int getMnc() {
		return mnc;
	}

	public void setMnc(int mnc) {
		this.mnc = mnc;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(int causeCode) {
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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="Events")

public class Event implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="Date") private Date date;
	
	@Column(name="Duration")private int duration;
	@Column(name="CellID") private int cellId;
	@Column(name="NE_Version") private String neVersion;
	@Column(name="IMSI") private String imsi;
	@Column(name="HIER3_ID") private String hier3id;
	@Column(name="HIER32_ID") private String hier32id;
	@Column(name="HIER321_ID")private String hier321id;
	
	
	
	@ManyToOne 
	@JoinColumn(name="FailureClass", referencedColumnName="FailureClass", nullable = false)
	//@JsonIgnore 
	private FailureClass fclass;
	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Cause_Code", referencedColumnName ="Cause_Code", nullable = false),
		@JoinColumn(name="EventId" , referencedColumnName="EventId", nullable = false)
	})
	//@JsonIgnore 
	private EventCause eventCause;
	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="TAC", referencedColumnName ="TAC", nullable = false),
	})
	//@JsonIgnore 
	private MobileType mobileType;
	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="MCC", referencedColumnName ="MCC", nullable = false),
		@JoinColumn(name="MNC" , referencedColumnName="MNC", nullable = false)
	})
	//@JsonIgnore 
	private Network network;


	public Event(){
		
	}
	


	public Event(Date date, int duration, int cellId, String neVersion, String imsi, String hier3id, String hier32id,
			String hier321id, FailureClass fclass, EventCause eventCause, MobileType mobileType, Network network) {

		this.date = date;
		this.duration = duration;
		this.cellId = cellId;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3id = hier3id;
		this.hier32id = hier32id;
		this.hier321id = hier321id;
		this.fclass = fclass;
		this.eventCause = eventCause;
		this.mobileType = mobileType;
		this.network = network;
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		DateFormat df = new SimpleDateFormat("yyy-mm-dd HH:MM:SS");
		
		return df.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
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

	public FailureClass getFclass() {
		return fclass;
	}

	public void setFclass(FailureClass fclass) {
		this.fclass = fclass;
	}
	
	public EventCause getEventCause() {
		return eventCause;
	}

	public void setEventCause(EventCause eventCause) {
		this.eventCause = eventCause;
	}


	public MobileType getMobileType() {
		return mobileType;
	}

	public void setMobileType(MobileType mobileType) {
		this.mobileType = mobileType;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		duration = duration;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}


	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

}
