package com.erp.MtServiceReportingMaster.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mt_service_reporting_master")
public class CMtServiceReportingMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_reporting_master_transaction_id")
	private int service_reporting_master_transaction_id;
	private String service_reporting_code;
	private String service_reporting_date;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private int assigned_service_engineer_id;
	private int service_engineer_reporting_to_id;
	private int customer_id;
	private String customer_order_no;
	private Date to_date;
	private Date from_date;
	private String reporting_status;
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
	public String getService_reporting_date() {
		return service_reporting_date;
	}
	public void setService_reporting_date(String service_reporting_date) {
		if(service_reporting_date == null || service_reporting_date.isEmpty()) {
			this.service_reporting_date = null;
		}else {
			this.service_reporting_date = service_reporting_date;
		}
		
	}

}
