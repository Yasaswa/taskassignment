package com.erp.DispatchSizedYarn.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Data
@Entity
@Table(name = "mtv_dispatch_challan_sized_yarn_master")
public class CDtSizedYarnMasterViewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dispatch_challan_sized_yarn_id")
    private int dispatch_challan_sized_yarn_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;

    private Integer job_type_id;
    private String job_type_name;
    private String set_no;
    private String yarn_count;
    private String product_material_id;
    private String product_material_name;
    private Integer total_ends;

    private Integer dispatch_challan_type_id;
    private String dispatch_challan_type;
    private String dispatch_challan_creation_type;
    private String dispatch_challan_no;
    private String dispatch_challan_status;
    private String dispatch_challan_date;
    private Integer dispatch_challan_version;

    private Integer customer_id;
    private Integer agent_id;
    private String agent_cell_no;

    private String company_cell_no;
    private String company_emailId;
    private String company_website;
    private String company_gst_no;
    private String company_pan_no;
    private String company_address;
    private String company_pincode;

    private String cust_branch_address1;
    private String cust_branch_gst_no;
    private String agent_name;
    private String agent_address1;

    private String customer_order_no;
    private Integer customer_contacts_ids;
    private Integer customer_state_id;
    private Integer customer_city_id;
    private Integer consignee_id;
    private Integer consignee_state_id;
    private Integer consignee_city_id;

    private String dispatch_date;
    private Integer approved_by_id;
    private String approved_date;

    private double rate;
    private double weight;

    private double issued_weight;
    private double issued_quantity;
    private double net_weight;
    private Integer sized_beams;

    private String other_terms_conditions;
    private String remark;

    private String driver_name;
    private String yarn_bill_no;
    private String vehicle_no;

    private String customer_name;
    private String customer_phone;
    private String customer_email;
    private String customer_city_name;
    private String customer_state_name;
    private String consignee_name;
    private String consignee_address;
    private String consignee_email;
    private String consignee_state_name;
    private String consignee_city_name;
    private String approved_by_name;
    private String company_name;
    private String company_branch_name;
    private String dispatch_payment_terms;
    private String dispatch_challan_status_desc;


    private String sales_dispatch_type;
    private String dispatch_hsnTax_type;
    private String dispatch_sales_type;
    private String dispatch_voucher_type;

    private boolean is_active = Boolean.TRUE;
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
    public String getCreated_by() {
        return created_by;
    }
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
    public Date getCreated_on() {
        return created_on;
    }
    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }
    public String getModified_by() {
        return modified_by;
    }
    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }
    public Date getModified_on() {
        return modified_on;
    }
    public void setModified_on(Date modified_on) {
        this.modified_on = modified_on;
    }
    public String getDeleted_by() {
        return deleted_by;
    }
    public void setDeleted_by(String deleted_by) {
        this.deleted_by = deleted_by;
    }
    public Date getDeleted_on() {
        return deleted_on;
    }
    public void setDeleted_on(Date deleted_on) {
        this.deleted_on = deleted_on;
    }
}
