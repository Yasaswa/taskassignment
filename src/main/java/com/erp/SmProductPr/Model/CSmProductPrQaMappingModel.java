package com.erp.SmProductPr.Model;

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
@Table(name = "sm_product_pr_qa_mapping")
public class CSmProductPrQaMappingModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_pr_qa_mapping_id")
	private int product_pr_qa_mapping_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_pr_id;
	private Integer product_pr_parameters_id;
	private Double product_pr_qa_from_range;
	private Double product_pr_qa_to_range;
	private Double product_pr_qa_from_deviation_percent;
	private Double product_pr_qa_to_deviation_percent;
	private String remark;
	private Boolean is_active = Boolean.TRUE;
	private Boolean is_delete = Boolean.FALSE;
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

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}


}
