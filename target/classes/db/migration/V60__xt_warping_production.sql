-- xtv_warping_production_order_details source

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
    ifnull((select sum(`st1`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `st1` where `st1`.`product_rm_id` = `xt`.`product_material_id` and `st1`.`company_id` = `xt`.`company_id`), 0) as `closing_balance_quantity`,
    ifnull((select sum(`st2`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `st2` where `st2`.`product_rm_id` = `xt`.`product_material_id` and `st2`.`company_id` = `xt`.`company_id`), 0) as `closing_balance_weight`,
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


ALTER TABLE xt_warping_production_order_creels ADD product_material_id varchar(100) NULL;
ALTER TABLE xt_warping_production_order_creels CHANGE product_material_id product_material_id varchar(100) NULL AFTER set_no;


-- erp_development.xtv_warping_production_order_stock_details source

create or replace
algorithm = UNDEFINED view `xtv_warping_production_order_stock_details` as
select
    `xt`.`goods_receipt_no` as `goods_receipt_no`,
    `xt`.`indent_no` as `indent_no`,
    `sprsd`.`customer_goods_receipt_no` as `customer_goods_receipt_no`,
    `sprsd`.`supplier_name` as `supplier_name`,
    `sprsd`.`customer_name` as `customer_name`,
    `sprsd`.`product_rm_name` as `product_material_name`,
    `rm`.`actual_count` as `actual_count`,
    `sprsd`.`product_packing_name` as `product_packing_name`,
    `sprsd`.`weight_per_packing` as `weight_per_packing`,
    `sprsd`.`closing_balance_quantity` as `closing_balance_quantity`,
    `sprsd`.`closing_balance_weight` as `closing_balance_weight`,
    `xt`.`cone_per_wt` as `cone_per_wt`,
    `xt`.`no_of_cones` as `no_of_cones`,
    `xt`.`warping_quantity` as `warping_quantity`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`warping_order_stock_details_id` as `warping_order_stock_details_id`,
    `xt`.`warping_production_order_id` as `warping_production_order_id`,
    `xt`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`product_material_id` as `product_material_id`,
    `sprsd`.`product_rm_code` as `product_material_code`,
    `xt`.`supplier_id` as `supplier_id`,
    `xt`.`set_no` as `set_no`
from
    ((`xt_warping_production_order_stock_details` `xt`
left join `smv_product_rm_stock_details` `sprsd` on
    (`sprsd`.`product_rm_id` = `xt`.`product_material_id`
        and `sprsd`.`goods_receipt_no` = `xt`.`goods_receipt_no`))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `xt`.`product_material_id`))
where
    `xt`.`is_delete` = 0;

ALTER TABLE sm_product_rm CHANGE actual_count actual_count decimal(18,4) DEFAULT NULL NULL AFTER product_rm_print_name;

ALTER TABLE xt_weaving_production_warping_master ADD set_no varchar(250) NULL;
ALTER TABLE xt_weaving_production_warping_master CHANGE set_no set_no varchar(250) NULL AFTER financial_year;


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
