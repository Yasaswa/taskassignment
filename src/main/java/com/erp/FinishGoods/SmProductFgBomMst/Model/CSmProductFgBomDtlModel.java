package com.erp.FinishGoods.SmProductFgBomMst.Model;

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
@Table(name = "sm_product_fg_bom_dtl")
public class CSmProductFgBomDtlModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_fg_bom_id")
	private int product_fg_bom_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_fg_bom_no;
	private Integer product_fg_bom_version;
	private String product_child_rm_id;
	private Double product_child_rm_quantity;
	private String product_child_rm_drawing_no;
	private String product_child_rm_tech_spect;
	private Double product_child_rm_weight;
	private Integer product_child_rm_unit_id;
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
