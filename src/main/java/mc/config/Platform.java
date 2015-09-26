package mc.config;

import mc.auth.AuthenticationHandler;
import mc.auth.LinuxAuthenticationHandler;
import mc.auth.MacAuthenticationHandler;
import mc.auth.WindowsAuthenticationHandler;
import mc.config.Config.OS;
import mc.explorer.MediaFinder;
import mc.explorer.MediaFinderLinux;
import mc.explorer.MediaFinderMac;
import mc.explorer.MediaFinderWindows;
import mc.playback.PlaybackHandler;
import mc.playback.PlaybackHandlerLinux;
import mc.playback.PlaybackHandlerMac;
import mc.playback.PlaybackHandlerWindows;

public class Platform {

	private static Platform instance;

	private Platform() {
		super();
		initializeMaps();
	}

	public static Platform getInstance() {
		if (instance == null) {
			instance = new Platform();
		}
		return instance;
	}

	private Class<? extends AuthenticationHandler> classAuthHandler;
	private Class<? extends PlaybackHandler> classPlaybackHandler;
	private Class<? extends MediaFinder> classMediaFinder;
	
	private void initializeMaps() {
		OS os = Config.getInstance().getCurrentOS();
		switch (os) {
		case Windows:
			classAuthHandler = WindowsAuthenticationHandler.class;
			classPlaybackHandler = PlaybackHandlerWindows.class;
			classMediaFinder = MediaFinderWindows.class;
			break;
		case Linux:
			classAuthHandler = LinuxAuthenticationHandler.class;
			classPlaybackHandler = PlaybackHandlerLinux.class;
			classMediaFinder = MediaFinderLinux.class;
			break;
		case Mac:
			classAuthHandler = MacAuthenticationHandler.class;
			classPlaybackHandler = PlaybackHandlerMac.class;
			classMediaFinder = MediaFinderMac.class;
			break;
		default:
		}
	}

	public Class<? extends AuthenticationHandler> getClassAuthHandler() {
		return classAuthHandler;
	}

	public Class<? extends PlaybackHandler> getClassPlaybackHandler() {
		return classPlaybackHandler;
	}

	public Class<? extends MediaFinder> getClassMediaFinder() {
		return classMediaFinder;
	}
	
	
	
}
