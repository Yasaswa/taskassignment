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
@Table(name = "sm_product_fg_bom_mst")
public class CSmProductFgBomMstModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_fg_bom_id")
	private int product_fg_bom_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_fg_bom_no;
	private Integer product_fg_bom_version;
	private String product_fg_bom_name;
	private Integer customer_id;
	private String customer_order_id;
	private String product_parent_fg_id;
	private String product_parent_fg_drawing_no;
	private String product_parent_fg_tech_spect;
	private Double product_parent_fg_quantity;
	private Double product_parent_fg_weight;
	private Integer product_parent_fg_unit_id;
	private String product_fg_bom_status;
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
