package com.erp.StRawMaterialReturnMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "st_indent_material_issue_return_details")
public class CStRawMaterialReturnDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_return_details_transaction_id")
	private Integer issue_return_details_transaction_id;

	@Column(name = "issue_return_master_transaction_id")
	private Integer issue_return_master_transaction_id;

	@Column(name = "company_id")
	private Integer company_id;

	@Column(name = "company_branch_id")
	private Integer company_branch_id;

	@Column(name = "financial_year")
	private String financial_year;

	@Column(name = "issue_return_no")
	private String issue_return_no;

	@Column(name = "material_type")
	private String material_type;

	@Column(name = "issue_batch_no")
	private String issue_batch_no;

	@Column(name = "product_material_id")
	private String product_material_id;

	@Column(name = "product_material_issue_return_quantity")
	private Double product_material_issue_return_quantity;

	@Column(name = "product_material_issue_return_weight")
	private Double product_material_issue_return_weight;

	@Column(name = "product_material_issue_return_boxes")
	private Integer product_material_issue_return_boxes;

	@Column(name = "product_material_receipt_quantity")
	private Double product_material_receipt_quantity;

	@Column(name = "product_material_receipt_weight")
	private Double product_material_receipt_weight;

	@Column(name = "product_material_receipt_boxes")
	private Integer product_material_receipt_boxes = 0;

	@Column(name = "issue_return_item_status")
	private String issue_return_item_status;

	@Column(name = "godown_id")
	private String godown_id;

	@Column(name = "godown_section_id")
	private String godown_section_id;

	@Column(name = "godown_section_beans_id")
	private String godown_section_beans_id;

	@Column(name = "issue_return_remark")
	private String issue_return_remark;

	@Column(name = "material_batch_rate")
	private Double material_batch_rate;

	@Column(name = "product_category1_id")
	private Integer product_category1_id;

	@Column(name = "product_category2_id")
	private Integer product_category2_id;

	@Column(name = "product_material_unit_id")
	private Integer product_material_unit_id;

	@Column(name = "is_active", columnDefinition = "boolean default true")
	private boolean is_active = true;

	@Column(name = "is_delete", columnDefinition = "boolean default false")
	private boolean is_delete = false;

	@Column(name = "created_by", updatable = false)
	private String created_by;

	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;

	@Column(name = "modified_by")
	private String modified_by;

	@UpdateTimestamp
	@Column(name = "modified_on")
	private Date modified_on;

	@Column(name = "deleted_by")
	private String deleted_by;

	@Column(name = "deleted_on")
	private Date deleted_on;

	@Column(name = "cone_per_wt")
	private Double cone_per_wt;

	@Column(name = "goods_receipt_no")
	private String goods_receipt_no;

	@Column(name = "creel_no")
	private String creel_no;

	@Column(name = "supplier_id")
	private Integer supplier_id;

	@Column(name = "issue_return_date")
	private String issue_return_date;

}