package com.erp.XmProductionElectricalMeter.Model;

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
@Subselect("select * from  xmv_production_electrical_meter")
public class CXmProductionElectricalMeterViewModel {

	@Id
	private String company_name;
	private String company_branch_name;
	private String prod_electrical_meter_name;
	private String prod_electrical_meter_short_name;
	private String prod_electrical_meter_tech_spect;
	private String prod_electrical_meter_sr_no;
	private String prod_electrical_meter_load_capacity;
	private String prod_electrical_meter_errection_date;
	private String department_name;
	private String department_group;
	private String parent_department;
	private String department_short_name;
	private String plant_name;
	private String plant_status;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer prod_electrical_meter_id;
	private Integer prod_plant_id;
	private Integer prod_department_id;
	private Integer prod_sub_department_id;
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
