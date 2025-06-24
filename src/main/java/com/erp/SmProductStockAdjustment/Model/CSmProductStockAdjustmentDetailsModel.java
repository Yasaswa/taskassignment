package com.erp.SmProductStockAdjustment.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sm_product_stock_adjustment_details")
public class CSmProductStockAdjustmentDetailsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_adjustment_details_transaction_id")
	private Integer stock_adjustment_details_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer stock_adjustment_transaction_id;
	private String 	product_material_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
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
	private String goods_receipt_no;
	private String batch_expiry_date;
	@Transient
	private Integer prev_godown_section_id;
	@Transient
	private Integer prev_godown_section_beans_id;
	@Transient
	private Integer product_type_id;
	private double material_rate;


	public void setBatch_expiry_date(String batch_expiry_date) {
		if (batch_expiry_date == null || batch_expiry_date.isEmpty()) {
			this.batch_expiry_date = null;
		} else {
			this.batch_expiry_date = batch_expiry_date;
		}
	}

	public String getBatch_expiry_date() {
		return batch_expiry_date;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}
}
