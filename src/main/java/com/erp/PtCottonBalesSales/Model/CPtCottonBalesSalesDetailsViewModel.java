package com.erp.PtCottonBalesSales.Model;


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
@Table(name = "ptv_cotton_bales_sales_details")
public class CPtCottonBalesSalesDetailsViewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_cotton_bales_sales_details_transaction_id")
    private int pt_cotton_bales_sales_details_transaction_id;
    private int pt_cotton_bales_sales_master_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String sales_hsnTax_type;
    private String sales_voucher_type;
    private String sales_dispatch_type;
    private String dispatch_sales_type;
    private Integer sales_return_type_id;
    private String sales_return_type;
    private String sales_return_no;
    private String sales_return_date;
    private Integer customer_id;
    private Integer consignee_id;
    private Integer return_by_id;
    private String sales_return_status;
    private Integer approved_by_id;
    private String approved_date;
    private String goods_receipt_no;
    private String purchase_order_no;
    private String product_material_name;
    private String product_material_code;
    private String product_material_id;
    private String batch_no;
    private String supplier_batch_no;
    private double stock_quantity;
    private double stock_weight;
    private double sales_return_quantity;
    private double sales_return_weight;
    private double sales_return_rate;
    private double product_rm_std_weight;
    private Integer product_purchase_unit_id;
    private String product_unit_name;
    private double material_discount_percent;
    private double material_discount_amount;
    private Integer product_material_hsn_code_id;
    private String hsn_sac_code;
    private double product_hsn_sac_rate;
    private double material_basic_amount;
    private double material_taxable_amount;
    private double material_cgst_percent;
    private double material_cgst_total;
    private double material_sgst_percent;
    private double material_sgst_total;
    private double material_igst_percent;
    private double material_igst_total;
    private double material_freight_amount;
    private double material_total_amount;
    private String sales_return_remark;
    private Integer godown_id;
    private String press_running_no_from;
    private String press_running_no_to;
    private String customer_name;
    private String approved_by_name;
    private String return_by_name;
    private String godown_name;
    private String company_name;
    private String company_address1;
    private String company_address2;
    private String company_phone_no;
    private String company_cell_no;
    private String company_EmailId;
    private String company_website;
    private String company_gst_no;
    private String company_pan_no;
    private String company_pincode;
    private String sales_return_status_desc;
    private String company_branch_bank_name;
    private String company_branch_bank_account_no;
    private String company_branch_bank_ifsc_code;

    private boolean is_delete;
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
