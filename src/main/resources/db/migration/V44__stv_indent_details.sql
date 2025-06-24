
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
	coalesce((select sum(`ptdetails`.`product_material_po_approved_quantity`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `pt`.`indent_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id`  and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id` and `pt`.`indent_no` != ''), 0) as `prev_po_quantity`,
	coalesce((select sum(`ptdetails`.`product_material_po_approved_weight`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `pt`.`indent_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id`  and `ptdetails`.`is_active` = 1  and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id` and `pt`.`indent_no` != ''), 0) as `prev_po_weight`,
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


	--



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
        when 'O' then 'Purchse Order Genreated'
        when 'IPO' then 'Partial PO Genreated'
        when 'POA' then 'Purchse Order Approved'
        when 'G' then 'Goods Receipts'
        when 'IG' then 'Partial Goods Receipts'
        when 'I' then 'Partial Issue'
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
    `child`.`product_material_grade_id` as `product_material_grade_id`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_quantity`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `st`.`indent_no` and `ptdetails`.`product_material_id` = `st`.`product_material_id`  and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `st`.`company_id` ), 0) as `prev_po_quantity`,
	coalesce((select sum(`ptdetails`.`product_material_po_approved_weight`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `st`.`indent_no` and `ptdetails`.`product_material_id` = `st`.`product_material_id`  and `ptdetails`.`is_active` = 1  and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `prev_po_weight`
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