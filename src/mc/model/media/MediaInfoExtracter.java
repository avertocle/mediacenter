package mc.model.media;

import java.io.File;

public class MediaInfoExtracter {

	public Media getMediaObject(File file) {
		
		if(file == null){
			return null;
		}
		Media video = (new Media(file.getAbsolutePath())).setFilename(file.getName()).setName(file.getName()).setFoldername(file.getParent());
		return video;
	}

}
