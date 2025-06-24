
CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_rm_stock_details` AS
select
    `sm`.`stock_date` AS `stock_date`,
    `sm`.`day_closed` AS `day_closed`,
    `sm`.`batch_no` AS `batch_no`,
    `supp`.`supp_branch_name` AS `supplier_name`,
    `sm`.`batch_expiry_date` AS `batch_expiry_date`,
    `sm`.`goods_receipt_no` AS `goods_receipt_no`,
    `sm`.`goods_receipt_version` AS `goods_receipt_version`,
    `cm`.`customer_code` AS `customer_code`,
    `cm`.`customer_name` AS `customer_name`,
    `sm`.`sales_order_no` AS `sales_order_no`,
    `sm`.`sales_order_version` AS `sales_order_version`,
    `sm`.`customer_goods_receipt_no` AS `customer_goods_receipt_no`,
    `sm`.`customer_goods_receipt_version` AS `customer_goods_receipt_version`,
    `sm`.`so_sr_no` AS `so_sr_no`,
    `sm`.`customer_order_no` AS `customer_order_no`,
    `rm`.`product_material_code` AS `product_rm_code`,
    `rm`.`product_material_name` AS `product_rm_name`,
    `rm`.`product_material_technical_name` AS `product_rm_technical_name`,
    `rm`.`product_type_name` AS `product_type_name`,
    `rm`.`product_material_category1_name` AS `product_material_category1_name`,
    `rm`.`product_material_category2_name` AS `product_material_category2_name`,
    `rm`.`product_material_short_name` AS `product_rm_short_name`,
    `su`.`product_unit_name` AS `product_material_unit_name`,
    `rm`.`product_material_stock_unit_name` AS `product_rm_purchase_unit_name`,
    `pp`.`product_packing_name` AS `product_packing_name`,
    `sm`.`batch_rate` AS `batch_rate`,
    `sm`.`order_quantity` AS `order_quantity`,
    `sm`.`order_weight` AS `order_weight`,
    `sm`.`pending_quantity` AS `pending_quantity`,
    `sm`.`pending_weight` AS `pending_weight`,
    `sm`.`opening_quantity` AS `opening_quantity`,
    `sm`.`opening_weight` AS `opening_weight`,
    `sm`.`closing_balance_quantity` AS `closing_balance_quantity`,
    `sm`.`closing_balance_weight` AS `closing_balance_weight`,
    `sm`.`batch_rate` * `sm`.`closing_balance_quantity` AS `closing_amount`,
    `sm`.`reserve_quantity` AS `reserve_quantity`,
    `sm`.`reserve_weight` AS `reserve_weight`,
    `sm`.`excess_quantity` AS `excess_quantity`,
    `sm`.`excess_weight` AS `excess_weight`,
    `sm`.`pree_closed_quantity` AS `pree_closed_quantity`,
    `sm`.`pree_closed_weight` AS `pree_closed_weight`,
    `sm`.`purchase_quantity` AS `purchase_quantity`,
    `sm`.`purchase_weight` AS `purchase_weight`,
    `sm`.`purchase_return_quantity` AS `purchase_return_quantity`,
    `sm`.`purchase_return_weight` AS `purchase_return_weight`,
    `sm`.`purchase_rejection_quantity` AS `purchase_rejection_quantity`,
    `sm`.`purchase_rejection_weight` AS `purchase_rejection_weight`,
    `sm`.`jobcard_quantity` AS `jobcard_quantity`,
    `sm`.`jobcard_weight` AS `jobcard_weight`,
    `sm`.`production_issue_quantity` AS `production_issue_quantity`,
    `sm`.`production_issue_weight` AS `production_issue_weight`,
    `sm`.`production_issue_return_quantity` AS `production_issue_return_quantity`,
    `sm`.`production_issue_return_weight` AS `production_issue_return_weight`,
    `sm`.`production_issue_rejection_quantity` AS `production_issue_rejection_quantity`,
    `sm`.`production_issue_rejection_weight` AS `production_issue_rejection_weight`,
    `sm`.`production_quantity` AS `production_quantity`,
    `sm`.`production_weight` AS `production_weight`,
    `sm`.`production_return_quantity` AS `production_return_quantity`,
    `sm`.`production_return_weight` AS `production_return_weight`,
    `sm`.`production_rejection_quantity` AS `production_rejection_quantity`,
    `sm`.`production_rejection_weight` AS `production_rejection_weight`,
    `sm`.`assembly_production_issue_quantity` AS `assembly_production_issue_quantity`,
    `sm`.`assembly_production_issue_weight` AS `assembly_production_issue_weight`,
    `sm`.`sales_quantity` AS `sales_quantity`,
    `sm`.`sales_weight` AS `sales_weight`,
    `sm`.`sales_return_quantity` AS `sales_return_quantity`,
    `sm`.`sales_return_weight` AS `sales_return_weight`,
    `sm`.`sales_rejection_quantity` AS `sales_rejection_quantity`,
    `sm`.`sales_rejection_weight` AS `sales_rejection_weight`,
    `sm`.`customer_receipt_quantity` AS `customer_receipt_quantity`,
    `sm`.`customer_receipt_weight` AS `customer_receipt_weight`,
    `sm`.`customer_return_quantity` AS `customer_return_quantity`,
    `sm`.`customer_return_weight` AS `customer_return_weight`,
    `sm`.`customer_rejection_quantity` AS `customer_rejection_quantity`,
    `sm`.`customer_rejection_weight` AS `customer_rejection_weight`,
    `sm`.`transfer_issue_quantity` AS `transfer_issue_quantity`,
    `sm`.`transfer_issue_weight` AS `transfer_issue_weight`,
    `sm`.`transfer_receipt_quantity` AS `transfer_receipt_quantity`,
    `sm`.`transfer_receipt_weight` AS `transfer_receipt_weight`,
    `sm`.`outsources_out_quantity` AS `outsources_out_quantity`,
    `sm`.`outsources_out_weight` AS `outsources_out_weight`,
    `sm`.`outsources_in_quantity` AS `outsources_in_quantity`,
    `sm`.`outsources_in_weight` AS `outsources_in_weight`,
    `sm`.`outsources_rejection_quantity` AS `outsources_rejection_quantity`,
    `sm`.`outsources_rejection_weight` AS `outsources_rejection_weight`,
    `sm`.`loan_receipt_quantity` AS `loan_receipt_quantity`,
    `sm`.`loan_receipt_weight` AS `loan_receipt_weight`,
    `sm`.`loan_issue_quantity` AS `loan_issue_quantity`,
    `sm`.`loan_issue_weight` AS `loan_issue_weight`,
    `sm`.`cancel_quantity` AS `cancel_quantity`,
    `sm`.`cancel_weight` AS `cancel_weight`,
    `sm`.`difference_quantity` AS `difference_quantity`,
    `sm`.`difference_weight` AS `difference_weight`,
    `sm`.`total_box_weight` AS `total_box_weight`,
    `sm`.`total_quantity_in_box` AS `total_quantity_in_box`,
    `sm`.`weight_per_box_item` AS `weight_per_box_item`,
    `sm`.`opening_no_of_boxes` AS `opening_no_of_boxes`,
    `sm`.`reserve_no_of_boxes` AS `reserve_no_of_boxes`,
    `sm`.`purchase_no_of_boxes` AS `purchase_no_of_boxes`,
    `sm`.`issue_no_of_boxes` AS `issue_no_of_boxes`,
    `sm`.`closing_no_of_boxes` AS `closing_no_of_boxes`,
    `gd`.`godown_name` AS `godown_name`,
    `gd`.`godown_short_name` AS `godown_short_name`,
    `gd`.`godown_area` AS `godown_area`,
    `gd`.`godown_section_count` AS `godown_section_count`,
    `gds`.`godown_section_name` AS `godown_section_name`,
    `gds`.`godown_section_short_name` AS `godown_section_short_name`,
    `gds`.`godown_section_area` AS `godown_section_area`,
    `gdsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `gdsb`.`godown_section_beans_short_name` AS `godown_section_beans_short_name`,
    `gdsb`.`godown_section_beans_area` AS `godown_section_beans_area`,
    `pp`.`quantity_per_packing` AS `quantity_per_packing`,
    `pp`.`weight_per_packing` AS `weight_per_packing`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `sm`.`financial_year` AS `financial_year`,
    `sm`.`product_type_group` AS `product_type_group`,
    case
        `sm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `sm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `sm`.`is_active` AS `is_active`,
    `sm`.`is_delete` AS `is_delete`,
    `sm`.`created_by` AS `created_by`,
    `sm`.`created_on` AS `created_on`,
    `sm`.`modified_by` AS `modified_by`,
    `sm`.`modified_on` AS `modified_on`,
    `sm`.`deleted_by` AS `deleted_by`,
    `sm`.`deleted_on` AS `deleted_on`,
    `sm`.`company_id` AS `company_id`,
    `sm`.`company_branch_id` AS `company_branch_id`,
    `sm`.`stock_transaction_id` AS `stock_transaction_id`,
    `sm`.`product_rm_id` AS `product_rm_id`,
    `sm`.`customer_id` AS `customer_id`,
    `sm`.`godown_id` AS `godown_id`,
    `sm`.`supplier_id` AS `supplier_id`,
    `sm`.`godown_section_id` AS `godown_section_id`,
    `sm`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `sm`.`product_type_id` AS `product_type_id`,
    `sm`.`product_material_unit_id` AS `product_material_unit_id`,
    `sm`.`product_material_packing_id` AS `product_material_packing_id`,
    `rm`.`product_material_category1_id` AS `product_material_category1_id`,
    `rm`.`product_material_category2_id` AS `product_material_category2_id`
