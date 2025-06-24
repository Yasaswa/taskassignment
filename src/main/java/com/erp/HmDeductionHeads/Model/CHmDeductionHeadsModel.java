package com.erp.HmDeductionHeads.Model;

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
@Table(name = "hm_deduction_heads")
public class CHmDeductionHeadsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "deduction_heads_id")
	private int deduction_heads_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String deduction_head_name;
	private String deduction_type;
	private String deduction_code;
	private Integer deduction_head_version;
	private String deduction_head_short_name;
	private Integer head_position;
	private String calculation_type;
	private double calculation_value;
	private String salary_parameter1;
	private String salary_parameter2;
	private String salary_parameter3;
	private String salary_parameter4;
	private String salary_parameter5;
	private String salary_parameter6;
	private String salary_parameter7;
	private String salary_parameter8;
	private String salary_parameter9;
	private String salary_parameter10;
	private String formula;
	private String remark;
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
