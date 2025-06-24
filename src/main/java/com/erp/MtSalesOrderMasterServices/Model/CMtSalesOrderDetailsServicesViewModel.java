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
@Subselect(value = "Select * From mtv_sales_order_details_services")
public class CMtSalesOrderDetailsServicesViewModel {

	@Id
	private int sales_order_details_transaction_id;
	private String sales_order_type;
	private String sales_order_life_desc;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String department_name;
	private String sales_order_status_desc;
	private String sales_order_acceptance_status_desc;
	private String sales_order_mail_sent_status_desc;
	private String customer_name;
	private String customer_state_name;
	private String consignee_name;
	private String consignee_state_name;
	private String customer_order_no;
	private String customer_order_Date;
	private String product_type;
	private String product_sr_name;
	private String product_sr_tech_spect;
	private String product_sr_std_price;
	private String product_sr_std_hours;
	private String product_sr_std_profit_percent;
	private String process_duration;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private Integer sr_no;
	private Integer so_sr_no;
	private String product_material_unit_name;
	private String product_material_hsn_sac_code;
	private Double product_material_hsn_sac_rate;
	private Double quotation_rate;
	private Double material_rate;
	private Double so_rate;
	private Double material_quantity;
	private Double material_weight;
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
	private String sales_order_creation_type;
	private String sales_order_item_status_desc;
	private String sales_order_item_status;
	private String sales_order_status;
	private String sales_quotation_no;
	private String sales_quotation_date;
	private String dispatch_note_nos;
	private String dispatch_challan_nos;
	private String invoice_nos;
	private String sales_order_life;
	private String company_name;
	private String company_branch_name;
	private String company_address1;
	private String company_address2;
	private String company_phone_no;
	private String company_cell_no;
	private String company_EmailId;
	private String company_website;
	private String company_gst_no;
	private String company_pan_no;
	private String company_state;
	private String company_pincode;
	private String financial_year;
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
	private Integer company_id;
	private Integer company_branch_id;
	private Integer sales_order_master_transaction_id;
	private Integer product_type_id;
	private String product_material_id;
	private Integer product_material_unit_id;
	private Integer product_material_hsn_code_id;
	private Integer product_material_packing_id;


}
