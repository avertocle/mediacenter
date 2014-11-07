package mc.gui;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import mc.event.a2g.RootEventA2G;
import mc.event.a2g.RootEventA2G.EventTypeA2G;
import mc.event.g2c.RootEventG2C;
import mc.event.g2g.RootEventG2G;
import mc.event.g2g.RootEventG2G.EventTypeG2G;
import mc.utils.Logger;

public class GuiController extends Thread {

	private ConcurrentLinkedQueue<RootEventG2G> guiInternalQueue;
	private ConcurrentLinkedQueue<RootEventA2G> anywhereToGui;
	ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	private boolean runForever;

	private MainPageGui mainPageGUI;
	private int controllerSleepTime = 50;

	public GuiController(ConcurrentLinkedQueue<RootEventA2G> anywhereToGui, ConcurrentLinkedQueue<RootEventG2C> gcToPc) 
	{
		super();
		guiInternalQueue = new ConcurrentLinkedQueue<RootEventG2G>();
		this.anywhereToGui = anywhereToGui;
		this.gcToPc = gcToPc;
		mainPageGUI = new MainPageGui(gcToPc, guiInternalQueue);
		runForever = true;
	}
	
	/*********** Exposed Methods *******************************/
	
	public void run()
	{
		Logger.logMajorEvent("Gui Controller Started.");
		
		mainPageGUI.start();
		
		/* Wait till GUI if fully initialized */
		while(!mainPageGUI.isGuiInitialised)
		{
			try {	sleep(100);	}
			catch (InterruptedException e) 
			{	Logger.logException(e);		}
		}

		boolean a2geEmpty = false;
		RootEventA2G a2ge = null;
		
		while(!a2geEmpty || runForever)
		{
			clearInternalGuievents();
			
			a2ge = anywhereToGui.poll();
			if(a2ge == null)
				a2geEmpty = true;
			else
				handleA2GEvent(a2ge);
			
			if(a2geEmpty)
			{
				try {	sleep(controllerSleepTime);	} 
				catch (InterruptedException e) { Logger.logException(e);	}
			}
		}
	}

	private void clearInternalGuievents() {
		RootEventG2G g2ge;
		while(!guiInternalQueue.isEmpty()){
			g2ge = guiInternalQueue.poll();
			if(g2ge == null){
				break;
			}
			handleInternalGuiEvent(g2ge);
		}
	}

	private void handleInternalGuiEvent(RootEventG2G g2ge) {
		EventTypeG2G eventTypeG2G = g2ge.getType();
		switch (eventTypeG2G) {
		case CloseConsole_CollectionMgmt:
		{
			mainPageGUI.closeConsole_CollectionMgmt();
			break;
		}
		}
	}

	public void stopServer()
	{
		runForever = false;
	}

	/*********** Internal Methods *******************************/
	
	private void handleA2GEvent(RootEventA2G a2ge) 
	{
		EventTypeA2G etype = a2ge.getType();
		switch(etype)
		{
		case GuiStart:
		{
			mainPageGUI.start();
			break;
		}
		case GuiReloadLibrary:
		{	
			Object data = a2ge.getData();
			mainPageGUI.reloadWholeLibrary(data);
			break;
		}
		case GuiAddMedia:
		{
			Object data = a2ge.getData();
			mainPageGUI.addMedia(data);
			break;
		}
		case GuiRemoveMedia:
		{
			Object data = a2ge.getData();
			mainPageGUI.removeMedia(data);
			break;
		}
		case GuiStop:
		{	
			anywhereToGui.clear();
			mainPageGUI.terminate();
			break;
		}
		case Resp_GetDirList:
		{
			@SuppressWarnings("unchecked")
			List<String> dirList = (List<String>)(a2ge.getData());
			mainPageGUI.showConsole_manageLibrary(dirList);
			break;
		}
		default:
			break;
		}
	}

	
	
}
