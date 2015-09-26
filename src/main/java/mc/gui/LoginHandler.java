package mc.gui;

import javax.swing.JOptionPane;

import mc.auth.AuthenticationHandler;

public class LoginHandler {
	
	private AuthenticationHandler authenticationHandler;
	
	public LoginHandler(AuthenticationHandler authenticationHandler) {
		this.authenticationHandler = authenticationHandler;
	}
	
	public boolean doLogin(String username){
		
		String pass = JOptionPane.showInputDialog(null, "Enter Password for " + username, "Login", JOptionPane.QUESTION_MESSAGE);
		boolean authResult = authenticationHandler.authenticate(username, pass);
		if(!authResult){
			JOptionPane.showMessageDialog(null, "Login Failure", "Login", JOptionPane.ERROR_MESSAGE);
		}
		return authResult;
	}

}
