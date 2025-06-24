package com.erp.HtSalarySummaryNew.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ht_salary_summary_new")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CHtSalarySummaryNewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_transaction_id")
    private int salary_transaction_id;
    private int company_id;
    private int company_branch_id;
    private String financial_year;
    private Integer salary_version;
    private Integer salary_month;
    private Integer salary_year;
    private Integer department_id;
    private Integer sub_department_id;
    private String employee_type;
    private Integer employee_id;
    private Integer designation_id;
    private double month_days;
    private double salary_days;
    private double monthly_salary;
    private Double worked_hours;
    private Double salary_perday;
    private Double salary_perhour;

    private double gross_salary;
    private double total_deduction;
    private double net_salary;
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

