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
@Table(name = "pt_grn_cotton_bales_details")
public class CPtGrnCottonBalesDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grn_cotton_bales_details_transaction_id")
    private int grn_cotton_bales_details_transaction_id;
    private Integer grn_cotton_bales_master_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String goods_receipt_no;
    private Date goods_receipt_date;
    private Integer goods_receipt_version;
    private String purchase_order_no;
    private Date purchase_order_date;
    private Integer purchase_order_version;
    private Integer item_qa_by_id;
    private Date item_qa_date;
    private Integer goods_receipt_type_id;
    private String goods_receipt_type;
    private String batch_no;
    private String supplier_batch_no;
    private String product_material_id;
    private Integer product_material_unit_id;
    private Integer product_purchase_unit_id;
    private double product_material_conversion_factor;
    private Integer product_material_packing_id;
    private Integer product_material_hsn_code_id;
    private double product_material_po_approved_quantity;
    private double product_material_po_approved_weight;
    private double product_material_grn_quantity;
    private double product_material_grn_weight;
    private double product_material_grn_accepted_quantity;
    private double product_material_grn_accepted_weight;
    private double product_material_grn_rejected_quantity;
    private double product_material_grn_rejected_weight;
//    private double product_rm_std_weight;
    private Integer currency_id;
    private double po_material_rate;
    private double material_rate;
    private Integer grn_currency_id;
    private double currency_exchange_rate;
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
    private Date material_schedule_date;
    private String grn_item_status;
    private double excess_quantity;
    private double excess_weight;
    private double pree_closed_grn_quantity;
    private double pree_closed_grn_weight;
    private double invoice_gross_weight;
    private double invoice_tare_weight;
    private double invoice_net_weight;
    private double mill_gross_weight;
    private double mill_tare_weight;
    private double mill_net_weight;
    private double difference_weight;
    private Integer godown_id;
    private Integer department_id;
    private String grn_remark;
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
    private Integer purchase_order_details_transaction_id;
    private Integer product_material_rejection_reason_id;
    private Integer press_running_no_from;
    private Integer press_running_no_to;
    private String press_running_remark;
    private Integer product_category1_id;
    private Integer product_category2_id;



    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

}

