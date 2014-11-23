package mc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import mc.model.media.Media;

public class LibraryTracker {
	
	private ConcurrentHashMap<Integer, Media> mapMediaToId;
	
	public LibraryTracker(){
		mapMediaToId = new ConcurrentHashMap<Integer, Media>();
	}
	
	/***************************************************************************
	/* API Methods
	/***************************************************************************/
	
	public void addMedia(Media media){
		mapMediaToId.put(media.getId(), media);
	}
	
	public Media getMediaById(int id){
		return mapMediaToId.get(id);
	}
	
	public List<Media> getAllMedia(){
		return (new ArrayList<Media>(mapMediaToId.values()));
	}
	
	public void clearAllMedia(){
		mapMediaToId.clear();
	}
	
}
