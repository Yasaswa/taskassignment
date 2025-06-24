package com.erp.SmProductProcess.Model;

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
@Subselect("select * from  smv_product_process")
public class CPmProductProcessViewModel {

	@Id
	private int product_process_id;
	private String product_process_name;
	private String product_process_short_name;
	private String product_process_type;
	private double product_process_std_scrap_percent;
	private double product_process_std_production_hrs;
	private String product_parent_process_name;
	private String company_name;
	private String remark;
	//Added By Dipti (ERP DB Testing 1.1)
	private String Active;
	private String Deleted;
	//Added By Dipti (ERP DB Testing 1.1)
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer product_parent_process_id;
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
