package com.erp.XtWeavingProductionWarpingMaster.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name = "xt_weaving_production_warping_bottom_details")
public class CXtWeavingProductionWarpingBottomDetailsModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weaving_production_warping_bottom_details_id")
    private int weaving_production_warping_bottom_details_id;
    private int weaving_production_warping_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String set_no;
    private String warping_production_code;
    private String sr_no;
    private String batch_no;
    private String product_rm_id;
    private double weight_per_pkg;
    private String warping_bottom_details_production_date;
    private String creel_no;
    private String goods_receipt_no;
    private double no_of_package;
    private double gross_weight;
    private double net_weight;
    private String package_type;
    private String cone_type;
    private double cone_type_value;
    private double tare_weight;
    private Integer no_of_boxes;
    private String material_status;
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

    @Transient
    private String product_rm_name;
//    public CXtWeavingProductionWarpingBottomDetailsModel(Double no_of_package, Double gross_weight, Double tare_weight, Double net_weight, String product_rm_name) {
//        this.no_of_package = no_of_package;
//        this.gross_weight = gross_weight;
//        this.tare_weight = tare_weight;
//        this.net_weight = net_weight;
//        this.product_rm_name = product_rm_name;
//    }
    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
}
