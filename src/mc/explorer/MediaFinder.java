package mc.explorer;

import java.io.File;
import java.util.List;

public interface MediaFinder {
	
	 public List<File> findAllMediaInDir(String absPath);

}
