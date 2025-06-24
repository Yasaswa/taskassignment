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
@Subselect("select * from  xmv_production_electrical_meter_machine_mapping")
public class CXmProductionElectricalMeterMachineMappingViewModel {

	@Id
	private int prod_electrical_meter_machine_mapping_id;
	private String prod_electrical_meter_name;
	private String prod_electrical_meter_short_name;
	private String machine_meter_load_capacity;
	private String company_name;
	private String company_branch_name;
	private String machine_name;
	private String machine_short_name;
	private String machine_status;
	private String prod_electrical_meter_tech_spect;
	private String prod_electrical_meter_sr_no;
	private String prod_electrical_meter_load_capacity;
	private String prod_electrical_meter_errection_date;
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
	private Integer machine_id;
	private Integer field_id;


}
