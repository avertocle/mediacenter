package mc.gui;

import javax.swing.JOptionPane;

import mc.auth.WindowsAuthenticationHandler;

public class LoginHandler {
	
	public static boolean doLogin(String username){
		
		String pass = JOptionPane.showInputDialog(null, "Enter Password for " + username, "Login", JOptionPane.QUESTION_MESSAGE);
		boolean authResult = WindowsAuthenticationHandler.authWindowsUser(username, "", pass);
		if(!authResult){
			JOptionPane.showMessageDialog(null, "Login Failure", "Login", JOptionPane.ERROR_MESSAGE);
		}
		return authResult;
	}

}
