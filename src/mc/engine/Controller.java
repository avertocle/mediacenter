package mc.engine;

import java.util.concurrent.ConcurrentLinkedQueue;

import mc.event.a2g.RootEventA2G;
import mc.event.g2c.RootEventG2C;
import mc.gui.GuiController;
import mc.utils.Logger;

public class Controller extends Thread {

	private ConcurrentLinkedQueue<RootEventA2G> anywhereToGui;
	private ConcurrentLinkedQueue<RootEventG2C> gcToPc;
	private boolean runForever;

	private GuiController guiController;
	private int controllerSleepTime = 50;

	public Controller() 
	{
		super();
		runForever = true;
	}
	
	private void initialize() {

		anywhereToGui = new ConcurrentLinkedQueue<RootEventA2G>();
		guiController = new GuiController(anywhereToGui);
		guiController.start();
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
		
		boolean a2geEmpty = false;
		RootEventG2C g2ce = null;
		
		while(!a2geEmpty || runForever)
		{
			g2ce = gcToPc.poll();
			if(g2ce == null)
				a2geEmpty = true;
			else
				handleG2CEvent(g2ce);
			
			if(a2geEmpty)
			{
				try {	sleep(controllerSleepTime);	} 
				catch (InterruptedException e) { Logger.logException(e);	}
			}
		}
	}

	private void handleG2CEvent(RootEventG2C g2ce) {
		
	}

}
