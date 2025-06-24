-- xtv_weaving_production_warping_details source

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
    `xt`.`speed` as `speed`,
    `xt`.`cut_cones` as `cut_cones`,
    `xt`.`guccha` as `guccha`,
    `xt`.`thin_places` as `thin_places`,
    `xt`.`week_places` as `week_places`,
    `xt`.`week_splice` as `week_splice`,
    `xt`.`sluff_off` as `sluff_off`,
    `xt`.`slub_yarn` as `slub_yarn`,
    `xt`.`total_breaks` as `total_breaks`,
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


-- xtv_weaving_production_warping_master source

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_master` as
select
    `xt`.`set_no` as `set_no`,
    `xt`.`warping_production_date` as `warping_production_date`,
    `xt`.`warping_production_code` as `warping_production_code`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `e`.`employee_name` as `production_supervisor_name`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    case
        `xt`.`warping_production_master_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Partial Approved'
    end as `warping_production_master_status_desc`,
    `xt`.`warping_production_master_status` as `warping_production_master_status`,
    (
    select
        ifnull(sum(`cr`.`no_of_beams`), 0)
    from
        `xt_warping_production_order_creels` `cr`
    where
        `cr`.`set_no` = `xt`.`set_no`
        and `cr`.`is_delete` = 0) as `no_of_beams`,
    (
    select
        ifnull(sum(`pw`.`actual_count`), 0)
    from
        `xtv_weaving_production_warping_details` `pw`
    where
        `pw`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw`.`is_delete` = 0) as `total_actual_count`,
    (
    select
        ifnull(sum(`pw1`.`yarn_count`), 0)
    from
        `xtv_weaving_production_warping_details` `pw1`
    where
        `pw1`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw1`.`is_delete` = 0) as `total_yarn_count`,
    (
    select
        ifnull(sum(`pw2`.`creel_ends`), 0)
    from
        `xtv_weaving_production_warping_details` `pw2`
    where
        `pw2`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw2`.`is_delete` = 0) as `total_creel_ends`,
    (
    select
        ifnull(sum(`pw3`.`total_weight_issue_to_warping`), 0)
    from
        `xtv_weaving_production_warping_details` `pw3`
    where
        `pw3`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw3`.`is_delete` = 0) as `total_weight_issue_to_warping`,
    (
    select
        ifnull(sum(`pw4`.`no_of_creels`), 0)
    from
        `xtv_weaving_production_warping_details` `pw4`
    where
        `pw4`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw4`.`is_delete` = 0) as `total_no_of_creels`,
    (
    select
        ifnull(sum(`pw5`.`net_weight`), 0)
    from
        `xtv_weaving_production_warping_details` `pw5`
    where
        `pw5`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw5`.`is_delete` = 0) as `total_net_weight`,
    (
    select
        ifnull(sum(`pw6`.`total_pkg_used`), 0)
    from
        `xtv_weaving_production_warping_details` `pw6`
    where
        `pw6`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw6`.`is_delete` = 0) as `total_pkg_used`,
    (
    select
        ifnull(sum(`pw7`.`weight_per_pkg`), 0)
    from
        `xtv_weaving_production_warping_details` `pw7`
    where
        `pw7`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw7`.`is_delete` = 0) as `total_weight_per_pkg`,
    (
    select
        ifnull(sum(`pw8`.`t_ends`), 0)
    from
        `xtv_weaving_production_warping_details` `pw8`
    where
        `pw8`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw8`.`is_delete` = 0) as `total_t_ends`,
    (
    select
        ifnull(sum(`pw9`.`length`), 0)
    from
        `xtv_weaving_production_warping_details` `pw9`
    where
        `pw9`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw9`.`is_delete` = 0) as `total_length`,
    (
    select
        ifnull(sum(`pw10`.`exp_bottom`), 0)
    from
        `xtv_weaving_production_warping_details` `pw10`
    where
        `pw10`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw10`.`is_delete` = 0) as `total_exp_bottom`,
    (
    select
        ifnull(sum(`pw11`.`breaks_per_million`), 0)
    from
        `xtv_weaving_production_warping_details` `pw11`
    where
        `pw11`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw11`.`is_delete` = 0) as `total_breaks_per_million`,
    (
    select
        ifnull(sum(`pw12`.`act_bottom`), 0)
    from
        `xtv_weaving_production_warping_details` `pw12`
    where
        `pw12`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw12`.`is_delete` = 0) as `total_act_bottom`,
    (
    select
        ifnull(sum(`pw13`.`bottom_percent`), 0)
    from
        `xtv_weaving_production_warping_details` `pw13`
    where
        `pw13`.`warping_production_code` = `xt`.`warping_production_code`
        and `pw13`.`is_delete` = 0) as `total_bottom_percent`,
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
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `xt`.`financial_year` as `financial_year`,
    `v`.`company_id` as `company_id`,
    `v`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`,
    `xt`.`weaving_production_warping_master_id` as `field_id`,
    `xt`.`warping_production_code` as `field_name`,
    `xt`.`calculative_bottom_kg` as `calculative_bottom_kg`,
    `xt`.`calculative_bottom_percent` as `calculative_bottom_percent`,
    `xt`.`actual_bottom_kg` as `actual_bottom_kg`,
    `xt`.`actual_bottom_percent` as `actual_bottom_percent`,
    `xt`.`difference_bottom_kg` as `difference_bottom_kg`,
    `xt`.`difference_bottom_percent` as `difference_bottom_percent`,
    `xt`.`warping_issue_kg` as `warping_issue_kg`,
    `wo`.`warping_order_no` as `warping_order_no`,
    `wo`.`product_material_id` as `product_material_id`,
    `rm_fg`.`product_material_name` as `product_material_name`,
    `wo`.`product_material_style` as `product_material_style`,
    `wo`.`schedule_quantity` as `schedule_quantity`,
    `wo`.`sort_no` as `sort_no`,
    `wo`.`no_of_creels` as `no_of_creels`,
    `wo`.`set_length` as `set_length`
from
    ((((((`xt_weaving_production_warping_master` `xt`
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `xt_warping_production_order` `wo` on
    (`wo`.`set_no` = `xt`.`set_no`
        and `wo`.`company_id` = `xt`.`company_id`))
left join `smv_product_rm_fg_sr` `rm_fg` on
    (`rm_fg`.`product_material_id` = `wo`.`product_material_id`))
left join `xmv_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `xt`.`company_id`))
left join `cmv_employee_list` `e` on
    (`e`.`employee_id` = `xt`.`production_supervisor_id`
        and `e`.`company_id` = `xt`.`company_id`))
where
    `xt`.`is_delete` = 0;