package com.erp.SmProductStockAdjustment.Model;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "smv_product_stock_adjustment_details")
public class CSmProductStockAdjustmentDetailsViewModel {

	@Id
	private Integer	stock_adjustment_details_transaction_id;
	private String product_material_code;
	private String product_material_name;
	private String godown_name;
	private String godown_section_name;
	private String godown_section_beans_name;
	private String stock_adjustment_type;
	private double stock_quantity;
	private double stock_weight;
	private double opening_quantity;
	private double opening_weight;
	private double stock_adjustment_quantity;
	private double stock_adjustment_weight;
	private double closing_balance_quantity;
	private double closing_balance_weight;
	private String stock_date;
	private String adjustment_by;
	private String adjustment_reason;
	private String approved_by;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer	company_id;
	private Integer	company_branch_id;
	private String 	financial_year;
	private Integer	stock_adjustment_transaction_id;
	private String 	product_material_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private String goods_receipt_no;
	private String batch_expiry_date;
	private double material_rate;

	
	public boolean isIs_delete() {
		return is_delete;
	}
	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}
	
	
	
}
