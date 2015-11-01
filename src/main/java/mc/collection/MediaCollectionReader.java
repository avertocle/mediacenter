package mc.collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import mc.config.Meta;
import mc.utils.Logger;

import org.apache.commons.lang3.StringUtils;

public class MediaCollectionReader {
	
	private static final String Separator = "|";

	private static final String PROPERTY_KEY_VERSION = "COLLECTION_MANAGER_VERSION";
	
	private MediaFolderManager mfm;
	private String fileName;
	
	public MediaCollectionReader(MediaFolderManager mfm, String fileName){
		this.fileName = fileName;
		this.mfm = mfm;
	}
	
	public List<MediaCollection> loadUserCollectionList() throws IOException {
		List<MediaCollection> usrCollectionList = new ArrayList<MediaCollection>();
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File(fileName)));
		String collectionName;
		String folderPathListString;
		List<String> folderPaths;
		List<Integer> folderIds;
		MediaCollection mc;
		
		String version = String.valueOf(properties.get(PROPERTY_KEY_VERSION));
		if(!version.equals(Meta.COLLECTION_MANAGER_VERSION)){
			Logger.logError("UserProfile :: Could not load config. Version mismatch");
			return usrCollectionList;
		}
		
		properties.remove(PROPERTY_KEY_VERSION);

		for (Entry<Object, Object> entry : properties.entrySet()) {
			collectionName = String.valueOf(entry.getKey());
			folderPathListString = String.valueOf(entry.getValue());
			folderPaths = Arrays.asList(StringUtils.split(folderPathListString, Separator));
			folderIds = createFoldersFromPathList(folderPaths);
			mc = new MediaCollection();
			for(int id : folderIds){
				mc.addFolder(id);
			}
			mc.setName(collectionName);
			usrCollectionList.add(mc);
		}
		return usrCollectionList;

	}
	
	private List<Integer> createFoldersFromPathList(List<String> folderPaths) {
		List<Integer> folderIds = new ArrayList<>();
		int id;
		for(String path : folderPaths){
			id = mfm.registerOrGetFolder(path);
			folderIds.add(id);
		}
		return folderIds;
	}

	public void saveUserCollectionList(List<MediaCollection> usrCollectionList) throws IOException {
		Properties properties = new Properties();
		List<String> folderPaths;
		String propertyKey = "", propertyValue = "";
		for (MediaCollection mc : usrCollectionList) {
			folderPaths = mfm.getFolderPaths(mc.getFolders());
			propertyKey = mc.getName();
			propertyValue = StringUtils.join(folderPaths, Separator);
			properties.put(propertyKey, propertyValue);
		}
		properties.put(PROPERTY_KEY_VERSION, Meta.COLLECTION_MANAGER_VERSION);
		String comment = " ^^^^^^ DO NOT TOUCH ^^^^^^";
		properties.store(new FileOutputStream(new File(fileName)), comment);
	}


}