from
    ((((((((((`sm_product_rm_stock_details` `sm`
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `sm`.`company_id`
        and `v`.`company_branch_id` = `sm`.`company_branch_id`))
left join `smv_product_rm_fg_sr` `rm` on
    (`rm`.`is_delete` = 0
        and `rm`.`product_material_id` = `sm`.`product_rm_id`
        and `rm`.`product_type_id` = `sm`.`product_type_id`))
left join `cm_customer` `cm` on
    (`cm`.`is_delete` = 0
        and `cm`.`company_branch_id` = `sm`.`company_branch_id`
        and `cm`.`customer_id` = `sm`.`customer_id`))
left join `cmv_godown` `gd` on
    (`gd`.`is_delete` = 0
        and `gd`.`godown_id` = `sm`.`godown_id`))
left join `cmv_godown_section` `gds` on
    (`gds`.`is_delete` = 0
        and `gds`.`godown_section_id` = `sm`.`godown_section_id`))
left join `cmv_godown_section_beans` `gdsb` on
    (`gdsb`.`is_delete` = 0
        and `gdsb`.`godown_section_beans_id` = `sm`.`godown_section_beans_id`))
left join `sm_product_packing` `pp` on
    (`pp`.`is_delete` = 0
        and `pp`.`product_packing_id` = `sm`.`product_material_packing_id`))
left join `smv_product_unit` `pu` on
    (`pu`.`is_delete` = 0
        and `pu`.`product_unit_id` = `sm`.`product_material_unit_id`))
left join `cm_supplier_branch` `supp` on
    (`supp`.`is_delete` = 0
        and `supp`.`supp_branch_id` = `sm`.`supplier_id`))
left join `sm_product_unit` `su` on
    (`su`.`product_unit_id` = `sm`.`product_material_unit_id`
        and `su`.`is_delete` = 0))
where
    `sm`.`is_delete` = 0
    and `sm`.`day_closed` = 0;