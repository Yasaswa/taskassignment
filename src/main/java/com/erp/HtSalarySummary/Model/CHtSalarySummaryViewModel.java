package com.erp.HtSalarySummary.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "htv_salary_summary")
public class CHtSalarySummaryViewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_transaction_id")
    private Integer salary_transaction_id;

    private String financial_year;
    private int salary_year;
    private Integer salary_month;
    private String department_name;
    private String employee_type_group;
    private String sub_department_name;
    private String employee_type;
    private String employee_name;
    private String employee_code;
    private String old_employee_code;
    private String designation_name;
    private double month_days;
    private double salary_days;
    private double monthly_salary;
    private Double worked_hours;
    private Double salary_perday;
    private Double salary_perhour;
    private double gross_salary;
    private double total_deduction;
    private double net_salary;
    private double night_days;
    private double ot_days;
    private double leaves_days;
    private double od_days;
    private double half_days;
    private double holiday_days;
    private double week_off_days;
    private double coff_days;
    private double absent_days;
    private double present_days;
    private String Active;
    private String Deleted;
    private boolean is_active = Boolean.TRUE;
    private boolean is_delete = Boolean.FALSE;
    private String created_by;
    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date created_on;
    private String modified_by;
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;
    private Integer employee_id;
    private Integer department_id;
    private Integer sub_department_id;
    private Integer designation_id;
    private int company_id;
    private int company_branch_id;


}

