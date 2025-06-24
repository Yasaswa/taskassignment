package com.erp.HmAttendanceDevice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ht_attendance_daily")
public class CHmDailyAttendanceProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_attendance_id")
    private int daily_attendance_id;
    private Integer company_id;
    private Integer employee_id;
    private Integer company_branch_id;
    private String employee_code;
    private Integer attendance_date;
    private Integer attendance_month;
    private Integer attendance_year;
    private String shift_scheduled;
    private String shift_present;
    private Integer job_type_id;
    private String job_type;
    private Date att_date_time;
    private String in_time;
    private String out_time;
    private Integer working_minutes;
    private String late_mark_flag;
    private String early_go_flag;
    private String gate_pass_flag;
    private String week_off_present_flag;
    private String night_shift_present_flag;
    private String remark;
    private String present_flag;
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


}
