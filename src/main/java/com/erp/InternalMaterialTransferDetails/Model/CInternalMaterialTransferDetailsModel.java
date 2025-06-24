package com.erp.InternalMaterialTransferDetails.Model;

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
@Table(name = "pt_inter_material_transfer_details")
public class CInternalMaterialTransferDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inter_material_transfer_details_id", nullable = false)
    private Integer inter_material_transfer_details_id;
    private Integer inter_material_transfer_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String inter_material_transfer_no;
    private Date inter_material_transfer_date;
    private String product_code;
    private String product_material_id;
    private String goods_receipt_no;
    private Integer from_godown_id;
    private Integer from_godown_section_id;
    private Integer from_godown_section_beans_id;
    private Integer to_godown_id;
    private Integer to_godown_section_id;
    private Integer to_godown_section_beans_id;
    private String internal_material_transfer_remark;
    private String batch_no;
    private Integer product_unit_id;
    private double material_rate;
    private double product_material_std_weight;
    private double cone_per_wt;
    private Integer supplier_id;
    //    private double stock_quantity;
    private String product_unit_name;
    private double inter_material_transfer_quantity;
    private double inter_material_transfer_weight;
    private Integer inter_material_transfer_boxes;
    //    private double inter_material_transfer_receipt_quantity;
//    private double inter_material_transfer_receipt_weight;
//    private Integer inter_material_transfer_receipt_boxes;
    private double closing_balance_quantity;
    private double closing_balance_weight;
    private Integer closing_no_of_boxes;
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
