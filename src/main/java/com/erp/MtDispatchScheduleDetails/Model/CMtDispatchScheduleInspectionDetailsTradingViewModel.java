package com.erp.MtDispatchScheduleDetails.Model;

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
@Subselect("select * from  mtv_dispatch_schedule_inspection_details_trading")
public class CMtDispatchScheduleInspectionDetailsTradingViewModel {

	@Id
	private Integer dispatch_schedule_inspection_details_transaction_id;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private Integer dispatch_schedule_version;
	private String customer_name;
	private String customer_order_no;
	private String customer_order_Date;
	private String sales_order_no;
	private String sales_order_Date;
	private String sales_order_version;
	private String product_material_name;
	private String product_type_group;
	private double product_material_std_weight;
	private String product_material_tech_spect;
	private String product_unit_name;
	private String inspection_production_code;
	private String inspection_order_no;
	private String inspection_production_date;
	private String inspection_production_set_no;
	private double inspection_mtr;
	private double dispatch_quantity;
	private String sort_no;
	private Integer roll_no;
	private String remark;
	private String company_name;
	private String financial_year;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private String product_material_id;
	private Integer product_material_unit_id;
	private Integer dispatch_schedule_master_transaction_id;
	private Integer sales_order_details_transaction_id;
	private Integer customer_id;
	private Integer godown_id;
	private Integer weaving_production_inspection_details_id;

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
