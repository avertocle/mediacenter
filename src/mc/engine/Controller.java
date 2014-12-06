package mc.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import mc.config.Config;
import mc.config.Config.OS;
import mc.config.UserProfile;
import mc.constants.GeneralConstants;
import mc.event.a2g.RootEventA2G;
import mc.event.a2g.RootEventA2G.EventTypeA2G;
import mc.event.g2c.RootEventG2C;
import mc.event.g2c.RootEventG2C.EventTypeG2C;
import mc.explorer.MediaFinder;
import mc.explorer.MediaFinderLinux;
import mc.explorer.MediaFinderWindows;
import mc.gui.GuiController;
import mc.model.LibraryTracker;
import mc.model.media.Media;
import mc.model.media.MediaInfo;
import mc.model.media.MediaInfoExtracter;
import mc.model.media.MediaInfoFetcher;
import mc.model.movie.LocalMovieInfoFetcher;
import mc.model.movie.RtMovieInfoFetcher;
import mc.model.translater.GuiTranslaterMovie;
import mc.model.translater.GuiTranslator;
import mc.playback.PlaybackHandler;
import mc.playback.PlaybackHandlerLinux;
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
	
	private LibraryTracker libraryTracker;
	
	private MediaFinder mediaFinder;
	private MediaInfoExtracter mediaInfoExtracter;
	private MediaInfoFetcher mediaInfoFetcher;
	private MediaInfoFetcher mediaInfoFetcherLocal;
	
	public Controller() {
		super();
		runForever = true;
	}

	public void stopServer() {
		if(guiController.isAlive()){
			guiController.stopServer();
		}
		runForever = false;
	}

	/************************************************************/
	/** Methods 											
	/************************************************************/	
	
	public void run() {
		initialize();
		doStartupTasks();
		
		boolean a2geEmpty = false;
		RootEventG2C g2ce = null;
		
		while(!a2geEmpty || runForever) {
			g2ce = gcToPc.poll();
			if(g2ce == null)
				a2geEmpty = true;
			else {
				try {	handleG2CEvent(g2ce);	}
				catch (Exception ex) { Logger.logException(ex);	}
			}
			
			if(a2geEmpty) {
				try {	sleep(controllerSleepTime);	} 
				catch (InterruptedException e) { Logger.logException(e);	}
			}
		}
	}

	private void doStartupTasks() {
		scanCollectionsAndInformGui();
	}
	
	private void scanCollectionsAndInformGui(){
		loadLibraryInModel();
		sendLibraryDataToGui();
	}
	
	private void scanCollectionsAndInformGui(List<String> list){
		loadLibraryInModel(list);
		sendLibraryDataToGui();
	}
	
	private void loadLibraryInModel(){
		List<String> collectionList = UserProfile.getInstance().getUserCollectionList();
		loadLibraryInModel(collectionList);
	}
	
	private void loadLibraryInModel(List<String> collectionList){
		List<File> allFileList = new ArrayList<File>();
		for(String collection : collectionList){
			allFileList.addAll(mediaFinder.findAllMediaInDir(collection));
		}
		Media media;
		MediaInfo mediaInfo;
		libraryTracker.clearAllMedia();
		for(File file : allFileList){
			media = mediaInfoExtracter.getMediaObject(file);
			mediaInfoFetcherLocal.fetchInfo(media.getName());
			mediaInfo = mediaInfoFetcherLocal.fetchInfo(media.getName());
			media.setMediaInfo(mediaInfo);
			if(GeneralConstants.fetchMovieInfo){
				mediaInfo = mediaInfoFetcher.fetchInfo(media.getName());
				if(mediaInfo != null){
					media.setMediaInfo(mediaInfo);
				}
			}
			libraryTracker.addMedia(media);
		}
	}
	
	private void sendLibraryDataToGui(){
		List<Object> guiData = new ArrayList<Object>();
		List<Media> mediaList = libraryTracker.getAllMedia();
		for(Media m : mediaList){
			guiData.add(guiTranslator.getGuiDisplayObject(EventTypeA2G.GuiReloadLibrary, m));
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
			try {
				playbackHandler.playInDefaultSystemPlayer(absPath);
			} catch (Exception ex) {
				Logger.logException(ex);
			}
			break;
		}
		case AddDirToLib:
		{
			File file = (File)(g2ce.getData());
			UserProfile.getInstance().addCollection(file);
			
			try{	UserProfile.getInstance().saveUserCollectionList();	}
			catch(Exception ex ){	Logger.logException(ex);	}

			List<String> dirList = UserProfile.getInstance().getUserCollectionList();
			anywhereToGui.add(new RootEventA2G(EventTypeA2G.Resp_GetDirList, dirList));
			
			break;
		}
		case ModelRequest_DirList:
		{
			List<String> dirList = UserProfile.getInstance().getUserCollectionList();
			anywhereToGui.add(new RootEventA2G(EventTypeA2G.Resp_GetDirList, dirList));
			break;
		}
		case RescanCollection:
		{
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>)g2ce.getData();
			scanCollectionsAndInformGui(list);
			break;
		}
		case OpenContainingFolder:
		{
			String absPath = (String)(g2ce.getData());
			try {
				playbackHandler.openContainingFolder(absPath);
			} catch (Exception ex) {
				Logger.logException(ex);
			}
			break;
		}
		}
	}
	
	/************************************************************/
	/** INITIALIZATION 											
	/************************************************************/
	
	private void initialize() {
		
		
		anywhereToGui = new ConcurrentLinkedQueue<RootEventA2G>();
		gcToPc = new ConcurrentLinkedQueue<RootEventG2C>();
		guiController = new GuiController(anywhereToGui, gcToPc);
		guiController.start();
		
		guiTranslator = new GuiTranslaterMovie();
		
		libraryTracker = new LibraryTracker();
		mediaInfoExtracter = new MediaInfoExtracter();
		mediaInfoFetcher = new RtMovieInfoFetcher();
		mediaInfoFetcherLocal = new LocalMovieInfoFetcher();
		
		initializePlatformDependentHandlers();
	}
	
	private void initializePlatformDependentHandlers(){
		OS os = Config.getInstance().getCurrentOS();
		
		switch(os) {
		case Windows:
		{
			playbackHandler = new PlaybackHandlerWindows();
			mediaFinder = new MediaFinderWindows();
			break;
		}
		case Linux:
		{
			playbackHandler = new PlaybackHandlerLinux();
			mediaFinder = new MediaFinderLinux();
			break;
		}
		case Mac:
			break;
		case Unknown:
			break;
		default:
			break;
			
		}
	}
	

}
