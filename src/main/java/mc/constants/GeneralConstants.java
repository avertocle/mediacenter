package mc.constants;

public class GeneralConstants {
	
	public static final String Version = "1.1";
	public static final String VersionInfo = "12.03.2015";
	
	public static String frameHeading = "Media Center " +"[v" + Version + "@" + VersionInfo + "]";
	
	
	public static String rootDir = "";
	public static final String rootDirWindows = "C://MediaCenter//";
	public static final String rootDirLinux = "MediaCenter/";
	
	public static final boolean fetchMovieInfo = false;
	
	public static String[] extensions = {"mov", "mkv", "mp4", "mpg", "wmv", "avi", "flv", "mpeg", "m4v"};
	
	public static enum MediaType { Movie, Video, TV};
	
	public static MediaType defaultMediaType = MediaType.Movie;

}
