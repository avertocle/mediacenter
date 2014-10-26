package mc.model;

public abstract class Media {
	
	private static int ctr = 0;
	
	protected int id;
	protected String name;
	protected String filename;
	protected String foldername;
	protected String absPath;
	
	public Media(String absPath) {
		super();
		this.id = ctr++;
		this.absPath = absPath;
	}
	
	/***************************************************************************
	/* Getters
	/***************************************************************************/
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFilename() {
		return filename;
	}

	public String getFoldername() {
		return foldername;
	}

	public String getAbsPath() {
		return absPath;
	}

	/***************************************************************************
	/* Setters
	/***************************************************************************/
	
	public Media setName(String name) {
		this.name = name;
		return this;
	}

	public Media setFilename(String filename) {
		this.filename = filename;
		return this;
	}

	public Media setFoldername(String foldername) {
		this.foldername = foldername;
		return this;
	}

	
}
