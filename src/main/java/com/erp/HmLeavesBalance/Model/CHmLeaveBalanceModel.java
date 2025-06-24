package com.erp.HmLeavesBalance.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "hm_leaves_balance")
public class CHmLeaveBalanceModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leaves_balance_id")
	private int leaves_balance_id;
	private Integer leave_type_id;
	private Integer company_id;
	private String financial_year;
	private String effective_date;
	private String employee_type;
	private String employee_code;
	private double opening_balance;
	private double leaves_earned;
	private double leaves_taken;
	private double closing_balance;
	private double leaves_adjusted;
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

	public String getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(String effective_date) {
		if (effective_date == null || effective_date.isEmpty()) {
			this.effective_date = null;
		} else {
			this.effective_date = effective_date;
		}
	}


}
