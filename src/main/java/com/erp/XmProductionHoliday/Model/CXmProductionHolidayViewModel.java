package com.erp.XmProductionHoliday.Model;

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
@Subselect("select * from xmv_production_holiday")
public class CXmProductionHolidayViewModel {

	@Id
	private int production_holiday_id;
	private String production_holiday_name;
	private String company_name;
	private String company_branch_name;
	private Date production_holiday_date;
	private String production_holiday_remark;

	//Added By Dipti (ERP DB Testing 1.1)
	private String Active;
	private String Deleted;
	//Added By Dipti (ERP DB Testing 1.1)

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
	private String field_name;
	private Integer field_id;

}
