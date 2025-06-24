package com.erp.SmProductTypeAccordianAccess.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sm_product_type_accordian_access")
public class CSmProductTypeAccordianAccessModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_type_accordian_access_id")
	private int product_type_accordian_access_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer module_id;
	private Integer sub_module_id;
	private Integer modules_forms_id;
	private Integer product_type_id;
	private String product_type_name;
	private String product_type_short_name;
	private String accordian_name;
	private boolean  is_display = Boolean.FALSE;
	private Integer display_sequence;
	private boolean  is_taxable = Boolean.FALSE;
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
	
	public boolean isIs_display() {
		return is_display;
	}
	public void setIs_display(boolean is_display) {
		this.is_display = is_display;
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
	

}
