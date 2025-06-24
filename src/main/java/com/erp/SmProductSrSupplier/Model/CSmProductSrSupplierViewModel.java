package com.erp.SmProductSrSupplier.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_sr_supplier")
public class CSmProductSrSupplierViewModel {

	@Id
	private Integer supplier_id;
	private String supplier_name;
	private String supp_branch_payment_terms;
	private String supp_branch_rating;
	private String city_name;
	private String state_name;
	private String supp_branch_phone_no;
	private String supp_branch_cell_no;
	private String supp_branch_EmailId;
	private String supp_branch_website;
	private String supp_branch_gst_no;
	private String product_sr_name;
	private String process_duration;
	private String product_sr_tech_spect;
	private String product_sr_hsn_sac_code;
	private String product_type_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_sr_id;
	private String contract_id;
	private String contract_start_date;
	private String contract_end_date;
	private String contract_rate;
	private String purchase_order_no1;
	private String purchase_order_date1;
	private String purchase_order_rate1;
	private String purchase_order_no2;
	private String purchase_order_date2;
	private String purchase_order_rate2;
	private String purchase_order_no3;
	private String purchase_order_date3;
	private String purchase_order_rate3;
	private Integer company_id;
	private Integer field_id;
	private String field_name;

}
