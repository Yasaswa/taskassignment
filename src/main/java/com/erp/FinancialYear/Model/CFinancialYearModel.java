package com.erp.FinancialYear.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "am_financial_year")
public class CFinancialYearModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "financial_year_id")
	private int financial_year_id;

	private Integer company_id;

	private String financial_year;

	private String short_name;

	private String short_year;

	private String start_date;

	private String end_date;

	private boolean is_delete = Boolean.FALSE;
	;

	private boolean is_active = Boolean.TRUE;
	;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private String remark;

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


}
