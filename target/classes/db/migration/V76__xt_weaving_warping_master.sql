ALTER TABLE xt_weaving_production_warping_stoppage ADD from_time time NULL;
ALTER TABLE xt_weaving_production_warping_stoppage ADD to_time time NULL;
ALTER TABLE xt_weaving_production_warping_stoppage ADD total_time varchar(55) NULL;

ALTER TABLE xt_weaving_production_warping_master MODIFY COLUMN calculative_bottom_kg decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_master MODIFY COLUMN calculative_bottom_percent decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_master MODIFY COLUMN actual_bottom_percent decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_master MODIFY COLUMN difference_bottom_kg decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_master MODIFY COLUMN difference_bottom_percent decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_master MODIFY COLUMN actual_bottom_kg decimal(18,4) NULL;
ALTER TABLE xt_weaving_production_warping_master ADD warping_issue_kg decimal(18,4) NULL;


-- erp_development.xtv_weaving_production_warping_master source

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
    `xt`.`weaving_production_warping_stoppage_id` as `weaving_production_warping_stoppage_id`,
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




-- erp_development.xtv_weaving_production_warping_stoppage source

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_stoppage` as
select
    `sp`.`warping_production_date` as `warping_production_date`,
    `sp`.`warping_production_code` as `warping_production_code`,
    `sp`.`shift` as `shift`,
    `sp`.`prod_month` as `prod_month`,
    `sp`.`prod_year` as `prod_year`,
    `sm`.`plant_name` as `plant_name`,
    `sm`.`production_sub_section_name` as `production_sub_section_name`,
    `sm`.`production_section_name` as `production_section_name`,
    `sm`.`production_supervisor_name` as `production_supervisor_name`,
    `cm`.`machine_name` as `machine_name`,
    `cm`.`machine_short_name` as `machine_short_name`,
    `st`.`production_stoppage_reasons_name` as `production_stoppage_reasons_name`,
    `st`.`production_stoppage_reasons_type` as `production_stoppage_reasons_type`,
    `st`.`std_stoppage_minutes` as `std_stoppage_minutes`,
    `sp`.`std_stoppage_loss_per_hour` as `std_stoppage_loss_per_hour`,
    `sp`.`loss_type` as `loss_type`,
    `sp`.`stoppage_time` as `stoppage_time`,
    `sp`.`std_stoppage_loss_kg` as `std_stoppage_loss_kg`,
    `sp`.`stoppage_production_loss_kg` as `stoppage_production_loss_kg`,
    `sp`.`actual_production_loss_kg` as `actual_production_loss_kg`,
    `sp`.`from_time` as `from_time`,
    `sp`.`to_time` as `to_time`,
    `sp`.`total_time` as `total_time`,
    case
        `sp`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `sp`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `sm`.`is_active` as `is_active`,
    `sm`.`is_delete` as `is_delete`,
    `sm`.`created_by` as `created_by`,
    `sm`.`created_on` as `created_on`,
    `sm`.`modified_by` as `modified_by`,
    `sm`.`modified_on` as `modified_on`,
    `sm`.`deleted_by` as `deleted_by`,
    `sm`.`deleted_on` as `deleted_on`,
    `sm`.`company_name` as `company_name`,
    `sm`.`company_branch_name` as `company_branch_name`,
    `sm`.`financial_year` as `financial_year`,
    `sm`.`company_id` as `company_id`,
    `sm`.`company_branch_id` as `company_branch_id`,
    `sm`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `sp`.`weaving_production_warping_stoppage_id` as `weaving_production_warping_stoppage_id`,
    `sm`.`plant_id` as `plant_id`,
    `sm`.`section_id` as `section_id`,
    `sm`.`sub_section_id` as `sub_section_id`,
    `sm`.`production_supervisor_id` as `production_supervisor_id`,
    `sp`.`machine_id` as `machine_id`,
    `sp`.`production_stoppage_reasons_id` as `production_stoppage_reasons_id`,
    `sp`.`weaving_production_warping_stoppage_id` as `field_id`,
    `sp`.`warping_production_code` as `field_name`
from
    (((`xt_weaving_production_warping_stoppage` `sp`
left join `xtv_weaving_production_warping_master` `sm` on
    (`sp`.`weaving_production_warping_master_id` = `sm`.`weaving_production_warping_master_id`
        and `sp`.`company_id` = `sm`.`company_id`))
left join `cmv_machine` `cm` on
    (`sp`.`machine_id` = `cm`.`machine_id`
        and `sp`.`company_id` = `cm`.`company_id`))
left join `xmv_production_stoppage_reasons` `st` on
    (`sp`.`production_stoppage_reasons_id` = `st`.`production_stoppage_reasons_id`
        and `sp`.`company_id` = `st`.`company_id`))
where
    `sp`.`is_delete` = 0
order by
    `sp`.`weaving_production_warping_stoppage_id` desc;
