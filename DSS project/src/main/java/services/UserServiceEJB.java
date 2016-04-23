package services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jpa.dao.IUserDAO;
import entities.User;


@Local
@Stateless
public class UserServiceEJB implements UserService {

	@Inject
	private IUserDAO userDao;
		
	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}
	
	public Collection<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public void addUser(User user) {
		userDao.addUser(user);		
	}

	public void deleteUser(User user){
		userDao.deleteUser(user);
	}
	
	public IUserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}	
	
}
