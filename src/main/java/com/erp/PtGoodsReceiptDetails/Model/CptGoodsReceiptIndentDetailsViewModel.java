package com.erp.PtGoodsReceiptDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from ptv_goods_receipts_indent_details")
public class CptGoodsReceiptIndentDetailsViewModel {
    @Id
    private int goods_receipts_indent_details_transaction_id;
    private String financial_year;
    private String goods_receipt_no;
    private String goods_receipt_date;
    private Integer goods_receipt_version;
    private String purchase_order_no;
    private String indent_no;
    private String product_material_name;
    private String product_unit_name;
    private double product_material_indent_approved_quantity;
    private double product_material_indent_approved_weight;
    private double product_material_grn_accepted_quantity;
    private double product_material_grn_accepted_weight;
    private Integer company_id;
    private Integer company_branch_id;
    private String product_material_id;
    private Integer indent_details_id;
    private Integer goods_receipt_master_transaction_id;
    private Integer purchase_order_details_transaction_id;
    private Integer product_material_unit_id;
    private String remark;
    private String Deleted;
    private boolean is_delete;
    private String created_by;
    private Date created_on;
    private String modified_by;
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;
}
