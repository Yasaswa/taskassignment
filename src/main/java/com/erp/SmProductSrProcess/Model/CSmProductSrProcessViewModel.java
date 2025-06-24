package com.erp.SmProductSrProcess.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_sr_process")
public class CSmProductSrProcessViewModel {

	@Id
	private int product_process_id;
	private String product_process_name;
	private String product_parent_process_name;
	private double product_process_std_scrap_percent;
	private double product_process_std_production_hrs;
	private String product_sr_name;
	private String process_duration;
	private double product_sr_process_rate_per_duration;
	private String product_sr_tech_spect;
	private String product_sr_hsn_sac_code;
	private String product_type_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_sr_id;
	private Integer company_id;
	private Integer product_parent_process_id;
	private Integer field_id;
	private String field_name;


}

	


