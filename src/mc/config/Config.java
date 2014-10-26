package mc.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import mc.constants.GeneralConstants;

public class Config {
	
	private static Config instance;
	
	private List<String> usrLibDirList;
	
	private static final String fname_userLibraryConf = GeneralConstants.rootDir + "userLibConf.ini";
	
	private Config(){
		super();
	}
	
	public static Config getInstance(){
		if(instance == null){
			instance = new Config();
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
		
		file = new File(fname_userLibraryConf);
		file.createNewFile();
	}
	
	public void loadUserLibraryDirList() throws IOException{
		usrLibDirList = new ArrayList<String>();
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File(fname_userLibraryConf)));
		for(Entry<Object, Object> entry : properties.entrySet()){
			usrLibDirList.add(entry.getValue().toString());
		}
	}

	/***************************************************************************
	/* Get Methods
	/***************************************************************************/

	public List<String> getUsrLibDirList() {
		return usrLibDirList;
	}
	
	
	
	
}
