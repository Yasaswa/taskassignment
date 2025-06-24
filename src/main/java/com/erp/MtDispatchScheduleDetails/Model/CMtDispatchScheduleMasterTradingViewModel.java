package com.erp.MtDispatchScheduleDetails.Model;

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
@Subselect("select * from  mtv_dispatch_schedule_master_trading")
public class CMtDispatchScheduleMasterTradingViewModel {

	@Id
	private int dispatch_schedule_master_transaction_id;

	private String dispatch_schedule_creation_type;
	private String dispatch_schedule_creation_type_desc;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private Integer dispatch_schedule_version;
	private String customer_name;
	private String customer_state_name;
	private String consignee_name;
	private String consignee_state_name;
	private String dispatch_supervisor_name;
	private String approved_by_name;
	private String dispatch_type_short_name;
	private String approved_date;
	private Double total_quantity;
	private Double total_weight;
	private Double actual_quantity;
	private Double actual_weight;
	private String dispatch_note_status;
	private String dispatch_note_remark;
	private String other_terms_conditions;
	private String remark;
	private String dispatch_type;

	private String company_name;
	private String company_branch_name;
	private String financial_year;
//	private String dispatch_schedule_type;
	private String customer_email;
	private String customer_city_name;
	private String consignee_email;
	private String consignee_city_name;
	private String customer_phone;
	private String customer_gst_no;
	private String customer_pan_no;
	private String cust_branch_address1;
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
	private Integer dispatch_schedule_type_id;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
	private Integer dispatch_supervisor_id;
	private Integer approved_by_id;
	private String gate_pass_no;
	private String gate_pass_date;
	private String vehical_no;
	private String container_no;
	private String seal_no;
	private Double net_weight;
	private String driver_name;
	private String driver_contact_no;
	private String vehicle_reporting_time;
	private String vehicle_loading_started_time;
	private String vehicle_loading_finish_time;
	private String dispatch_note_status_desc;
	private String consignee_address;
	private String Active;
	private String Deleted;
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

}
