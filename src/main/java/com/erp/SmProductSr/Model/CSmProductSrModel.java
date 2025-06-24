package com.erp.SmProductSr.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sm_product_sr")
public class CSmProductSrModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int product_id;
	private String product_sr_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer product_type_id;
	private Integer product_category1_id;
	private Integer product_category2_id;
	private Integer product_category3_id;
	private Integer product_category4_id;
	private Integer product_category5_id;
	private String process_duration;
	private String product_sr_code;
	private String product_sr_name;
	private String product_sr_short_name;
	private String product_sr_print_name;
	private String product_sr_tech_spect;
	private Integer product_sr_hsn_sac_code_id;
	private double product_sr_std_price;
	private double product_sr_std_hours;
	private double product_sr_std_profit_percent;
	private double product_sr_std_discount_percent;
	private Integer product_sr_sales_unit_id;
	private String product_sr_bar_code;
	private String product_sr_qr_code;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

//	private Integer profit_center_id;
//	private Integer cost_center_id;

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
