package com.erp.XtWeavingBeamIssueMaster.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="xtv_weaving_beam_issue_master")
public class CXtWeavingBeamIssueMasterViewModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="weaving_beam_issue_master_id")
    private Integer weaving_beam_issue_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String beam_issue_no;
    private Integer set_no;
    private Integer beam_no;
    private Integer loom_no;
    private String beam_issue_date;
    private String shift_name;
    private String beam_issue_time;
    private String loom_process_type;
    private String loom_process_charge;
    private String sort_no;
    private String count;
    private double t_ends;
    private double reed;
    private double pick;
    private double rs;
    private double length;
    private double remaining_length;
    private String loom_beam_status;
    private String cut_beam_reason;
    private String cut_beam_date;
    private String cut_beam_time;

    private String company_name;
    private String company_branch_name;
    private String company_address;
    private String company_pincode;
    private String company_phone_no;
    private String company_EmailId;
    private String company_website;
    private String company_gst_no;
    private String company_pan_no;
    private String shift;
    private String beam_name;
    private Integer beam_issuer_id;
    private Integer beam_cutter_id;
    private String beam_issuer_name;
    private String beam_cutter_name;

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
