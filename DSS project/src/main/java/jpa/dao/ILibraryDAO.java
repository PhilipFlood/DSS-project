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
	List<Library> searchLibrary(int libraryID);
	abstract void addLibrary(ArrayList<Library> library);
	Collection<Library> getAllLibrary();
}
