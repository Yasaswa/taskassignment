-- erp_development.xtv_warping_production_order_stock_details source

create or replace
algorithm = UNDEFINED view `xtv_warping_production_order_stock_details` as
select
    `supp`.`supp_branch_name` as `supplier_name`,
    `cc`.`customer_name` as `customer_name`,
    `rm`.`product_rm_name` as `product_material_name`,
    `rm`.`actual_count` as `actual_count`,
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
    `rm`.`product_rm_code` as `product_material_code`,
    `xt`.`product_material_unit_id` as `product_material_unit_id`,
    `xt`.`supplier_id` as `supplier_id`,
    `xt`.`set_no` as `set_no`,
    `xt`.`goods_receipt_no` as `goods_receipt_no`,
    `xt`.`issue_requisition_type` as `issue_requisition_type`,
    `rm`.`godown_id` as `godown_id`,
    `rm`.`godown_section_id` as `godown_section_id`,
    `rm`.`godown_section_beans_id` as `godown_section_beans_id`,
    `xt`.`batch_no` as `batch_no`
from
    ((((`xt_warping_production_order_stock_details` `xt`
left join `xt_warping_production_order` `xwpo` on
    (`xwpo`.`set_no` = `xt`.`set_no`))
left join `cm_supplier_branch` `supp` on
    (`supp`.`is_delete` = 0
        and `supp`.`supp_branch_id` = `xt`.`supplier_id`))
left join `cm_customer` `cc` on
    (`xwpo`.`customer_id` = `cc`.`customer_id`))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `xt`.`product_material_id`))
where
    `xt`.`is_delete` = 0;
