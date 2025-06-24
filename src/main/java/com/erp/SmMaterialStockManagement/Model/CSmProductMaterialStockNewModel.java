package com.erp.SmMaterialStockManagement.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@Table(name = "sm_product_material_stock_new")
public class CSmProductMaterialStockNewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_transaction_id")
    private int stock_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String product_type_group;
    private Integer product_type_id;
    private String product_material_id;
    private Integer product_material_unit_id;
    private Integer product_material_packing_id;
    private String batch_no;
    private String supplier_batch_no;
    private String goods_receipt_no;
    private double batch_rate = 0;
    private double opening_quantity = 0;
    private double opening_weight = 0;
    private Integer opening_no_of_boxes = 0;
    private double reserve_quantity = 0;
    private double reserve_weight = 0;
    private Integer reserve_no_of_boxes = 0;
    private double excess_quantity = 0;
    private double excess_weight = 0;
    private double pree_closed_quantity = 0;
    private double pree_closed_weight = 0;
    private double purchase_quantity = 0;
    private double purchase_weight = 0;
    private Integer purchase_no_of_boxes = 0;
    private double purchase_return_quantity = 0;
    private double purchase_return_weight = 0;
    private Integer purchase_return_no_of_boxes = 0;
    private double supplier_return_quantity = 0;
    private double supplier_return_weight = 0;
    private Integer supplier_return_boxes = 0;
    private double production_issue_quantity = 0;
    private double production_issue_weight = 0;
    private Integer issue_no_of_boxes = 0;
    private double production_issue_return_quantity = 0;
    private double production_issue_return_weight = 0;
    private Integer production_issue_return_boxes = 0;
    private double production_quantity = 0;
    private double production_weight = 0;
    private Integer production_no_of_boxes = 0;
    private double sales_quantity = 0;
    private double sales_weight = 0;
    private Integer sales_no_of_boxes = 0;
    private double sales_return_quantity = 0;
    private double sales_return_weight = 0;
    private double transfer_issue_quantity = 0;
    private double transfer_issue_weight = 0;
    private double transfer_receipt_quantity = 0;
    private double transfer_receipt_weight = 0;
    private double closing_balance_quantity = 0;
    private double closing_balance_weight = 0;
    private Integer closing_no_of_boxes = 0;
    private Integer godown_id;
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


    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

}

