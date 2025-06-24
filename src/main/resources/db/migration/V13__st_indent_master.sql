
--  smv_product_rm_stock_summary source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW  `smv_product_rm_stock_summary` AS
select
    `sm`.`financial_year` AS `financial_year`,
    `sup`.`supplier_name` AS `supplier_name`,
    `cust`.`customer_name` AS `customer_name`,
    `pt`.`product_type_name` AS `product_type_name`,
    `sm`.`product_type_group` AS `product_type_group`,
    `rm`.`product_material_code` AS `product_rm_code`,
    `rm`.`product_material_name` AS `product_rm_name`,
    `rm`.`product_material_category1_name` AS `product_material_category1_name`,
    `rm`.`product_material_category2_name` AS `product_material_category2_name`,
    `pu`.`product_unit_name` AS `product_unit_name`,
    `pp`.`product_packing_name` AS `product_packing_name`,
    `sm`.`order_quantity` AS `order_quantity`,
    `sm`.`order_weight` AS `order_weight`,
    `sm`.`pending_quantity` AS `pending_quantity`,
    `sm`.`pending_weight` AS `pending_weight`,
    `sm`.`opening_quantity` AS `opening_quantity`,
    `sm`.`opening_weight` AS `opening_weight`,
    `sm`.`closing_balance_quantity` AS `closing_balance_quantity`,
    `sm`.`closing_balance_weight` AS `closing_balance_weight`,
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
    `sm`.`customer_rejection_weight` AS `customer_rejection_weight`,
    `sm`.`customer_rejection_quantity` AS `customer_rejection_quantity`,
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
    `gd`.`godown_name` AS `godown_name`,
    `gd`.`godown_short_name` AS `godown_short_name`,
    `gd`.`godown_section_count` AS `godown_section_count`,
    `gds`.`godown_section_name` AS `godown_section_name`,
    `gds`.`godown_section_short_name` AS `godown_section_short_name`,
    `gdsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `gdsb`.`godown_section_beans_short_name` AS `godown_section_beans_short_name`,
    `sm`.`remark` AS `remark`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `pt`.`product_type_short_name` AS `product_type_short_name`,
    `pp`.`quantity_per_packing` AS `quantity_per_packing`,
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
    `sm`.`supplier_id` AS `supplier_id`,
    `sm`.`customer_id` AS `customer_id`,
    `sm`.`product_type_id` AS `product_type_id`,
    `sm`.`product_rm_id` AS `product_rm_id`,
    `sm`.`product_material_unit_id` AS `product_material_unit_id`,
    `sm`.`product_material_packing_id` AS `product_material_packing_id`,
    `sm`.`godown_id` AS `godown_id`,
    `sm`.`godown_section_id` AS `godown_section_id`,
    `sm`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `rm`.`product_material_category1_id` AS `product_material_category1_id`,
    `rm`.`product_material_category2_id` AS `product_material_category2_id`
from
    (((((((((( `sm_product_rm_stock_summary` `sm`
left join  `cmv_company` `v` on
    (`v`.`company_id` = `sm`.`company_id`
        and `v`.`company_branch_id` = `sm`.`company_branch_id`))
left join  `cm_supplier` `sup` on
    (`sup`.`company_branch_id` = `sm`.`company_branch_id`
        and `sup`.`company_id` = `sm`.`company_id`
        and `sup`.`supplier_id` = `sm`.`supplier_id`
        and `sup`.`is_delete` = 0))
left join  `cm_godown` `gd` on
    (`gd`.`godown_id` = `sm`.`godown_id`
        and `gd`.`is_delete` = 0))
left join  `cm_godown_section` `gds` on
    (`gds`.`godown_section_id` = `sm`.`godown_section_id`
        and `gds`.`is_delete` = 0))
left join  `cm_godown_section_beans` `gdsb` on
    (`gdsb`.`godown_section_beans_id` = `sm`.`godown_section_beans_id`
        and `gdsb`.`is_delete` = 0))
left join  `cm_customer` `cust` on
    (`cust`.`company_branch_id` = `sm`.`company_branch_id`
        and `cust`.`company_id` = `sm`.`company_id`
        and `cust`.`customer_id` = `sm`.`customer_id`
        and `cust`.`is_delete` = 0))
left join  `sm_product_type` `pt` on
    (`pt`.`product_type_id` = `sm`.`product_type_id`
        and `pt`.`is_delete` = 0))
left join  `smv_product_rm_fg_sr` `rm` on
    (`rm`.`product_material_id` = `sm`.`product_rm_id`
        and `rm`.`product_type_id` = `sm`.`product_type_id`))
left join  `sm_product_unit` `pu` on
    (`pu`.`product_unit_id` = `sm`.`product_material_unit_id`
        and `pu`.`is_delete` = 0))
left join  `sm_product_packing` `pp` on
    (`pp`.`is_delete` = 0
        and `pp`.`product_packing_id` = `sm`.`product_material_packing_id`))
where
    `sm`.`is_delete` = 0;

-- smv_product_rm_stock_summary_rpt source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_rm_stock_summary_rpt` AS
select
    concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:Y:C:smv_product_type:F') AS `product_type_name`,
    concat(ifnull(`v`.`product_rm_name`, ''), ':Material Name:Y:T') AS `product_rm_name`,
    concat(ifnull(`v`.`product_rm_code`, ''), ':Material Code:Y:T') AS `product_rm_code`,
    concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:Y:C:cmv_godown:F') AS `godown_name`,
    concat(ifnull(`v`.`godown_section_name`, ''), ':Godown Section Name:Y:C:cmv_godown_section:F') AS `godown_section_name`,
    concat(ifnull(`v`.`godown_section_beans_name`, ''), ':Godown Section Beans Name:Y:C:cmv_godown_section_beans:F') AS `godown_section_beans_name`,
    concat(ifnull(`v`.`product_material_category1_name`, ''), ':Catergory1 Name:Y:C:smv_product_category1:F') AS `product_material_category1_name`,
    concat(ifnull(`v`.`product_material_category2_name`, ''), ':Catergory2 Name:Y:C:smv_product_category2:F') AS `product_material_category2_name`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') AS `company_name`,
    concat(ifnull(`v`.`product_type_group`, ''), ':Product Type Group:O:N:') AS `product_type_group`,
    concat(ifnull(`v`.`order_quantity`, ''), ':Order Quantity:O:N:') AS `order_quantity`,
    concat(ifnull(`v`.`order_weight`, ''), ':Order Weight:O:N:') AS `order_weight`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier:F') AS `supplier_name`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer:Y:C:cmv_customer:F') AS `customer_name`,
    concat(ifnull(`v`.`pending_quantity`, ''), ':Pending Quantity:O:N:') AS `pending_quantity`,
    concat(ifnull(`v`.`pending_weight`, ''), ':Pending Weight:O:N:') AS `pending_weight`,
    concat(ifnull(`v`.`opening_quantity`, ''), ':Opening Quantity:O:N:') AS `opening_quantity`,
    concat(ifnull(`v`.`opening_weight`, ''), ':Opening Weight:O:N:') AS `opening_weight`,
    concat(ifnull(`v`.`reserve_quantity`, ''), ':Reserve Quantity:O:N:') AS `reserve_quantity`,
    concat(ifnull(`v`.`reserve_weight`, ''), ':Reserve Weight:O:N:') AS `reserve_weight`,
    concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') AS `excess_quantity`,
    concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') AS `excess_weight`,
    concat(ifnull(`v`.`pree_closed_quantity`, ''), ':Pree Closed Quantity:O:N:') AS `pree_closed_quantity`,
    concat(ifnull(`v`.`pree_closed_weight`, ''), ':Pree Closed Weight:O:N:') AS `pree_closed_weight`,
    concat(ifnull(`v`.`purchase_quantity`, ''), ':Purchase Quantity:O:N:') AS `purchase_quantity`,
    concat(ifnull(`v`.`purchase_weight`, ''), ':Purchase Weight:O:N:') AS `purchase_weight`,
    concat(ifnull(`v`.`purchase_return_quantity`, ''), ':Purchase Return Quantity:O:N:') AS `purchase_return_quantity`,
    concat(ifnull(`v`.`purchase_return_weight`, ''), ':Purchase Return Weight:O:N:') AS `purchase_return_weight`,
    concat(ifnull(`v`.`purchase_rejection_quantity`, ''), ':Purchase Rejection Quantity:O:N:') AS `purchase_rejection_quantity`,
    concat(ifnull(`v`.`purchase_rejection_weight`, ''), ':Purchase Rejection Weight:O:N:') AS `purchase_rejection_weight`,
    concat(ifnull(`v`.`jobcard_quantity`, ''), ':Jobcard Quantity:O:N:') AS `jobcard_quantity`,
    concat(ifnull(`v`.`jobcard_weight`, ''), ':Jobcard Weight:O:N:') AS `jobcard_weight`,
    concat(ifnull(`v`.`production_issue_quantity`, ''), ':Issue Quantity:O:N:') AS `production_issue_quantity`,
    concat(ifnull(`v`.`production_issue_weight`, ''), ':Issue Weight:O:N:') AS `production_issue_weight`,
    concat(ifnull(`v`.`production_issue_return_quantity`, ''), ':Issue Return Quantity:O:N:') AS `production_issue_return_quantity`,
    concat(ifnull(`v`.`production_issue_return_weight`, ''), ':Issue Return Weight:O:N:') AS `production_issue_return_weight`,
    concat(ifnull(`v`.`production_issue_rejection_quantity`, ''), ':Issue Rejection Quantity:O:N:') AS `production_issue_rejection_quantity`,
    concat(ifnull(`v`.`production_issue_rejection_weight`, ''), ':Issue Rejection Weight:O:N:') AS `production_issue_rejection_weight`,
    concat(ifnull(`v`.`production_quantity`, ''), ':Production Quantity:O:N:') AS `production_quantity`,
    concat(ifnull(`v`.`production_weight`, ''), ':Production Weight:O:N:') AS `production_weight`,
    concat(ifnull(`v`.`production_return_quantity`, ''), ':Production Return Quantity:O:N:') AS `production_return_quantity`,
    concat(ifnull(`v`.`production_return_weight`, ''), ':Production Return Weight:O:N:') AS `production_return_weight`,
    concat(ifnull(`v`.`production_rejection_quantity`, ''), ':Production Rejection Quantity:O:N:') AS `production_rejection_quantity`,
    concat(ifnull(`v`.`production_rejection_weight`, ''), ':Production Rejection Weight:O:N:') AS `production_rejection_weight`,
    concat(ifnull(`v`.`assembly_production_issue_quantity`, ''), ':Assembly Production Issue Quantity:O:N:') AS `assembly_production_issue_quantity`,
    concat(ifnull(`v`.`assembly_production_issue_weight`, ''), ':Assembly Production Issue Weight:O:N:') AS `assembly_production_issue_weight`,
    concat(ifnull(`v`.`customer_receipt_quantity`, ''), ':Customer Receipt Quantity:O:N:') AS `customer_receipt_quantity`,
    concat(ifnull(`v`.`customer_receipt_weight`, ''), ':Customer Receipt Weight:O:N:') AS `customer_receipt_weight`,
    concat(ifnull(`v`.`customer_return_quantity`, ''), ':Customer Return Quantity:O:N:') AS `customer_return_quantity`,
    concat(ifnull(`v`.`customer_return_weight`, ''), ':Customer Return Weight:O:N:') AS `customer_return_weight`,
    concat(ifnull(`v`.`customer_rejection_weight`, ''), ':Customer Rejection Weight:O:N:') AS `customer_rejection_weight`,
    concat(ifnull(`v`.`customer_rejection_quantity`, ''), ':Customer Rejection Quantity:O:N:') AS `customer_rejection_quantity`,
    concat(ifnull(`v`.`sales_quantity`, ''), ':Sales Quantity:O:N:') AS `sales_quantity`,
    concat(ifnull(`v`.`sales_weight`, ''), ':Sales Weight:O:N:') AS `sales_weight`,
    concat(ifnull(`v`.`sales_return_quantity`, ''), ':Sales Return Quantity:O:N:') AS `sales_return_quantity`,
    concat(ifnull(`v`.`sales_return_weight`, ''), ':Sales Return Weight:O:N:') AS `sales_return_weight`,
    concat(ifnull(`v`.`sales_rejection_quantity`, ''), ':Sales Rejection Quantity:O:N:') AS `sales_rejection_quantity`,
    concat(ifnull(`v`.`sales_rejection_weight`, ''), ':Sales Rejection Weight:O:N:') AS `sales_rejection_weight`,
    concat(ifnull(`v`.`transfer_issue_quantity`, ''), ':Transfer Issue Quantity:O:N:') AS `transfer_issue_quantity`,
    concat(ifnull(`v`.`transfer_issue_weight`, ''), ':Transfer Issue Weight:O:N:') AS `transfer_issue_weight`,
    concat(ifnull(`v`.`transfer_receipt_quantity`, ''), ':Transfer Receipt Quantity:O:N:') AS `transfer_receipt_quantity`,
    concat(ifnull(`v`.`transfer_receipt_weight`, ''), ':Transfer Receipt Weight:O:N:') AS `transfer_receipt_weight`,
    concat(ifnull(`v`.`outsources_out_quantity`, ''), ':Outsources Out Quantity:O:N:') AS `outsources_out_quantity`,
    concat(ifnull(`v`.`outsources_out_weight`, ''), ':Outsources Out Weight:O:N:') AS `outsources_out_weight`,
    concat(ifnull(`v`.`outsources_in_quantity`, ''), ':Outsources In Quantity:O:N:') AS `outsources_in_quantity`,
    concat(ifnull(`v`.`outsources_in_weight`, ''), ':Outsources In Weight:O:N:') AS `outsources_in_weight`,
    concat(ifnull(`v`.`outsources_rejection_quantity`, ''), ':Outsources Rejection Quantity:O:N:') AS `outsources_rejection_quantity`,
    concat(ifnull(`v`.`outsources_rejection_weight`, ''), ':Outsources Rejection Weight:O:N:') AS `outsources_rejection_weight`,
    concat(ifnull(`v`.`loan_receipt_quantity`, ''), ':Loan Receipt Quantity:O:N:') AS `loan_receipt_quantity`,
    concat(ifnull(`v`.`loan_receipt_weight`, ''), ':Loan Receipt Weight:O:N:') AS `loan_receipt_weight`,
    concat(ifnull(`v`.`loan_issue_quantity`, ''), ':Loan Issue Quantity:O:N:') AS `loan_issue_quantity`,
    concat(ifnull(`v`.`loan_issue_weight`, ''), ':Loan Issue Weight:O:N:') AS `loan_issue_weight`,
    concat(ifnull(`v`.`cancel_quantity`, ''), ':Cancel Quantity:O:N:') AS `cancel_quantity`,
    concat(ifnull(`v`.`cancel_weight`, ''), ':Cancel Weight:O:N:') AS `cancel_weight`,
    concat(ifnull(`v`.`difference_quantity`, ''), ':Difference Quantity:O:N:') AS `difference_quantity`,
    concat(ifnull(`v`.`difference_weight`, ''), ':Difference Weight:O:N:') AS `difference_weight`,
    concat(ifnull(`v`.`closing_balance_quantity`, ''), ':Closing Balance Quantity:O:N:') AS `closing_balance_quantity`,
    concat(ifnull(`v`.`closing_balance_weight`, ''), ':Closing Balance Weight:O:N:') AS `closing_balance_weight`,
    concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') AS `product_type_short_name`,
    concat(ifnull(`v`.`product_packing_name`, ''), ':Product Packing Name:Y:C:smv_product_packing') AS `product_packing_name`,
    concat(ifnull(`v`.`quantity_per_packing`, ''), ':Quantity Per Packing:O:N:') AS `quantity_per_packing`,
    concat(ifnull(`v`.`product_unit_name`, ''), ':Product Unit Name:Y:C:smv_product_unit:F') AS `product_unit_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') AS `financial_year`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') AS `remark`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch:F') AS `company_branch_name`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') AS `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') AS `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(ifnull(`v`.`stock_transaction_id`, ''), ':Stock Transaction Id:O:N:') AS `stock_transaction_id`,
    concat(ifnull(`v`.`product_type_id`, ''), ':Product Type Id:N:N:') AS `product_type_id`,
    concat(ifnull(`v`.`product_rm_id`, ''), ':Product Rm Id:O:N:') AS `product_rm_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Product Material Unit Id:N:N:') AS `product_material_unit_id`,
    concat(ifnull(`v`.`product_material_packing_id`, ''), ':Product Material Packing Id:N:N:') AS `product_material_packing_id`,
    concat(ifnull(`v`.`godown_id`, ''), ':Godown Id:N:N:') AS `godown_id`,
    concat(ifnull(`v`.`godown_section_id`, ''), ':Godown Section Id:N:N:') AS `godown_section_id`,
    concat(ifnull(`v`.`godown_section_beans_id`, ''), ':Godown Section Beans Id:N:N:') AS `godown_section_beans_id`
from
    `smv_product_rm_stock_summary` `v`
limit 1;

-- smv_product_rm_stock_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_rm_stock_details` AS
select
    `sm`.`stock_date` AS `stock_date`,
    `sm`.`day_closed` AS `day_closed`,
    `sm`.`batch_no` AS `batch_no`,
    `supp`.`supplier_code` AS `supplier_code`,
    `supp`.`supplier_name` AS `supplier_name`,
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
left join `cm_supplier` `supp` on
    (`supp`.`is_delete` = 0
        and `supp`.`supplier_id` = `sm`.`supplier_id`))
