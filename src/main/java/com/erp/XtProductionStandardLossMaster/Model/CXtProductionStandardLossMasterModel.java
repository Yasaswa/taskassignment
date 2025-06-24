package com.erp.XtProductionStandardLossMaster.Model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "xt_production_standard_losses")
public class CXtProductionStandardLossMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer production_standard_losses_id;
    private Integer weave_design_id;
    private String weave_design_name;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private double traget_efficiency;
    private double warp_break;
    private double weft_break;
    private double knotting;
    private double gaiting;
    private boolean is_active = Boolean.TRUE;
    private boolean is_delete = Boolean.FALSE;
    private String created_by;
    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date created_on;
    private String modified_by;
    @UpdateTimestamp
    @Column(name = "modified_on")
    private Date modified_on;
    private String deleted_by;
    @Column(name = "deleted_on")
    private Date deleted_on;
}
