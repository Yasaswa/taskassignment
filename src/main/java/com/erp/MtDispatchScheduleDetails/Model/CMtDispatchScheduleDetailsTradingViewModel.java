package com.erp.MtDispatchScheduleDetails.Model;

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
@Subselect("select * from mtv_dispatch_schedule_details_trading")
public class CMtDispatchScheduleDetailsTradingViewModel {

	@Id
	private int dispatch_schedule_details_transaction_id;
	private String dispatch_schedule_type;
	private String dispatch_schedule_creation_type;
	private String dispatch_schedule_creation_type_desc;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private Integer dispatch_schedule_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String so_sr_no;
	private String batch_no;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private Integer sr_no;
	private String sales_order_no;
	private Integer sales_order_version;
	private String sales_order_date;
	private String product_type;
//	private String product_type_group;
//	private String product_rm_name;
//	private String product_rm_tech_spect;
//	private String product_material_stock_unit_name;
//	private Double product_material_std_weight;
//	private String product_material_technical_name;
	private String product_material_hsn_code;
//	private String product_material_packing_name;
	private Double product_material_hsn_rate;
	private Double material_quantity;
	private Double material_weight;
	private Double material_rate;
	private Double so_rate;
	private Double material_basic_amount;
	private Double material_discount_percent;
	private Double material_discount_amount;
	private Double material_taxable_amount;
	private Double material_cgst_percent;
	private Double material_cgst_total;
	private Double material_sgst_percent;
	private Double material_sgst_total;
	private Double material_igst_percent;
	private Double material_igst_total;
	private Double material_total_amount;
	private String material_schedule_date;
	private Double expected_dispatch_quantity;
	private Double expected_dispatch_weight;
	private Double actual_dispatch_quantity;
	private Double actual_dispatch_weight;
	private Double dispatch_return_quantity;
	private Double dispatch_return_weight;
	private Double pending_quantity;
	private Double pending_weight;
	private Date expected_schedule_date;
	private Integer delayed_days;
	private Double dispatched_quantity;
	private Double dispatched_weight;
	private Double invoice_quantity;
	private Double invoice_weight;
	private String dispatch_schedule_item_status;
	private String dispatch_schedule_remark;
	private Date approved_date;
	private Double total_quantity;
	private Double total_weight;
	private Double actual_quantity;
	private Double actual_weight;
	private String dispatch_note_status;
	private String dispatch_note_remark;
	private String other_terms_conditions;
	private String remark;
	private String lot_no;
	private String count_no;
	private String set_no;
	private String roll_no;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer product_material_hsn_code_id;
	private String product_material_id;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Integer dispatch_schedule_type_id;
	private Integer dispatch_schedule_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer sales_order_details_transaction_id;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer consignee_id;
//	private Integer consignee_state_id;
//	private Integer consignee_city_id;
	private Integer dispatch_supervisor_id;
	private Integer approved_by_id;
//	private String product_material_name;
	private String product_material_code;
	private Integer agent_id;
	private String agent_name;
	private String agent_address1;
	private String agent_city_name;
	private String agent_state_name;
	private String consi_customer_name;
	private String consi_branch_address1;
	private String consi_city_name;
	private String consi_state_name;


}
