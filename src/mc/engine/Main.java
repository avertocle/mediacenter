package mc.engine;

import mc.config.UserProfile;
import mc.utils.Logger;

public class Main {
	
	public static void main(String[] args){
		
		initFilesAndFolders();
		Controller controller = new Controller();
		controller.start();
	}

	private static void initFilesAndFolders() {
		try{
			UserProfile.getInstance().makeConfigFilesAndDirectories();
			UserProfile.getInstance().loadUserCollectionList();
		}
		catch(Exception ex){
			Logger.logMajorEvent("Error loading config");
			Logger.logException(ex);
			System.exit(0);
		}
	}
}
