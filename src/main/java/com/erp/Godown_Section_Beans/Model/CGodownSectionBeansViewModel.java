package com.erp.Godown_Section_Beans.Model;

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
@Subselect("Select * From cmv_godown_section_beans")
public class CGodownSectionBeansViewModel {

	@Id
	private int godown_section_beans_id;
	private String company_name;
	private String company_branch_name;
	private String godown_section_beans_name;
	private String godown_section_beans_short_name;
	private double godown_section_beans_area;
	private Integer godown_section_id;
	private String godown_section_name;
	private String godown_section_short_name;
	private Integer godown_id;
	private String godown_name;
	private String godown_short_name;
	private Integer godown_section_count;
	private Double godown_section_area;

	// Addded By Dipti ( ERP DB Testing 1.1)
//	private Integer section_beans_count;
//	private String product_type_name;
//	private String product_type_short_name;
//	private String product_type_group;
//	private String Active;
//	private String Deleted;
	// Addded By Dipti ( ERP DB Testing 1.1)

	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Date deleted_on;
	private String deleted_by;
	private Integer company_id;

	// Addded By Dipti ( ERP DB Testing 1.1)
//	private Integer company_branch_id;
//	private Integer product_type_id;
	// Addded By Dipti ( ERP DB Testing 1.1)

	private String field_name;
	private Integer field_id;

}
