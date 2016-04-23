/*package jaxb;

import java.io.File;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import jaxb.entities.Array;
import jaxb.entities.Dict;
import jaxb.entities.Key;
import jaxb.entities.Plist;
import entities.Playlist;
import entities.Track;

public class main {
	static ArrayList<Track> tracks = new ArrayList<Track>();
	public static void main(String[] args) {
		try {
			//for final obj
			
			ArrayList<Playlist> playlists = new ArrayList<Playlist>();
			//for temp
			Dict trackdict = null;
			Array playlistarr = null;
			
			//main funct
			File file = new File("iTunes Music Library1.xml");  
			JAXBContext jaxbContext = JAXBContext.newInstance(Plist.class);  
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			Plist plist = (Plist) jaxbUnmarshaller.unmarshal(file);  			//main data construct
			Dict dict = (Dict) plist.getchildren().get(0);						//every element inside
			
			List<Object> list = dict.getchildren();								//using this for lots of things
			
			String libpersistance;	//library persistance
			for(int i=0;i<list.size();i++){
				if (list.get(i)  instanceof Key) {
					Key key = (Key) list.get(i);
					if(key.getvalue().matches("Library Persistent ID")){
						libpersistance = (String) list.get(i+1);
						System.out.println("library persistance ID: "+libpersistance);
					}
					if(key.getvalue().matches("Tracks")){
						trackdict = (Dict) list.get(i+1);
						System.out.println("track dict: "+trackdict);

					}
					if(key.getvalue().matches("Playlists")){
						playlistarr = (Array) list.get(i+1);
						System.out.println("playlists: "+playlistarr);
					}
				}
			}
			
			list.clear();
			list = trackdict.getchildren();
			//going through all songs dict
			for(int i = 1;i<list.size();i=i+2){	
				Dict song = (Dict) list.get(i);
				List songinfo = song.getchildren();
				
				int trackid = 0;
				String name = null;
				String artist = null;
				String album = null;
				String genre = null;
				//System.out.println("");
				//System.out.println("****** track number: " + i + "******");
				//going through indiv song dict
				for(int j = 0;j<songinfo.size();j++){
					if (songinfo.get(j) instanceof Key) {
						Key key = (Key) songinfo.get(j);
						if(key.getvalue().matches("Track ID")){
							//System.out.println("track id: " + songinfo.get(j+1));
							trackid = (int) songinfo.get(j+1);
						}
						if(key.getvalue().matches("Name")){
							//System.out.println("name: " + songinfo.get(j+1));
							name = (String) songinfo.get(j+1);
						}
						if(key.getvalue().matches("Artist")){
							//System.out.println("artist: " + songinfo.get(j+1));
							artist = (String) songinfo.get(j+1);
						}
						if(key.getvalue().matches("Album")){
							//System.out.println("album: " + songinfo.get(j+1));
							album = (String) songinfo.get(j+1);
						}
						if(key.getvalue().matches("Genre")){
							//System.out.println("genre: " + songinfo.get(j+1));
							genre = (String) songinfo.get(j+1);
						}
					}
				}
				Track track = new Track(trackid, name, artist, album, genre);
				tracks.add(track);
			}
												
			
			list.clear();
			list = playlistarr.getchildren();
			//System.out.println("**********");
			//System.out.println("Going through playlists");
			
			for(int i = 0;i<list.size();i++){
				Dict playlist = (Dict) list.get(i);
				List playlistinfo = playlist.getchildren();			
				
				int playlistID = 0;
				String name = null;
				ArrayList<Integer> ptrackIds = new ArrayList<Integer>();
				ArrayList<Track> ptracks = new ArrayList<Track>();
				
				//System.out.println("");
				//System.out.println("playlist number: "+i);
				//GET INFRO FROM PLAYLISTS
				for(int j = 0; j<playlistinfo.size(); j++){
					if (playlistinfo.get(j) instanceof Key) {
						Key key = (Key) playlistinfo.get(j);
						if(key.getvalue().matches("Name")){
							name = (String) playlistinfo.get(j+1);
							//System.out.println("Playlist Name: "+name);
						}
						if(key.getvalue().matches("Playlist ID")){
							playlistID = (int) playlistinfo.get(j+1);
							//System.out.println("Playlist ID: " + playlistID);
						}
					}
					if (playlistinfo.get(j) instanceof Array) {
						Array array = (Array) playlistinfo.get(j);
						List songdicts = array.getchildren();
						//GET SONGS ID
						for(int k=0;k<songdicts.size();k++){					
							Dict plistsong = (Dict) songdicts.get(k);
							int plistsongid = (int) plistsong.getchildren().get(1); 
							
							//System.out.println("song ID: "+plistsong.getchildren().get(1));
							ptrackIds.add(plistsongid);
						}
					}
				}
				
				if(!ptrackIds.isEmpty()){
					for(int j = 0;j<ptrackIds.size();j++){
						
						Track newtrack = getTrack(ptrackIds.get(j));
						ptracks.add(newtrack);
					}
				}
				
				Playlist newplaylist = new Playlist(playlistID, name, ptracks);
				playlists.add(newplaylist);
			}
			
			System.out.println("");
			System.out.println("********");
			System.out.println("arraylist of tracks by name");
			for(int i = 0; i <tracks.size();i++){
				System.out.println(tracks.get(i).getName());
			}
			
			System.out.println("");
			System.out.println("********");
			System.out.println("arraylist of playlists:");
			for(int i = 0; i <playlists.size();i++){
				//System.out.println(playlists.get(i).getName());
				//System.out.println("tracks in playlist "+playlists.get(i).getName());
				//System.out.println(playlists.get(i).getTracks());
				System.out.println(playlists.get(i));
			}
		} 
		catch (JAXBException e) {  
			e.printStackTrace();  
		}  
	}
	
	public static Track getTrack(int trackID){
		for(int i=0;i<tracks.size();i++)
			if(tracks.get(i).getTrackid() == trackID){
				Track track = tracks.get(i);
				return track;
			}
		return null;
	}
}*/