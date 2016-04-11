/*package entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity @Table(name="MobileType")

public class MobileType implements Serializable {
	
	@Id
	@Column(name="TAC")
	private int tac;
	@Column(name="Marketing_name")
	private String marketingName;
	@Column(name="Manufacturer")
	private String manufacturer;
	@Column(name="Access_Compatibility")
	private String accessCompatibility;
	@Column(name="UE_Type")
	private String ueType;
	@Column(name="OS")
	private String os;
	@Column(name="Input_Type")
	private String inputType;
	
	
	public MobileType(){
		
	}
	
	public MobileType(int tac, String marketingName, String manufacturer,
			String accessCompatibility, String ueType, String os,
			String inputType) {
		this.tac = tac;
		this.marketingName = marketingName;
		this.manufacturer = manufacturer;
		this.accessCompatibility = accessCompatibility;
		this.ueType = ueType;
		this.os = os;
		this.inputType = inputType;
	}

	public int getTac() {
		return tac;
	}

	public void setTac(int tac) {
		this.tac = tac;
	}

	public String getMarketingName() {
		return marketingName;
	}

	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAccessCompatibility() {
		return accessCompatibility;
	}

	public void setAccessCompatibility(String accessCompatibility) {
		this.accessCompatibility = accessCompatibility;
	}

	public String getUeType() {
		return ueType;
	}

	public void setUeType(String ueType) {
		this.ueType = ueType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

}
*/

package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="MobileType")

public class MobileType implements Serializable {
	
	@Id
	@Column(name="TAC")
	private int tac;
	@Column(name="Marketing_name")
	private String marketingName;
	@Column(name="Manufacturer")
	private String manufacturer;
	@Column(name="Access_Compatibility")
	private String accessCompatibility;
	@Column(name="UE_Type")
	private String ueType;
	@Column(name="OS")
	private String os;
	@Column(name="Input_Type")
	private String inputType;
	
	
	@OneToMany(mappedBy="mobileType")
	//@JsonIgnore
	private List <Event>events = new ArrayList<Event>();
	
	
	public List getEvents() {
		return events;
	}

	public void setEvents(List events) {
		this.events = events;
	}

	public MobileType(){
		
	}
	
	public MobileType(int tac, String marketingName, String manufacturer,
			String accessCompatibility, String ueType, String os,
			String inputType) {
		this.tac = tac;
		this.marketingName = marketingName;
		this.manufacturer = manufacturer;
		this.accessCompatibility = accessCompatibility;
		this.ueType = ueType;
		this.os = os;
		this.inputType = inputType;
	}

	public int getTac() {
		return tac;
	}

	public void setTac(int tac) {
		this.tac = tac;
	}

	public String getMarketingName() {
		return marketingName;
	}

	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAccessCompatibility() {
		return accessCompatibility;
	}

	public void setAccessCompatibility(String accessCompatibility) {
		this.accessCompatibility = accessCompatibility;
	}

	public String getUeType() {
		return ueType;
	}

	public void setUeType(String ueType) {
		this.ueType = ueType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

}
