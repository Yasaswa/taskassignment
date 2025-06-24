package com.erp.MtSalesOrderMasterTrading.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mt_sales_order_raw_material")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesOrderRawMaterialsDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt_sales_order_rm_transaction_id")
    private Integer mt_sales_order_rm_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private Integer sales_order_master_transaction_id;
    private String sales_order_no;
    private String sales_order_date;
    private Integer sales_order_version;
    private String customer_order_no;
    private String customer_order_Date;
    private Integer product_type_id;
    private String product_type;
    private String product_material_id;
    private String count_type;
    private Integer product_material_unit_id;
    private double material_quantity;
    private double material_weight;
    private double weight_per_unit;
    private String remark;
    private boolean is_active;
    private boolean is_delete;
    @Column(name = "created_by", updatable = false)
    private String created_by;
    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date created_on;
    private String modified_by;
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;

}
