package mc.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class MediaFinderWindows implements MediaFinder {

	@Override
	public List<File> findAllMediaInDir(String absPath) {
		
		List<File> fileList = null;
		File file = new File(absPath);
		if(file.exists() && file.isDirectory()){
			fileList = new ArrayList<File>(FileUtils.listFiles(file, null, true));
		}
		return fileList;
	}
	
}
