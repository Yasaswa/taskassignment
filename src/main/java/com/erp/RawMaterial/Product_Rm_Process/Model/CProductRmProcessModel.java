package com.erp.RawMaterial.Product_Rm_Process.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sm_product_rm_process")
public class CProductRmProcessModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_rm_process_id")
	private int product_rm_process_id;
	private String product_rm_id;
	private int product_process_id;
	private double product_rm_process_std_scrap_percent;
	private double product_rm_process_std_production_hrs;
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
	private int company_id;
	private int company_branch_id;


	public int getProduct_rm_process_id() {
		return product_rm_process_id;
	}

	public void setProduct_rm_process_id(int product_rm_process_id) {
		this.product_rm_process_id = product_rm_process_id;
	}

	public String getProduct_rm_id() {
		return product_rm_id;
	}

	public void setProduct_rm_id(String product_rm_id) {
		this.product_rm_id = product_rm_id;
	}

	public int getProduct_process_id() {
		return product_process_id;
	}

	public void setProduct_process_id(int product_process_id) {
		this.product_process_id = product_process_id;
	}

	public double getProduct_rm_process_std_scrap_percent() {
		return product_rm_process_std_scrap_percent;
	}

	public void setProduct_rm_process_std_scrap_percent(double product_rm_process_std_scrap_percent) {
		this.product_rm_process_std_scrap_percent = product_rm_process_std_scrap_percent;
	}

	public double getProduct_rm_process_std_production_hrs() {
		return product_rm_process_std_production_hrs;
	}

	public void setProduct_rm_process_std_production_hrs(double product_rm_process_std_production_hrs) {
		this.product_rm_process_std_production_hrs = product_rm_process_std_production_hrs;
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

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(int company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public CProductRmProcessModel(int product_rm_process_id, String product_rm_id, int product_process_id,
	                              double product_rm_process_std_scrap_percent, double product_rm_process_std_production_hrs, String remark,
	                              boolean is_active, boolean is_delete, Date created_on, Date modified_on, Date deleted_on, String created_by,
	                              String modified_by, String deleted_by, int company_id, int company_branch_id) {
		super();
		this.product_rm_process_id = product_rm_process_id;
		this.product_rm_id = product_rm_id;
		this.product_process_id = product_process_id;
		this.product_rm_process_std_scrap_percent = product_rm_process_std_scrap_percent;
		this.product_rm_process_std_production_hrs = product_rm_process_std_production_hrs;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_on = created_on;
		this.modified_on = modified_on;
		this.deleted_on = deleted_on;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.deleted_by = deleted_by;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
	}

	public CProductRmProcessModel() {
		super();
	}


}
