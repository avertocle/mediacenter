package mc.model.video;

import mc.model.media.MediaInfo;

public class VideoInfo extends MediaInfo{

	protected String name;
	protected String quality;
	protected String type;
	
	public VideoInfo() {
		super();
		this.name = "";
		this.quality = "";
		this.type = "";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}
	
}
