package com.erp.Common.PasswordsSecurity;

import com.erp.Employee.Employees.Repository.IEmployeesRepository;
import com.erp.users.models.User;
import com.erp.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/UserPasswordReset")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UserPasswordReset {

	@Autowired
	IEmployeesRepository iEmployeesRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody PasswordResetRequest passwordResetRequest) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Check if the user exists
			User user = userRepository.findUserByCodeAndPass(passwordResetRequest.getUser_code(), passwordResetRequest.getCompany_id());

			if (passwordEncoder.matches(passwordResetRequest.getCurrent_password(), user.getPassword())) {
				String confirmEncryptedPass = passwordEncoder.encode(passwordResetRequest.getConfirm_password());

				// Update the password
				iEmployeesRepository.updateUserCode(confirmEncryptedPass, user.getUser_code(), user.getCompany_id());

				response.put("status", 1);
				response.put("message", "Password has been reset successfully...!");

			} else {
				response.put("status", 0);
				response.put("message", "Current password not matched..!.");
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				response.put("success", 0);
				response.put("data", "");
				response.put("error", sqlEx.getMessage());
				return response;
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());
			return response;
		}

		return response;

	}
}