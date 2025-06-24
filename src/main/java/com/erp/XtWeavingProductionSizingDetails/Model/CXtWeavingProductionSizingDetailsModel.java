package com.erp.XtWeavingProductionSizingDetails.Model;

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
@Table(name = "xt_weaving_production_sizing_details")
public class CXtWeavingProductionSizingDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weaving_production_sizing_details_id")
    private int weaving_production_sizing_details_id;
    private Integer weaving_production_sizing_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String sizing_production_order_no;
    private String sizing_production_date;
    private String sizing_production_code;
    private String sizing_production_status;
    private String customer_order_no;
    private Integer prod_month;
    private Integer prod_year;
    private String shift;
    private String weaving_production_set_no;
    private String yarn_count;
//    private String beam_no;
    private double actual_count;
    private double total_ends;
    private double warping_length;
    private double sizing_length;
    private double net_weight;
    private double gross_weight;
    private double tare_weight;
    private double unsize_beam_weight;
    private double size_pickup;
    private double sizing_rs;
    private double rate;
    private double amount;
    private double size_waste;
    private double unsize_waste;
    private double creel_waste;
    private String sizing_beam_no;
    private Integer plant_id;
    private Integer production_operator_id;
    private Integer production_supervisor_id;
    private Integer section_id;
    private Integer sub_section_id;
    private Integer machine_id;
    private Integer godown_id;
    private Integer no_of_creels;
    private Integer speed;

    private double calculative_net_weight;

    private double rf;

    private double visc;

    private double creel_a_tension;

    private double creel_b_tension;

    private double sq_pres_max;

    private double sq_pres_min;

    private double saw_box_a_temp;

    private double saw_box_b_temp;

    private double strech;

    private double moisture;

    private double after_waxing;

    private double leasing_tension;

    private double t_1;

    private double t_2;

    private double t_3;

    private double t_4;

    private double winding_tension;

    private double beam_pressing_tension;

    private double lappers;

    private double comb_breaks;

    private String status_remark;
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
