package com.erp.InternalMaterialTransferDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pt_inter_material_transfer_master")
public class CInternalMaterialTransferMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inter_material_transfer_master_id")
    private int inter_material_transfer_master_id;
    private String financial_year;
    private String inter_material_transfer_no;
    private Integer product_type_id;
    private String product_type_name;
    private Date inter_material_transfer_date;
    private Integer from_department_id;
    private Integer from_sub_department_id;
    private Integer requested_by_id;
    private Integer to_department_id;
    private Integer to_sub_department_id;
    private String inter_material_transfer_status;
    private int company_id;
    private Integer company_branch_id;

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


}

