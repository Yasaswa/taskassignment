


ALTER TABLE sm_product_rm_stock_summary ADD loan_issue_boxes BIGINT(20) DEFAULT 0 NULL COMMENT 'no of boxes issue to department';
ALTER TABLE sm_product_rm_stock_summary CHANGE loan_issue_boxes loan_issue_boxes BIGINT(20) DEFAULT 0 NULL COMMENT 'no of boxes issue to department' AFTER loan_issue_weight;
ALTER TABLE sm_product_rm_stock_summary ADD loan_receipt_boxes varchar(100) DEFAULT '0' NULL COMMENT 'loan receipt boxes to department';
ALTER TABLE sm_product_rm_stock_summary CHANGE loan_receipt_boxes loan_receipt_boxes varchar(100) DEFAULT '0' NULL COMMENT 'loan receipt boxes to department' AFTER loan_receipt_weight;


ALTER TABLE sm_product_rm_stock_details ADD loan_issue_boxes BIGINT(20) DEFAULT 0 NULL COMMENT 'no of boxes issue to department';
ALTER TABLE sm_product_rm_stock_details CHANGE loan_issue_boxes loan_issue_boxes BIGINT(20) DEFAULT 0 NULL COMMENT 'no of boxes issue to department' AFTER loan_issue_weight;
ALTER TABLE sm_product_rm_stock_details ADD loan_receipt_boxes varchar(100) DEFAULT '0' NULL COMMENT 'loan receipt boxes to department';
ALTER TABLE sm_product_rm_stock_details CHANGE loan_receipt_boxes loan_receipt_boxes varchar(100) DEFAULT '0' NULL COMMENT 'loan receipt boxes to department' AFTER loan_receipt_weight;




-- smv_product_rm_stock_summary source

