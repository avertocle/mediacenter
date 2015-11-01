package mc.collection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mc.constants.GeneralConstants;
import mc.utils.Logger;

public class UserProfile {

	private static final String fname_userCollections = GeneralConstants.rootDir + "userCollections.ini";

	private Map<Integer, MediaCollection> mapIdToMC;
	private Map<String, MediaCollection> mapNameToMC;
	
	private MediaFolderManager mediaFolderManager;
	private MediaCollectionReader mediaCollectionReader;
	private int userPrimaryCollection;

	public UserProfile() {
		super();
		mapIdToMC = new HashMap<>();
		mapNameToMC = new HashMap<>();
		userPrimaryCollection = -1;
		this.mediaFolderManager = new MediaFolderManager();
		this.mediaCollectionReader = new MediaCollectionReader(mediaFolderManager, fname_userCollections);
	}

	/***************************************************************************
	 * /* Initialization Methods /
	 ***************************************************************************/

	public static void makeConfigFilesAndDirectories() throws IOException {

		File file = new File(GeneralConstants.rootDir);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}

		file = new File(fname_userCollections);
		file.createNewFile();
	}

	public void initialize() throws IOException {
		List<MediaCollection> mcList = mediaCollectionReader.loadUserCollectionList();
		for(MediaCollection mc : mcList){
			mapIdToMC.put(mc.getId(), mc);
			mapNameToMC.put(mc.getName(), mc);
		}
	}

	/***************************************************************************
	 * API Methods :: ADD
	 ***************************************************************************/

	public int addMediaCollection(String collectionName) throws IOException {
		MediaCollection mc = new MediaCollection();
		mc.setName(collectionName);
		mapIdToMC.put(mc.getId(), mc);
		mapNameToMC.put(mc.getName(), mc);
		mediaCollectionReader.saveUserCollectionList(getUserCollectionList());
		return mc.getId();
	}

	public void addFolderToCollection(int collectionId, File file) throws IOException {
		int folderId;
		if (mapIdToMC.containsKey(collectionId)) {
			folderId = mediaFolderManager.registerOrGetFolder(file.getAbsolutePath());
			mapIdToMC.get(collectionId).addFolder(folderId);
		}
		Logger.logError("UserProfile :: Error while adding folder to collection :: No collection found with id = "
				+ collectionId);
		mediaCollectionReader.saveUserCollectionList(getUserCollectionList());
	}

	public void setUserPrimaryCollection(int collectionId) {
		if (mapIdToMC.containsKey(collectionId)) {
			userPrimaryCollection = collectionId;
		}
		Logger.logError("UserProfile :: Error while settignpromary collection :: No collection found with id = "
				+ collectionId);
	}

	/***************************************************************************
	 * API Methods :: GET
	 ***************************************************************************/

	public MediaCollection getMediaCollection(int id){
		return mapIdToMC.get(id);
	}
	
	public MediaCollection getMediaCollection(String name){
		return mapNameToMC.get(name);
	}
	
	public int getUserPrimaryCollection() {
		if(userPrimaryCollection == -1 && mapIdToMC.size() > 0){
			return mapIdToMC.keySet().iterator().next();
		}
		return userPrimaryCollection;
	}

	public List<MediaCollection> getUserCollectionList() {
		return new ArrayList<MediaCollection>(mapIdToMC.values());
	}

	public List<String> getAllFolderPaths(MediaCollection mc) {
		List<Integer> folderIds = mc.getFolders();
		List<String> folderPaths = mediaFolderManager.getFolderPaths(folderIds);
		return folderPaths;
	}
}
