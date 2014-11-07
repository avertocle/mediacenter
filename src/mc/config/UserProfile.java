package mc.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import mc.constants.GeneralConstants;
import mc.utils.MiscUtils;

public class UserProfile {
	
	private static UserProfile instance;
	
	private List<String> usrCollectionList;
	
	private static final String fname_userCollections = GeneralConstants.rootDir + "userCollections.ini";
	
	private static final String ConfigFileKeyName = "collection";
	
	private UserProfile(){
		super();
	}
	
	public static UserProfile getInstance(){
		if(instance == null){
			instance = new UserProfile();
		}
		return instance;
	}
	
	/***************************************************************************
	/* Initialization Methods
	/***************************************************************************/
	
	public void makeConfigFilesAndDirectories() throws IOException {
		
		File file = new File(GeneralConstants.rootDir);
		if(!file.exists() || !file.isDirectory()){
			file.mkdirs();
		}
		
		file = new File(fname_userCollections);
		file.createNewFile();
	}
	
	public void loadUserCollectionList() throws IOException{
		usrCollectionList = new ArrayList<String>();
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File(fname_userCollections)));
		for(Entry<Object, Object> entry : properties.entrySet()){
			usrCollectionList.add(entry.getValue().toString());
		}
	}
	
	public void saveUserCollectionList() throws IOException{
		Properties properties = new Properties();
		int ctr=0;
		for(String dirName : usrCollectionList){
			properties.put(ConfigFileKeyName + "_" + ctr, dirName);
			ctr++;
		}
		String comment = "Settings saved at : " + MiscUtils.getCurrentTimestamp();
		properties.store(new FileOutputStream(new File(fname_userCollections)), comment);
	}
	/***************************************************************************
	/* API Methods
	/***************************************************************************/

	public List<String> getUserCollectionList() {
		return usrCollectionList;
	}
	
	public void addCollection(File file){
		usrCollectionList.add(file.getAbsolutePath());
	}
	
	
	
}
