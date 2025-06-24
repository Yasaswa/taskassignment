package com.erp.MtDispatchScheduleDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mt_dispatch_schedule_details_trading")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtDispatchScheduleDetailsTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dispatch_schedule_details_transaction_id")
	private int dispatch_schedule_details_transaction_id;
	private Integer dispatch_schedule_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer dispatch_schedule_type_id;
	private String dispatch_schedule_type;
	private String dispatch_schedule_creation_type;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private Integer dispatch_schedule_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String product_material_id;
	private String sales_order_no;
	private String so_sr_no;
	private String batch_no;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private Integer sr_no;
	private Integer sales_order_details_transaction_id;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Double expected_dispatch_quantity;
	private Double expected_dispatch_weight;
	private Double actual_dispatch_quantity;
	private Double actual_dispatch_weight;
	private Double dispatch_return_quantity;
	private Double dispatch_return_weight;
	private Double pending_quantity;
	private Double pending_weight;
	private String expected_schedule_date;
	private Integer delayed_days;
	private Double dispatched_quantity;
	private Double dispatched_weight;
	private Double invoice_quantity;
	private Double invoice_weight;
	private String dispatch_schedule_item_status;
	private String dispatch_schedule_remark;
	private String remark;
	private String lot_no;
	private String count_no;
	private String set_no;
	private Integer agent_id;
	private Integer consignee_id;
	private String product_material_code;


	private String roll_no;
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
