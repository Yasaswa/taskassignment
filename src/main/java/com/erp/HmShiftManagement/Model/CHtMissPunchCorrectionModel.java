package com.erp.HmShiftManagement.Model;

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
@Table(name = "ht_misspunch_correction")
public class CHtMissPunchCorrectionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "misspunch_correction_id")
    private int misspunch_correction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String employee_code;
    private String punch_code;
    private String att_date_time;
    private String in_time;
    private String out_time;
    private String punch_type;
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

    public CHtMissPunchCorrectionModel(CHtMissPunchCorrectionModel model) {
        if (model != null) {
            this.punch_code = model.punch_code;
            this.employee_code = model.employee_code;
            this.att_date_time = model.att_date_time;
            this.in_time = model.in_time;
            this.out_time = model.out_time;
            this.punch_type = model.punch_type;
            this.created_by = model.created_by;
            this.created_on = model.created_on;
            this.company_id = model.company_id;
            this.company_branch_id = model.company_branch_id;

            // Copy other fields as needed
            // For example:
            // this.someField = model.someField;
        }
    }

}

















