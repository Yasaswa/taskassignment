package com.erp.XtWarpingProductionOrder.Model;


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
@Table(name = "xt_warping_production_order_beam_details")
public class CXtWarpingProductionOrderBeamDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warping_production_order_beam_details_id")
    private int warping_production_order_beam_details_id;
    private Integer warping_production_order_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String set_no;
    private String warping_order_no;
    private Integer customer_id;
    private String customer_order_no;
    private String sort_no;
    private String warping_plan_date;
    private String warping_order_status;

    private String creel_no;
    private String beam_set_no;
    private Integer production_count;
    private Integer no_of_cones;
    private Integer no_of_beams;
    private Double cone_per_wt;
    private double per_beam_ends;
    private double beam_meter;

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
