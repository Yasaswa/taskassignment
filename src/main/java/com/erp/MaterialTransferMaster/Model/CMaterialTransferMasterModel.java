package com.erp.MaterialTransferMaster.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "pt_material_transfer_master")
public class CMaterialTransferMasterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_transfer_master_id", nullable = false)
    private Integer material_transfer_master_id;

    private Integer material_transfer_type_id;
    private String financial_year;
    private String transfer_no;

    private Integer transfer_version;

    private String transfer_date;

    private Integer company_id;

    private Integer company_branch_id;

    private Integer from_company_id;

    private Integer from_company_branch_id;

    private Integer transfer_by_id;

    private Integer to_company_id;

    private Integer to_company_branch_id;

    private Boolean is_active;

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
    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
}
