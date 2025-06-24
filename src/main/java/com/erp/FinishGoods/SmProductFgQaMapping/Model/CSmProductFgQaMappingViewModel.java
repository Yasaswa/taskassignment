package com.erp.FinishGoods.SmProductFgQaMapping.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_fg_qa_mapping")
public class CSmProductFgQaMappingViewModel {

	@Id
	private int product_qa_parameters_id;
	private String product_qa_type;
	private String product_qa_parameters_name;
	private String product_qa_parameters_short_name;
	private String product_qa_parameters_desc;
	private Double product_fg_qa_from_range;
	private Double product_fg_qa_to_range;
	private Double product_fg_qa_from_deviation_percent;
	private Double product_fg_qa_to_deviation_percent;
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
	private Integer product_type_id;
	private String product_fg_id;
	private Integer field_id;
	private String field_name;

	public int getproduct_qa_parameters_id() {
		return product_qa_parameters_id;
	}

	public void setproduct_qa_parameters_id(int product_qa_parameters_id) {
		this.product_qa_parameters_id = product_qa_parameters_id;
	}

	public String getproduct_qa_type() {
		return product_qa_type;
	}

	public void setproduct_qa_type(String product_qa_type) {
		this.product_qa_type = product_qa_type;
	}

	public String getproduct_qa_parameters_name() {
		return product_qa_parameters_name;
	}

	public void setproduct_qa_parameters_name(String product_qa_parameters_name) {
		this.product_qa_parameters_name = product_qa_parameters_name;
	}

	public String getproduct_qa_parameters_short_name() {
		return product_qa_parameters_short_name;
	}

	public void setproduct_qa_parameters_short_name(String product_qa_parameters_short_name) {
		this.product_qa_parameters_short_name = product_qa_parameters_short_name;
	}

	public String getproduct_qa_parameters_desc() {
		return product_qa_parameters_desc;
	}

	public void setproduct_qa_parameters_desc(String product_qa_parameters_desc) {
		this.product_qa_parameters_desc = product_qa_parameters_desc;
	}

	public Double getproduct_fg_qa_from_range() {
		return product_fg_qa_from_range;
	}

	public void setproduct_fg_qa_from_range(Double product_fg_qa_from_range) {
		this.product_fg_qa_from_range = product_fg_qa_from_range;
	}

	public Double getproduct_fg_qa_to_range() {
		return product_fg_qa_to_range;
	}

	public void setproduct_fg_qa_to_range(Double product_fg_qa_to_range) {
		this.product_fg_qa_to_range = product_fg_qa_to_range;
	}

	public Double getproduct_fg_qa_from_deviation_percent() {
		return product_fg_qa_from_deviation_percent;
	}

	public void setproduct_fg_qa_from_deviation_percent(Double product_fg_qa_from_deviation_percent) {
		this.product_fg_qa_from_deviation_percent = product_fg_qa_from_deviation_percent;
	}

	public Double getproduct_fg_qa_to_deviation_percent() {
		return product_fg_qa_to_deviation_percent;
	}

	public void setproduct_fg_qa_to_deviation_percent(Double product_fg_qa_to_deviation_percent) {
		this.product_fg_qa_to_deviation_percent = product_fg_qa_to_deviation_percent;
	}

	public String getproduct_fg_name() {
		return product_fg_name;
	}

	public void setproduct_fg_name(String product_fg_name) {
		this.product_fg_name = product_fg_name;
	}

	public String getproduct_fg_technical_name() {
		return product_fg_technical_name;
	}

	public void setproduct_fg_technical_name(String product_fg_technical_name) {
		this.product_fg_technical_name = product_fg_technical_name;
	}

	public String getproduct_fg_oem_part_code() {
		return product_fg_oem_part_code;
	}

	public void setproduct_fg_oem_part_code(String product_fg_oem_part_code) {
		this.product_fg_oem_part_code = product_fg_oem_part_code;
	}

	public String getproduct_fg_our_part_code() {
		return product_fg_our_part_code;
	}

	public void setproduct_fg_our_part_code(String product_fg_our_part_code) {
		this.product_fg_our_part_code = product_fg_our_part_code;
	}

	public String getproduct_fg_drawing_no() {
		return product_fg_drawing_no;
	}

