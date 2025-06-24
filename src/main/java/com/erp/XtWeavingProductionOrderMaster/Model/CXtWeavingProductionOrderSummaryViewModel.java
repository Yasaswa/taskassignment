package com.erp.XtWeavingProductionOrderMaster.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Immutable
@Subselect("SELECT * FROM xtv_weaving_production_order")
public class CXtWeavingProductionOrderSummaryViewModel {

	@Id
	private int weaving_production_order_id;
	private String sales_order_no;
	private String warping_order_no;
	private String sizing_order_no;
	private String weaving_order_no;
	private String production_section_name;
	private String production_sub_section_name;
	private Integer product_material_name;
	private Integer product_unit_name;
	private String set_no;
	private Integer no_of_ends;
	private String sort_no;
	private String weaving_order_status_desc;
	private double order_quantity;
	private double schedule_quantity;
	private String schedule_Date;
	private String start_date;
	private String end_date;
	private double actual_count;
	private String yarn_count;
	private String customer_name;
	private String approved_by_name;
	private String approved_date;
	private String order_type;
	private double sizing_job_rate;
	private String status_remark;
	private String other_terms_conditions;
	private String remark;
	private String company_name;
	private String financial_year;
	private String Active;
	private String Deleted;
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
	private Integer product_unit_id;
	private Integer production_section_id;
	private String product_material_id;
	private Integer approved_by_id;
	private Integer field_id;
	private String field_name;
	private Double glm;
	private Double gsm;

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
