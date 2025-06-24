package com.erp.SaudaSheetMaster.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name="mtv_sauda_sheet_master")
public class CMtSaudaSheetMasterViewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sauda_sheet_master_id")
    private Integer sauda_sheet_master_id;

    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String sauda_sheet_no;
    private String sauda_sheet_date;
    private String sauda_sheet_status;
    private String product_sort_no;
    private String product_type_id;
    private Integer customer_id;
        private Integer customer_state_id;
    private Integer customer_city_id;
    private Integer agent_id;
    private Integer agent_state_id;
    private Integer agent_city_id;
    private double warp_crimp;
    private double weft_crimp;
    private double weft_count;
    private double warp_count;
    private double warp_req_1_mtr_cloth;
    private double weft_req_1_mtr_cloth;
    private double warp_waste;
    private double weft_waste;
    private double warp_for_cost_Rs_Per_mtr;
    private double weft_for_cost_Rs_Per_mtr;
    private double weaving_cost_RS_per_Pick_per_inch_per_mtr;
    private double warping_sizing_cost_RS_per_kg;
    private double stream_cost_Rs_per_mtr;
    private double weaving_cost_Rs_per_mtr;
    private double mending_cost_RS_per_mtr;
    private double packing_cost_RS_per_mtr;
    private double epi;
    private double ppi;
    private double finance_cost;
    private double weft_yarn_price;
    private double warp_yarn_price;
    private double warping_sizing_cost_Rs_per_mtr;
    private double total_cost_yarn_for_1_mtr;
    private double total_cost_Rs_per_mtr;
    private double value_loss_percent;
    private double value_loss_Rs_per_mtr;
    private double cost_of_fabric_Rs_per_mtr;
    private double glm_without_size;
    private double waste_realization_Rs_per_mtr;
    private double size_add_on;
    private double glm_with_size;
    private double gsm_with_size;
    private double strem_cost_RS_per_Kg;
    private double selling_price_without_commi_rs_pr_mtr;
    private double commission_percent;
    private double commission_Rs_per_mtr;
    private double cost_of_fabric_Rs_mtr;
    private double grey_width;
    private Integer approved_by_id;
    private String approved_date;
    private String approved_by_name;

    private String company_name;
    private String company_branch_name;
    private String company_address1;
    private String company_address2;
    private String company_phone_no;
    private String company_cell_no;
    private String company_EmailId;
    private String company_website;
    private String company_gst_no;
    private String company_pan_no;
    private String company_pincode;
    private String company_branch_bank_name;
    private String company_branch_bank_account_no;
    private String company_branch_bank_ifsc_code;
    private String customer_name;
    private String agent_name;
    private String cust_city_name;
    private String cust_state_name;
    private String agent_city_name;
    private String agent_state_name;

    private boolean is_delete = Boolean.FALSE;
    private boolean is_active = Boolean.TRUE;

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
