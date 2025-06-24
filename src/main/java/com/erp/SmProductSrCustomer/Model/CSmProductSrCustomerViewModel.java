package com.erp.SmProductSrCustomer.Model;

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
@Subselect("select * from  smv_product_sr_customer")
public class CSmProductSrCustomerViewModel {

	@Id
	private int product_sr_customer_id;
	private String product_sr_id;
	private String product_sr_name;
	private String process_duration;
	private String product_sr_tech_spect;
	private String customer_name;
	private String cust_branch_phone_no;
	private String cust_branch_cell_no;
	private String cust_branch_EmailId;
	private String cust_branch_website;
	private String cust_branch_gst_no;
	private String cust_branch_payment_terms;
	private String cust_branch_rating;
	private String city_name;
	private String state_name;
	private String contract_id;
	private String contract_start_date;
	private String contract_end_date;
	private String contract_rate;
	private String sales_order_no1;
	private String sales_order_date1;
	private String sales_order_rate1;
	private String sales_order_no2;
	private String sales_order_date2;
	private String sales_order_rate2;
	private String sales_order_no3;
	private String sales_order_date3;
	private String sales_order_rate3;
	private String product_sr_hsn_sac_code;
	private String product_type_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private Integer customer_id;
	private Integer company_id;
	private Integer field_id;
	private String field_name;

}
