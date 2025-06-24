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
@Table(name = "mt_enquiry_existing_expected_functionality_service")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtEnquiryExistingExpectedFunctionalityServiceModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enquiry_functionality_transaction_id")
	private int enquiry_functionality_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer enquiry_master_transaction_id;
	private Integer enquiry_details_transaction_id;
	private String enquiry_no;
	private Date enquiry_date;
	private Integer enquiry_version;
	private String enquiry_existing_functionality;
	private String enquiry_expected_functionality;
	private String enquiry_expected_value;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
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
