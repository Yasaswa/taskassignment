package com.erp.MtDispatchScheduleDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mt_dispatch_schedule_master_trading")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtDispatchScheduleMasterTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dispatch_schedule_master_transaction_id")
	private int dispatch_schedule_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer dispatch_schedule_type_id;
	private String dispatch_schedule_type;
	private String dispatch_schedule_creation_type;
	private String dispatch_schedule_no;
	private Date dispatch_schedule_date;
	private Integer dispatch_schedule_version;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
	private Integer dispatch_supervisor_id;
	private Integer approved_by_id;
	private Date approved_date;
	private double total_quantity;
	private double total_weight;
	private double actual_quantity;
	private double actual_weight;
	private String dispatch_note_status;
	private String dispatch_note_remark;
	private String other_terms_conditions;
	private String gate_pass_no;
	private String gate_pass_date;
	private String vehical_no;
	private String container_no;
	private String seal_no;
	private double net_weight;
	private String driver_name;
	private String driver_contact_no;
	private String vehicle_reporting_time;
	private String vehicle_loading_started_time;
	private String vehicle_loading_finish_time;
	private String dispatch_type;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String expected_schedule_date;

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

	public String getGate_pass_date() { return gate_pass_date; }
	
	public void setGate_pass_date(String gate_pass_date) {
		if (gate_pass_date == null || gate_pass_date.isEmpty()) {
			this.gate_pass_date = null;
		} else {
			this.gate_pass_date = gate_pass_date;
		}
	}
	
	public void setVehicle_reporting_time(String vehicle_reporting_time) {
		if (vehicle_reporting_time == null || vehicle_reporting_time.isEmpty()) {
			this.vehicle_reporting_time = null;
		} else {
			this.vehicle_reporting_time = vehicle_reporting_time;
		}
	}

	public void setVehicle_loading_started_time(String vehicle_loading_started_time) {
		if (vehicle_loading_started_time == null || vehicle_loading_started_time.isEmpty()) {
			this.vehicle_loading_started_time = null;
		} else {
			this.vehicle_loading_started_time = vehicle_loading_started_time;
		}
	}

	public void setVehicle_loading_finish_time(String vehicle_loading_finish_time) {
		if (vehicle_loading_finish_time == null || vehicle_loading_finish_time.isEmpty()) {
			this.vehicle_loading_finish_time = null;
		} else {
			this.vehicle_loading_finish_time = vehicle_loading_finish_time;
		}
	}
}
