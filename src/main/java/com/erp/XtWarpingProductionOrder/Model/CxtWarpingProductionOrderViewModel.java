package com.erp.XtWarpingProductionOrder.Model;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Immutable
@Entity
@Table(name = "xtv_warping_production_order")
public class CxtWarpingProductionOrderViewModel {

    @Id
    private Integer warping_production_order_id;
    private Integer production_order_type_id;
    private String set_no;
    private String warping_order_no;
    private String sort_no;
    private String customer_order_no;
    private String sales_order_no;
    private String sales_order_date;
    private String warping_order_status;
    private String warping_schedule_date;
    private String warping_plan_date;
    private Double schedule_quantity;
    private Double order_quantity;
    private String other_terms_conditions;
    private Double no_of_creels;
    private String creel_type;
    private Double set_length;
    private Double t_ends;
    private String product_material_name;
    private String product_material_style;
    private String warping_order_status_desc;
    private String financial_year;
    private String status_remark;
    private String approved_by_name;
    private String approved_date;
    private String remark;
    private String deleted;
    private Boolean is_delete;
    private String created_by;
    private Date created_on;
    private String modified_by;
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;
    private Integer company_id;
    private Integer company_branch_id;
    private String product_material_id;
    private Integer approved_by_id;
    private Integer customer_id;
    private String job_type;
    private String bottom_value;
    public boolean isIs_delete() {
        return is_delete;
    }
    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }


}



