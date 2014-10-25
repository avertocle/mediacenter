package mc.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import mc.config.Config;
import mc.event.a2g.RootEventA2G;
import mc.event.a2g.RootEventA2G.EventTypeA2G;
import mc.event.g2c.RootEventG2C;
import mc.event.g2c.RootEventG2C.EventTypeG2C;
import mc.explorer.MediaFinder;
import mc.explorer.MediaFinderWindows;
import mc.gui.GuiController;
import mc.model.translater.DefaultGuiTranslater;
import mc.model.translater.GuiTranslator;
import mc.playback.PlaybackHandler;
import mc.playback.PlaybackHandlerWindows;
import mc.utils.Logger;

public class Controller extends Thread {

	private ConcurrentLinkedQueue<RootEventA2G> anywhereToGui;
	private ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	private boolean runForever;

	private GuiController guiController;
	private int controllerSleepTime = 50;
	
	private GuiTranslator guiTranslator;
	private PlaybackHandler playbackHandler;
	
	private MediaFinder mediaFinder;

	public Controller() 
	{
		super();
		runForever = true;
	}
	
	private void initialize() {
		
		mediaFinder = new MediaFinderWindows();
		
		anywhereToGui = new ConcurrentLinkedQueue<RootEventA2G>();
		gcToPc = new ConcurrentLinkedQueue<RootEventG2C>();
		guiController = new GuiController(anywhereToGui, gcToPc);
		guiController.start();
		
		guiTranslator = new DefaultGuiTranslater();
		playbackHandler = new PlaybackHandlerWindows();
	}
	
	public void stopServer()
	{
		if(guiController.isAlive()){
			guiController.stopServer();
		}
		
		runForever = false;
	}

	/*********** Exposed Methods *******************************/
	
	public void run()
	{
		initialize();
		doStartupTasks();
		
		boolean a2geEmpty = false;
		RootEventG2C g2ce = null;
		
		while(!a2geEmpty || runForever)
		{
			g2ce = gcToPc.poll();
			if(g2ce == null)
				a2geEmpty = true;
			else
			{
				try {	handleG2CEvent(g2ce);	}
				catch (Exception ex) { Logger.logException(ex);	}
			}
			
			if(a2geEmpty)
			{
				try {	sleep(controllerSleepTime);	} 
				catch (InterruptedException e) { Logger.logException(e);	}
			}
		}
	}

	private void doStartupTasks() {
		List<String> libFolderList = new Config().getLibraryFolders();
		List<File> linFiles = mediaFinder.findAllMediaInDir(libFolderList.get(0));
		
		List<Object[]> guiData = new ArrayList<Object[]>();
		for(File file : linFiles){
			guiData.add(new Object[]{file.getAbsolutePath()});
		}
		
		RootEventA2G event = new RootEventA2G(EventTypeA2G.GuiReloadLibrary, guiData);
		anywhereToGui.add(event);
	}
	
	private void handleG2CEvent(RootEventG2C g2ce) {
		EventTypeG2C g2ceEventType = g2ce.getType();
		switch (g2ceEventType) {
		case PlayMedia:
		{
			String absPath = (String)(g2ce.getData());
			playbackHandler.playInDefaultSystemPlayer(absPath);
			break;
		}
		default:
			break;
		}
	}

}
