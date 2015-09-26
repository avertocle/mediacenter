package mc.playback;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class PlaybackHandlerLinux implements PlaybackHandler{

	@Override
	public void playInDefaultSystemPlayer(String absPath) throws IOException {
//		String encodedPath =  "\"" + absPath + "\"";
//		String command = "start " +  encodedPath;
//		Logger.log("Starting PlayBack for file : " + absPath);
//		Logger.log("Firing System command : " + command);
		Desktop.getDesktop().open(new File(absPath));
	}

	@Override
	public void openContainingFolder(String absPath) throws IOException {
		File file = new File(absPath);
		Desktop.getDesktop().open(file.getParentFile());
	}

}
