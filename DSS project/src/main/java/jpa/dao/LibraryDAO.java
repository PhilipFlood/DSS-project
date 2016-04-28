package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Library;
import entities.Playlist;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class LibraryDAO implements ILibraryDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Library> searchLibrary(String username){
		Query query  = em.createQuery("select libraryID from Library where owner.username = :username");
		query.setParameter("username", username);
		List<Library> result = query.getResultList();
		return result;
	}

	@Override
	public Collection<Library> getAllLibrary() {
		Query query  = em.createQuery("select libraryID, owner.username from Library");
		List<Library> result = query.getResultList();
		return result;
	}

	@Override
	public void addLibrary(ArrayList<Library> libraries) {
		for (Library library : libraries){
			em.merge(library);
		}
	}
	
	@Override
	public void deleteLibrary(Library Library) {
		em.remove(em.contains(Library) ? Library : em.merge(Library));
	}

	@Override
	public Library getLibraryByID(String libraryID) {
		Query query  = em.createQuery("from Library where libraryID = :libraryID");
		query.setParameter("libraryID", libraryID);

		if(query.getResultList().size()>0){
			Library library =  (Library) query.getResultList().get(0);
			return library;
		}
		return null;
	}
}
