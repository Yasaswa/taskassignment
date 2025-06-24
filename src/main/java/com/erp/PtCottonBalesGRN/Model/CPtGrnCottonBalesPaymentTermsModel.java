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
@Table(name = "pt_grn_cotton_bales_payment_terms")
public class CPtGrnCottonBalesPaymentTermsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grn_cotton_bales_payment_terms_transaction_id")
    private int grn_cotton_bales_payment_terms_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private Integer grn_cotton_bales_master_transaction_id;
    private String goods_receipt_no;
    private Date goods_receipt_date;
    private Integer goods_receipt_version;
    private String purchase_order_no;
    private Date purchase_order_date;
    private Integer purchase_order_version;
    private Integer payment_terms_id;
    private String payment_terms_name;
    private String payment_terms_days;
    private Integer payment_terms_grace_days;
    private String payment_terms_Milestome;
    private String payment_percent;
    private double payment_expected_value;
    private Date payment_expected_date;
    private String payment_paid_flag;
    private String payment_paid_transaction_id;
    private String payment_paid_date;
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

