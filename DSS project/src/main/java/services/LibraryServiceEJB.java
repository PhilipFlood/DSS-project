package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import entities.Library;
import jpa.dao.ILibraryDAO;

@Stateless
public class LibraryServiceEJB implements LibraryService{
	
	@Inject
	private ILibraryDAO libraryDAO;

	@Override
	public List<Library> searchLibrary(String libraryID) {
		return 	libraryDAO.searchLibrary(libraryID);
	}

	@Override
	public Collection<Library> getAllLibrary() {
		return libraryDAO.getAllLibrary();
	}

	@Override
	public void addLibrary(ArrayList<Library> librariesTable) {
		libraryDAO.addLibrary(librariesTable);
	}

	public ILibraryDAO getLibraryDAO() {
		return libraryDAO;
	}

	public void setLibraryDAO(ILibraryDAO libraryDAO) {
		this.libraryDAO = libraryDAO;
	}
}
