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
@Table(name = "mt_sales_order_stock_transfer_details")
public class CMtSalesOrderStockTransferDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sales_order_transfer_details_id;
	private Long sales_order_transfer_id;
	private Long company_id;
	private Long company_branch_id;
	private String goods_receipt_no;
	private Long customer_id;
	private String customer_order_no;
	private Double transfer_quantity;
	private Double transfer_weight;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
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
