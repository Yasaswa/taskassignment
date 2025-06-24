package com.erp.Common.EmailUtilities.Controller;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsService;
import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.EmailServices.EmailSender;
import com.erp.Common.EmailUtilities.Model.EmailModel;
import com.erp.Common.EmailUtilities.PropertyReader.PropertiesReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmailController {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	CAmApplicationErrorLogsService cAmApplicationErrorLogsService;

	@PostMapping("/send/{company_id}")
	public Map<String, Object> emailSender(@PathVariable int company_id, @RequestParam("EmailData") JSONObject jsonObject) {
		Map<String, Object> response = new HashMap<>();

		if (company_id > 0) {
			PropertiesReader propertiesReader = new PropertiesReader();
			try {
				// Get the email settings.
				Map<String, Object> emailDetails = propertiesReader.getEmailDetailsByCompanyId(company_id);

				if (emailDetails != null) {
					// set the company email properties. 
					String fromEmail = (String) emailDetails.get("from_email_id");
					String host = (String) emailDetails.get("smtp_host_name");
					String smtpUserName = (String) emailDetails.get("from_email_username");
					String smtpPassword = (String) emailDetails.get("from_email_password");
					Integer port = (Integer) emailDetails.get("smtp_port");


					String emailType = jsonObject.getString("EmailType");
					String subject = jsonObject.getString("subject");
					JSONArray arrayOfmailTo = (JSONArray) jsonObject.get("toMailData");
					JSONArray arrayOfbcc = (JSONArray) jsonObject.get("bccData");
					JSONArray arrayOfcc = (JSONArray) jsonObject.get("ccData");
					JSONArray mailAttachmentFilePathsarray = (JSONArray) jsonObject.get("mailAttachmentFilePaths");
					JSONArray templateprameters = (JSONArray) jsonObject.get("parameters");
					String alias = jsonObject.getString("alias");

					// add the mailTo headers in email.
					ArrayList<String> mailTo = jsonArrayToArrayList(arrayOfmailTo);
					ArrayList<String> bcc = jsonArrayToArrayList(arrayOfbcc);
					ArrayList<String> cc = jsonArrayToArrayList(arrayOfcc);
					ArrayList<String> mailAttachmentFilePath = jsonArrayToArrayList(mailAttachmentFilePathsarray);
					ArrayList<String> templatesprameters = jsonArrayToArrayList(templateprameters);

					String[] templateParams = templatesprameters.toArray(new String[0]);
					System.out.println(Arrays.toString(templateParams));

//					// add the attachment file paths in email.
//					ArrayList<String> mailAttachmentFilePaths = new ArrayList<>();

//					String seperator = File.separator; // for file seperator
//					String fileLocation = System.getProperty("user.dir").toString() + seperator + "src" + seperator + "main" + seperator + "java" + seperator + "com" + seperator + "erp" + seperator + "Company" + seperator + "Companies" + seperator + "Model" + seperator;
//					mailAttachmentFilePaths.add(fileLocation + "CCompanyModel.java");
//					mailAttachmentFilePaths.add(fileLocation + "CCompanyRptModel.java");
//					mailAttachmentFilePaths.add(fileLocation + "CCompanyViewModel.java");

					// set the data in in emailModel.
					EmailModel email = new EmailModel();
//					email.setMailfrom("store@pashupaticotspin.com <admin@proerpsolution.com>");
					email.setMailfrom(alias + " <"+fromEmail+">");
					email.setMailto(mailTo);
					email.setBcc(bcc);
					email.setCc(cc);
					email.setMailSubject(subject);

					if (mailAttachmentFilePath.isEmpty()) {
						response.put("No attachments found.", false);
					} else {
						email.setMailAttachmentFilePaths(mailAttachmentFilePath);
					}

					Boolean setDebugger = true;

					EmailSender emailSender = new EmailSender();

					// Set the placeholder parameters for the email template.
					String emailTemplate = emailSender.generateEmailTemplate(emailType, company_id, templateParams);

//					//replace parameters for the email template.
//					String replaceParams = emailSender.replaceParameters(emailTemplate,templateParams);
//					System.out.println(replaceParams);

					// Set the emailBodyMsg in emailModel.
					email.setMailBodyMsg(emailTemplate);

					// Sending the email.
					Boolean emailSendStatus = emailSender.sendEmails(email, host, smtpUserName, smtpPassword, port, setDebugger);
					response.put("Status", emailSendStatus);

					response.put("success", "1");
					response.put("error", "");
					response.put("message", "Email Send succesfully...!");
				}

			} catch (ClassNotFoundException | IOException | SQLException | MessagingException e) {
				response.put("Status", false);
				response.put("success", "0");
				response.put("error", e.getMessage());
				response.put("message", "Email not sent...!");
				cAmApplicationErrorLogsService.addErrorLog(1, "api", "/api/email/send", e.hashCode(), e.getMessage());
			System.out.println("error" + e.getMessage());
			}
		}

		return response;
	}

	private ArrayList<String> jsonArrayToArrayList(JSONArray jsonArray) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(jsonArray.toString(), ArrayList.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}

// How to use common email service.
// 1. First set all data in emailModel
// 2. In emailModel for setting the emailBodyMsg call the EmailSender.generateEmailTemplate(String emailTemplateName, int company_id, String[] templateParams) templateParams are the replacement for the placeholder with sent params. In generateEmailTemplate() it will get the template from database by using PropertiesReader.getEmailTemplateByTemplateName(int companyId, String templateName).
// 3. Then get the PropertiesReader.getEmailDetailsByCompanyId(int companyId) for the get email details from the database.
