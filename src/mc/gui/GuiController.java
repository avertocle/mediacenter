package mc.gui;

import java.util.concurrent.ConcurrentLinkedQueue;

import mc.event.a2g.RootEventA2G;
import mc.event.a2g.RootEventA2G.EventTypeA2G;
import mc.event.g2c.RootEventG2C;
import mc.utils.Logger;

public class GuiController extends Thread {

	private ConcurrentLinkedQueue<RootEventA2G> anywhereToGui;
	ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	private boolean runForever;

	private MainPageGui mainPageGUI;
	private int controllerSleepTime = 50;

	public GuiController(ConcurrentLinkedQueue<RootEventA2G> anywhereToGui, ConcurrentLinkedQueue<RootEventG2C> gcToPc) 
	{
		super();
		this.anywhereToGui = anywhereToGui;
		this.gcToPc = gcToPc;
		mainPageGUI = new MainPageGui(gcToPc);
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
			mainPageGUI.stop();
			break;
		}
		}
	}

	
	
}
