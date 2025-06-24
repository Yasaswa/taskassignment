package com.erp.SmProductRmStockDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "smv_product_stock_tracking")
public class CSmProductStockTrackingViewModel {
	@Id
	private Integer stock_transaction_id;
	private String financial_year;
	private String goods_receipt_no;
	private String stock_date;
	private String product_material_name;
	private String product_material_technical_name;
	private String product_type_name;
	private String product_material_purchase_unit_name;
	private Integer stock_quantity;
	private String consumption_no;
	private String consumption_detail_no;
	private String consumption_date;
	private String consumption_location;
	private Integer consumption_quantity;
	private String godown_name;
	private String godown_short_name;
	private String godown_section_name;
	private String godown_section_beans_name;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private String stock_type;
	private Boolean is_stock_consumed;
	private boolean is_active;
	private boolean is_delete;
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
	private Integer product_material_unit_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_material_id;

	// Getters and setters
}


