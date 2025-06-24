package com.erp.Common.EmailUtilities.EmailServices;

import com.erp.Common.EmailUtilities.Model.EmailModel;
import com.erp.Common.EmailUtilities.PropertyReader.PropertiesReader;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class EmailSender {

	public Boolean sendEmails(EmailModel emailModel, String host, String smtpUserName, String smtpPassword,
	                          Integer port, Boolean setDebugger) throws AddressException, MessagingException {
		Boolean emailIsSent = true;
		// Sets the properties form mailing.
		Properties props = System.getProperties();
		props.put("mail.smtp.user", smtpUserName);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
//		props.put("mail.smtp.ssl.enable", "false");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		// creates a new javax.mail.Session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpUserName, smtpPassword);
			}
		};
		Session session = Session.getInstance(props, auth);

		// mailing debugger setting;
		session.setDebug(setDebugger);

		// Creating the MimeMessage using mail session
		MimeMessage message = new MimeMessage(session);

		// Set the email headers for From;
		message.setFrom(new InternetAddress(emailModel.getMailfrom()));

		// Set the email headers for To, BCC, CC;
		if (emailModel.getMailto() != null && !emailModel.getMailto().isEmpty()) {
			ArrayList<String> mailToHeaders = emailModel.getMailto();
			for (int mailToHeader = 0; mailToHeader < mailToHeaders.size(); mailToHeader++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailToHeaders.get(mailToHeader)));
			}
		}
		if (emailModel.getBcc() != null && !emailModel.getBcc().isEmpty()) {
			ArrayList<String> mailBCCHeaders = emailModel.getBcc();
			for (int mailBCCHeader = 0; mailBCCHeader < mailBCCHeaders.size(); mailBCCHeader++) {
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(mailBCCHeaders.get(mailBCCHeader)));
			}
		}
		if (emailModel.getCc() != null && !emailModel.getCc().isEmpty()) {
			ArrayList<String> mailCCHeaders = emailModel.getCc();
			for (int mailCCHeader = 0; mailCCHeader < mailCCHeaders.size(); mailCCHeader++) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(mailCCHeaders.get(mailCCHeader)));
			}
		}

		// set mail subject.
		message.setSubject(emailModel.getMailSubject());

		// Now set the date to actual message
		message.setSentDate(new Date());

		// Creating the Multipart. Creating EmailBody
		Multipart multipart = new MimeMultipart("mixed");

		// 1. HTML Body Part.
		if (emailModel.getMailBodyMsg() != null && !"".equals(emailModel.getMailBodyMsg())) {
			BodyPart htmlBodyPart = new MimeBodyPart();
			htmlBodyPart.setContent(emailModel.getMailBodyMsg(), "text/html");
			multipart.addBodyPart(htmlBodyPart);
		}

		// 2. Files attachments Body Part.
		if (emailModel.getMailAttachmentFilePaths() != null && !emailModel.getMailAttachmentFilePaths().isEmpty()) {
			MimeBodyPart attachmentsBodyPart = new MimeBodyPart();
			ArrayList<String> attachmentFilePaths = emailModel.getMailAttachmentFilePaths();
			for (int filePath = 0; filePath < attachmentFilePaths.size(); filePath++) {
				attachmentsBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachmentFilePaths.get(filePath));
				attachmentsBodyPart.setDataHandler(new DataHandler(source));
				attachmentsBodyPart.setFileName(attachmentFilePaths.get(filePath));
				multipart.addBodyPart(attachmentsBodyPart);
			}
		}

		// set the message content
		message.setContent(multipart);

		// Transport the mail.
		Transport transport = session.getTransport("smtp"); // MAIL_SERVER
		transport.connect(host, port, smtpUserName, smtpPassword); // SMTP_HOST_NAME, SMTP_HOST_PORT, USER_NAME,
		// PASSWORD
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		System.out.println("Emails delivered successfully.");
		return true;
	}

	public String generateEmailTemplate(String emailTemplateName, int company_id, String[] templateParams)
			throws ClassNotFoundException, IOException, SQLException {
		String msgBody = "";

		PropertiesReader propertiesReader = new PropertiesReader();
		Map<String, Object> foundedTemplate = propertiesReader.getEmailTemplateByTemplateName(
//				company_id,
				emailTemplateName);

		// get email templates description from database
		msgBody = (String) foundedTemplate.get("communications_templates_description");

		String modifiedMessage = msgBody.replace("\\n", "<br><br>");
		modifiedMessage = replaceParameters(modifiedMessage, templateParams);

		String emailTemplate = "<!DOCTYPE html><html>" + "<head><title> " + templateParams[0] + " </title></head>"
				+ "<body style='font-family: Arial, sans-serif; margin: 0; padding: 0;'>"
				+ "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600'>" + "<tr>"
				+ "<td align='center' bgcolor='#007bff' style='padding: 5px 0;'> <h1 style='color: #ffffff;'> "
				+ templateParams[0] + " </h1></td>" + "</tr>" + "<tr>"
				+ "<td bgcolor='#f4f4f4' style='padding: 40px; text-align:justify; font-family: Arial, sans-serif; font-size: 14px'> "
				+ modifiedMessage + " </td>" + "</tr>" + "</table>" + "</body>" + "</html>";

		return emailTemplate;

	}

	public String replaceParameters(String emailTemplate, String[] templateParams) {
		for (int param = 1; param < templateParams.length; param++) {
			String placeholder = "$Param" + param + "$";
			emailTemplate = emailTemplate.replaceAll(Pattern.quote(placeholder),
					"<Strong>" + templateParams[param] + "</Strong>");
		}
		return emailTemplate;
	}

}
