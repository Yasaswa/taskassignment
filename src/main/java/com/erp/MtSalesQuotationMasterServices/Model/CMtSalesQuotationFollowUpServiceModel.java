package com.erp.MtSalesQuotationMasterServices.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = ("mt_sales_quotation_followup_services"))
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesQuotationFollowUpServiceModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quotation_followup_transaction_id")
	private int quotation_followup_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer quotation_master_transaction_id;
	private Integer quotation_details_transaction_id;
	private String quotation_no;
	private String quotation_date;
	private Integer quotation_version;
	private Integer customer_id;
	private Integer expected_branch_id;
	private Integer enquiry_by_id;
	private Integer quotation_by_id;
	private Integer assign_to_head_id;
	private Integer assign_to_id;
	private Integer approved_by_id;
	private String approved_date;
	private double quotation_total_cost;
	private Integer followup_by_id;
	private String followup_date;
	private Integer quotation_conversion_rating;
	private String followup_remarks;
	private String positive_remarks;
	private String negative_remarks;
	private boolean cancelation_flag = Boolean.FALSE;
	private String cancelation_remarks;
	private String next_followup_date;
	private String future_reference_remarks;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
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

	public String getQuotation_date() {
		return quotation_date;
	}

	public void setQuotation_date(String quotation_date) {
		if (quotation_date == null || quotation_date.isEmpty()) {
			this.quotation_date = null;
		} else {
			this.quotation_date = quotation_date;
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


	public String getFollowup_date() {
		return followup_date;
	}

	public void setFollowup_date(String followup_date) {
		if (followup_date == null || followup_date.isEmpty()) {
			this.followup_date = null;
		} else {
			this.followup_date = followup_date;
		}
	}

	public String getNext_followup_date() {
		return next_followup_date;
	}

	public void setNext_followup_date(String next_followup_date) {
		if (next_followup_date == null || next_followup_date.isEmpty()) {
			this.next_followup_date = null;
		} else {
			this.next_followup_date = next_followup_date;
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

	public boolean isCancelation_flag() {
		return cancelation_flag;
	}

	public void setCancelation_flag(boolean cancelation_flag) {
		this.cancelation_flag = cancelation_flag;
	}

	public String getCancelation_remarks() {
		return cancelation_remarks;
	}

	public void setCancelation_remarks(String cancelation_remarks) {
		this.cancelation_remarks = cancelation_remarks;
	}
}
