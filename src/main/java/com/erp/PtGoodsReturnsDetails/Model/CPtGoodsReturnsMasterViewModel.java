
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
@Table(name = "ptv_goods_return_master")
public class CPtGoodsReturnsMasterViewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_return_master_id")
    private int goods_return_master_id;
    private String product_type_id;
    private String goods_return_no;
    private String goods_return_date;
//    private String goods_receipt_no;
    private int goods_version;
//    private int supplier_id;
    private String goods_return_status;
    private String product_type_name;
//    private String supplier_name;
    private String financial_year;
    private String sales_type;
    private Integer company_id;
    private Integer company_branch_id;
    private String transport_mode;
    private String invoice_no;
    private String driver_name;
    private String vehical_no;
    private String customer_name;
    private String cust_branch_address1;
    private String cust_branch_gst_no;
    private String cust_branch_phone_no;
    private String consignee_name;
    private String consi_branch_address1;
    private String consi_branch_gst_no;
    private String consi_branch_phone_no;

    private Integer customer_id;
    private Integer customer_state_id;
    private Integer customer_city_id;
    private Integer consignee_id;
    private Integer consignee_state_id;
    private Integer consignee_city_id;
    private Integer freight_hsn_code_id;

    private double basic_total;
    private double transport_amount;
    private double freight_amount;
    private double packing_amount;
    private double discount_percent;
    private double discount_amount;
    private double other_amount;
    private double taxable_total;
    private double cgst_percent;
    private double cgst_total;
    private double sgst_percent;
    private double sgst_total;
    private double igst_percent;
    private double igst_total;
    private double roundoff;
    private double grand_total;
    private boolean is_freight_taxable = Boolean.FALSE;

    private String cust_city_name;
    private String cust_state_name;
    private String consi_city_name;
    private String consi_state_name;
    private String yarn_type;
    private Integer sales_type_id;



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
    private Integer approved_by_id;
    private String approved_by_name;
    private String approved_date;

    private String sales_dispatch_type;
    private String dispatch_hsnTax_type;
    private String dispatch_sales_type;
    private String dispatch_voucher_type;

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
