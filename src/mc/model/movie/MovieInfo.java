package mc.model.movie;

import mc.model.media.MediaInfo;

public class MovieInfo extends MediaInfo{

	protected String cleanedName;
	protected String year;
	protected String imdbId;
	protected String actors;
	protected String director;
	protected String rating;
	
	public MovieInfo() {
		super();
	}

	public String getCleanedName() {
		return cleanedName;
	}

	public void setCleanedName(String cleanedName) {
		this.cleanedName = cleanedName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	

}
