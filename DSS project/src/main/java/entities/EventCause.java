/*package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;



@Entity @Table(name="EventCause") @IdClass(EventCauseId.class)
public class EventCause implements Serializable{

	@Id
	@Column (name="Cause_Code")
	private int causeCode;
	
	@Id
	@Column (name="EventId")
	private int eventId;
	
	@Column(name="Description")
	private String description;
	
	public EventCause(){}

	public EventCause(int causeCode, int eventId, String description) {
		this.causeCode = causeCode;
		this.eventId = eventId;
		this.description = description;
	}

	public int getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
*/
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity @Table(name="EventCause") @IdClass(EventCauseId.class)
public class EventCause implements Serializable{

	@Id
	@Column (name="Cause_Code")
	private int causeCode;
	
	@Id
	@Column (name="EventId")
	private int eventId;
	
	@Column(name="Description")
	private String description;
	
	
	@OneToMany(mappedBy="eventCause")
	//@JsonIgnore
	private List<Event> events ;
	
	
	public List getEvents() {
		return events;
	}

	public void setEvents(List events) {
		this.events = events;
	}

	
	
	public EventCause(){}

	public EventCause(int causeCode, int eventId, String description) {
		this.causeCode = causeCode;
		this.eventId = eventId;
		this.description = description;
	}

	public int getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
