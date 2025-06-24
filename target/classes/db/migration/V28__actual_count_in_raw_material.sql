ALTER TABLE sm_product_rm ADD actual_count decimal(18, 4) NULL;
ALTER TABLE sm_product_rm CHANGE actual_count actual_count decimal(18, 4) NULL AFTER is_active;


create or replace
algorithm = UNDEFINED view `smv_product_rm` as
select
    `b`.`product_id` as `product_id`,
    `b`.`product_rm_name` as `product_rm_name`,
    `b`.`product_rm_code` as `product_rm_code`,
    `b`.`product_rm_short_name` as `product_rm_short_name`,
    `b`.`product_rm_print_name` as `product_rm_print_name`,
    `b`.`product_rm_tech_spect` as `product_rm_tech_spect`,
    `b`.`actual_count` as `actual_count`,
    `b`.`product_rm_oem_part_code` as `product_rm_oem_part_code`,
    `b`.`product_rm_our_part_code` as `product_rm_our_part_code`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `p`.`product_type_name` as `product_type_name`,
    `p`.`product_type_short_name` as `product_type_short_name`,
    `p`.`product_type_group` as `product_type_group`,
    `pc1`.`product_category1_name` as `product_category1_name`,
    `pc1`.`product_category1_short_name` as `product_category1_short_name`,
    `h`.`hsn_sac_code` as `product_rm_hsn_sac_code`,
    `h`.`hsn_sac_rate` as `product_rm_hsn_sac_rate`,
    `h`.`hsn_sac_type` as `product_rm_hsn_sac_type`,
    `h`.`is_exampted` as `product_rm_hsn_sac_is_exampted`,
    `u1`.`product_unit_name` as `product_rm_purchase_unit_name`,
    `u1`.`product_unit_name` as `product_rm_sales_unit_name`,
    `u1`.`product_unit_name` as `product_rm_stock_unit_name`,
    `b`.`product_rm_item_sr_no` as `product_rm_item_sr_no`,
    `b`.`product_rm_drawing_no` as `product_rm_drawing_no`,
    `b`.`product_rm_model_no` as `product_rm_model_no`,
    `b`.`product_rm_bar_code` as `product_rm_bar_code`,
    `b`.`bom_applicable` as `bom_applicable`,
    `b`.`product_rm_qr_code` as `product_rm_qr_code`,
    `b`.`product_consumption_mode` as `product_consumption_mode`,
    `b`.`product_origin_type` as `product_origin_type`,
    `b`.`product_origin_country` as `product_origin_country`,
    `p1`.`product_packing_name` as `product_rm_packing_name`,
    `p1`.`quantity_per_packing` as `product_rm_quantity_per_packing`,
    `p1`.`weight_per_packing` as `product_rm_weight_per_packing`,
    `g`.`godown_name` as `godown_name`,
    `g`.`godown_short_name` as `godown_short_name`,
    0 as `godown_area`,
    `g`.`godown_section_count` as `godown_section_count`,
    `gs`.`godown_section_name` as `godown_section_name`,
    `gs`.`godown_section_short_name` as `godown_section_short_name`,
    0 as `godown_section_area`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `gsb`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    0 as `godown_section_beans_area`,
    `b`.`remark` as `remark`,
    case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `b`.`is_active` as `is_active`,
    `b`.`is_delete` as `is_delete`,
    `b`.`created_by` as `created_by`,
    `b`.`created_on` as `created_on`,
    `b`.`modified_by` as `modified_by`,
    `b`.`modified_on` as `modified_on`,
    `b`.`deleted_by` as `deleted_by`,
    `b`.`deleted_on` as `deleted_on`,
    `b`.`company_id` as `company_id`,
    `b`.`company_branch_id` as `company_branch_id`,
    `b`.`product_rm_id` as `product_rm_id`,
    `b`.`product_type_id` as `product_type_id`,
    `b`.`product_category1_id` as `product_category1_id`,
    `b`.`product_rm_hsn_sac_code_id` as `product_rm_hsn_sac_code_id`,
    `b`.`product_rm_purchase_unit_id` as `product_rm_purchase_unit_id`,
    `b`.`product_rm_sales_unit_id` as `product_rm_sales_unit_id`,
    `b`.`product_rm_stock_unit_id` as `product_rm_stock_unit_id`,
    `b`.`product_rm_packing_id` as `product_rm_packing_id`,
    `b`.`godown_id` as `godown_id`,
    `b`.`godown_section_id` as `godown_section_id`,
    `b`.`godown_section_beans_id` as `godown_section_beans_id`,
    `b`.`product_rm_name` as `field_name`,
    `b`.`product_rm_id` as `field_id`
