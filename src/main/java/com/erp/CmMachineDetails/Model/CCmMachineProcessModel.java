package com.erp.CmMachineDetails.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_machine_process")
public class CCmMachineProcessModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "machine_process_id")
	private int machine_process_id;
	private Integer company_id;
	private Integer machine_id;
	private Integer product_process_id;
	private double product_process_std_production_perhrs;
	private double product_process_std_production_perday;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public int getMachine_process_id() {
		return machine_process_id;
	}

	public void setMachine_process_id(int machine_process_id) {
		this.machine_process_id = machine_process_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(Integer machine_id) {
		this.machine_id = machine_id;
	}

	public Integer getProduct_process_id() {
		return product_process_id;
	}

	public void setProduct_process_id(Integer product_process_id) {
		this.product_process_id = product_process_id;
	}

	public double getProduct_process_std_production_perhrs() {
		return product_process_std_production_perhrs;
	}

	public void setProduct_process_std_production_perhrs(double product_process_std_production_perhrs) {
		this.product_process_std_production_perhrs = product_process_std_production_perhrs;
	}

	public double getProduct_process_std_production_perday() {
		return product_process_std_production_perday;
	}

	public void setProduct_process_std_production_perday(double product_process_std_production_perday) {
		this.product_process_std_production_perday = product_process_std_production_perday;
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

	public CCmMachineProcessModel(int machine_process_id, Integer company_id, Integer machine_id,
	                              Integer product_process_id, double product_process_std_production_perhrs,
	                              double product_process_std_production_perday, String remark, boolean is_active, boolean is_delete,
	                              String created_by, Date created_on, String modified_by, Date modified_on, String deleted_by,
	                              Date deleted_on) {
		super();
		this.machine_process_id = machine_process_id;
		this.company_id = company_id;
		this.machine_id = machine_id;
		this.product_process_id = product_process_id;
		this.product_process_std_production_perhrs = product_process_std_production_perhrs;
		this.product_process_std_production_perday = product_process_std_production_perday;
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

	public CCmMachineProcessModel() {
		super();
	}

}