left join `sm_product_unit` `su` on
    (`su`.`product_unit_id` = `sm`.`product_material_unit_id`
        and `su`.`is_delete` = 0))
where
    `sm`.`is_delete` = 0
    and `sm`.`day_closed` = 0;


-- smv_product_rm_stock_details_rpt source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_rm_stock_details_rpt` AS
select
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') AS `company_name`,
    concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:Y:C:cmv_godown:F') AS `godown_name`,
    concat(ifnull(`v`.`godown_section_name`, ''), ':Godown Section Name:Y:C:cmv_godown_section') AS `godown_section_name`,
    concat(ifnull(`v`.`godown_section_beans_name`, ''), ':Godown Section Beans Name:Y:C:cmv_godown_section_beans') AS `godown_section_beans_name`,
    concat(ifnull(`v`.`product_rm_name`, ''), ':Material Name:Y:T') AS `product_rm_name`,
    concat(ifnull(`v`.`product_rm_code`, ''), ':Material Code:Y:T') AS `product_rm_code`,
    concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:O:N:') AS `product_type_name`,
    concat(ifnull(`v`.`product_material_category1_name`, ''), ':Catergory1 Name:Y:C:smv_product_category1:F') AS `product_material_category1_name`,
    concat(ifnull(`v`.`product_material_category2_name`, ''), ':Catergory2 Name:Y:C:smv_product_category2:F') AS `product_material_category2_name`,
    concat(ifnull(`v`.`stock_date`, ''), ':Stock Date:Y:D:') AS `stock_date`,
    concat(ifnull(`v`.`day_closed`, ''), ':Day Closed:O:N:') AS `day_closed`,
    concat(ifnull(`v`.`batch_no`, ''), ':Batch No:O:N:') AS `batch_no`,
    concat(ifnull(`v`.`batch_expiry_date`, ''), ':Batch Expiry Date:Y:D:') AS `batch_expiry_date`,
    concat(ifnull(`v`.`sales_order_no`, ''), ':Sales Order No:O:N:') AS `sales_order_no`,
    concat(ifnull(`v`.`sales_order_version`, ''), ':Sales Order Version:O:N:') AS `sales_order_version`,
    concat(ifnull(`v`.`goods_receipt_no`, ''), ':Goods Receipt No:O:N:') AS `goods_receipt_no`,
    concat(ifnull(`v`.`goods_receipt_version`, ''), ':Goods Receipt Version:O:N:') AS `goods_receipt_version`,
    concat(ifnull(`v`.`customer_goods_receipt_no`, ''), ':Customer Goods Receipt No:O:N:') AS `customer_goods_receipt_no`,
    concat(ifnull(`v`.`customer_goods_receipt_no`, ''), ':Customer Goods Receipt Version:O:N:') AS `customer_goods_receipt_version`,
    concat(ifnull(`v`.`so_sr_no`, ''), ':So Sr No:O:N:') AS `so_sr_no`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:O:N:') AS `customer_order_no`,
    concat(ifnull(`v`.`order_quantity`, ''), ':Order Quantity:O:N:') AS `order_quantity`,
    concat(ifnull(`v`.`order_weight`, ''), ':Order Weight:O:N:') AS `order_weight`,
    concat(ifnull(`v`.`batch_rate`, ''), ':Batch Rate:Y:T:') AS `batch_rate`,
    concat(ifnull(`v`.`closing_amount`, ''), ':Closing Amount:Y:T:') AS `closing_amount`,
    concat(ifnull(`v`.`pending_quantity`, ''), ':Pending Quantity:O:N:') AS `pending_quantity`,
    concat(ifnull(`v`.`pending_weight`, ''), ':Pending Weight:O:N:') AS `pending_weight`,
    concat(ifnull(`v`.`opening_quantity`, ''), ':Opening Quantity:O:N:') AS `opening_quantity`,
    concat(ifnull(`v`.`opening_weight`, ''), ':Opening Weight:O:N:') AS `opening_weight`,
    concat(ifnull(`v`.`reserve_quantity`, ''), ':Reserve Quantity:O:N:') AS `reserve_quantity`,
    concat(ifnull(`v`.`reserve_weight`, ''), ':Reserve Weight:O:N:') AS `reserve_weight`,
    concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') AS `excess_quantity`,
    concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') AS `excess_weight`,
    concat(ifnull(`v`.`pree_closed_quantity`, ''), ':Pree Closed Quantity:O:N:') AS `pree_closed_quantity`,
    concat(ifnull(`v`.`pree_closed_weight`, ''), ':Pree Closed Weight:O:N:') AS `pree_closed_weight`,
    concat(ifnull(`v`.`purchase_quantity`, ''), ':Purchase Quantity:O:N:') AS `purchase_quantity`,
    concat(ifnull(`v`.`purchase_weight`, ''), ':Purchase Weight:O:N:') AS `purchase_weight`,
    concat(ifnull(`v`.`purchase_return_quantity`, ''), ':Purchase Return Quantity:O:N:') AS `purchase_return_quantity`,
    concat(ifnull(`v`.`purchase_return_weight`, ''), ':Purchase Return Weight:O:N:') AS `purchase_return_weight`,
    concat(ifnull(`v`.`purchase_rejection_quantity`, ''), ':Purchase Rejection Quantity:O:N:') AS `purchase_rejection_quantity`,
    concat(ifnull(`v`.`purchase_rejection_weight`, ''), ':Purchase Rejection Weight:O:N:') AS `purchase_rejection_weight`,
    concat(ifnull(`v`.`jobcard_quantity`, ''), ':Jobcard Quantity:O:N:') AS `jobcard_quantity`,
    concat(ifnull(`v`.`jobcard_weight`, ''), ':Jobcard Weight:O:N:') AS `jobcard_weight`,
    concat(ifnull(`v`.`production_issue_quantity`, ''), ':Issue Quantity:O:N:') AS `production_issue_quantity`,
    concat(ifnull(`v`.`production_issue_weight`, ''), ':Issue Weight:O:N:') AS `production_issue_weight`,
    concat(ifnull(`v`.`production_issue_return_quantity`, ''), ':Issue Return Quantity:O:N:') AS `production_issue_return_quantity`,
    concat(ifnull(`v`.`production_issue_return_weight`, ''), ':Issue Return Weight:O:N:') AS `production_issue_return_weight`,
    concat(ifnull(`v`.`production_issue_rejection_quantity`, ''), ':Issue Rejection Quantity:O:N:') AS `production_issue_rejection_quantity`,
    concat(ifnull(`v`.`production_issue_rejection_weight`, ''), ':Production Issue Rejection Weight:O:N:') AS `production_issue_rejection_weight`,
    concat(ifnull(`v`.`production_quantity`, ''), ':Production Quantity:O:N:') AS `production_quantity`,
    concat(ifnull(`v`.`production_weight`, ''), ':Production Weight:O:N:') AS `production_weight`,
    concat(ifnull(`v`.`production_return_quantity`, ''), ':Return Quantity:O:N:') AS `production_return_quantity`,
    concat(ifnull(`v`.`production_return_weight`, ''), ':Return Weight:O:N:') AS `production_return_weight`,
    concat(ifnull(`v`.`production_rejection_quantity`, ''), ':Rejection Quantity:O:N:') AS `production_rejection_quantity`,
    concat(ifnull(`v`.`production_rejection_weight`, ''), ':Rejection Weight:O:N:') AS `production_rejection_weight`,
    concat(ifnull(`v`.`assembly_production_issue_quantity`, ''), ':Assembly Production Issue Quantity:O:N:') AS `assembly_production_issue_quantity`,
    concat(ifnull(`v`.`assembly_production_issue_weight`, ''), ':Assembly Production Issue Weight:O:N:') AS `assembly_production_issue_weight`,
    concat(ifnull(`v`.`customer_receipt_quantity`, ''), ':Customer Receipt Quantity:O:N:') AS `customer_receipt_quantity`,
    concat(ifnull(`v`.`customer_return_quantity`, ''), ':Customer Return Quantity:O:N:') AS `customer_return_quantity`,
    concat(ifnull(`v`.`customer_receipt_weight`, ''), ':Customer Receipt Weight:O:N:') AS `customer_receipt_weight`,
    concat(ifnull(`v`.`customer_return_weight`, ''), ':Customer Return Weight:O:N:') AS `customer_return_weight`,
    concat(ifnull(`v`.`customer_rejection_weight`, ''), ':Customer Rejection Weight:O:N:') AS `customer_rejection_weight`,
    concat(ifnull(`v`.`customer_rejection_quantity`, ''), ':Customer Rejection Quantity:O:N:') AS `customer_rejection_quantity`,
    concat(ifnull(`v`.`sales_quantity`, ''), ':Sales Quantity:O:N:') AS `sales_quantity`,
    concat(ifnull(`v`.`sales_weight`, ''), ':Sales Weight:O:N:') AS `sales_weight`,
    concat(ifnull(`v`.`sales_return_quantity`, ''), ':Sales Return Quantity:O:N:') AS `sales_return_quantity`,
    concat(ifnull(`v`.`sales_return_weight`, ''), ':Sales Return Weight:O:N:') AS `sales_return_weight`,
    concat(ifnull(`v`.`sales_rejection_quantity`, ''), ':Sales Rejection Quantity:O:N:') AS `sales_rejection_quantity`,
    concat(ifnull(`v`.`sales_rejection_weight`, ''), ':Sales Rejection Weight:O:N:') AS `sales_rejection_weight`,
    concat(ifnull(`v`.`transfer_issue_quantity`, ''), ':Transfer Issue Quantity:O:N:') AS `transfer_issue_quantity`,
    concat(ifnull(`v`.`transfer_issue_weight`, ''), ':Transfer Issue Weight:O:N:') AS `transfer_issue_weight`,
    concat(ifnull(`v`.`transfer_receipt_quantity`, ''), ':Transfer Receipt Quantity:O:N:') AS `transfer_receipt_quantity`,
    concat(ifnull(`v`.`transfer_receipt_weight`, ''), ':Transfer Receipt Weight:O:N:') AS `transfer_receipt_weight`,
    concat(ifnull(`v`.`outsources_out_quantity`, ''), ':Outsources Out Quantity:O:N:') AS `outsources_out_quantity`,
    concat(ifnull(`v`.`outsources_out_weight`, ''), ':Outsources Out Weight:O:N:') AS `outsources_out_weight`,
    concat(ifnull(`v`.`outsources_in_quantity`, ''), ':Outsources In Quantity:O:N:') AS `outsources_in_quantity`,
    concat(ifnull(`v`.`outsources_in_weight`, ''), ':Outsources In Weight:O:N:') AS `outsources_in_weight`,
    concat(ifnull(`v`.`outsources_rejection_quantity`, ''), ':Outsources Rejection Quantity:O:N:') AS `outsources_rejection_quantity`,
    concat(ifnull(`v`.`outsources_rejection_weight`, ''), ':Outsources Rejection Weight:O:N:') AS `outsources_rejection_weight`,
    concat(ifnull(`v`.`loan_receipt_quantity`, ''), ':Loan Receipt Quantity:O:N:') AS `loan_receipt_quantity`,
    concat(ifnull(`v`.`loan_receipt_weight`, ''), ':Loan Receipt Weight:O:N:') AS `loan_receipt_weight`,
    concat(ifnull(`v`.`loan_issue_quantity`, ''), ':Loan Issue Quantity:O:N:') AS `loan_issue_quantity`,
    concat(ifnull(`v`.`loan_issue_weight`, ''), ':Loan Issue Weight:O:N:') AS `loan_issue_weight`,
    concat(ifnull(`v`.`cancel_quantity`, ''), ':Cancel Quantity:O:N:') AS `cancel_quantity`,
    concat(ifnull(`v`.`cancel_weight`, ''), ':Cancel Weight:O:N:') AS `cancel_weight`,
    concat(ifnull(`v`.`difference_quantity`, ''), ':Difference Quantity:O:N:') AS `difference_quantity`,
    concat(ifnull(`v`.`difference_weight`, ''), ':Difference Weight:O:N:') AS `difference_weight`,
    concat(ifnull(`v`.`closing_balance_quantity`, ''), ':Closing Balance Quantity:O:N:') AS `closing_balance_quantity`,
    concat(ifnull(`v`.`closing_balance_weight`, ''), ':Closing Balance Weight:O:N:') AS `closing_balance_weight`,
    concat(ifnull(`v`.`product_rm_technical_name`, ''), ':Raw Material Technical Name:O:N:') AS `product_rm_technical_name`,
    concat(ifnull(`v`.`product_rm_short_name`, ''), ':Raw Material Short Name:O:N:') AS `product_rm_short_name`,
    concat(ifnull(`v`.`product_rm_purchase_unit_name`, ''), 'Raw Material Purchase Unit Name:O:N:') AS `product_rm_purchase_unit_name`,
    concat(ifnull(`v`.`customer_code`, ''), ':Customer Code:O:N:') AS `customer_code`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer:F') AS `customer_name`,
    concat(ifnull(`v`.`godown_short_name`, ''), ':Godown Short Name:O:N:') AS `godown_short_name`,
    concat(ifnull(`v`.`godown_area`, ''), ':Godown Area:O:N:') AS `godown_area`,
    concat(ifnull(`v`.`godown_section_count`, ''), ':Godown Section Count:O:N:') AS `godown_section_count`,
    concat(ifnull(`v`.`godown_section_short_name`, ''), ':Godown Section Short Name:O:N:') AS `godown_section_short_name`,
    concat(ifnull(`v`.`godown_section_area`, ''), ':Godown Section Area:O:N:') AS `godown_section_area`,
    concat(ifnull(`v`.`godown_section_beans_short_name`, ''), ':Godown Section Beans Short Name:O:N:') AS `godown_section_beans_short_name`,
    concat(ifnull(`v`.`godown_section_beans_area`, ''), ':Godown Section Beans Area:O:N:') AS `godown_section_beans_area`,
    concat(ifnull(`v`.`product_packing_name`, ''), ':Product Packing Name:Y:C:smv_product_packing:F') AS `product_packing_name`,
    concat(ifnull(`v`.`quantity_per_packing`, ''), ':Quantity Per Packing:O:N:') AS `quantity_per_packing`,
    concat(ifnull(`v`.`weight_per_packing`, ''), ':Weight Per Packing:O:N:') AS `weight_per_packing`,
    concat(ifnull(`v`.`supplier_code`, ''), ':Supplier Code:O:N:') AS `supplier_code`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier:F') AS `supplier_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') AS `financial_year`,
    concat(ifnull(`v`.`product_type_group`, ''), ':Product Type Group:O:N:') AS `product_type_group`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch:F') AS `company_branch_name`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') AS `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') AS `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(ifnull(`v`.`stock_transaction_id`, ''), ':Stock Transaction Id:O:N:') AS `stock_transaction_id`,
    concat(ifnull(`v`.`product_rm_id`, ''), ':Product Rm Id:O:N:') AS `product_rm_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:O:N:') AS `customer_id`,
    concat(ifnull(`v`.`godown_id`, ''), ':Godown Id:N:N:') AS `godown_id`,
    concat(ifnull(`v`.`supplier_id`, ''), ':Supplier Id:N:N:') AS `supplier_id`,
    concat(ifnull(`v`.`godown_section_id`, ''), ':Godown Section Id:N:N:') AS `godown_section_id`,
    concat(ifnull(`v`.`godown_section_beans_id`, ''), ':Godown Section Beans Id:N:N:') AS `godown_section_beans_id`,
    concat(ifnull(`v`.`product_type_id`, ''), ':Product Type Id:N:N:') AS `product_type_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Product Material Unit_id:N:N:') AS `product_material_unit_id`,
    concat(ifnull(`v`.`product_material_packing_id`, ''), ':Product Material Packing Id:N:N:') AS `product_material_packing_id`
