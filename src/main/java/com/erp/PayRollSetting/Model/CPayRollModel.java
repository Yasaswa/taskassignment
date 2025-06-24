package com.erp.PayRollSetting.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hm_hrpayroll_settings")
public class CPayRollModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hrpayroll_settings_id")
    private Long hr_payroll_setting_id;

    private Long company_id;

    private Double pf_limit;

    private boolean is_delete;

    private Double attendance_allowance_days;

    private Double night_allowance_days;

    private Double worker_minimum_wages;

    private Double short_leave_hours;

}
