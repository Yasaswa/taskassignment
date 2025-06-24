package com.erp.Taxtype.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from cmv_taxtype")
public class CTaxtypeViewModel {

	@Id
	private int taxtype_id;
	private String taxtype_name;
	private String taxtype_short_name;
	private String calculation_type;
	private String company_name;
	private String company_branch_name;
	//Added By Dipti (ERP DB Testing 1.1)
	private String Active;
	private String Deleted;
	//Added By Dipti (ERP DB Testing 1.1)
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private Date modified_on;
	private String modified_by;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	//Added By Dipti (ERP DB Testing 1.1)
	private Integer company_branch_id;
	//Added By Dipti (ERP DB Testing 1.1)
	private Integer field_id;
	private String field_name;

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
