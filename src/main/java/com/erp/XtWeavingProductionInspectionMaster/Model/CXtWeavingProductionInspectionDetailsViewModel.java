package com.erp.XtWeavingProductionInspectionMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

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
@Table(name = "xtv_weaving_production_inspection_details")
public class CXtWeavingProductionInspectionDetailsViewModel {

	@Id
	private Integer weaving_production_inspection_details_id;
	private String inspection_production_code;
	private String inspection_production_set_no;
	private String sales_order_no;
	private String inspection_order_no;
	private String inspection_production_date;
	private String machine_name;
	private String godown_name;
	private String product_material_name;
	private String inspection_production_status_desc;
	private String inspection_production_status;
//	private String plant_name;
	private Integer prod_month;
	private Integer prod_year;
	private String sizing_beam_no;
	private String production_operator_name;
	private String style;
	private String shift;
	private double product_in_meter;
	private double inspection_mtr;
	private double dispatch_quantity;
	private double product_pick;
	private double difference;
	private double width;
	private String sort_no;
	private Integer roll_no;
	private double average;
	private double weight;
	private String product_material_id;
//	private String inspection_production_master_status_desc;
	private String inspection_production_master_status;
	private String production_section_name;
	private String production_sub_section_name;
	private String status_remark;
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
	private String company_name;
//	private String company_branch_name;
	private String financial_year;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer weaving_production_inspection_master_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private Integer sub_section_id;
//	private Integer production_operator_id;
	private Integer machine_id;
	private String product_type_group;
	private Integer product_material_packing_id;
	private Integer product_type_id;
	private Integer product_material_stock_unit_id;
	private String dispatch_date;
	private Double dispatch_weight;
	private String stock_status;
	private String stock_status_description;
	private String approved_date;
	private Integer book_type_id;
	private String book_type_name;
	private String dispatch_challan_no;


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
