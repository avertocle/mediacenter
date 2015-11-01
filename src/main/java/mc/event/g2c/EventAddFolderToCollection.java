package mc.event.g2c;

import java.io.File;

public class EventAddFolderToCollection extends RootEventG2C {

	private String collectionName;
	private File file;
	
	public EventAddFolderToCollection(String collectionName, File file) {
		super(EventTypeG2C.AddFolderToCollection, null);
		this.collectionName = collectionName;
		this.file = file;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public File getFile() {
		return file;
	}
	
}
