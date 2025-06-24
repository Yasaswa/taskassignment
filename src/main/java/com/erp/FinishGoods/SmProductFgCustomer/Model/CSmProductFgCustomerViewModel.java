package com.erp.FinishGoods.SmProductFgCustomer.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_fg_customer")
public class CSmProductFgCustomerViewModel {

	@Id
	private Integer customer_id;
	private int company_id;
	private String customer_name;
	private String cust_branch_payment_terms;
	private String cust_branch_rating;
	private String city_name;
	private String state_name;
	private String cust_branch_phone_no;
	private String cust_branch_cell_no;
	private String cust_branch_EmailId;
	private String cust_branch_website;
	private String cust_branch_gst_no;
	private String product_fg_name;
	private String product_fg_technical_name;
	private String product_fg_oem_part_code;
	private String product_fg_our_part_code;
	private String product_fg_drawing_no;
	private String product_fg_hsn_sac_code;
	private String product_type_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_material_type_name;
	private String product_material_grade_name;
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
	private String product_fg_id;
	private Integer field_id;
	private String field_name;

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCust_branch_payment_terms() {
		return cust_branch_payment_terms;
	}

	public void setCust_branch_payment_terms(String cust_branch_payment_terms) {
		this.cust_branch_payment_terms = cust_branch_payment_terms;
	}

	public String getCust_branch_rating() {
		return cust_branch_rating;
	}

