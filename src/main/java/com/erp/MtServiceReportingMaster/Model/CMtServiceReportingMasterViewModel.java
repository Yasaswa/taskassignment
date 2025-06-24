package com.erp.MtServiceReportingMaster.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Subselect(value  = "mtv_service_reporting_master")
public class CMtServiceReportingMasterViewModel {

	@Id
	private int service_reporting_master_transaction_id;
	private String service_reporting_code;
	private String service_reporting_date;
	private String customer_name;
	private String customer_order_no;
	private String from_date;
	private String to_date;
	private String assigned_service_engineer;
	private String service_engineer_reporting_to;
	private String reporting_status_desc;
	private String reporting_status;
	private String company_name;
	private String company_branch_name;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String financial_year;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer customer_id;
	private Integer assigned_service_engineer_id;
	private Integer service_engineer_reporting_to_id;
	
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
