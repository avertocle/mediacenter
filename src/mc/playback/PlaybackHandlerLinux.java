package mc.playback;

import java.awt.Desktop;
import java.io.File;

public class PlaybackHandlerLinux implements PlaybackHandler{

	@Override
	public void playInDefaultSystemPlayer(String absPath) throws Exception {
//		String encodedPath =  "\"" + absPath + "\"";
//		String command = "start " +  encodedPath;
//		Logger.log("Starting PlayBack for file : " + absPath);
//		Logger.log("Firing System command : " + command);
		Desktop.getDesktop().open(new File(absPath));
	}

}
