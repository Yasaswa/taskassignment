package com.erp.RawMaterial.SmProductRmQaMapping.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_rm_qa_mapping")
public class CSmProductRmQaMappingViewModel {

	@Id
	private int product_qa_parameters_id;
	private String product_qa_type;
	private String product_qa_parameters_name;
	private String product_qa_parameters_short_name;
	private String product_qa_parameters_desc;
	private Double product_rm_qa_from_range;
	private Double product_rm_qa_to_range;
	private Double product_rm_qa_from_deviation_percent;
	private Double product_rm_qa_to_deviation_percent;
	private String product_rm_name;
	private String product_rm_technical_name;
	private String product_rm_oem_part_code;
	private String product_rm_our_part_code;
	private String product_rm_drawing_no;
	private String product_rm_hsn_sac_code;
	private String product_type_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_make_name;
	private String product_material_type_name;
	private String product_material_grade_name;
	private Integer product_type_id;
	private String product_rm_id;
	private int company_id;
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

	public Double getProduct_rm_qa_from_range() {
		return product_rm_qa_from_range;
	}

	public void setProduct_rm_qa_from_range(Double product_rm_qa_from_range) {
		this.product_rm_qa_from_range = product_rm_qa_from_range;
	}

	public Double getProduct_rm_qa_to_range() {
		return product_rm_qa_to_range;
	}

	public void setProduct_rm_qa_to_range(Double product_rm_qa_to_range) {
		this.product_rm_qa_to_range = product_rm_qa_to_range;
	}

	public Double getProduct_rm_qa_from_deviation_percent() {
		return product_rm_qa_from_deviation_percent;
	}

	public void setProduct_rm_qa_from_deviation_percent(Double product_rm_qa_from_deviation_percent) {
		this.product_rm_qa_from_deviation_percent = product_rm_qa_from_deviation_percent;
	}

	public Double getProduct_rm_qa_to_deviation_percent() {
		return product_rm_qa_to_deviation_percent;
	}

	public void setProduct_rm_qa_to_deviation_percent(Double product_rm_qa_to_deviation_percent) {
		this.product_rm_qa_to_deviation_percent = product_rm_qa_to_deviation_percent;
	}

	public String getProduct_rm_name() {
		return product_rm_name;
	}

	public void setProduct_rm_name(String product_rm_name) {
		this.product_rm_name = product_rm_name;
	}

	public String getProduct_rm_technical_name() {
		return product_rm_technical_name;
	}

	public void setProduct_rm_technical_name(String product_rm_technical_name) {
		this.product_rm_technical_name = product_rm_technical_name;
	}

	public String getProduct_rm_oem_part_code() {
		return product_rm_oem_part_code;
	}

	public void setProduct_rm_oem_part_code(String product_rm_oem_part_code) {
		this.product_rm_oem_part_code = product_rm_oem_part_code;
	}

	public String getProduct_rm_our_part_code() {
		return product_rm_our_part_code;
	}

	public void setProduct_rm_our_part_code(String product_rm_our_part_code) {
		this.product_rm_our_part_code = product_rm_our_part_code;
	}

	public String getProduct_rm_drawing_no() {
		return product_rm_drawing_no;
	}

	public void setProduct_rm_drawing_no(String product_rm_drawing_no) {
		this.product_rm_drawing_no = product_rm_drawing_no;
	}

	public String getProduct_rm_hsn_sac_code() {
		return product_rm_hsn_sac_code;
	}

	public void setProduct_rm_hsn_sac_code(String product_rm_hsn_sac_code) {
		this.product_rm_hsn_sac_code = product_rm_hsn_sac_code;
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

	public String getProduct_make_name() {
		return product_make_name;
	}

	public void setProduct_make_name(String product_make_name) {
		this.product_make_name = product_make_name;
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

	public Integer getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getProduct_rm_id() {
		return product_rm_id;
	}

	public void setProduct_rm_id(String product_rm_id) {
		this.product_rm_id = product_rm_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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

	public CSmProductRmQaMappingViewModel(int product_qa_parameters_id, String product_qa_type,
	                                      String product_qa_parameters_name, String product_qa_parameters_short_name,
	                                      String product_qa_parameters_desc, Double product_rm_qa_from_range, Double product_rm_qa_to_range,
	                                      Double product_rm_qa_from_deviation_percent, Double product_rm_qa_to_deviation_percent,
	                                      String product_rm_name, String product_rm_technical_name, String product_rm_oem_part_code,
	                                      String product_rm_our_part_code, String product_rm_drawing_no, String product_rm_hsn_sac_code,
	                                      String product_type_name, String product_category1_name, String product_category2_name,
	                                      String product_category3_name, String product_category4_name, String product_category5_name,
	                                      String product_make_name, String product_material_type_name, String product_material_grade_name,
	                                      Integer product_type_id, String product_rm_id, int company_id, Integer field_id, String field_name) {
		super();
		this.product_qa_parameters_id = product_qa_parameters_id;
		this.product_qa_type = product_qa_type;
		this.product_qa_parameters_name = product_qa_parameters_name;
		this.product_qa_parameters_short_name = product_qa_parameters_short_name;
		this.product_qa_parameters_desc = product_qa_parameters_desc;
		this.product_rm_qa_from_range = product_rm_qa_from_range;
		this.product_rm_qa_to_range = product_rm_qa_to_range;
		this.product_rm_qa_from_deviation_percent = product_rm_qa_from_deviation_percent;
		this.product_rm_qa_to_deviation_percent = product_rm_qa_to_deviation_percent;
		this.product_rm_name = product_rm_name;
		this.product_rm_technical_name = product_rm_technical_name;
		this.product_rm_oem_part_code = product_rm_oem_part_code;
		this.product_rm_our_part_code = product_rm_our_part_code;
		this.product_rm_drawing_no = product_rm_drawing_no;
		this.product_rm_hsn_sac_code = product_rm_hsn_sac_code;
		this.product_type_name = product_type_name;
		this.product_category1_name = product_category1_name;
		this.product_category2_name = product_category2_name;
		this.product_category3_name = product_category3_name;
		this.product_category4_name = product_category4_name;
		this.product_category5_name = product_category5_name;
		this.product_make_name = product_make_name;
		this.product_material_type_name = product_material_type_name;
		this.product_material_grade_name = product_material_grade_name;
		this.product_type_id = product_type_id;
		this.product_rm_id = product_rm_id;
		this.company_id = company_id;
		this.field_id = field_id;
		this.field_name = field_name;
	}

	public CSmProductRmQaMappingViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
