package com.erp.MtSalesOrderMasterTrading.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mt_sales_order_schedules_trading")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesOrderSchedulesTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_order_schedules_transaction_id")
	private int sales_order_schedules_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer sales_order_master_transaction_id;
	private Integer sales_order_details_transaction_id;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private Integer product_type_id;
	private String product_type;
	private String product_material_id;
	private Integer product_material_unit_id;
	private double material_quantity;
	private double material_weight;
	private double product_material_schedule_quantity;
	private double product_material_schedule_weight;
	private String expected_schedule_date;
	private double product_material_issue_quantity;
	private double product_material_issue_weight;
	private String material_issue_date;
	private String sales_order_schedules_trading_item_status;
	private String remark;
	private String so_sr_no;
	private boolean is_active;
	private boolean is_delete;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;


	public String getSales_order_date() {
		return sales_order_date;
	}


	public void setSales_order_date(String sales_order_date) {
		if (sales_order_date == null || sales_order_date.isEmpty()) {
			this.sales_order_date = null;
		} else {
			this.sales_order_date = sales_order_date;
		}
	}


	public void setCustomer_order_Date(String customer_order_Date) {
		if (customer_order_Date == null || customer_order_Date.isEmpty()) {
			this.customer_order_Date = null;
		} else {
			this.customer_order_Date = customer_order_Date;
		}
	}

	public String getExpected_schedule_date() {
		return expected_schedule_date;
	}


	public void setExpected_schedule_date(String expected_schedule_date) {
		if (expected_schedule_date == null || expected_schedule_date.isEmpty()) {
			this.expected_schedule_date = null;
		} else {
			this.expected_schedule_date = expected_schedule_date;
		}
	}


	public String getMaterial_issue_date() {
		return material_issue_date;
	}


	public void setMaterial_issue_date(String material_issue_date) {
		if (material_issue_date == null || material_issue_date.isEmpty()) {
			this.material_issue_date = null;
		} else {
			this.material_issue_date = material_issue_date;
		}
	}

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
