package mc.auth;

public interface AuthenticationHandler {
	
	public boolean authenticate(String username, String password);
}
