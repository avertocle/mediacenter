package mc.mediautils;

import java.io.File;

import mc.model.Media;
import mc.model.Video;

public class VideoInfoExtracter implements MediaInfoExtracter{

	@Override
	public Media getMediaObject(File file) {
		
		if(file == null){
			return null;
		}
		Media video = (new Video(file.getAbsolutePath())).setFilename(file.getName()).setName(file.getName()).setFoldername(file.getParent());
		return video;
	}

}
