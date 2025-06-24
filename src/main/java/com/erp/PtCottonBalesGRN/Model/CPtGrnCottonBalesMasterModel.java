package com.erp.PtCottonBalesGRN.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name = "pt_grn_cotton_bales_master")
public class CPtGrnCottonBalesMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grn_cotton_bales_master_transaction_id")
    private int grn_cotton_bales_master_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
//    private Integer product_category2_id;
    private String financial_year;
    private String goods_receipt_no;
    private Date goods_receipt_date;
    private Integer goods_receipt_version;
    private Integer supplier_branch_id;
    private Integer supplier_state_id;
    private Integer supplier_city_id;
    private String supplier_contacts_ids;
    private Integer expected_branch_id;
    private Integer expected_branch_state_id;
    private Integer expected_branch_city_id;
    private String purchase_order_no;
    private Date purchase_order_date;
    private Integer purchase_order_version;
    private Integer goods_receipt_type_id;
    private String goods_receipt_type;
    private String supplier_challan_no;
    private Date supplier_challan_date;
    private Integer approved_by_id;
    private Date approved_date;
    private Integer qa_by_id;
    private Date qa_date;
    private double basic_total;
    private boolean is_freight_taxable= Boolean.TRUE;
    private double transport_amount;
    private double freight_amount;
    private Integer freight_hsn_code_id;
    private String batch_no;
    private double insurance_amount;
    private double goods_receipt_discount_percent;
    private double goods_receipt_discount_amount;
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
    private String goods_receipt_status;
    private String lr_no;
    private Date lr_date;
    private String vehicle_no;
    private Integer supplier_id;
    private String supplier_invoice_no;
    private Date supplier_invoice_date;
    private String ev_bill_no;
    private Date ev_bill_date;
    private String gate_entry_no;
    private Date gate_entry_date;
    private String station;
    private double invoice_amount;
    private double amount_difference;
    private String transporter_name;
    private boolean is_preeclosed= Boolean.TRUE;
    private String remark;
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

    public boolean isIs_preeclosed() {
        return is_preeclosed;
    }

    public void setIs_preeclosed(boolean is_preeclosed) {
        this.is_preeclosed = is_preeclosed;
    }

    public boolean isIs_freight_taxable() {
        return is_freight_taxable;
    }

    public void setIs_freight_taxable(boolean is_freight_taxable) {
        this.is_freight_taxable = is_freight_taxable;
    }


}



