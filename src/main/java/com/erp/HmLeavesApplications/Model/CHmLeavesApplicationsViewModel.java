package com.erp.HmLeavesApplications.Model;

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
@Subselect("select * from  hmv_leaves_applications")
public class CHmLeavesApplicationsViewModel {

	@Id
	private int leaves_transaction_id;
	private String leaves_applications_id;
	private String leaves_application_date;
	private String financial_year;
	private String employee_type;
	private String employee_code;
	private String employee_name;
	private Integer sub_department_id;
	private String designation_name;
	private String department_name;
	private String sub_department_name;
	private String leave_type_name;
	private String leave_type_code;
	private String leave_type_paid_flag;
	private String leaves_requested_from_date;
	private String leaves_requested_to_date;
	private String leaves_approved_from_date;
	private String leaves_approved_to_date;
	private String leave_sandwich;
	private double leaves_applied_days;
	private double leaves_sanction_days;
	private double leaves_rejection_days;
	private String leave_status;
	private String leave_reason;
	private String entered_by_name;
	private String work_handover_to;
	private String approved_by_name;
	private String approved_date;
	private String leave_approve_remark;
	private Integer employee_id;
	private Integer reporting_to;
	private String reporting_to_name;
	private Integer leave_type_id;
	private String work_handover_id;
	private Integer department_id;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer field_id;
	private String field_name;
	private String punch_code;

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
