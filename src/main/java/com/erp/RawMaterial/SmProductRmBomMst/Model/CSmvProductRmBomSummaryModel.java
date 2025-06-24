package com.erp.RawMaterial.SmProductRmBomMst.Model;

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
@Subselect("select * from smv_product_rm_bom_summary")
public class CSmvProductRmBomSummaryModel {
	@Id
	private Integer product_rm_bom_id;
	private String product_child_rm_id;
	private String company_name;
	private String company_branch_name;
	private String product_rm_bom_no;
	private Integer product_rm_bom_version;
	private String product_rm_bom_name;
	private String customer_name;
	private Integer lead_time;
	private String customer_order_id;
	private Integer product_parent_rm_id;
	private String product_parent_rm_name;
	private String product_parent_rm_drawing_no;
	private String product_parent_rm_tech_spect;
	private Double product_parent_rm_quantity;
	private Double product_parent_rm_weight;
	private String product_parent_rm_stock_unit_name;
	private String product_rm_bom_status;
	private String product_child_rm_name;
	private Double product_child_rm_quantity;
	private String product_child_rm_drawing_no;
	private String product_child_rm_tech_spect;
	private Double product_child_rm_weight;
	private String product_child_rm_stock_unit_name;
	private String field_name;
	private Integer field_id;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private int company_id;
	private int company_branch_id;
	private Integer customer_id;

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
