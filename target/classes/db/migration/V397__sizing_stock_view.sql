--  xtv_sizing_production_stock_details source

create or REPLACE algorithm = UNDEFINED view  `xtv_sizing_production_stock_details` as
select
    `xt`.`sizing_stock_details_id` as `sizing_stock_details_id`,
    `xt`.`weaving_production_sizing_details_id` as `weaving_production_sizing_details_id`,
    `xt`.`weaving_production_sizing_master_id` as `weaving_production_sizing_master_id`,
    `xt`.`sizing_production_code` as `sizing_production_code`,
    `xt`.`dispatch_date` as `dispatch_date`,
    `xt`.`set_no` as `set_no`,
    `xt`.`job_type` as `job_type`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`beam_no` as `beam_no`,
    `xt`.`total_ends` as `total_ends`,
    `xt`.`sizing_length` as `sizing_length`,
    `xt`.`gross_weight` as `gross_weight`,
    `xt`.`tare_weight` as `tare_weight`,
    `xt`.`net_weight` as `net_weight`,
    `xt`.`customer_id` as `customer_id`,
    `xt`.`amount` as `amount`,
    `xt`.`rate` as `rate`,
    `xt`.`sizing_production_date` as `sizing_production_date`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`sized_beam_status` as `sized_beam_status`,
    `xt`.`customer_order_no` as `customer_order_no`,
    `xt`.`remaining_length` as `remaining_length`,
    `xt`.`cut_beam_date` as `cut_beam_date`,
    case
        when `xt`.`sized_beam_status` = 'A' collate utf8mb4_unicode_ci then 'Available' collate utf8mb4_unicode_ci
        else 'Dispatched' collate utf8mb4_unicode_ci
    end as `sized_beam_status_desc`,
    `xt`.`godown_id` as `godown_id`,
    `b`.`beam_inward_type` as `beam_inward_type`,
    `b`.`beam_status` as `beam_status`,
    `b`.`property_group` as `property_group`,
    `c`.`customer_name` as `customer_name`,
    rm.product_rm_name as product_material_name,
    rm.actual_count as actual_count,
--     `fg`.`product_fg_name` as `product_fg_name`,
--     `fg`.`product_fg_code` as `product_fg_code`,
--     `wp`.`production_count_name` as `actual_count`,
--     `wp`.`product_material_name` as `product_material_name`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`
from
    (((( `xt_sizing_production_stock_details` `xt`
left join  `xtv_beam_inwards_table` `b` on
    (`b`.`beam_inwards_id` = `xt`.`beam_no`))
left join  `cm_customer` `c` on
    (`c`.`customer_id` = `xt`.`customer_id`))
left join  `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `xt`.`product_material_id`))
-- left join  `xtv_warping_production_order_details` `wp` on
--     (`wp`.`set_no` = `xt`.`set_no`)
    )
where
    `xt`.`is_delete` = 0;