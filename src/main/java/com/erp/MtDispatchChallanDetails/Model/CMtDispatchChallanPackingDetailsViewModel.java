package com.erp.MtDispatchChallanDetails.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  mtv_dispatch_challan_packing_details")
public class CMtDispatchChallanPackingDetailsViewModel {

	@Id
	private Integer dispatch_challan_packing_id;
	private Integer sr_no;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private String customer_order_no;
	private String production_count_name;
	private String yarn_packing_types_name;
	private String so_sr_no;
	private String product_unit_name;
	private Double item_dispatch_quantity;
	private Double item_dispatch_weight;
	private Double cone_weight;
	private Double cone_quantity;
	private Double per_packing_weight;
	private Double packing_gross_weight;
	private Double packing_net_weight;
	private Double packing_quantity;
	private Integer packing_starting_no;
	private Integer packing_ending_no;
	private String lot_no;
	private String sub_lot_no;
	private String remark;
	private String company_name;
	private String company_branch_name;
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
	private Integer yarn_packing_types_id;
	private Integer production_count_id;
	private String product_material_id;
	private Integer product_unit_id;
	private Integer dispatch_challan_master_transaction_id;
	private Integer sales_order_details_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer yarn_packing_details_id;
//	private double production_actual_count;
	
	
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
