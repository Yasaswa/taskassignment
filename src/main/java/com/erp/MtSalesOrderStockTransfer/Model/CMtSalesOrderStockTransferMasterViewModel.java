package com.erp.MtSalesOrderStockTransfer.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "mtv_sales_order_stock_transfer_master")
public class CMtSalesOrderStockTransferMasterViewModel {
	@Id
	private Integer sales_order_transfer_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer sales_order_transfer_type_id;
	private String sales_order_transfer_type;
	private String sales_order_transfer_no;
	private Date sales_order_transfer_date;
	private String customer_name;
	private Integer to_customer_id;
	private String to_customer_order_no;
	private String product_material_name;
	private String product_material_id;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String Active;
	private String Deleted;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

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
