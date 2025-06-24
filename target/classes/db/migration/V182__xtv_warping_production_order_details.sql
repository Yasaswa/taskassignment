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
    `wpo`.`schedule_quantity` as `schedule_quantity`,
    `wpo`.`no_of_creels` as `no_of_creels`,
    `wpo`.`set_length` as `set_length`,
    `wpo`.`product_material_style` as `product_material_style`,
    `wpo`.`warping_order_no` as `warping_order_no`,
    `wpo`.`t_ends` as `t_ends`,
    `wpo`.`product_material_name` as `product_fg_name`,
    
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
left join `xtv_warping_production_order` `wpo` on
    (`wpo`.`warping_production_order_id` = `xt`.`warping_production_order_id`))
left join `smv_product_rm` `rm` on
    (`rm`.`product_rm_id` = `xt`.`product_material_id`))
left join `sm_product_unit` `u` on
    (`u`.`product_unit_id` = `xt`.`product_material_unit_id`
        and `u`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0;