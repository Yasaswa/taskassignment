package com.erp.MtSalesQuotationMasterProject.Model;

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
@Table(name = "mt_sales_quotation_terms_project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesQuotationTermsProjectModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quotation_terms_transaction_id")
	private int quotation_terms_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer quotation_master_transaction_id;
	private Integer quotation_details_transaction_id;
	private String quotation_no;
	private String quotation_date;
	private Integer quotation_version;
	private Integer quotation_terms_parameters_id;
	private String quotation_terms_parameters_name;
	private String quotation_terms_parameters_value;
	private String quotation_terms_parameters_expected_value;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
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
