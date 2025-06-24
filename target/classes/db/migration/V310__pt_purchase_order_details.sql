 ALTER TABLE  pt_purchase_order_details ADD currency_id BIGINT(20) DEFAULT 3 NULL COMMENT 'currency id for po amount';


ALTER TABLE pt_goods_receipt_details ADD po_material_rate DECIMAL(18,2) DEFAULT 0 NULL COMMENT 'po material rate will come from po';
ALTER TABLE pt_goods_receipt_details ADD currency_id BIGINT(20) DEFAULT 3 NULL COMMENT 'currency id for po amount';
ALTER TABLE pt_goods_receipt_details ADD grn_currency_id BIGINT(20) DEFAULT 3 NULL COMMENT 'currency id default rupees given';
ALTER TABLE pt_goods_receipt_details CHANGE po_material_rate po_material_rate DECIMAL(18,2) DEFAULT 0 NULL COMMENT 'po material rate will come from po' AFTER currency_id;
ALTER TABLE pt_goods_receipt_details CHANGE currency_id currency_id BIGINT(20) DEFAULT 3 NULL COMMENT 'default rupees given' AFTER product_material_grn_rejected_weight;
ALTER TABLE pt_goods_receipt_details CHANGE grn_currency_id grn_currency_id BIGINT(20) DEFAULT 3 NULL COMMENT 'default grn currency is rupees' AFTER po_material_rate;


ALTER TABLE pt_goods_receipt_details ADD currency_exchange_rate DECIMAL(18,4) DEFAULT 0 NULL COMMENT 'exchange rate of base currency INR';
ALTER TABLE pt_goods_receipt_details CHANGE currency_exchange_rate currency_exchange_rate DECIMAL(18,4) DEFAULT 0 NULL COMMENT 'exchange rate of base currency INR' AFTER material_rate;

-- ptv_purchase_order_details source

create or REPLACE algorithm = UNDEFINED view `ptv_purchase_order_details` as
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
        when 'Z' then 'Pree Closed'
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
    `rmfgsr`.`product_material_std_weight` as `product_material_std_weight`,
    `rmfgsr`.`product_material_stock_unit_name` as `product_material_stock_unit_name`,
    `pp`.`product_packing_name` as `product_packing_name`,
    `pp`.`quantity_per_packing` as `quantity_per_packing`,
    `hsn`.`hsn_sac_type` as `hsn_sac_type`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `hsn`.`hsn_sac_description` as `hsn_sac_description`,
    `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
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
    `pt`.`pre_closed_remark` as `pre_closed_remark`,
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
    `cm`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `cb`.`branch_cell_no` as `company_cell_no`,
    `cb`.`branch_phone_no` as `company_phone_no`,
    `cb`.`branch_EmailId` as `company_EmailId`,
    `cb`.`branch_website` as `company_website`,
    `cb`.`branch_gst_no` as `company_gst_no`,
    `cb`.`branch_pan_no` as `company_pan_no`,
    `cs`.`state_name` as `company_state`,
    `cb`.`branch_pincode` as `company_pincode`,
    `cb`.`branch_address1` as `company_address1`,
    `pt`.`financial_year` as `financial_year`,
    `pom`.`city_name` as `city_name`,
    `fc`.`currency_name` as `currency_name`,
    `fc`.`currency_code` as `currency_code`,
    `fc`.`currency_symbol` as `currency_symbol`,
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
    `pt`.`currency_id` as `currency_id`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_quantity`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `pt`.`indent_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id` and `pt`.`indent_no` <> ''), 0) as `prev_po_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_weight`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `pt`.`indent_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id` and `pt`.`indent_no` <> ''), 0) as `prev_po_weight`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `prev_grn_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `prev_grn_weight`
from
    ((((((((((((((`pt_purchase_order_details` `pt`
left join `ptv_purchase_order_master_summary` `pom` on
    (`pom`.`company_branch_id` = `pt`.`company_branch_id`
        and `pom`.`company_id` = `pt`.`company_id`
        and `pom`.`purchase_order_no` = `pt`.`purchase_order_no`
        and `pom`.`purchase_order_version` = `pt`.`purchase_order_version`
        and `pom`.`is_delete` = 0))
left join `cm_company` `cm` on
    (`cm`.`company_id` = `pt`.`company_id`
        and `cm`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `cm_state` `cs` on
    (`cs`.`state_id` = `cb`.`branch_state_id`
        and `cs`.`state_id` = 0))
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `pt`.`purchase_order_type_id`
        and `p`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `pt`.`product_material_id`))
left join `sm_product_packing` `pp` on
    (`pp`.`product_packing_id` = `pt`.`product_material_packing_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`product_material_hsn_code_id`))
left join `cm_department` `vp` on
    (`vp`.`department_id` = `pt`.`department_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `pt`.`approved_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `pt`.`indented_by_id`
        and `e1`.`company_id` = `pt`.`company_id`))
left join `cm_customer` `c` on
    (`c`.`customer_id` = `pt`.`customer_id`))
left join `cm_department` `sdp` on
    (`sdp`.`department_id` = `pt`.`sub_department_id`))
left join `fm_currency` `fc` on
    (`fc`.`currency_id` = `pt`.`currency_id`
        and `fc`.`is_delete` = 0))
