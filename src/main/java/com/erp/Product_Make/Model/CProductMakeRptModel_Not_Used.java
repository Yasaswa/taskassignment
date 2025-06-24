package com.erp.Product_Make.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
public class CProductMakeRptModel_Not_Used {
	@Id
	private String product_make_id;
	private String company_name;
	private String company_id;
	private String product_type_id;
	private String product_make_name;
	private String product_make_short_name;
	private String remark;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String field_name;
	private String field_id;

	public String getProduct_make_id() {
		return product_make_id;
	}

	public void setProduct_make_id(String product_make_id) {
		this.product_make_id = product_make_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(String product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getProduct_make_name() {
		return product_make_name;
	}

	public void setProduct_make_name(String product_make_name) {
		this.product_make_name = product_make_name;
	}

	public String getProduct_make_short_name() {
		return product_make_short_name;
	}

	public void setProduct_make_short_name(String product_make_short_name) {
		this.product_make_short_name = product_make_short_name;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
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

	public CProductMakeRptModel_Not_Used(String product_make_id, String company_name, String company_id, String product_type_id,
	                                     String product_make_name, String product_make_short_name, String remark, String is_active, String is_delete,
	                                     String created_by, String created_on, String modified_by, String modified_on, String deleted_by,
	                                     String deleted_on, String field_name, String field_id) {
		super();
		this.product_make_id = product_make_id;
		this.company_name = company_name;
		this.company_id = company_id;
		this.product_type_id = product_type_id;
		this.product_make_name = product_make_name;
		this.product_make_short_name = product_make_short_name;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.field_name = field_name;
		this.field_id = field_id;
	}

	public CProductMakeRptModel_Not_Used() {
		super();
		// TODO Auto-generated constructor stub
	}


}
