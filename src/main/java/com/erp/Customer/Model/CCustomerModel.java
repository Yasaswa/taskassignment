package com.erp.Customer.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cm_customer")
public class CCustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customer_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String customer_type;
	private String customer_code;
	private String customer_name;
	private String customer_short_name;
	private String customer_sector;
	private String nature_of_business;
	private Integer payment_term_id;
	private String customer_rating;
	private String customer_gl_codes;
	private String customer_accounts_id;
	private String customer_history;
	private String username;
	private String password;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private String remark;

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
}