from
    `smv_product_rm_stock_details` `v`
limit 1;


-- 20-08-24

ALTER TABLE st_indent_master ADD po_status varchar(3) DEFAULT 'P' NULL COMMENT 'purchase order status for the indent master';
ALTER TABLE st_indent_master CHANGE po_status po_status varchar(3) DEFAULT 'P' NULL COMMENT 'purchase order status for the indent master' AFTER indent_status;
ALTER TABLE st_indent_master ADD grn_status varchar(3) DEFAULT 'P' NULL COMMENT 'GRN status for the Indent master';
ALTER TABLE st_indent_master CHANGE grn_status grn_status varchar(3) DEFAULT 'P' NULL COMMENT 'GRN status for the Indent master' AFTER po_status;
ALTER TABLE st_indent_master ADD issue_status varchar(3) DEFAULT 'P' NULL COMMENT 'issue_status for the Indent master';
ALTER TABLE st_indent_master CHANGE issue_status issue_status varchar(3) DEFAULT 'P' NULL COMMENT 'issue_status for the Indent master' AFTER grn_status;



ALTER TABLE st_indent_details ADD po_item_status varchar(3) DEFAULT 'P' NULL COMMENT 'purchase order status for the indent master';
ALTER TABLE st_indent_details CHANGE po_item_status po_item_status varchar(3) DEFAULT 'P' NULL COMMENT 'purchase order status for the indent master' AFTER indent_item_status;
ALTER TABLE st_indent_details ADD grn_item_status varchar(3) DEFAULT 'P' NULL COMMENT 'GRN status for the Indent master';
ALTER TABLE st_indent_details CHANGE grn_item_status grn_item_status varchar(3) DEFAULT 'P' NULL COMMENT 'GRN status for the Indent master' AFTER po_item_status;
ALTER TABLE st_indent_details ADD issue_item_status varchar(3) DEFAULT 'P' NULL COMMENT 'issue_item_status for the Indent master';
ALTER TABLE st_indent_details CHANGE issue_item_status issue_item_status varchar(3) DEFAULT 'P' NULL COMMENT 'issue_item_status for the Indent master' AFTER grn_item_status;


