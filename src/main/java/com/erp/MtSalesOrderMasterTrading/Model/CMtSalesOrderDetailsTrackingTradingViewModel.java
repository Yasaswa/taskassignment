package com.erp.MtSalesOrderMasterTrading.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  mtv_sales_order_details_tracking_trading")
public class CMtSalesOrderDetailsTrackingTradingViewModel {

	@Id
	private int sales_order_details_transaction_id;
	private String company_name;
	private String company_branch_name;
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
	private String product_material_print_name;
	private Integer sr_no;
	private String so_sr_no;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Integer product_material_hsn_code_id;
	private double material_quantity;
	private double material_weight;
	private double quotation_rate;
	private double material_rate;
	private String material_schedule_date;
	private String sales_order_item_status;
	private String sales_order_item_status_desc;
	private double pending_quantity;
	private double pending_weight;
	private double opening_quantity;
	private double opening_weight;
	private double reserve_quantity;
	private double reserve_weight;
	private double excess_quantity;
	private double excess_weight;
	private double pree_closed_quantity;
	private double pree_closed_weight;
	private double purchase_quantity;
	private double purchase_weight;
	private double purchase_return_quantity;
	private double purchase_return_weight;
	private double purchase_rejection_quantity;
	private double purchase_rejection_weight;
	private double jobcard_quantity;
	private double jobcard_weight;
	private double production_issue_quantity;
	private double production_issue_weight;
	private double production_issue_return_quantity;
	private double production_issue_return_weight;
	private double production_issue_rejection_quantity;
	private double production_issue_rejection_weight;
	private double production_quantity;
	private double production_weight;
	private double production_return_quantity;
	private double production_return_weight;
	private double production_rejection_quantity;
	private double production_rejection_weight;
	private double assembly_production_issue_quantity;
	private double assembly_production_issue_weight;
	private double sales_quantity;
	private double sales_weight;
	private double sales_return_quantity;
	private double sales_return_weight;
	private double sales_rejection_quantity;
	private double sales_rejection_weight;
	private double transfer_issue_quantity;
	private double transfer_issue_weight;
	private double transfer_receipt_quantity;
	private double transfer_receipt_weight;
	private double outsources_out_quantity;
	private double outsources_out_weight;
	private double outsources_in_quantity;
	private double outsources_in_weight;
	private double outsources_rejection_quantity;
	private double outsources_rejection_weight;
	private double loan_receipt_quantity;
	private double loan_receipt_weight;
	private double loan_issue_quantity;
	private double loan_issue_weight;
	private double cancel_quantity;
	private double cancel_weight;
	private double difference_quantity;
	private double difference_weight;
	private String remark;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer sales_order_master_transaction_id;
	private Integer product_type_id;
	private String product_material_id;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Date deleted_on;
	private String deleted_by;

}
