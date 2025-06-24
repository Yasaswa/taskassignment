package com.erp.FinishGoods.SmProductFg.Model;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Immutable
@Table(name = "smv_product_fg")
public class CSmProductFgViewModel {

	@Id
	private Integer product_id;
	private String product_fg_id;
	private String company_name;
	private String company_branch_name;
	private String product_fg_code;
	private String product_fg_name;
	private String product_type_group;
	private String product_type_name;
	private String product_category1_name;
	private String product_fg_short_name;
	private String product_fg_print_name;
	private String product_fg_tech_spect;
	private String product_fg_oem_part_code;
	private String product_fg_our_part_code;
	private String product_fg_hsn_sac_code;
	private double product_fg_hsn_sac_rate;
	private String product_fg_purchase_unit_name;
	private String product_fg_sales_unit_name;
	private String product_fg_stock_unit_name;
	private String product_fg_packing_name;
	private String product_fg_item_sr_no;
	private String product_fg_drawing_no;
	private String product_fg_model_no;
	private String product_fg_bar_code;
	private String product_fg_qr_code;
	private String product_consumption_mode;
	private String product_origin_type;
	private String product_origin_country;
	private String product_category1_short_name;
	private String product_type_short_name;
	private String product_fg_hsn_sac_type;
	private boolean product_fg_hsn_sac_is_exampted;
	private double product_fg_quantity_per_packing;
	private double product_fg_weight_per_packing;
	private String godown_name;
	private String godown_short_name;
	private String godown_section_name;
	private String godown_section_beans_name;
	private String bom_applicable;
	private String remark;
	private String qa_remark;
	// added by mohit use for db 1.1
//	private String Active;
//	private String Deleted;
//	private Integer section_beans_count;
	// added by mohit use for db 1.1
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
	private Integer product_type_id;
	private Integer product_category1_id;
	private Integer product_fg_hsn_sac_code_id;
	private Integer product_fg_purchase_unit_id;
	private Integer product_fg_sales_unit_id;
	private Integer product_fg_stock_unit_id;
	private Integer product_fg_packing_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private String field_name;
	private Integer field_id;

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

	public boolean isProduct_fg_hsn_sac_is_exampted() {
		return product_fg_hsn_sac_is_exampted;
	}

	public void setProduct_fg_hsn_sac_is_exampted(boolean product_fg_hsn_sac_is_exampted) {
		this.product_fg_hsn_sac_is_exampted = product_fg_hsn_sac_is_exampted;
	}

}
