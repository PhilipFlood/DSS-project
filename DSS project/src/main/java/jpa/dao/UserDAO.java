package jpa.dao;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.User;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addUser(User user) {
		em.merge(user);		
	}

	@Override
	public void deleteUser(User user) {

		em.remove(em.contains(user) ? user : em.merge(user));

	}

	@Override
	public Collection<User> getUserByType(int userType) {
		Query query  = em.createQuery("from User where userType = :userType");
		query.setParameter("userType", userType);
		List<User> allUsersOfCertainType = query.getResultList();	
		return allUsersOfCertainType;
	}

	@Override
	public Collection<User> getAllUsers() {
		Query query  = em.createQuery("select username, password, userType from User");
		List<User> listOfAllUsers = query.getResultList();
		return listOfAllUsers;
	}
	
	@Override
	public User getUserByName(String username) {
		Query query  = em.createQuery("from User where username = :username");
		query.setParameter("username", username);

		if(query.getResultList().size()>0){
			User user =  (User) query.getResultList().get(0);
			return user;
		}
		return null;
	}
	
	
	
}
