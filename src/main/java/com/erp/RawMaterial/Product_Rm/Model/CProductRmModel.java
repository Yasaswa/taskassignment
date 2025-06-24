package com.erp.RawMaterial.Product_Rm.Model;

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
@Table(name = "sm_product_rm")
public class CProductRmModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer product_id;
	private String product_rm_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer product_type_id;
	private Integer product_category1_id;
	private String product_rm_code;
	private String product_rm_name;
	private String product_rm_short_name;
	private String product_rm_print_name;
	private String product_rm_tech_spect;
	private String product_rm_oem_part_code;
	private String product_rm_our_part_code;
	private String product_rm_item_sr_no;
	private String product_rm_drawing_no;
	private String product_rm_model_no;
	private Integer product_rm_hsn_sac_code_id;
	private Integer product_rm_purchase_unit_id;
	private Integer product_rm_sales_unit_id;
	private Integer product_rm_stock_unit_id;
	private Integer product_rm_packing_id;
	private Double opening_qty = 0.0;
	private Double opening_weight = 0.0;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private String product_rm_bar_code;
	private String bom_applicable;
	private String product_rm_qr_code;
	private String product_consumption_mode;
	private String product_origin_type;
	private String product_origin_country;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private Double actual_count;

//	private Integer profit_center_id;
//	private Integer cost_center_id;
	
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