where
    `pt`.`is_delete` = 0;






    -- ptv_purchase_order_details_rpt source

    create or REPLACE algorithm = UNDEFINED view `ptv_purchase_order_details_rpt` as
    select
        concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') as `purchase_order_no`,
        concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') as `purchase_order_date`,
        concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
        concat(ifnull(`v`.`purchase_order_item_status`, ''), ':Purchase Order Item Status:Y:H:(Approved,Rejected,Partial Receipt,Completed,Canceled,Pree Closed,Bill Booked)') as `purchase_order_item_status_desc`,
        concat(ifnull(`v`.`purchase_order_type`, ''), ':Purchase Order Type:O:N:') as `purchase_order_type`,
        concat(ifnull(`v`.`product_material_code`, ''), ':Material Code:Y:T') as `product_material_code`,
        concat(ifnull(`v`.`product_material_name`, ''), ':Material Name:Y:T') as `product_material_name`,
        concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') as `indent_no`,
        concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') as `indent_date`,
        concat(ifnull(`v`.`product_material_stock_unit_name`, ''), ':Material Unit Name:O:N:') as `product_material_stock_unit_name`,
        concat(ifnull(`v`.`product_material_po_quantity`, ''), ':Material Po Quantity:O:N:') as `product_material_po_quantity`,
        concat(ifnull(`v`.`product_material_po_weight`, ''), ':Material Po Weight:O:N:') as `product_material_po_weight`,
        concat(ifnull(`v`.`material_rate`, ''), ':Material Rate:O:N:') as `material_rate`,
        concat(ifnull(`v`.`material_basic_amount`, ''), ':Material Basic Amount:O:N:') as `material_basic_amount`,
        concat(ifnull(`v`.`material_discount_percent`, ''), ':Material Discount Percent:O:N:') as `material_discount_percent`,
        concat(ifnull(`v`.`material_discount_amount`, ''), ':Material Discount Amount:O:N:') as `material_discount_amount`,
        concat(ifnull(`v`.`material_taxable_amount`, ''), ':Material Taxable Amount:O:N:') as `material_taxable_amount`,
        concat(ifnull(`v`.`material_cgst_percent`, ''), ':Material Cgst Percent:O:N:') as `material_cgst_percent`,
        concat(ifnull(`v`.`material_cgst_total`, ''), ':Material Cgst Total:O:N:') as `material_cgst_total`,
        concat(ifnull(`v`.`material_sgst_percent`, ''), ':Material Sgst Percent:O:N:') as `material_sgst_percent`,
        concat(ifnull(`v`.`material_sgst_total`, ''), ':Material Sgst Total:O:N:') as `material_sgst_total`,
        concat(ifnull(`v`.`material_igst_percent`, ''), ':Material Igst Percent:O:N:') as `material_igst_percent`,
        concat(ifnull(`v`.`material_igst_total`, ''), ':Material Igst Total:O:N:') as `material_igst_total`,
        concat(ifnull(`v`.`material_total_amount`, ''), ':Material Total Amount:O:N:') as `material_total_amount`,
        concat(ifnull(`v`.`currency_name`, ''), ':Currency Name:O:N:') as `currency_name`,
        concat(ifnull(`v`.`product_material_po_approved_quantity`, ''), ':Material Po Approved Quantity:O:N:') as `product_material_po_approved_quantity`,
        concat(ifnull(`v`.`product_material_po_approved_weight`, ''), ':Material Po Approved Weight:O:N:') as `product_material_po_approved_weight`,
        concat(ifnull(`v`.`product_material_po_rejected_quantity`, ''), ':Material Po Rejected Quantity:O:N:') as `product_material_po_rejected_quantity`,
        concat(ifnull(`v`.`product_material_po_rejected_weight`, ''), ':Material Po Rejected Weight:O:N:') as `product_material_po_rejected_weight`,
        concat(ifnull(`v`.`material_po_approval_remark`, ''), ':Material Po Approval Remark:O:N:') as `material_po_approval_remark`,
        concat(ifnull(`v`.`purchase_order_mail_sent_status`, ''), ':Purchase Order Mail Sent Status:Y:H:(Email Sent,Email Failed,Email Pending)') as `purchase_order_mail_sent_status_desc`,
        concat(ifnull(`v`.`material_schedule_date`, ''), ':Material Schedule Date:Y:D:') as `material_schedule_date`,
        concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:T') as `approved_by_name`,
        concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') as `department_name`,
        concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
        concat(ifnull(`v`.`hsn_sac_code`, ''), ':Hsn Sac Code:O:N:') as `hsn_sac_code`,
        concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Hsn Sac Rate:O:N:') as `hsn_sac_rate`,
        concat(ifnull(`v`.`pree_closed_quantity`, ''), ':Pree-Closed Quantity:O:N:') as `pree_closed_quantity`,
        concat(ifnull(`v`.`pree_closed_weight`, ''), ':Pree-Closed Weight:O:N:') as `pree_closed_weight`,
        concat(ifnull(`v`.`pre_closed_remark`, ''), ':Pree-Closed Remark:O:N:') as `pre_closed_remark`,
        concat(ifnull(`v`.`purchase_return_quantity`, ''), ':Purchase Return Quantity:O:N:') as `purchase_return_quantity`,
        concat(ifnull(`v`.`purchase_return_weight`, ''), ':Purchase Return Weight:O:N:') as `purchase_return_weight`,
        concat(ifnull(`v`.`pending_quantity`, ''), ':Pending Quantity:O:N:') as `pending_quantity`,
        concat(ifnull(`v`.`pending_weight`, ''), ':Pending Weight:O:N:') as `pending_weight`,
        concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') as `excess_quantity`,
        concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') as `excess_weight`,
        concat(ifnull(`v`.`production_issue_quantity`, ''), ':Issue Quantity:O:N:') as `production_issue_quantity`,
        concat(ifnull(`v`.`production_issue_weight`, ''), ':Issue Weight:O:N:') as `production_issue_weight`,
        concat(ifnull(`v`.`production_issue_return_quantity`, ''), ':Issue Return Quantity:O:N:') as `production_issue_return_quantity`,
        concat(ifnull(`v`.`production_issue_return_weight`, ''), ':Issue Return Weight:O:N:') as `production_issue_return_weight`,
        concat(ifnull(`v`.`production_issue_rejection_quantity`, ''), ':Issue Rejection Quantity:O:N:') as `production_issue_rejection_quantity`,
        concat(ifnull(`v`.`production_issue_rejection_weight`, ''), ':Issue Rejection Weight:O:N:') as `production_issue_rejection_weight`,
        concat(ifnull(`v`.`assembly_production_issue_quantity`, ''), ':Assembly Production Issue Quantity:O:N:') as `assembly_production_issue_quantity`,
        concat(ifnull(`v`.`assembly_production_issue_weight`, ''), ':Assembly Production Issue Weight:O:N:') as `assembly_production_issue_weight`,
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
        concat(ifnull(`v`.`outsources_out_weight`, ''), ':Outsourcess Out Weight:O:N:') as `outsources_out_weight`,
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
        concat(ifnull(`v`.`prev_grn_quantity`, ''), ':Prev GRN Quantity:O:N:') as `prev_grn_quantity`,
        concat(ifnull(`v`.`prev_grn_weight`, ''), ':Prev GRN Quantity:O:N:') as `prev_grn_weight`,
        concat(ifnull(`v`.`difference_quantity`, ''), ':Difference Quantity:O:N:') as `difference_quantity`,
        concat(ifnull(`v`.`difference_weight`, ''), ':Difference Weight:O:N:') as `difference_weight`,
        concat(ifnull(`v`.`lead_time`, ''), ':Lead Time:O:N:') as `lead_time`,
        concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:T') as `supplier_name`,
        concat(ifnull(`v`.`supplier_state_name`, ''), ':Supplier State Name:O:N:') as `supplier_state_name`,
        concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:O:N:') as `customer_name`,
        concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') as `indent_version`,
        concat(ifnull(`v`.`indented_by_name`, ''), ':Indented By:Y:T') as `indented_by_name`,
        concat(ifnull(`v`.`sr_no`, ''), ':Sr No:O:N:') as `sr_no`,
        concat(ifnull(`v`.`so_sr_no`, ''), ':So Sr No:O:N:') as `so_sr_no`,
        concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') as `product_type_short_name`,
        concat(ifnull(`v`.`product_type_group`, ''), ':Product Type Group:O:N:') as `product_type_group`,
        concat(ifnull(`v`.`product_material_print_name`, ''), ':Material Print Name:O:N:') as `product_material_print_name`,
        concat(ifnull(`v`.`product_material_tech_spect`, ''), ':Material Tech Spect:O:N:') as `product_material_tech_spect`,
        concat(ifnull(`v`.`product_packing_name`, ''), ':Product Packing Name:O:N:') as `product_packing_name`,
        concat(ifnull(`v`.`hsn_sac_type`, ''), ':Hsn Sac Type:O:N:') as `hsn_sac_type`,
        concat(ifnull(`v`.`hsn_sac_description`, ''), ':Hsn Sac Description:O:N:') as `hsn_sac_description`,
        concat(ifnull(`v`.`purchase_order_status`, ''), ':Purchase Order Status:O:N:') as `purchase_order_status`,
        concat(ifnull(`v`.`purchase_order_item_status`, ''), ':Purchase Order Item Status:O:N:') as `purchase_order_item_status`,
        concat(ifnull(`v`.`grn_item_status`, ''), ':GRN Item Status:O:N:') as `grn_item_status`,
        concat(ifnull(`v`.`purchase_order_mail_sent_status`, ''), ':Purchase Order Mail Sent Status:O:N:') as `purchase_order_mail_sent_status`,
        concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
        concat(ifnull(`v`.`city_name`, ''), ':City Name:O:N:') as `city_name`,
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
        concat(ifnull(`v`.`supplier_id`, ''), ':Supplier Id:N:N:') as `supplier_id`,
        concat(ifnull(`v`.`supplier_state_id`, ''), ':Supplier State Id:N:N:') as `supplier_state_id`,
        concat(ifnull(`v`.`supplier_city_id`, ''), ':Supplier City Id:N:N:') as `supplier_city_id`,
        concat(ifnull(`v`.`purchase_order_version`, ''), ':Purchase Order Version:O:N:') as `purchase_order_version`,
        concat(ifnull(`v`.`supplier_contacts_ids`, ''), ':Supplier Contacts Ids:N:N:') as `supplier_contacts_ids`,
        concat(ifnull(`v`.`purchase_order_type_id`, ''), ':Purchase Order Type Id:N:N:') as `purchase_order_type_id`,
        concat(ifnull(`v`.`purchase_order_master_transaction_id`, ''), ':Purchase Order Master Transaction Id:O:N:') as `purchase_order_master_transaction_id`,
        concat(ifnull(`v`.`purchase_order_details_transaction_id`, ''), ':Purchase Order Details Transaction_id:N:N:') as `purchase_order_details_transaction_id`,
        concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
        concat(ifnull(`v`.`product_material_unit_id`, ''), ':Product Material Unit Id:N:N:') as `product_material_unit_id`,
        concat(ifnull(`v`.`product_material_packing_id`, ''), ':Product Material Packing Id:N:N:') as `product_material_packing_id`,
        concat(ifnull(`v`.`product_material_hsn_code_id`, ''), ':Product Material Hsn Code Id:N:N:') as `product_material_hsn_code_id`,
        concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
        concat(ifnull(`v`.`indented_by_id`, ''), ':Indented By Id:N:N:') as `indented_by_id`,
        concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By Id:O:N:') as `approved_by_id`
    from
        `ptv_purchase_order_details` `v`
    limit 1;




