package com.erp.MtSalesOrderStockTransfer.Model;

import lombok.*;
import org.hibernate.annotations.Immutable;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Getter
@Setter
@Entity
@Table(name = "mtv_sales_order_stock_transfer_details")
public class CMtSalesOrderStockTransferDetailsViewModel {
	@Id
	private Integer sales_order_transfer_details_id;
	private Integer sales_order_transfer_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String goods_receipt_no;
	private String customer_name;
	private Integer customer_id;
	private String customer_order_no;
	private double transfer_quantity;
	private double transfer_weight;
	private String godown_name;
	private String godown_section_name;
	private String godown_section_beans_name;
	private String sales_order_no;
	private String 	sales_order_date;
	private String overall_schedule_date;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String product_material_id;
	private Double weight_per_box_item;
	private String product_material_unit_name;
	private String product_material_code;
	private String count_type;

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
