package services;

import java.text.ParseException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import jpa.dao.FolderListener;

@Startup
@Singleton
public class FolderImporter {
	
	@EJB
	private FolderListener listener;
	
	@PostConstruct
	public void init() throws ParseException{
		listener.listen();
	}


}
