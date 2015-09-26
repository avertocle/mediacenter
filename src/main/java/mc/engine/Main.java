package mc.engine;

import mc.config.Config;
import mc.config.UserProfile;
import mc.config.Config.OS;
import mc.constants.GeneralConstants;
import mc.gui.LoginHandler;
import mc.utils.Logger;

public class Main {
	
	public static void main(String[] args){
		login();
		detectAndSetOS();
		setupSystemSpecificConstants();
		initFilesAndFolders();
		Controller controller = new Controller();
		controller.start();
	}

	private static void login() {
		String username = System.getProperty("user.name");
		boolean authResult = LoginHandler.doLogin(username);
		if(!authResult){
			System.exit(1);
		}
	}

	private static void setupSystemSpecificConstants(){
		OS os = Config.getInstance().getCurrentOS();
		
		switch(os) {
		case Windows:
			GeneralConstants.rootDir = GeneralConstants.rootDirWindows;
			break;
		case Linux:
			String home = System.getProperty("user.home");
			GeneralConstants.rootDir = home + "/" + GeneralConstants.rootDirLinux;
			break;
		case Mac:
			break;
		case Unknown:
			break;
		default:
			break;
		}
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
	
	private static void detectAndSetOS() {
		String osName = System.getProperty("os.name");
		if(osName.startsWith("Windows")){
			Config.getInstance().setCurrentOS(OS.Windows);
		}
		else if(osName.startsWith("Linux")){
			Config.getInstance().setCurrentOS(OS.Linux);
		}
		else if(osName.equalsIgnoreCase("Mac OS X")){
			Config.getInstance().setCurrentOS(OS.Mac);
		}
		else {
			Config.getInstance().setCurrentOS(OS.Unknown);
		}
	}
}
