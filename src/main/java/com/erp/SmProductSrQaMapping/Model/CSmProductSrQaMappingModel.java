package com.erp.SmProductSrQaMapping.Model;

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
@Table(name = "sm_product_sr_qa_mapping")
public class CSmProductSrQaMappingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_sr_qa_mapping_id")
	private int product_sr_qa_mapping_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_sr_id;
	private Integer product_qa_parameters_id;
	private double product_sr_qa_from_range;
	private double product_sr_qa_to_range;
	private double product_sr_qa_from_deviation_percent;
	private double product_sr_qa_to_deviation_percent;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
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
