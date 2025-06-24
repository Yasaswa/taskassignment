package com.erp.Product_Grade.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
public class CProductGradeRptModel {

	@Id
	private String product_grade_id;
	private String company_name;
	private String product_grade;
	private String product_grade_short_name;
	private String product_type_id;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private String product_material_type_id;
	private String product_material_type_name;
	private String remark;
	private String is_active;
	private String is_delete;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String field_name;
	private String field_id;

	public String getProduct_grade_id() {
		return product_grade_id;
	}

	public void setProduct_grade_id(String product_grade_id) {
		this.product_grade_id = product_grade_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getProduct_grade() {
		return product_grade;
	}

	public void setProduct_grade(String product_grade) {
		this.product_grade = product_grade;
	}

	public String getProduct_grade_short_name() {
		return product_grade_short_name;
	}

	public void setProduct_grade_short_name(String product_grade_short_name) {
		this.product_grade_short_name = product_grade_short_name;
	}

	public String getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(String product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getProduct_type_name() {
		return product_type_name;
	}

	public void setProduct_type_name(String product_type_name) {
		this.product_type_name = product_type_name;
	}

	public String getProduct_type_short_name() {
		return product_type_short_name;
	}

	public void setProduct_type_short_name(String product_type_short_name) {
		this.product_type_short_name = product_type_short_name;
	}

	public String getProduct_type_group() {
		return product_type_group;
	}

	public void setProduct_type_group(String product_type_group) {
		this.product_type_group = product_type_group;
	}

	public String getProduct_material_type_id() {
		return product_material_type_id;
	}

	public void setProduct_material_type_id(String product_material_type_id) {
		this.product_material_type_id = product_material_type_id;
	}

	public String getProduct_material_type_name() {
		return product_material_type_name;
	}

	public void setProduct_material_type_name(String product_material_type_name) {
		this.product_material_type_name = product_material_type_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public String getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(String deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getField_id() {
		return field_id;
	}

	public void setField_id(String field_id) {
		this.field_id = field_id;
	}

	public CProductGradeRptModel(String product_grade_id, String company_name, String product_grade,
	                             String product_grade_short_name, String product_type_id, String product_type_name,
	                             String product_type_short_name, String product_type_group, String product_material_type_id,
	                             String product_material_type_name, String remark, String is_active, String is_delete, String created_on,
	                             String modified_by, String modified_on, String deleted_by, String deleted_on, String company_id,
	                             String field_name, String field_id) {
		super();
		this.product_grade_id = product_grade_id;
		this.company_name = company_name;
		this.product_grade = product_grade;
		this.product_grade_short_name = product_grade_short_name;
		this.product_type_id = product_type_id;
		this.product_type_name = product_type_name;
		this.product_type_short_name = product_type_short_name;
		this.product_type_group = product_type_group;
		this.product_material_type_id = product_material_type_id;
		this.product_material_type_name = product_material_type_name;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_id = company_id;
		this.field_name = field_name;
		this.field_id = field_id;
	}

	public CProductGradeRptModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
