package com.erp.Supplier.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cm_supplier")
public class CSupplierModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private int supplier_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String supplier_type;
	private String supplier_name;
	private String supplier_code;
	private String supplier_short_name;
	private String supplier_sector;
	private String nature_of_business;
	private Integer payment_term_id;
	private String supplier_rating;
	private String supplier_gl_codes;
	private String supplier_accounts_id;
	private String supplier_history;
	private String username;
	private String password;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String modified_by;
	private String deleted_by;
	private String remark;

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
