package mc.mediautils;

import java.io.File;

import mc.model.Media;

public interface MediaInfoExtracter {
	
	public Media getMediaObject(File file);

}
