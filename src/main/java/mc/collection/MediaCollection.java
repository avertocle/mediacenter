package mc.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MediaCollection {

	private static int ctr = 0;
	private static final String DEFAULT_NAME = "Collection";
	
	private int id;
	private String name;
	private Set<Integer> folderSet;

	public MediaCollection() {
		super();
		this.id = ctr++;
		this.name = DEFAULT_NAME + String.valueOf(id);
		this.folderSet = new HashSet<>();
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean addFolder(int id) {
		return folderSet.add(id);
	}

	public boolean removeFolder(int id) {
		return folderSet.remove(id);
	}
	
	public List<Integer> getFolders(){
		return new ArrayList<Integer>(folderSet);
	}
	
}
