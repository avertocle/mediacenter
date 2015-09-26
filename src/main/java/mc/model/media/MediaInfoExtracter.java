package mc.model.media;

import java.io.File;
import java.text.DecimalFormat;

public class MediaInfoExtracter {

	public Media getMediaObject(File file) {
		
		if(file == null){
			return null;
		}
		Media media = (new Media(file.getAbsolutePath())).setFilename(file.getName()).setName(file.getName()).setFoldername(file.getParent());
		media.setSize(getMediaSizeAsString(file.length()));
		return media;
	}

	private String getMediaSizeAsString(long filesize){
		double fs = (double)filesize;
		double fs_mb = fs/(1024*1024*1024);
		DecimalFormat df = new DecimalFormat("0.000"); 
		return df.format(fs_mb) + " GB";
	}
}
