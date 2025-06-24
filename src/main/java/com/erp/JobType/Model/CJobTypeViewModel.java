package com.erp.JobType.Model;

import com.google.errorprone.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("Select * From hmv_job_type")
public class CJobTypeViewModel {

	@Id
	private int job_type_id;
	private String job_type_name;
	private String job_type_short_name;
	private Double job_type_rate;
	private Double job_type_night_allowance;
	private Double job_type_attendance_allowance;
	private Double job_type_rate_group;;
	private String job_type_category;
	private String department_name;
	private String skill_type;
	private Integer hours_month;
	private Double job_type_special_allowance;
	private String Active;
	private String Deleted;
	private Boolean is_active;
	private Boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer department_id;
	private Integer sub_department_id;
	private Integer field_id;
	private String field_name;
	private String company_name;
	private Double job_type_attendance_allow_24_days;
	private Double job_type_attendance_allow_26_days;
	private Double on_time_allowance;

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}


}
