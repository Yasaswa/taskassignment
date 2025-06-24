package com.erp.Godown_Section.Model;

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
@Subselect("select * from cmv_godown_section")
public class CGodownSectionViewModel {

	@Id
	private int godown_section_id;
	private String godown_section_name;
	private String godown_section_short_name;
	private double godown_section_area;
	private Integer section_beans_count;
	private String godown_name;
	private String godown_short_name;
	private Integer godown_section_count;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	//Added By Dipti(Testing 1.1)
	private String Active;
	private String Deleted;
	//Added By Dipti(Testing 1.1)
	private String company_name;
	private String company_branch_name;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Date deleted_on;
	private String deleted_by;
	private Integer company_id;
	//Added By Dipti(Testing 1.1)
	private Integer company_branch_id;
	//Added By Dipti(Testing 1.1)
	private Integer godown_id;
	private Integer product_type_id;
	private String field_name;
	private Integer field_id;

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
