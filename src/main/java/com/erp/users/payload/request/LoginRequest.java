/*** 
 * @author Dakshabhi IT Solutions
 * Company Controller Class
 */
package com.erp.users.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

	private int company_id;

	@NotBlank
	private String username;

	@NotBlank
	private String password;


}
