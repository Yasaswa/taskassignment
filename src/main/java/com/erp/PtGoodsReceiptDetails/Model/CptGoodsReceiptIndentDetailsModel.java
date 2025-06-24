package com.erp.PtGoodsReceiptDetails.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pt_goods_receipts_indent_details")
public class CptGoodsReceiptIndentDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_receipts_indent_details_transaction_id")
    private int goods_receipts_indent_details_transaction_id;
    private Integer goods_receipt_master_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String goods_receipt_no;
    private String goods_receipt_date;
    private Integer goods_receipt_version;
    private String purchase_order_no;
    private String indent_no;
    private String product_material_id;
    private double product_material_indent_approved_quantity;
    private double product_material_indent_approved_weight;
    private double product_material_grn_accepted_quantity;
    private double product_material_grn_accepted_weight;
    private Integer indent_details_id;
    private Integer purchase_order_details_transaction_id;
    private double product_material_std_weight;
    private Integer product_material_unit_id;
    private String remark;
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


}
