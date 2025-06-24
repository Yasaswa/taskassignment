package com.erp.XtSpinningProdSpinnPlanMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Entity
@Subselect("select * from xtv_spinning_prod_spinn_plan_summary")
public class CXtSpinningProdSpinnPlanSummaryViewModel {


	@Id
	private int spinn_plan_id;
	private String spinn_plan_code;
	private String spinn_plan_month;
	private String spinn_plan_year;
	private String spinn_plan_start_date;
	private String spinn_plan_end_date;
	private Integer plant_id;
	private String customer_name;
	private String spinn_plan_created_by_name;
	private String spinn_plan_approved_by_name;
	private String spinn_plan_description;
	private String spinn_plan_avg_count;
	private String spinn_plan_status;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private Integer spinn_plan_created_by_id;
	private Integer spinn_plan_approved_by_id;
	private String field_name;
	private Integer field_id;
	private Integer customer_id;
	private Integer company_id;
	private Integer company_branch_id;

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
