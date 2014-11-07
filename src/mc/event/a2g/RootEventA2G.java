package mc.event.a2g;

public class RootEventA2G {

	public static enum EventTypeA2G {GuiStart, GuiReloadLibrary, GuiAddMedia, GuiStop, GuiRemoveMedia, Resp_GetDirList};
	
	private EventTypeA2G type;
	private Object data;
	
	public RootEventA2G(EventTypeA2G type, Object data) 
	{
		super();
		this.type = type;
		this.data = data;
	}

	public EventTypeA2G getType() {
		return type;
	}

	public Object getData() {
		return data;
	}
	
}
