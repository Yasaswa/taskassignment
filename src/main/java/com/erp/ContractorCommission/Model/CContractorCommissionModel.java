package com.erp.ContractorCommission.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "hm_commission_rate")
public class CContractorCommissionModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commission_rate_id;
    private Integer company_id;
    private String skill_type;
    private String resident_type;
    private Integer department_id;
    private Integer sub_department_id;
    private String commission_type;
    private Double commission_rate;
    private Boolean is_active = Boolean.TRUE;
    private Boolean is_delete = Boolean.FALSE;
    @Column(name = "created_by", updatable = false)
    private String created_by;
    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date created_on;
    private String modified_by;
    @UpdateTimestamp
    @Column(name = "modified_on", updatable = false)
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }


}
