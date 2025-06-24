package com.erp.RawMaterial.SmProductRmBomMst.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sm_product_rm_bom_mst")
public class CSmProductRmBomMstModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_rm_bom_id")
	private int product_rm_bom_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_rm_bom_no;
	private Integer product_rm_bom_version;
	private String product_rm_bom_name;
	private Integer customer_id;
	private String customer_order_id;
	private String product_parent_rm_id;
	private String product_parent_rm_drawing_no;
	private String product_parent_rm_tech_spect;
	private Double product_parent_rm_quantity;
	private Double product_parent_rm_weight;
	private Integer product_parent_rm_unit_id;
	private String product_rm_bom_status;
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