-- stv_indent_details source

create or replace
algorithm = UNDEFINED view `stv_indent_details` as
select
	`v`.`indent_no` as `indent_no`,
	`v`.`indent_date` as `indent_date`,
	`v`.`indent_version` as `indent_version`,
	case
		`st`.`indent_item_status` when 'A' then 'Approved'
		when 'R' then 'Rejected'
		when 'C' then 'Completed'
		when 'X' then 'Cenceled'
		else 'Pending'
	end as `indent_item_status_desc`,
	`v`.`indent_source_desc` as `indent_source_desc`,
	`v`.`department_name` as `department_name`,
	`v`.`sub_department_name` as `sub_department_name`,
	`v`.`indented_by_name` as `indented_by_name`,
	`v`.`approved_by_name` as `approved_by_name`,
	`v`.`customer_name` as `customer_name`,
	`v`.`customer_order_date` as `customer_order_date`,
	`v`.`expected_schedule_date` as `expected_schedule_date`,
	`v`.`indent_status_desc` as `indent_status_desc`,
	`st`.`customer_order_no` as `customer_order_no`,
	`st`.`so_sr_no` as `so_sr_no`,
	`st`.`sales_order_details_transaction_id` as `sales_order_details_transaction_id`,
	`st`.`remark` as `remark`,
	`parent`.`product_material_name` as `product_parent_fg_name`,
	`st`.`product_fg_material_quantity` as `product_fg_material_quantity`,
	`st`.`product_fg_material_weight` as `product_fg_material_weight`,
	`child`.`product_material_name` as `product_rm_name`,
	`child`.`product_material_drawing_no` as `product_rm_drawing_no`,
	`child`.`product_material_tech_spect` as `product_rm_tech_spect`,
	`child`.`product_material_stock_unit_name` as `product_rm_stock_unit_name`,
	`child`.`product_type_name` as `product_type_name`,
	`child`.`product_material_category1_name` as `product_category1_name`,
	`child`.`product_material_oem_part_code` as `product_rm_oem_part_code`,
	`child`.`product_material_our_part_code` as `product_rm_our_part_code`,
	`child`.`product_material_category2_name` as `product_category2_name`,
	`child`.`product_material_category3_name` as `product_category3_name`,
	`child`.`product_material_category4_name` as `product_category4_name`,
	`child`.`product_material_category5_name` as `product_category5_name`,
	`child`.`product_material_make_name` as `product_make_name`,
	`child`.`product_material_type_name` as `product_material_type_name`,
	`child`.`product_material_grade_name` as `product_material_grade_name`,
	`child`.`product_material_std_weight` as `product_rm_std_weight`,
	`child`.`product_material_packing_name` as `product_rm_packing_name`,
	`child`.`product_material_hsn_sac_code` as `product_rm_hsn_sac_code`,
	`child`.`product_material_hsn_sac_rate` as `product_rm_hsn_sac_rate`,
	`st`.`product_material_quantity` as `product_material_quantity`,
	`st`.`product_material_weight` as `product_material_weight`,
	`st`.`lead_time` as `lead_time`,
	coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `smv_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_quantity`,
	coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `smv_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_weight`,
	coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id`), 0) as `product_Rawmaterial_stock_quantity`,
	coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id`), 0) as `product_Rawmaterial_stock_weight`,
	`st`.`product_material_reserve_quantity` as `product_material_reserve_quantity`,
	`st`.`product_material_reserve_weight` as `product_material_reserve_weight`,
	`st`.`product_material_approved_quantity` as `product_material_approved_quantity`,
	`st`.`product_material_approved_weight` as `product_material_approved_weight`,
	`st`.`product_material_issue_quantity` as `product_material_issue_quantity`,
	`st`.`product_material_issue_weight` as `product_material_issue_weight`,
	`st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
	`st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
	`st`.`product_material_rejected_quantity` as `product_material_rejected_quantity`,
	`st`.`product_material_rejected_weight` as `product_material_rejected_weight`,
	`st`.`product_material_return_quantity` as `product_material_return_quantity`,
	`st`.`product_material_return_weight` as `product_material_return_weight`,
	`st`.`product_material_grn_accepted_quantity` as `product_material_grn_accepted_quantity`,
	`st`.`product_material_grn_accepted_weight` as `product_material_grn_accepted_weight`,
	`st`.`product_child_bom_quantity` as `product_child_bom_quantity`,
	`st`.`product_child_bom_weight` as `product_child_bom_weight`,
	`st`.`approval_remark` as `approval_remark`,
	`e1`.`employee_name` as `issued_by_name`,
	`st`.`issued_by_id` as `issued_by_id`,
	`st`.`issued_date` as `issued_date`,
	`st`.`indent_item_status` as `indent_item_status`,
	`st`.`po_item_status` as `po_item_status`,
	`st`.`grn_item_status` as `grn_item_status`,
	`st`.`issue_item_status` as `issue_item_status`,
	`v`.`indent_source` as `indent_source`,
	`v`.`company_name` as `company_name`,
	`v`.`company_branch_name` as `company_branch_name`,
	`v`.`financial_year` as `financial_year`,
	`child`.`product_type_group` as `product_type_group`,
	`child`.`product_material_type_short_name` as `indent_type_short_name`,
	`v`.`indent_type` as `indent_type`,
	`v`.`indent_status` as `indent_status`,
	`st`.`indent_no` as `field_name`,
	`st`.`indent_version` as `field_id`,
	case
		when `st`.`is_active` = 1 then 'Active'
		else 'In Active'
	end as `Active`,
	case
		when `st`.`is_delete` = 1 then 'Yes'
		else 'No'
	end as `Deleted`,
	`v`.`is_active` as `is_active`,
	`st`.`is_delete` as `is_delete`,
	`st`.`created_by` as `created_by`,
	`st`.`created_on` as `created_on`,
	`st`.`modified_by` as `modified_by`,
	`st`.`modified_on` as `modified_on`,
	`st`.`deleted_by` as `deleted_by`,
	`st`.`deleted_on` as `deleted_on`,
	`st`.`indent_details_id` as `indent_details_id`,
	`st`.`product_fg_id` as `product_fg_id`,
	`st`.`product_material_id` as `product_material_id`,
	`st`.`product_material_unit_id` as `product_material_unit_id`,
	`st`.`cost_center_id` as `cost_center_id`,
	`fm`.`cost_center_name` as `cost_center_name`,
	`v`.`indent_transaction_type` as `indent_transaction_type`,
	`v`.`indent_master_id` as `indent_master_id`,
	`v`.`indent_type_id` as `indent_type_id`,
	`v`.`customer_id` as `customer_id`,
	`v`.`department_id` as `department_id`,
	`v`.`sub_department_id` as `sub_department_id`,
	`v`.`indented_by_id` as `indented_by_id`,
	`v`.`company_id` as `company_id`,
	`v`.`company_branch_id` as `company_branch_id`,
	`child`.`product_type_id` as `product_type_id`,
	`child`.`product_material_hsn_sac_code_id` as `product_rm_hsn_sac_code_id`,
	`child`.`product_material_packing_id` as `product_rm_packing_id`,
	`child`.`product_material_category1_id` as `product_category1_id`,
	`child`.`product_material_category2_id` as `product_category2_id`,
	`child`.`product_material_category3_id` as `product_category3_id`,
	`child`.`product_material_category4_id` as `product_category4_id`,
	`child`.`product_material_category5_id` as `product_category5_id`,
	`child`.`product_material_make_id` as `product_make_id`,
	`child`.`product_material_type_id` as `product_material_type_id`,
	`child`.`product_material_grade_id` as `product_material_grade_id`
