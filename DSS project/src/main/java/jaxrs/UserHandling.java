package jaxrs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import services.LibraryService;
import services.UserService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Library;
import entities.User;

@Path("/users")
public class UserHandling {

	@Inject 
	private UserService userService;
	@Inject 
	private LibraryService libraryService;
	
	@GET
	@Path("/allUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getAllUsers() {
		List <User> users = new ArrayList<User>();
		users =(List<User>) userService.getAllUsers();
		return users;
	}
	
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User SearchUser(String user) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();	
		user = objectMapper.readValue(user, String.class);
		
		if(userService.getUserByName(user) != null){
			return userService.getUserByName(user);
		}
		else{
			return null;
		}	
	}
	
	@POST
	@Path("/authenticate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User Authenticate(String user) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();	
		user = objectMapper.readValue(user, String.class);
		String username = user.split(" ")[0];
		String password = user.split(" ")[1];
		User nullUser = new User("","");
		User selectedUser=null;
		
		if(userService.getUserByName(username) != null){
			selectedUser = userService.getUserByName(username);
		}
		else{
			return nullUser;
		}
		
		if(password.equals(selectedUser.getPassword())){
			return selectedUser;
		}
		else{
			return nullUser;
		}
	}
	
	@POST
	@Path("/adduser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User AddUser(String user) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();	
		user = objectMapper.readValue(user, String.class);	
		String username = user.split(" ")[0];
		String password = user.split(" ")[1];
		
		User newUser = new User(username, password);

		if(userService.getUserByName(username) != null){
			User nullUser = new User("","");
			return nullUser;
		}
		else{
			userService.addUser(newUser);
			return newUser;
		}	
	}
	
	@POST
	@Path("/deleteuser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User DeleteUser(String user) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();	
		user = objectMapper.readValue(user, String.class);
		//System.out.println(user);
		
		if(userService.getUserByName(user) != null){
			User deleteuser = userService.getUserByName(user);
			userService.deleteUser(deleteuser);
			return deleteuser;
		}
		else{
			User nullUser = new User("","");
			return nullUser;
		}	
	}
	
	@POST
	@Path("/edituser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User EditUser(String user) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();	
		user = objectMapper.readValue(user, String.class);
		
		String oldname = user.split(" ")[0];
		String username = user.split(" ")[1];
		String password = user.split(" ")[2];
		User nulluser;
		
		User selecteduser = userService.getUserByName(oldname);
		selecteduser.setPassword(password);		
		userService.addUser(selecteduser);
		
		if(oldname.equals(username)){
			return selecteduser;
		}
		else{
			if(userService.getUserByName(username)!=null){
				nulluser = new User("","");
				return nulluser;
			}
			else{
				selecteduser.setUsername(username);
				userService.deleteUser(userService.getUserByName(oldname));
				userService.addUser(selecteduser);
				return selecteduser;
			}
		}	

	}
	
}
