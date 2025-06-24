package com.erp.XtStoppageProduction.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "xt_weaving_production_warping_stoppage")
public class CXtWeavingProductionStoppageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_stoppage_id")
	private int weaving_production_stoppage_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String production_set_no;
	private String production_date;
	private Integer section_id;
	private Integer sub_section_id;
	private String shift;
	private Integer machine_id;
	private Integer production_stoppage_reasons_id;
	private Double std_stoppage_loss_per_hour;
	private String loss_type;
	private Double std_stoppage_loss_kg;
	private Double stoppage_production_loss_kg;
	private Double actual_production_loss_kg;
	private String from_time;
	private String to_time;
	private String total_time;
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
