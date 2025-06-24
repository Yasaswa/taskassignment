package com.erp.SmProductRmStockDetails.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.swing.text.StyledEditorKit;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "sm_product_stock_tracking")
public class CSmProductStockTracking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_transaction_id")
	private Integer stock_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String product_material_id;
	private Integer product_material_unit_id;
	private String goods_receipt_no;
	private String stock_date;
	private double stock_quantity;
	private String consumption_no;
	private String consumption_detail_no;
	private Date consumption_date;
	private String consumption_location;
	private double consumption_quantity;
	private String stock_type;
	private boolean is_stock_consumed = Boolean.FALSE;
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

	@Transient
	private boolean stockAddOrConsume = Boolean.FALSE; // this is field use for if we want to return the stock which we have consumed


	public boolean isStockAddOrConsume() {
		return stockAddOrConsume;
	}

	public void setStockAddOrConsume(boolean stockAddOrConsume) {
		this.stockAddOrConsume = stockAddOrConsume;
	}
}
