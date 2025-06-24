-- ERP_PASHUPATI_PROD_1_0.hmv_salary_rules source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `hmv_salary_rules` AS
select
    `am`.`property_name` AS `property_name`,
    `vp`.`department_name` AS `department_name`,
    `vp1`.`department_name` AS `sub_department_name`,
    `j`.`attendance_status_name` AS `job_type_name`,
    `hm`.`rule_days` AS `rule_days`,
    `j`.`attendance_status_short_name` AS `job_type_short_name`,
    `hm`.`company_id` AS `company_id`,
    `hm`.`salary_rule_id` AS `salary_rule_id`,
    `hm`.`property_id` AS `property_id`,
    `hm`.`department_id` AS `department_id`,
    `hm`.`sub_department_id` AS `sub_department_id`,
    `hm`.`job_type_id` AS `job_type_id`,
    `hm`.`is_delete` AS `is_delete`,
    `hm`.`created_by` AS `created_by`,
    `hm`.`created_on` AS `created_on`,
    `hm`.`modified_by` AS `modified_by`,
    `hm`.`modified_on` AS `modified_on`,
    `hm`.`deleted_by` AS `deleted_by`,
    `hm`.`deleted_on` AS `deleted_on`
from
    ((((`hm_salary_rules` `hm`
left join `cm_department` `vp` on
    (`vp`.`department_id` = `hm`.`department_id`
        and `vp`.`is_delete` = 0))
left join `cm_department` `vp1` on
    (`vp1`.`department_id` = `hm`.`sub_department_id`
        and `vp1`.`is_delete` = 0))
left join `hm_attendance_status` `j` on
    (`j`.`attendance_status_id` = `hm`.`job_type_id`))
left join `am_properties` `am` on
    (`am`.`property_id` = `hm`.`property_id`))
where
    `hm`.`is_delete` = 0;


-- amv_properties_rpt source

create or replace
algorithm = UNDEFINED view `amv_properties_rpt` as
select
    concat(ifnull(`p`.`company_id`, ''), ':N:N:') as `company_id`,
    concat(ifnull(`m`.`properties_master_name`, ''), ':Property Master:Y:T:') as `properties_master_name`,
    concat(ifnull(`p`.`property_name`, ''), ':Property:Y:C:amv_properties') as `property_name`,
    concat(ifnull(`p`.`property_value`, ''), ':Property Value:Y:T:') as `property_value`,
    concat(case when `p`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active:Y:H:(Active, In Active)') as `Active`,
    concat(case when `p`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`p`.`is_active`, ''), ':N:N:') as `is_active`,
    concat(ifnull(`p`.`is_delete`, ''), ':N:N:') as `is_delete`,
    concat(ifnull(`p`.`created_on`, ''), ':N:N:') as `created_on`,
    concat(ifnull(`p`.`modified_on`, ''), ':N:N:') as `modified_on`,
    concat(ifnull(`p`.`deleted_on`, ''), ':N:N:') as `deleted_on`,
    concat(ifnull(`p`.`property_master_id`, ''), ':N:N:') as `property_master_id`,
    concat(ifnull(`p`.`created_by`, ''), ':N:N:') as `created_by`,
    concat(ifnull(`p`.`modified_by`, ''), ':N:N:') as `modified_by`,
    concat(ifnull(`p`.`deleted_by`, ''), ':N:N:') as `deleted_by`,
    concat(ifnull(`p`.`property_id`, ''), ':N:N:') as `field_id`,
    concat(ifnull(`p`.`property_name`, ''), ':N:N:') as `field_name`,
    concat(ifnull(`p`.`property_id`, ''), ':Property Id:O:N:') as `property_id`
from
    (`am_properties` `p`
left join `am_properties_master` `m` on
    (`m`.`properties_master_id` = `p`.`property_master_id`))
limit 1;


