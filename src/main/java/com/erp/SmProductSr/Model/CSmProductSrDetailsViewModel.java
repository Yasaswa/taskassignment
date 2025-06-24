package com.erp.SmProductSr.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("Select * From smv_product_sr_details")
public class CSmProductSrDetailsViewModel {

	@Id
	private String product_sr_id;
	private String company_name;
	private String company_branch_name;
	private String product_sr_code;
	private String product_sr_name;
	private String product_type_group;
	private String process_duration;
	private String product_type_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_sr_short_name;
	private String product_sr_print_name;
	private String product_sr_tech_spect;
	private double product_sr_std_price;
	private double product_sr_std_hours;
	private double product_sr_std_profit_percent;
	private double product_sr_std_discount_percent;
	private String product_sr_hsn_sac_code;
	private String product_sr_hsn_sac_rate;
	private String product_sr_sales_unit_name;
	private String product_sr_bar_code;
	private String product_sr_qr_code;
	private String product_category1_short_name;
	private String product_type_short_name;
	private String product_sr_hsn_sac_type;
	private boolean product_sr_hsn_sac_is_exampted;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String company_id;
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


}
