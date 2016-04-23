package entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="Users") 
public class User implements Serializable {

	@Id
	@Column(name="username")private String username;
	@Column (name ="password")private String password;

	@OneToMany(mappedBy="owner")
	//@JsonIgnore
	private List<Library> librarys = new ArrayList<Library>();

	
	public User(){}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public List<Library> getLibrarys() {
		return librarys;
	}
	public void setLibrarys(List<Library> librarys) {
		this.librarys = librarys;
	}
}
