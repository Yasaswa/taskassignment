package com.erp.AmCompanySettings.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from  amv_company_settings")
public class CAmCompanySettingsViewModel {

	@Id
	private int am_company_settings_id;
	private Integer company_id;
	private String company_name;
	private Integer company_type_id;
	private String auto_rm_materail_name_flag;
	private Integer RawMaterialCategoryCount;
	private String e_inv_type_version;
	private String e_inv_tax_scheme;
	private String e_inv_transaction_category;
	private String e_inv_reverse_charges;
	private String e_inv_ecmgst;
	private String e_inv_igst_on_intra;
	private String e_inv_api_provider;
	private String e_inv_client_id;
	private String e_inv_client_secret;
	private String e_inv_sandbox_gsp_name;
	private String e_inv_sandbox_auth_url;
	private String e_inv_sandbox_base_url;
	private String e_inv_sandbox_ewb_url;
	private String e_inv_sandbox_cancel_ewb_url;
	private String e_inv_sandbox_user_name;
	private String e_inv_sandbox_password;
	private String e_inv_production_gsp_name;
	private String e_inv_production_auth_url;
	private String e_inv_production_base_url;
	private String e_inv_production_ewb_url;
	private String e_inv_production_cancel_ewb_url;
	private String e_inv_production_user_name;
	private String e_inv_production_password;
	private String e_inv_purchase_invoice_count;
	private String e_inv_balance_invoice_count;
	private String e_inv_client_connection_string;
	private String e_inv_auth_token;
	private String e_inv_sek;
	private String e_inv_token_expiry;
	private String e_inv_sez_header;
	private String e_inv_sez_footer;
	private String earning_deduction_mapping_base;
	private String earning_deduction_mapping_base_desc;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String remark;
	private String from_email_id;
	private String from_email_password;
	private String smtp_host_name;
	private String pop3_host_name;
	private Integer smtp_port;
	private boolean email_service_status;
	private boolean sms_service_status;
	private String sms_api;
	private boolean whatsapp_service_status;
	private String whatsapp_api;
	private String purchase_process_entry;
	private Integer so_schedule_grace_days;
	private Integer po_schedule_grace_days;
	private double administration_percent;
	private double profit_percent;
	private boolean is_excess_allowed = Boolean.FALSE;
	private Integer adminstration_cost;
	private Integer field_id;
	private String field_name;
	private String erp_version;

	public boolean isIs_excess_allowed() {
		return is_excess_allowed;
	}

	public void setIs_excess_allowed(boolean is_excess_allowed) {
		this.is_excess_allowed = is_excess_allowed;
	}

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
