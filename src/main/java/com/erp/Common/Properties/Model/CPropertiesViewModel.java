package com.erp.Common.Properties.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Immutable
@Table(name = "amv_properties")
public class CPropertiesViewModel {

	@Id
	private long property_id;
	private Integer company_id;
	private String properties_master_name;
	private String property_name;
	private String property_value;
	private String Active;
	private String Deleted;
	private Boolean is_active;
	private Boolean is_delete;
	private String created_on;
	private String created_by;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private long property_master_id;
	private long field_id;
	private String field_name;
	private String remark;

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}

}
