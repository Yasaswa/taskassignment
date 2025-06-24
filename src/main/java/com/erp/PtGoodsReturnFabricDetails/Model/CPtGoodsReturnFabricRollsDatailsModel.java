package com.erp.PtGoodsReturnFabricDetails.Model;

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
@Table(name = "pt_goods_return_fabric_rolls_details")
public class CPtGoodsReturnFabricRollsDatailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_return_fabric_rolls_details_id")
    private int goods_return_fabric_rolls_details_id;
    private Integer goods_return_fabric_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String goods_return_fabric_no;
    private String goods_return_fabric_date;
    private Integer goods_return_fabric_version;
    private String style;
    private String roll_no;
    private String sort_no;
    private String product_material_id;
    private double goods_return_roll_mtr;
    private double goods_return_roll_weight;

    private String goods_return_date;
    private String remark;

    private boolean is_delete = Boolean.FALSE;
    @Column(name = "created_by", updatable = false)
    private String created_by;
    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date created_on;
    private String modified_by;
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;

    public String getGoods_return_fabric_date() {
        return goods_return_fabric_date;
    }

    public void setGoods_return_fabric_date(String goods_return_fabric_date) {
        if (goods_return_fabric_date == null || goods_return_fabric_date.isEmpty()) {
            this.goods_return_fabric_date = null;
        } else {
            this.goods_return_fabric_date = goods_return_fabric_date;
        }
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
}