-- pashupati_erp_qa.smv_product_rm_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_rm_details` AS
select
    'NA' AS `company_name`,
    'NA' AS `company_branch_name`,
    `rm`.`product_rm_code` AS `product_rm_code`,
    `rm`.`product_rm_name` AS `product_rm_name`,
    `rm`.`product_rm_short_name` AS `product_rm_short_name`,
    `rm`.`product_rm_print_name` AS `product_rm_print_name`,
    `rm`.`product_rm_tech_spect` AS `product_rm_tech_spect`,
    `rm`.`product_rm_oem_part_code` AS `product_rm_oem_part_code`,
    `rm`.`product_rm_our_part_code` AS `product_rm_our_part_code`,
    `rm`.`product_rm_drawing_no` AS `product_rm_drawing_no`,
    `rm`.`product_rm_item_sr_no` AS `product_rm_item_sr_no`,
    `rm`.`product_rm_model_no` AS `product_rm_model_no`,
    `rm`.`product_rm_bar_code` AS `product_rm_bar_code`,
    `rm`.`product_rm_qr_code` AS `product_rm_qr_code`,
    `rm`.`product_consumption_mode` AS `product_consumption_mode`,
    `rm`.`product_origin_type` AS `product_origin_type`,
    `rm`.`product_origin_country` AS `product_origin_country`,
    `p`.`product_type_name` AS `product_type_name`,
    `p`.`product_type_short_name` AS `product_type_short_name`,
    `p`.`product_type_group` AS `product_type_group`,
    `h`.`hsn_sac_code` AS `product_rm_hsn_sac_code`,
    `h`.`hsn_sac_rate` AS `product_rm_hsn_sac_rate`,
    `h`.`hsn_sac_type` AS `product_rm_hsn_sac_type`,
    `h`.`is_exampted` AS `product_rm_hsn_sac_is_exampted`,
    `u1`.`product_unit_name` AS `product_rm_purchase_unit_name`,
    `u1`.`product_unit_name` AS `product_rm_sales_unit_name`,
    `u1`.`product_unit_name` AS `product_rm_stock_unit_name`,
    `p1`.`product_packing_name` AS `product_rm_packing_name`,
    `p1`.`quantity_per_packing` AS `product_rm_quantity_per_packing`,
    `p1`.`weight_per_packing` AS `product_rm_weight_per_packing`,
    `pm`.`product_make_name` AS `product_make_name`,
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
    `rm`.`product_rm_name` AS `product_alternate_rm_name`,
    `rmt`.`product_rm_technical_name` AS `product_rm_technical_name`,
    `pc1`.`product_category1_name` AS `product_category1_name`,
    `pc2`.`product_category2_name` AS `product_category2_name`,
    `pc3`.`product_category3_name` AS `product_category3_name`,
    'NA' AS `product_category4_name`,
    'NA' AS `product_category5_name`,
    'NA' AS `product_material_type_name`,
    'NA' AS `product_material_grade_name`,
    'NA' AS `product_material_shape_name`,
    'NA' AS `product_material_colour`,
    0 AS `assembly_scrap_percent`,
    `rmc`.`product_rm_gross_weight` AS `product_rm_gross_weight`,
    `rmc`.`product_rm_net_weight` AS `product_rm_net_weight`,
    `rmc`.`product_rm_std_weight` AS `product_rm_std_weight`,
    `rmc`.`product_rm_volume` AS `product_rm_volume`,
    `rmc`.`product_rm_mrp` AS `product_rm_mrp`,
    `rmc`.`product_rm_landed_price` AS `product_rm_landed_price`,
    `rmc`.`product_rm_avg_price` AS `product_rm_avg_price`,
    `rmc`.`product_rm_std_profit_percent` AS `product_rm_std_profit_percent`,
    `rmc`.`product_rm_std_discount_percent` AS `product_rm_std_discount_percent`,
    `rmc`.`lead_time` AS `lead_time`,
    `rmc`.`product_rm_moq` AS `product_rm_moq`,
    `rmc`.`product_rm_mpq` AS `product_rm_mpq`,
    `rmc`.`product_rm_mov` AS `product_rm_mov`,
    `rmc`.`product_rm_eoq` AS `product_rm_eoq`,
    `rmc`.`product_rm_min_stock_level` AS `product_rm_min_stock_level`,
    `rmc`.`product_rm_max_stock_level` AS `product_rm_max_stock_level`,
    `rmc`.`product_rm_reorder_stock_level` AS `product_rm_reorder_stock_level`,
    `rmc`.`product_rm_depriciation_percent` AS `product_rm_depriciation_percent`,
    `rmc`.`purchase_tolerance_percent` AS `purchase_tolerance_percent`,
    `rmc`.`product_rm_price_type` AS `product_rm_price_type`,
    `rmc`.`product_rm_abc_analysis` AS `product_rm_abc_analysis`,
    `rmc`.`qa_required` AS `qa_required`,
    `rmc`.`test_certificate_required` AS `test_certificate_required`,
    `rmc`.`routing_code` AS `routing_code`,
    `fpc`.`profit_center_name` AS `profit_center_name`,
    `fcc`.`cost_center_name` AS `cost_center_name`,
    `rm`.`remark` AS `remark`,
    case
        `rm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `rm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `rm`.`is_active` AS `is_active`,
    `rm`.`is_delete` AS `is_delete`,
    `rm`.`created_by` AS `created_by`,
    `rm`.`created_on` AS `created_on`,
    `rm`.`modified_by` AS `modified_by`,
    `rm`.`modified_on` AS `modified_on`,
    `rm`.`deleted_by` AS `deleted_by`,
    `rm`.`deleted_on` AS `deleted_on`,
    `rm`.`company_id` AS `company_id`,
    `rm`.`company_branch_id` AS `company_branch_id`,
    `rm`.`product_rm_id` AS `product_rm_id`,
    `rm`.`product_type_id` AS `product_type_id`,
    `rm`.`product_rm_hsn_sac_code_id` AS `product_rm_hsn_sac_code_id`,
    `rm`.`product_rm_purchase_unit_id` AS `product_rm_purchase_unit_id`,
    `rm`.`product_rm_sales_unit_id` AS `product_rm_sales_unit_id`,
    `rm`.`product_rm_stock_unit_id` AS `product_rm_stock_unit_id`,
    `rm`.`product_rm_packing_id` AS `product_rm_packing_id`,
    `rm`.`godown_id` AS `godown_id`,
    `rm`.`godown_section_id` AS `godown_section_id`,
    `rm`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `rm`.`product_category1_id` AS `product_category1_id`,
    `rmt`.`product_alternate_rm_id` AS `product_alternate_rm_id`,
    `rmt`.`product_category2_id` AS `product_category2_id`,
    `rmt`.`product_category3_id` AS `product_category3_id`,
    `rmt`.`product_category4_id` AS `product_category4_id`,
    `rmt`.`product_category5_id` AS `product_category5_id`,
    `rmt`.`product_material_type_id` AS `product_material_type_id`,
    `rmt`.`product_material_grade_id` AS `product_material_grade_id`,
    `rmt`.`product_material_shape_id` AS `product_material_shape_id`,
    `rmt`.`product_make_id` AS `product_make_id`,
    `rmc`.`profit_center_id` AS `profit_center_id`,
    `rmc`.`cost_center_id` AS `cost_center_id`,
    `rm`.`product_id` AS `product_id`,
    `rm`.`product_rm_name` AS `field_name`,
    `rm`.`product_rm_id` AS `field_id`
from
    (((((((((((((((`sm_product_rm` `rm`
left join `sm_product_type` `p` on
    (`p`.`is_delete` = 0
        and `p`.`product_type_id` = `rm`.`product_type_id`))
left join `sm_product_rm_technical` `rmt` on
    (`rmt`.`is_delete` = 0
        and `rmt`.`product_rm_id` = `rm`.`product_rm_id`))
left join `sm_product_rm_commercial` `rmc` on
    (`rmc`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmc`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `rm`.`product_rm_hsn_sac_code_id`
        and `h`.`is_delete` = 0))
