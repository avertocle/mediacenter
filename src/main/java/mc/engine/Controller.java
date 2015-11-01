package mc.engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import mc.collection.MediaCollection;
import mc.collection.UserProfile;
import mc.config.Platform;
import mc.constants.GeneralConstants;
import mc.constants.GeneralConstants.MediaType;
import mc.event.a2g.RootEventA2G;
import mc.event.a2g.RootEventA2G.EventTypeA2G;
import mc.event.g2c.EventAddFolderToCollection;
import mc.event.g2c.RootEventG2C;
import mc.event.g2c.RootEventG2C.EventTypeG2C;
import mc.explorer.MediaFinder;
import mc.gui.GuiController;
import mc.model.LibraryTracker;
import mc.model.media.Media;
import mc.model.media.MediaInfo;
import mc.model.media.MediaInfoExtracter;
import mc.model.media.MediaInfoFetcher;
import mc.model.movie.LocalMovieInfoFetcher;
import mc.model.movie.RtMovieInfoFetcher;
import mc.model.translater.GuiTranslaterMovie;
import mc.model.translater.GuiTranslaterVideo;
import mc.model.translater.GuiTranslator;
import mc.model.video.LocalVideoInfoFetcher;
import mc.playback.PlaybackHandler;
import mc.utils.Logger;
import mc.utils.MiscUtils;

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
	
	private UserProfile userProfile;

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
		int primaryMcId = userProfile.getUserPrimaryCollection();
		MediaCollection mc = userProfile.getMediaCollection(primaryMcId);
		List<String> collectionList = new ArrayList<String>();
		if(mc != null){
			collectionList = userProfile.getAllFolderPaths(mc);
		}
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
			try { playbackHandler.playInDefaultSystemPlayer(absPath); }
			catch (Exception ex) {	Logger.logException(ex); }
			break;
		}
		case AddFolderToCollection:
		{
			EventAddFolderToCollection ev = (EventAddFolderToCollection) g2ce;
			String collectionName = ev.getCollectionName();
			File file = ev.getFile();
			MediaCollection mc = userProfile.getMediaCollection(collectionName);
			if(mc == null){
				Logger.logError("C :: Error while adding folder to collection :: No collection found with name : " + collectionName);
				break;
			}
			
			try {	userProfile.addFolderToCollection(mc.getId(), file); }
			catch (IOException e) { Logger.logException(e); }
			
			sendUserCollectionInfoToGui();
			break;
		}
		case ModelRequest_DirList:
		{
			sendUserCollectionInfoToGui();
			break;
		}
		case RescanCollection:
		{
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>)g2ce.getData();
			if(list != null){
				scanCollectionsAndInformGui(list);
			}
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
		case AddCollection:
			String collectionName = (String)(g2ce.getData());
			try { userProfile.addMediaCollection(collectionName); }
			catch (IOException e) { Logger.logException(e); }
			sendUserCollectionInfoToGui();
			break;
		}
	}
	
	
	private void sendUserCollectionInfoToGui() {
		List<MediaCollection> mcList = userProfile.getUserCollectionList();
		Map<String, List<String>> mapMC = new HashMap<>();
		for(MediaCollection mc : mcList) {
			mapMC.put(mc.getName(), userProfile.getAllFolderPaths(mc));
		}
		
		anywhereToGui.add(new RootEventA2G(EventTypeA2G.Resp_GetDirList, mapMC));
	}
	
	/************************************************************/
	/** INITIALIZATION 											
	/************************************************************/
	
	private void initialize() {
		
		userProfile = new UserProfile();
		try {	userProfile.initialize();	}
		catch (IOException e) { Logger.logException(e); }
		
		anywhereToGui = new ConcurrentLinkedQueue<RootEventA2G>();
		gcToPc = new ConcurrentLinkedQueue<RootEventG2C>();
		guiController = new GuiController(anywhereToGui, gcToPc);
		guiController.start();
		
		libraryTracker = new LibraryTracker();
		mediaInfoExtracter = new MediaInfoExtracter();
		
		initializeMediaDependentHandlers();
		initializePlatformDependentHandlers();
	}
	
	private void initializeMediaDependentHandlers() {
		MediaType mediaType = GeneralConstants.defaultMediaType;
		switch(mediaType){
			case Movie:
				guiTranslator = new GuiTranslaterMovie();
				mediaInfoFetcher = new RtMovieInfoFetcher();
				mediaInfoFetcherLocal = new LocalMovieInfoFetcher();
				break;
			case Video:
				guiTranslator = new GuiTranslaterVideo();
				mediaInfoFetcher = null;
				mediaInfoFetcherLocal = new LocalVideoInfoFetcher();
				break;
			default:
				break;
		}
	}
	
	private void initializePlatformDependentHandlers(){
		Platform platform = Platform.getInstance();
		playbackHandler = MiscUtils.instantiate(platform.getClassPlaybackHandler());
		mediaFinder = MiscUtils.instantiate(platform.getClassMediaFinder());
	}
	

}
