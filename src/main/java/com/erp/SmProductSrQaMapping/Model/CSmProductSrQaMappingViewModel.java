package com.erp.SmProductSrQaMapping.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_sr_qa_mapping")
public class CSmProductSrQaMappingViewModel {

	@Id
	private int product_qa_parameters_id;
	private String product_qa_type;
	private String product_qa_parameters_name;
	private String product_qa_parameters_short_name;
	private String product_qa_parameters_desc;
	private double product_sr_qa_from_range;
	private double product_sr_qa_to_range;
	private double product_sr_qa_from_deviation_percent;
	private double product_sr_qa_to_deviation_percent;
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
	private Integer company_id;
	private String product_sr_id;
	private Integer field_id;
	private String field_name;

	public int getProduct_qa_parameters_id() {
		return product_qa_parameters_id;
	}

	public void setProduct_qa_parameters_id(int product_qa_parameters_id) {
		this.product_qa_parameters_id = product_qa_parameters_id;
	}

	public String getProduct_qa_type() {
		return product_qa_type;
	}

	public void setProduct_qa_type(String product_qa_type) {
		this.product_qa_type = product_qa_type;
	}

	public String getProduct_qa_parameters_name() {
		return product_qa_parameters_name;
	}

	public void setProduct_qa_parameters_name(String product_qa_parameters_name) {
		this.product_qa_parameters_name = product_qa_parameters_name;
	}

	public String getProduct_qa_parameters_short_name() {
		return product_qa_parameters_short_name;
	}

	public void setProduct_qa_parameters_short_name(String product_qa_parameters_short_name) {
		this.product_qa_parameters_short_name = product_qa_parameters_short_name;
	}

	public String getProduct_qa_parameters_desc() {
		return product_qa_parameters_desc;
	}

	public void setProduct_qa_parameters_desc(String product_qa_parameters_desc) {
		this.product_qa_parameters_desc = product_qa_parameters_desc;
	}

	public double getProduct_sr_qa_from_range() {
		return product_sr_qa_from_range;
	}

	public void setProduct_sr_qa_from_range(double product_sr_qa_from_range) {
		this.product_sr_qa_from_range = product_sr_qa_from_range;
	}

	public double getProduct_sr_qa_to_range() {
		return product_sr_qa_to_range;
	}

	public void setProduct_sr_qa_to_range(double product_sr_qa_to_range) {
		this.product_sr_qa_to_range = product_sr_qa_to_range;
	}

	public double getProduct_sr_qa_from_deviation_percent() {
		return product_sr_qa_from_deviation_percent;
	}

	public void setProduct_sr_qa_from_deviation_percent(double product_sr_qa_from_deviation_percent) {
		this.product_sr_qa_from_deviation_percent = product_sr_qa_from_deviation_percent;
	}

	public double getProduct_sr_qa_to_deviation_percent() {
		return product_sr_qa_to_deviation_percent;
	}

	public void setProduct_sr_qa_to_deviation_percent(double product_sr_qa_to_deviation_percent) {
		this.product_sr_qa_to_deviation_percent = product_sr_qa_to_deviation_percent;
	}

	public String getProduct_sr_name() {
		return product_sr_name;
	}

	public void setProduct_sr_name(String product_sr_name) {
		this.product_sr_name = product_sr_name;
	}

	public String getProduct_sr_tech_spect() {
		return product_sr_tech_spect;
	}

	public void setProduct_sr_tech_spect(String product_sr_tech_spect) {
		this.product_sr_tech_spect = product_sr_tech_spect;
	}

	public String getProduct_sr_hsn_sac_code() {
		return product_sr_hsn_sac_code;
	}

	public void setProduct_sr_hsn_sac_code(String product_sr_hsn_sac_code) {
		this.product_sr_hsn_sac_code = product_sr_hsn_sac_code;
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

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getProduct_sr_id() {
		return product_sr_id;
	}

	public void setProduct_sr_id(String product_sr_id) {
		this.product_sr_id = product_sr_id;
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

	@Override
	public String toString() {
		return "CSmProductSrQaMappingViewModel [product_qa_parameters_id=" + product_qa_parameters_id
				+ ", product_qa_type=" + product_qa_type + ", product_qa_parameters_name=" + product_qa_parameters_name
				+ ", product_qa_parameters_short_name=" + product_qa_parameters_short_name
				+ ", product_qa_parameters_desc=" + product_qa_parameters_desc + ", product_sr_qa_from_range="
				+ product_sr_qa_from_range + ", product_sr_qa_to_range=" + product_sr_qa_to_range
				+ ", product_sr_qa_from_deviation_percent=" + product_sr_qa_from_deviation_percent
				+ ", product_sr_qa_to_deviation_percent=" + product_sr_qa_to_deviation_percent + ", product_sr_name="
				+ product_sr_name + ", product_sr_tech_spect=" + product_sr_tech_spect + ", product_sr_hsn_sac_code="
				+ product_sr_hsn_sac_code + ", product_type_name=" + product_type_name + ", product_category1_name="
				+ product_category1_name + ", product_category2_name=" + product_category2_name
				+ ", product_category3_name=" + product_category3_name + ", product_category4_name="
				+ product_category4_name + ", product_category5_name=" + product_category5_name + ", company_id="
				+ company_id + ", product_sr_id=" + product_sr_id + ", field_id=" + field_id + ", field_name="
				+ field_name + "]";
	}

	public CSmProductSrQaMappingViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
