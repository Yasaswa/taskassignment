package com.erp.MtSalesOrderMasterTrading.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from  mtv_sales_order_schedules_trading")
public class CMtSalesOrderSchedulesTradingViewModel {

	@Id
	private int sales_order_schedules_transaction_id;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String product_type;
	private String product_material_name;
	private String product_material_tech_spect;
	private String product_material_stock_unit_name;
	private Double product_material_std_weight;
	private String product_material_technical_name;
	private String product_material_make_name;
	private String product_material_type_name;
	private String product_material_grade_name;
	private String product_material_shape_name;
	private Double material_quantity;
	private Double material_weight;
	private Double product_material_schedule_quantity;
	private Double product_material_schedule_weight;
	private String expected_schedule_date;
	private Double product_material_issue_quantity;
	private Double product_material_issue_weight;
	private String material_issue_date;
	private String sales_order_schedules_trading_item_status;
	private String sales_order_schedules_trading_item_status_desc;
	private String so_sr_no;
	private String remark;
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
	private Integer sales_order_master_transaction_id;
	private Integer sales_order_details_transaction_id;
	private Integer product_type_id;
	private String product_material_id;
	private Integer product_material_unit_id;


}
