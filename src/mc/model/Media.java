package mc.model;

public abstract class Media {
	
	private static int ctr = 0;
	
	protected int id;
	protected String name;
	protected String path;
	protected String filename;
	
	public Media(int id, String name, String path, String filename) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.filename = filename;
	}
	
	
	
}
