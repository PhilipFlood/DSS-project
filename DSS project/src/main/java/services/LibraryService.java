package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import entities.Library;;

@Local
public interface LibraryService {
	
	abstract List<Library> searchLibrary(String libraryID);
	abstract Collection<Library> getAllLibrary();
	abstract void addLibrary(ArrayList<Library> librariesTable);
	abstract void deleteLibrary(Library Library);
	abstract Library getLibraryByID(String libraryID);
}
