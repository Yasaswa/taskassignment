package com.erp.ResetPassword.Service;



import java.util.Map;


public interface IPasswordService {

	Map<String, String> checkICredential(String inputCred);

	Map<String, String> verifyOTP(String mail, String otp);

	Map<String, String> PasswordReset(String password, String confirmpassword, String emplyee_code);

}
