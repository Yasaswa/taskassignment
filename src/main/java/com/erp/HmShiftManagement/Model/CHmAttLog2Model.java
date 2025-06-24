package com.erp.HmShiftManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hm_att_log2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CHmAttLog2Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_log2_transaction_id")
    private Long att_log2_transaction_id;

    @Column(name = "att_device_id", nullable = false)
    private Integer att_device_id = 1; // Hardcoded value

    @Column(name = "att_device_ip")
    private String att_device_ip;

    @Column(name = "att_device_emp_code", nullable = false)
    private String att_device_emp_code;

    @Column(name = "att_device_transaction_id")
    private String att_device_transaction_id;

    @Column(name = "att_date_time", nullable = false)
    private String att_date_time;

    @Column(name = "att_date_added", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date att_date_added;

    @Column(name = "att_punch_status")
    private String att_punch_status;

    @Column(name = "att_punch_type")
    private String att_punch_type;
}
