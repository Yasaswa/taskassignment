package com.erp.FinancialYear.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from amv_financial_year")
public class CFinancialYearViewModel {

	@Id
	private int financial_year_id;

	private Integer company_id;

	private String company_name;

	private String financial_year;

	private String short_name;

	private String start_date;

	private String short_year;

	private String end_date;

	private String remark;

	private boolean is_delete = Boolean.FALSE;
	;

	private boolean is_active = Boolean.TRUE;
	;

	private String created_by;

	private String modified_by;

	private String deleted_by;

	private String field_id;

	private String field_name;

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
