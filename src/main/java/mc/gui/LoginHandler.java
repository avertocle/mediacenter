package mc.gui;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import mc.auth.AuthenticationHandler;

public class LoginHandler {

	private AuthenticationHandler authenticationHandler;

	public LoginHandler(AuthenticationHandler authenticationHandler) {
		this.authenticationHandler = authenticationHandler;
	}

	public boolean doLogin(String username) {

		String msg = "Enter Password for " + username;
		JPasswordField pf = new JPasswordField();
		int choice = JOptionPane.showConfirmDialog(null, pf, msg, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		boolean authResult = false;
		if (choice == JOptionPane.OK_OPTION) {
			String pass = new String(pf.getPassword());
			authResult = authenticationHandler.authenticate(username, pass);
			if (!authResult) {
				JOptionPane.showMessageDialog(null, "Login Failure", "Login", JOptionPane.ERROR_MESSAGE);
			}
		}

		return authResult;
	}

}