create or REPLACE algorithm = UNDEFINED view `smv_product_rm_stock_summary` as
select
    `sm`.`financial_year` as `financial_year`,
    `supp`.`supp_branch_name` as `supplier_name`,
    `cust`.`customer_name` as `customer_name`,
    `pt`.`product_type_name` as `product_type_name`,
    `sm`.`product_type_group` as `product_type_group`,
    `rm`.`product_material_code` as `product_rm_code`,
    `rm`.`product_material_name` as `product_rm_name`,
    `rm`.`product_material_category1_name` as `product_material_category1_name`,
    `rm`.`product_material_category2_name` as `product_material_category2_name`,
    `pu`.`product_unit_name` as `product_unit_name`,
    `pp`.`product_packing_name` as `product_packing_name`,
    `sm`.`order_quantity` as `order_quantity`,
    `sm`.`order_weight` as `order_weight`,
    `sm`.`pending_quantity` as `pending_quantity`,
    `sm`.`pending_weight` as `pending_weight`,
    `sm`.`opening_quantity` as `opening_quantity`,
    `sm`.`opening_weight` as `opening_weight`,
    `sm`.`closing_balance_quantity` as `closing_balance_quantity`,
    `sm`.`closing_balance_weight` as `closing_balance_weight`,
    `sm`.`reserve_quantity` as `reserve_quantity`,
    `sm`.`reserve_weight` as `reserve_weight`,
    `sm`.`excess_quantity` as `excess_quantity`,
    `sm`.`excess_weight` as `excess_weight`,
    `sm`.`pree_closed_quantity` as `pree_closed_quantity`,
    `sm`.`pree_closed_weight` as `pree_closed_weight`,
    `sm`.`purchase_quantity` as `purchase_quantity`,
    `sm`.`purchase_weight` as `purchase_weight`,
    `sm`.`purchase_return_quantity` as `purchase_return_quantity`,
    `sm`.`purchase_return_weight` as `purchase_return_weight`,
    `sm`.`purchase_rejection_quantity` as `purchase_rejection_quantity`,
    `sm`.`purchase_rejection_weight` as `purchase_rejection_weight`,
    `sm`.`jobcard_quantity` as `jobcard_quantity`,
    `sm`.`jobcard_weight` as `jobcard_weight`,
    `sm`.`production_issue_quantity` as `production_issue_quantity`,
    `sm`.`production_issue_weight` as `production_issue_weight`,
    `sm`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `sm`.`production_issue_return_weight` as `production_issue_return_weight`,
    `sm`.`production_issue_return_boxes` as `production_issue_return_boxes`,
    `sm`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `sm`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `sm`.`production_quantity` as `production_quantity`,
    `sm`.`production_weight` as `production_weight`,
    `sm`.`production_return_quantity` as `production_return_quantity`,
    `sm`.`production_return_weight` as `production_return_weight`,
    `sm`.`production_rejection_quantity` as `production_rejection_quantity`,
    `sm`.`production_rejection_weight` as `production_rejection_weight`,
    `sm`.`assembly_production_issue_quantity` as `assembly_production_issue_quantity`,
    `sm`.`assembly_production_issue_weight` as `assembly_production_issue_weight`,
    `sm`.`sales_quantity` as `sales_quantity`,
    `sm`.`sales_weight` as `sales_weight`,
    `sm`.`sales_return_quantity` as `sales_return_quantity`,
    `sm`.`sales_return_weight` as `sales_return_weight`,
    `sm`.`sales_rejection_quantity` as `sales_rejection_quantity`,
    `sm`.`sales_rejection_weight` as `sales_rejection_weight`,
    `sm`.`customer_receipt_quantity` as `customer_receipt_quantity`,
    `sm`.`customer_receipt_weight` as `customer_receipt_weight`,
    `sm`.`customer_return_quantity` as `customer_return_quantity`,
    `sm`.`customer_return_weight` as `customer_return_weight`,
    `sm`.`customer_rejection_weight` as `customer_rejection_weight`,
    `sm`.`customer_rejection_quantity` as `customer_rejection_quantity`,
    `sm`.`transfer_issue_quantity` as `transfer_issue_quantity`,
    `sm`.`transfer_issue_weight` as `transfer_issue_weight`,
    `sm`.`transfer_receipt_quantity` as `transfer_receipt_quantity`,
    `sm`.`transfer_receipt_weight` as `transfer_receipt_weight`,
    `sm`.`outsources_out_quantity` as `outsources_out_quantity`,
    `sm`.`outsources_out_weight` as `outsources_out_weight`,
    `sm`.`outsources_in_quantity` as `outsources_in_quantity`,
    `sm`.`outsources_in_weight` as `outsources_in_weight`,
    `sm`.`outsources_rejection_quantity` as `outsources_rejection_quantity`,
    `sm`.`outsources_rejection_weight` as `outsources_rejection_weight`,
    `sm`.`loan_receipt_quantity` as `loan_receipt_quantity`,
    `sm`.`loan_receipt_weight` as `loan_receipt_weight`,
    `sm`.`loan_receipt_boxes` as `loan_receipt_boxes`,
    `sm`.`loan_issue_quantity` as `loan_issue_quantity`,
    `sm`.`loan_issue_weight` as `loan_issue_weight`,
    `sm`.`loan_issue_boxes` as `loan_issue_boxes`,
    `sm`.`cancel_quantity` as `cancel_quantity`,
    `sm`.`cancel_weight` as `cancel_weight`,
    `sm`.`difference_quantity` as `difference_quantity`,
    `sm`.`difference_weight` as `difference_weight`,
    `sm`.`total_box_weight` as `total_box_weight`,
    `sm`.`total_quantity_in_box` as `total_quantity_in_box`,
    `sm`.`weight_per_box_item` as `weight_per_box_item`,
    `sm`.`opening_no_of_boxes` as `opening_no_of_boxes`,
    `sm`.`reserve_no_of_boxes` as `reserve_no_of_boxes`,
    `sm`.`purchase_no_of_boxes` as `purchase_no_of_boxes`,
    `sm`.`issue_no_of_boxes` as `issue_no_of_boxes`,
    `sm`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `sm`.`supplier_return_quantity` as `supplier_return_quantity`,
    `sm`.`supplier_return_weight` as `supplier_return_weight`,
    `sm`.`supplier_return_boxes` as `supplier_return_boxes`,
    `gd`.`godown_name` as `godown_name`,
    `gd`.`godown_short_name` as `godown_short_name`,
    `gd`.`godown_section_count` as `godown_section_count`,
    `gds`.`godown_section_name` as `godown_section_name`,
    `gds`.`godown_section_short_name` as `godown_section_short_name`,
    `gdsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `gdsb`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    `sm`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `pt`.`product_type_short_name` as `product_type_short_name`,
    `pp`.`quantity_per_packing` as `quantity_per_packing`,
    `sm`.`material_rate` as `material_rate`,
    case
        `sm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `sm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `sm`.`is_active` as `is_active`,
    `sm`.`is_delete` as `is_delete`,
    `sm`.`created_by` as `created_by`,
    `sm`.`created_on` as `created_on`,
    `sm`.`modified_by` as `modified_by`,
    `sm`.`modified_on` as `modified_on`,
    `sm`.`deleted_by` as `deleted_by`,
    `sm`.`deleted_on` as `deleted_on`,
    `sm`.`company_id` as `company_id`,
    `sm`.`company_branch_id` as `company_branch_id`,
    `sm`.`stock_transaction_id` as `stock_transaction_id`,
    `sm`.`supplier_id` as `supplier_id`,
    `sm`.`customer_id` as `customer_id`,
    `sm`.`product_type_id` as `product_type_id`,
    `sm`.`product_rm_id` as `product_rm_id`,
    `sm`.`product_material_unit_id` as `product_material_unit_id`,
    `sm`.`product_material_packing_id` as `product_material_packing_id`,
    `sm`.`godown_id` as `godown_id`,
    `sm`.`godown_section_id` as `godown_section_id`,
    `sm`.`godown_section_beans_id` as `godown_section_beans_id`,
    `rm`.`product_material_category1_id` as `product_material_category1_id`,
    `rm`.`product_material_category2_id` as `product_material_category2_id`
from
    (((((((((((`sm_product_rm_stock_summary` `sm`
left join `cm_company` `v` on
    (`v`.`company_id` = `sm`.`company_id`
        and `v`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_id` = `sm`.`company_id`
        and `cb`.`company_branch_id` = `sm`.`company_branch_id`
        and `v`.`is_delete` = 0))
