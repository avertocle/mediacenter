package mc.playback;

import java.io.IOException;

public interface PlaybackHandler {
	
	public void playInDefaultSystemPlayer(String absPath) throws IOException;

	public void openContainingFolder(String absPath) throws IOException;

}
