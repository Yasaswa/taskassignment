package com.erp.XtWarpingBottomReturn.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "xt_warping_bottom_return_details")
public class CXtWarpingBottomReturnModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warping_bottom_return_details_id")
    private int warping_bottom_return_details_id;
    private int weaving_production_warping_bottom_details_id;
    private int weaving_production_warping_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String set_no;
    private String warping_production_code;
    private String bottom_return_no;
    private String bottom_receipt_date;
    private String bottom_return_date;
    private String sr_no;
    private String batch_no;
    private String product_material_id;
    private double weight_per_pkg;
    private String warping_bottom_details_production_date;
    private String creel_no;
    private String goods_receipt_no;
    private double no_of_package;
    private double gross_weight;
    private double net_weight;
    private String cone_type;
    private String bora_box;
    private double cone_type_value;
    private double tare_weight;
    private Integer no_of_boxes;
    private Integer customer_id;
    private Integer supplier_id;
    private Integer godown_id;
    private Integer godown_section_id;
    private Integer godown_section_beans_id;
    private Integer bottom_return_type_id;
    private Integer return_by_id;
    private Integer received_by_id;
    private Integer department_id;
    private Integer sub_department_id;
    private String bottom_return_type;
    private String supplier_name;
    private String customer_name;
    private String bottom_return_item_status;
    private String package_type;
    private String remark;
    private double actual_gross_weight;
    private double actual_net_weight;

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
