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
@Table(name = "ptv_grn_cotton_bales_details")
public class CPtGrnCottonBalesDetailsViewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grn_cotton_bales_details_transaction_id")
    private int grn_cotton_bales_details_transaction_id;

    private int grn_cotton_bales_master_transaction_id;
    private int purchase_order_details_transaction_id;
    private int company_id;
    private int company_branch_id;
    private String financial_year;
    private String goods_receipt_no;
    private Date goods_receipt_date;
    private int goods_receipt_version;
    private String grn_item_status_desc;
    private String purchase_order_no;
    private Date purchase_order_date;
    private int purchase_order_version;
    private String supplier_challan_no;
    private Date supplier_challan_date;
    private String supplier_name;
    private String supplier_city_name;
    private String supplier_state_name;
    private int item_qa_by_id;
    private Date item_qa_date;
    private String goods_receipt_type;
    private String batch_no;
    private String product_material_code;
    private String product_material_name;
    private String product_material_stock_unit_name;
    private double product_material_std_weight;
    private String product_category1_name;
    private String product_category2_name;
    private String product_packing_name;
    private String hsn_sac_code;
    private double hsn_sac_rate;
    private double product_material_po_approved_quantity;
    private double product_material_po_approved_weight;
    private double product_material_grn_quantity;
    private double product_material_grn_weight;
    private double product_material_conversion_factor;
    private double product_material_grn_rejected_quantity;
    private double product_material_grn_rejected_weight;
//    private double product_rm_std_weight;
    private String product_rejection_type;
    private String product_rejection_parameters_name;
    private String product_rejection_parameters_short_name;
    private double product_material_grn_accepted_quantity;
    private double product_material_grn_accepted_weight;
    private double prev_grn_bales_quantity;
    private double prev_grn_bales_weight;
    private double product_material_prev_accepted_quantity;
    private double product_material_prev_accepted_weight;
    private double po_material_rate;

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
    private Date material_schedule_date;
    private String grn_item_status;
    private double excess_quantity;
    private double excess_weight;

    private Integer press_running_no_from;
    private Integer press_running_no_to;
    private String press_running_remark;
    private double pree_closed_grn_quantity;
    private double pree_closed_grn_weight;
    private String grn_remark;
    private String company_name;
    private String company_branch_name;

    private String approved_by_name;
    private String godown_name;

    private String product_material_type_group;
    private String product_type_name;
    private String product_type_short_name;
    private String cost_center_name;
    private double currency_exchange_rate;
    private Integer grn_currency_id;
    private String grn_currency_name;
    private String grn_currency_code;
    private String grn_currency_symbol;
    private Integer department_id;
    private Integer product_material_unit_id;
    private Integer product_purchase_unit_id;
    private Integer product_material_packing_id;
    private Integer product_material_hsn_code_id;
    private Integer goods_receipt_type_id;
    private Integer product_material_rejection_reason_id;
    private Integer supplier_id;
    private Integer approved_by_id;
    private Integer supplier_state_id;
    private Integer supplier_city_id;
    private String supplier_contacts_ids;
    private Integer expected_branch_id;
    private Integer expected_branch_state_id;
    private Integer expected_branch_city_id;
    private Integer payment_term_id;
    private Integer agent_id;

    private double invoice_gross_weight;
    private double invoice_tare_weight;
    private double invoice_net_weight;
    private double mill_gross_weight;
    private double mill_tare_weight;
    private double mill_net_weight;
    private double difference_weight;
    private String supplier_batch_no;
    private String product_material_id;
    private Integer godown_id;

    private Integer product_category1_id;
    private Integer product_category2_id;
    private Integer currency_id;
    private String currency_name;
    private String currency_code;
    private Integer issue_flag;


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

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
}