left join `sm_product_unit` `u1` on
    (`u1`.`product_unit_id` = `rm`.`product_rm_stock_unit_id`
        and `u1`.`is_delete` = 0))
left join `sm_product_make` `pm` on
    (`pm`.`is_delete` = 0
        and `pm`.`product_make_id` = `rmt`.`product_make_id`))
left join `sm_product_packing` `p1` on
    (`p1`.`product_packing_id` = `rm`.`product_rm_packing_id`
        and `p1`.`is_delete` = 0))
left join `sm_product_category1` `pc1` on
    (`pc1`.`is_delete` = 0
        and `pc1`.`product_category1_id` = `rm`.`product_category1_id`))
left join `sm_product_category2` `pc2` on
    (`pc2`.`is_delete` = 0
        and `pc2`.`product_category2_id` = `rmt`.`product_category2_id`))
left join `sm_product_category3` `pc3` on
    (`pc3`.`is_delete` = 0
        and `pc3`.`product_category3_id` = `rmt`.`product_category3_id`))
 left join `cm_godown` `g` on
    (`g`.`godown_id` = `rm`.`godown_id`))
left join `cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `rm`.`godown_section_id`))
left join `cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `rm`.`godown_section_beans_id`))
left join `fm_profit_center` `fpc` on
    (`fpc`.`company_id` = `rmc`.`company_id`
        and `fpc`.`profit_center_id` = `rmc`.`profit_center_id`))
left join `fm_cost_center` `fcc` on
    (`fcc`.`company_id` = `rmc`.`company_id`
        and `fcc`.`cost_center_id` = `rmc`.`cost_center_id`))
