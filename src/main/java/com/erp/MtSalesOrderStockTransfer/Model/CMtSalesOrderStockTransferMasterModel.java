package com.erp.MtSalesOrderStockTransfer.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mt_sales_order_stock_transfer_master")
public class CMtSalesOrderStockTransferMasterModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sales_order_transfer_id;
	private Long company_id;
	private Long company_branch_id;
	private String financial_year;
	private Long sales_order_transfer_type_id;
	private String sales_order_transfer_type;
	private String sales_order_transfer_no;
	private String sales_order_transfer_date;
	private Long to_customer_id;
	private String to_customer_order_no;
	private String product_material_id;
	private String remark;
	private Boolean is_active = Boolean.TRUE;
	private Boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public void setis_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean getis_delete() {
		return is_delete;
	}

	public void setis_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}
}
