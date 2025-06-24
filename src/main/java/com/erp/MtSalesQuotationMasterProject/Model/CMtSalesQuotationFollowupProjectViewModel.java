package com.erp.MtSalesQuotationMasterProject.Model;

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
@Subselect("select * from mtv_sales_quotation_followup_project")
public class CMtSalesQuotationFollowupProjectViewModel {


	@Id
	private int quotation_followup_transaction_id;
	private String quotation_type;
	private String financial_year;
	private String quotation_no;
	private Integer quotation_version;
	private String quotation_date;
	private String expected_branch_name;
	private String department_name;
	private String customer_name;
	private String quotation_by_name;
	private String enquiry_by_name;
	private String assign_to_head_name;
	private String assign_to_name;
	private String approved_by_name;
	private String followup_by_name;
	private double quotation_total_cost;
	private Integer quotation_master_transaction_id;
	private Integer quotation_details_transaction_id;
	private String followup_date;
	private Integer quotation_conversion_rating;
	private String followup_remarks;
	private String positive_remarks;
	private String negative_remarks;
	private boolean cancelation_flag;
	private String cancelation_remarks;
	private String next_followup_date;
	private String future_reference_remarks;
	private String company_name;
	private String company_branch_name;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer customer_id;
	private Integer quotation_by_id;
	private Integer assign_to_head_id;
	private Integer assign_to_id;
	private Integer approved_by_id;
	private Integer followup_by_id;


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
