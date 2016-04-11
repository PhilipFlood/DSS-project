package services;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import entities.User;
import jpa.dao.IEventDAO;
import jpa.dao.IUserDAO;

@Local
@Stateless
public class UserServiceEJB implements UserService {

	@Inject
	private IUserDAO userDao;
	
	public Collection<User> getUserByType(int userType) {
		return userDao.getUserByType(userType);
	}
	
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
