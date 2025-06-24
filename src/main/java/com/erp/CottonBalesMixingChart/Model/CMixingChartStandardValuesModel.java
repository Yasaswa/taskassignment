package com.erp.CottonBalesMixingChart.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pt_mixing_chart_standard_values")
public class CMixingChartStandardValuesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mixing_chart_standard_values_id")
    private Integer mixing_chart_standard_values_id;
    private Integer company_id;
    private Integer company_branch_id;
    private Integer plant_id;
    private Integer mixing_by_id;
    private String financial_year;
    private String mixing_chart_no;
    private Date mixing_chart_date;

    private double max_uhml;
    private double min_uhml;
    private double max_ul;
    private double min_ul;
    private double max_mi;
    private double min_mi;
    private double max_mic;
    private double min_mic;
    private double max_str;
    private double min_str;
    private double max_rd;
    private double min_rd;
    private double max_b_plus;
    private double min_b_plus;
    private double max_total_neps;
    private double min_total_neps;
    private double max_sc_n;
    private double min_sc_n;
    private double max_sfc_n;
    private double min_sfc_n;
    private double max_trash;
    private double min_trash;
    private double max_moisture;
    private double min_moisture;

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
