package com.erp.PtGoodsReturnsDetails.Model;

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
@Table(name = "pt_goods_return_details")
public class CPtGoodsReturnsDetailsModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_return_details_id")
    private int goods_return_details_id;
    private int goods_return_master_id;
    private String product_rm_id;
    private double goods_return_quantity;
    private double goods_return_weight;
    private double goods_return_rate;
    private int goods_return_boxes;
    private String goods_receipt_no;
    private String goods_return_remark;
    private Integer company_id;
    private Integer company_branch_id;
    private String godown_id;
    private String godown_section_id;
    private String godown_section_beans_id;
    private String financial_year;
    private String issue_batch_no;
    private Double cone_per_wt;

    private Integer product_material_unit_id;
    private Integer product_material_packing_id;
    private Integer product_hsn_sac_code_id;
    private double product_hsn_sac_rate;

    private double material_basic_amount;
    private double material_discount_percent;
    private double material_discount_amount;
    private double material_taxable_amount;
    private double material_cgst_percent;
    private double material_cgst_total;
    private double material_sgst_percent;
    private double material_sgst_total;
    private double material_igst_percent;
    private double material_igst_total;
    private double material_freight_amount;
    private double material_total_amount;

    private boolean is_active = Boolean.TRUE;
    private boolean is_delete =  Boolean.FALSE;
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