	public void setproduct_fg_drawing_no(String product_fg_drawing_no) {
		this.product_fg_drawing_no = product_fg_drawing_no;
	}

	public String getproduct_fg_hsn_sac_code() {
		return product_fg_hsn_sac_code;
	}

	public void setproduct_fg_hsn_sac_code(String product_fg_hsn_sac_code) {
		this.product_fg_hsn_sac_code = product_fg_hsn_sac_code;
	}

	public String getproduct_type_name() {
		return product_type_name;
	}

	public void setproduct_type_name(String product_type_name) {
		this.product_type_name = product_type_name;
	}

	public String getproduct_category1_name() {
		return product_category1_name;
	}

	public void setproduct_category1_name(String product_category1_name) {
		this.product_category1_name = product_category1_name;
	}

	public String getproduct_category2_name() {
		return product_category2_name;
	}

	public void setproduct_category2_name(String product_category2_name) {
		this.product_category2_name = product_category2_name;
	}

	public String getproduct_category3_name() {
		return product_category3_name;
	}

	public void setproduct_category3_name(String product_category3_name) {
		this.product_category3_name = product_category3_name;
	}

	public String getproduct_category4_name() {
		return product_category4_name;
	}

	public void setproduct_category4_name(String product_category4_name) {
		this.product_category4_name = product_category4_name;
	}

	public String getproduct_category5_name() {
		return product_category5_name;
	}

	public void setproduct_category5_name(String product_category5_name) {
		this.product_category5_name = product_category5_name;
	}

	public String getproduct_material_type_name() {
		return product_material_type_name;
	}

	public void setproduct_material_type_name(String product_material_type_name) {
		this.product_material_type_name = product_material_type_name;
	}

	public String getproduct_material_grade_name() {
		return product_material_grade_name;
	}

	public void setproduct_material_grade_name(String product_material_grade_name) {
		this.product_material_grade_name = product_material_grade_name;
	}

	public Integer getproduct_type_id() {
		return product_type_id;
	}

	public void setproduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getproduct_fg_id() {
		return product_fg_id;
	}

	public void setproduct_fg_id(String product_fg_id) {
		this.product_fg_id = product_fg_id;
	}

	public Integer getfield_id() {
		return field_id;
	}

	public void setfield_id(Integer field_id) {
		this.field_id = field_id;
	}

	public String getfield_name() {
		return field_name;
	}

	public void setfield_name(String field_name) {
		this.field_name = field_name;
	}

	public CSmProductFgQaMappingViewModel(int product_qa_parameters_id, String product_qa_type,
	                                      String product_qa_parameters_name, String product_qa_parameters_short_name,
	                                      String product_qa_parameters_desc, Double product_fg_qa_from_range, Double product_fg_qa_to_range,
	                                      Double product_fg_qa_from_deviation_percent, Double product_fg_qa_to_deviation_percent,
	                                      String product_fg_name, String product_fg_technical_name, String product_fg_oem_part_code,
	                                      String product_fg_our_part_code, String product_fg_drawing_no, String product_fg_hsn_sac_code,
	                                      String product_type_name, String product_category1_name, String product_category2_name,
	                                      String product_category3_name, String product_category4_name, String product_category5_name,
	                                      String product_material_type_name, String product_material_grade_name, Integer product_type_id,
	                                      String product_fg_id, Integer field_id, String field_name) {
		super();
		this.product_qa_parameters_id = product_qa_parameters_id;
		this.product_qa_type = product_qa_type;
		this.product_qa_parameters_name = product_qa_parameters_name;
		this.product_qa_parameters_short_name = product_qa_parameters_short_name;
		this.product_qa_parameters_desc = product_qa_parameters_desc;
		this.product_fg_qa_from_range = product_fg_qa_from_range;
		this.product_fg_qa_to_range = product_fg_qa_to_range;
		this.product_fg_qa_from_deviation_percent = product_fg_qa_from_deviation_percent;
		this.product_fg_qa_to_deviation_percent = product_fg_qa_to_deviation_percent;
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
		this.product_type_id = product_type_id;
		this.product_fg_id = product_fg_id;
		this.field_id = field_id;
		this.field_name = field_name;
	}

	public CSmProductFgQaMappingViewModel() {
		super();
	}

}
