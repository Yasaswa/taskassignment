package com.erp.MaterialTransferMaster.Model;


import lombok.*;
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
@Table(name = "ptv_material_transfer_details")
public class CMaterialTransferDetailsViewModel {

    @Id
    private Integer material_transfer_details_id;
    private Integer material_transfer_type_id;
    private String product_code;
    private String product_rm_name;
    private Double stock_quantity;
    private Double stock_weight;
    private Double transfer_quantity;
    private Double transfer_weight;
    private Double closing_balance_quantity;
    private Double closing_balance_weight;
    private String godown_name;
    private String godown_section_name;
    private String godown_section_beans_name;
    private Double material_rate;
    private Double product_material_std_weight;


    // no use in view for now
    private String transfer_no;
    private Date transfer_date;
    private Integer transfer_version;
    private Integer transfer_by_id;
    private Integer from_company_id;
    private String from_company_name;
    private String from_company_branch_id;
    private Integer to_company_id;
    private String to_company_name;
    private Integer to_company_branch_id;
    private Integer company_id;
    private String financial_year;
    private Integer company_branch_id;
    private Integer material_transfer_master_id;

    private String material_transfer_type_name;
    private String transfer_by_name;
    private String product_material_id;
    private Integer godown_id;
    private Integer godown_section_id;
    private Integer godown_section_beans_id;
    private String product_unit_id;
    private String product_unit_name;
    private String Active;
    private String Deleted;
    private Boolean is_active;
    private Boolean is_delete;
    private String created_by;
    private Date created_on;
    private String modified_by;
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;





}
