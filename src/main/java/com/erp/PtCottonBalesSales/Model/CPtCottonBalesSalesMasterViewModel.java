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
@Table(name = "ptv_cotton_bales_sales_master")
public class CPtCottonBalesSalesMasterViewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_cotton_bales_sales_master_transaction_id")
    private Integer pt_cotton_bales_sales_master_transaction_id;
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
    private Integer customer_state_id;
    private Integer customer_city_id;
    private String customer_address;
    private String customer_gst_no;
    private String customer_cell_no;
    private String goods_receipt_no;
    private String purchase_order_no;
    private Integer consignee_id;
    private Integer consignee_state_id;
    private Integer consignee_city_id;
    private String consignee_address;
    private String consignee_gst_no;
    private String consignee_cell_no;
    private String invoice_no;
    private String driver_name;
    private String vehicle_no;
    private String transport_mode;
    private Integer return_by_id;
    private String sales_return_status;
    private Integer approved_by_id;
    private Date approved_date;
    private double sales_total;
    private double basic_total;
    private double freight_amount;
    private boolean is_freight_taxable= Boolean.TRUE;
    private Integer freight_hsn_code_id;
    private double taxable_total;
    private double transport_amount;
    private double packing_amount;
    private double other_amount;
    private double cgst_total;
    private double sgst_total;
    private double igst_total;
    private double roundoff;
    private double grand_total;

    private String approved_by_name;
//    private String company_branch_name;
    private String customer_name;
    private String cust_city_name;
    private String cust_state_name;
    private String consi_city_name;
    private String consi_state_name;
    private String consignee_name;
    private String return_by_name;
    private String sales_return_status_desc;

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

    public boolean isIs_freight_taxable() {
        return is_freight_taxable;
    }

    public void setIs_freight_taxable(boolean is_freight_taxable) {
        this.is_freight_taxable = is_freight_taxable;
    }
}
