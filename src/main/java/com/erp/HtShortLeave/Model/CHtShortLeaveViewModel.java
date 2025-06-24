package com.erp.HtShortLeave.Model;

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
@Table(name = "htv_short_leave")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CHtShortLeaveViewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "short_leave_transaction_id")
    private Integer short_leave_transaction_id;
    private int company_id;
    private Integer company_branch_id;
//    private Integer department_id;
//    private Integer sub_department_id;
    private Integer employee_id;
    private String short_leave_id;
    private String financial_year;
    private String leave_type;
    private String gate_pass_type;
    private String employee_code;
    private String out_time;
    private String in_time;
    private String short_leave_date;
    private String total_hours;
    private String leave_reason;
    private Integer approved_by;
    private String approval_status;
    private String department_name;
    private String sub_department_name;
    private String employee_name;
    private String company_branch_name;
    private String company_name;
    private String approved_by_name;
    private String shift_name;
    private String shift_start_end_time;
    private String reporting_to_name;
    private Integer month;
    private String punch_code;
    private String half_day_type;
    private Integer leave_type_id;
    private String leave_type_name;

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

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

}
