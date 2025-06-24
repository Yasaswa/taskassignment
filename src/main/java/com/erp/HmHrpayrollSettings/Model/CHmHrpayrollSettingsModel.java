package com.erp.HmHrpayrollSettings.Model;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hm_hrpayroll_settings")
public class CHmHrpayrollSettingsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hrpayroll_settings_id")
    private int hrpayroll_settings_id;
    private Integer company_id;
    private Double pf_limit;
    private Double attendance_allowance_days;
    private Double night_allowance_days;
    private Double worker_minimum_wages;
    private Double short_leave_hours;
    private boolean is_delete = Boolean.FALSE;
    private boolean lock_status = Boolean.FALSE;
    private String lock_date;
    private Double attendance_min_allow_days;
    private Double attendance_max_allow_days;

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public boolean isLockStatus() {
        return lock_status;
    }

    public void setLockStatus(boolean lock_status) {
        this.lock_status = lock_status;
    }

}
