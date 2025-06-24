
create or replace
algorithm = UNDEFINED view `xtv_warping_production_order` as
select
    `xt`.`set_no` as `set_no`,
    `xt`.`warping_order_no` as `warping_order_no`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`customer_order_no` as `customer_order_no`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `mt`.`job_type` as `job_type`,
    `xt`.`warping_order_status` as `warping_order_status`,
    `xt`.`warping_schedule_date` as `warping_schedule_date`,
    `xt`.`warping_plan_date` as `warping_plan_date`,
    `xt`.`schedule_quantity` as `schedule_quantity`,
    `xt`.`order_quantity` as `order_quantity`,
    `xt`.`other_terms_conditions` as `other_terms_conditions`,
    `xt`.`no_of_creels` as `no_of_creels`,
    `xt`.`set_length` as `set_length`,
    case
        when `xt`.`production_order_type_id` in (2, 11, 12, 13) then `spr`.`product_rm_name`
        else `spf`.`product_fg_name`
    end as `product_material_name`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`production_order_type_id` as `production_order_type_id`,
    `xt`.`product_material_style` as `product_material_style`,
    `xt`.`t_ends` as `t_ends`,
    case
        when `xt`.`warping_order_status` = 'P' then 'Pending'
        when `xt`.`warping_order_status` = 'A' then 'Approved'
        when `xt`.`warping_order_status` = 'R' then 'Rejected'
        when `xt`.`warping_order_status` = 'C' then 'Completed'
        when `xt`.`warping_order_status` = 'X' then 'Canceled'
        when `xt`.`warping_order_status` = 'I' then 'Partial'
    end as `warping_order_status_desc`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`status_remark` as `status_remark`,
    `e`.`employee_name` as `approved_by_name`,
    `xt`.`approved_date` as `approved_date`,
    `xt`.`bottom_value` as `bottom_value`,
    `xt`.`remark` as `remark`,
    case
        when `xt`.`is_delete` = 1 then 'Yes'
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
    `xt`.`warping_production_order_id` as `warping_production_order_id`,
    `xt`.`customer_id` as `customer_id`,
    `xt`.`approved_by_id` as `approved_by_id`,
    `xt`.`warping_production_order_id` as `field_id`
from
    ((((`xt_warping_production_order` `xt`
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`approved_by_id`
        and `e`.`is_delete` = 0))
left join `mt_sales_order_master_trading` `mt` on
    (`mt`.`customer_order_no` = `xt`.`customer_order_no`
        and `mt`.`is_delete` = 0))
left join `sm_product_rm` `spr` on
    (`spr`.`product_rm_id` = `xt`.`product_material_id`
        and `xt`.`production_order_type_id` in (2, 11, 12, 13)
            and `spr`.`is_delete` = 0))
left join `sm_product_fg` `spf` on
    (`spf`.`product_fg_id` = `xt`.`product_material_id`
        and `xt`.`production_order_type_id` not in (2, 11, 12, 13)
            and `spf`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0
order by
    `xt`.`warping_production_order_id` desc;