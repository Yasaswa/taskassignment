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
@Table(name = "pt_grn_cotton_bales_order_tax_summary")
public class CPtGrnCottonBalesOrderTaxSummaryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grn_cotton_bales_tax_summary_transaction_id")
    private int grn_cotton_bales_tax_summary_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private Integer purchase_order_master_transaction_id;
    private Integer supplier_id;
    private Integer supplier_state_id;
    private Integer supplier_city_id;
    private String supplier_contacts_ids;
    private Integer expected_branch_id;
    private Integer expected_branch_state_id;
    private Integer expected_branch_city_id;
    private String purchase_order_no;
    private Date purchase_order_date;
    private Integer purchase_order_version;
    private Integer hsn_code_id;
    private double summary_taxable_amount;
    private double summary_cgst_percent;
    private double summary_cgst_total;
    private double summary_sgst_percent;
    private double summary_sgst_total;
    private double summary_igst_percent;
    private double summary_igst_total;
    private double summary_total_amount;
    private String tax_upload__status;
    private String tax_upload_id;
    private Date tax_upload_date;
    private String remark;
    private boolean is_active;
    private boolean is_delete;
    @Column(name = "created_by", updatable = false)
    private String created_by;
    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date created_on;
    @Column(name = "modified_by", updatable = false)
    private String modified_by;
    @UpdateTimestamp
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;
    private String goods_receipt_no;
    private Date goods_receipt_date;
    private Integer goods_receipt_version;
    private Integer grn_cotton_bales_master_transaction_id;

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

