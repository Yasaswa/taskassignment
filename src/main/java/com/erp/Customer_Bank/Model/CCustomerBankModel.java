package com.erp.Customer_Bank.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "cm_customer_banks")
public class CCustomerBankModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_bank_id")
	private int cust_bank_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer customer_id;
	private Integer cust_branch_id;
	private Integer bank_id;
	private String customer_bank_name;
	private String customer_bank_branch_name;
	private String cust_bank_account_type;
	private String cust_bank_account_no;
	private String cust_bank_ifsc_code;
	private String cust_bank_swift_code;
	private String cust_bank_gst_no;
	private String currency_type;
	private String cust_bank_gl_codes;
	private String cust_bank_accounts_id;
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
