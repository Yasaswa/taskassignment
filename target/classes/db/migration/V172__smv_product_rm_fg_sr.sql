-- erp_dev_temp_1.smv_product_rm_fg_sr source

create or replace
algorithm = UNDEFINED view `smv_product_rm_fg_sr` as
select
    `rmt`.`product_type_group` as `product_type_group`,
    `rmt`.`product_type_name` as `product_type_name`,
    `rmt`.`product_category1_name` as `product_material_category1_name`,
    `rm`.`product_rm_id` as `product_material_id`,
    `rm`.`product_rm_code` as `product_material_code`,
    `rm`.`product_rm_name` as `product_material_name`,
    `rm`.`product_rm_short_name` as `product_material_short_name`,
    `rm`.`product_rm_tech_spect` as `product_material_tech_spect`,
    `u3`.`product_unit_name` as `product_material_stock_unit_name`,
    ifnull(null, 0) as `stock_quantity`,
    ifnull(null, 0) as `stock_weight`,
    ifnull(null, 0) as `customer_stock_quantity`,
    ifnull(null, 0) as `customer_stock_weight`,
    `rmc`.`lead_time` as `lead_time`,
    `rmc`.`product_rm_moq` as `product_material_moq`,
    `rmc`.`product_rm_mrp` as `product_material_mrp`,
    `rmc`.`product_rm_landed_price` as `product_material_landed_price`,
    `rmt`.`product_rm_technical_name` as `product_material_technical_name`,
    `rmt`.`product_type_short_name` as `product_material_type_short_name`,
    `rmt`.`product_category2_name` as `product_material_category2_name`,
    `rmt`.`product_category3_name` as `product_material_category3_name`,
    `rmt`.`product_category4_name` as `product_material_category4_name`,
    `rmt`.`product_category5_name` as `product_material_category5_name`,
    `p1`.`product_packing_name` as `product_material_packing_name`,
    `rmt`.`product_make_name` as `product_material_make_name`,
    `rmt`.`product_material_type_name` as `product_material_type_name`,
    `rmt`.`product_material_grade_name` as `product_material_grade_name`,
    `rmt`.`product_material_shape_name` as `product_material_shape_name`,
    `rm`.`product_rm_oem_part_code` as `product_material_oem_part_code`,
    `rm`.`product_rm_our_part_code` as `product_material_our_part_code`,
    `rmt`.`product_rm_drawing_no` as `product_material_drawing_no`,
    `rmc`.`product_rm_std_weight` as `product_material_std_weight`,
    1 as `product_material_std_hours`,
    1 as `process_duration`,
    0 as `product_std_profit_percent`,
    0 as `product_std_discount_percent`,
    case
        `rm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `rm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `g`.`godown_name` as `godown_name`,
    `g`.`godown_short_name` as `godown_short_name`,
    `g`.`godown_area` as `godown_area`,
    `gs`.`godown_section_name` as `godown_section_name`,
    `gs`.`godown_section_short_name` as `godown_section_short_name`,
    `gs`.`godown_section_area` as `godown_section_area`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `gsb`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    `gsb`.`godown_section_beans_area` as `godown_section_beans_area`,
    `h`.`hsn_sac_code` as `product_material_hsn_sac_code`,
    `h`.`hsn_sac_rate` as `product_material_hsn_sac_rate`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `rm`.`product_category1_id` as `product_material_category1_id`,
    `rm`.`product_type_id` as `product_type_id`,
    `rm`.`product_rm_stock_unit_id` as `product_material_stock_unit_id`,
    `rm`.`product_rm_packing_id` as `product_material_packing_id`,
    `rm`.`product_rm_hsn_sac_code_id` as `product_material_hsn_sac_code_id`,
    `rmt`.`product_category2_id` as `product_material_category2_id`,
    `rmt`.`product_category3_id` as `product_material_category3_id`,
    `rmt`.`product_category4_id` as `product_material_category4_id`,
    `rmt`.`product_category5_id` as `product_material_category5_id`,
    `rmt`.`product_make_id` as `product_material_make_id`,
    `rmt`.`product_material_type_id` as `product_material_type_id`,
    `rmt`.`product_material_grade_id` as `product_material_grade_id`,
    `rmt`.`product_material_shape_id` as `product_material_shape_id`,
    `rm`.`company_id` as `company_id`,
    `rm`.`company_branch_id` as `company_branch_id`,
    `rm`.`godown_id` as `godown_id`,
    `rm`.`godown_section_id` as `godown_section_id`,
    `rm`.`godown_section_beans_id` as `godown_section_beans_id`,
    'No' as `bom_applicable`,
    `rm`.`is_active` as `is_active`,
    `rm`.`is_delete` as `is_delete`,
    `rmc`.`cost_center_id` as `cost_center_id`,
    `rm`.`product_rm_name` as `field_name`,
    `rm`.`actual_count` as `actual_count`,
    `rm`.`product_rm_id` as `field_id`
