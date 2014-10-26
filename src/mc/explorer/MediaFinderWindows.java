package mc.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class MediaFinderWindows implements MediaFinder {

	private static String[] extensions = {"mov", "mkv", "mp4", "mpg", "wmv", "avi", "flv", "mpeg", "m4v"};
	
	@Override
	public List<File> findAllMediaInDir(String absPath) {
		
		List<File> fileList = null;
		File file = new File(absPath);
		if(file.exists() && file.isDirectory()){
			fileList = new ArrayList<File>(FileUtils.listFiles(file, extensions, true));
		}
		return fileList;
	}
	
}
