package jpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Library;;

@Local
@Stateless
public interface ILibraryDAO {
	List<Library> searchLibrary(String username);
	abstract void addLibrary(ArrayList<Library> library);
	Collection<Library> getAllLibrary();
	void deleteLibrary(Library Library);
	Library getLibraryByID(String libraryID);
}
