-- erp_development.xtv_warping_production_order_details source

create or replace
algorithm = UNDEFINED view `xtv_warping_production_order_details` as
select
    `rm`.`product_rm_code` as `product_material_code`,
    `rm`.`product_rm_name` as `product_material_name`,
    `rm`.`product_rm_stock_unit_name` as `product_material_unit_name`,
    `rm`.`actual_count` as `production_count_name`,
    `xt`.`cone_per_wt` as `cone_per_wt`,
    `xt`.`warping_quantity` as `warping_quantity`,
    `xt`.`no_of_cones` as `no_of_cones`,
    `u`.`product_unit_name` as `product_unit_name`,
    ifnull((select sum(`st1`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `st1` where `st1`.`product_rm_id` = `xt`.`product_material_id`), 0) as `closing_balance_quantity`,
    ifnull((select sum(`st2`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `st2` where `st2`.`product_rm_id` = `xt`.`product_material_id`), 0) as `closing_balance_weight`,
    case
        `xt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`warping_order_details_id` as `warping_order_details_id`,
    `xt`.`warping_production_order_id` as `warping_production_order_id`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`product_material_unit_id` as `product_material_unit_id`,
    `xt`.`warping_order_details_id` as `field_id`,
    `wpo`.`set_no` as `set_no`,
    (
    select
        ifnull(`wp`.`product_material_quantity` - sum(`wp`.`consumption_quantity`), 0)
    from
        `xt_weaving_production_warping_material` `wp`
    where
        `wp`.`weaving_production_set_no` = `wpo`.`set_no`
        and `wp`.`product_material_id` = `xt`.`product_material_id`
        and `wp`.`is_delete` = 0) as `product_material_balance_quantity`
from
    (((`xt_warping_production_order_details` `xt`
left join `xt_warping_production_order` `wpo` on
    (`wpo`.`warping_production_order_id` = `xt`.`warping_production_order_id`))
left join `smv_product_rm` `rm` on
    (`rm`.`product_rm_id` = `xt`.`product_material_id`))
left join `sm_product_unit` `u` on
    (`u`.`product_unit_id` = `xt`.`product_material_unit_id`
        and `u`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0;



create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_details` as
select
    `xt`.`warping_production_date` as `warping_production_date`,
    `xt`.`weaving_production_set_no` as `weaving_production_set_no`,
    `xt`.`warping_order_no` as `warping_order_no`,
    `xt`.`warping_production_code` as `warping_production_code`,
    `m`.`machine_name` as `machine_name`,
    `s`.`godown_name` as `godown_name`,
    `po`.`product_material_name` as `product_material_name`,
    case
        `xt`.`warping_production_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'Z' then 'Canceled'
        when 'I' then 'Inprogress'
        else 'Partial'
    end as `warping_production_status_desc`,
    `xt`.`warping_production_status` as `warping_production_status`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `xt`.`actual_count` as `actual_count`,
    `e`.`employee_name` as `production_operator_name`,
    `e1`.`employee_name` as `production_supervisor_name`,
    `xt`.`shift` as `shift`,
    (
    select
        ifnull(sum(`wp`.`length`), 0)
    from
        `xt_weaving_production_warping_details` `wp`
    where
        `wp`.`shift` = `xt`.`shift`
        and `wp`.`warping_production_date` = `xt`.`warping_production_date`
        and `wp`.`warping_production_status` = 'A'
        and `wp`.`is_delete` = 0) as `warping_total_legnth`,
    (
    select
        ifnull(sum(`wp`.`act_bottom`), 0)
    from
        `xt_weaving_production_warping_details` `wp`
    where
        `wp`.`shift` = `xt`.`shift`
        and `wp`.`warping_production_date` = `xt`.`warping_production_date`
        and `wp`.`warping_production_status` = 'A'
        and `wp`.`is_delete` = 0) as `warping_total_act_bottom`,
    (
    select
        ifnull(sum(`sp`.`stoppage_time`), 0)
    from
        `xt_weaving_production_warping_stoppage` `sp`
    where
        `sp`.`warping_production_date` = `xt`.`warping_production_date`
        and `sp`.`shift` = `xt`.`shift`
        and `sp`.`is_delete` = 0) as `stoppage_time`,
    `xt`.`creel_ends` as `creel_ends`,
    `xt`.`total_weight_issue_to_warping` as `total_weight_issue_to_warping`,
    `xt`.`no_of_creels` as `no_of_creels`,
    `xt`.`net_weight` as `net_weight`,
    `xt`.`total_pkg_used` as `total_pkg_used`,
    `xt`.`weight_per_pkg` as `weight_per_pkg`,
    `xt`.`t_ends` as `t_ends`,
    `xt`.`length` as `length`,
    `xt`.`exp_bottom` as `exp_bottom`,
    `xt`.`breaks_per_million` as `breaks_per_million`,
    `xt`.`act_bottom` as `act_bottom`,
    `xt`.`bottom_percent` as `bottom_percent`,
    `po`.`set_no` as `set_no`,
    `po`.`product_material_id` as `product_material_id`,
    `wm`.`warping_production_master_status` as `warping_production_master_status_desc`,
    `wm`.`warping_production_master_status` as `warping_production_master_status`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    `xt`.`status_remark` as `status_remark`,
    case
        `xt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `xt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `xt`.`is_active` as `is_active`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_warping_details_id` as `weaving_production_warping_details_id`,
    `xt`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`godown_id` as `godown_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`yarn_count` as `yarn_count`,
    `xt`.`production_operator_id` as `production_operator_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`,
    `xt`.`machine_id` as `machine_id`,
    `xt`.`weaving_production_warping_details_id` as `field_id`,
    `xt`.`warping_order_no` as `field_name`,
    `xtw`.`no_of_creels` as `warping_creels`,
    `xtw`.`set_length` as `warping_set_length`,
    `xt`.`beam_no` as `beam_no`,
    `am`.`property_name` as `beam_name`
from
    ((((((((((`xt_weaving_production_warping_details` `xt`
left join `xt_weaving_production_warping_master` `wm` on
    (`wm`.`weaving_production_warping_master_id` = `xt`.`weaving_production_warping_master_id`
        and `wm`.`company_id` = `xt`.`company_id`
        and `wm`.`is_delete` = 0))
left join `cm_godown` `s` on
    (`s`.`godown_id` = `xt`.`godown_id`
        and `s`.`company_id` = `xt`.`company_id`))
left join `xtv_warping_production_order` `po` on
    (`po`.`set_no` = `xt`.`weaving_production_set_no`
        and `po`.`company_id` = `xt`.`company_id`))
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`
        and `p`.`is_delete` = 0))
left join `cm_machine` `m` on
    (`m`.`machine_id` = `xt`.`machine_id`
        and `m`.`company_id` = `xt`.`company_id`))
left join `xmv_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cmv_employee_list` `e` on
    (`e`.`employee_id` = `xt`.`production_operator_id`
        and `e`.`company_id` = `xt`.`company_id`))
left join `cmv_employee_list` `e1` on
    (`e1`.`employee_id` = `xt`.`production_supervisor_id`
        and `e1`.`company_id` = `xt`.`company_id`))
left join `xt_warping_production_order` `xtw` on
    (`xtw`.`set_no` = `xt`.`weaving_production_set_no`
        and `xtw`.`company_id` = `xt`.`company_id`
        and `xtw`.`is_delete` = 0))
left join `am_properties` `am` on
    (`am`.`property_id` = `xt`.`beam_no`
        and `am`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0
order by
    `xt`.`weaving_production_warping_details_id` desc;
