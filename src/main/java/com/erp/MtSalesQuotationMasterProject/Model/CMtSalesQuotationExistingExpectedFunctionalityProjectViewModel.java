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
@Immutable
@Entity
@Subselect(value = "Select * From mtv_sales_quotation_existing_expected_functionality_project")
public class CMtSalesQuotationExistingExpectedFunctionalityProjectViewModel {

	@Id
	private int quotation_functionality_transaction_id;
	private String quotation_no;
	private String quotation_date;
	private Integer quotation_version;
	private String quotation_existing_functionality;
	private String quotation_expected_functionality;
	private String quotation_expected_value;
	private String company_name;
	private String company_branch_name;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer quotation_master_transaction_id;
	private Integer quotation_details_transaction_id;

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
