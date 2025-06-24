package com.erp.FinishGoods.SmProductFgTechnical.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sm_product_fg_technical")
public class CSmProductFgTechnicalModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_fg_technical_id")
	private int product_fg_technical_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_fg_id;
	private String product_fg_technical_name;
	private Integer product_category2_id;
	private Integer product_category3_id;
	private Integer product_category4_id;
	private Integer product_category5_id;
	private Integer product_material_type_id;
	private Integer product_material_grade_id;
	private Integer product_material_shape_id;
	private String product_material_colour;
	private Integer product_alternate_fg_id;
	private double assembly_scrap_percent;
	private Double product_gsm;
	private Double product_glm;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public int getproduct_fg_technical_id() {
		return product_fg_technical_id;
	}

	public void setproduct_fg_technical_id(int product_fg_technical_id) {
		this.product_fg_technical_id = product_fg_technical_id;
	}

	public Integer getcompany_id() {
		return company_id;
	}

	public void setcompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getcompany_branch_id() {
		return company_branch_id;
	}

	public void setcompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getproduct_fg_id() {
		return product_fg_id;
	}

	public void setproduct_fg_id(String product_fg_id) {
		this.product_fg_id = product_fg_id;
	}

	public String getproduct_fg_technical_name() {
		return product_fg_technical_name;
	}

	public void setproduct_fg_technical_name(String product_fg_technical_name) {
		this.product_fg_technical_name = product_fg_technical_name;
	}

	public Integer getproduct_category2_id() {
		return product_category2_id;
	}

	public void setproduct_category2_id(Integer product_category2_id) {
		this.product_category2_id = product_category2_id;
	}

	public Integer getproduct_category3_id() {
		return product_category3_id;
	}

	public void setproduct_category3_id(Integer product_category3_id) {
		this.product_category3_id = product_category3_id;
	}

	public Integer getproduct_category4_id() {
		return product_category4_id;
	}

	public void setproduct_category4_id(Integer product_category4_id) {
		this.product_category4_id = product_category4_id;
	}

	public Integer getproduct_category5_id() {
		return product_category5_id;
	}

	public void setproduct_category5_id(Integer product_category5_id) {
		this.product_category5_id = product_category5_id;
	}

	public Integer getproduct_material_type_id() {
		return product_material_type_id;
	}

	public void setproduct_material_type_id(Integer product_material_type_id) {
		this.product_material_type_id = product_material_type_id;
	}

	public Integer getproduct_material_grade_id() {
		return product_material_grade_id;
	}

	public void setproduct_material_grade_id(Integer product_material_grade_id) {
		this.product_material_grade_id = product_material_grade_id;
	}

	public Integer getproduct_material_shape_id() {
		return product_material_shape_id;
	}

	public void setproduct_material_shape_id(Integer product_material_shape_id) {
		this.product_material_shape_id = product_material_shape_id;
	}

	public String getproduct_material_colour() {
		return product_material_colour;
	}

	public void setproduct_material_colour(String product_material_colour) {
		this.product_material_colour = product_material_colour;
	}

	public Integer getproduct_alternate_fg_id() {
		return product_alternate_fg_id;
	}

	public void setproduct_alternate_fg_id(Integer product_alternate_fg_id) {
		this.product_alternate_fg_id = product_alternate_fg_id;
	}

	public double getassembly_scrap_percent() {
		return assembly_scrap_percent;
	}

	public void setassembly_scrap_percent(double assembly_scrap_percent) {
		this.assembly_scrap_percent = assembly_scrap_percent;
	}
	
	public Double getproduct_glm() {
		return product_glm;
	}

	public void setproduct_glm(Double product_glm) {
		this.product_glm = product_glm;
	}

	public Double getproduct_gsm() {
		return product_gsm;
	}

	public void setproduct_gsm(Double product_gsm) {
		this.product_gsm = product_gsm;
	}
	
	
	public String getremark() {
		return remark;
	}

	public void setremark(String remark) {
		this.remark = remark;
	}

	public boolean getis_active() {
		return is_active;
	}

	public void setis_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean getis_delete() {
		return is_delete;
	}

	public void setis_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public String getcreated_by() {
		return created_by;
	}

	public void setcreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getcreated_on() {
		return created_on;
	}

	public void setcreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getmodified_by() {
		return modified_by;
	}

	public void setmodified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getmodified_on() {
		return modified_on;
	}

	public void setmodified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getdeleted_by() {
		return deleted_by;
	}

	public void setdeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getdeleted_on() {
		return deleted_on;
	}

	public void setdeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public CSmProductFgTechnicalModel(int product_fg_technical_id, Integer company_id, Integer company_branch_id,
	                                  String product_fg_id, String product_fg_technical_name, Integer product_category2_id,
	                                  Integer product_category3_id, Integer product_category4_id, Integer product_category5_id,
	                                  Integer product_material_type_id, Integer product_material_grade_id, Integer product_material_shape_id,
	                                  String product_material_colour, Integer product_alternate_fg_id, double assembly_scrap_percent, Double product_glm, Double product_gsm,
	                                  String remark, boolean is_active, boolean is_delete, String created_by, Date created_on, String modified_by,
	                                  Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.product_fg_technical_id = product_fg_technical_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.product_fg_id = product_fg_id;
		this.product_fg_technical_name = product_fg_technical_name;
		this.product_category2_id = product_category2_id;
		this.product_category3_id = product_category3_id;
		this.product_category4_id = product_category4_id;
		this.product_category5_id = product_category5_id;
		this.product_material_type_id = product_material_type_id;
		this.product_material_grade_id = product_material_grade_id;
		this.product_material_shape_id = product_material_shape_id;
		this.product_material_colour = product_material_colour;
		this.product_alternate_fg_id = product_alternate_fg_id;
		this.assembly_scrap_percent = assembly_scrap_percent;
		this.product_glm = product_glm;
		this.product_gsm = product_gsm;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
	}

	public CSmProductFgTechnicalModel() {
		super();
	}

}
