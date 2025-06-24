package com.erp.Shift.Model;

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
@Subselect("select * from cmv_shift")
public class CShiftViewModel {

	@Id
	private int shift_id;
	private String shift_name;
	private String company_name;
	private String company_branch_name;
	private String employee_type_group;
	private String start_time;
	private String end_time;
	private String ot_start_time;
	private Integer grace_early_time;
	private Integer grace_late_time;
	private double halfday_hours;
	private double fullday_hours;
	private boolean auto_rotate_flag;
	private boolean two_day_shift;
	private Integer shift_grace_hours_min;
	private Integer shift_grace_hours_max;
	private String Active;
	private String Deleted;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private String field_name;
	private Integer field_id;

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isAuto_rotate_flag() {
		return auto_rotate_flag;
	}

	public void setAuto_rotate_flag(boolean auto_rotate_flag) {
		this.auto_rotate_flag = auto_rotate_flag;
	}

	public boolean isTwo_day_shift() {
		return two_day_shift;
	}

	public void setTwo_day_shift(boolean two_day_shift) {
		this.two_day_shift = two_day_shift;
	}


}
