package mc.playback;

import mc.utils.Logger;

public class PlaybackHandlerWindows implements PlaybackHandler{

	@Override
	public void playInDefaultSystemPlayer(String absPath) {
		String encodedPath =  "\"" + absPath + "\"";
		String command = "\"C:\\Program Files (x86)\\The KMPlayer\\kmplayer\" " +  encodedPath;
		
		Logger.log("Starting PlayBack for file : " + absPath);
		Logger.log("Firing System command : " + command);
		
		try {
			Runtime.getRuntime().exec(command);
		} catch (Exception ex) {
			Logger.logException(ex);
		}
	}

}
