package com.erp.HtAttendaceNew.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hm_att_log_new")
public class CHmAttLogNewModel {

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

