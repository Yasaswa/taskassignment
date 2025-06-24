package com.erp.HmShiftManagement.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hm_att_log")
public class CHmAttLogModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "att_log_transaction_id")
	private int att_log_transaction_id;
	private int att_device_id;
	private String att_device_ip;
	private String att_device_emp_code;
	private Integer att_device_transaction_id;
	private String att_date_time;
	private Date att_date_added;
	private String att_punch_status;
	private String att_punch_type;

}

