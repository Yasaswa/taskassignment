package com.erp.XmProductionStoppageReasons.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "xm_production_stoppage_reasons")
public class CXmProductionStoppageReasonsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "production_stoppage_reasons_id")
	private int production_stoppage_reasons_id;
	private Integer company_branch_id;
	private Integer company_id;
	private Integer production_department_id;
	private Integer production_sub_department_id;
	private Integer section_id;
	private Integer sub_section_id;
	private String production_stoppage_reasons_type;
	private String loss_type;
	private String production_stoppage_reasons_name;
	private double std_stoppage_loss_per_hour;
	private double std_stoppage_minutes;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
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
