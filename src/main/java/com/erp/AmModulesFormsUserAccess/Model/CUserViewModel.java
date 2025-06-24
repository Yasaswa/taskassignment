package com.erp.AmModulesFormsUserAccess.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  amv_users")
public class CUserViewModel {

	@Id
	private String user_type;
	private String user_id;
	private String user_code;
	private String user;
	private String username;
	private String password;
	private Integer company_id;


}
