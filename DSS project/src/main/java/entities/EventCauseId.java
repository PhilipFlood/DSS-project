package entities;

import java.io.Serializable;

public class EventCauseId implements Serializable{

	private int causeCode;
	private int eventId;
	
	public EventCauseId() {
	}
	
	public EventCauseId(int causeCode, int eventId) {
		this.causeCode = causeCode;
		this.eventId = eventId;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId;
		result = prime * result + causeCode;
		return result;
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NetworkId other = (NetworkId) obj;
		if(other.getMcc()!= eventId || other.getMnc()!= causeCode){
			return false;
		}
		
		return true;
	}
	
}
