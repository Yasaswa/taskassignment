package com.erp.RawMaterial.Product_Rm_Technical.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sm_product_rm_technical")
public class CProductRmTechnicalModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_rm_technical_id")
	private int product_rm_technical_id;
	private String product_rm_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_rm_technical_name;
	private Integer product_category2_id;
	private Integer product_category3_id;
	private Integer product_category4_id;
	private Integer product_category5_id;
	private Integer product_make_id;
	private Integer product_material_type_id;
	private Integer product_material_grade_id;
	private Integer product_material_shape_id;
	private String product_material_colour;
	private String product_alternate_rm_id;
	private double assembly_scrap_percent;
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

	public int getProduct_rm_technical_id() {
		return product_rm_technical_id;
	}

	public void setProduct_rm_technical_id(int product_rm_technical_id) {
		this.product_rm_technical_id = product_rm_technical_id;
	}

	public String getProduct_rm_id() {
		return product_rm_id;
	}

	public void setProduct_rm_id(String product_rm_id) {
		this.product_rm_id = product_rm_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getProduct_rm_technical_name() {
		return product_rm_technical_name;
	}

	public void setProduct_rm_technical_name(String product_rm_technical_name) {
		this.product_rm_technical_name = product_rm_technical_name;
	}

	public Integer getProduct_category2_id() {
		return product_category2_id;
	}

	public void setProduct_category2_id(Integer product_category2_id) {
		this.product_category2_id = product_category2_id;
	}

	public Integer getProduct_category3_id() {
		return product_category3_id;
	}

	public void setProduct_category3_id(Integer product_category3_id) {
		this.product_category3_id = product_category3_id;
	}

	public Integer getProduct_category4_id() {
		return product_category4_id;
	}

	public void setProduct_category4_id(Integer product_category4_id) {
		this.product_category4_id = product_category4_id;
	}

	public Integer getProduct_category5_id() {
		return product_category5_id;
	}

	public void setProduct_category5_id(Integer product_category5_id) {
		this.product_category5_id = product_category5_id;
	}

	public Integer getProduct_make_id() {
		return product_make_id;
	}

	public void setProduct_make_id(Integer product_make_id) {
		this.product_make_id = product_make_id;
	}

	public Integer getProduct_material_type_id() {
		return product_material_type_id;
	}

	public void setProduct_material_type_id(Integer product_material_type_id) {
		this.product_material_type_id = product_material_type_id;
	}

	public Integer getProduct_material_grade_id() {
		return product_material_grade_id;
	}

	public void setProduct_material_grade_id(Integer product_material_grade_id) {
		this.product_material_grade_id = product_material_grade_id;
	}

	public Integer getProduct_material_shape_id() {
		return product_material_shape_id;
	}

	public void setProduct_material_shape_id(Integer product_material_shape_id) {
		this.product_material_shape_id = product_material_shape_id;
	}

	public String getProduct_material_colour() {
		return product_material_colour;
	}

	public void setProduct_material_colour(String product_material_colour) {
		this.product_material_colour = product_material_colour;
	}

	public String getProduct_alternate_rm_id() {
		return product_alternate_rm_id;
	}

	public void setProduct_alternate_rm_id(String product_alternate_rm_id) {
		this.product_alternate_rm_id = product_alternate_rm_id;
	}

	public double getAssembly_scrap_percent() {
		return assembly_scrap_percent;
	}

	public void setAssembly_scrap_percent(double assembly_scrap_percent) {
		this.assembly_scrap_percent = assembly_scrap_percent;
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

	public CProductRmTechnicalModel(int product_rm_technical_id, String product_rm_id, Integer company_id,
	                                Integer company_branch_id, String product_rm_technical_name, Integer product_category2_id,
	                                Integer product_category3_id, Integer product_category4_id, Integer product_category5_id,
	                                Integer product_make_id, Integer product_material_type_id, Integer product_material_grade_id,
	                                Integer product_material_shape_id, String product_material_colour, String product_alternate_rm_id,
	                                double assembly_scrap_percent, String remark, boolean is_active, boolean is_delete, Date created_on,
	                                Date modified_on, Date deleted_on, String created_by, String modified_by, String deleted_by) {
		super();
		this.product_rm_technical_id = product_rm_technical_id;
		this.product_rm_id = product_rm_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.product_rm_technical_name = product_rm_technical_name;
		this.product_category2_id = product_category2_id;
		this.product_category3_id = product_category3_id;
		this.product_category4_id = product_category4_id;
		this.product_category5_id = product_category5_id;
		this.product_make_id = product_make_id;
		this.product_material_type_id = product_material_type_id;
		this.product_material_grade_id = product_material_grade_id;
		this.product_material_shape_id = product_material_shape_id;
		this.product_material_colour = product_material_colour;
		this.product_alternate_rm_id = product_alternate_rm_id;
		this.assembly_scrap_percent = assembly_scrap_percent;
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

	public CProductRmTechnicalModel() {
		super();
	}


}
