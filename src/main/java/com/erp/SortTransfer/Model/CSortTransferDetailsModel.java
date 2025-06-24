package com.erp.SortTransfer.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mt_sort_transfer_details")
public class CSortTransferDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sort_transfer_details_id;
    private Integer sort_transfer_master_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String transfer_no;
    private String from_set_no;
    private Integer roll_no;
    private String to_set_no;
    private String from_sort_no;
    private String to_sort_no;
    private String from_style;
    private Double inspection_mtr;
    private Double weight;
    private Double average;
    private String to_style;
    private String from_product_material_id;
    private String to_product_material_id;
    private String to_product_material_name;
    private Integer product_type_id;
    private Integer godown_id;
    private Integer godown_section_id;
    private Integer godown_section_beans_id;
    private String inspection_production_date;
    private String sizing_beam_no;
    private Integer machine_id;
    private String to_sizing_beam_no;
    private Integer to_machine_id;
    private String remark;
    private Integer product_material_stock_unit_id;
    private Integer product_material_packing_id;

    @JsonProperty("is_delete")
    private boolean is_delete = false;
    @Column(name = "created_by", updatable = false)
    private String created_by = "1";
    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date created_on;
    private String modified_by;
    @UpdateTimestamp
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;

}
