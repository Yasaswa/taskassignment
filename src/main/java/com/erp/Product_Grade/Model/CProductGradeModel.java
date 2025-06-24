package com.erp.Product_Grade.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sm_product_grade")
public class CProductGradeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_grade_id")
	private int product_grade_id;
	private Integer company_id;
	private Integer product_type_id;
	private Integer product_material_type_id;
	private String product_grade_name;
	private String product_grade_short_name;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String created_by;
	private String modified_by;
	private String deleted_by;

	public int getProduct_grade_id() {
		return product_grade_id;
	}

	public void setProduct_grade_id(int product_grade_id) {
		this.product_grade_id = product_grade_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}

	public Integer getProduct_material_type_id() {
		return product_material_type_id;
	}

	public void setProduct_material_type_id(Integer product_material_type_id) {
		this.product_material_type_id = product_material_type_id;
	}

	public String getProduct_grade_name() {
		return product_grade_name;
	}

	public void setProduct_grade_name(String product_grade_name) {
		this.product_grade_name = product_grade_name;
	}

	public String getProduct_grade_short_name() {
		return product_grade_short_name;
	}

	public void setProduct_grade_short_name(String product_grade_short_name) {
		this.product_grade_short_name = product_grade_short_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public CProductGradeModel(int product_grade_id, Integer company_id, Integer product_type_id,
	                          Integer product_material_type_id, String product_grade_name, String product_grade_short_name, String remark,
	                          boolean is_active, boolean is_delete, Date created_on, Date modified_on, Date deleted_on, String created_by,
	                          String modified_by, String deleted_by) {
		super();
		this.product_grade_id = product_grade_id;
		this.company_id = company_id;
		this.product_type_id = product_type_id;
		this.product_material_type_id = product_material_type_id;
		this.product_grade_name = product_grade_name;
		this.product_grade_short_name = product_grade_short_name;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_on = created_on;
		this.modified_on = modified_on;
		this.deleted_on = deleted_on;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.deleted_by = deleted_by;
	}

	public CProductGradeModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