-- ptv_goods_receipt_details source

create or REPLACE algorithm = UNDEFINED view `ptv_goods_receipt_details` as
select
    `pt`.`goods_receipt_no` as `goods_receipt_no`,
    `pt`.`goods_receipt_date` as `goods_receipt_date`,
    `pt`.`goods_receipt_version` as `goods_receipt_version`,
    case
        `pt`.`grn_item_status` when 'G' then 'GRN Done'
        when 'Q' then 'QC Done'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        when 'B' then 'Bill Booked'
        else 'GRN'
    end as `grn_item_status_desc`,
    `d`.`department_name` as `department_name`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
    `pt`.`purchase_order_date` as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    case
        `po`.`purchase_order_item_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        else 'Pending'
    end as `purchase_order_item_status_desc`,
    `cu`.`customer_name` as `customer_name`,
    `pt`.`customer_order_no` as `customer_order_no`,
    `pt`.`customer_order_Date` as `customer_order_Date`,
    `grm`.`purchase_order_life` as `purchase_order_life`,
    `grm`.`supplier_challan_no` as `supplier_challan_no`,
    `grm`.`supplier_challan_date` as `supplier_challan_date`,
    `sup`.`supp_branch_name` as `supplier_name`,
    `ct`.`city_name` as `supplier_city_name`,
    `ss`.`state_name` as `supplier_state_name`,
    `pt`.`item_qa_by_id` as `item_qa_by_id`,
    `pt`.`item_qa_date` as `item_qa_date`,
    `pt`.`goods_receipt_type` as `goods_receipt_type`,
    `pt`.`product_material_tech_spect` as `product_material_tech_spect`,
    `pt`.`lead_time` as `lead_time`,
    `pt`.`sr_no` as `sr_no`,
    `pt`.`batch_no` as `batch_no`,
    `po`.`purchase_order_item_status` as `purchase_order_item_status`,
    `rm_fg`.`product_material_code` as `product_material_code`,
    `rm_fg`.`product_material_name` as `product_material_name`,
    `u`.`product_unit_name` as `product_material_stock_unit_name`,
    `rm_fg`.`product_material_std_weight` as `product_material_std_weight`,
    `rm_fg`.`product_material_category1_name` as `product_category1_name`,
    `rm_fg`.`product_material_category2_name` as `product_category2_name`,
    `pk`.`product_packing_name` as `product_packing_name`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
    `pt`.`product_material_po_approved_quantity` as `product_material_po_approved_quantity`,
    `pt`.`product_material_po_approved_weight` as `product_material_po_approved_weight`,
    `pt`.`product_material_grn_quantity` as `product_material_grn_quantity`,
    `pt`.`product_material_grn_weight` as `product_material_grn_weight`,
    `pt`.`product_material_conversion_factor` as `product_material_conversion_factor`,
    `pt`.`product_material_grn_rejected_quantity` as `product_material_grn_rejected_quantity`,
    `pt`.`product_material_grn_rejected_weight` as `product_material_grn_rejected_weight`,
    `rp`.`product_rejection_type` as `product_rejection_type`,
    `rp`.`product_rejection_parameters_name` as `product_rejection_parameters_name`,
    `rp`.`product_rejection_parameters_short_name` as `product_rejection_parameters_short_name`,
    `pt`.`product_material_grn_accepted_quantity` as `product_material_grn_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` as `product_material_grn_accepted_weight`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) as `prev_grn_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) as `prev_grn_weight`,
    `so`.`sales_order_no` as `sales_order_no`,
    `pt`.`product_material_grn_accepted_quantity` as `product_material_prev_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` as `product_material_prev_accepted_weight`,
    `pt`.`po_material_rate` as `po_material_rate`,
    `fpo`.`currency_name` as `currency_name`,
    `pt`.`material_rate` as `material_rate`,
    `fgrn`.`currency_name` as `grn_currency_name`,
    `pt`.`material_basic_amount` as `material_basic_amount`,
    `pt`.`material_freight_amount` as `material_freight_amount`,
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
    `pt`.`grn_item_status` as `grn_item_status`,
    `pt`.`excess_quantity` as `excess_quantity`,
    `pt`.`excess_weight` as `excess_weight`,
    `pt`.`pree_closed_grn_quantity` as `pree_closed_grn_quantity`,
    `pt`.`pree_closed_grn_weight` as `pree_closed_grn_weight`,
    `pt`.`purchase_return_quantity` as `purchase_return_quantity`,
    `pt`.`purchase_return_weight` as `purchase_return_weight`,
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
    `pt`.`total_box_weight` as `total_box_weight`,
    `pt`.`total_quantity_in_box` as `total_quantity_in_box`,
    `pt`.`weight_per_box_item` as `weight_per_box_item`,
    `pt`.`no_of_boxes` as `no_of_boxes`,
    `pt`.`remark` as `remark`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `pt`.`financial_year` as `financial_year`,
    `pt`.`expiry_date` as `expiry_date`,
    `po`.`indent_no` as `indent_no`,
    `e1`.`employee_name` as `indented_by_name`,
    `e2`.`employee_name` as `approved_by_name`,
    `g`.`godown_name` as `godown_name`,
    `gs`.`godown_section_name` as `godown_section_name`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `pdt`.`product_type_group` as `product_material_type_group`,
    `pdt`.`product_type_name` as `product_type_name`,
    `pdt`.`product_type_short_name` as `product_type_short_name`,
    `sdp`.`department_name` as `sub_department_name`,
    `fpc`.`cost_center_name` as `cost_center_name`,
    `pt`.`currency_exchange_rate` as `currency_exchange_rate`,
    `fgrn`.`currency_code` as `grn_currency_code`,
    `fgrn`.`currency_symbol` as `currency_symbol`,
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
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`goods_receipt_details_transaction_id` as `goods_receipt_details_transaction_id`,
    `pt`.`goods_receipt_master_transaction_id` as `goods_receipt_master_transaction_id`,
    `pt`.`purchase_order_details_transaction_id` as `purchase_order_details_transaction_id`,
    `pt`.`department_id` as `department_id`,
    `pt`.`product_material_unit_id` as `product_material_unit_id`,
    `pt`.`product_material_packing_id` as `product_material_packing_id`,
    `pt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `pt`.`goods_receipt_type_id` as `goods_receipt_type_id`,
    `pt`.`customer_id` as `customer_id`,
    `pt`.`product_material_rejection_reason_id` as `product_material_rejection_reason_id`,
    `grm`.`goods_receipt_status` as `goods_receipt_status`,
    `grm`.`supplier_id` as `supplier_id`,
    `grm`.`approved_by_id` as `approved_by_id`,
    `grm`.`supplier_state_id` as `supplier_state_id`,
    `grm`.`supplier_city_id` as `supplier_city_id`,
    `grm`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `grm`.`expected_branch_id` as `expected_branch_id`,
    `grm`.`expected_branch_state_id` as `expected_branch_state_id`,
    `grm`.`expected_branch_city_id` as `expected_branch_city_id`,
    `sup`.`supp_branch_payment_term_id` as `payment_term_id`,
    `grm`.`agent_id` as `agent_id`,
    `pt`.`product_material_id` as `product_material_id`,
    `pt`.`godown_id` as `godown_id`,
    `pt`.`godown_section_id` as `godown_section_id`,
    `pt`.`godown_section_beans_id` as `godown_section_beans_id`,
    `po`.`purchase_order_type_id` as `product_type_id`,
    `rm_fg`.`product_material_category1_id` as `product_category1_id`,
    `rm_fg`.`product_material_category2_id` as `product_category2_id`,
    `po`.`indented_by_id` as `indent_by_id`,
    `pt`.`currency_id` as `currency_id`,
    `pt`.`grn_currency_id` as `grn_currency_id`
from
    (((((((((((((((((((((((((`pt_goods_receipt_details` `pt`
left join `cm_company` `c` on
    (`c`.`company_id` = `pt`.`company_id`
        and `pt`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`company_branch_id`
        and `cb`.`company_id` = `pt`.`company_id`
        and `pt`.`is_delete` = 0))
left join `pt_goods_receipt_master` `grm` on
    (`grm`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`
        and `grm`.`company_id` = `pt`.`company_id`
        and `grm`.`company_branch_id` = `pt`.`company_branch_id`))
