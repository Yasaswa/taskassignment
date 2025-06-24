package com.erp.StIndentDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  stv_indent_details")
public class CStIndentDetailsViewModel {

	@Id
	private Integer indent_details_id;
	private String indent_no;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String indent_date;
	private Integer indent_version;
	private String indent_type;
	private String indent_source;
	private String indent_source_desc;
	private String department_name;
	private String indented_by_name;
	private String approved_by_name;
	private String customer_name;
	private String customer_order_no;
	private Integer so_sr_no;
	private String customer_order_date;
	private String expected_schedule_date;
	private String indent_status;
	private String indent_status_desc;
	private String remark;
	private boolean is_active;
	private String product_fg_id;
	private String product_material_id;
	private String product_rm_name;
	//	private String product_rm_drawing_no;
	private String product_rm_tech_spect;
	private String product_rm_stock_unit_name;
	//	private String product_type_name;
	private String product_category1_name;
	private String product_rm_oem_part_code;
	private String product_rm_our_part_code;
	private String product_category2_name;
	private Integer product_material_unit_id;
	private double product_fg_material_quantity;
	private double product_fg_material_weight;
	private double product_material_quantity;
	private double product_material_weight;
	private double product_rm_std_weight;
	private int lead_time;
	private double product_material_stock_quantity;
	private double product_material_stock_weight;
	private double product_material_reserve_quantity;
	private double product_material_reserve_weight;
	private double product_material_approved_quantity;
	private double product_material_approved_weight;
	private double product_material_rejected_quantity;
	private double product_material_rejected_weight;
	private double product_material_issue_quantity;
	private double product_material_issue_weight;
	private double product_material_return_quantity;
	private double product_material_return_weight;
	private double product_material_preclosed_quantity;
	private double product_material_preclosed_weight;
	private double product_material_grn_accepted_quantity;
	private double product_material_grn_accepted_weight;
	private String product_rm_packing_name;
	//	private String product_rm_hsn_sac_code;
//	private String product_rm_hsn_sac_rate;
	private String approval_remark;
	private double product_material_receipt_quantity;
	private double product_material_receipt_weight;
	private String issued_by_name;
	private Integer issued_by_id;
	private Date issued_date;
	private String indent_item_status;
	private String indent_item_status_desc;
	private String field_name;
	private Integer field_id;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer indent_master_id;
	private Integer indent_type_id;
	private Integer customer_id;
	private Integer department_id;
	private Integer indented_by_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer product_type_id;
	private Integer product_category1_id;
	private Integer product_category2_id;
	//	private Integer product_make_id;
//	private Integer product_material_type_id;
//	private Integer product_material_grade_id;
	private Integer product_rm_hsn_sac_code_id;
	private Integer product_rm_packing_id;
	private String sales_order_details_transaction_id;
	private double product_child_bom_quantity;
	private double product_child_bom_weight;
	private Integer cost_center_id;
	private String cost_center_name;
	private double prev_po_quantity;
	private double prev_po_weight;
	private String indent_priority_desc;
}
