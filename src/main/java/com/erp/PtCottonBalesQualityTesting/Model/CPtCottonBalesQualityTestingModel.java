package com.erp.PtCottonBalesQualityTesting.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name = "pt_quality_testing_cotton_bales")
public class CPtCottonBalesQualityTestingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quality_testing_cotton_bales_transaction_id")
    private int quality_testing_cotton_bales_transaction_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private String quality_testing_no;
    private Date quality_testing_date;
    private String batch_no;
    private String pr_no;
    private double uhml;
    private double ul;
    private double mi;
    private double mic;
    private double str;
    private double rd;
    private double b_plus;
    private double total_neps;
    private double sc_n;
    private double sfc_n;
    private double trash;
    private double moisture;
    private String cg;
    private String quality_testing_status;
    private Integer quality_tester_id;
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
