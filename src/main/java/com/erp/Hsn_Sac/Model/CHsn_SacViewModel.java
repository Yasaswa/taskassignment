package com.erp.Hsn_Sac.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from cmv_hsn_sac")
public class CHsn_SacViewModel {

//	@Id
//	private int hsn_sac_id;
//	private String company_name;
//	private String hsn_sac_type;
//	private String hsn_sac_code;
//	private String hsn_sac_description;
//	private double hsn_sac_rate;
//	private boolean is_exampted;
//	private boolean is_active = Boolean.TRUE;
//	private boolean is_delete = Boolean.FALSE;
//	private Integer company_id;
//	private String field_name;
//	private Integer field_id;

	//Added By Dipti (Testing 1.1) Removed company_name and Added Active and Deleted 
	@Id
	private int hsn_sac_id;
	private String hsn_sac_type;
	private String hsn_sac_code;
	private String hsn_sac_description;
	private double hsn_sac_rate;
	private boolean is_exampted;
	private String Active;
	private String Deleted;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private Integer company_id;
	private String field_name;
	private Integer field_id;
	//Added By DIpti (Testing 1.1)


	public boolean isIs_exampted() {
		return is_exampted;
	}

	public void setIs_exampted(boolean is_exampted) {
		this.is_exampted = is_exampted;
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
