
update am_properties_master set properties_master_name = 'Fiber Name' where properties_master_id= 55;

update am_properties set properties_master_name = 'Fiber Name' where property_master_id= 55;


CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_rm_summary` AS
select
    `p`.`product_type_group` AS `product_type_group`,
    `p`.`product_type_name` AS `product_type_name`,
    `pc1`.`product_category1_name` AS `product_category1_name`,
    `rm`.`product_rm_id` AS `product_rm_id`,
    `rm`.`product_rm_code` AS `product_rm_code`,
    `rm`.`product_rm_name` AS `product_rm_name`,
    `rm`.`product_rm_short_name` AS `product_rm_short_name`,
    `rm`.`product_rm_tech_spect` AS `product_rm_tech_spect`,
    `u1`.`product_unit_name` AS `product_rm_stock_unit_name`,
    ifnull((select sum(`st1`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `st1` where `st1`.`product_rm_id` = `rm`.`product_rm_id` and `st1`.`company_id` = `rm`.`company_id`), 0) AS `stock_quantity`,
    ifnull((select sum(`st2`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `st2` where `st2`.`product_rm_id` = `rm`.`product_rm_id` and `st2`.`company_id` = `rm`.`company_id`), 0) AS `stock_weight`,
    ifnull((select sum(`st3`.`closing_balance_quantity`) from `sm_product_rm_stock_summary_customer` `st3` where `st3`.`product_rm_id` = `rm`.`product_rm_id` and `st3`.`company_id` = `rm`.`company_id`), 0) AS `customer_stock_quantity`,
    ifnull((select sum(`st4`.`closing_balance_weight`) from `sm_product_rm_stock_summary_customer` `st4` where `st4`.`product_rm_id` = `rm`.`product_rm_id` and `st4`.`company_id` = `rm`.`company_id`), 0) AS `customer_stock_weight`,
    `rmc`.`lead_time` AS `lead_time`,
    `rmc`.`product_rm_moq` AS `product_rm_moq`,
    `rmc`.`product_rm_mrp` AS `product_rm_mrp`,
    `rmc`.`product_rm_landed_price` AS `product_rm_landed_price`,
    `rmt`.`product_rm_technical_name` AS `product_rm_technical_name`,
    `p`.`product_type_short_name` AS `product_type_short_name`,
    `pc2`.`product_category2_name` AS `product_category2_name`,
    `pc3`.`product_category3_name` AS `product_category3_name`,
    'NA' AS `product_category4_name`,
    'NA' AS `product_category5_name`,
    `p1`.`product_packing_name` AS `product_rm_packing_name`,
    `pm`.`product_make_name` AS `product_make_name`,
    'NA' AS `product_material_type_name`,
    'NA' AS `product_material_grade_name`,
    'NA' AS `product_material_shape_name`,
    `rm`.`product_rm_oem_part_code` AS `product_rm_oem_part_code`,
    `rm`.`product_rm_our_part_code` AS `product_rm_our_part_code`,
    `rm`.`product_rm_drawing_no` AS `product_rm_drawing_no`,
    `rmc`.`product_rm_std_weight` AS `product_rm_std_weight`,
    1 AS `product_rm_std_hours`,
    1 AS `process_duration`,
    0 AS `product_rm_std_profit_percent`,
    0 AS `product_rm_std_discount_percent`,
    case
        `rm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `rm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `g`.`godown_name` AS `godown_name`,
    `g`.`godown_short_name` AS `godown_short_name`,
    0 AS `godown_area`,
    `g`.`godown_section_count` AS `godown_section_count`,
    `gs`.`godown_section_name` AS `godown_section_name`,
    `gs`.`godown_section_short_name` AS `godown_section_short_name`,
    0 AS `godown_section_area`,
    `gsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `gsb`.`godown_section_beans_short_name` AS `godown_section_beans_short_name`,
    0 AS `godown_section_beans_area`,
    `h`.`hsn_sac_code` AS `product_rm_hsn_sac_code`,
    `h`.`hsn_sac_rate` AS `product_rm_hsn_sac_rate`,
    'NA' AS `company_name`,
    'NA' AS `company_branch_name`,
    `rm`.`product_category1_id` AS `product_category1_id`,
    `rm`.`product_type_id` AS `product_type_id`,
    `rm`.`product_rm_stock_unit_id` AS `product_rm_stock_unit_id`,
    `rm`.`product_rm_packing_id` AS `product_rm_packing_id`,
    `rm`.`product_rm_hsn_sac_code_id` AS `product_rm_hsn_sac_code_id`,
    `rmt`.`product_category2_id` AS `product_category2_id`,
    `rmt`.`product_category3_id` AS `product_category3_id`,
    `rmt`.`product_category4_id` AS `product_category4_id`,
    `rmt`.`product_category5_id` AS `product_category5_id`,
    `rmt`.`product_make_id` AS `product_make_id`,
    `rmt`.`product_material_type_id` AS `product_material_type_id`,
    `rmt`.`product_material_grade_id` AS `product_material_grade_id`,
    `rmt`.`product_material_shape_id` AS `product_material_shape_id`,
    `rm`.`company_id` AS `company_id`,
    `rm`.`company_branch_id` AS `company_branch_id`,
    `rm`.`godown_id` AS `godown_id`,
    `rm`.`godown_section_id` AS `godown_section_id`,
    `rm`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `rm`.`product_id` AS `product_id`,
    'No' AS `bom_applicable`,
    `rm`.`is_active` AS `is_active`,
    `rm`.`is_delete` AS `is_delete`,
    `rm`.`created_by` AS `created_by`,
    `rm`.`created_on` AS `created_on`,
    `rm`.`modified_by` AS `modified_by`,
    `rm`.`modified_on` AS `modified_on`,
    `rm`.`deleted_by` AS `deleted_by`,
    `rm`.`deleted_on` AS `deleted_on`,
    `rm`.`product_rm_name` AS `field_name`,
    `rm`.`product_rm_id` AS `field_id`
from
    (((((((((((((`sm_product_rm` `rm`
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `rm`.`product_type_id`))
left join `sm_product_rm_technical` `rmt` on
    (`rmt`.`company_id` = `rm`.`company_id`
        and `rmt`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmt`.`is_delete` = 0))
left join `sm_product_category1` `pc1` on
    (`pc1`.`product_category1_id` = `rm`.`product_category1_id`))
left join `sm_product_category2` `pc2` on
    (`pc2`.`product_category2_id` = `rmt`.`product_category2_id`))
left join `sm_product_category3` `pc3` on
    (`pc3`.`product_category1_id` = `rmt`.`product_category3_id`))
left join `sm_product_make` `pm` on
    (`pm`.`company_id` = `rmt`.`company_id`
        and `pm`.`product_make_id` = `rmt`.`product_make_id`))
left join `sm_product_unit` `u1` on
    (`u1`.`product_unit_id` = `rm`.`product_rm_stock_unit_id`))
left join `sm_product_packing` `p1` on
    (`p1`.`product_packing_id` = `rm`.`product_rm_packing_id`))
 left join `cm_godown` `g` on
    (`g`.`godown_id` = `rm`.`godown_id`))
left join `cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `rm`.`godown_section_id`))
left join `cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `rm`.`godown_section_beans_id`))
left join `sm_product_rm_commercial` `rmc` on
    (`rmc`.`company_id` = `rm`.`company_id`
        and `rmc`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmc`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `rm`.`product_rm_hsn_sac_code_id`))
where
    `rm`.`is_delete` = 0;