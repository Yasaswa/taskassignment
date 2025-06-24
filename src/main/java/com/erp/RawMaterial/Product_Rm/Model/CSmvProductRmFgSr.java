package com.erp.RawMaterial.Product_Rm.Model;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Immutable
@Table(name = "smv_product_rm_fg_sr")
public class CSmvProductRmFgSr {


		@Id
		private Long id;

		@Column(name = "product_type_group")
		private String product_type_group;

		@Column(name = "product_type_name")
		private String product_type_name;

		@Column(name = "product_material_category1_name")
		private String product_material_category1_name;

		@Column(name = "product_material_id")
		private Long product_material_id;

		@Column(name = "product_material_code")
		private String product_material_code;

		@Column(name = "product_material_name")
		private String product_material_name;

		@Column(name = "product_material_short_name")
		private String product_material_short_name;

		@Column(name = "product_material_tech_spect")
		private String product_material_tech_spect;

		@Column(name = "product_material_stock_unit_name")
		private String product_material_stock_unit_name;

		@Column(name = "stock_quantity")
		private Double stock_quantity;

		@Column(name = "stock_weight")
		private Double stock_weight;

		@Column(name = "customer_stock_quantity")
		private Double customer_stock_quantity;

		@Column(name = "customer_stock_weight")
		private Double customer_stock_weight;

		@Column(name = "lead_time")
		private Integer lead_time;

		@Column(name = "product_material_moq")
		private Integer product_material_moq;

		@Column(name = "product_material_mrp")
		private Double product_material_mrp;

		@Column(name = "product_material_landed_price")
		private Double product_material_landed_price;

		@Column(name = "product_material_technical_name")
		private String product_material_technical_name;

		@Column(name = "product_material_type_short_name")
		private String product_material_type_short_name;

		@Column(name = "product_material_category2_name")
		private String product_material_category2_name;

		@Column(name = "product_material_category3_name")
		private String product_material_category3_name;

		@Column(name = "product_material_category4_name")
		private String product_material_category4_name;

		@Column(name = "product_material_category5_name")
		private String product_material_category5_name;

		@Column(name = "product_material_packing_name")
		private String product_material_packing_name;

		@Column(name = "product_material_make_name")
		private String product_material_make_name;

		@Column(name = "product_material_type_name")
		private String product_material_type_name;

		@Column(name = "product_material_grade_name")
		private String product_material_grade_name;

		@Column(name = "product_material_shape_name")
		private String product_material_shape_name;

		@Column(name = "product_material_oem_part_code")
		private String product_material_oem_part_code;

		@Column(name = "product_material_our_part_code")
		private String product_material_our_part_code;

		@Column(name = "product_material_drawing_no")
		private String product_material_drawing_no;

		@Column(name = "product_material_std_weight")
		private Double product_material_std_weight;

		@Column(name = "product_material_std_hours")
		private Integer product_material_std_hours;

		@Column(name = "process_duration")
		private Integer process_duration;

		@Column(name = "product_std_profit_percent")
		private Double product_std_profit_percent;

		@Column(name = "product_std_discount_percent")
		private Double product_std_discount_percent;

		@Column(name = "Active")
		private String active;

		@Column(name = "Deleted")
		private String deleted;

		@Column(name = "godown_name")
		private String godown_name;

		@Column(name = "godown_short_name")
		private String godown_short_name;

		@Column(name = "godown_area")
		private String godown_area;

		@Column(name = "godown_section_name")
		private String godown_section_name;

		@Column(name = "godown_section_short_name")
		private String godown_section_short_name;

		@Column(name = "godown_section_area")
		private String godown_section_area;

		@Column(name = "godown_section_beans_name")
		private String godown_section_beans_name;

		@Column(name = "godown_section_beans_short_name")
		private String godown_section_beans_short_name;

		@Column(name = "godown_section_beans_area")
		private String godown_section_beans_area;

		@Column(name = "product_material_hsn_sac_code")
		private String product_material_hsn_sac_code;

		@Column(name = "product_material_hsn_sac_rate")
		private Double product_material_hsn_sac_rate;

		@Column(name = "company_name")
		private String company_name;

		@Column(name = "company_branch_name")
		private String company_branch_name;

		@Column(name = "product_category1_id")
		private Long product_category1_id;

		@Column(name = "product_type_id")
		private Long product_type_id;

		@Column(name = "product_material_stock_unit_id")
		private Long product_material_stock_unit_id;

		@Column(name = "product_material_packing_id")
		private Long product_material_packing_id;

		@Column(name = "product_material_hsn_sac_code_id")
		private Long product_material_hsn_sac_code_id;

		@Column(name = "product_material_category2_id")
		private Long product_material_category2_id;

		@Column(name = "product_material_category3_id")
		private Long product_material_category3_id;

		@Column(name = "product_material_category4_id")
		private Long product_material_category4_id;

		@Column(name = "product_material_category5_id")
		private Long product_material_category5_id;

		@Column(name = "product_material_make_id")
		private Long product_material_make_id;

		@Column(name = "product_material_type_id")
		private Long product_material_type_id;

		@Column(name = "product_material_grade_id")
		private Long product_material_grade_id;

		@Column(name = "product_material_shape_id")
		private Long product_material_shape_id;

		@Column(name = "company_id")
		private Long company_id;

		@Column(name = "company_branch_id")
		private Long company_branch_id;

		@Column(name = "godown_id")
		private Long godown_id;

		@Column(name = "godown_section_id")
		private Long godown_section_id;

		@Column(name = "godown_section_beans_id")
		private Long godown_section_beans_id;

		@Column(name = "bom_applicable")
		private String bom_applicable;

		@Column(name = "is_active")
		private Boolean is_active;

		@Column(name = "is_delete")
		private Boolean is_delete;

		@Column(name = "field_name")
		private String field_name;

		@Column(name = "field_id")
		private Long field_id;




}