from
    (((((((((`sm_product_rm` `rm`
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `rm`.`company_id`
        and `v`.`company_branch_id` = `rm`.`company_branch_id`))
left join `sm_product_unit` `u3` on
    (`u3`.`product_unit_id` = `rm`.`product_rm_stock_unit_id`
        and `u3`.`is_delete` = 0))
left join `sm_product_packing` `p1` on
    (`p1`.`product_packing_id` = `rm`.`product_rm_packing_id`
        and `p1`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `rm`.`product_rm_hsn_sac_code_id`
        and `h`.`is_delete` = 0))
left join `smv_product_rm_technical` `rmt` on
    (`rmt`.`is_delete` = 0
        and `rmt`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmt`.`is_delete` = 0))
left join `sm_product_rm_commercial` `rmc` on
    (`rmc`.`is_delete` = 0
        and `rmc`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmc`.`is_delete` = 0))
left join `cm_godown` `g` on
    (`g`.`godown_id` = `rm`.`godown_id`))
left join `cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `rm`.`godown_section_id`))
left join `cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `rm`.`godown_section_beans_id`))
where
    `rm`.`is_delete` = 0
union all
select
    `fgt`.`product_type_group` as `product_type_group`,
    `fgt`.`product_type_name` as `product_type_name`,
    `fgt`.`product_category1_name` as `product_material_category1_name`,
    `fg`.`product_fg_id` as `product_material_id`,
    `fg`.`product_fg_code` as `product_material_code`,
    `fg`.`product_fg_name` as `product_material_name`,
    `fg`.`product_fg_short_name` as `product_material_short_name`,
    `fg`.`product_fg_tech_spect` as `product_material_tech_spect`,
    `u3`.`product_unit_name` as `product_material_stock_unit_name`,
    ifnull(null, 0) as `stock_quantity`,
    ifnull(null, 0) as `stock_weight`,
    ifnull(null, 0) as `customer_stock_quantity`,
    ifnull(null, 0) as `customer_stock_weight`,
    0 as `lead_time`,
    0 as `product_material_moq`,
    0 as `product_material_mrp`,
    0 as `product_material_landed_price`,
    `fgt`.`product_fg_technical_name` as `product_material_technical_name`,
    `fgt`.`product_type_short_name` as `product_material_type_short_name`,
    `fgt`.`product_category2_name` as `product_material_category2_name`,
    `fgt`.`product_category3_name` as `product_material_category3_name`,
    `fgt`.`product_category4_name` as `product_material_category4_name`,
    `fgt`.`product_category5_name` as `product_material_category5_name`,
    `p`.`product_packing_name` as `product_material_packing_name`,
    'NA' as `product_material_make_name`,
    `fgt`.`product_material_type_name` as `product_material_type_name`,
    `fgt`.`product_material_grade_name` as `product_material_grade_name`,
    `fgt`.`product_material_shape_name` as `product_material_shape_name`,
    `fg`.`product_fg_oem_part_code` as `product_material_oem_part_code`,
    `fg`.`product_fg_our_part_code` as `product_material_our_part_code`,
    `fgt`.`product_fg_drawing_no` as `product_material_drawing_no`,
    `fgc`.`product_fg_std_weight` as `product_material_std_weight`,
    1 as `product_material_std_hours`,
    1 as `process_duration`,
    0 as `product_std_profit_percent`,
    0 as `product_std_discount_percent`,
    case
        `fg`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `fg`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    'NA' as `godown_name`,
    'NA' as `godown_short_name`,
    'NA' as `godown_area`,
    'NA' as `godown_section_name`,
    'NA' as `godown_section_short_name`,
    'NA' as `godown_section_area`,
    'NA' as `godown_section_beans_name`,
    'NA' as `godown_section_beans_short_name`,
    'NA' as `godown_section_beans_area`,
    `h`.`hsn_sac_code` as `product_material_hsn_sac_code`,
    `h`.`hsn_sac_rate` as `product_material_hsn_sac_rate`,
    `v`.`company_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `fgt`.`product_category1_id` as `product_material_category1_id`,
    `fgt`.`product_type_id` as `product_type_id`,
    `fg`.`product_fg_stock_unit_id` as `product_material_stock_unit_id`,
    1 as `product_material_packing_id`,
    `fg`.`product_fg_hsn_sac_code_id` as `product_material_hsn_sac_code_id`,
    `fgt`.`product_category2_id` as `product_material_category2_id`,
    `fgt`.`product_category3_id` as `product_material_category3_id`,
    `fgt`.`product_category4_id` as `product_material_category4_id`,
    `fgt`.`product_category5_id` as `product_material_category5_id`,
    1 as `product_material_make_id`,
    `fgt`.`product_material_type_id` as `product_material_type_id`,
    `fgt`.`product_material_grade_id` as `product_material_grade_id`,
    `fgt`.`product_material_shape_id` as `product_material_shape_id`,
    `fg`.`company_id` as `company_id`,
    `fg`.`company_branch_id` as `company_branch_id`,
    '1' as `godown_id`,
    '1' as `godown_section_id`,
    '1' as `godown_section_beans_id`,
    `fg`.`bom_applicable` as `bom_applicable`,
    `fg`.`is_active` as `is_active`,
    `fg`.`is_delete` as `is_delete`,
    `fgc`.`cost_center_id` as `cost_center_id`,
    `fg`.`product_fg_name` as `field_name`,
   	 0 as `actual_count`,
	`fg`.`product_fg_id` as `field_id`
from
    ((((((`sm_product_fg` `fg`
left join `cmv_company` `v` on
    (`v`.`company_id` = `fg`.`company_id`))
left join `sm_product_unit` `u3` on
    (`u3`.`product_unit_id` = `fg`.`product_fg_stock_unit_id`
        and `u3`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `fg`.`product_fg_hsn_sac_code_id`
        and `h`.`is_delete` = 0))
left join `sm_product_packing` `p` on
    (`p`.`product_packing_id` = `fg`.`product_fg_packing_id`))
left join `smv_product_fg_technical` `fgt` on
    (`fgt`.`company_id` = `fg`.`company_id`
        and `fgt`.`product_fg_id` = `fg`.`product_fg_id`))
left join `sm_product_fg_commercial` `fgc` on
    (`fgc`.`product_fg_id` = `fg`.`product_fg_id`
        and `fgc`.`is_delete` = 0))
where
    `fg`.`is_delete` = 0;