from
    (((((((((`sm_product_rm` `b`
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `b`.`company_id`
        and `v`.`company_branch_id` = `b`.`company_branch_id`))
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `b`.`product_type_id`
        and `p`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `b`.`product_rm_hsn_sac_code_id`
        and `h`.`is_delete` = 0))
left join `sm_product_unit` `u1` on
    (`u1`.`product_unit_id` = `b`.`product_rm_purchase_unit_id`
        and `u1`.`is_delete` = 0))
left join `sm_product_category1` `pc1` on
    (`pc1`.`is_delete` = 0
        and `pc1`.`product_category1_id` = `b`.`product_category1_id`))
left join `sm_product_packing` `p1` on
    (`p1`.`product_packing_id` = `b`.`product_rm_packing_id`
        and `p1`.`is_delete` = 0))
left join `cm_godown` `g` on
    (`g`.`godown_id` = `b`.`godown_id`))
left join `cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `b`.`godown_section_id`))
left join `cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `b`.`godown_section_beans_id`))
where
    `b`.`is_delete` = 0;


-- smv_product_fg_rpt_details source

create or replace
algorithm = UNDEFINED view `smv_product_fg_rpt_details` as
select
    concat(ifnull(`v`.`product_fg_name`, ''), ':Product Name:Y:C:smv_product_fg:F') as `product_fg_name`,
    concat(ifnull(`v`.`product_fg_code`, ''), ':Product Code:O:N:') as `product_fg_code`,
    concat(ifnull(`v`.`product_fg_short_name`, ''), ':Short Name:O:N:') as `product_fg_short_name`,
    concat(ifnull(`v`.`product_fg_tech_spect`, ''), ':Tech. Spect:N:N') as `product_fg_tech_spect`,
    concat(ifnull(`v`.`product_fg_oem_part_code`, ''), ':OEM Part Code:N:N') as `product_fg_oem_part_code`,
    concat(ifnull(`v`.`product_fg_our_part_code`, ''), ':Our Part Code:N:N') as `product_fg_our_part_code`,
    concat(ifnull(`v`.`product_fg_item_sr_no`, ''), ':Item Sr. No.:N:N:') as `product_fg_item_sr_no`,
    concat(ifnull(`v`.`product_fg_drawing_no`, ''), ':Drawing No:N:N:') as `product_fg_drawing_no`,
    concat(ifnull(`v`.`product_fg_model_no`, ''), ':Model No:N:N:') as `product_fg_model_no`,
    concat(ifnull(`v`.`product_fg_bar_code`, ''), ':Bar Code:O:N:') as `product_fg_bar_code`,
    concat(ifnull(`v`.`product_fg_qr_code`, ''), ':QR Code:O:N:') as `product_fg_qr_code`,
    concat(ifnull(`v`.`product_consumption_mode`, ''), ':Consumption:N:N') as `product_consumption_mode`,
    concat(ifnull(`v`.`product_origin_type`, ''), ':Origin:N:N') as `product_origin_type`,
    concat(ifnull(`v`.`product_origin_country`, ''), ':Origin Country:N:N') as `product_origin_country`,
    concat(ifnull(`v`.`product_type_name`, ''), ':Product Type:Y:C:smv_product_type') as `product_type_name`,
    concat(ifnull(`v`.`product_fg_hsn_sac_code`, ''), ':HSN Code:Y:C:cmv_hsn_sac:F') as `product_fg_hsn_sac_code`,
    concat(ifnull(`v`.`product_fg_hsn_sac_rate`, ''), ':HSN %:Y:T') as `product_fg_hsn_sac_rate`,
    concat(ifnull(`v`.`product_fg_purchase_unit_name`, ''), ':Purchase Unit:Y:C:smv_product_unit:F') as `product_fg_purchase_unit_name`,
    concat(ifnull(`v`.`product_fg_sales_unit_name`, ''), ':Sales Unit:Y:C:smv_product_unit:F') as `product_fg_sales_unit_name`,
    concat(ifnull(`v`.`product_fg_packing_name`, ''), ':Packing:Y:C:smv_product_packing:F') as `product_fg_packing_name`,
    concat(ifnull(`v`.`product_fg_technical_name`, ''), ':Tech. Name:N:N') as `product_fg_technical_name`,
    concat(ifnull(`v`.`assembly_scrap_percent`, ''), ':Scrap %:O:N:') as `assembly_scrap_percent`,
    concat(ifnull(`v`.`product_category1_name`, ''), ':Category 1:Y:C:smv_product_category1:F') as `product_category1_name`,
    concat(ifnull(`v`.`product_category2_name`, ''), ':Category 2:Y:C:smv_product_category2:F') as `product_category2_name`,
    concat(ifnull(`v`.`product_category3_name`, ''), ':Category 3:N:N:') as `product_category3_name`,
    concat(ifnull(`v`.`product_category4_name`, ''), ':Category 4:N:N:') as `product_category4_name`,
    concat(ifnull(`v`.`product_category5_name`, ''), ':Category 5:N:N:') as `product_category5_name`,
    concat(ifnull(`v`.`product_material_type_name`, ''), ':Material Type:N:N:') as `product_material_type_name`,
    concat(ifnull(`v`.`product_material_grade_name`, ''), ':Grade:N:N:') as `product_material_grade_name`,
    concat(ifnull(`v`.`product_material_shape_name`, ''), ':Shape:N:N:') as `product_material_shape_name`,
    concat(ifnull(`v`.`product_fg_gross_weight`, ''), ':Gross Weight:Y:T') as `product_fg_gross_weight`,
    concat(ifnull(`v`.`product_fg_net_weight`, ''), ':Net Weight:Y:T') as `product_fg_net_weight`,
    concat(ifnull(`v`.`product_fg_std_weight`, ''), ':Std. Weight:Y:T') as `product_fg_std_weight`,
    concat(ifnull(`v`.`product_fg_volume`, ''), ':Volume:Y:T') as `product_fg_volume`,
    concat(ifnull(`v`.`product_fg_mrp`, ''), ':MRP:Y:T') as `product_fg_mrp`,
    concat(ifnull(`v`.`product_fg_landed_price`, ''), ':Landed Price:Y:T') as `product_fg_landed_price`,
    concat(ifnull(`v`.`product_fg_avg_price`, ''), ':Avg. Price:Y:T') as `product_fg_avg_price`,
    concat(ifnull(`v`.`product_fg_std_profit_percent`, ''), ':Std. Profit %:Y:T') as `product_fg_std_profit_percent`,
    concat(ifnull(`v`.`product_fg_std_discount_percent`, ''), ':Std. Discount %:Y:T') as `product_fg_std_discount_percent`,
    concat(ifnull(`v`.`product_fg_moq`, ''), ':MOQ:Y:T') as `product_fg_moq`,
    concat(ifnull(`v`.`product_fg_mpq`, ''), ':MPQ:Y:T') as `product_fg_mpq`,
    concat(ifnull(`v`.`product_fg_mov`, ''), ':MOV:Y:T') as `product_fg_mov`,
    concat(ifnull(`v`.`product_fg_eoq`, ''), ':EOQ:Y:T') as `product_fg_eoq`,
    concat(ifnull(`v`.`product_fg_min_stock_level`, ''), ':Minimum Level:Y:T') as `product_fg_min_stock_level`,
    concat(ifnull(`v`.`product_fg_max_stock_level`, ''), ':Maximum Level:Y:T') as `product_fg_max_stock_level`,
    concat(ifnull(`v`.`product_fg_reorder_stock_level`, ''), ':Reorder Level:Y:T') as `product_fg_reorder_stock_level`,
    concat(ifnull(`v`.`product_fg_depriciation_percent`, ''), ':Depriciation %:Y:T') as `product_fg_depriciation_percent`,
    concat(ifnull(`v`.`sales_tolerance_percent`, ''), ':Tolerance %:Y:T') as `sales_tolerance_percent`,
    concat(ifnull(`v`.`product_fg_abc_analysis`, ''), ':ABC Analysis:Y:H:(A,B,C)') as `product_fg_abc_analysis`,
    concat(ifnull(`v`.`qa_required`, ''), ':QA Required:Y:H:(Yes,No)') as `qa_required`,
    concat(ifnull(`v`.`test_certificate_required`, ''), ':Test Certificate:Y:H:(Yes,No)') as `test_certificate_required`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ''), ':Is Active:Y:H:(Active, In Active)') as `Active`,
    concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Is Deleted:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:N:N') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Branch Name:N:N') as `company_branch_name`,
    concat(ifnull(`v`.`product_fg_print_name`, ''), ':Print Name:O:N:') as `product_fg_print_name`,
    concat(ifnull(`v`.`product_fg_hsn_sac_is_exampted`, ''), ':HSN Exempted:O:N:') as `product_fg_hsn_sac_is_exampted`,
    concat(ifnull(`v`.`product_fg_stock_unit_name`, ''), ':Stock Unit:Y:C:smv_product_unit:F') as `product_fg_stock_unit_name`,
    concat(ifnull(`v`.`product_fg_name`, ''), ':Alt. Product:Y:C:smv_product_fg') as `product_alternate_fg_name`,
    concat(ifnull(`v`.`product_fg_quantity_per_packing`, ''), ':Qty Per Packing:O:N:') as `product_fg_quantity_per_packing`,
    concat(ifnull(`v`.`product_fg_weight_per_packing`, ''), ':weight Per Packing:O:N:') as `product_fg_weight_per_packing`,
    concat(ifnull(`v`.`product_material_colour`, ''), ':Colour:N:N:') as `product_material_colour`,
    concat(ifnull(`v`.`product_fg_id`, ''), ':Material Id:O:N:') as `product_fg_id`
