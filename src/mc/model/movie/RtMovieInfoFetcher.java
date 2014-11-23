package mc.model.movie;

import com.google.gson.Gson;

import mc.model.media.MediaInfoFetcher;
import mc.utils.HttpLibrary;

public class RtMovieInfoFetcher implements MediaInfoFetcher{

	private final String BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json";

	private final String API_KEY_VALUE = "jcbx3fbduyudqg9k36tz24ww";
	private final String API_KEY_PARAM = "apikey";
	
	private final String Page_Limit_Param = "page_limit";
	private final String Page_Limit_Value= "1";
			
	@Override
	public MovieInfo fetchInfo(String movieName) {
		MovieInfo movieInfo = new MovieInfo();
		String url = "";
		url += (BASE_URL);
		url += (getFilterString(url, API_KEY_PARAM, API_KEY_VALUE));
		url += (getFilterString(url, "q", movieName));
		url += (getFilterString(url, Page_Limit_Param, Page_Limit_Value));
		
		String response = "";
		try {
			response = HttpLibrary.sendGet(url.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(response != null && !response.isEmpty()){
			RtApiResponse rtar = new Gson().fromJson(response, RtApiResponse.class);
			movieInfo.setName(rtar.getMovieName());
		}
		
		return movieInfo;
	}
	
	private String getFilterString(String url, String key, String value){
		String delim = ((url.contains("?")) ? "&" : "?");
		return (delim + key + "=" + value);
	}
}

