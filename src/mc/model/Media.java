package mc.model;

public abstract class Media {
	
	private static int ctr = 0;
	
	protected int id;
	protected String name;
	protected String path;
	protected String filename;
	protected String foldername;
	protected String absPath;
	
	public Media(String name, String path, String filename,
			String foldername, String absPath) {
		super();
		this.id = ctr++;
		this.name = name;
		this.path = path;
		this.filename = filename;
		this.foldername = foldername;
		this.absPath = absPath;
	}
	
}
