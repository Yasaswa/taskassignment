package com.erp.MtEnquiryMasterServices.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mt_enquiry_details_services")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtEnquiryDetailsServicesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enquiry_details_transaction_id;
	private Integer enquiry_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer enquiry_type_id;
	private String enquiry_type;
	private String enquiry_no;
	private String enquiry_date;
	private Integer enquiry_version;
	private Integer sr_no;
	private String product_type_short_name;
	private Integer product_type_id;
	private String product_material_id;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Double product_material_enquiry_quantity;
	private Double product_material_enquiry_weight;
	private Double product_material_moq_quantity;
	private String product_material_notes;
	private Double product_material_conversion_factor;
	private Integer expected_lead_time;
	private Double material_std_rate;
	private String material_schedule_date;
	private String enquiry_item_status;
	private String material_enquiry_approval_remark;
	private Integer department_id;
	private Integer approved_by_id;
	private String approved_date;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public String getEnquiry_date() {
		return enquiry_date;
	}

	public void setEnquiry_date(String enquiry_date) {
		if (enquiry_date == null || enquiry_date.isEmpty()) {
			this.enquiry_date = null;
		} else {
			this.enquiry_date = enquiry_date;
		}
	}

	public String getMaterial_schedule_date() {
		return material_schedule_date;
	}

	public void setMaterial_schedule_date(String material_schedule_date) {
		if (material_schedule_date == null || material_schedule_date.isEmpty()) {
			this.material_schedule_date = null;
		} else {
			this.material_schedule_date = material_schedule_date;
		}
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		if (approved_date == null || approved_date.isEmpty()) {
			this.approved_date = null;
		} else {
			this.approved_date = approved_date;
		}
	}

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
