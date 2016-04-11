package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity @Table(name="Users") 
	public class User implements Serializable {
		
		@Id
		@Column(name="Username")
		private String username;
		
		@Column (name ="Password")
		private String password;
		
		@Column (name = "Usertype")
		private int userType;

		public User(){}
		
		public User(String username, String password, int userType) {
			this.username = username;
			this.password = password;
			this.userType = userType;
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

		public int getUserType() {
			return userType;
		}

		public void setUserType(int userType) {
			this.userType = userType;
		}
}
