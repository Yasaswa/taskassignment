package com.erp.Common.EmailUtilities.Model;

import java.util.ArrayList;

public class EmailModel {

	private String mailSubject;
	private String mailfrom;
	private ArrayList<String> mailto;
	private ArrayList<String> bcc;
	private ArrayList<String> cc;
	private ArrayList<String> mailAttachmentFilePaths;
	private String mailBodyMsg;

	public EmailModel() {
		super();
	}

	public EmailModel(String mailSubject, String mailfrom, ArrayList<String> mailto, ArrayList<String> bcc,
	                  ArrayList<String> cc, ArrayList<String> mailAttachmentFilePaths, String mailBodyMsg) {
		super();
		this.mailSubject = mailSubject;
		this.mailfrom = mailfrom;
		this.mailto = mailto;
		this.bcc = bcc;
		this.cc = cc;
		this.mailAttachmentFilePaths = mailAttachmentFilePaths;
		this.mailBodyMsg = mailBodyMsg;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailfrom() {
		return mailfrom;
	}

	public void setMailfrom(String mailfrom) {
		this.mailfrom = mailfrom;
	}

	public ArrayList<String> getMailto() {
		return mailto;
	}

	public void setMailto(ArrayList<String> mailto) {
		this.mailto = mailto;
	}

	public ArrayList<String> getBcc() {
		return bcc;
	}

	public void setBcc(ArrayList<String> bcc) {
		this.bcc = bcc;
	}

	public ArrayList<String> getCc() {
		return cc;
	}

	public void setCc(ArrayList<String> cc) {
		this.cc = cc;
	}

	public ArrayList<String> getMailAttachmentFilePaths() {
		return mailAttachmentFilePaths;
	}

	public void setMailAttachmentFilePaths(ArrayList<String> mailAttachmentFilePaths) {
		this.mailAttachmentFilePaths = mailAttachmentFilePaths;
	}

	public String getMailBodyMsg() {
		return mailBodyMsg;
	}

	public void setMailBodyMsg(String mailBodyMsg) {
		this.mailBodyMsg = mailBodyMsg;
	}

}
