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
@Table(name = "mt_sort_transfer_master")
public class CSortTransferMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sort_transfer_master_id;

    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String transfer_no;
    private Date transfer_date;
    private String from_sort_no;
    private String to_sort_no;
    private String transfer_by;
    private String remark;

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
