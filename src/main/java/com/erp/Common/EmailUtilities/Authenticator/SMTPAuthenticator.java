package com.erp.Common.EmailUtilities.Authenticator;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator {

	String username = "";
	String password = "";

	public SMTPAuthenticator(String uName, String pass) {
		username = uName;
		password = pass;
	}

	public PasswordAuthentication getPasswordAuthentication() {

		return new PasswordAuthentication(username, password);
	}
}