left join `cm_supplier_branch` `supp` on
    (`supp`.`is_delete` = 0
        and `supp`.`supp_branch_id` = `sm`.`supplier_id`))
left join `cm_godown` `gd` on
    (`gd`.`godown_id` = `sm`.`godown_id`
        and `gd`.`is_delete` = 0))
left join `cm_godown_section` `gds` on
    (`gds`.`godown_section_id` = `sm`.`godown_section_id`
        and `gds`.`is_delete` = 0))
left join `cm_godown_section_beans` `gdsb` on
    (`gdsb`.`godown_section_beans_id` = `sm`.`godown_section_beans_id`
        and `gdsb`.`is_delete` = 0))
left join `cm_customer` `cust` on
    (`cust`.`company_branch_id` = `sm`.`company_branch_id`
        and `cust`.`company_id` = `sm`.`company_id`
        and `cust`.`customer_id` = `sm`.`customer_id`
        and `cust`.`is_delete` = 0))
left join `sm_product_type` `pt` on
    (`pt`.`product_type_id` = `sm`.`product_type_id`
        and `pt`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rm` on
    (`rm`.`product_material_id` = `sm`.`product_rm_id`
        and `rm`.`product_type_id` = `sm`.`product_type_id`))
left join `sm_product_unit` `pu` on
    (`pu`.`product_unit_id` = `sm`.`product_material_unit_id`
        and `pu`.`is_delete` = 0))
left join `sm_product_packing` `pp` on
    (`pp`.`is_delete` = 0
        and `pp`.`product_packing_id` = `sm`.`product_material_packing_id`))
where
    `sm`.`is_delete` = 0;




-- smv_product_rm_stock_details source

create or REPLACE algorithm = UNDEFINED view `smv_product_rm_stock_details` as
select
    `sm`.`stock_date` as `stock_date`,
    `sm`.`day_closed` as `day_closed`,
    `sm`.`batch_no` as `batch_no`,
    `supp`.`supp_branch_name` as `supplier_name`,
    `sm`.`batch_expiry_date` as `batch_expiry_date`,
    `sm`.`goods_receipt_no` as `goods_receipt_no`,
    `sm`.`goods_receipt_version` as `goods_receipt_version`,
    `cm`.`customer_code` as `customer_code`,
    `cm`.`customer_name` as `customer_name`,
    `sm`.`sales_order_no` as `sales_order_no`,
    `sm`.`sales_order_version` as `sales_order_version`,
    `sm`.`customer_goods_receipt_no` as `customer_goods_receipt_no`,
    `sm`.`customer_goods_receipt_version` as `customer_goods_receipt_version`,
    `sm`.`so_sr_no` as `so_sr_no`,
    `sm`.`customer_order_no` as `customer_order_no`,
    `rm`.`product_material_code` as `product_rm_code`,
    `rm`.`product_material_name` as `product_rm_name`,
    `rm`.`product_material_technical_name` as `product_rm_technical_name`,
    `rm`.`product_type_name` as `product_type_name`,
    `rm`.`product_material_category1_name` as `product_material_category1_name`,
    `rm`.`product_material_category2_name` as `product_material_category2_name`,
    `rm`.`product_material_short_name` as `product_rm_short_name`,
    `su`.`product_unit_name` as `product_material_unit_name`,
    `rm`.`product_material_stock_unit_name` as `product_rm_purchase_unit_name`,
    `pp`.`product_packing_name` as `product_packing_name`,
    `sm`.`batch_rate` as `batch_rate`,
    `sm`.`order_quantity` as `order_quantity`,
    `sm`.`order_weight` as `order_weight`,
    `sm`.`pending_quantity` as `pending_quantity`,
    `sm`.`pending_weight` as `pending_weight`,
    `sm`.`opening_quantity` as `opening_quantity`,
    `sm`.`opening_weight` as `opening_weight`,
    `sm`.`closing_balance_quantity` as `closing_balance_quantity`,
    `sm`.`closing_balance_weight` as `closing_balance_weight`,
    `sm`.`batch_rate` * `sm`.`closing_balance_quantity` as `closing_amount`,
    `sm`.`reserve_quantity` as `reserve_quantity`,
    `sm`.`reserve_weight` as `reserve_weight`,
    `sm`.`excess_quantity` as `excess_quantity`,
    `sm`.`excess_weight` as `excess_weight`,
    `sm`.`pree_closed_quantity` as `pree_closed_quantity`,
    `sm`.`pree_closed_weight` as `pree_closed_weight`,
    `sm`.`purchase_quantity` as `purchase_quantity`,
    `sm`.`purchase_weight` as `purchase_weight`,
    `sm`.`purchase_return_quantity` as `purchase_return_quantity`,
    `sm`.`purchase_return_weight` as `purchase_return_weight`,
    `sm`.`purchase_rejection_quantity` as `purchase_rejection_quantity`,
    `sm`.`purchase_rejection_weight` as `purchase_rejection_weight`,
    `sm`.`jobcard_quantity` as `jobcard_quantity`,
    `sm`.`jobcard_weight` as `jobcard_weight`,
    `sm`.`production_issue_quantity` as `production_issue_quantity`,
    `sm`.`production_issue_weight` as `production_issue_weight`,
    `sm`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `sm`.`production_issue_return_weight` as `production_issue_return_weight`,
    `sm`.`production_issue_return_boxes` as `production_issue_return_boxes`,
    `sm`.`supplier_return_quantity` as `supplier_return_quantity`,
    `sm`.`supplier_return_weight` as `supplier_return_weight`,
    `sm`.`supplier_return_boxes` as `supplier_return_boxes`,
    `sm`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `sm`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `sm`.`production_quantity` as `production_quantity`,
    `sm`.`production_weight` as `production_weight`,
    `sm`.`production_return_quantity` as `production_return_quantity`,
    `sm`.`production_return_weight` as `production_return_weight`,
    `sm`.`production_rejection_quantity` as `production_rejection_quantity`,
    `sm`.`production_rejection_weight` as `production_rejection_weight`,
    `sm`.`assembly_production_issue_quantity` as `assembly_production_issue_quantity`,
    `sm`.`assembly_production_issue_weight` as `assembly_production_issue_weight`,
    `sm`.`sales_quantity` as `sales_quantity`,
    `sm`.`sales_weight` as `sales_weight`,
    `sm`.`sales_return_quantity` as `sales_return_quantity`,
    `sm`.`sales_return_weight` as `sales_return_weight`,
    `sm`.`sales_rejection_quantity` as `sales_rejection_quantity`,
    `sm`.`sales_rejection_weight` as `sales_rejection_weight`,
    `sm`.`customer_receipt_quantity` as `customer_receipt_quantity`,
    `sm`.`customer_receipt_weight` as `customer_receipt_weight`,
    `sm`.`customer_return_quantity` as `customer_return_quantity`,
    `sm`.`customer_return_weight` as `customer_return_weight`,
    `sm`.`customer_rejection_quantity` as `customer_rejection_quantity`,
    `sm`.`customer_rejection_weight` as `customer_rejection_weight`,
    `sm`.`transfer_issue_quantity` as `transfer_issue_quantity`,
    `sm`.`transfer_issue_weight` as `transfer_issue_weight`,
    `sm`.`transfer_receipt_quantity` as `transfer_receipt_quantity`,
    `sm`.`transfer_receipt_weight` as `transfer_receipt_weight`,
    `sm`.`outsources_out_quantity` as `outsources_out_quantity`,
    `sm`.`outsources_out_weight` as `outsources_out_weight`,
    `sm`.`outsources_in_quantity` as `outsources_in_quantity`,
    `sm`.`outsources_in_weight` as `outsources_in_weight`,
    `sm`.`outsources_rejection_quantity` as `outsources_rejection_quantity`,
    `sm`.`outsources_rejection_weight` as `outsources_rejection_weight`,
    `sm`.`loan_receipt_quantity` as `loan_receipt_quantity`,
    `sm`.`loan_receipt_weight` as `loan_receipt_weight`,
    `sm`.`loan_receipt_boxes` as `loan_receipt_boxes`,
    `sm`.`loan_issue_quantity` as `loan_issue_quantity`,
    `sm`.`loan_issue_weight` as `loan_issue_weight`,
    `sm`.`loan_issue_boxes` as `loan_issue_boxes`,
    `sm`.`cancel_quantity` as `cancel_quantity`,
    `sm`.`cancel_weight` as `cancel_weight`,
    `sm`.`difference_quantity` as `difference_quantity`,
    `sm`.`difference_weight` as `difference_weight`,
    `sm`.`total_box_weight` as `total_box_weight`,
    `sm`.`total_quantity_in_box` as `total_quantity_in_box`,
    `sm`.`weight_per_box_item` as `weight_per_box_item`,
    `sm`.`opening_no_of_boxes` as `opening_no_of_boxes`,
    `sm`.`reserve_no_of_boxes` as `reserve_no_of_boxes`,
    `sm`.`purchase_no_of_boxes` as `purchase_no_of_boxes`,
    `sm`.`issue_no_of_boxes` as `issue_no_of_boxes`,
    `sm`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `gd`.`godown_name` as `godown_name`,
    `gd`.`godown_short_name` as `godown_short_name`,
    `gd`.`godown_area` as `godown_area`,
    `gd`.`godown_section_count` as `godown_section_count`,
    `gds`.`godown_section_name` as `godown_section_name`,
    `gds`.`godown_section_short_name` as `godown_section_short_name`,
    `gds`.`godown_section_area` as `godown_section_area`,
    `gdsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `gdsb`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    `gdsb`.`godown_section_beans_area` as `godown_section_beans_area`,
    `pp`.`quantity_per_packing` as `quantity_per_packing`,
    `pp`.`weight_per_packing` as `weight_per_packing`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `sm`.`financial_year` as `financial_year`,
    `sm`.`product_type_group` as `product_type_group`,
    case
        `sm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `sm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `sm`.`is_active` as `is_active`,
    `sm`.`is_delete` as `is_delete`,
    `sm`.`created_by` as `created_by`,
    `sm`.`created_on` as `created_on`,
    `sm`.`modified_by` as `modified_by`,
    `sm`.`modified_on` as `modified_on`,
    `sm`.`deleted_by` as `deleted_by`,
    `sm`.`deleted_on` as `deleted_on`,
    `sm`.`company_id` as `company_id`,
    `sm`.`company_branch_id` as `company_branch_id`,
    `sm`.`stock_transaction_id` as `stock_transaction_id`,
    `sm`.`product_rm_id` as `product_rm_id`,
    `sm`.`customer_id` as `customer_id`,
    `sm`.`godown_id` as `godown_id`,
    `sm`.`supplier_id` as `supplier_id`,
    `sm`.`godown_section_id` as `godown_section_id`,
    `sm`.`godown_section_beans_id` as `godown_section_beans_id`,
    `sm`.`product_type_id` as `product_type_id`,
    `sm`.`product_material_unit_id` as `product_material_unit_id`,
    `sm`.`product_material_packing_id` as `product_material_packing_id`,
    `rm`.`product_material_category1_id` as `product_material_category1_id`,
    `rm`.`product_material_category2_id` as `product_material_category2_id`
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


ALTER TABLE cm_department ADD godown_id BIGINT(20) NULL COMMENT 'main godown of department';
ALTER TABLE cm_department ADD godown_section_id BIGINT(20) NULL COMMENT 'godown section of department';
ALTER TABLE cm_department ADD godown_section_beans_id BIGINT(20) NULL COMMENT 'godown_section_beans of department';

