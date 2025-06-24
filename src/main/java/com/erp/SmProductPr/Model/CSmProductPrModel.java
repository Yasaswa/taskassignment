package com.erp.SmProductPr.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sm_product_pr")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSmProductPrModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int product_id;
	private String product_pr_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer product_type_id;
	private Integer product_category1_id;
	private String product_pr_code;
	private String product_pr_name;
	private String project_name;
	private String project_reference_number;
	private String project_start_date;
	private String project_end_date;
	private String product_pr_short_name;
	private String product_pr_print_name;
	private String product_pr_tech_spect;
	private String product_pr_oem_part_code;
	private String product_pr_our_part_code;
	private String product_pr_item_sr_no;
	private String product_pr_drawing_no;
	private String product_pr_model_no;
	private Integer product_pr_hsn_sac_code_id;
	private Integer product_pr_purchase_unit_id;
	private Integer product_pr_sales_unit_id;
	private Integer product_pr_stock_unit_id;
	private Integer product_pr_packing_id;
	private String product_pr_bar_code;
	private String product_pr_qr_code;
	private String product_consumption_mode;
	private String product_origin_type;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	
//	private Integer profit_center_id;
//	private Integer cost_center_id;
	
	private String product_origin_country;
	private String bom_applicable;
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

	public boolean getis_active() {
		return is_active;
	}

	public void setis_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean getis_delete() {
		return is_delete;
	}

	public void setis_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public String getProject_start_date() {
		return project_start_date;
	}

	public void setProject_start_date(String project_start_date) {
		if (project_start_date == null || project_start_date.isEmpty()) {
			this.project_start_date = null;
		} else {
			this.project_start_date = project_start_date;
		}
	}

	public String getProject_end_date() {
		return project_end_date;
	}

	public void setProject_end_date(String project_end_date) {
		if (project_end_date == null || project_end_date.isEmpty()) {
			this.project_end_date = null;
		} else {
			this.project_end_date = project_end_date;
		}
	}

}
