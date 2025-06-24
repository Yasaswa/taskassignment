package com.erp.Transporter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from cmv_transporter_banks")
public class CTransporterBanksViewModel {

	@Id
	private int transporter_bank_id;
	private Integer bank_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer transporter_id;
	private String company_name;
	private String company_branch_name;
	private String transporter_name;
	private String transporter_bank_name;
	private String transporter_bank_branch_name;
	private String bank_name;
	private String transporter_bank_account_type;
	private String transporter_bank_account_no;
	private String transporter_bank_ifsc_code;
	private String transporter_bank_swift_code;
	private String transporter_bank_gst_no;
	private String currency_type;
	private String transporter_bank_gl_codes;
	private String transporter_bank_accounts_id;
	//  Added By Mohit
//	private String Active;
//	private String Deleted;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer field_id;
	private String field_name;


	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}


}
