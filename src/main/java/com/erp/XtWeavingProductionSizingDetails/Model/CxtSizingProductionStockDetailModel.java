package com.erp.XtWeavingProductionSizingDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Entity
@Table(name = "xt_sizing_production_stock_details")
public class CxtSizingProductionStockDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sizing_stock_details_id;
    private int weaving_production_sizing_details_id;
    private int weaving_production_sizing_master_id;
    private String sizing_production_code;
    private String set_no;
    private String job_type;
    private String product_material_id;
    private String beam_no;
    private String customer_order_no;
    private double total_ends;
    private double sizing_length;
    private double remaining_length;
    private String cut_beam_date;
    private double net_weight;
    private double gross_weight;
    private double tare_weight;
    private int customer_id;
    private double amount;
    private double rate;
    private Date sizing_production_date;
    private Date dispatch_date;
    private int godown_id;
    private String sized_beam_status;
    private int section_id;
    private int sub_section_id;
    private String financial_year;
    private int company_id;
    private int company_branch_id;

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
