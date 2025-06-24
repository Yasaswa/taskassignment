package com.erp.InternalMaterialTransferDetails.Model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ptv_inter_material_transfer_master")
public class CInternalMaterialTransferMasterViewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inter_material_transfer_master_id")
    private int inter_material_transfer_master_id;
    private String inter_material_transfer_no;
    private Date inter_material_transfer_date;
    private String from_department_name;
    private String from_sub_department_name;
    private String to_department_name;
    private String to_sub_department_name;
    private String requested_by_name;
    private String inter_material_transfer_status_desc;
    private String financial_year;
    private String inter_material_transfer_status;
    private Integer from_department_id;
    private Integer from_sub_department_id;
    private Integer requested_by_id;
    private Integer to_department_id;
    private Integer to_sub_department_id;

    private Integer product_type_id;
    private String product_type_name;
    private int company_id;


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
