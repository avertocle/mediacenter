package mc.model.translater;

import mc.event.a2g.RootEventA2G.EventTypeA2G;
import mc.model.media.Media;
import mc.model.movie.MovieInfo;

public class GuiTranslaterMovie implements GuiTranslator{

	@Override
	public Object getGuiDisplayObject(EventTypeA2G eventTypeA2G, Object modelResponse) {
		
		switch (eventTypeA2G) {
		case GuiReloadLibrary:
		{
			Media m = (Media)modelResponse;
			MovieInfo info = (MovieInfo)m.getMediaInfo();
			return (new Object[]{m.getName(), m.getId(), m.getAbsPath(), info.getYear(), info.getDirector()});
		}
		default:
			return null;
		}
		
	}

}
