package com.erp.Supplier.Model;

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
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "cm_supplier_banks")
public class CSupplierBanksModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supp_bank_id")
	private int supp_bank_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer supplier_id;
	private Integer bank_id;
	private Integer supp_branch_id;
	private String supp_bank_name;
	private String supp_bank_branch_name;
	private String supp_bank_account_type;
	private String supp_bank_account_no;
	private String supp_bank_ifsc_code;
	private String supp_bank_swift_code;
	private String supp_bank_gst_no;
	private String currency_type;
	private String supp_bank_gl_codes;
	private String supp_bank_accounts_id;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
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
