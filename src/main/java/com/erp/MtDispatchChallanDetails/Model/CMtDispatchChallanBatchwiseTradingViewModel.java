package com.erp.MtDispatchChallanDetails.Model;

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
@Entity
@Immutable
@Subselect("select * from mtv_dispatch_challan_batchwise_trading")
public class CMtDispatchChallanBatchwiseTradingViewModel {

	@Id
	private int dispatch_challan__batchwise_transaction_id;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private String dispatch_challan_batch_status_desc;
	private String sales_order_no;
	private String sales_order_Date;
	private String sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private Integer dispatch_schedule_version;
	private String customer_name;
	private String customer_state_name;
	private String consignee_name;
	private String consignee_state_name;
	private String so_sr_no;
	private String batch_no;
//	private String product_type_group;
	private String product_material_name;
	private double batch_dispatch_quantity;
	private double batch_dispatch_weight;
	private Integer batch_dispatch_roll;
	private String dispatch_challan_batch_status;
	private String dispatch_challan_batch_remark;
	private String goods_receipt_no;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String customer_email;
	private String customer_city_name;
	private String consignee_email;
	private String consignee_city_name;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer dispatch_challan_details_transaction_id;
	private Integer dispatch_challan_master_transaction_id;
	private Integer company_branch_id;
	private String product_material_id;

}
