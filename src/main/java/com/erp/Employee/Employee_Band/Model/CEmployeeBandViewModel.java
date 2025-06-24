package com.erp.Employee.Employee_Band.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
@Immutable
@Subselect("select * from cmv_employee_band")
public class CEmployeeBandViewModel {
	@Id
	private int employee_band_id;
	private String employee_band_name;
	private String company_name;
	private String Active;
	private String Deleted;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer field_id;
	private String field_name;

	public int getEmployee_band_id() {
		return employee_band_id;
	}

	public void setEmployee_band_id(int employee_band_id) {
		this.employee_band_id = employee_band_id;
	}

	public String getEmployee_band_name() {
		return employee_band_name;
	}

	public void setEmployee_band_name(String employee_band_name) {
		this.employee_band_name = employee_band_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getActive() {
		return Active;
	}

	public void setActive(String active) {
		Active = active;
	}

	public String getDeleted() {
		return Deleted;
	}

	public void setDeleted(String deleted) {
		Deleted = deleted;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
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

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getField_id() {
		return field_id;
	}

	public void setField_id(Integer field_id) {
		this.field_id = field_id;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public CEmployeeBandViewModel(int employee_band_id, String employee_band_name, String company_name, String active,
	                              String deleted, boolean is_delete, boolean is_active, String created_by, Date created_on,
	                              String modified_by, Date modified_on, String deleted_by, Date deleted_on, Integer company_id,
	                              Integer field_id, String field_name) {
		super();
		this.employee_band_id = employee_band_id;
		this.employee_band_name = employee_band_name;
		this.company_name = company_name;
		Active = active;
		Deleted = deleted;
		this.is_delete = is_delete;
		this.is_active = is_active;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_id = company_id;
		this.field_id = field_id;
		this.field_name = field_name;
	}

	public CEmployeeBandViewModel() {
		super();
	}


}
