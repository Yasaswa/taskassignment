package com.erp.XtLoomEfficiencyMaster.Model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "xt_loom_efficiency_master")
@Getter @Setter
public class CXtLoomEfficiencyMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loom_efficiency_master_id")
    private int loom_efficiency_master_id;
    private Integer company_branch_id;
    private Integer company_id;
    private String properties_master_name;
    private Integer weave_design_id;
    private String weave_design_name;
    private double target_efficiency;
    private double target_speed;
    private String status_remark;

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
