package com.erp.FinishGoods.SmProductFgSupplier.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sm_product_fg_supplier")
public class CSmProductFgSupplierModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_fg_supplier_id")
	private int product_fg_supplier_id;
	private String product_fg_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer supplier_id;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public int getProduct_fg_supplier_id() {
		return product_fg_supplier_id;
	}

	public void setProduct_fg_supplier_id(int product_fg_supplier_id) {
		this.product_fg_supplier_id = product_fg_supplier_id;
	}

	public String getProduct_fg_id() {
		return product_fg_id;
	}

	public void setProduct_fg_id(String product_fg_id) {
		this.product_fg_id = product_fg_id;
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

	public Integer getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public CSmProductFgSupplierModel(int product_fg_supplier_id, String product_fg_id, Integer company_id,
	                                 Integer company_branch_id, Integer supplier_id, String remark, boolean is_active, boolean is_delete,
	                                 String created_by, Date created_on, String modified_by, Date modified_on, String deleted_by,
	                                 Date deleted_on) {
		super();
		this.product_fg_supplier_id = product_fg_supplier_id;
		this.product_fg_id = product_fg_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.supplier_id = supplier_id;
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

	public CSmProductFgSupplierModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
