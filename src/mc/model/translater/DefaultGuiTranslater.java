package mc.model.translater;

import mc.event.a2g.RootEventA2G.EventTypeA2G;
import mc.model.Media;


public class DefaultGuiTranslater implements GuiTranslator{

	@Override
	public Object getGuiDisplayObject(EventTypeA2G eventTypeA2G, Object modelResponse) {
		
		switch (eventTypeA2G) {
		case GuiReloadLibrary:
		{
			Media m = (Media)modelResponse;
			return (new Object[]{m.getName(), m.getId(), m.getAbsPath()});
		}
		default:
			return null;
		}
		
	}

}
