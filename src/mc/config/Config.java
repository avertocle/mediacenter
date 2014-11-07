package mc.config;

public class Config {
	
	private static Config instance;

	private Config(){
		super();
	}
	
	public static Config getInstance(){
		if(instance == null){
			instance = new Config();
		}
		return instance;
	}
	
	/*********************************************************************/
	
	public enum OS {Windows, Linux, Mac, Unknown};
	
	private OS currentOS;

	public OS getCurrentOS() {
		return currentOS;
	}

	public void setCurrentOS(OS currentOS) {
		this.currentOS = currentOS;
	}
	
	
}
