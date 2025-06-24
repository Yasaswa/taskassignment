
create or replace
algorithm = UNDEFINED view `mtv_sales_order_schedules_trading` as
select
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `mt`.`sales_order_version` as `sales_order_version`,
    case
        `mt`.`sales_order_schedules_trading_item_status` when 'P' then 'Pending'
        when 'A' then 'Aprroved'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Issue'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'O' then 'PO'
        when 'Z' then 'PreeClosed'
        else 'GRN'
    end as `sales_order_schedules_trading_item_status_desc`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `mt`.`product_type` as `product_type`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `rmfgsr`.`product_material_code` as `product_material_code`,
    `rmfgsr`.`product_material_tech_spect` as `product_material_tech_spect`,
    `rmfgsr`.`product_material_stock_unit_name` as `product_material_stock_unit_name`,
    `rmfgsr`.`product_material_std_weight` as `product_material_std_weight`,
    `rmfgsr`.`product_material_technical_name` as `product_material_technical_name`,
    `rmfgsr`.`product_material_make_name` as `product_material_make_name`,
    `rmfgsr`.`product_material_type_name` as `product_material_type_name`,
    `rmfgsr`.`product_material_grade_name` as `product_material_grade_name`,
    `rmfgsr`.`product_material_shape_name` as `product_material_shape_name`,
    `rmfgsr`.`product_material_packing_name` as `product_material_packing_name`,
    `mt`.`material_quantity` as `material_quantity`,
    `mt`.`material_weight` as `material_weight`,
    `mt`.`product_material_schedule_quantity` as `product_material_schedule_quantity`,
    `mt`.`product_material_schedule_weight` as `product_material_schedule_weight`,
    `mt`.`expected_schedule_date` as `expected_schedule_date`,
    `mt`.`product_material_issue_quantity` as `product_material_issue_quantity`,
    `mt`.`product_material_issue_weight` as `product_material_issue_weight`,
    `mt`.`material_issue_date` as `material_issue_date`,
    `mt`.`sales_order_schedules_trading_item_status` as `sales_order_schedules_trading_item_status`,
    `mt`.`so_sr_no` as `so_sr_no`,
    `mt`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`sales_order_schedules_transaction_id` as `sales_order_schedules_transaction_id`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`sales_order_master_transaction_id` as `sales_order_master_transaction_id`,
    `mt`.`sales_order_details_transaction_id` as `sales_order_details_transaction_id`,
    `mt`.`product_type_id` as `product_type_id`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_unit_id` as `product_material_unit_id`
from
    ((`mt_sales_order_schedules_trading` `mt`
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `mt`.`product_material_id`))
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `mt`.`company_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
where
    `mt`.`is_delete` = 0;


update am_modules_forms set is_delete = 1 where modules_forms_id in (260, 261);