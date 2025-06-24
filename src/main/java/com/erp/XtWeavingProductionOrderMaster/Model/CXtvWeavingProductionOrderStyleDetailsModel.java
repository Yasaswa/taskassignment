package com.erp.XtWeavingProductionOrderMaster.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "xtv_weaving_production_order_style_details")
public class CXtvWeavingProductionOrderStyleDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_style_id")
	private Integer weaving_production_style_id;
	private String sales_order_no;
	private String customer_name;
	private String set_no;
	private String sort_no;
	private double schedule_quantity;
	private Date schedule_date;
	private String style_variant1;
	private Double style_variant1_qty;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_material_id;
	private Integer weaving_production_order_id;




}