from
    `smv_product_fg_details` `v`
limit 1;


-- smv_product_fg_rpt_summary source

create or replace
algorithm = UNDEFINED view `smv_product_fg_rpt_summary` as
select
    concat(ifnull(`v`.`product_fg_name`, ''), ':Product Name:Y:C:smv_product_fg:F') as `product_fg_name`,
    concat(ifnull(`v`.`product_fg_technical_name`, ''), ':Technical Name:Y:C:smv_product_fg_technical:F') as `product_fg_technical_name`,
    concat(ifnull(`v`.`product_fg_code`, ''), ':Product Code:O:N') as `product_fg_code`,
    concat(ifnull(`v`.`product_fg_drawing_no`, ''), ':Drawing No:N:N') as `product_fg_drawing_no`,
    concat(ifnull(`v`.`product_type_name`, ''), ':Product Type:Y:C:smv_product_type:F') as `product_type_name`,
    concat(ifnull(`v`.`product_fg_oem_part_code`, ''), ':OEM Part Code:N:N') as `product_fg_oem_part_code`,
    concat(ifnull(`v`.`product_fg_our_part_code`, ''), ':Our Part Code:N:N') as `product_fg_our_part_code`,
    concat(ifnull(`v`.`product_fg_tech_spect`, ''), ':TEch. Spect.:N:N') as `product_fg_tech_spect`,
    concat(ifnull(`v`.`product_fg_hsn_sac_code`, ''), ':HSN_SAC Code :Y:C:cmv_hsn_sac:F') as `product_fg_hsn_sac_code`,
    concat(ifnull(`v`.`product_fg_stock_unit_name`, ''), ':Unit:Y:C:smv_product_unit:F') as `product_fg_stock_unit_name`,
    concat(ifnull(`v`.`product_category1_name`, ''), ':Category1:Y:C:smv_product_category1:F') as `product_category1_name`,
    concat(ifnull(`v`.`product_category2_name`, ''), ':Category 2:Y:C:smv_product_category2:F') as `product_category2_name`,
    concat(ifnull(`v`.`product_category3_name`, ''), ':Category 3:N:N') as `product_category3_name`,
    concat(ifnull(`v`.`product_category4_name`, ''), ':Category 4:N:N') as `product_category4_name`,
    concat(ifnull(`v`.`product_category5_name`, ''), ':Category 5:N:N') as `product_category5_name`,
    concat(ifnull(`v`.`product_material_type_name`, ''), ':Material Type::N:N') as `product_material_type_name`,
    concat(ifnull(`v`.`product_material_grade_name`, ''), ':Grade:N:N') as `product_material_grade_name`,
    concat(ifnull(`v`.`product_material_shape_name`, ''), ':Shape:N:N') as `product_material_shape_name`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name::N:N') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch::N:N') as `company_branch_name`,
    concat(ifnull(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ''), ':Is Active:Y:H:(Active, In Active)') as `Active`,
    concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Is Deleted:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:N:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:N:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:N:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:N:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:N:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:N:N:') as `deleted_on`,
    concat(ifnull(`v`.`product_fg_id`, ''), ':Material Id:O:N:') as `product_fg_id`
from
    `smv_product_fg_summary` `v`
limit 1;
