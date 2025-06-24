package com.erp.SmProductSr.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from smv_product_sr")
public class CSmProductSrViewModel {

	@Id
	private int product_id;
	private String product_sr_id;
	private String company_name;
	private String company_branch_name;
	private String product_sr_code;
	private String product_sr_name;
	private String product_sr_tech_spect;
	private Double product_sr_std_price;
	private Double product_sr_std_hours;
	private Double product_sr_std_profit_percent;
	private String process_duration;
	private Double product_sr_std_discount_percent;
	private String product_type_group;
	private String product_type_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_sr_short_name;
	private String product_sr_print_name;
	private String product_sr_hsn_sac_code;
	private String product_sr_hsn_sac_rate;
	private String product_sr_sales_unit_name;
	private String product_sr_bar_code;
	private String product_sr_qr_code;
	private String product_sr_hsn_sac_type;
	private Boolean product_sr_hsn_sac_is_exampted = Boolean.FALSE;
	private String remark;
	private String Active;
	private String Deleted;
	private Boolean is_active;
	private Boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private String deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer product_type_id;
	private Integer product_category1_id;
	private Integer product_category2_id;
	private Integer product_category3_id;
	private Integer product_category4_id;
	private Integer product_category5_id;
	private Integer product_sr_hsn_sac_code_id;
	private Integer product_sr_sales_unit_id;
	private String field_name;
	private Integer field_id;


//	private Integer profit_center_id;
//	private Integer cost_center_id;

	
	public Boolean getProduct_sr_hsn_sac_is_exampted() {
		return product_sr_hsn_sac_is_exampted;
	}

	public void setProduct_sr_hsn_sac_is_exampted(Boolean product_sr_hsn_sac_is_exampted) {
		this.product_sr_hsn_sac_is_exampted = product_sr_hsn_sac_is_exampted;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}
}
