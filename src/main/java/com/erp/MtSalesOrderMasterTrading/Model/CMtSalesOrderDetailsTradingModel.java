package com.erp.MtSalesOrderMasterTrading.Model;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mt_sales_order_details_trading")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesOrderDetailsTradingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_order_details_transaction_id")
    private int sales_order_details_transaction_id;
    private Integer company_branch_id;
    private Integer sales_order_master_transaction_id;
    private Integer company_id;
    private String sales_order_no;
    private String sales_order_date;
    private Integer sales_order_version;
    private String customer_order_no;
    private String customer_order_Date;
    private Integer product_type_id;
    private String product_type;
    private String product_material_id;
    private String product_material_print_name;
    private String product_material_tech_spect;
    private Integer sr_no;
    private String so_sr_no;
    private Integer product_material_unit_id;
    private Integer product_material_packing_id;
    private Integer product_material_hsn_code_id;
    private double material_quantity;
    private double material_weight;
    private double quotation_rate;
    private double material_rate;
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
    private String material_schedule_date;
    private String sales_order_item_status;

    private double epi_1;
    private double ppi_1;
    private double width;
    private double warp_crimp;
    private double weft_crimp;
    private double warp_waste;
    private double weft_waste;
    private double warp_count_1__wc1__actual_count;
    private double warp_count_2__wc2__actual_count;
    private double warp_count_3__wc3__actual_count;
    private double warp_count_4__wc4__actual_count;
    private double weft_count_1__fc1__actual_count;
    private double weft_count_2__fc2__actual_count;
    private double weft_count_3__fc3__actual_count;
    private double weft_count_4__fc4__actual_count;

    private String product_recommendation;    // Added For Fabric SO;
    private String special_remark;    // Added For Fabric SO;
    private boolean monogram_is_applicable = Boolean.FALSE;    // Added For Fabric SO;
    private String weft_color;    // Added for Fabric SO;
    private String material_style;    // Added for Fabric SO;
    private String material_style_value;    // Added for Fabric SO;
    private String material_style_abbrevation;    // Added for Fabric SO;

    private double pending_quantity;
    private double pending_weight;
    private double opening_quantity;
    private double opening_weight;
    private double reserve_quantity;
    private double reserve_weight;
    private double excess_quantity;
    private double excess_weight;
    private double pree_closed_quantity;
    private double pree_closed_weight;
    private double purchase_quantity;
    private double purchase_weight;
    private double purchase_return_quantity;
    private double purchase_return_weight;
    private double purchase_rejection_quantity;
    private double purchase_rejection_weight;
    private double jobcard_quantity;
    private double jobcard_weight;
    private double production_issue_quantity;
    private double production_issue_weight;
    private double production_issue_return_quantity;
    private double production_issue_return_weight;
    private double production_issue_rejection_weight;
    private double production_quantity;
    private double production_weight;
    private double production_return_quantity;
    private double production_return_weight;
    private double production_rejection_quantity;
    private double production_rejection_weight;
    private double assembly_production_issue_quantity;
    private double assembly_production_issue_weight;
    private double production_issue_rejection_quantity;
    private double sales_quantity;
    private double sales_weight;
    private double sales_return_quantity;
    private double sales_return_weight;
    private double sales_rejection_quantity;
    private double sales_rejection_weight;
    private double transfer_issue_quantity;
    private double transfer_issue_weight;
    private double transfer_receipt_quantity;
    private double transfer_receipt_weight;
    private Double so_rate;
    private double outsources_out_quantity;
    private double outsources_out_weight;
    private double outsources_in_quantity;
    private double outsources_in_weight;
    private double outsources_rejection_quantity;
    private double outsources_rejection_weight;
    private double loan_receipt_quantity;
    private double loan_receipt_weight;
    private double loan_issue_quantity;
    private double loan_issue_weight;
    private double cancel_quantity;
    private double cancel_weight;
    private double difference_quantity;
    private double difference_weight;
    private String remark;
    private boolean is_active;
    private boolean is_delete;
    @Column(name = "created_by", updatable = false)
    private String created_by;
    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date created_on;
    private String modified_by;
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;
    private String sales_quotation_no;
    private String sales_quotation_date;
    private String dispatch_note_nos;
    private String dispatch_challan_nos;
    private String invoice_nos;
    private String financial_year;
    private String set_no;


    public void setSales_order_date(String sales_order_date) {
        if (sales_order_date == null || sales_order_date.isEmpty()) {
            this.sales_order_date = null;
        } else {
            this.sales_order_date = sales_order_date;
        }
    }

    public void setCustomer_order_Date(String customer_order_Date) {
        if (customer_order_Date == null || customer_order_Date.isEmpty()) {
            this.customer_order_Date = null;
        } else {
            this.customer_order_Date = customer_order_Date;
        }
    }

    public void setMaterial_schedule_date(String material_schedule_date) {
        if (material_schedule_date == null || material_schedule_date.isEmpty()) {
            this.material_schedule_date = null;
        } else {
            this.material_schedule_date = material_schedule_date;
        }
    }

    public void setSales_quotation_date(String sales_quotation_date) {
        if (sales_quotation_date == null || sales_quotation_date.isEmpty()) {
            this.sales_quotation_date = null;
        } else {
            this.sales_quotation_date = sales_quotation_date;
        }
    }

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

    public boolean isMonogram_is_applicable() {
        return monogram_is_applicable;
    }

    public void setMonogram_is_applicable(boolean monogram_is_applicable) {
        this.monogram_is_applicable = monogram_is_applicable;
    }

}