from
	(((((`st_indent_details` `st`
left join `stv_indent_master_summary` `v` on
	(`st`.`company_branch_id` = `v`.`company_branch_id`
		and `st`.`company_id` = `v`.`company_id`
		and `st`.`indent_no` = `v`.`indent_no`
		and `st`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `child` on
	(`child`.`product_material_id` = `st`.`product_material_id`))
left join `cm_employee` `e1` on
	(`e1`.`employee_id` = `st`.`issued_by_id`
		and `e1`.`company_id` = `st`.`company_id`))
left join `smv_product_rm_fg_sr` `parent` on
	(`parent`.`product_material_id` = `st`.`product_fg_id`))
left join `fm_cost_center` `fm` on
	(`fm`.`company_id` = `st`.`company_id`
		and `fm`.`cost_center_id` = `st`.`cost_center_id`))
where
	`st`.`is_delete` = 0;


-- stv_indent_master_summary source

create or replace
algorithm = UNDEFINED view `stv_indent_master_summary` as
select
    `st`.`indent_no` as `indent_no`,
    `st`.`indent_date` as `indent_date`,
    `st`.`indent_version` as `indent_version`,
    case
        `st`.`indent_source` when 'S' then 'SO Based'
        when 'O' then 'Direct'
        else 'Internal'
    end as `indent_source_desc`,
    `vp`.`department_name` as `department_name`,
    `sd`.`department_name` as `sub_department_name`,
    `e`.`employee_name` as `indented_by_name`,
    `e1`.`employee_name` as `approved_by_name`,
    `e2`.`employee_name` as `accepted_by_name`,
    `st`.`approved_date` as `approved_date`,
    case
        `st`.`indent_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Cenceled'
        else 'Pending'
    end as `indent_status_desc`,
    `c`.`customer_name` as `customer_name`,
    `c`.`state_name` as `customer_state_name`,
    `c`.`cust_branch_phone_no` as `cust_branch_phone_no`,
    `c`.`cust_branch_address1` as `cust_branch_address1`,
    `c`.`cust_branch_EmailId` as `cust_branch_EmailId`,
    `st`.`customer_order_no` as `customer_order_no`,
    `st`.`customer_order_Date` as `customer_order_date`,
    `st`.`expected_schedule_date` as `expected_schedule_date`,
    `fc`.`cost_center_name` as `cost_center_name`,
    `e`.`email_id1` as `indented_by_email`,
    `e1`.`email_id1` as `approved_by_email`,
    case
        `st`.`indent_transaction_type` when 'A' then 'BOM Based'
        else 'Manual'
    end as `indent_transaction_type_desc`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `st`.`remark` as `remark`,
    `st`.`indent_no` as `field_name`,
    `st`.`indent_version` as `field_id`,
    `st`.`is_active` as `is_active`,
    `st`.`is_delete` as `is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`deleted_on` as `deleted_on`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `st`.`financial_year` as `financial_year`,
    `st`.`indent_master_id` as `indent_master_id`,
    `st`.`indent_type_id` as `indent_type_id`,
    `st`.`customer_id` as `customer_id`,
    `c`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `c`.`cust_branch_pan_no` as `cust_branch_pan_no`,
    `st`.`department_id` as `department_id`,
    `st`.`sub_department_id` as `sub_department_id`,
    `st`.`indented_by_id` as `indented_by_id`,
    `st`.`approved_by_id` as `approved_by_id`,
    `st`.`accepted_by_id` as `accepted_by_id`,
    `st`.`cost_center_id` as `cost_center_id`,
    `st`.`indent_status` as `indent_status`,
    `st`.`po_status` as `po_status`,
    `st`.`grn_status` as `grn_status`,
    `st`.`issue_status` as `issue_status`,
    `st`.`company_id` as `company_id`,
    `st`.`company_branch_id` as `company_branch_id`,
    `st`.`indent_transaction_type` as `indent_transaction_type`,
    `st`.`Indent_type` as `indent_type`,
    `p`.`product_type_short_name` as `indent_type_short_name`,
    `st`.`indent_source` as `indent_source`
from
    (((((((((`st_indent_master` `st`
left join `cmv_company_summary` `v` on
    (`v`.`company_branch_id` = `st`.`company_branch_id`
        and `v`.`company_id` = `st`.`company_id`))
left join `cmv_customer` `c` on
    (`c`.`customer_id` = `st`.`customer_id`))
left join `cm_department` `vp` on
    (`vp`.`department_id` = `st`.`department_id`))
left join `cm_department` `sd` on
    (`sd`.`department_id` = `st`.`sub_department_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `st`.`indented_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `st`.`approved_by_id`))
left join `cm_employee` `e2` on
    (`e2`.`employee_id` = `st`.`accepted_by_id`))
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `st`.`indent_type_id`))
left join `fmv_cost_center` `fc` on
    (`fc`.`cost_center_id` = `st`.`cost_center_id`
        and `fc`.`company_id` = `st`.`company_id`))
where
    `st`.`is_delete` = 0
order by
    `st`.`indent_no`,
    `st`.`indent_version`;

-- stv_indent_master_summary_rpt source

create or replace
algorithm = UNDEFINED view `stv_indent_master_summary_rpt` as
select
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:C:stv_indent_master_summary:F') as `indent_no`,
    concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') as `indent_date`,
    concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') as `indent_version`,
    concat(ifnull(`v`.`indent_type`, ''), ':Indent Type:Y:C:smv_product_type:F') as `indent_type`,
    concat(ifnull(`v`.`indent_status_desc`, ''), ':Indent Status:Y:H:(Pending,Approved,Rejected,Completed,Cenceled)') as `indent_status_desc`,
    concat(ifnull(`v`.`indent_source_desc`, ''), ':Indent Source:Y:H:(SO Based,Direct,Internal)') as `indent_source_desc`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') as `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer_summary:F') as `customer_name`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:C:stv_indent_master_summary:O') as `customer_order_no`,
    concat(ifnull(`v`.`customer_order_date`, ''), ':Customer Order Date:Y:D:') as `customer_order_date`,
    concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') as `expected_schedule_date`,
    concat(ifnull(`v`.`indented_by_name`, ''), ':Indent By:Y:C:cmv_employee_list:F') as `indented_by_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Indent Approved By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`indent_source`, ''), ':Indent Source:O:N:') as `indent_source`,
    concat(ifnull(`v`.`indent_status`, ''), ':Indent Status:O:N:') as `indent_status`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`indent_master_id`, ''), ':Indent Master Id:O:N:') as `indent_master_id`,
    concat(ifnull(`v`.`indent_type_id`, ''), ':Intdent Type Id:N:N:') as `indent_type_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') as `customer_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
    concat(ifnull(`v`.`indented_by_id`, ''), ':Indeneted By Id:N:N:') as `indented_by_id`,
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:N:N:') as `field_name`,
    concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:N:N:') as `field_id`
from
    `stv_indent_master_summary` `v`
limit 1;


-- stv_indent_details_rpt source

create or replace
algorithm = UNDEFINED view `stv_indent_details_rpt` as
select
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:C:stv_indent_master_summary:F') as `indent_no`,
    concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') as `indent_date`,
    concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') as `indent_version`,
    concat(ifnull(`v`.`indent_type`, ''), ':Indent Type:Y:C:smv_product_type:F') as `indent_type`,
    concat(ifnull(`v`.`indent_item_status_desc`, ''), ':Indent Item Status:Y:H:(Pending,Approved,Rejected,Completed,Cancel)') as `indent_item_status_desc`,
    concat(ifnull(`v`.`department_name`, ''), ':Department:Y:C:cmv_department:F') as `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
    concat(ifnull(`v`.`product_rm_name`, ''), ':Material name:Y:T') as `product_rm_name`,
    concat(ifnull(`v`.`product_rm_stock_unit_name`, ''), ':Material Stock Unit Name:O:N:') as `product_rm_stock_unit_name`,
    concat(ifnull(`v`.`product_material_quantity`, ''), ':Material Quantity:O:N:') as `product_material_quantity`,
    concat(ifnull(`v`.`product_material_weight`, ''), ':Material Weight:O:N:') as `product_material_weight`,
    concat(ifnull(`v`.`product_material_stock_quantity`, ''), ':Material Stock Quantity:O:N:') as `product_material_stock_quantity`,
    concat(ifnull(`v`.`product_material_stock_weight`, ''), ':Material Stock Weight:O:N:') as `product_material_stock_weight`,
    concat(ifnull(`v`.`product_material_reserve_quantity`, ''), ':Material Reserve Quantity:O:N:') as `product_material_reserve_quantity`,
    concat(ifnull(`v`.`product_material_reserve_weight`, ''), ':Material Reserve Weight:O:N:') as `product_material_reserve_weight`,
    concat(ifnull(`v`.`product_material_approved_quantity`, ''), ':Material Approved Quantity:O:N:') as `product_material_approved_quantity`,
    concat(ifnull(`v`.`product_material_approved_weight`, ''), ':Material Approved Weight:O:N:') as `product_material_approved_weight`,
    concat(ifnull(`v`.`product_material_rejected_quantity`, ''), ':Material Rejected Quantity:O:N:') as `product_material_rejected_quantity`,
    concat(ifnull(`v`.`product_material_rejected_weight`, ''), ':Material Rejected Weight:O:N:') as `product_material_rejected_weight`,
    concat(ifnull(`v`.`product_material_return_quantity`, ''), ':Material Return Quantity:O:N:') as `product_material_return_quantity`,
    concat(ifnull(`v`.`product_material_return_weight`, ''), ':Material Return Weight:O:N:') as `product_material_return_weight`,
    concat(ifnull(`v`.`product_material_receipt_quantity`, ''), ':Material Receipt Quantity:O:N:') as `product_material_receipt_quantity`,
    concat(ifnull(`v`.`product_material_receipt_weight`, ''), ':Material Receipt Weight:O:N:') as `product_material_receipt_weight`,
    concat(ifnull(`v`.`product_material_issue_quantity`, ''), ':Material Issue Quantity:O:N:') as `product_material_issue_quantity`,
    concat(ifnull(`v`.`product_material_issue_weight`, ''), ':Material Issue Weight:O:N:') as `product_material_issue_weight`,
    concat(ifnull(`v`.`product_material_grn_accepted_quantity`, ''), ':GRN Accepted Quantity:O:N:') as `product_material_grn_accepted_quantity`,
    concat(ifnull(`v`.`product_material_grn_accepted_weight`, ''), ':GRN Accepted Weight:O:N:') as `product_material_grn_accepted_weight`,
    concat(ifnull(`v`.`product_child_bom_quantity`, ''), ':Product Child Bom Quantity:O:N:') as `product_child_bom_quantity`,
    concat(ifnull(`v`.`product_child_bom_weight`, ''), ':Product Child Bom Weight:O:N:') as `product_child_bom_weight`,
    concat(ifnull(`v`.`product_parent_fg_name`, ''), ':Parent Finish Goods Name:O:N:') as `product_parent_fg_name`,
    concat(ifnull(`v`.`product_fg_material_quantity`, ''), ':Fg Material Quantity:O:N:') as `product_fg_material_quantity`,
    concat(ifnull(`v`.`product_fg_material_weight`, ''), ':Fg Material Weight:O:N:') as `product_fg_material_weight`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer_summary:F') as `customer_name`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:C:stv_indent_master_summary:O') as `customer_order_no`,
    concat(ifnull(`v`.`customer_order_date`, ''), ':Customer Order Date:Y:D:') as `customer_order_date`,
    concat(ifnull(`v`.`indented_by_name`, ''), ':Indent By:Y:C:cmv_employee_list:F') as `indented_by_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Indent Approved By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(ifnull(`v`.`product_rm_tech_spect`, ''), ':Material Tech spec:O:N:') as `product_rm_tech_spect`,
    concat(ifnull(`v`.`product_rm_drawing_no`, ''), ':Material Drawing No:O:N:') as `product_rm_drawing_no`,
    concat(ifnull(`v`.`product_rm_oem_part_code`, ''), ':Material Oem Part Code:O:N:') as `product_rm_oem_part_code`,
    concat(ifnull(`v`.`product_rm_our_part_code`, ''), ':Material Oem Part Code:O:N:') as `product_rm_our_part_code`,
    concat(ifnull(`v`.`product_category1_name`, ''), ':Product Category1 Name:O:N:') as `product_category1_name`,
    concat(ifnull(`v`.`product_category2_name`, ''), ':Product Category2 Name:O:N:') as `product_category2_name`,
    concat(ifnull(`v`.`product_category3_name`, ''), ':Product Category3 Name:O:N:') as `product_category3_name`,
    concat(ifnull(`v`.`product_category4_name`, ''), ':Product Category4 Name:O:N:') as `product_category4_name`,
    concat(ifnull(`v`.`product_category5_name`, ''), ':Product Category5 Name:O:N:') as `product_category5_name`,
    concat(ifnull(`v`.`product_make_name`, ''), ':Product Make Name:O:N:') as `product_make_name`,
    concat(ifnull(`v`.`product_material_type_name`, ''), ':Material Type Name:O:N:') as `product_material_type_name`,
    concat(ifnull(`v`.`product_material_grade_name`, ''), ':Material Grade Name:O:N:') as `product_material_grade_name`,
    concat(ifnull(`v`.`lead_time`, ''), ':Lead Time:O:N:') as `lead_time`,
    concat(ifnull(`v`.`so_sr_no`, ''), ':So Sr No:O:N:') as `so_sr_no`,
    concat(ifnull(`v`.`issued_by_name`, ''), ':Issued By:Y:C:cmv_employee_list:F') as `issued_by_name`,
    concat(ifnull(`v`.`issued_date`, ''), ':Issued Date:Y:D:') as `issued_date`,
    concat(ifnull(`v`.`indent_item_status`, ''), ':Indent Item Status:O:N:') as `indent_item_status`,
    concat(ifnull(`v`.`indent_status`, ''), ':Indent Status:O:N:') as `indent_status`,
    concat(ifnull(`v`.`indent_source`, ''), ':Indent Source:O:N:') as `indent_source`,
    concat(ifnull(`v`.`remark`, ''), 'remark:O:N:') as `remark`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`product_fg_id`, ''), ':Product Fg Id:N:N:') as `product_fg_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Material Unit Id:N:N:') as `product_material_unit_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`product_type_id`, ''), ':Product Type Id:N:N:') as `product_type_id`,
    concat(ifnull(`v`.`product_category1_id`, ''), ':Product Category1 Id:N:N:') as `product_category1_id`,
    concat(ifnull(`v`.`product_category2_id`, ''), ':Product Category2 Id:N:N:') as `product_category2_id`,
    concat(ifnull(`v`.`product_category3_id`, ''), ':Product Category3 Id:N:N:') as `product_category3_id`,
    concat(ifnull(`v`.`product_category4_id`, ''), ':Product Category4 Id:N:N:') as `product_category4_id`,
    concat(ifnull(`v`.`product_category5_id`, ''), ':Product Category5 Id:N:N:') as `product_category5_id`,
    concat(ifnull(`v`.`product_make_id`, ''), ':Product Make Id:N:N:') as `product_make_id`,
    concat(ifnull(`v`.`product_material_type_id`, ''), ':Product Material Type Id:N:N:') as `product_material_type_id`,
    concat(ifnull(`v`.`product_material_grade_id`, ''), ':Product Material Grade Id:N:N:') as `product_material_grade_id`,
    concat(ifnull(`v`.`indent_master_id`, ''), ':Indent Master Id:O:N:') as `indent_master_id`,
    concat(ifnull(`v`.`indent_details_id`, ''), ':Indent Details Id:N:N:') as `indent_details_id`,
    concat(ifnull(`v`.`indent_type_id`, ''), ':Indent Type Id:N:N:') as `indent_type_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') as `customer_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
    concat(ifnull(`v`.`indented_by_id`, ''), ':Indented By Id:N:N:') as `indented_by_id`,
    concat(ifnull(`v`.`issued_by_id`, ''), ':Issued By Id:N:N:') as `issued_by_id`
