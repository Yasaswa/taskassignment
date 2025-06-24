/*** 
 * @author Dakshabhi IT Solutions
 * Company Controller Class
 */
package com.erp.users.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Data
@Table(name = "amv_users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username")
		})
public class User {
	@Id
	private String user_code;

	private Integer user_id;

	private int company_id;

	private int company_branch_id;
	
	private int department_id;

	private String user;

	private String user_type;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	private String company_name;

	private String company_branch_name;

	private String company_branch_address;

	private String branch_short_name;

	private String department_name;

	private String company_access;
}
