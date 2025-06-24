package com.erp.MtEnquiryMasterProject.Model;

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
@Table(name = "mt_enquiry_details_project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtEnquiryDetailsProjectModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enquiry_details_transaction_id")
	private int enquiry_details_transaction_id;
	private Integer enquiry_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer enquiry_type_id;
	private String enquiry_type;
	private String enquiry_no;
	private Date enquiry_date;
	private Integer enquiry_version;
	private Integer sr_no;
	@Transient
	private String product_material_name;    // This field is not present in the database.
	private String product_type_short_name;
	private String product_material_id;
	private String product_material_tech_spect;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private double product_material_enquiry_quantity;
	private double product_material_enquiry_weight;
	private double product_material_moq_quantity;
	private String product_material_notes;
	private double product_material_conversion_factor;
	private int expected_lead_time;
	private double material_std_rate;
	private Date material_schedule_date;
	private String enquiry_item_status;
	private String material_enquiry_approval_remark;
	private Integer department_id;
	private Integer approved_by_id;
	private Date approved_date;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;


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

