-- ptv_purchase_order_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `ptv_purchase_order_details` AS
select
    `pt`.`purchase_order_type` AS `purchase_order_type`,
    `pt`.`purchase_order_no` AS `purchase_order_no`,
    `pt`.`purchase_order_date` AS `purchase_order_date`,
    `pt`.`purchase_order_version` AS `purchase_order_version`,
    `pt`.`indent_no` AS `indent_no`,
    `pt`.`indent_date` AS `indent_date`,
    `pt`.`indent_version` AS `indent_version`,
    case
        `pom`.`purchase_order_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'X' then 'Canceled'
        else 'Pending'
    end AS `purchase_order_status_desc`,
    `vp`.`department_name` AS `department_name`,
    `sdp`.`department_name` AS `sub_department_name`,
    case
        `pt`.`purchase_order_item_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        when 'B' then 'Bill Booked'
        else 'Pending'
    end AS `purchase_order_item_status_desc`,
    case
        `pom`.`purchase_order_mail_sent_status` when 'S' then 'Email Sent'
        when 'F' then 'Email Failed'
        else 'Email Pending'
    end AS `purchase_order_mail_sent_status_desc`,
    `pom`.`supplier_name` AS `supplier_name`,
    `pom`.`state_name` AS `supplier_state_name`,
    `c`.`customer_name` AS `customer_name`,
    `e1`.`employee_name` AS `indented_by_name`,
    `e`.`employee_name` AS `approved_by_name`,
    `pt`.`sr_no` AS `sr_no`,
    `pt`.`so_sr_no` AS `so_sr_no`,
    `p`.`product_type_name` AS `product_type_name`,
    `p`.`product_type_short_name` AS `product_type_short_name`,
    `p`.`product_type_group` AS `product_type_group`,
    `rmfgsr`.`product_material_code` AS `product_material_code`,
    `rmfgsr`.`product_material_name` AS `product_material_name`,
    `pt`.`product_material_print_name` AS `product_material_print_name`,
    `pt`.`product_material_tech_spect` AS `product_material_tech_spect`,
    `pt`.`lead_time` AS `lead_time`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id` and `ptdetails`.`company_id` = `pt`.`company_id`), 0) AS `available_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id` and `ptdetails`.`company_id` = `pt`.`company_id`), 0) AS `available_stock_weight`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id`), 0) AS `available_rawmaterial_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id`), 0) AS `available_rawmaterial_stock_weight`,
    `rmfgsr`.`product_material_std_weight` AS `product_material_std_weight`,
    `rmfgsr`.`product_material_stock_unit_name` AS `product_material_stock_unit_name`,
    `pp`.`product_packing_name` AS `product_packing_name`,
    `pp`.`quantity_per_packing` AS `quantity_per_packing`,
    `hsn`.`hsn_sac_type` AS `hsn_sac_type`,
    `hsn`.`hsn_sac_code` AS `hsn_sac_code`,
    `hsn`.`hsn_sac_description` AS `hsn_sac_description`,
    `hsn`.`hsn_sac_rate` AS `hsn_sac_rate`,
    `rmfgsr`.`product_type_group` AS `product_material_type_group`,
    `rmfgsr`.`product_material_make_name` AS `product_make_name`,
    `rmfgsr`.`product_material_category1_name` AS `product_category1_name`,
    `rmfgsr`.`product_material_category2_name` AS `product_category2_name`,
    `rmfgsr`.`product_material_category3_name` AS `product_category3_name`,
    `rmfgsr`.`product_material_category4_name` AS `product_category4_name`,
    `rmfgsr`.`product_material_category5_name` AS `product_category5_name`,
    `rmfgsr`.`product_material_type_name` AS `product_material_type_name`,
    `rmfgsr`.`product_material_grade_name` AS `product_material_grade_name`,
    `rmfgsr`.`product_material_shape_name` AS `product_material_shape_name`,
    `rmfgsr`.`product_material_oem_part_code` AS `product_material_oem_part_code`,
    `rmfgsr`.`product_material_our_part_code` AS `product_material_our_part_code`,
    `rmfgsr`.`product_material_drawing_no` AS `product_material_drawing_no`,
    `rmfgsr`.`product_material_hsn_sac_code` AS `product_material_hsn_sac_code`,
    `rmfgsr`.`godown_name` AS `godown_name`,
    `rmfgsr`.`godown_short_name` AS `godown_short_name`,
    `rmfgsr`.`godown_area` AS `godown_area`,
    `rmfgsr`.`godown_section_name` AS `godown_section_name`,
    `rmfgsr`.`godown_section_short_name` AS `godown_section_short_name`,
    `rmfgsr`.`godown_section_area` AS `godown_section_area`,
    `rmfgsr`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `rmfgsr`.`godown_section_beans_short_name` AS `godown_section_beans_short_name`,
    `rmfgsr`.`godown_section_beans_area` AS `godown_section_beans_area`,
    `pt`.`product_material_po_quantity_qunital` AS `product_material_po_quantity_qunital`,
    `pt`.`product_material_po_quantity` AS `product_material_po_quantity`,
    `pt`.`product_material_conversion_factor` AS `product_material_conversion_factor`,
    `pt`.`product_material_po_weight` AS `product_material_po_weight`,
    `pt`.`product_material_po_approved_quantity` AS `product_material_po_approved_quantity`,
    `pt`.`product_material_po_approved_weight` AS `product_material_po_approved_weight`,
    `pt`.`product_material_po_rejected_quantity` AS `product_material_po_rejected_quantity`,
    `pt`.`product_material_po_rejected_weight` AS `product_material_po_rejected_weight`,
    `pt`.`customer_id` AS `customer_id`,
    `pt`.`customer_order_no` AS `customer_order_no`,
    `pt`.`customer_order_Date` AS `customer_order_Date`,
    `pt`.`pree_closed_quantity` AS `pree_closed_quantity`,
    `pt`.`pree_closed_weight` AS `pree_closed_weight`,
    `pt`.`purchase_return_quantity` AS `purchase_return_quantity`,
    `pt`.`purchase_return_weight` AS `purchase_return_weight`,
    `pt`.`material_rate` AS `material_rate`,
    `pt`.`material_basic_amount` AS `material_basic_amount`,
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
    `pt`.`material_freight_amount` AS `material_freight_amount`,
    `pt`.`pending_quantity` AS `pending_quantity`,
    `pt`.`pending_weight` AS `pending_weight`,
    `pt`.`excess_quantity` AS `excess_quantity`,
    `pt`.`excess_weight` AS `excess_weight`,
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
    `pt`.`transfer_issue_quantity` AS `transfer_issue_quantity`,
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
    `pt`.`material_po_approval_remark` AS `material_po_approval_remark`,
    `pom`.`purchase_order_status` AS `purchase_order_status`,
    `pt`.`purchase_order_item_status` AS `purchase_order_item_status`,
    `pt`.`grn_item_status` AS `grn_item_status`,
    `pom`.`purchase_order_mail_sent_status` AS `purchase_order_mail_sent_status`,
    `pt`.`approved_date` AS `approved_date`,
    `pt`.`remark` AS `remark`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `v`.`company_cell_no` AS `company_cell_no`,
    `v`.`company_phone_no` AS `company_phone_no`,
    `v`.`company_EmailId` AS `company_EmailId`,
    `v`.`company_website` AS `company_website`,
    `v`.`company_gst_no` AS `company_gst_no`,
    `v`.`company_pan_no` AS `company_pan_no`,
    `v`.`company_state` AS `company_state`,
    `v`.`company_pincode` AS `company_pincode`,
    concat(`v`.`company_address1`, `v`.`company_address2`) AS `company_address1`,
    `pt`.`financial_year` AS `financial_year`,
    `pom`.`city_name` AS `city_name`,
    `pom`.`district_name` AS `district_name`,
    `pom`.`country_name` AS `country_name`,
    case
        `pt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `pt`.`is_delete` when 1 then 'Yes'
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
    `pt`.`purchase_order_type_id` AS `purchase_order_type_id`,
    `pt`.`purchase_order_master_transaction_id` AS `purchase_order_master_transaction_id`,
    `pt`.`purchase_order_details_transaction_id` AS `purchase_order_details_transaction_id`,
    `pom`.`supplier_id` AS `supplier_id`,
    `pom`.`supplier_state_id` AS `supplier_state_id`,
    `pom`.`supplier_city_id` AS `supplier_city_id`,
    `pom`.`supplier_contacts_ids` AS `supplier_contacts_ids`,
    `pom`.`expected_branch_id` AS `expected_branch_id`,
    `pom`.`expected_branch_state_id` AS `expected_branch_state_id`,
    `pom`.`expected_branch_city_id` AS `expected_branch_city_id`,
    `pt`.`cost_center_id` AS `cost_center_id`,
    `rmfgsr`.`product_material_type_id` AS `product_material_type_id`,
    `rmfgsr`.`product_material_grade_id` AS `product_material_grade_id`,
    `rmfgsr`.`product_material_shape_id` AS `product_material_shape_id`,
    `rmfgsr`.`product_material_stock_unit_id` AS `product_material_stock_unit_id`,
    `rmfgsr`.`product_material_category1_id` AS `product_category1_id`,
    `rmfgsr`.`product_material_category2_id` AS `product_category2_id`,
    `rmfgsr`.`product_material_category3_id` AS `product_category3_id`,
    `rmfgsr`.`product_material_category4_id` AS `product_category4_id`,
    `rmfgsr`.`product_material_category5_id` AS `product_category5_id`,
    `rmfgsr`.`godown_id` AS `godown_id`,
    `rmfgsr`.`godown_section_id` AS `godown_section_id`,
    `rmfgsr`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `pt`.`product_material_id` AS `product_material_id`,
    `pt`.`product_material_unit_id` AS `product_material_unit_id`,
    `pt`.`product_material_packing_id` AS `product_material_packing_id`,
    `pt`.`product_material_hsn_code_id` AS `product_material_hsn_code_id`,
    `pt`.`department_id` AS `department_id`,
    `pt`.`sub_department_id` AS `sub_department_id`,
    `pt`.`indented_by_id` AS `indented_by_id`,
    `pt`.`approved_by_id` AS `approved_by_id`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id`), 0) AS `prev_grn_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id`), 0) AS `prev_grn_weight`
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