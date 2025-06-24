package com.erp.MtSalesOrderMasterServices.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "mt_sales_order_schedules_services")
public class CMtSalesOrderSchedulesServicesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_order_schedules_transaction_id")
	private int sales_order_schedules_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
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
	private Integer so_sr_no;
	private Integer product_material_unit_id;
	private String process_duration;
	private Double product_sr_std_hours;
	private String expected_schedule_start_date;
	private String expected_schedule_end_date;
	private String services_plan_id;
	private String services_plan_date;
	private String services_provided_date;
	private String sales_order_schedules_services_item_status;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public String getCustomer_order_Date() {
		return customer_order_Date;
	}

	public void setCustomer_order_Date(String customer_order_Date) {

		if (customer_order_Date == null || customer_order_Date.isEmpty()) {
			this.customer_order_Date = null;
		} else {
			this.customer_order_Date = customer_order_Date;
		}

	}

	public String getExpected_schedule_start_date() {
		return expected_schedule_start_date;
	}

	public void setExpected_schedule_start_date(String expected_schedule_start_date) {

		if (expected_schedule_start_date == null || expected_schedule_start_date.isEmpty()) {
			this.expected_schedule_start_date = null;
		} else {
			this.expected_schedule_start_date = expected_schedule_start_date;
		}
	}

	public String getExpected_schedule_end_date() {
		return expected_schedule_end_date;
	}

	public void setExpected_schedule_end_date(String expected_schedule_end_date) {

		if (expected_schedule_end_date == null || expected_schedule_end_date.isEmpty()) {
			this.expected_schedule_end_date = null;
		} else {
			this.expected_schedule_end_date = expected_schedule_end_date;
		}

	}

	public String getServcies_plan_date() {
		return services_plan_date;
	}

	public void setServcies_plan_date(String services_plan_date) {

		if (services_plan_date == null || services_plan_date.isEmpty()) {
			this.services_plan_date = null;
		} else {
			this.services_plan_date = services_plan_date;
		}
	}

	public String getServcies_provided_date() {
		return services_provided_date;
	}

	public void setServcies_provided_date(String services_provided_date) {

		if (services_provided_date == null || services_provided_date.isEmpty()) {
			this.services_provided_date = null;
		} else {
			this.services_provided_date = services_provided_date;
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
