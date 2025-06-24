package com.erp.MtEnquiryDetailsTrading.Model;

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
@Subselect("select * from mtv_enquiry_details_trading")
public class CMtEnquiryDetailsTradingViewModel {

	@Id
	private int enquiry_details_transaction_id;
	private String enquiry_type;
	private String enquiry_life;
	private String enquiry_no;
	private String enquiry_date;
	private Integer enquiry_version;
	private String department_name;
	private String customer_name;
	private String customer_state_name;
	private String enquiry_type_name;
	private String enquiry_type_short_name;
	private Integer sr_no;
	private Integer enquiry_by_id;
	private String product_type_short_name;
	private String product_material_code;
	private String product_material_name;
	private double product_material_std_weight;
	private String product_material_unit_name;
	private String enquiry_item_status_desc;
	private String hsn_sac_code;
	private double hsn_sac_percent;
	private double item_material_mrp;
	private double item_material_landed_price;
	private String product_material_tech_spect;
	private String product_packing_name;
	private double product_material_enquiry_quantity;
	private double product_material_enquiry_weight;
	private double product_material_moq_quantity;
	private String product_material_notes;
	private Integer expected_lead_time;
	private double material_std_rate;
	private Date material_schedule_date;
	private String enquiry_mail_sent_status_desc;
	private String approved_by_name;
	private String approved_date;
	private String material_enquiry_approval_remark;
	private String overall_schedule_date;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String customer_email;
	private String customer_country_name;
	private String customer_city_name;
	private String customer_district_name;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer enquiry_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer enquiry_type_id;
	private Integer product_type_id;
	private String product_type_name;
	private String product_material_id;
	private Integer hsn_sac_id;
	private double product_material_conversion_factor;
	private String enquiry_item_status;
	private String enquiry_status;
	private String enquiry_mail_sent_status;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Integer department_id;
	private Integer approved_by_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer assign_to_head_id;
	private Integer assign_to_id;
	private Integer customer_id;
	private Integer agent_id;
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
