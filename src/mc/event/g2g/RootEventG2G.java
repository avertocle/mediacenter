package mc.event.g2g;

public class RootEventG2G {

	public static enum EventTypeG2G {CloseConsole_CollectionMgmt};
	
	private EventTypeG2G type;
	private Object data;
	
	public RootEventG2G(EventTypeG2G type, Object data) 
	{
		super();
		this.type = type;
		this.data = data;
	}

	public EventTypeG2G getType() {
		return type;
	}

	public Object getData() {
		return data;
	}
	
}
