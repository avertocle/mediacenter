package mc.model.translater;

import mc.event.a2g.RootEventA2G.EventTypeA2G;

public interface GuiTranslator {
	
	public Object getGuiDisplayObject(EventTypeA2G eventTypeA2G, Object modelResponse);
	
}
