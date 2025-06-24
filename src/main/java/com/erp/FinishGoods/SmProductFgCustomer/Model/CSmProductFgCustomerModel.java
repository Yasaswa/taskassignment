package com.erp.FinishGoods.SmProductFgCustomer.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sm_product_fg_customer")
public class CSmProductFgCustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_fg_customer_id")
	private int product_fg_customer_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_fg_id;
	private Integer customer_id;
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

	public int getproduct_fg_customer_id() {
		return product_fg_customer_id;
	}

	public void setproduct_fg_customer_id(int product_fg_customer_id) {
		this.product_fg_customer_id = product_fg_customer_id;
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

	public Integer getcustomer_id() {
		return customer_id;
	}

	public void setcustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
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

	public CSmProductFgCustomerModel(int product_fg_customer_id, Integer company_id, Integer company_branch_id,
	                                 String product_fg_id, Integer customer_id, String remark, boolean is_active, boolean is_delete,
	                                 String created_by, Date created_on, String modified_by, Date modified_on, String deleted_by,
	                                 Date deleted_on) {
		super();
		this.product_fg_customer_id = product_fg_customer_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.product_fg_id = product_fg_id;
		this.customer_id = customer_id;
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

	public CSmProductFgCustomerModel() {
		super();
	}

}
