package com.erp.MtSalesOrderMasterServices.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mt_sales_order_details_services")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesOrderDetailsServicesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_order_details_transaction_id")
	private int sales_order_details_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer sales_order_master_transaction_id;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String financial_year;
	private String customer_order_no;
	private String customer_order_Date;
	private Integer product_type_id;
	private String product_type;
	private String product_material_id;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private Integer sr_no;
	private Integer so_sr_no;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Integer product_material_hsn_code_id;
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
	private String sales_order_item_status;
	private String sales_quotation_no;
	private String sales_quotation_date;
	private String dispatch_note_nos;
	private String dispatch_challan_nos;
	private String invoice_nos;
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

	public String getMaterial_schedule_date() {
		return material_schedule_date;
	}

	public void setMaterial_schedule_date(String material_schedule_date) {
		if (material_schedule_date == null || material_schedule_date.isEmpty()) {
			this.material_schedule_date = null;
		} else {
			this.material_schedule_date = material_schedule_date;

		}
	}

	public String getSales_quotation_date() {
		return sales_quotation_date;
	}

	public void setSales_quotation_date(String sales_quotation_date) {
		if (sales_quotation_date == null || sales_quotation_date.isEmpty()) {
			this.sales_quotation_date = null;
		} else {
			this.sales_quotation_date = sales_quotation_date;

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
