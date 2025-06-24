ALTER TABLE xt_warping_production_order_creels MODIFY COLUMN creel_weight decimal(18,4) DEFAULT NULL NULL;
ALTER TABLE xt_warping_production_order_creels MODIFY COLUMN cone_per_wt decimal(18,4) DEFAULT NULL NULL;


CREATE OR REPLACE
ALGORITHM = UNDEFINED
VIEW `xtv_warping_production_order` AS
SELECT
    `xt`.`set_no` AS `set_no`,
    `xt`.`warping_order_no` AS `warping_order_no`,
    `xt`.`sort_no` AS `sort_no`,
    `xt`.`customer_order_no` AS `customer_order_no`,
    `mt`.`sales_order_no` AS `sales_order_no`,
    `mt`.`sales_order_date` AS `sales_order_date`,
    `mt`.`job_type` AS `job_type`,
    `xt`.`warping_order_status` AS `warping_order_status`,
    `xt`.`warping_schedule_date` AS `warping_schedule_date`,
    `xt`.`warping_plan_date` AS `warping_plan_date`,
    `xt`.`schedule_quantity` AS `schedule_quantity`,
    `xt`.`order_quantity` AS `order_quantity`,
    `xt`.`other_terms_conditions` AS `other_terms_conditions`,
    `xt`.`no_of_creels` AS `no_of_creels`,
    `xt`.`set_length` AS `set_length`,
    CASE
        WHEN `xt`.`production_order_type_id` IN (2, 11, 12, 13) THEN `spr`.`product_rm_name`
        ELSE `spf`.`product_fg_name`
    END AS `product_material_name`,
        `xt`.`product_material_id` AS `product_material_id`,
    `xt`.`production_order_type_id` AS `production_order_type_id`,
    `xt`.`product_material_style` AS `product_material_style`,
    `xt`.`t_ends` AS `t_ends`,
    CASE
        WHEN `xt`.`warping_order_status` = 'P' THEN 'Pending'
        WHEN `xt`.`warping_order_status` = 'A' THEN 'Approved'
        WHEN `xt`.`warping_order_status` = 'R' THEN 'Rejected'
        WHEN `xt`.`warping_order_status` = 'C' THEN 'Completed'
        WHEN `xt`.`warping_order_status` = 'X' THEN 'Canceled'
        WHEN `xt`.`warping_order_status` = 'I' THEN 'Partial'
    END AS `warping_order_status_desc`,
    `xt`.`financial_year` AS `financial_year`,
    `xt`.`status_remark` AS `status_remark`,
    `e`.`employee_name` AS `approved_by_name`,
    `xt`.`approved_date` AS `approved_date`,
    `xt`.`remark` AS `remark`,
    CASE
        WHEN `xt`.`is_delete` = 1 THEN 'Yes'
        ELSE 'No'
    END AS `Deleted`,
    `xt`.`is_delete` AS `is_delete`,
    `xt`.`created_by` AS `created_by`,
    `xt`.`created_on` AS `created_on`,
    `xt`.`modified_by` AS `modified_by`,
    `xt`.`modified_on` AS `modified_on`,
    `xt`.`deleted_by` AS `deleted_by`,
    `xt`.`deleted_on` AS `deleted_on`,
    `xt`.`company_id` AS `company_id`,
    `xt`.`company_branch_id` AS `company_branch_id`,
    `xt`.`warping_production_order_id` AS `warping_production_order_id`,
    `xt`.`customer_id` AS `customer_id`,
    `xt`.`approved_by_id` AS `approved_by_id`,
    `xt`.`warping_production_order_id` AS `field_id`
FROM
    `xt_warping_production_order` `xt`
LEFT JOIN `cm_employee` `e`
    ON `e`.`employee_id` = `xt`.`approved_by_id` AND `e`.`is_delete` = 0
LEFT JOIN `mt_sales_order_master_trading` `mt`
    ON `mt`.`customer_order_no` = `xt`.`customer_order_no` AND `mt`.`is_delete` = 0
LEFT JOIN `sm_product_rm` `spr`
    ON `spr`.`product_rm_id` = `xt`.`product_material_id` AND `xt`.`production_order_type_id` IN (2, 11, 12, 13) AND `spr`.`is_delete` = 0
LEFT JOIN `sm_product_fg` `spf`
    ON `spf`.`product_fg_id` = `xt`.`product_material_id` AND `xt`.`production_order_type_id` NOT IN (2, 11, 12, 13) AND `spf`.`is_delete` = 0
WHERE
    `xt`.`is_delete` = 0;





