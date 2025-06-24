package com.erp.MaterialTransferMaster.Model;


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
@Table(name = "pt_material_transfer_details")
public class CMaterialTransferDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_transfer_details_id", nullable = false)
    private Integer material_transfer_details_id;

     private Integer material_transfer_master_id;

     private Integer material_transfer_type_id;

     private Integer company_id;

     private Integer company_branch_id;

     private String financial_year;

     private String transfer_no;

     private Integer transfer_version;

     private Date transfer_date;

     private String product_code;

     private String product_material_id;

     private Integer godown_id;

     private Integer godown_section_id;

     private Integer godown_section_beans_id;

     private Integer product_unit_id;

    private double material_rate;

    private double product_material_std_weight ;

    private double stock_quantity;

    private double stock_weight;

    private double transfer_quantity;

    private double transfer_weight;

    private double closing_balance_quantity ;

    private double closing_balance_weight;

    private Boolean is_active;

    private boolean is_delete = Boolean.FALSE;

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
