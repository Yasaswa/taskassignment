package com.erp.ResetPassword.Controller;

import com.erp.ResetPassword.Service.IPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/reset", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CResetPasswordController {

    @Autowired
    IPasswordService iPasswordService;

    @PostMapping("/checkCredential")
    public Map<String, String> GetCredential(@RequestBody Map<String, String> cred) {
        String inputCred = cred.get("inputCred");
        return iPasswordService.checkICredential(inputCred);
    }


    @PostMapping("/verifyOtp")
    public Map<String, String> verifyOtp(@RequestBody Map<String, String> otpRequest) {
        String emailId = otpRequest.get("emailId");
        String otp = otpRequest.get("otp");
        return iPasswordService.verifyOTP(emailId, otp);

    }

    @PostMapping("/PasswordReset")
    public Map<String, String>  PasswordReset(@RequestBody Map<String, String> passCred){
        String password = passCred.get("password");
        String employee_code = passCred.get("employee_code");
        String confirmpassword = passCred.get("confirmpassword");
        return iPasswordService.PasswordReset(password, confirmpassword, employee_code);
    }


}
