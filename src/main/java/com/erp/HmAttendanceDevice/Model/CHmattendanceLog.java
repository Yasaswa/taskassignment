package com.erp.HmAttendanceDevice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "hm_att_log")
public class CHmattendanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_log_transaction_id")
    private int att_log_transaction_id;
    private Integer att_device_id;
    private String att_device_ip;
    private Date att_date_added;
    private String att_device_emp_code;
    private Integer att_device_transaction_id;
    private String att_date_time;
    private String att_punch_status;
    private String att_punch_type;


}
