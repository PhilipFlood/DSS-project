package jpa.dao;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.User;

@Local
@Stateless
public interface IUserDAO {

	void addUser(User user);
	void deleteUser(User user);
	Collection<User> getUserByType(int userType);
	Collection<User> getAllUsers();
	User getUserByName(String username);

}
