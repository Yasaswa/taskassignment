package com.erp.HmShiftManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hmv_att_log")
public class CHmvAttLogCombinedModel {
	@Id
	@Column(name = "transaction_id")
	private int transaction_id;

	@Column(name = "att_device_id")
	private int att_device_id;

	@Column(name = "att_device_ip")
	private String att_device_ip;

	@Column(name = "att_device_emp_code")
	private String att_device_emp_code;

	@Column(name = "att_device_transaction_id")
	private Integer att_device_transaction_id;

	@Column(name = "att_date_time")
	private String att_date_time;

	@Column(name = "att_date_added")
	private Date att_date_added;

	@Column(name = "att_punch_status")
	private String att_punch_status;

	@Column(name = "att_punch_type")
	private String att_punch_type;

	@Column(name = "source_table")
	private String source_table;
}
