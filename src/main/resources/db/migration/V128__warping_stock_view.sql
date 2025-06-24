
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
    `xt`.`set_no` as `set_no`,
    `sprsd` .`batch_no`
from
    ((`xt_warping_production_order_stock_details` `xt`
left join `smv_product_rm_stock_details` `sprsd` on
    (`sprsd`.`product_rm_id` = `xt`.`product_material_id`
        and `sprsd`.`goods_receipt_no` = `xt`.`goods_receipt_no` ))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `xt`.`product_material_id`))
where
    `xt`.`is_delete` = 0;
-- erp_dev_temp.xtv_warping_production_order_stock_details source

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
    `xt`.`set_no` as `set_no`,
    `sprsd` .`batch_no`
from
    ((`xt_warping_production_order_stock_details` `xt`
left join `smv_product_rm_stock_details` `sprsd` on
    (`sprsd`.`product_rm_id` = `xt`.`product_material_id`
        and `sprsd`.`goods_receipt_no` = `xt`.`goods_receipt_no` ))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `xt`.`product_material_id`))
where
    `xt`.`is_delete` = 0;
