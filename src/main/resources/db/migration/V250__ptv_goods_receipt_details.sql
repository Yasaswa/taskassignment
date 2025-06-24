-- ptv_goods_receipt_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `ptv_goods_receipt_details` AS
select
    `pt`.`goods_receipt_no` AS `goods_receipt_no`,
    `pt`.`goods_receipt_date` AS `goods_receipt_date`,
    `pt`.`goods_receipt_version` AS `goods_receipt_version`,
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
    end AS `grn_item_status_desc`,
    `d`.`department_name` AS `department_name`,
    `pt`.`purchase_order_no` AS `purchase_order_no`,
    `pt`.`purchase_order_date` AS `purchase_order_date`,
    `pt`.`purchase_order_version` AS `purchase_order_version`,
    case
        `po`.`purchase_order_item_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        else 'Pending'
    end AS `purchase_order_item_status_desc`,
    `cu`.`customer_name` AS `customer_name`,
    `pt`.`customer_order_no` AS `customer_order_no`,
    `pt`.`customer_order_Date` AS `customer_order_Date`,
    `grm`.`purchase_order_life` AS `purchase_order_life`,
    `grm`.`supplier_challan_no` AS `supplier_challan_no`,
    `grm`.`supplier_challan_date` AS `supplier_challan_date`,
    `sup`.`supp_branch_name` AS `supplier_name`,
    `ct`.`city_name` AS `supplier_city_name`,
    `ss`.`state_name` AS `supplier_state_name`,
    `pt`.`item_qa_by_id` AS `item_qa_by_id`,
    `pt`.`item_qa_date` AS `item_qa_date`,
    `pt`.`goods_receipt_type` AS `goods_receipt_type`,
    `pt`.`product_material_tech_spect` AS `product_material_tech_spect`,
    `pt`.`lead_time` AS `lead_time`,
    `pt`.`sr_no` AS `sr_no`,
    `pt`.`batch_no` AS `batch_no`,
    `po`.`purchase_order_item_status` AS `purchase_order_item_status`,
    `rm_fg`.`product_material_name` AS `product_material_name`,
    `u`.`product_unit_name` AS `product_material_stock_unit_name`,
    `rm_fg`.`product_material_std_weight` AS `product_material_std_weight`,
    `rm_fg`.`product_material_category1_name` AS `product_category1_name`,
    `rm_fg`.`product_material_category2_name` AS `product_category2_name`,
    `pk`.`product_packing_name` AS `product_packing_name`,
    `hsn`.`hsn_sac_code` AS `hsn_sac_code`,
    `hsn`.`hsn_sac_rate` AS `hsn_sac_rate`,
    `pt`.`product_material_po_approved_quantity` AS `product_material_po_approved_quantity`,
    `pt`.`product_material_po_approved_weight` AS `product_material_po_approved_weight`,
    `pt`.`product_material_grn_quantity` AS `product_material_grn_quantity`,
    `pt`.`product_material_grn_weight` AS `product_material_grn_weight`,
    `pt`.`product_material_conversion_factor` AS `product_material_conversion_factor`,
    `pt`.`product_material_grn_rejected_quantity` AS `product_material_grn_rejected_quantity`,
    `pt`.`product_material_grn_rejected_weight` AS `product_material_grn_rejected_weight`,
    `rp`.`product_rejection_type` AS `product_rejection_type`,
    `rp`.`product_rejection_parameters_name` AS `product_rejection_parameters_name`,
    `rp`.`product_rejection_parameters_short_name` AS `product_rejection_parameters_short_name`,
    `pt`.`product_material_grn_accepted_quantity` AS `product_material_grn_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` AS `product_material_grn_accepted_weight`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) AS `prev_grn_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) AS `prev_grn_weight`,
    `so`.`sales_order_no` AS `sales_order_no`,
    `pt`.`product_material_grn_accepted_quantity` AS `product_material_prev_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` AS `product_material_prev_accepted_weight`,
    `pt`.`material_rate` AS `material_rate`,
    `pt`.`material_basic_amount` AS `material_basic_amount`,
    `pt`.`material_freight_amount` AS `material_freight_amount`,
    `pt`.`material_discount_percent` AS `material_discount_percent`,
    `pt`.`material_discount_amount` AS `material_discount_amount`,
    `pt`.`material_taxable_amount` AS `material_taxable_amount`,
    `pt`.`material_cgst_percent` AS `material_cgst_percent`,
    `pt`.`material_cgst_total` AS `material_cgst_total`,
    `pt`.`material_sgst_percent` AS `material_sgst_percent`,
    `pt`.`material_sgst_total` AS `material_sgst_total`,
    `pt`.`material_igst_percent` AS `material_igst_percent`,
    `pt`.`material_igst_total` AS `material_igst_total`,
    `pt`.`material_total_amount` AS `material_total_amount`,
    `pt`.`material_schedule_date` AS `material_schedule_date`,
    `pt`.`grn_item_status` AS `grn_item_status`,
    `pt`.`excess_quantity` AS `excess_quantity`,
    `pt`.`excess_weight` AS `excess_weight`,
    `pt`.`pree_closed_grn_quantity` AS `pree_closed_grn_quantity`,
    `pt`.`pree_closed_grn_weight` AS `pree_closed_grn_weight`,
    `pt`.`purchase_return_quantity` AS `purchase_return_quantity`,
    `pt`.`purchase_return_weight` AS `purchase_return_weight`,
    `pt`.`production_issue_quantity` AS `production_issue_quantity`,
    `pt`.`production_issue_weight` AS `production_issue_weight`,
    `pt`.`production_issue_return_quantity` AS `production_issue_return_quantity`,
    `pt`.`production_issue_return_weight` AS `production_issue_return_weight`,
    `pt`.`production_issue_rejection_quantity` AS `production_issue_rejection_quantity`,
    `pt`.`production_issue_rejection_weight` AS `production_issue_rejection_weight`,
    `pt`.`assembly_production_issue_quantity` AS `assembly_production_issue_quantity`,
    `pt`.`assembly_production_issue_weight` AS `assembly_production_issue_weight`,
    `pt`.`sales_quantity` AS `sales_quantity`,
    `pt`.`sales_weight` AS `sales_weight`,
    `pt`.`sales_return_quantity` AS `sales_return_quantity`,
    `pt`.`sales_return_weight` AS `sales_return_weight`,
    `pt`.`sales_rejection_quantity` AS `sales_rejection_quantity`,
    `pt`.`sales_rejection_weight` AS `sales_rejection_weight`,
    `pt`.`transfer_issue_weight` AS `transfer_issue_weight`,
    `pt`.`transfer_receipt_quantity` AS `transfer_receipt_quantity`,
    `pt`.`transfer_receipt_weight` AS `transfer_receipt_weight`,
    `pt`.`outsources_out_quantity` AS `outsources_out_quantity`,
    `pt`.`outsources_out_weight` AS `outsources_out_weight`,
    `pt`.`outsources_in_quantity` AS `outsources_in_quantity`,
    `pt`.`outsources_in_weight` AS `outsources_in_weight`,
    `pt`.`outsources_rejection_quantity` AS `outsources_rejection_quantity`,
    `pt`.`outsources_rejection_weight` AS `outsources_rejection_weight`,
    `pt`.`loan_receipt_quantity` AS `loan_receipt_quantity`,
    `pt`.`loan_receipt_weight` AS `loan_receipt_weight`,
    `pt`.`loan_issue_quantity` AS `loan_issue_quantity`,
    `pt`.`loan_issue_weight` AS `loan_issue_weight`,
    `pt`.`cancel_quantity` AS `cancel_quantity`,
    `pt`.`cancel_weight` AS `cancel_weight`,
    `pt`.`difference_quantity` AS `difference_quantity`,
    `pt`.`difference_weight` AS `difference_weight`,
    `pt`.`total_box_weight` AS `total_box_weight`,
    `pt`.`total_quantity_in_box` AS `total_quantity_in_box`,
    `pt`.`weight_per_box_item` AS `weight_per_box_item`,
    `pt`.`no_of_boxes` AS `no_of_boxes`,
    `pt`.`remark` AS `remark`,
    `c`.`company_legal_name` AS `company_name`,
    `cb`.`company_branch_name` AS `company_branch_name`,
    `pt`.`financial_year` AS `financial_year`,
    `pt`.`expiry_date` AS `expiry_date`,
    `po`.`indent_no` AS `indent_no`,
    `e1`.`employee_name` AS `indented_by_name`,
    `e2`.`employee_name` AS `approved_by_name`,
    `g`.`godown_name` AS `godown_name`,
    `gs`.`godown_section_name` AS `godown_section_name`,
    `gsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `pdt`.`product_type_group` AS `product_material_type_group`,
    `pdt`.`product_type_name` AS `product_type_name`,
    `pdt`.`product_type_short_name` AS `product_type_short_name`,
    `sdp`.`department_name` AS `sub_department_name`,
    `fpc`.`cost_center_name` AS `cost_center_name`,
    case
        when `pt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `pt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `pt`.`is_active` AS `is_active`,
    `pt`.`is_delete` AS `is_delete`,
    `pt`.`created_by` AS `created_by`,
    `pt`.`created_on` AS `created_on`,
    `pt`.`modified_by` AS `modified_by`,
    `pt`.`modified_on` AS `modified_on`,
    `pt`.`deleted_by` AS `deleted_by`,
    `pt`.`deleted_on` AS `deleted_on`,
    `pt`.`company_id` AS `company_id`,
    `pt`.`company_branch_id` AS `company_branch_id`,
    `pt`.`goods_receipt_details_transaction_id` AS `goods_receipt_details_transaction_id`,
    `pt`.`goods_receipt_master_transaction_id` AS `goods_receipt_master_transaction_id`,
    `pt`.`purchase_order_details_transaction_id` AS `purchase_order_details_transaction_id`,
    `pt`.`department_id` AS `department_id`,
    `pt`.`product_material_unit_id` AS `product_material_unit_id`,
    `pt`.`product_material_packing_id` AS `product_material_packing_id`,
    `pt`.`product_material_hsn_code_id` AS `product_material_hsn_code_id`,
    `pt`.`goods_receipt_type_id` AS `goods_receipt_type_id`,
    `pt`.`customer_id` AS `customer_id`,
    `pt`.`product_material_rejection_reason_id` AS `product_material_rejection_reason_id`,
    `grm`.`goods_receipt_status` AS `goods_receipt_status`,
    `grm`.`supplier_id` AS `supplier_id`,
    `grm`.`approved_by_id` AS `approved_by_id`,
    `grm`.`supplier_state_id` AS `supplier_state_id`,
    `grm`.`supplier_city_id` AS `supplier_city_id`,
    `grm`.`supplier_contacts_ids` AS `supplier_contacts_ids`,
    `grm`.`expected_branch_id` AS `expected_branch_id`,
    `grm`.`expected_branch_state_id` AS `expected_branch_state_id`,
    `grm`.`expected_branch_city_id` AS `expected_branch_city_id`,
    `sup`.`supp_branch_payment_term_id` AS `payment_term_id`,
    `grm`.`agent_id` AS `agent_id`,
    `pt`.`product_material_id` AS `product_material_id`,
    `pt`.`godown_id` AS `godown_id`,
    `pt`.`godown_section_id` AS `godown_section_id`,
    `pt`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `po`.`purchase_order_type_id` AS `product_type_id`,
    `rm_fg`.`product_material_category1_id` AS `product_category1_id`,
    `rm_fg`.`product_material_category2_id` AS `product_category2_id`,
    `po`.`indented_by_id` AS `indent_by_id`
from
    (((((((((((((((((((((((`pt_goods_receipt_details` `pt`
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
where
    `pt`.`is_delete` = 0;