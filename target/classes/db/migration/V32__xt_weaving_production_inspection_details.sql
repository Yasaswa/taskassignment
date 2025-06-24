-- pashupati_erp_qa.xtv_weaving_production_inspection_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `xtv_weaving_production_inspection_details` AS
select
    `xt`.`inspection_production_code` AS `inspection_production_code`,
    `xt`.`inspection_production_set_no` AS `inspection_production_set_no`,
    `xt`.`inspection_order_no` AS `inspection_order_no`,
    `xt`.`inspection_production_date` AS `inspection_production_date`,
    `m`.`machine_name` AS `machine_name`,
    `s`.`godown_name` AS `godown_name`,
    case
        `xt`.`inspection_production_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'Z' then 'Canceled'
        when 'I' then 'Inprogress'
        when 'DG' then 'Dispatch Note Generated'
        when 'PD' then 'Partial Dispatch'
        when 'D' then 'Dispatch Done'
        else 'Pending'
    end AS `inspection_production_status_desc`,
    `xt`.`inspection_production_status` AS `inspection_production_status`,
    `p`.`plant_name` AS `plant_name`,
    `xt`.`prod_month` AS `prod_month`,
    `xt`.`prod_year` AS `prod_year`,
    `xt`.`sizing_beam_no` AS `sizing_beam_no`,
    `e`.`employee_name` AS `production_operator_name`,
    `e1`.`employee_name` AS `production_supervisor_name`,
    `xt`.`shift` AS `shift`,
    `xt`.`product_in_meter` AS `product_in_meter`,
    `xt`.`inspection_mtr` AS `inspection_mtr`,
    `xt`.`product_pick` AS `product_pick`,
    `xt`.`difference` AS `difference`,
    `xt`.`width` AS `width`,
    `xt`.`sort_no` AS `sort_no`,
    `xt`.`roll_no` AS `roll_no`,
    `xt`.`average` AS `average`,
    `xt`.`weight` AS `weight`,
    `po`.`product_material_id` AS `product_material_id`,
    `po`.`product_material_name` AS `product_material_name`,
    (
    select
        ifnull(sum(`wpi`.`product_in_meter`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi`
    where
        `wpi`.`shift` = `xt`.`shift`
        and `wpi`.`inspection_production_date` = `xt`.`inspection_production_date`
        and `wpi`.`inspection_production_status` = 'A'
        and `wpi`.`is_delete` = 0) AS `total_product_in_meter`,
    (
    select
        ifnull(sum(`wpi1`.`inspection_mtr`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi1`
    where
        `wpi1`.`shift` = `xt`.`shift`
        and `wpi1`.`inspection_production_date` = `xt`.`inspection_production_date`
        and `wpi1`.`inspection_production_status` = 'A'
        and `wpi1`.`is_delete` = 0) AS `total_inspection_mtr`,
    (
    select
        ifnull(`wi`.`product_in_meter` - sum(`wi`.`inspection_mtr`), 0)
    from
        `xt_weaving_production_inspection_details` `wi`
    where
        `wi`.`inspection_production_set_no` = `xt`.`inspection_production_set_no`
        and `wi`.`is_delete` = 0) AS `balance_product_in_meter`,
    (
    select
        ifnull(sum(`wpi2`.`weight`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi2`
    where
        `wpi2`.`shift` = `xt`.`shift`
        and `wpi2`.`inspection_production_date` = `xt`.`inspection_production_date`
        and `wpi2`.`inspection_production_status` = 'A'
        and `wpi2`.`is_delete` = 0) AS `total_weight`,
    `wm`.`inspection_production_master_status` AS `inspection_production_master_status_desc`,
    `wm`.`inspection_production_master_status` AS `inspection_production_master_status`,
    `sb`.`production_section_name` AS `production_section_name`,
    `sb`.`production_sub_section_name` AS `production_sub_section_name`,
    `po`.`order_type` AS `order_type`,
    `xts`.`sales_order_no` AS `sales_order_no`,
    `xts`.`customer_name` AS `customer_name`,
    `xt`.`status_remark` AS `status_remark`,
    `xt`.`dispatch_quantity` AS `dispatch_quantity`,
    `xt`.`dispatch_weight` AS `dispatch_weight`,
    case
        `xt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `xt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `xt`.`is_active` AS `is_active`,
    `xt`.`is_delete` AS `is_delete`,
    `xt`.`created_by` AS `created_by`,
    `xt`.`created_on` AS `created_on`,
    `xt`.`modified_by` AS `modified_by`,
    `xt`.`modified_on` AS `modified_on`,
    `xt`.`deleted_by` AS `deleted_by`,
    `xt`.`deleted_on` AS `deleted_on`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `xt`.`financial_year` AS `financial_year`,
    `v`.`company_id` AS `company_id`,
    `v`.`company_branch_id` AS `company_branch_id`,
    `xt`.`weaving_production_inspection_details_id` AS `weaving_production_inspection_details_id`,
    `xt`.`weaving_production_inspection_master_id` AS `weaving_production_inspection_master_id`,
    `xt`.`plant_id` AS `plant_id`,
    `xt`.`product_id` AS `product_id`,
    `rm`.`product_type_group` AS `product_type_group`,
    `rm`.`product_material_packing_id` AS `product_material_packing_id`,
    `rm`.`product_type_id` AS `product_type_id`,
    `rm`.`product_material_stock_unit_id` AS `product_material_stock_unit_id`,
    `xt`.`section_id` AS `section_id`,
    `xt`.`godown_id` AS `godown_id`,
    `xt`.`sub_section_id` AS `sub_section_id`,
    `xt`.`production_operator_id` AS `production_operator_id`,
    `xt`.`production_supervisor_id` AS `production_supervisor_id`,
    `xt`.`machine_id` AS `machine_id`,
    `xt`.`total_no_of_ends` AS `total_no_of_ends`
from
    (((((((((((`xt_weaving_production_inspection_details` `xt`
left join `xt_weaving_production_inspection_master` `wm` on
    (`wm`.`weaving_production_inspection_master_id` = `xt`.`weaving_production_inspection_master_id`
        and `wm`.`company_id` = `xt`.`company_id`))
left join `xmv_production_sub_section_godown_mapping` `s` on
    (`s`.`production_sub_section_godown_id` = `xt`.`godown_id`
        and `s`.`company_id` = `xt`.`company_id`))
left join `xtv_weaving_production_order` `po` on
    (`po`.`set_no` = `xt`.`inspection_production_set_no`
        and `po`.`company_id` = `xt`.`company_id`))
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `cm_machine` `m` on
    (`m`.`machine_id` = `xt`.`machine_id`
        and `m`.`company_id` = `xt`.`company_id`))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `xt`.`company_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`production_operator_id`
        and `e`.`company_id` = `xt`.`company_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `xt`.`production_supervisor_id`
        and `e1`.`company_id` = `xt`.`company_id`))
left join `smv_product_rm_fg_sr` `rm` on
    (`rm`.`product_material_id` = `xt`.`product_id`))
left join `xtv_weaving_production_order_style_details` `xts` on
    (`xts`.`set_no` = `xt`.`inspection_production_set_no`
        and `xts`.`company_id` = `xt`.`company_id`))
where
    `xt`.`is_delete` = 0;
