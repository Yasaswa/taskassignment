ALTER TABLE xt_warping_production_order ADD t_ends decimal(18, 4) DEFAULT 0;

-- erp_dev_temp.xtv_warping_production_order source

create or replace
algorithm = UNDEFINED view `xtv_warping_production_order` as
select
    `xt`.`set_no` as `set_no`,
    `xt`.`warping_order_no` as `warping_order_no`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`customer_order_no` as `customer_order_no`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `xt`.`warping_order_status` as `warping_order_status`,
    `xt`.`warping_schedule_date` as `warping_schedule_date`,
    `xt`.`warping_plan_date` as `warping_plan_date`,
    `xt`.`schedule_quantity` as `schedule_quantity`,
    `xt`.`order_quantity` as `order_quantity`,
    `xt`.`other_terms_conditions` as `other_terms_conditions`,
    `xt`.`no_of_creels` as `no_of_creels`,
    `xt`.`set_length` as `set_length`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `xt`.`product_material_style` as `product_material_style`,
    `xt`.t_ends,
    case
        `xt`.`warping_order_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'I' then 'Partial'
    end as `warping_order_status_desc`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`status_remark` as `status_remark`,
    `e`.`employee_name` as `approved_by_name`,
    `xt`.`approved_date` as `approved_date`,
    `xt`.`remark` as `remark`,
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
    `xt`.`warping_production_order_id` as `warping_production_order_id`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`production_order_type_id` as `production_order_type_id`,
    `xt`.`customer_id` as `customer_id`,
    `xt`.`approved_by_id` as `approved_by_id`,
    `xt`.`warping_production_order_id` as `field_id`
from
    (((`xt_warping_production_order` `xt`
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`approved_by_id`
        and `e`.`is_delete` = 0))
left join `mt_sales_order_master_trading` `mt` on
    (`mt`.`customer_order_no` = `xt`.`customer_order_no`
        and `mt`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `xt`.`product_material_id`))
where
    `xt`.`is_delete` = 0;
