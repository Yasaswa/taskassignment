package com.erp.MtSalesOrderMasterTrading.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
//@Subselect("select * from  mt_sales_order_sauda_sheet")
@Table(name = "mt_sales_order_sauda_sheet")

public class CMtSalesOrderSaudaSheetModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_order_sauda_sheet_id")
    private int sales_order_sauda_sheet_id;
    private int sales_order_master_transaction_id;
    private Integer company_id;
//    private Integer company_branch_id;
    private String financial_year;
    private String sales_order_no;
//    private String sales_order_date;
    private String product_id;
//    private String product_material_name;

    private double warp_weft_crimp;
//    private double weft_crimp;
    private double warp_weft_waste;
//    private double weft_waste;
    private double req_warp_and_weft_per_kg;
//    private double req_weft_per_kg;
    private double glm_without_size;
    private double req_order_quantity;
    private String count;
    private String count_type;
    private String product_material_code;

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
