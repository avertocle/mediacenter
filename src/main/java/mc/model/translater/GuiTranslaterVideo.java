package mc.model.translater;

import mc.event.a2g.RootEventA2G.EventTypeA2G;
import mc.model.media.Media;
import mc.model.video.VideoInfo;

public class GuiTranslaterVideo implements GuiTranslator{

	@Override
	public Object getGuiDisplayObject(EventTypeA2G eventTypeA2G, Object modelResponse) {
		
		switch (eventTypeA2G) {
		case GuiReloadLibrary:
		{
			Media m = (Media)modelResponse;
			VideoInfo info = (VideoInfo)m.getMediaInfo();
			return (new Object[]{m.getId(), m.getAbsPath(), m.getName(), info.getName(), info.getQuality(), info.getType(), m.getSize()});
		}
		default:
			return null;
		}
		
	}

}
