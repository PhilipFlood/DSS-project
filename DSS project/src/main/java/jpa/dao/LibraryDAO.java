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

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class LibraryDAO implements ILibraryDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Library> searchLibrary(int libraryID){
		Query query  = em.createQuery("from Library where libraryID = :library");
		query.setParameter("library", libraryID);
		List<Library> result = query.getResultList();
		return result;
	}

	@Override
	public Collection<Library> getAllLibrary() {
		Query query  = em.createQuery("from Library");
		List<Library> result = query.getResultList();
		return result;
	}

	@Override
	public void addLibrary(ArrayList<Library> libraries) {
		for (Library library : libraries){
			em.merge(library);
		}
	}
}
