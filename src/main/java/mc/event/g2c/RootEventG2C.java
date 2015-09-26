package mc.event.g2c;

public class RootEventG2C {

	public static enum EventTypeG2C {PlayMedia, AddDirToLib, ModelRequest_DirList, RescanCollection, OpenContainingFolder};
	
	private EventTypeG2C type;
	private Object data;
	
	public RootEventG2C(EventTypeG2C type, Object data) 
	{
		super();
		this.type = type;
		this.data = data;
	}

	public EventTypeG2C getType() {
		return type;
	}

	public Object getData() {
		return data;
	}
	
}