from
    `stv_indent_details` `v`
limit 1;


ALTER TABLE pt_purchase_order_master ADD grn_status varchar(3) DEFAULT 'P' NULL COMMENT 'GRN status for the purchase order master';
ALTER TABLE pt_purchase_order_master CHANGE grn_status grn_status varchar(3) DEFAULT 'P' NULL COMMENT 'GRN status for the purchase order master' AFTER deduction4_percent;
ALTER TABLE pt_purchase_order_master CHANGE grn_status grn_status varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'P' NULL COMMENT 'GRN status for the purchase order master' AFTER purchase_order_status;

ALTER TABLE pt_purchase_order_details ADD grn_item_status varchar(3) DEFAULT 'P' NULL COMMENT 'grn item status for the po item status';
ALTER TABLE pt_purchase_order_details  CHANGE grn_item_status grn_item_status varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'P' NULL COMMENT 'GRN item status for the purchase order item details' AFTER purchase_order_item_status;


-- ptv_purchase_order_master_summary source

create or replace
algorithm = UNDEFINED view `ptv_purchase_order_master_summary` as
select
    case
        `pt`.`purchase_order_creation_type` when 'M' then 'Manual'
        when 'R' then 'Reorder Based'
        else 'Auto'
    end as `purchase_order_creation_type_desc`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
    `pt`.`purchase_order_date` as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    case
        when `pt`.`purchase_order_status` = 'A' then 'Approved'
        when `pt`.`purchase_order_status` = 'R' then 'Rejected'
        when `pt`.`purchase_order_status` = 'X' then 'Canceled'
        else 'Pending'
    end as `purchase_order_status_desc`,
    case
        `pt`.`purchase_order_life` when 'C' then 'Close'
        else 'Open'
    end as `purchase_order_life_desc`,
    case
        `pt`.`purchase_order_mail_sent_status` when 'S' then 'Email Sent'
        when 'F' then 'Email Failed'
        else 'Email Pending'
    end as `purchase_order_mail_sent_status_desc`,
    `s`.`supplier_name` as `supplier_name`,
    `st`.`state_name` as `state_name`,
    `s`.`supp_branch_phone_no` as `supp_branch_phone_no`,
    `s`.`supp_branch_EmailId` as `supp_branch_EmailId`,
    `cb`.`company_branch_name` as `expected_branch_name`,
    `st1`.`state_name` as `expected_branch_state_name`,
    `e`.`employee_name` as `approved_by`,
    `pt`.`approved_date` as `approved_date`,
    `pt`.`expected_schedule_date` as `expected_schedule_date`,
    `pt`.`basic_total` as `basic_total`,
    `pt`.`transport_amount` as `transport_amount`,
    `pt`.`freight_amount` as `freight_amount`,
    `pt`.`is_freight_taxable` as `is_freight_taxable`,
    `pt`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `pt`.`packing_amount` as `packing_amount`,
    `pt`.`po_discount_percent` as `po_discount_percent`,
    `pt`.`po_discount_amount` as `po_discount_amount`,
    `pt`.`other_amount` as `other_amount`,
    `pt`.`taxable_total` as `taxable_total`,
    `pt`.`cgst_total` as `cgst_total`,
    `pt`.`sgst_total` as `sgst_total`,
    `pt`.`igst_total` as `igst_total`,
    `pt`.`roundoff` as `roundoff`,
    `pt`.`grand_total` as `grand_total`,
    `a`.`agent_name` as `agent_name`,
    `pt`.`agent_percent` as `agent_percent`,
    case
        `pt`.`agent_paid_status` when 'A' then 'Approved'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Pending'
    end as `agent_paid_status_desc`,
    case
        convert(`pt`.`purchase_order_acceptance_status`
            using utf8mb4)
        when 'A' then 'Approved'
        when 'X' then 'Canceled'
        else 'Pending'
    end as `purchase_order_acceptance_status_desc`,
    `pt`.`purchase_order_acceptance_status` as `purchase_order_acceptance_status`,
    `pt`.`purchase_order_mail_sent_status` as `purchase_order_mail_sent_status`,
    `pt`.`status_remark` as `status_remark`,
    `pt`.`other_terms_conditions` as `other_terms_conditions`,
    `s`.`supplier_type` as `supplier_type`,
    `s`.`supplier_code` as `supplier_code`,
    `s`.`supplier_short_name` as `supplier_short_name`,
    `s`.`supp_branch_address1` as `supp_branch_address1`,
    `s`.`supp_branch_pincode` as `supp_branch_pincode`,
    `ct`.`city_name` as `city_name`,
    `s`.`district_name` as `district_name`,
    `s`.`country_name` as `country_name`,
    `hsn`.`hsn_sac_type` as `hsn_sac_type`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `hsn`.`hsn_sac_description` as `hsn_sac_description`,
    `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
    `cb`.`branch_short_name` as `expected_branch_short_name`,
    `cb`.`branch_address1` as `expected_branch_address1`,
    `cb`.`branch_pincode` as `expected_branch_pincode`,
    `ct1`.`city_name` as `expected_branch_city_name`,
    `cb`.`district_name` as `expected_branch_district_name`,
    `cb`.`country_name` as `expected_branch_country_name`,
    `cb`.`branch_phone_no` as `expected_branch_phone_no`,
    `cb`.`branch_EmailId` as `expected_branch_EmailId`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `pt`.`financial_year` as `financial_year`,
    `pt`.`purchase_process_entry` as `purchase_process_entry`,
    `pt`.`purchase_order_type` as `purchase_order_type`,
    `p`.`product_type_short_name` as `product_type_short_name`,
    `pt`.`purchase_order_life` as `purchase_order_life`,
    `pt`.`purchase_order_status` as `purchase_order_status`,
    `pt`.`grn_status` as `grn_status`,
    case
        `pt`.`purchase_process_entry` when 'M' then 'Manual'
        else 'Auto'
    end as `purchase_process_entry_desc`,
    `pt`.`purchase_order_creation_type` as `purchase_order_creation_type`,
    `pt`.`gate_pass_no` as `gate_pass_no`,
    `pt`.`gate_pass_date` as `gate_pass_date`,
    `pt`.`vehicale_no` as `vehicale_no`,
    `pt`.`vehicale_type` as `vehicale_type`,
    `pt`.`gross_weight` as `gross_weight`,
    `pt`.`tare_weight` as `tare_weight`,
    `pt`.`net_weight` as `net_weight`,
    `pt`.`variation_weight` as `variation_weight`,
    `pt`.`deduction1_name` as `deduction1_name`,
    `pt`.`deduction1_percent` as `deduction1_percent`,
    `pt`.`deduction1_amount` as `deduction1_amount`,
    `pt`.`deduction2_name` as `deduction2_name`,
    `pt`.`deduction2_percent` as `deduction2_percent`,
    `pt`.`deduction2_amount` as `deduction2_amount`,
    `pt`.`deduction3_name` as `deduction3_name`,
    `pt`.`deduction3_percent` as `deduction3_percent`,
    `pt`.`deduction3_amount` as `deduction3_amount`,
    `pt`.`deduction4_name` as `deduction4_name`,
    `pt`.`deduction4_percent` as `deduction4_percent`,
    `pt`.`deduction4_amount` as `deduction4_amount`,
    `pt`.`deduction5_name` as `deduction5_name`,
    `pt`.`deduction5_percent` as `deduction5_percent`,
    `pt`.`deduction5_amount` as `deduction5_amount`,
    `e1`.`employee_name` as `grader_by_name`,
    `pt`.`remark` as `remark`,
    case
        when `pt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `pt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `pt`.`is_active` as `is_active`,
    `pt`.`is_delete` as `is_delete`,
    `pt`.`created_by` as `created_by`,
    `pt`.`created_on` as `created_on`,
    `pt`.`modified_by` as `modified_by`,
    `pt`.`modified_on` as `modified_on`,
    `pt`.`deleted_by` as `deleted_by`,
    `pt`.`deleted_on` as `deleted_on`,
    `pt`.`purchase_order_master_transaction_id` as `purchase_order_master_transaction_id`,
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`purchase_order_type_id` as `purchase_order_type_id`,
    `pt`.`supplier_id` as `supplier_id`,
    `pt`.`supplier_state_id` as `supplier_state_id`,
    `pt`.`supplier_city_id` as `supplier_city_id`,
    `pt`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `pt`.`expected_branch_id` as `expected_branch_id`,
    `pt`.`expected_branch_state_id` as `expected_branch_state_id`,
    `pt`.`expected_branch_city_id` as `expected_branch_city_id`,
    `pt`.`approved_by_id` as `approved_by_id`,
    `pt`.`agent_id` as `agent_id`,
    `cc`.`supplier_name` as `supplier_cosnignee_name`,
    `cc`.`supp_branch_address1` as `supp_consignee_address1`,
    `cc`.`supp_branch_address2` as `supp_consignee_address2`,
    `cc`.`supp_branch_pincode` as `supp_consignee_pincode`,
    `cc`.`city_name` as `supp_consignee_city_name`,
    `cc`.`district_name` as `supp_consignee_district_name`,
    `cc`.`state_name` as `supp_consignee_state_name`,
    `pt`.`consignee_id` as `consignee_id`,
    `pt`.`consignee_state_id` as `consignee_state_id`,
    `pt`.`consignee_city_id` as `consignee_city_id`,
    `pt`.`grader_by_id` as `grader_by_id`,
    `pt`.`deduction5_id` as `deduction5_id`,
    `pt`.`deduction4_id` as `deduction4_id`,
    `pt`.`deduction3_id` as `deduction3_id`,
    `pt`.`deduction2_id` as `deduction2_id`,
    `pt`.`deduction1_id` as `deduction1_id`,
    `pt`.`agent_paid_status` as `agent_paid_status`
from
    (((((((((((((`pt_purchase_order_master` `pt`
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `pt`.`company_branch_id`
        and `v`.`company_id` = `pt`.`company_id`))
left join `cmv_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`company_branch_id`
        and `cb`.`company_id` = `pt`.`company_id`))
