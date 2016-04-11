/*package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @Table(name="Network") @IdClass(NetworkId.class)
public class Network implements Serializable {
	@Id
	@Column(name="MCC")
	private int mcc;
	
	@Id
	@Column (name ="MNC")
	private int mnc;
	
	@Column (name = "Country")
	private String country;
	
	@Column(name="Operator")
	private String operator;
	
	public Network(){}

	public Network(int mcc, int mnc, String country, String operator) {
		this.mcc = mcc;
		this.mnc = mnc;
		this.country = country;
		this.operator = operator;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

@Entity @Table(name="Network") @IdClass(NetworkId.class)
public class Network implements Serializable {
	@Id
	@Column(name="MCC")
	private int mcc;
	
	@Id
	@Column (name ="MNC")
	private int mnc;
	
	@Column (name = "Country")
	private String country;
	
	@Column(name="Operator")
	private String operator;
	
	
	@OneToMany(mappedBy="network")
	//@JsonIgnore
	private List <Event>events = new ArrayList<Event>();
	
	
	public List getEvents() {
		return events;
	}

	public void setEvents(List events) {
		this.events = events;
	}

	public Network(){}

	public Network(int mcc, int mnc, String country, String operator) {
		this.mcc = mcc;
		this.mnc = mnc;
		this.country = country;
		this.operator = operator;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
}
