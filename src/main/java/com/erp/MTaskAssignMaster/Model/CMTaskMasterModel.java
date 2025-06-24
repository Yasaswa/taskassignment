package com.erp.MTaskAssignMaster.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mt_task_assign_table")
public class CMTaskMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_transaction_id")
    private Integer task_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;

    private String employee_type;
    private Integer employee_id;
    private String employee_designation;
    private Integer module_id;
    private Integer sub_module_id;
    private String task_name;
    private String task_purpose;
    private String assigned_task_start_time;
    private String assigned_task_end_time;
    private String assigned_task_start_date;
    private String assigned_task_end_date;
    private String accomplished_task_end_time;
    private String accomplished_task_end_date;
    private String task_status;
    private String task_remark;
    private Integer task_assigned_by;

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