left join `cmv_supplier` `s` on
    (`s`.`supplier_id` = `pt`.`supplier_id`))
left join `cmv_employee` `e` on
    (`e`.`employee_id` = `pt`.`approved_by_id`))
left join `cmv_employee` `e1` on
    (`e1`.`employee_id` = `pt`.`grader_by_id`))
left join `cm_agent` `a` on
    (`a`.`agent_id` = `pt`.`agent_id`
        and `a`.`company_id` = `pt`.`company_id`))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `pt`.`supplier_city_id`))
left join `cm_city` `ct1` on
    (`ct1`.`city_id` = `pt`.`expected_branch_city_id`))
left join `cm_state` `st` on
    (`st`.`state_id` = `pt`.`supplier_state_id`))
left join `cm_state` `st1` on
    (`st1`.`state_id` = `pt`.`expected_branch_state_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`freight_hsn_code_id`))
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `pt`.`purchase_order_type_id`))
left join `cmv_supplier_branch` `cc` on
    (`cc`.`supplier_id` = `pt`.`consignee_id`))
where
    `pt`.`is_delete` = 0;


-- ptv_purchase_order_details source

create or replace
algorithm = UNDEFINED view `ptv_purchase_order_details` as
select
    `pt`.`purchase_order_type` as `purchase_order_type`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
    `pt`.`purchase_order_date` as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    `pt`.`indent_no` as `indent_no`,
    `pt`.`indent_date` as `indent_date`,
    `pt`.`indent_version` as `indent_version`,
    case
        `pom`.`purchase_order_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'X' then 'Canceled'
        else 'Pending'
    end as `purchase_order_status_desc`,
    `vp`.`department_name` as `department_name`,
    `sdp`.`department_name` as `sub_department_name`,
    case
        `pt`.`purchase_order_item_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        when 'B' then 'Bill Booked'
        else 'Pending'
    end as `purchase_order_item_status_desc`,
    case
        `pom`.`purchase_order_mail_sent_status` when 'S' then 'Email Sent'
        when 'F' then 'Email Failed'
        else 'Email Pending'
    end as `purchase_order_mail_sent_status_desc`,
    `pom`.`supplier_name` as `supplier_name`,
    `pom`.`state_name` as `supplier_state_name`,
    `c`.`customer_name` as `customer_name`,
    `e1`.`employee_name` as `indented_by_name`,
    `e`.`employee_name` as `approved_by_name`,
    `pt`.`sr_no` as `sr_no`,
    `pt`.`so_sr_no` as `so_sr_no`,
    `p`.`product_type_name` as `product_type_name`,
    `p`.`product_type_short_name` as `product_type_short_name`,
    `p`.`product_type_group` as `product_type_group`,
    `rmfgsr`.`product_material_code` as `product_material_code`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `pt`.`product_material_print_name` as `product_material_print_name`,
    `pt`.`product_material_tech_spect` as `product_material_tech_spect`,
    `pt`.`lead_time` as `lead_time`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id` and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `available_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id` and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `available_stock_weight`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id`), 0) as `available_rawmaterial_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id`), 0) as `available_rawmaterial_stock_weight`,
    `rmfgsr`.`product_material_std_weight` as `product_material_std_weight`,
    `rmfgsr`.`product_material_stock_unit_name` as `product_material_stock_unit_name`,
    `pp`.`product_packing_name` as `product_packing_name`,
    `pp`.`quantity_per_packing` as `quantity_per_packing`,
    `hsn`.`hsn_sac_type` as `hsn_sac_type`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `hsn`.`hsn_sac_description` as `hsn_sac_description`,
    `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
    `rmfgsr`.`product_type_group` as `product_material_type_group`,
    `rmfgsr`.`product_material_make_name` as `product_make_name`,
    `rmfgsr`.`product_material_category1_name` as `product_category1_name`,
    `rmfgsr`.`product_material_category2_name` as `product_category2_name`,
    `rmfgsr`.`product_material_category3_name` as `product_category3_name`,
    `rmfgsr`.`product_material_category4_name` as `product_category4_name`,
    `rmfgsr`.`product_material_category5_name` as `product_category5_name`,
    `rmfgsr`.`product_material_type_name` as `product_material_type_name`,
    `rmfgsr`.`product_material_grade_name` as `product_material_grade_name`,
    `rmfgsr`.`product_material_shape_name` as `product_material_shape_name`,
    `rmfgsr`.`product_material_oem_part_code` as `product_material_oem_part_code`,
    `rmfgsr`.`product_material_our_part_code` as `product_material_our_part_code`,
    `rmfgsr`.`product_material_drawing_no` as `product_material_drawing_no`,
    `rmfgsr`.`product_material_hsn_sac_code` as `product_material_hsn_sac_code`,
    `rmfgsr`.`godown_name` as `godown_name`,
    `rmfgsr`.`godown_short_name` as `godown_short_name`,
    `rmfgsr`.`godown_area` as `godown_area`,
    `rmfgsr`.`godown_section_name` as `godown_section_name`,
    `rmfgsr`.`godown_section_short_name` as `godown_section_short_name`,
    `rmfgsr`.`godown_section_area` as `godown_section_area`,
    `rmfgsr`.`godown_section_beans_name` as `godown_section_beans_name`,
    `rmfgsr`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    `rmfgsr`.`godown_section_beans_area` as `godown_section_beans_area`,
    `pt`.`product_material_po_quantity_qunital` as `product_material_po_quantity_qunital`,
    `pt`.`product_material_po_quantity` as `product_material_po_quantity`,
    `pt`.`product_material_conversion_factor` as `product_material_conversion_factor`,
    `pt`.`product_material_po_weight` as `product_material_po_weight`,
    `pt`.`product_material_po_approved_quantity` as `product_material_po_approved_quantity`,
    `pt`.`product_material_po_approved_weight` as `product_material_po_approved_weight`,
    `pt`.`product_material_po_rejected_quantity` as `product_material_po_rejected_quantity`,
    `pt`.`product_material_po_rejected_weight` as `product_material_po_rejected_weight`,
    `pt`.`customer_id` as `customer_id`,
    `pt`.`customer_order_no` as `customer_order_no`,
    `pt`.`customer_order_Date` as `customer_order_Date`,
    `pt`.`pree_closed_quantity` as `pree_closed_quantity`,
    `pt`.`pree_closed_weight` as `pree_closed_weight`,
    `pt`.`purchase_return_quantity` as `purchase_return_quantity`,
    `pt`.`purchase_return_weight` as `purchase_return_weight`,
    `pt`.`material_rate` as `material_rate`,
    `pt`.`material_basic_amount` as `material_basic_amount`,
    `pt`.`material_discount_percent` as `material_discount_percent`,
    `pt`.`material_discount_amount` as `material_discount_amount`,
    `pt`.`material_taxable_amount` as `material_taxable_amount`,
    `pt`.`material_cgst_percent` as `material_cgst_percent`,
    `pt`.`material_cgst_total` as `material_cgst_total`,
    `pt`.`material_sgst_percent` as `material_sgst_percent`,
    `pt`.`material_sgst_total` as `material_sgst_total`,
    `pt`.`material_igst_percent` as `material_igst_percent`,
    `pt`.`material_igst_total` as `material_igst_total`,
    `pt`.`material_total_amount` as `material_total_amount`,
    `pt`.`material_schedule_date` as `material_schedule_date`,
    `pt`.`material_freight_amount` as `material_freight_amount`,
    `pt`.`pending_quantity` as `pending_quantity`,
    `pt`.`pending_weight` as `pending_weight`,
    `pt`.`excess_quantity` as `excess_quantity`,
    `pt`.`excess_weight` as `excess_weight`,
    `pt`.`production_issue_quantity` as `production_issue_quantity`,
    `pt`.`production_issue_weight` as `production_issue_weight`,
    `pt`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `pt`.`production_issue_return_weight` as `production_issue_return_weight`,
    `pt`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `pt`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `pt`.`assembly_production_issue_quantity` as `assembly_production_issue_quantity`,
    `pt`.`assembly_production_issue_weight` as `assembly_production_issue_weight`,
    `pt`.`sales_quantity` as `sales_quantity`,
    `pt`.`sales_weight` as `sales_weight`,
    `pt`.`sales_return_quantity` as `sales_return_quantity`,
    `pt`.`sales_return_weight` as `sales_return_weight`,
    `pt`.`sales_rejection_quantity` as `sales_rejection_quantity`,
    `pt`.`sales_rejection_weight` as `sales_rejection_weight`,
    `pt`.`transfer_issue_quantity` as `transfer_issue_quantity`,
    `pt`.`transfer_issue_weight` as `transfer_issue_weight`,
    `pt`.`transfer_receipt_quantity` as `transfer_receipt_quantity`,
    `pt`.`transfer_receipt_weight` as `transfer_receipt_weight`,
    `pt`.`outsources_out_quantity` as `outsources_out_quantity`,
    `pt`.`outsources_out_weight` as `outsources_out_weight`,
    `pt`.`outsources_in_quantity` as `outsources_in_quantity`,
    `pt`.`outsources_in_weight` as `outsources_in_weight`,
    `pt`.`outsources_rejection_quantity` as `outsources_rejection_quantity`,
    `pt`.`outsources_rejection_weight` as `outsources_rejection_weight`,
    `pt`.`loan_receipt_quantity` as `loan_receipt_quantity`,
    `pt`.`loan_receipt_weight` as `loan_receipt_weight`,
    `pt`.`loan_issue_quantity` as `loan_issue_quantity`,
    `pt`.`loan_issue_weight` as `loan_issue_weight`,
    `pt`.`cancel_quantity` as `cancel_quantity`,
    `pt`.`cancel_weight` as `cancel_weight`,
    `pt`.`difference_quantity` as `difference_quantity`,
    `pt`.`difference_weight` as `difference_weight`,
    `pt`.`material_po_approval_remark` as `material_po_approval_remark`,
    `pom`.`purchase_order_status` as `purchase_order_status`,
    `pt`.`purchase_order_item_status` as `purchase_order_item_status`,
    `pt`.`grn_item_status` as `grn_item_status`,
    `pom`.`purchase_order_mail_sent_status` as `purchase_order_mail_sent_status`,
    `pt`.`approved_date` as `approved_date`,
    `pt`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `v`.`company_cell_no` as `company_cell_no`,
    `v`.`company_phone_no` as `company_phone_no`,
    `v`.`company_EmailId` as `company_EmailId`,
    `v`.`company_website` as `company_website`,
    `v`.`company_gst_no` as `company_gst_no`,
    `v`.`company_pan_no` as `company_pan_no`,
    `v`.`company_state` as `company_state`,
    `v`.`company_pincode` as `company_pincode`,
    `pt`.`financial_year` as `financial_year`,
    `pom`.`city_name` as `city_name`,
    `pom`.`district_name` as `district_name`,
    `pom`.`country_name` as `country_name`,
    case
        `pt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `pt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `pt`.`is_active` as `is_active`,
    `pt`.`is_delete` as `is_delete`,
    `pt`.`created_by` as `created_by`,
    `pt`.`created_on` as `created_on`,
    `pt`.`modified_by` as `modified_by`,
    `pt`.`modified_on` as `modified_on`,
    `pt`.`deleted_by` as `deleted_by`,
    `pt`.`deleted_on` as `deleted_on`,
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`purchase_order_type_id` as `purchase_order_type_id`,
    `pt`.`purchase_order_master_transaction_id` as `purchase_order_master_transaction_id`,
    `pt`.`purchase_order_details_transaction_id` as `purchase_order_details_transaction_id`,
    `pom`.`supplier_id` as `supplier_id`,
    `pom`.`supplier_state_id` as `supplier_state_id`,
    `pom`.`supplier_city_id` as `supplier_city_id`,
    `pom`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `pom`.`expected_branch_id` as `expected_branch_id`,
    `pom`.`expected_branch_state_id` as `expected_branch_state_id`,
    `pom`.`expected_branch_city_id` as `expected_branch_city_id`,
    `pt`.`cost_center_id` as `cost_center_id`,
    `rmfgsr`.`product_material_type_id` as `product_material_type_id`,
    `rmfgsr`.`product_material_grade_id` as `product_material_grade_id`,
    `rmfgsr`.`product_material_shape_id` as `product_material_shape_id`,
    `rmfgsr`.`product_material_stock_unit_id` as `product_material_stock_unit_id`,
    `rmfgsr`.`product_material_category1_id` as `product_category1_id`,
    `rmfgsr`.`product_material_category2_id` as `product_category2_id`,
    `rmfgsr`.`product_material_category3_id` as `product_category3_id`,
    `rmfgsr`.`product_material_category4_id` as `product_category4_id`,
    `rmfgsr`.`product_material_category5_id` as `product_category5_id`,
    `rmfgsr`.`godown_id` as `godown_id`,
    `rmfgsr`.`godown_section_id` as `godown_section_id`,
    `rmfgsr`.`godown_section_beans_id` as `godown_section_beans_id`,
    `pt`.`product_material_id` as `product_material_id`,
    `pt`.`product_material_unit_id` as `product_material_unit_id`,
    `pt`.`product_material_packing_id` as `product_material_packing_id`,
    `pt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `pt`.`department_id` as `department_id`,
    `pt`.`sub_department_id` as `sub_department_id`,
    `pt`.`indented_by_id` as `indented_by_id`,
    `pt`.`approved_by_id` as `approved_by_id`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `prev_grn_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `prev_grn_weight`
from
    (((((((((((`pt_purchase_order_details` `pt`
left join `ptv_purchase_order_master_summary` `pom` on
    (`pom`.`company_branch_id` = `pt`.`company_branch_id`
        and `pom`.`company_id` = `pt`.`company_id`
        and `pom`.`purchase_order_no` = `pt`.`purchase_order_no`
        and `pom`.`purchase_order_version` = `pt`.`purchase_order_version`
        and `pom`.`is_delete` = 0))
left join `cmv_company_summary` `v` on
    (`v`.`company_branch_id` = `pt`.`company_branch_id`
        and `v`.`company_id` = `pt`.`company_id`))
left join `smv_product_type` `p` on
    (`p`.`product_type_id` = `pt`.`purchase_order_type_id`))
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `pt`.`product_material_id`))
left join `smv_product_packing` `pp` on
    (`pp`.`product_packing_id` = `pt`.`product_material_packing_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`product_material_hsn_code_id`))
left join `cmv_department` `vp` on
    (`vp`.`department_id` = `pt`.`department_id`))
left join `cmv_employee` `e` on
    (`e`.`employee_id` = `pt`.`approved_by_id`))
left join `cmv_employee` `e1` on
    (`e1`.`employee_id` = `pt`.`indented_by_id`
        and `e1`.`company_id` = `pt`.`company_id`))
left join `cmv_customer` `c` on
    (`c`.`customer_id` = `pt`.`customer_id`))
left join `cmv_department` `sdp` on
    (`sdp`.`department_id` = `pt`.`sub_department_id`))
where
    `pt`.`is_delete` = 0;

-- stv_indent_material_issue_details source

create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_details` as
select
    `st`.`issue_no` as `issue_no`,
    `st`.`issue_date` as `issue_date`,
    `st`.`issue_version` as `issue_version`,
    `v`.`department_name` as `department_name`,
    `v`.`sub_department_name` as `sub_department_name`,
    case
        `st`.`issue_item_status` when 'MI' then 'Material Issue'
        when 'C' then 'Completed'
        when 'I' then 'Partial Issue'
        when 'AC' then 'Accepted'
        else 'Pending'
    end as `issue_item_status_desc`,
    `e`.`employee_name` as `indented_by_name`,
    `v`.`customer_name` as `customer_name`,
    `v`.`expected_schedule_date` as `expected_schedule_date`,
    `st`.`customer_order_no` as `customer_order_no`,
    `rmfgsr`.`product_type_group` as `product_type_group`,
    `st`.`product_material_id` as `product_material_id`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `st`.`product_material_indent_quantity` as `product_material_indent_quantity`,
    `st`.`product_material_indent_weight` as `product_material_indent_weight`,
    `st`.`product_material_issue_quantity` as `product_material_issue_quantity`,
    `st`.`product_material_issue_weight` as `product_material_issue_weight`,
    `st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
    `rmfgsr`.`product_material_name` as `product_rm_name`,
    `rmfgsr`.`product_material_drawing_no` as `product_rm_drawing_no`,
    `rmfgsr`.`product_material_tech_spect` as `product_rm_tech_spect`,
    `st`.`product_material_issue_return_quantity` as `product_material_issue_return_quantity`,
    `rmfgsr`.`product_type_name` as `product_type_name`,
    `rmfgsr`.`product_material_make_name` as `product_make_name`,
    `rmfgsr`.`product_material_type_name` as `product_material_type_name`,
    `rmfgsr`.`product_material_stock_unit_name` as `product_material_unit_name`,
    `rmfgsr`.`lead_time` as `product_lead_time`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_weight`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `product_Rawmaterial_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `product_Rawmaterial_stock_weight`,
    coalesce((select sum(`ptdetails`.`reserve_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `reserve_quantity`,
    coalesce((select sum(`ptdetails`.`reserve_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `reserve_weight`,
    `st`.`product_material_approved_quantity` as `product_material_approved_quantity`,
    `st`.`product_material_approved_weight` as `product_material_approved_weight`,
    `st`.`product_material_rejected_quantity` as `product_material_rejected_quantity`,
    `st`.`product_material_rejected_weight` as `product_material_rejected_weight`,
    `st`.`product_material_issue_return_weight` as `product_material_issue_return_weight`,
    `v`.`customer_state_name` as `customer_state_name`,
    `v`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `st`.`product_std_weight` as `product_std_weight`,
    `st`.`indent_no` as `indent_no`,
    `st`.`indent_date` as `indent_date`,
    `st`.`indent_version` as `indent_version`,
    `st`.`issue_item_status` as `issue_item_status`,
    `st`.`issue_batch_no` as `issue_batch_no`,
    `st`.`material_batch_rate` as `material_batch_rate`,
    `st`.`product_material_issue_quantity` * `st`.`material_batch_rate` as `material_issue_amount`,
    `v`.`company_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `st`.`financial_year` as `financial_year`,
    `st`.`issue_remark` as `issue_remark`,
    `gd`.`godown_name` as `godown_name`,
    `gds`.`godown_section_name` as `godown_section_name`,
    `gdsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `st`.`godown_id` as `godown_id`,
    `st`.`godown_section_id` as `godown_section_id`,
    `st`.`godown_section_beans_id` as `godown_section_beans_id`,
    `st`.`cost_center_id` as `cost_center_id`,
    `fm`.`cost_center_name` as `cost_center_name`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `st`.`is_active` as `is_active`,
    `st`.`is_delete` as `is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`deleted_on` as `deleted_on`,
    `v`.`customer_id` as `customer_id`,
    `v`.`department_id` as `department_id`,
    `st`.`indented_by_id` as `indented_by_id`,
    `v`.`issued_by_id` as `issued_by_id`,
    `v`.`company_id` as `company_id`,
    `v`.`company_branch_id` as `company_branch_id`,
    `v`.`indent_issue_type_id` as `product_type_id`,
    `rmfgsr`.`product_material_type_id` as `product_material_type_id`,
    `rmfgsr`.`product_material_grade_id` as `product_material_grade_id`,
    `rmfgsr`.`product_material_packing_id` as `product_material_packing_id`,
    `st`.`product_material_unit_id` as `product_material_unit_id`,
    `st`.`issue_details_transaction_id` as `issue_details_transaction_id`,
    `st`.`issue_master_transaction_id` as `issue_master_transaction_id`,
    `st`.`indent_details_id` as `indent_details_id`,
    `v`.`sub_department_id` as `sub_department_id`,
    `v`.`issue_no` as `field_name`,
    `v`.`issue_version` as `field_id`
from
    (((((((`st_indent_material_issue_details` `st`
left join `stv_indent_material_issue_summary` `v` on
    (`v`.`company_branch_id` = `st`.`company_branch_id`
        and `v`.`company_id` = `st`.`company_id`
        and `v`.`issue_master_transaction_id` = `st`.`issue_master_transaction_id`
        and `v`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `st`.`product_material_id`))
left join `cm_employee` `e` on
    (`e`.`is_delete` = 0
        and `e`.`employee_id` = `st`.`indented_by_id`))
left join `cm_godown` `gd` on
    (`gd`.`is_delete` = 0
        and `gd`.`godown_id` = `st`.`godown_id`))
left join `cm_godown_section` `gds` on
    (`gds`.`is_delete` = 0
        and `gds`.`godown_section_id` = `st`.`godown_section_id`))
left join `cm_godown_section_beans` `gdsb` on
    (`gdsb`.`is_delete` = 0
        and `gdsb`.`godown_section_beans_id` = `st`.`godown_section_beans_id`))
left join `fm_cost_center` `fm` on
    (`fm`.`company_id` = `st`.`company_id`
        and `fm`.`cost_center_id` = `st`.`cost_center_id`))
where
    `st`.`is_delete` = 0;




