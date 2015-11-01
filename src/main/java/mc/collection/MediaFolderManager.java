package mc.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaFolderManager {

	private Map<Integer, MediaFolder> mapIdToFolder;
	private Map<String, MediaFolder> mapPathToFolder;
	
	public MediaFolderManager(){
		mapIdToFolder = new HashMap<>();
		mapPathToFolder = new HashMap<>();
	}
	
	public int registerOrGetFolder(String path){
		if(mapPathToFolder.containsKey(path)){
			return mapPathToFolder.get(path).getId();
		}
		
		MediaFolder folder = new MediaFolder(path);
		mapIdToFolder.put(folder.getId(), folder);
		mapPathToFolder.put(folder.getPath(), folder);
		return folder.getId();
	}
	
	public MediaFolder getFolder(int id){
		return mapIdToFolder.get(id);
	}
	
	public MediaFolder getFolder(String path){
		if(mapPathToFolder.get(path) != null){
			return mapIdToFolder.get(mapPathToFolder.get(path));
		}
		return null;
	}

	public List<String> getFolderPaths(List<Integer> folderIds){
		MediaFolder mediaFolder;
		List<String> folderPaths = new ArrayList<>();
		for(int id : folderIds){
			mediaFolder = getFolder(id);
			if(mediaFolder != null){
				folderPaths.add(mediaFolder.getPath());
			}
		}
		return folderPaths;
	}
	
	public static class MediaFolder {
		private static int ctr = 0;

		private final int id;
		private final String path;

		private MediaFolder(String path) {
			super();
			this.id = ctr++;
			this.path = path;
		}

		public int getId() {
			return id;
		}

		public String getPath() {
			return path;
		}

	}

}
