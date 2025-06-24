package com.erp.XtWarpingProductionOrder.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "xt_warping_production_order")
public class CXtWarpingProductionOrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer warping_production_order_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer production_order_type_id;
	private String financial_year;
	private String warping_order_no;
	private Integer customer_id;
	private String customer_order_no;
	private String product_material_id;
	private String product_material_style;
	private String set_no;
	private Double order_quantity;
	private Double schedule_quantity;
	private Double t_ends;
	private String warping_schedule_date;
	private String sort_no;
	private Double no_of_creels;
	private String creel_type;
	private Double set_length;
	private String warping_plan_date;
	private String warping_order_status;
	private String status_remark;
	private String other_terms_conditions;
	private Integer approved_by_id;
	private String approved_date;
	private String remark;
	private Boolean is_delete = Boolean.FALSE;
	private String bottom_value;

	private String created_by;

	@CreationTimestamp
	private Date created_on;

	private String modified_by;

	@UpdateTimestamp
	private Date modified_on;

	private String deleted_by;

	private Date deleted_on;

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		if(approved_date.isEmpty() || approved_date == null){
			this.approved_date = null;
		}else{
			this.approved_date = approved_date;
		}
	}
}

