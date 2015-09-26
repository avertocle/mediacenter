package mc.model.movie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import mc.constants.GeneralConstants;
import mc.model.media.MediaInfoFetcher;
import mc.utils.Logger;
import mc.utils.MiscUtils;

public class LocalMovieInfoFetcher implements MediaInfoFetcher{

	private String[] stopwords = { "480p", "720p", "1080p", "x264", "brrip", "bluray", "m-hd", "dvdscr", "dvdrip" };
	private Set<String> setStopwords;
	
	public LocalMovieInfoFetcher() {
		setStopwords = new HashSet<String>(Arrays.asList(stopwords));
		setStopwords.addAll(Arrays.asList(GeneralConstants.extensions));
	}
	
	@Override
	public MovieInfo fetchInfo(String movieName) {
		MovieInfo movieInfo = new MovieInfo();
		
		movieInfo.setName(getMovieName(movieName));
		
		int year = getMovieYear(movieName);
		if(year > 0){
			movieInfo.setYear(String.valueOf(year));
		}
		
		return movieInfo;
	}
	
	private String[] getTokens(String raw){
		String sp[] = StringUtils.split(raw, " .()[]{}_");
		return sp;
	}
	
	private String getMovieName(String raw){
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
	
	private int getMovieYear(String raw){
		String sp[] = getTokens(raw);
		for(String s : sp){
			if(MiscUtils.isYear(s)){
				return Integer.parseInt(s);
			}
		}
		return 0;
	}
	
	private boolean isStopword(String s){
		return (setStopwords.contains(s.toLowerCase()));
	}

}

