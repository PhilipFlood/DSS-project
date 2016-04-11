package services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.User;

@Local
public interface UserService {

	abstract void addUser(User user);	
	abstract void deleteUser(User user);	
	abstract Collection<User> getUserByType(int userType);	
	abstract Collection<User> getAllUsers();
	abstract User getUserByName(String username);	
}
