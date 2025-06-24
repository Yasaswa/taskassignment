package com.erp.Common.PasswordsSecurity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordResetRequest {
	private Integer employee_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String user_code;
	private String current_password;
	private String new_passsword;
	private String confirm_password;
}
