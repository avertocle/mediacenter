package mc.auth;


public class MacAuthenticationHandler implements AuthenticationHandler {

	@Override
	public boolean authenticate(String username, String password) {
		return true;
	}

}
