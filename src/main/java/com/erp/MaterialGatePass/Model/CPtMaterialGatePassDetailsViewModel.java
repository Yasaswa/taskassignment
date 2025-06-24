package com.erp.MaterialGatePass.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ptv_material_gate_pass_details")
public class CPtMaterialGatePassDetailsViewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_gate_pass_details_id",nullable = false)
    private Integer material_gate_pass_details_id;
    private Integer material_gate_pass_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String gate_pass_no;
    private String gate_pass_date;
    private Integer product_type_id;
    private Integer cost_center_id;
    private String cost_center_name;
    private String product_type_name;
    private String product_material_id;
    private String material_code;
    private String material_name;
    private double outward_quantity;
    private double outward_weight;
    private double inward_quantity;
    private double inward_weight;
    private double pending_inward_quantity;

    private String gate_pass_item_status;
    private String gate_pass_item_status_desc;
    private String return_item_status;

    private double rate;
    private String remark;
    private String purpose;

    private String company_name;
    private String company_branch_name;
    private String company_address;
    private String company_pincode;
    private String company_phone_no;
    private String company_EmailId;
    private String company_website;
    private String company_gst_no;
    private String company_pan_no;
//    private Integer department_id;
//    private Integer sub_department_id;
    private String sub_department_name;
    private String department_name;

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
    @UpdateTimestamp
    private Date deleted_on;

    public boolean isIs_delete() {
        return is_delete;
    }
    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
}
