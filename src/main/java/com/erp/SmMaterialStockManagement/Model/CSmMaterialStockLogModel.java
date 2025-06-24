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
@Table(name = "sm_material_stock_log")
public class CSmMaterialStockLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_log_transaction_id")
    private Integer stock_log_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private Date transaction_date;
    private String transaction_type;
    private String transaction_no;
    private Integer product_type_id;
    private Integer product_category1_id;
    private Integer product_category2_id;
    private String product_material_id;
    private Integer product_material_unit_id;
    private String batch_no;
    private double batch_rate;
    private double quantity;
    private double weight;
    private Integer no_of_boxes;
    private Integer godown_id;
    private Integer godown_section_id;
    private Integer godown_section_beans_id;
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

    // Default constructor
    public CSmMaterialStockLogModel() {
    }

    // Copy constructor
    public CSmMaterialStockLogModel(CSmMaterialStockLogModel other) {
        this.stock_log_transaction_id = other.stock_log_transaction_id;
        this.company_id = other.company_id;
        this.company_branch_id = other.company_branch_id;
        this.financial_year = other.financial_year;
        this.transaction_date = other.transaction_date;
        this.transaction_type = other.transaction_type;
        this.transaction_no = other.transaction_no;
        this.product_type_id = other.product_type_id;
        this.product_category1_id = other.product_category1_id;
        this.product_category2_id = other.product_category2_id;
        this.product_material_id = other.product_material_id;
        this.product_material_unit_id = other.product_material_unit_id;
        this.batch_no = other.batch_no;
        this.batch_rate = other.batch_rate;
        this.quantity = other.quantity;
        this.weight = other.weight;
        this.no_of_boxes = other.no_of_boxes;
        this.godown_id = other.godown_id;
        this.godown_section_id = other.godown_section_id;
        this.godown_section_beans_id = other.godown_section_beans_id;
        this.remark = other.remark;
        this.is_delete = other.is_delete;
        this.created_by = other.created_by;
        this.created_on = other.created_on;
        this.modified_by = other.modified_by;
        this.modified_on = other.modified_on;
        this.deleted_by = other.deleted_by;
        this.deleted_on = other.deleted_on;
    }

}
