
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ptv_material_return_gate_pass_master")
public class CPtMaterialReturnGatePassMasterViewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_return_gate_pass_master_id", nullable = false)
    private Integer material_return_gate_pass_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String gate_pass_no;
    private String return_gate_pass_no;
    private String return_gate_pass_date;
    private Integer product_type_id;
    private String product_type_name;
    private String material_type;
    private String vehicle_no;
    private Integer supplier_branch_id;
    private Integer supplier_branch_state_id;
    private Integer supplier_branch_city_id;
    private String supplier_branch_contacts_id;
    private String supplier_branch_address;
    private Integer checker_by_id;
    private String checker_by_name;
    private Integer returned_by_id;
    private String returned_by_name;
    private String return_status_desc;
    private String return_status;
    private String material_type_desc;
    private String company_name;
    private String company_branch_name;
    private String company_address;
    private String company_pincode;
    private String company_phone_no;
    private String company_EmailId;
    private String company_website;
    private String company_gst_no;
    private String company_pan_no;
    private String supp_branch_name;
    private String state_name;
    private String city_name;
    private String supp_contact_person;
    private String supp_contact_no;
    private Integer department_id;
    private String department_name;
    private Integer sub_department_id;
    private String sub_department_name;
    private String challan_no;
    private String challan_date;
    private String supp_branch_gst_no;

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
