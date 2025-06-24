package com.erp.MtSalesOrderMasterTrading.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "mtv_sales_order_raw_material")
public class CMtSalesOrderRawMaterialsDetailsViewModel {

    @Id
    private Integer mt_sales_order_rm_transaction_id;
    private String sales_order_no;
    private Date sales_order_date;
    private Integer sales_order_version;
    private String customer_order_no;
    private Date customer_order_date;
    private String product_type;
    private String product_material_name;
    private String count_type;
    private String product_material_tech_spect;
    private String product_material_unit_name;
    private double product_material_std_weight;
    private String product_material_technical_name;
    private double material_quantity;
    private double material_weight;
    private double weight_per_unit;
    private String remark;
    private String company_name;
    private String company_branch_name;
    private Boolean is_active;
    private Boolean is_delete;
    private String created_by;
    private Date created_on;
    private String modified_by;
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;
    private Integer company_id;
    private Integer company_branch_id;
    private Integer sales_order_master_transaction_id;
    private Integer product_type_id;
    private String product_material_id;
    private Integer product_material_unit_id;
    private double stock_quantity;
    private double stock_weight;
}
