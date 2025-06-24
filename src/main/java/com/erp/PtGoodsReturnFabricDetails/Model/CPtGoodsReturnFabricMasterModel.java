package com.erp.PtGoodsReturnFabricDetails.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="pt_goods_return_fabric_master")
public class CPtGoodsReturnFabricMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_return_fabric_master_id")
    private int goods_return_fabric_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String goods_return_fabric_no;
    private String goods_return_fabric_date;
    private Integer goods_return_fabric_version;
    private String supplier_invoice_no;
    private String supplier_invoice_date;
    private Integer supplier_id;
    private Integer supplier_state_id;
    private Integer supplier_city_id;
    private String supplier_contacts_ids;
    private Integer expected_branch_id;
    private Integer expected_branch_state_id;
    private Integer expected_branch_city_id;

    private Integer goods_return_fabric_type_id;
    private String goods_return_fabric_type;
    private String supplier_challan_no;
    private String supplier_challan_date;
    private Integer approved_by_id;
    private String approved_date;
    private Integer qa_by_id;
    private String qa_date;
    private double basic_total;
    private double transport_amount;
    private double freight_amount;
    private boolean is_freight_taxable;
    private Integer freight_hsn_code_id;
    private double packing_amount;
    private double goods_return_discount_percent;
    private double goods_return_discount_amount;
    private double other_amount;
    private double taxable_total;
    private double cgst_total;
    private double sgst_total;
    private double igst_total;
    private double grand_total;
    private double roundoff;
    private Integer agent_id;
    private double agent_percent;
    private String agent_paid_status;
    private String goods_return_fabric_status;
    private String lr_no;
    private String lr_date;
    private String vehicle_no;
    private String other_terms_conditions;
    private String remark;
    private String goods_purchase_type;
    private String goods_purchase_tax_type;
    private String goods_purchase_sales_type;
    private String goods_purchase_voucher_type;
    private String fabric_type;

    private boolean is_active = Boolean.TRUE;
    private boolean is_delete = Boolean.FALSE;
    private boolean is_preeclosed = Boolean.FALSE;
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
    private String ev_bill_no;
    private String ev_bill_date;
    private String purchase_type;
    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }


    public boolean isIs_freight_taxable() {
        return is_freight_taxable;
    }


    public void setIs_freight_taxable(boolean is_freight_taxable) {
        this.is_freight_taxable = is_freight_taxable;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public String getApproved_date() {
        return approved_date;
    }

    public void setApproved_date(String approved_date) {
        if (approved_date == null || approved_date.isEmpty()) {
            this.approved_date = null;
        } else {
            this.approved_date = approved_date;
        }
    }
}
