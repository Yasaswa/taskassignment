package com.erp.FinishGoods.SmProductFgProcess.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_fg_process")
public class CSmProductFgProcessViewModel {

	@Id
	private int product_process_id;
	private String product_process_name;
	private String product_parent_process_name;
	private double product_process_std_scrap_percent;
	private double product_process_std_production_hrs;
	private String product_fg_name;
	private String product_fg_technical_name;
	private String product_fg_oem_part_code;
	private String product_fg_our_part_code;
	private String product_fg_drawing_no;
	private String product_fg_hsn_sac_code;
	private String product_type_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_material_type_name;
	private String product_material_grade_name;
	private String product_fg_id;
	private Integer product_parent_process_id;
	private Integer field_id;
	private String field_name;

// Added by mohit and use for db 1.1	
//	private String remark;
//	private String Active;
//	private String Deleted;
//	private boolean is_active;
//	private boolean is_delete;
//	private String created_by;
//	private Date created_on;
//	private String modified_by;
//	private Date modified_on;
//	private String deleted_by;
//	private Date deleted_on;
//	private Integer company_id;
//	private Integer company_branch_id;
//	private Integer product_fg_process_id;
//	private String company_name;
//	private String company_branch_name;
//	
//	public boolean isIs_active() {
//		return is_active;
//	}
//	public void setIs_active(boolean is_active) {
//		this.is_active = is_active;
//	}
//	public boolean isIs_delete() {
//		return is_delete;
//	}
//	public void setIs_delete(boolean is_delete) {
//		this.is_delete = is_delete;
//	}

}
