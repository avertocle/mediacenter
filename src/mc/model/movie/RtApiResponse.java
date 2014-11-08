package mc.model.movie;

import java.util.List;

public class RtApiResponse {
	
	int total;
	List<MovieDetails> movies;

	class MovieDetails{
		String title;
	}
	
	public String getMovieName(){
		if(movies != null && !movies.isEmpty()){
			return movies.get(0).title;
		}
		else {
			return "--";
		}
	}
	
}
