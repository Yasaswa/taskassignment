package com.erp.HmCompOffDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "htv_comp_off_intimation_details")
public class CHmCompOffDetailsViewModel {
    @Id
    private int comp_off_intimation_details_id;
    private Integer company_id;
    private Integer company_branch_id;
    private Integer employee_id;
    private String employee_code;
    private String status;
    private String remark;
    private String approval_remark;
    private String punch_code;
    private String att_date_time;
    private String employee_type;
    private String weeklyoff_name;
    private String approved_date;
    private Integer approved_by_id;
    private Integer reporting_to;
    private String comp_holiday_type;
    private String reporting_to_name;
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

}

















