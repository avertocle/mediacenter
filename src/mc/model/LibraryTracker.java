package mc.model;

import java.util.concurrent.ConcurrentHashMap;

public class LibraryTracker {
	
	public ConcurrentHashMap<Integer, Media> mapMediaToId;
	
	public LibraryTracker(){
		mapMediaToId = new ConcurrentHashMap<Integer, Media>();
	}
	
}
