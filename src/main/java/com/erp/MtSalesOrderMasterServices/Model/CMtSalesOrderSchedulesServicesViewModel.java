package com.erp.MtSalesOrderMasterServices.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Entity
@Subselect("Select * From mtv_sales_order_schedules_services")
public class CMtSalesOrderSchedulesServicesViewModel {

	@Id
	private int sales_order_schedules_transaction_id;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String sales_order_schedules_services_item_status_desc;
	private String customer_order_no;
	private String customer_order_date;
	private String product_type;
	private String product_sr_name;
	private String product_sr_tech_spect;
	private String process_duration;
	private Double product_sr_std_hours;
	private String expected_schedule_start_date;
	private String expected_schedule_end_date;
	private String services_plan_id;
	private String services_plan_date;
	private String services_provided_date;
	private String sales_order_schedules_services_item_status;
	private String so_sr_no;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer customer_id;
	private Integer company_branch_id;
	private Integer sales_order_master_transaction_id;
	private Integer sales_order_details_transaction_id;
	private Integer product_type_id;
	private String product_material_id;
	private Integer product_material_unit_id;

}
