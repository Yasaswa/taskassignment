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
@Table(name = "mt_enquiry_terms_project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtEnquiryTermsProjectModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enquiry_terms_transaction_id")
	private int enquiry_terms_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer enquiry_master_transaction_id;
	private Integer enquiry_details_transaction_id;
	private String enquiry_no;
	private String enquiry_date;
	private Integer enquiry_version;
	private Integer enquiry_terms_parameters_id;
	private String enquiry_terms_parameters_name;
	private String enquiry_terms_parameters_value;
	private String enquiry_terms_parameters_expected_value;
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
}

