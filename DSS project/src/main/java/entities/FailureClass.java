/*package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="FailureClass")
public class FailureClass implements Serializable{
	
	@Id
	@Column(name="FailureClass")
	private int failureClass ;  //need to rename this entry on dB
	
	@Column(name="Description")
	private String description;
	
	public FailureClass(){}

	public FailureClass(int failureCode, String description) {
		this.failureClass = failureCode;
		this.description = description;
	}

	public int getFailureCode() {
		return failureClass;
	}

	public void setFailureCode(int failureCode) {
		this.failureClass = failureCode;
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
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="FailureClass")

public class FailureClass implements Serializable{
	
	@Id
	private int failureClass ;  //need to rename this entry on dB
	
	@Column(name="Description")
	private String description;
	
	
	@OneToMany(mappedBy="fclass")
	//@JsonIgnore
	private List <Event> events = new ArrayList<Event>() ;
	
	public int getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	
	public List getEvents() {
		return events;
	}

	public void setEvents(List events) {
		this.events = events;
	}

	public FailureClass(){}

	public FailureClass(int failureCode, String description) {
		this.failureClass = failureCode;
		this.description = description;
	}

	public int getFailureCode() {
		return failureClass;
	}

	public void setFailureCode(int failureCode) {
		this.failureClass = failureCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
	
	
	
	
	
}
