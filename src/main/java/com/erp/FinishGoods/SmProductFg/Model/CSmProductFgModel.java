package com.erp.FinishGoods.SmProductFg.Model;

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
@Table(name = "sm_product_fg")
public class CSmProductFgModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer product_id;
	private String product_fg_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer product_type_id;
	private Integer product_category1_id;
	private String product_fg_code;
	private String product_fg_name;
	private String product_fg_short_name;
	private String product_fg_print_name;
	private String product_fg_tech_spect;
	private String product_fg_oem_part_code;
	private String product_fg_our_part_code;
	private String product_fg_item_sr_no;
	private String product_fg_drawing_no;
	private String product_fg_model_no;
	private Integer product_fg_hsn_sac_code_id;
	private Integer product_fg_purchase_unit_id;
	private Integer product_fg_sales_unit_id;
	private Integer product_fg_stock_unit_id;
	private Integer product_fg_packing_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private String product_fg_bar_code;
	private String product_fg_qr_code;
	private String product_consumption_mode;
	private String product_origin_type;
	private String product_origin_country;
	private String remark;
	private String qa_remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String bom_applicable;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

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
