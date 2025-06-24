package com.erp.HmManualAttendance.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "hm_manual_attendance")
public class CHmManualAttendanceModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_transaction_id")
	private int attendance_transaction_id;
	private Integer company_id;
	private String employee_type;
	private Integer department_id;
	private Integer sub_department_id;
	private String employee_code;
	private String employee_name;
	private String attendance_date;
	private String attendance_date_time;
	private String attendance_type;
	private Integer attendance_type_id;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

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

	public String getAttendance_date() {
		return attendance_date;
	}

	public void setAttendance_date(String attendance_date) {
		if (attendance_date == null || attendance_date.isEmpty()) {
			this.attendance_date = null;
		} else {
			this.attendance_date = attendance_date;
		}
	}


}
