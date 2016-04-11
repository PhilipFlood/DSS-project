package jpa.dao;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.ParseException;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import services.LoadService;

@Stateless
public class FolderListener {
	
	@Inject
	private LoadService loadService;

	@Asynchronous
    public void listen() throws ParseException {
        // Sanity check - Check if path is a folder
		//Path path = Paths.get("C:/Users/D15122957/Documents/test");
		Path path = Paths.get("/home/shanu/Desktop/datasets");
		//Path path = Paths.get("C:/Users/D15122951/Desktop/datasets");
		//Path path = Paths.get("/home/user1/Music");
        try {
            Boolean isFolder = (Boolean) Files.getAttribute(path,
                    "basic:isDirectory", NOFOLLOW_LINKS);
            if (!isFolder) {
                throw new IllegalArgumentException("Path: " + path
                        + " is not a folder");
            }
        } catch (IOException ioe) {
            // Folder does not exists
            ioe.printStackTrace();
        }

        System.out.println("Watching path: " + path);

        // We obtain the file system of the Path
        FileSystem fs = path.getFileSystem();

        // We create the new WatchService using the new try() block
        try(WatchService service = fs.newWatchService()) {
            // We register the path to the service
            // We watch for creation events
            path.register(service, ENTRY_MODIFY);

            // Start the infinite polling loop
            WatchKey key = null;
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
						service.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            });
            while (true) {
                key = service.take();

                // Dequeueing events
                Kind<?> kind = null;
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    // Get the type of the event
                    kind = watchEvent.kind();
                    if (OVERFLOW == kind) {
                        continue; // loop
                    } else if (ENTRY_MODIFY == kind) {
                        // modified
                    	//****CHANGE FILE PATH****
                        //File newPath = new File("C:/Users/D15122957/Documents/test/" + watchEvent.context());
                        File newPath = new File("/home/shanu/Desktop/datasets/" + watchEvent.context());

                        //File newPath = new File("C:/Users/D15122951/Desktop/datasets/" + watchEvent.context());

                        loadService.addAllData(newPath);
                        // Output
                        System.out.println("New path modified: " + newPath);
                    }
                }

                if (!key.reset()) {
                    break; // loop
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
		
    }
}
