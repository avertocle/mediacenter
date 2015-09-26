package mc.playback;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class PlaybackHandlerMac implements PlaybackHandler{

	@Override
	public void playInDefaultSystemPlayer(String absPath) throws IOException {
		Desktop.getDesktop().open(new File(absPath));
	}

	@Override
	public void openContainingFolder(String absPath) throws IOException {
		File file = new File(absPath);
		Desktop.getDesktop().open(file.getParentFile());
	}

}
