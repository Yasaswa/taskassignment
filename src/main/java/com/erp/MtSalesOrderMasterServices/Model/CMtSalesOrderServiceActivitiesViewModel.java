package com.erp.MtSalesOrderMasterServices.Model;

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
@Immutable
@Entity
@Subselect("Select * From mtv_sales_order_service_activities")
public class CMtSalesOrderServiceActivitiesViewModel {

	@Id
	private int  sales_order_activity_transaction_id;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String product_material_name;
	private String product_material_activity_name;
	private String product_material_activity_description;
	private double product_sr_activity_std_hour;
	private String from_range;
	private String to_range;
	private String remark;
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
	private String customer_name;
	private String company_name;
	private String company_branch_name;
	private Integer sales_order_master_transaction_id;
	private Integer product_activity_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer customer_id;
	private String product_material_id;
	
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