	public void setCust_branch_rating(String cust_branch_rating) {
		this.cust_branch_rating = cust_branch_rating;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getCust_branch_phone_no() {
		return cust_branch_phone_no;
	}

	public void setCust_branch_phone_no(String cust_branch_phone_no) {
		this.cust_branch_phone_no = cust_branch_phone_no;
	}

	public String getCust_branch_cell_no() {
		return cust_branch_cell_no;
	}

	public void setCust_branch_cell_no(String cust_branch_cell_no) {
		this.cust_branch_cell_no = cust_branch_cell_no;
	}

	public String getCust_branch_EmailId() {
		return cust_branch_EmailId;
	}

	public void setCust_branch_EmailId(String cust_branch_EmailId) {
		this.cust_branch_EmailId = cust_branch_EmailId;
	}

	public String getCust_branch_website() {
		return cust_branch_website;
	}

	public void setCust_branch_website(String cust_branch_website) {
		this.cust_branch_website = cust_branch_website;
	}

	public String getCust_branch_gst_no() {
		return cust_branch_gst_no;
	}

	public void setCust_branch_gst_no(String cust_branch_gst_no) {
		this.cust_branch_gst_no = cust_branch_gst_no;
	}

	public String getProduct_fg_name() {
		return product_fg_name;
	}

	public void setProduct_fg_name(String product_fg_name) {
		this.product_fg_name = product_fg_name;
	}

	public String getProduct_fg_technical_name() {
		return product_fg_technical_name;
	}

	public void setProduct_fg_technical_name(String product_fg_technical_name) {
		this.product_fg_technical_name = product_fg_technical_name;
	}

	public String getProduct_fg_oem_part_code() {
		return product_fg_oem_part_code;
	}

	public void setProduct_fg_oem_part_code(String product_fg_oem_part_code) {
		this.product_fg_oem_part_code = product_fg_oem_part_code;
	}

	public String getProduct_fg_our_part_code() {
		return product_fg_our_part_code;
	}

	public void setProduct_fg_our_part_code(String product_fg_our_part_code) {
		this.product_fg_our_part_code = product_fg_our_part_code;
	}

	public String getProduct_fg_drawing_no() {
		return product_fg_drawing_no;
	}

	public void setProduct_fg_drawing_no(String product_fg_drawing_no) {
		this.product_fg_drawing_no = product_fg_drawing_no;
	}

	public String getProduct_fg_hsn_sac_code() {
		return product_fg_hsn_sac_code;
	}

	public void setProduct_fg_hsn_sac_code(String product_fg_hsn_sac_code) {
		this.product_fg_hsn_sac_code = product_fg_hsn_sac_code;
	}

	public String getProduct_type_name() {
		return product_type_name;
	}

	public void setProduct_type_name(String product_type_name) {
		this.product_type_name = product_type_name;
	}

	public String getProduct_category1_name() {
		return product_category1_name;
	}

	public void setProduct_category1_name(String product_category1_name) {
		this.product_category1_name = product_category1_name;
	}

	public String getProduct_category2_name() {
		return product_category2_name;
	}

	public void setProduct_category2_name(String product_category2_name) {
		this.product_category2_name = product_category2_name;
	}

	public String getProduct_category3_name() {
		return product_category3_name;
	}

	public void setProduct_category3_name(String product_category3_name) {
		this.product_category3_name = product_category3_name;
	}

	public String getProduct_category4_name() {
		return product_category4_name;
	}

	public void setProduct_category4_name(String product_category4_name) {
		this.product_category4_name = product_category4_name;
	}

	public String getProduct_category5_name() {
		return product_category5_name;
	}

	public void setProduct_category5_name(String product_category5_name) {
		this.product_category5_name = product_category5_name;
	}

	public String getProduct_material_type_name() {
		return product_material_type_name;
	}

	public void setProduct_material_type_name(String product_material_type_name) {
		this.product_material_type_name = product_material_type_name;
	}

	public String getProduct_material_grade_name() {
		return product_material_grade_name;
	}

	public void setProduct_material_grade_name(String product_material_grade_name) {
		this.product_material_grade_name = product_material_grade_name;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	public String getContract_start_date() {
		return contract_start_date;
	}

	public void setContract_start_date(String contract_start_date) {
		this.contract_start_date = contract_start_date;
	}

	public String getContract_end_date() {
		return contract_end_date;
	}

	public void setContract_end_date(String contract_end_date) {
		this.contract_end_date = contract_end_date;
	}

	public String getContract_rate() {
		return contract_rate;
	}

	public void setContract_rate(String contract_rate) {
		this.contract_rate = contract_rate;
	}

	public String getSales_order_no1() {
		return sales_order_no1;
	}

	public void setSales_order_no1(String sales_order_no1) {
		this.sales_order_no1 = sales_order_no1;
	}

	public String getSales_order_date1() {
		return sales_order_date1;
	}

	public void setSales_order_date1(String sales_order_date1) {
		this.sales_order_date1 = sales_order_date1;
	}

	public String getSales_order_rate1() {
		return sales_order_rate1;
	}

	public void setSales_order_rate1(String sales_order_rate1) {
		this.sales_order_rate1 = sales_order_rate1;
	}

	public String getSales_order_no2() {
		return sales_order_no2;
	}

	public void setSales_order_no2(String sales_order_no2) {
		this.sales_order_no2 = sales_order_no2;
	}

	public String getSales_order_date2() {
		return sales_order_date2;
	}

	public void setSales_order_date2(String sales_order_date2) {
		this.sales_order_date2 = sales_order_date2;
	}

	public String getSales_order_rate2() {
		return sales_order_rate2;
	}

	public void setSales_order_rate2(String sales_order_rate2) {
		this.sales_order_rate2 = sales_order_rate2;
	}

	public String getSales_order_no3() {
		return sales_order_no3;
	}

	public void setSales_order_no3(String sales_order_no3) {
		this.sales_order_no3 = sales_order_no3;
	}

	public String getSales_order_date3() {
		return sales_order_date3;
	}

	public void setSales_order_date3(String sales_order_date3) {
		this.sales_order_date3 = sales_order_date3;
	}

	public String getSales_order_rate3() {
		return sales_order_rate3;
	}

	public void setSales_order_rate3(String sales_order_rate3) {
		this.sales_order_rate3 = sales_order_rate3;
	}

	public String getProduct_fg_id() {
		return product_fg_id;
	}

	public void setProduct_fg_id(String product_fg_id) {
		this.product_fg_id = product_fg_id;
	}

	public Integer getField_id() {
		return field_id;
	}

	public void setField_id(Integer field_id) {
		this.field_id = field_id;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public CSmProductFgCustomerViewModel(Integer customer_id, int company_id, String customer_name,
	                                     String cust_branch_payment_terms, String cust_branch_rating, String city_name, String state_name,
	                                     String cust_branch_phone_no, String cust_branch_cell_no, String cust_branch_EmailId,
	                                     String cust_branch_website, String cust_branch_gst_no, String product_fg_name,
	                                     String product_fg_technical_name, String product_fg_oem_part_code, String product_fg_our_part_code,
	                                     String product_fg_drawing_no, String product_fg_hsn_sac_code, String product_type_name,
	                                     String product_category1_name, String product_category2_name, String product_category3_name,
	                                     String product_category4_name, String product_category5_name, String product_material_type_name,
	                                     String product_material_grade_name, String contract_id, String contract_start_date,
	                                     String contract_end_date, String contract_rate, String sales_order_no1, String sales_order_date1,
	                                     String sales_order_rate1, String sales_order_no2, String sales_order_date2, String sales_order_rate2,
	                                     String sales_order_no3, String sales_order_date3, String sales_order_rate3, String product_fg_id,
	                                     Integer field_id, String field_name) {
		super();
		this.customer_id = customer_id;
		this.company_id = company_id;
		this.customer_name = customer_name;
		this.cust_branch_payment_terms = cust_branch_payment_terms;
		this.cust_branch_rating = cust_branch_rating;
		this.city_name = city_name;
		this.state_name = state_name;
		this.cust_branch_phone_no = cust_branch_phone_no;
		this.cust_branch_cell_no = cust_branch_cell_no;
		this.cust_branch_EmailId = cust_branch_EmailId;
		this.cust_branch_website = cust_branch_website;
		this.cust_branch_gst_no = cust_branch_gst_no;
		this.product_fg_name = product_fg_name;
		this.product_fg_technical_name = product_fg_technical_name;
		this.product_fg_oem_part_code = product_fg_oem_part_code;
		this.product_fg_our_part_code = product_fg_our_part_code;
		this.product_fg_drawing_no = product_fg_drawing_no;
		this.product_fg_hsn_sac_code = product_fg_hsn_sac_code;
		this.product_type_name = product_type_name;
		this.product_category1_name = product_category1_name;
		this.product_category2_name = product_category2_name;
		this.product_category3_name = product_category3_name;
		this.product_category4_name = product_category4_name;
		this.product_category5_name = product_category5_name;
		this.product_material_type_name = product_material_type_name;
		this.product_material_grade_name = product_material_grade_name;
		this.contract_id = contract_id;
		this.contract_start_date = contract_start_date;
		this.contract_end_date = contract_end_date;
		this.contract_rate = contract_rate;
		this.sales_order_no1 = sales_order_no1;
		this.sales_order_date1 = sales_order_date1;
		this.sales_order_rate1 = sales_order_rate1;
		this.sales_order_no2 = sales_order_no2;
		this.sales_order_date2 = sales_order_date2;
		this.sales_order_rate2 = sales_order_rate2;
		this.sales_order_no3 = sales_order_no3;
		this.sales_order_date3 = sales_order_date3;
		this.sales_order_rate3 = sales_order_rate3;
		this.product_fg_id = product_fg_id;
		this.field_id = field_id;
		this.field_name = field_name;
	}

	public CSmProductFgCustomerViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
