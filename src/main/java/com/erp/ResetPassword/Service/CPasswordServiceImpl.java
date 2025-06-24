package com.erp.ResetPassword.Service;

import com.erp.Common.EmailUtilities.EmailServices.EmailSender;
import com.erp.Common.EmailUtilities.Model.EmailModel;
import com.erp.Common.EmailUtilities.PropertyReader.PropertiesReader;
import com.erp.ResetPassword.Repository.IPasswordResetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CPasswordServiceImpl implements IPasswordService{


    @Autowired
    IPasswordResetRepository iPasswordResetRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private PropertiesReader propertiesReader;

    private Map<String, String> otpStore = new HashMap<>();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Map<String, String> checkICredential(String inputCred) {
        Map<String, String> resultMap = new HashMap<>();
        List<Object[]> credentials = iPasswordResetRepository.checkCredentialInDatabase(inputCred);

        if (!credentials.isEmpty()) {
            otpStore.clear();
            Object[] firstResult = credentials.get(0);
            String employeeName = (String) firstResult[0];
            String employeeCode = (String) firstResult[1];
            String emailId = ((String) firstResult[2]).toLowerCase().trim();
            int company_id = (Integer) firstResult[3];
            otpStore.put("emailId", emailId);
            otpStore.put("Employee_Code", employeeCode);
            otpStore.put("company_id", String.valueOf(company_id));

            if (emailId != null || !emailId.isEmpty()) {
                String otp = generateOTP();
                boolean mailSend = sendOtpEmail(emailId, employeeName, otp);

                if(mailSend){
                    LocalDateTime expireTime = LocalDateTime.now().plusMinutes(5);
                    otpStore.put("otp", otp);
                    otpStore.put("expireTime", expireTime.format(formatter));

                    resultMap.put("email", emailId);
                    resultMap.put("status", "success");
                    resultMap.put("message", "OTP sent to " + emailId + ".");
                }else {
                    resultMap.put("status", "fail");
                    resultMap.put("message", "Email address is empty or invalid");
                }
            } else {
                resultMap.put("status", "fail");
                resultMap.put("message", "Email address is empty or invalid");
            }
        } else {
            resultMap.put("status", "fail");
            resultMap.put("message", "Credential not found");
        }

        return resultMap;
    }

    @Override
    public Map<String, String> verifyOTP(String emailId, String otp) {
        Map<String, String> resultMap = new HashMap<>();
        LocalDateTime expireTime = LocalDateTime.parse(otpStore.get("expireTime"), formatter);
        LocalDateTime currentTime = LocalDateTime.now();
        if(currentTime.isBefore(expireTime)) {
            if (emailId.equalsIgnoreCase(otpStore.get("emailId"))) {
                String storedOtp = otpStore.get("otp");
                if (storedOtp.equals(otp)) {
                    System.out.println( "OTP verified successfully.");
                    resultMap.put("status", "success");
                    resultMap.put("message", "OTP verified successfully.");
                } else {
                    System.out.println( "Invalid OTP.");
                    resultMap.put("status", "fail");
                    resultMap.put("message", "Invalid OTP.");
                }

            } else {
                resultMap.put("status", "fail");
                resultMap.put("message", "Invalid Email request, Please check Email/Resend OTP");
            }
        }else{
            resultMap.put("status", "fail");
            resultMap.put("message", "OTP Time expired, Please Resend OTP");
        }

        return resultMap;
    }

    @Override
    public Map<String, String> PasswordReset(String password, String confirmpassword, String employee_code) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            if(password.equals(confirmpassword)){
            String confirmEncryptedPass = passwordEncoder.encode(password);
            System.out.println("Encrepted: " + confirmEncryptedPass + " passowrd: " +  password);
           int check =  iPasswordResetRepository.updateUserCode(confirmEncryptedPass, employee_code, Integer.parseInt(otpStore.get("company_id")));
           if(check >= 1){
               resultMap.put("status" , "success");
               resultMap.put("message" , "Password reset successfully");
           }else{
               resultMap.put("status" , "fail");
               resultMap.put("message" , "Password reset fail, try again");
           }
            }else{
                resultMap.put("status" , "fail");
                resultMap.put("message" , "Password reset fail, check password again");
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultMap;
    }

    private String generateOTP() {
        synchronized (this) {
            int otp = (int) (Math.random() * 9000) + 1000;
            return String.valueOf(otp);
        }
    }

    private boolean sendOtpEmail(String toEmail, String employeeName, String otp) {
        synchronized (this) {
            try {
                int company_id = Integer.parseInt(otpStore.get("company_id"));

                PropertiesReader propertiesReader = new PropertiesReader();

                Map<String, Object> emailDetails = propertiesReader.getEmailDetailsByCompanyId(company_id);
                System.out.println("getmaildeatails: " + emailDetails);

                String fromEmail = (String) emailDetails.get("from_email_id");
                String host = (String) emailDetails.get("smtp_host_name");
                String smtpUserName = (String) emailDetails.get("from_email_username");
                String smtpPassword = (String) emailDetails.get("from_email_password");
                Integer port = (Integer) emailDetails.get("smtp_port");

                EmailModel emailModel = new EmailModel();
                emailModel.setMailSubject("Password reset OTP");
                emailModel.setMailfrom(fromEmail);

                //set list of email address
                ArrayList<String> mailto = new ArrayList<>();
                mailto.add(toEmail);
                emailModel.setMailto(mailto);
                emailModel.setMailBodyMsg(
                        "<!DOCTYPE html>"
                                + "<html><head>"
                                + "<style>"
                                + ".email-body {font-family: Arial, sans-serif; font-size: 14px; line-height: 1.6; color: #333;}"
                                + ".email-header {font-size: 18px; font-weight: bold; margin-bottom: 20px;}"
                                + ".otp-code {font-size: 16px; color: #ff0000; font-weight: bold;}"
                                + ".footer {margin-top: 30px; font-size: 12px; color: #888;}"
                                + "</style></head>"
                                + "<body>"
                                + "<div class='email-body'>"
                                + "<p>Hello <strong>" + employeeName + "</strong>,</p>"
                                + "<p>Your Password reset OTP is <span class='otp-code'>" + otp + "</span>.</p>"
                                + "<p>It will only be active for the next <strong>5 minutes</strong>.</p>"
                                + "<p>Regards,</p>"
                                + "<p>Pashupati</p>"
                                + "<div class='footer'><p>This is an automated message. Please do not reply to this email.</p></div>"
                                + "</div></body></html>"
                );

                EmailSender emailSender = new EmailSender();
                return emailSender.sendEmails(emailModel, host, smtpUserName, smtpPassword, port, false);
            } catch (MessagingException e) {
                System.err.println("Error sending OTP email: " + e.getMessage());
                return false;
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
                return false;
            }
        }

    }
}