left join `cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `grm`.`supplier_id`
        and `pt`.`is_delete` = 0))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `grm`.`supplier_city_id`))
left join `cm_state` `ss` on
    (`ss`.`state_id` = `grm`.`supplier_state_id`))
left join `pt_purchase_order_details` `po` on
    (`po`.`company_branch_id` = `pt`.`company_branch_id`
        and `po`.`company_id` = `pt`.`company_id`
        and `po`.`product_material_id` = `pt`.`product_material_id`
        and `po`.`purchase_order_no` = `pt`.`purchase_order_no`
        and `po`.`purchase_order_version` = `pt`.`purchase_order_version`
        and `po`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id`))
left join `sm_product_unit` `u` on
    (`u`.`product_unit_id` = `pt`.`product_material_unit_id`
        and `pt`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rm_fg` on
    (`rm_fg`.`product_material_id` = `pt`.`product_material_id`
        and `rm_fg`.`is_delete` = 0))
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `po`.`purchase_order_type_id`
        and `pdt`.`is_delete` = 0))
left join `sm_product_packing` `pk` on
    (`pk`.`product_packing_id` = `pt`.`product_material_packing_id`
        and `pk`.`is_delete` = 0))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`product_material_hsn_code_id`
        and `hsn`.`is_delete` = 0))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `po`.`indented_by_id`
        and `e1`.`is_delete` = 0))
left join `cm_employee` `e2` on
    (`e2`.`employee_id` = `grm`.`approved_by_id`
        and `e2`.`is_delete` = 0))
left join `cm_customer` `cu` on
    (`cu`.`customer_id` = `po`.`customer_id`
        and `cu`.`is_delete` = 0))
left join `cm_department` `d` on
    (`d`.`department_id` = `pt`.`department_id`
        and `d`.`is_delete` = 0))
left join `sm_product_rejection_parameters` `rp` on
    (`rp`.`company_id` = `pt`.`company_id`
        and `rp`.`product_rejection_parameters_id` = `pt`.`product_material_rejection_reason_id`))
left join `mt_sales_order_master_trading` `so` on
    (`so`.`company_id` = `pt`.`company_id`
        and `so`.`customer_order_no` = `pt`.`customer_order_no`
        and `so`.`is_delete` = 0))
left join `cm_godown` `g` on
    (`g`.`godown_id` = `pt`.`godown_id`))
left join `cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `pt`.`godown_section_id`))
left join `cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `pt`.`godown_section_beans_id`))
left join `cm_department` `sdp` on
    (`sdp`.`department_id` = `po`.`sub_department_id`))
left join `fm_cost_center` `fpc` on
    (`fpc`.`cost_center_id` = `po`.`cost_center_id`))
left join `fm_currency` `fpo` on
    (`fpo`.`currency_id` = `pt`.`currency_id`))
left join `fm_currency` `fgrn` on
    (`fgrn`.`currency_id` = `pt`.`grn_currency_id`))
where
    `pt`.`is_delete` = 0;



-- ptv_goods_receipt_details_rpt source

create or REPLACE algorithm = UNDEFINED view `ptv_goods_receipt_details_rpt` as
select
    concat(ifnull(`v`.`goods_receipt_no`, ''), ':Goods Receipt No:Y:T') as `goods_receipt_no`,
    concat(ifnull(`v`.`goods_receipt_date`, ''), ':Goods Receipt Date:Y:D:') as `goods_receipt_date`,
    concat(ifnull(`v`.`goods_receipt_version`, ''), ':Goods Receipt Version:O:N:') as `goods_receipt_version`,
    concat(ifnull(`v`.`grn_item_status`, ''), ':Grn Item Status:Y:H:(GRN,Done,Rejected,Partial Receipt,Completed,Canceled,Pree Closed)') as `grn_item_status_desc`,
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') as `purchase_order_no`,
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') as `purchase_order_date`,
    concat(ifnull(`v`.`purchase_order_item_status`, ''), ':Purchase Order Item Status:Y:H:(Approved,Rejected,Partial Receipt,Completed,Canceled,Pree Closed,Pending,Pree Closed)') as `purchase_order_item_status_desc`,
    concat(ifnull(`v`.`supplier_challan_no`, ''), ':Supplier Challan No:Y:C:ptv_goods_receipt_summary:O') as `supplier_challan_no`,
    concat(ifnull(`v`.`supplier_challan_date`, ''), ':Supplier Challan Date:Y:D:') as `supplier_challan_date`,
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') as `indent_no`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Material Name:Y:T') as `product_material_name`,
    concat(ifnull(`v`.`product_material_std_weight`, ''), ':Material Std Weight:O:N:') as `product_material_std_weight`,
    concat(ifnull(`v`.`product_material_stock_unit_name`, ''), ':Material Stock Unit Name:O:N:') as `product_material_stock_unit_name`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier:F') as `supplier_name`,
    concat(ifnull(`v`.`supplier_city_name`, ''), ':Supplier City Name:O:N:') as `supplier_city_name`,
    concat(ifnull(`v`.`supplier_state_name`, ''), ':Supplier State Name:Y:C:cmv_state:F') as `supplier_state_name`,
    concat(ifnull(`v`.`product_material_po_approved_quantity`, ''), ':Material Po Approved Quantity:O:N:') as `product_material_po_approved_quantity`,
    concat(ifnull(`v`.`product_material_po_approved_weight`, ''), ':Material Po Approved Weight:O:N:') as `product_material_po_approved_weight`,
    concat(ifnull(`v`.`product_material_grn_quantity`, ''), ':Material GRN Quantity:O:N:') as `product_material_grn_quantity`,
    concat(ifnull(`v`.`product_material_grn_weight`, ''), ':Material GRN Weight:O:N:') as `product_material_grn_weight`,
    concat(ifnull(`v`.`product_material_grn_accepted_quantity`, ''), ':Material GRN Accepted Quantity:O:N:') as `product_material_grn_accepted_quantity`,
    concat(ifnull(`v`.`product_material_grn_accepted_weight`, ''), ':Material GRN Accepted Weight:O:N:') as `product_material_grn_accepted_weight`,
    concat(ifnull(`v`.`po_material_rate`, ''), ':PO Material Rate:O:N:') as `po_material_rate`,
    concat(ifnull(`v`.`currency_name`, ''), ':PO Currency Name:O:N:') as `currency_name`,
    concat(ifnull(`v`.`material_rate`, ''), ':GRN Material Rate:O:N:') as `material_rate`,
    concat(ifnull(`v`.`grn_currency_name`, ''), ':GRN Currency Name:O:N:') as `grn_currency_name`,
    concat(ifnull(`v`.`material_basic_amount`, ''), ':Material Basic Amount:O:N:') as `material_basic_amount`,
    concat(ifnull(`v`.`material_discount_percent`, ''), ':Material Discount Percent:O:N:') as `material_discount_percent`,
    concat(ifnull(`v`.`material_discount_amount`, ''), ':Material Discount Amount:O:N:') as `material_discount_amount`,
    concat(ifnull(`v`.`material_taxable_amount`, ''), ':Material Taxable Amount:O:N:') as `material_taxable_amount`,
    concat(ifnull(`v`.`material_cgst_percent`, ''), ':Material Cgst Percent:O:N:') as `material_cgst_percent`,
    concat(ifnull(`v`.`material_cgst_total`, ''), ':Material Cgst Total:O:N:') as `material_cgst_total`,
    concat(ifnull(`v`.`material_sgst_percent`, ''), ':Material Sgst Percent:O:N:') as `material_sgst_percent`,
    concat(ifnull(`v`.`material_sgst_total`, ''), ':Material Sgst Total:O:N:') as `material_sgst_total`,
    concat(ifnull(`v`.`material_igst_percent`, ''), ':Material Igst Percent:O:N:') as `material_igst_percent`,
    concat(ifnull(`v`.`material_igst_total`, ''), ':Material Igst Total:O:N:') as `material_igst_total`,
    concat(ifnull(`v`.`material_total_amount`, ''), ':Material Total Amount:O:N:') as `material_total_amount`,
    concat(ifnull(`v`.`item_qa_by_id`, ''), ':Item QA By Id:O:N:') as `item_qa_by_id`,
    concat(ifnull(`v`.`item_qa_date`, ''), ':Item QA Date:Y:D:') as `item_qa_date`,
    concat(ifnull(`v`.`product_material_tech_spect`, ''), ':Product Material Tech Spect:O:N:') as `product_material_tech_spect`,
    concat(ifnull(`v`.`lead_time`, ''), ':Lead Time:O:N:') as `lead_time`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer:O') as `customer_name`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:O:N:') as `customer_order_no`,
    concat(ifnull(`v`.`customer_order_Date`, ''), ':Customer Order Date:Y:D:') as `customer_order_Date`,
    concat(ifnull(`v`.`material_schedule_date`, ''), ':Material Schedule Date:Y:D:') as `material_schedule_date`,
    concat(ifnull(`v`.`product_packing_name`, ''), ':Packing Name:O:N:') as `product_packing_name`,
    concat(ifnull(`v`.`product_category1_name`, ''), ':Material Category1 Name:O:N:') as `product_category1_name`,
    concat(ifnull(`v`.`product_category2_name`, ''), ':Material Category2 Name:O:N:') as `product_category2_name`,
    concat(ifnull(`v`.`hsn_sac_code`, ''), ':Material Hsn Sac Code:O:N:') as `hsn_sac_code`,
    concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Hsn Sac Rate:O:N:') as `hsn_sac_rate`,
    concat(ifnull(`v`.`prev_grn_quantity`, ''), ':Prev GRN Quantity:O:N:') as `prev_grn_quantity`,
    concat(ifnull(`v`.`prev_grn_weight`, ''), ':Prev GRN Weight:O:N:') as `prev_grn_weight`,
    concat(ifnull(`v`.`product_material_prev_accepted_quantity`, ''), ':Material Prev Accepted Quantity:O:N:') as `product_material_prev_accepted_quantity`,
    concat(ifnull(`v`.`product_material_prev_accepted_weight`, ''), ':Material Prev Accepted Weight:O:N:') as `product_material_prev_accepted_weight`,
    concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') as `excess_quantity`,
    concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') as `excess_weight`,
    concat(ifnull(`v`.`pree_closed_grn_quantity`, ''), ':Pree Closed Grn Quantity:O:N:') as `pree_closed_grn_quantity`,
    concat(ifnull(`v`.`pree_closed_grn_weight`, ''), ':Pree Closed Grn Weight:O:N:') as `pree_closed_grn_weight`,
    concat(ifnull(`v`.`purchase_return_quantity`, ''), ':Purchase Return Quantity:O:N:') as `purchase_return_quantity`,
    concat(ifnull(`v`.`purchase_return_weight`, ''), ':Purchase Return Weight:O:N:') as `purchase_return_weight`,
    concat(ifnull(`v`.`production_issue_quantity`, ''), ':Issue Quantity:O:N:') as `production_issue_quantity`,
    concat(ifnull(`v`.`production_issue_weight`, ''), ':Issue weight:O:N:') as `production_issue_weight`,
    concat(ifnull(`v`.`production_issue_return_quantity`, ''), ':Issue Return Quantity:O:N:') as `production_issue_return_quantity`,
    concat(ifnull(`v`.`production_issue_return_weight`, ''), ':Issue Return Weight:O:N:') as `production_issue_return_weight`,
    concat(ifnull(`v`.`production_issue_rejection_quantity`, ''), ':Issue Rejection Quantity:O:N:') as `production_issue_rejection_quantity`,
    concat(ifnull(`v`.`production_issue_rejection_weight`, ''), ':Issue Rejection Weight:O:N:') as `production_issue_rejection_weight`,
    concat(ifnull(`v`.`assembly_production_issue_quantity`, ''), ':Assembly Production Issue Quantity:O:N:') as `assembly_production_issue_quantity`,
    concat(ifnull(`v`.`assembly_production_issue_weight`, ''), ':Assembly Production Issue Weight:O:N:') as `assembly_production_issue_weight`,
    concat(ifnull(`v`.`purchase_order_life`, ''), ':Purchase Order Life:O:N:') as `purchase_order_life`,
    concat(ifnull(`v`.`sales_quantity`, ''), ':Sales Quantity:O:N:') as `sales_quantity`,
    concat(ifnull(`v`.`sales_weight`, ''), ':Sales Weight:O:N:') as `sales_weight`,
    concat(ifnull(`v`.`sales_return_quantity`, ''), ':Sales Return Quantity:O:N:') as `sales_return_quantity`,
    concat(ifnull(`v`.`sales_return_weight`, ''), ':Sales Return Weight:O:N:') as `sales_return_weight`,
    concat(ifnull(`v`.`sales_rejection_quantity`, ''), ':Sales Rejection Quantity:O:N:') as `sales_rejection_quantity`,
    concat(ifnull(`v`.`sales_rejection_weight`, ''), ':Sales Rejection Weight:O:N:') as `sales_rejection_weight`,
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
    concat(ifnull(`v`.`expiry_date`, ''), ':Expiry Date:Y:D:') as `expiry_date`,
    concat(ifnull(`v`.`remark`, ''), ':remark:O:N:') as `remark`,
    concat(ifnull(`v`.`grn_item_status`, ''), ':Grn Item Status:O:N:') as `grn_item_status`,
    concat(ifnull(`v`.`purchase_order_item_status`, ''), ':Purchase Order Item Status:O:N:') as `purchase_order_item_status`,
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
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
    concat(ifnull(`v`.`goods_receipt_details_transaction_id`, ''), 'goods_receipt_details_transaction_id:N:N:') as `goods_receipt_details_transaction_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Material Unit Id:N:N:') as `product_material_unit_id`,
    concat(ifnull(`v`.`product_material_packing_id`, ''), ':Product Material Packing_id:N:N:') as `product_material_packing_id`,
    concat(ifnull(`v`.`product_material_hsn_code_id`, ''), ':Product Material Hsn Code_id:N:N:') as `product_material_hsn_code_id`,
    concat(ifnull(`v`.`goods_receipt_type_id`, ''), ':Goods Receipt Type Id:N:N:') as `goods_receipt_type_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`supplier_id`, ''), ':Supplier Id:N:N:') as `supplier_id`,
    concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By:N:N:') as `approved_by_id`,
    concat(ifnull(`v`.`supplier_state_id`, ''), ':Supplier State Id:N:N:') as `supplier_state_id`,
    concat(ifnull(`v`.`supplier_city_id`, ''), ':Supplier City Id:N:N:') as `supplier_city_id`,
    concat(ifnull(`v`.`supplier_contacts_ids`, ''), ':Supplier Contacts Ids:N:N:') as `supplier_contacts_ids`,
    concat(ifnull(`v`.`expected_branch_id`, ''), ':Expected Branch Id:N:N:') as `expected_branch_id`,
    concat(ifnull(`v`.`expected_branch_state_id`, ''), ':Expected Branch State Id:N:N:') as `expected_branch_state_id`,
    concat(ifnull(`v`.`expected_branch_city_id`, ''), ':Expected Branch City Id:N:N:') as `expected_branch_city_id`,
    concat(ifnull(`v`.`payment_term_id`, ''), ':Payment Term Id:N:N:') as `payment_term_id`,
    concat(ifnull(`v`.`agent_id`, ''), ':Agent Id:N:N:') as `agent_id`,
    concat(ifnull(`v`.`product_category1_id`, ''), ':Product Category1 Id:N:N:') as `product_category1_id`,
    concat(ifnull(`v`.`product_category2_id`, ''), ':Product Category2 Id:N:N:') as `product_category2_id`,
    concat(ifnull(`v`.`indent_by_id`, ''), ':Indent By Id:N:N:') as `indent_by_id`
from
    `ptv_goods_receipt_details` `v`
limit 1;



    







