package mc.model.video;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import mc.constants.GeneralConstants;
import mc.model.media.MediaInfoFetcher;
import mc.utils.Logger;
import mc.utils.MiscUtils;

public class LocalVideoInfoFetcher implements MediaInfoFetcher{

	private String[] stopwords = { "480p", "720p", "1080p", "x264", "brrip", "bluray", "m-hd", "dvdscr", "dvdrip" };
	private Set<String> setStopwords;
	
	private String[] qualityMarkers = {"480p","540p", "720p", "1080p"};
	private Set<String> setQualityMarkers;
	
	private String[] typeMarkers = {"T - ", "TB - ",  "L - ", "x_", "l_", "L_", "lx_"};
	private Set<String> setTypeMarkers;
	
	public LocalVideoInfoFetcher() {
		setStopwords = new HashSet<String>(Arrays.asList(stopwords));
		setStopwords.addAll(Arrays.asList(GeneralConstants.extensions));
		setQualityMarkers = new HashSet<String>(Arrays.asList(qualityMarkers));
		setTypeMarkers = new HashSet<String>(Arrays.asList(typeMarkers));
	}
	
	@Override
	public VideoInfo fetchInfo(String movieName) {
		VideoInfo videoInfo = new VideoInfo();

		videoInfo.setName(getVideoName(movieName));
		videoInfo.setQuality(getVideoQuality(movieName));
		videoInfo.setType(getVideoType(movieName));

		return videoInfo;
	}
	
	private String[] getTokens(String raw){
		String sp[] = StringUtils.split(raw, " .()[]{}_");
		return sp;
	}
	
	private String getVideoName(String raw){
		String sp[] = getTokens(raw);
		String name = "";
		int ctr=0;
		for(String s : sp){
			if(MiscUtils.isYear(s) || isStopword(s)){
				if(ctr > 0){
					break;
				}
			}
			name += (" " + s);
			ctr++;
		}
		
		name = name.trim();
		
		if(name.isEmpty()){
			Logger.logError("Unhandled movie name : " + raw);
		}
		
		int endIndex = name.length()-1;
		while(!CharUtils.isAsciiAlphanumeric(name.charAt(endIndex))){
			endIndex--;
		}
		name = name.substring(0, endIndex+1);
		return name;
	}
	
	private String getVideoQuality(String raw){
		String sp[] = getTokens(raw);
		for(String s : sp){
			if(setQualityMarkers.contains(s)){
				return s;
			}
		}
		return "";
	}
	
	private String getVideoType(String raw){
		for(String tm : setTypeMarkers){
			if(raw.startsWith(tm)){
				return StringUtils.split(tm, " _-")[0];
			}
		}
		return "";
	}
	
	private boolean isStopword(String s){
		return (setStopwords.contains(s.toLowerCase()));
	}

}

