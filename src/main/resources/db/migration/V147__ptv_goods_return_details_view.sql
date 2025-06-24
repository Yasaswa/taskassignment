create or replace
algorithm = UNDEFINED view `ptv_goods_return_details` as
select
    `pt`.`goods_receipt_no` as `goods_receipt_no`,
    `grm`.`goods_return_date` as `goods_return_date`,
    `grm`.`goods_version` as `goods_version`,
    `grm`.`goods_return_no` as `goods_return_no`,
    `grm`.`goods_return_master_id` as `goods_return_master_id`,
    `pt`.`product_rm_id` as `product_rm_id`,
    `pt`.`goods_return_quantity` as `goods_return_quantity`,
    `pt`.`goods_return_weight` as `goods_return_weight`,
    `pt`.`goods_return_boxes` as `goods_return_boxes`,
    `pt`.`goods_return_details_id` as `goods_return_details_id`,
    `grm`.`product_type_id` as `product_type_id`,
    `grm`.`supplier_id` as `supplier_id`,
    `sup`.`supp_branch_name` as `supplier_name`,
    `pdt`.`product_type_name` as `product_type_name`,
    `ptv`.`remark` as `remark`,
    `rm`.`product_rm_name` as `product_material_name`,
    `ptv`.`batch_no` as `batch_no`,
    `ptv`.`product_material_grn_accepted_quantity` as `product_material_grn_accepted_quantity`,
    `ptv`.`product_material_grn_accepted_weight` as `product_material_grn_accepted_weight`,
    `ptv`.`no_of_boxes` as `no_of_boxes`,
    `ptv`.`total_box_weight` as `total_box_weight`,
    `ptv`.`total_quantity_in_box` as `total_quantity_in_box`,
    `ptv`.`weight_per_box_item` as `weight_per_box_item`,
    `ptv`.`material_rate` as `material_rate`,
    `smv`.`closing_balance_quantity` as `closing_balance_quantity`,
    `smv`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `smv`.`closing_balance_weight` as `closing_balance_weight`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `grm`.`company_id` as `company_id`,
    `grm`.`company_branch_id` as `company_branch_id`,
    case
        when `grm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `grm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `grm`.`is_active`,
    `grm`.`is_delete`,
    `grm`.`created_by` as `created_by`,
    `grm`.`created_on` as `created_on`,
    `grm`.`modified_by` as `modified_by`,
    `grm`.`modified_on` as `modified_on`,
    `grm`.`deleted_by` as `deleted_by`,
    `grm`.`deleted_on` as `deleted_on`
from
    ((((((`pt_goods_return_details` `pt`
left join `pt_goods_return_master` `grm` on
    (`grm`.`goods_return_master_id` = `pt`.`goods_return_master_id`
        and `grm`.`company_id` = `pt`.`company_id`))
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `grm`.`product_type_id`
        and `pdt`.`is_delete` = 0))
left join `pt_goods_receipt_details` `ptv` on
    (`pt`.`goods_receipt_no` = `ptv`.`goods_receipt_no`
        and `pt`.`is_delete` = 0))
left join `sm_product_rm_stock_details` `smv` on
    (`pt`.`goods_receipt_no` = `smv`.`goods_receipt_no`
        and `pt`.`is_delete` = 0))
 left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `pt`.`product_rm_id`)
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `pt`.`company_id`))
left join `cm_supplier_branch` `sup` on
 (`sup`.`supp_branch_id` = `grm`.`supplier_id`
        and `pt`.`is_delete` = 0))
where
    `pt`.`is_delete` = 0;