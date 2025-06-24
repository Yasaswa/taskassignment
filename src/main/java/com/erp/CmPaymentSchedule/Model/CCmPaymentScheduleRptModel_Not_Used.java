package com.erp.CmPaymentSchedule.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  cmv_payment_schedule_rpt")
public class CCmPaymentScheduleRptModel_Not_Used {

	@Id
	private String payment_schedule_id;
	private String company_name;
	private String payment_schedule_name;
	private String payment_schedule_type;
	private String payment_schedule_days;
	private String payment_schedule_grace_days;
	private String remark;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String field_name;
	private String field_id;

}
