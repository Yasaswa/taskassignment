package com.erp.Transporter.Model;

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
@Table(name = "cm_transporter_banks")
public class CTransporterBanksModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transporter_bank_id")
	private int transporter_bank_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer transporter_id;
	private Integer bank_id;
	private String transporter_bank_name;
	private String transporter_bank_branch_name;
	private String transporter_bank_account_type;
	private String transporter_bank_account_no;
	private String transporter_bank_ifsc_code;
	private String transporter_bank_swift_code;
	private String transporter_bank_gst_no;
	private String currency_type;
	private String transporter_bank_gl_codes;
	private String transporter_bank_accounts_id;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String created_by;
	private String modified_by;
	private String deleted_by;

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
