package com.erp.SmProductSr.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from  smv_product_sr_rpt_summary")
public class CSmProductSrRptModel_Not_Used {

	@Id
	private String company_name;
	private String company_branch_name;
	private String product_sr_id;
	private String product_sr_code;
	private String product_sr_name;
	private String process_duration;
	private String product_sr_print_name;
	private String product_sr_short_name;
	private String product_sr_tech_spect;
	private String product_type_group;
	private String product_type_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_sr_hsn_sac_code;
	private String product_sr_std_price;
	private String product_sr_std_profit_percent;
	private String product_sr_std_discount_percent;
	private String product_sr_std_hours;
	private String product_type_id;
	private String product_category1_id;
	private String product_category2_id;
	private String product_category3_id;
	private String product_category4_id;
	private String product_category5_id;
	private String product_sr_hsn_sac_code_id;
	private String product_sr_sales_unit_id;
	private String product_sr_bar_code;
	private String product_sr_qr_code;


}