where
    `rm`.`is_delete` = 0;

-- pashupati_erp_qa.smv_product_rm source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_rm` AS
select
    `b`.`product_id` AS `product_id`,
    `b`.`product_rm_name` AS `product_rm_name`,
    `b`.`product_rm_code` AS `product_rm_code`,
    `b`.`product_rm_short_name` AS `product_rm_short_name`,
    `b`.`product_rm_print_name` AS `product_rm_print_name`,
    `b`.`product_rm_tech_spect` AS `product_rm_tech_spect`,
    `b`.`product_rm_oem_part_code` AS `product_rm_oem_part_code`,
    `b`.`product_rm_our_part_code` AS `product_rm_our_part_code`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `p`.`product_type_name` AS `product_type_name`,
    `p`.`product_type_short_name` AS `product_type_short_name`,
    `p`.`product_type_group` AS `product_type_group`,
    `pc1`.`product_category1_name` AS `product_category1_name`,
    `pc1`.`product_category1_short_name` AS `product_category1_short_name`,
    `h`.`hsn_sac_code` AS `product_rm_hsn_sac_code`,
    `h`.`hsn_sac_rate` AS `product_rm_hsn_sac_rate`,
    `h`.`hsn_sac_type` AS `product_rm_hsn_sac_type`,
    `h`.`is_exampted` AS `product_rm_hsn_sac_is_exampted`,
    `u1`.`product_unit_name` AS `product_rm_purchase_unit_name`,
    `u1`.`product_unit_name` AS `product_rm_sales_unit_name`,
    `u1`.`product_unit_name` AS `product_rm_stock_unit_name`,
    `b`.`product_rm_item_sr_no` AS `product_rm_item_sr_no`,
    `b`.`product_rm_drawing_no` AS `product_rm_drawing_no`,
    `b`.`product_rm_model_no` AS `product_rm_model_no`,
    `b`.`product_rm_bar_code` AS `product_rm_bar_code`,
    `b`.`bom_applicable` AS `bom_applicable`,
    `b`.`product_rm_qr_code` AS `product_rm_qr_code`,
    `b`.`product_consumption_mode` AS `product_consumption_mode`,
    `b`.`product_origin_type` AS `product_origin_type`,
    `b`.`product_origin_country` AS `product_origin_country`,
    `p1`.`product_packing_name` AS `product_rm_packing_name`,
    `p1`.`quantity_per_packing` AS `product_rm_quantity_per_packing`,
    `p1`.`weight_per_packing` AS `product_rm_weight_per_packing`,
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
    `b`.`remark` AS `remark`,
    case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `b`.`is_active` AS `is_active`,
    `b`.`is_delete` AS `is_delete`,
    `b`.`created_by` AS `created_by`,
    `b`.`created_on` AS `created_on`,
    `b`.`modified_by` AS `modified_by`,
    `b`.`modified_on` AS `modified_on`,
    `b`.`deleted_by` AS `deleted_by`,
    `b`.`deleted_on` AS `deleted_on`,
    `b`.`company_id` AS `company_id`,
    `b`.`company_branch_id` AS `company_branch_id`,
    `b`.`product_rm_id` AS `product_rm_id`,
    `b`.`product_type_id` AS `product_type_id`,
    `b`.`product_category1_id` AS `product_category1_id`,
    `b`.`product_rm_hsn_sac_code_id` AS `product_rm_hsn_sac_code_id`,
    `b`.`product_rm_purchase_unit_id` AS `product_rm_purchase_unit_id`,
    `b`.`product_rm_sales_unit_id` AS `product_rm_sales_unit_id`,
    `b`.`product_rm_stock_unit_id` AS `product_rm_stock_unit_id`,
    `b`.`product_rm_packing_id` AS `product_rm_packing_id`,
    `b`.`godown_id` AS `godown_id`,
    `b`.`godown_section_id` AS `godown_section_id`,
    `b`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `b`.`product_rm_name` AS `field_name`,
    `b`.`product_rm_id` AS `field_id`
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


ALTER TABLE sm_product_rm
DROP INDEX ind_company_id,
DROP INDEX ind_deleted,
DROP INDEX ind_product_category_id1;

ALTER TABLE sm_product_rm
ADD FULLTEXT INDEX idx_fulltext_product_rm_name (product_rm_name);

ALTER TABLE sm_product_rm
ADD FULLTEXT INDEX idx_fulltext_product_rm_code (product_rm_code);