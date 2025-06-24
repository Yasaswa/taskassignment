package com.erp.PtGoodsReturnFabricDetails.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table (name="pt_goods_return_fabric_details")
public class CPtGoodsReturnFabricDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_return_fabric_details_id")
    private int goods_return_fabric_details_id;
    private Integer goods_return_fabric_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String goods_return_fabric_no;
    private String goods_return_fabric_date;
    private Integer goods_return_fabric_version;
    private Integer item_qa_by_id;
    private String item_qa_date;
    private Integer goods_return_fabric_type_id;
    private String goods_return_fabric_type;
    private String product_material_id;
    private String product_material_name;
    private String sort_no;
    private Integer product_material_unit_id;
    private Integer product_material_packing_id;
    private Integer product_material_hsn_code_id;
    private double product_goods_return_quantity;
    private double product_goods_return_weight;
    private Integer product_return_no_of_rolls;

    private double product_material_conversion_factor;
    private double material_rate;
    private double material_basic_amount;
    private double material_freight_amount;
    private double material_discount_percent;
    private double material_discount_amount;
    private double material_taxable_amount;
    private double material_cgst_percent;
    private double material_cgst_total;
    private double material_sgst_percent;
    private double material_sgst_total;
    private double material_igst_percent;
    private double material_igst_total;
    private double material_total_amount;

    private String goods_return_fabric_product_status;
    private Integer godown_id;
    private Integer godown_section_id;
    private Integer godown_section_beans_id;
    private String remark;
    private Integer currency_id;
    private double currency_exchange_rate;
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
