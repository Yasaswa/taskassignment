package com.erp.XtBeamInwards.Model;

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
@Table(name = "xtv_beam_inwards_table")
public class CXtBeamInwardsViewModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beam_inwards_id")
    private Integer beam_inwards_id;
    private Integer company_id;
    private Integer company_branch_id;
    private String financial_year;
    private Integer customer_id;
    private String beam_inwards_date;
    private String customer_name;
    private String customer_short_name;
    private String section;
    private Integer beam_type;
    private String beam_no;
    private double beam_width;
    private String beam_status;
    private String beam_inward_type;
    private String customer_order_no;
    private double tare_weight;

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
