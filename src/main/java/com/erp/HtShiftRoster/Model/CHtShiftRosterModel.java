package com.erp.HtShiftRoster.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ht_shift_roster")
public class CHtShiftRosterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roster_process_id")
	private Integer roster_process_id;
	private int company_id;
	private Integer company_branch_id;
	private Date process_date;
	private String roster_id;
	private String employee_type;
	private Integer employee_id;
	@Column(name = "employee_code")
	private String employee_code;
	private Integer attendance_month;
	private Integer attendance_year;
	private Integer department_id;
	private Integer sub_department_id;
	private String financial_year;
	private String shift_id1;
	private String shift_name1;

	private String shift_id2;
	private String shift_name2;

	private String shift_id3;
	private String shift_name3;

	private String shift_id4;
	private String shift_name4;

	private String shift_id5;
	private String shift_name5;

	private String shift_id6;
	private String shift_name6;

	private String shift_id7;
	private String shift_name7;

	private String shift_id8;
	private String shift_name8;

	private String shift_id9;
	private String shift_name9;

	private String shift_id10;
	private String shift_name10;

	private String shift_id11;
	private String shift_name11;

	private String shift_id12;
	private String shift_name12;

	private String shift_id13;
	private String shift_name13;

	private String shift_id14;
	private String shift_name14;

	private String shift_id15;
	private String shift_name15;

	private String shift_id16;
	private String shift_name16;

	private String shift_id17;
	private String shift_name17;

	private String shift_id18;
	private String shift_name18;

	private String shift_id19;
	private String shift_name19;

	private String shift_id20;
	private String shift_name20;

	private String shift_id21;
	private String shift_name21;

	private String shift_id22;
	private String shift_name22;

	private String shift_id23;
	private String shift_name23;

	private String shift_id24;
	private String shift_name24;

	private String shift_id25;
	private String shift_name25;

	private String shift_id26;
	private String shift_name26;

	private String shift_id27;
	private String shift_name27;

	private String shift_id28;
	private String shift_name28;

	private String shift_id29;
	private String shift_name29;

	private String shift_id30;
	private String shift_name30;

	private String shift_id31;
	private String shift_name31;


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
	private String remark;

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
