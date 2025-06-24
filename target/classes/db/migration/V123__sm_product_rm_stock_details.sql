


ALTER TABLE sm_product_rm_stock_details ADD no_of_boxes BIGINT(20) DEFAULT 0 NULL COMMENT 'no of boxes quantity of materials';


ALTER TABLE sm_product_rm_stock_summary ADD no_of_boxes BIGINT(20) DEFAULT 0 NULL COMMENT 'no of boxes of materials';




UPDATE sm_product_rm_stock_details AS st
LEFT JOIN pt_goods_receipt_details AS grn
ON grn.goods_receipt_no = st.goods_receipt_no
AND grn.product_material_id = st.product_rm_id
SET st.no_of_boxes = COALESCE(grn.no_of_boxes, st.no_of_boxes)
where st.product_type_id = 12;



-- smv_product_rm_stock_summary source

create or REPLACE algorithm = UNDEFINED view `smv_product_rm_stock_summary` as
select
    `sm`.`financial_year` as `financial_year`,
    `sup`.`supplier_name` as `supplier_name`,
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
    `sm`.`loan_issue_quantity` as `loan_issue_quantity`,
    `sm`.`loan_issue_weight` as `loan_issue_weight`,
    `sm`.`cancel_quantity` as `cancel_quantity`,
    `sm`.`cancel_weight` as `cancel_weight`,
    `sm`.`difference_quantity` as `difference_quantity`,
    `sm`.`difference_weight` as `difference_weight`,
    `sm`.`total_box_weight` as `total_box_weight`,
    `sm`.`total_quantity_in_box` as `total_quantity_in_box`,
    `sm`.`weight_per_box_item` as `weight_per_box_item`,
    `sm`.`no_of_boxes` as `no_of_boxes`,
    `gd`.`godown_name` as `godown_name`,
    `gd`.`godown_short_name` as `godown_short_name`,
    `gd`.`godown_section_count` as `godown_section_count`,
    `gds`.`godown_section_name` as `godown_section_name`,
    `gds`.`godown_section_short_name` as `godown_section_short_name`,
    `gdsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `gdsb`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    `sm`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `pt`.`product_type_short_name` as `product_type_short_name`,
    `pp`.`quantity_per_packing` as `quantity_per_packing`,
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
    ((((((((((`sm_product_rm_stock_summary` `sm`
left join `cmv_company` `v` on
    (`v`.`company_id` = `sm`.`company_id`
        and `v`.`company_branch_id` = `sm`.`company_branch_id`))
left join `cm_supplier` `sup` on
    (`sup`.`company_branch_id` = `sm`.`company_branch_id`
        and `sup`.`company_id` = `sm`.`company_id`
        and `sup`.`supplier_id` = `sm`.`supplier_id`
        and `sup`.`is_delete` = 0))
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






    -- smv_product_rm_stock_summary_rpt source

    create or REPLACE algorithm = UNDEFINED view `smv_product_rm_stock_summary_rpt` as
    select
        concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:Y:C:smv_product_type:F') as `product_type_name`,
        concat(ifnull(`v`.`product_rm_name`, ''), ':Material Name:Y:T') as `product_rm_name`,
        concat(ifnull(`v`.`product_rm_code`, ''), ':Material Code:Y:T') as `product_rm_code`,
        concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:Y:C:cmv_godown:F') as `godown_name`,
        concat(ifnull(`v`.`godown_section_name`, ''), ':Godown Section Name:Y:C:cmv_godown_section:F') as `godown_section_name`,
        concat(ifnull(`v`.`godown_section_beans_name`, ''), ':Godown Section Beans Name:Y:C:cmv_godown_section_beans:F') as `godown_section_beans_name`,
        concat(ifnull(`v`.`product_material_category1_name`, ''), ':Catergory1 Name:Y:C:smv_product_category1:F') as `product_material_category1_name`,
        concat(ifnull(`v`.`product_material_category2_name`, ''), ':Catergory2 Name:Y:C:smv_product_category2:F') as `product_material_category2_name`,
        concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') as `company_name`,
        concat(ifnull(`v`.`product_type_group`, ''), ':Product Type Group:O:N:') as `product_type_group`,
        concat(ifnull(`v`.`order_quantity`, ''), ':Order Quantity:O:N:') as `order_quantity`,
        concat(ifnull(`v`.`order_weight`, ''), ':Order Weight:O:N:') as `order_weight`,
        concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier:F') as `supplier_name`,
        concat(ifnull(`v`.`customer_name`, ''), ':Customer:Y:C:cmv_customer:F') as `customer_name`,
        concat(ifnull(`v`.`pending_quantity`, ''), ':Pending Quantity:O:N:') as `pending_quantity`,
        concat(ifnull(`v`.`pending_weight`, ''), ':Pending Weight:O:N:') as `pending_weight`,
        concat(ifnull(`v`.`opening_quantity`, ''), ':Opening Quantity:O:N:') as `opening_quantity`,
        concat(ifnull(`v`.`opening_weight`, ''), ':Opening Weight:O:N:') as `opening_weight`,
        concat(ifnull(`v`.`reserve_quantity`, ''), ':Reserve Quantity:O:N:') as `reserve_quantity`,
        concat(ifnull(`v`.`reserve_weight`, ''), ':Reserve Weight:O:N:') as `reserve_weight`,
        concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') as `excess_quantity`,
        concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') as `excess_weight`,
        concat(ifnull(`v`.`pree_closed_quantity`, ''), ':Pree Closed Quantity:O:N:') as `pree_closed_quantity`,
        concat(ifnull(`v`.`pree_closed_weight`, ''), ':Pree Closed Weight:O:N:') as `pree_closed_weight`,
        concat(ifnull(`v`.`purchase_quantity`, ''), ':Purchase Quantity:O:N:') as `purchase_quantity`,
        concat(ifnull(`v`.`purchase_weight`, ''), ':Purchase Weight:O:N:') as `purchase_weight`,
        concat(ifnull(`v`.`purchase_return_quantity`, ''), ':Purchase Return Quantity:O:N:') as `purchase_return_quantity`,
        concat(ifnull(`v`.`purchase_return_weight`, ''), ':Purchase Return Weight:O:N:') as `purchase_return_weight`,
        concat(ifnull(`v`.`purchase_rejection_quantity`, ''), ':Purchase Rejection Quantity:O:N:') as `purchase_rejection_quantity`,
        concat(ifnull(`v`.`purchase_rejection_weight`, ''), ':Purchase Rejection Weight:O:N:') as `purchase_rejection_weight`,
        concat(ifnull(`v`.`jobcard_quantity`, ''), ':Jobcard Quantity:O:N:') as `jobcard_quantity`,
        concat(ifnull(`v`.`jobcard_weight`, ''), ':Jobcard Weight:O:N:') as `jobcard_weight`,
        concat(ifnull(`v`.`production_issue_quantity`, ''), ':Issue Quantity:O:N:') as `production_issue_quantity`,
        concat(ifnull(`v`.`production_issue_weight`, ''), ':Issue Weight:O:N:') as `production_issue_weight`,
        concat(ifnull(`v`.`production_issue_return_quantity`, ''), ':Issue Return Quantity:O:N:') as `production_issue_return_quantity`,
        concat(ifnull(`v`.`production_issue_return_weight`, ''), ':Issue Return Weight:O:N:') as `production_issue_return_weight`,
        concat(ifnull(`v`.`production_issue_rejection_quantity`, ''), ':Issue Rejection Quantity:O:N:') as `production_issue_rejection_quantity`,
        concat(ifnull(`v`.`production_issue_rejection_weight`, ''), ':Issue Rejection Weight:O:N:') as `production_issue_rejection_weight`,
        concat(ifnull(`v`.`production_quantity`, ''), ':Production Quantity:O:N:') as `production_quantity`,
        concat(ifnull(`v`.`production_weight`, ''), ':Production Weight:O:N:') as `production_weight`,
        concat(ifnull(`v`.`production_return_quantity`, ''), ':Production Return Quantity:O:N:') as `production_return_quantity`,
        concat(ifnull(`v`.`production_return_weight`, ''), ':Production Return Weight:O:N:') as `production_return_weight`,
        concat(ifnull(`v`.`production_rejection_quantity`, ''), ':Production Rejection Quantity:O:N:') as `production_rejection_quantity`,
        concat(ifnull(`v`.`production_rejection_weight`, ''), ':Production Rejection Weight:O:N:') as `production_rejection_weight`,
        concat(ifnull(`v`.`assembly_production_issue_quantity`, ''), ':Assembly Production Issue Quantity:O:N:') as `assembly_production_issue_quantity`,
        concat(ifnull(`v`.`assembly_production_issue_weight`, ''), ':Assembly Production Issue Weight:O:N:') as `assembly_production_issue_weight`,
        concat(ifnull(`v`.`customer_receipt_quantity`, ''), ':Customer Receipt Quantity:O:N:') as `customer_receipt_quantity`,
        concat(ifnull(`v`.`customer_receipt_weight`, ''), ':Customer Receipt Weight:O:N:') as `customer_receipt_weight`,
        concat(ifnull(`v`.`customer_return_quantity`, ''), ':Customer Return Quantity:O:N:') as `customer_return_quantity`,
        concat(ifnull(`v`.`customer_return_weight`, ''), ':Customer Return Weight:O:N:') as `customer_return_weight`,
        concat(ifnull(`v`.`customer_rejection_weight`, ''), ':Customer Rejection Weight:O:N:') as `customer_rejection_weight`,
        concat(ifnull(`v`.`customer_rejection_quantity`, ''), ':Customer Rejection Quantity:O:N:') as `customer_rejection_quantity`,
        concat(ifnull(`v`.`sales_quantity`, ''), ':Sales Quantity:O:N:') as `sales_quantity`,
        concat(ifnull(`v`.`sales_weight`, ''), ':Sales Weight:O:N:') as `sales_weight`,
        concat(ifnull(`v`.`sales_return_quantity`, ''), ':Sales Return Quantity:O:N:') as `sales_return_quantity`,
        concat(ifnull(`v`.`sales_return_weight`, ''), ':Sales Return Weight:O:N:') as `sales_return_weight`,
        concat(ifnull(`v`.`sales_rejection_quantity`, ''), ':Sales Rejection Quantity:O:N:') as `sales_rejection_quantity`,
        concat(ifnull(`v`.`sales_rejection_weight`, ''), ':Sales Rejection Weight:O:N:') as `sales_rejection_weight`,
        concat(ifnull(`v`.`transfer_issue_quantity`, ''), ':Transfer Issue Quantity:O:N:') as `transfer_issue_quantity`,
        concat(ifnull(`v`.`transfer_issue_weight`, ''), ':Transfer Issue Weight:O:N:') as `transfer_issue_weight`,
        concat(ifnull(`v`.`transfer_receipt_quantity`, ''), ':Transfer Receipt Quantity:O:N:') as `transfer_receipt_quantity`,
        concat(ifnull(`v`.`transfer_receipt_weight`, ''), ':Transfer Receipt Weight:O:N:') as `transfer_receipt_weight`,
        concat(ifnull(`v`.`outsources_out_quantity`, ''), ':Outsources Out Quantity:O:N:') as `outsources_out_quantity`,
        concat(ifnull(`v`.`outsources_out_weight`, ''), ':Outsources Out Weight:O:N:') as `outsources_out_weight`,
        concat(ifnull(`v`.`outsources_in_quantity`, ''), ':Outsources In Quantity:O:N:') as `outsources_in_quantity`,
        concat(ifnull(`v`.`outsources_in_weight`, ''), ':Outsources In Weight:O:N:') as `outsources_in_weight`,
        concat(ifnull(`v`.`outsources_rejection_quantity`, ''), ':Outsources Rejection Quantity:O:N:') as `outsources_rejection_quantity`,
        concat(ifnull(`v`.`outsources_rejection_weight`, ''), ':Outsources Rejection Weight:O:N:') as `outsources_rejection_weight`,
        concat(ifnull(`v`.`loan_receipt_quantity`, ''), ':Loan Receipt Quantity:O:N:') as `loan_receipt_quantity`,
        concat(ifnull(`v`.`loan_receipt_weight`, ''), ':Loan Receipt Weight:O:N:') as `loan_receipt_weight`,
        concat(ifnull(`v`.`loan_issue_quantity`, ''), ':Loan Issue Quantity:O:N:') as `loan_issue_quantity`,
        concat(ifnull(`v`.`loan_issue_weight`, ''), ':Loan Issue Weight:O:N:') as `loan_issue_weight`,
        concat(ifnull(`v`.`cancel_quantity`, ''), ':Cancel Quantity:O:N:') as `cancel_quantity`,
        concat(ifnull(`v`.`cancel_weight`, ''), ':Cancel Weight:O:N:') as `cancel_weight`,
        concat(ifnull(`v`.`difference_quantity`, ''), ':Difference Quantity:O:N:') as `difference_quantity`,
        concat(ifnull(`v`.`difference_weight`, ''), ':Difference Weight:O:N:') as `difference_weight`,
        concat(ifnull(`v`.`closing_balance_quantity`, ''), ':Closing Balance Quantity:O:N:') as `closing_balance_quantity`,
        concat(ifnull(`v`.`closing_balance_weight`, ''), ':Closing Balance Weight:O:N:') as `closing_balance_weight`,
         concat(ifnull(`v`.`no_of_boxes`, ''), ':No. of Boxes:O:N:') as `no_of_boxes`,
        concat(ifnull(`v`.`total_box_weight`, ''), ':Gross Weight:O:N:') as `total_box_weight`,
        concat(ifnull(`v`.`total_quantity_in_box`, ''), ':No. Of Package:O:N:') as `total_quantity_in_box`,
        concat(ifnull(`v`.`weight_per_box_item`, ''), ':Weight Per Package:O:N:') as `weight_per_box_item`,
        concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') as `product_type_short_name`,
        concat(ifnull(`v`.`product_packing_name`, ''), ':Product Packing Name:Y:C:smv_product_packing') as `product_packing_name`,
        concat(ifnull(`v`.`quantity_per_packing`, ''), ':Quantity Per Packing:O:N:') as `quantity_per_packing`,
        concat(ifnull(`v`.`product_unit_name`, ''), ':Product Unit Name:Y:C:smv_product_unit:F') as `product_unit_name`,
        concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
        concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
        concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch:F') as `company_branch_name`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
        concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
        concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
        concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
        concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
        concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
        concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
        concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
        concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
        concat(ifnull(`v`.`stock_transaction_id`, ''), ':Stock Transaction Id:O:N:') as `stock_transaction_id`,
        concat(ifnull(`v`.`product_type_id`, ''), ':Product Type Id:N:N:') as `product_type_id`,
        concat(ifnull(`v`.`product_rm_id`, ''), ':Product Rm Id:O:N:') as `product_rm_id`,
        concat(ifnull(`v`.`product_material_unit_id`, ''), ':Product Material Unit Id:N:N:') as `product_material_unit_id`,
        concat(ifnull(`v`.`product_material_packing_id`, ''), ':Product Material Packing Id:N:N:') as `product_material_packing_id`,
        concat(ifnull(`v`.`godown_id`, ''), ':Godown Id:N:N:') as `godown_id`,
        concat(ifnull(`v`.`godown_section_id`, ''), ':Godown Section Id:N:N:') as `godown_section_id`,
        concat(ifnull(`v`.`godown_section_beans_id`, ''), ':Godown Section Beans Id:N:N:') as `godown_section_beans_id`
    from
        `smv_product_rm_stock_summary` `v`
    limit 1;






    -- smv_product_rm_stock_details source

    create or REPLACE algorithm = UNDEFINED view `smv_product_rm_stock_details` as
    select
        `sm`.`stock_date` as `stock_date`,
        `sm`.`day_closed` as `day_closed`,
        `sm`.`batch_no` as `batch_no`,
        `supp`.`supplier_code` as `supplier_code`,
        `supp`.`supplier_name` as `supplier_name`,
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
        `sm`.`loan_issue_quantity` as `loan_issue_quantity`,
        `sm`.`loan_issue_weight` as `loan_issue_weight`,
        `sm`.`cancel_quantity` as `cancel_quantity`,
        `sm`.`cancel_weight` as `cancel_weight`,
        `sm`.`difference_quantity` as `difference_quantity`,
        `sm`.`difference_weight` as `difference_weight`,
        `sm`.`total_box_weight` as `total_box_weight`,
        `sm`.`total_quantity_in_box` as `total_quantity_in_box`,
        `sm`.`weight_per_box_item` as `weight_per_box_item`,
        `sm`.`no_of_boxes` as `no_of_boxes`,
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

        create or REPLACE algorithm = UNDEFINED view `smv_product_rm_stock_details_rpt` as
        select
            concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') as `company_name`,
            concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:Y:C:cmv_godown:F') as `godown_name`,
            concat(ifnull(`v`.`godown_section_name`, ''), ':Godown Section Name:Y:C:cmv_godown_section') as `godown_section_name`,
            concat(ifnull(`v`.`godown_section_beans_name`, ''), ':Godown Section Beans Name:Y:C:cmv_godown_section_beans') as `godown_section_beans_name`,
            concat(ifnull(`v`.`product_rm_name`, ''), ':Material Name:Y:T') as `product_rm_name`,
            concat(ifnull(`v`.`product_rm_code`, ''), ':Material Code:Y:T') as `product_rm_code`,
            concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:O:N:') as `product_type_name`,
            concat(ifnull(`v`.`product_material_category1_name`, ''), ':Catergory1 Name:Y:C:smv_product_category1:F') as `product_material_category1_name`,
            concat(ifnull(`v`.`product_material_category2_name`, ''), ':Catergory2 Name:Y:C:smv_product_category2:F') as `product_material_category2_name`,
            concat(ifnull(`v`.`stock_date`, ''), ':Stock Date:Y:D:') as `stock_date`,
            concat(ifnull(`v`.`day_closed`, ''), ':Day Closed:O:N:') as `day_closed`,
            concat(ifnull(`v`.`batch_no`, ''), ':Batch No:O:N:') as `batch_no`,
            concat(ifnull(`v`.`batch_expiry_date`, ''), ':Batch Expiry Date:Y:D:') as `batch_expiry_date`,
            concat(ifnull(`v`.`sales_order_no`, ''), ':Sales Order No:O:N:') as `sales_order_no`,
            concat(ifnull(`v`.`sales_order_version`, ''), ':Sales Order Version:O:N:') as `sales_order_version`,
            concat(ifnull(`v`.`goods_receipt_no`, ''), ':Goods Receipt No:O:N:') as `goods_receipt_no`,
            concat(ifnull(`v`.`goods_receipt_version`, ''), ':Goods Receipt Version:O:N:') as `goods_receipt_version`,
            concat(ifnull(`v`.`customer_goods_receipt_no`, ''), ':Customer Goods Receipt No:O:N:') as `customer_goods_receipt_no`,
            concat(ifnull(`v`.`customer_goods_receipt_no`, ''), ':Customer Goods Receipt Version:O:N:') as `customer_goods_receipt_version`,
            concat(ifnull(`v`.`so_sr_no`, ''), ':So Sr No:O:N:') as `so_sr_no`,
            concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:O:N:') as `customer_order_no`,
            concat(ifnull(`v`.`order_quantity`, ''), ':Order Quantity:O:N:') as `order_quantity`,
            concat(ifnull(`v`.`order_weight`, ''), ':Order Weight:O:N:') as `order_weight`,
            concat(ifnull(`v`.`batch_rate`, ''), ':Batch Rate:Y:T:') as `batch_rate`,
            concat(ifnull(`v`.`closing_amount`, ''), ':Closing Amount:Y:T:') as `closing_amount`,
            concat(ifnull(`v`.`pending_quantity`, ''), ':Pending Quantity:O:N:') as `pending_quantity`,
            concat(ifnull(`v`.`pending_weight`, ''), ':Pending Weight:O:N:') as `pending_weight`,
            concat(ifnull(`v`.`opening_quantity`, ''), ':Opening Quantity:O:N:') as `opening_quantity`,
            concat(ifnull(`v`.`opening_weight`, ''), ':Opening Weight:O:N:') as `opening_weight`,
            concat(ifnull(`v`.`reserve_quantity`, ''), ':Reserve Quantity:O:N:') as `reserve_quantity`,
            concat(ifnull(`v`.`reserve_weight`, ''), ':Reserve Weight:O:N:') as `reserve_weight`,
            concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') as `excess_quantity`,
            concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') as `excess_weight`,
            concat(ifnull(`v`.`pree_closed_quantity`, ''), ':Pree Closed Quantity:O:N:') as `pree_closed_quantity`,
            concat(ifnull(`v`.`pree_closed_weight`, ''), ':Pree Closed Weight:O:N:') as `pree_closed_weight`,
            concat(ifnull(`v`.`purchase_quantity`, ''), ':Purchase Quantity:O:N:') as `purchase_quantity`,
            concat(ifnull(`v`.`purchase_weight`, ''), ':Purchase Weight:O:N:') as `purchase_weight`,
            concat(ifnull(`v`.`purchase_return_quantity`, ''), ':Purchase Return Quantity:O:N:') as `purchase_return_quantity`,
            concat(ifnull(`v`.`purchase_return_weight`, ''), ':Purchase Return Weight:O:N:') as `purchase_return_weight`,
            concat(ifnull(`v`.`purchase_rejection_quantity`, ''), ':Purchase Rejection Quantity:O:N:') as `purchase_rejection_quantity`,
            concat(ifnull(`v`.`purchase_rejection_weight`, ''), ':Purchase Rejection Weight:O:N:') as `purchase_rejection_weight`,
            concat(ifnull(`v`.`jobcard_quantity`, ''), ':Jobcard Quantity:O:N:') as `jobcard_quantity`,
            concat(ifnull(`v`.`jobcard_weight`, ''), ':Jobcard Weight:O:N:') as `jobcard_weight`,
            concat(ifnull(`v`.`production_issue_quantity`, ''), ':Issue Quantity:O:N:') as `production_issue_quantity`,
            concat(ifnull(`v`.`production_issue_weight`, ''), ':Issue Weight:O:N:') as `production_issue_weight`,
            concat(ifnull(`v`.`production_issue_return_quantity`, ''), ':Issue Return Quantity:O:N:') as `production_issue_return_quantity`,
            concat(ifnull(`v`.`production_issue_return_weight`, ''), ':Issue Return Weight:O:N:') as `production_issue_return_weight`,
            concat(ifnull(`v`.`production_issue_rejection_quantity`, ''), ':Issue Rejection Quantity:O:N:') as `production_issue_rejection_quantity`,
            concat(ifnull(`v`.`production_issue_rejection_weight`, ''), ':Production Issue Rejection Weight:O:N:') as `production_issue_rejection_weight`,
            concat(ifnull(`v`.`production_quantity`, ''), ':Production Quantity:O:N:') as `production_quantity`,
            concat(ifnull(`v`.`production_weight`, ''), ':Production Weight:O:N:') as `production_weight`,
            concat(ifnull(`v`.`production_return_quantity`, ''), ':Return Quantity:O:N:') as `production_return_quantity`,
            concat(ifnull(`v`.`production_return_weight`, ''), ':Return Weight:O:N:') as `production_return_weight`,
            concat(ifnull(`v`.`production_rejection_quantity`, ''), ':Rejection Quantity:O:N:') as `production_rejection_quantity`,
            concat(ifnull(`v`.`production_rejection_weight`, ''), ':Rejection Weight:O:N:') as `production_rejection_weight`,
            concat(ifnull(`v`.`assembly_production_issue_quantity`, ''), ':Assembly Production Issue Quantity:O:N:') as `assembly_production_issue_quantity`,
            concat(ifnull(`v`.`assembly_production_issue_weight`, ''), ':Assembly Production Issue Weight:O:N:') as `assembly_production_issue_weight`,
            concat(ifnull(`v`.`customer_receipt_quantity`, ''), ':Customer Receipt Quantity:O:N:') as `customer_receipt_quantity`,
            concat(ifnull(`v`.`customer_return_quantity`, ''), ':Customer Return Quantity:O:N:') as `customer_return_quantity`,
            concat(ifnull(`v`.`customer_receipt_weight`, ''), ':Customer Receipt Weight:O:N:') as `customer_receipt_weight`,
            concat(ifnull(`v`.`customer_return_weight`, ''), ':Customer Return Weight:O:N:') as `customer_return_weight`,
            concat(ifnull(`v`.`customer_rejection_weight`, ''), ':Customer Rejection Weight:O:N:') as `customer_rejection_weight`,
            concat(ifnull(`v`.`customer_rejection_quantity`, ''), ':Customer Rejection Quantity:O:N:') as `customer_rejection_quantity`,
            concat(ifnull(`v`.`sales_quantity`, ''), ':Sales Quantity:O:N:') as `sales_quantity`,
            concat(ifnull(`v`.`sales_weight`, ''), ':Sales Weight:O:N:') as `sales_weight`,
            concat(ifnull(`v`.`sales_return_quantity`, ''), ':Sales Return Quantity:O:N:') as `sales_return_quantity`,
            concat(ifnull(`v`.`sales_return_weight`, ''), ':Sales Return Weight:O:N:') as `sales_return_weight`,
            concat(ifnull(`v`.`sales_rejection_quantity`, ''), ':Sales Rejection Quantity:O:N:') as `sales_rejection_quantity`,
            concat(ifnull(`v`.`sales_rejection_weight`, ''), ':Sales Rejection Weight:O:N:') as `sales_rejection_weight`,
            concat(ifnull(`v`.`transfer_issue_quantity`, ''), ':Transfer Issue Quantity:O:N:') as `transfer_issue_quantity`,
            concat(ifnull(`v`.`transfer_issue_weight`, ''), ':Transfer Issue Weight:O:N:') as `transfer_issue_weight`,
            concat(ifnull(`v`.`transfer_receipt_quantity`, ''), ':Transfer Receipt Quantity:O:N:') as `transfer_receipt_quantity`,
            concat(ifnull(`v`.`transfer_receipt_weight`, ''), ':Transfer Receipt Weight:O:N:') as `transfer_receipt_weight`,
            concat(ifnull(`v`.`outsources_out_quantity`, ''), ':Outsources Out Quantity:O:N:') as `outsources_out_quantity`,
            concat(ifnull(`v`.`outsources_out_weight`, ''), ':Outsources Out Weight:O:N:') as `outsources_out_weight`,
            concat(ifnull(`v`.`outsources_in_quantity`, ''), ':Outsources In Quantity:O:N:') as `outsources_in_quantity`,
            concat(ifnull(`v`.`outsources_in_weight`, ''), ':Outsources In Weight:O:N:') as `outsources_in_weight`,
            concat(ifnull(`v`.`outsources_rejection_quantity`, ''), ':Outsources Rejection Quantity:O:N:') as `outsources_rejection_quantity`,
            concat(ifnull(`v`.`outsources_rejection_weight`, ''), ':Outsources Rejection Weight:O:N:') as `outsources_rejection_weight`,
            concat(ifnull(`v`.`loan_receipt_quantity`, ''), ':Loan Receipt Quantity:O:N:') as `loan_receipt_quantity`,
            concat(ifnull(`v`.`loan_receipt_weight`, ''), ':Loan Receipt Weight:O:N:') as `loan_receipt_weight`,
            concat(ifnull(`v`.`loan_issue_quantity`, ''), ':Loan Issue Quantity:O:N:') as `loan_issue_quantity`,
            concat(ifnull(`v`.`loan_issue_weight`, ''), ':Loan Issue Weight:O:N:') as `loan_issue_weight`,
            concat(ifnull(`v`.`cancel_quantity`, ''), ':Cancel Quantity:O:N:') as `cancel_quantity`,
            concat(ifnull(`v`.`cancel_weight`, ''), ':Cancel Weight:O:N:') as `cancel_weight`,
            concat(ifnull(`v`.`difference_quantity`, ''), ':Difference Quantity:O:N:') as `difference_quantity`,
            concat(ifnull(`v`.`difference_weight`, ''), ':Difference Weight:O:N:') as `difference_weight`,
            concat(ifnull(`v`.`closing_balance_quantity`, ''), ':Closing Balance Quantity:O:N:') as `closing_balance_quantity`,
            concat(ifnull(`v`.`closing_balance_weight`, ''), ':Closing Balance Weight:O:N:') as `closing_balance_weight`,
            concat(ifnull(`v`.`no_of_boxes`, ''), ':No. of Boxes:O:N:') as `no_of_boxes`,
            concat(ifnull(`v`.`total_box_weight`, ''), ':Gross Weight:O:N:') as `total_box_weight`,
            concat(ifnull(`v`.`total_quantity_in_box`, ''), ':No. Of Package:O:N:') as `total_quantity_in_box`,
            concat(ifnull(`v`.`weight_per_box_item`, ''), ':Weight Per Package:O:N:') as `weight_per_box_item`,
            concat(ifnull(`v`.`product_rm_technical_name`, ''), ':Raw Material Technical Name:O:N:') as `product_rm_technical_name`,
            concat(ifnull(`v`.`product_rm_short_name`, ''), ':Raw Material Short Name:O:N:') as `product_rm_short_name`,
            concat(ifnull(`v`.`product_rm_purchase_unit_name`, ''), 'Raw Material Purchase Unit Name:O:N:') as `product_rm_purchase_unit_name`,
            concat(ifnull(`v`.`customer_code`, ''), ':Customer Code:O:N:') as `customer_code`,
            concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer:F') as `customer_name`,
            concat(ifnull(`v`.`godown_short_name`, ''), ':Godown Short Name:O:N:') as `godown_short_name`,
            concat(ifnull(`v`.`godown_area`, ''), ':Godown Area:O:N:') as `godown_area`,
            concat(ifnull(`v`.`godown_section_count`, ''), ':Godown Section Count:O:N:') as `godown_section_count`,
            concat(ifnull(`v`.`godown_section_short_name`, ''), ':Godown Section Short Name:O:N:') as `godown_section_short_name`,
            concat(ifnull(`v`.`godown_section_area`, ''), ':Godown Section Area:O:N:') as `godown_section_area`,
            concat(ifnull(`v`.`godown_section_beans_short_name`, ''), ':Godown Section Beans Short Name:O:N:') as `godown_section_beans_short_name`,
            concat(ifnull(`v`.`godown_section_beans_area`, ''), ':Godown Section Beans Area:O:N:') as `godown_section_beans_area`,
            concat(ifnull(`v`.`product_packing_name`, ''), ':Product Packing Name:Y:C:smv_product_packing:F') as `product_packing_name`,
            concat(ifnull(`v`.`quantity_per_packing`, ''), ':Quantity Per Packing:O:N:') as `quantity_per_packing`,
            concat(ifnull(`v`.`weight_per_packing`, ''), ':Weight Per Packing:O:N:') as `weight_per_packing`,
            concat(ifnull(`v`.`supplier_code`, ''), ':Supplier Code:O:N:') as `supplier_code`,
            concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier:F') as `supplier_name`,
            concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
            concat(ifnull(`v`.`product_type_group`, ''), ':Product Type Group:O:N:') as `product_type_group`,
            concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch:F') as `company_branch_name`,
            concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
            concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
            concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
            concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
            concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
            concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
            concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
            concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
            concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
            concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
            concat(ifnull(`v`.`stock_transaction_id`, ''), ':Stock Transaction Id:O:N:') as `stock_transaction_id`,
            concat(ifnull(`v`.`product_rm_id`, ''), ':Product Rm Id:O:N:') as `product_rm_id`,
            concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:O:N:') as `customer_id`,
            concat(ifnull(`v`.`godown_id`, ''), ':Godown Id:N:N:') as `godown_id`,
            concat(ifnull(`v`.`supplier_id`, ''), ':Supplier Id:N:N:') as `supplier_id`,
            concat(ifnull(`v`.`godown_section_id`, ''), ':Godown Section Id:N:N:') as `godown_section_id`,
            concat(ifnull(`v`.`godown_section_beans_id`, ''), ':Godown Section Beans Id:N:N:') as `godown_section_beans_id`,
            concat(ifnull(`v`.`product_type_id`, ''), ':Product Type Id:N:N:') as `product_type_id`,
            concat(ifnull(`v`.`product_material_unit_id`, ''), ':Product Material Unit_id:N:N:') as `product_material_unit_id`,
            concat(ifnull(`v`.`product_material_packing_id`, ''), ':Product Material Packing Id:N:N:') as `product_material_packing_id`
        from
            `smv_product_rm_stock_details` `v`
        limit 1;
