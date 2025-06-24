package com.erp.SortTransfer.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mtv_sort_transfer_details")
public class CSortTransferDetailsViewModel {
    @Id
    private Integer sort_transfer_details_id;

    private Integer sort_transfer_master_id;
    private Integer company_id;
    private String financial_year;
    private Integer company_branch_id;
    private String transfer_no;
    private String from_set_no;
    private String to_set_no;
    private String from_sort_no;
    private String to_sort_no;
    private String from_style;
    private Double inspection_mtr;
    private Double weight;
    private Double average;
    private String remark;
    private boolean is_delete;
    private String created_by;
    private Date created_on;
    private String modified_by;
    private Date modified_on;
    private String deleted_by;
    private Date deleted_on;
    private String transfer_by;
    private Date transfer_date;
    private String company_name;
    private String company_branch_name;
    private Integer roll_no;
    private String to_style;
    private String sizing_beam_no;
    private Integer machine_id;
    private String to_sizing_beam_no;
    private Integer to_machine_id;
    private String product_type_name;
    private String product_type_short_name;
    private String transfer_by_name;
}
