-- 22-08-2024 --

-- add quotation_no & quotation_date in PO master table --

ALTER TABLE pt_purchase_order_master ADD quotation_no varchar(50) NULL COMMENT 'Quotation No./Reference PI No. data entry field';
ALTER TABLE pt_purchase_order_master CHANGE quotation_no quotation_no varchar(50) NULL COMMENT 'Quotation No./Reference PI No. data entry field' AFTER consignee_city_id;
ALTER TABLE pt_purchase_order_master ADD quotation_date DATE NULL COMMENT 'Quotation Date data entry';
ALTER TABLE pt_purchase_order_master CHANGE quotation_date quotation_date DATE NULL COMMENT 'Quotation Date data entry' AFTER quotation_no;



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
        when 'O' then 'Purchse Order Genreated'
        when 'IPO' then 'Partial PO Genreated'
        when 'POA' then 'Purchse Order Approved'
        when 'G' then 'Goods Receipts'
        when 'IG' then 'Partial Goods Receipts'
        when 'I' then 'Partial Issue'
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
        when `pt`.`purchase_order_status` = 'I' then 'Partial Receipt'
        when `pt`.`purchase_order_status` = 'C' then 'Completed'
        when `pt`.`purchase_order_status` = 'X' then 'Canceled'
        when `pt`.`purchase_order_status` = 'Z' then 'Pree Closed'
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
    `pt`.`quotation_no` as `quotation_no`,
    `pt`.`quotation_date` as `quotation_date`,
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

   -- ptv_purchase_order_master_summary_rpt source

create or replace
algorithm = UNDEFINED view `ptv_purchase_order_master_summary_rpt` as
select
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:C:ptv_purchase_order_master_summary:O') as `purchase_order_no`,
    concat(ifnull(`v`.`purchase_order_version`, ''), ':Purchase Order Version:O:N:') as `purchase_order_version`,
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') as `purchase_order_date`,
    concat(ifnull(`v`.`purchase_order_status`, ''), ':Purchase Order Status:Y:H:(Approved,Rejected,Partial Receipt,Completed,Canceled,Pree Closed,Pending)') as `purchase_order_status_desc`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier_summary:F') as `supplier_name`,
    concat(ifnull(`v`.`basic_total`, ''), ':Basic Total:O:N:') as `basic_total`,
    concat(ifnull(`v`.`transport_amount`, ''), ':Transport Amount:O:N:') as `transport_amount`,
    concat(ifnull(`v`.`freight_amount`, ''), ':Freight Amount:O:N:') as `freight_amount`,
    concat(ifnull(`v`.`is_freight_taxable`, ''), ':Is Freight Taxable:O:N:') as `is_freight_taxable`,
    concat(ifnull(`v`.`packing_amount`, ''), ':Packing Amount:O:N:') as `packing_amount`,
    concat(ifnull(`v`.`po_discount_percent`, ''), ':PO Discount Percent:O:N:') as `po_discount_percent`,
    concat(ifnull(`v`.`po_discount_amount`, ''), ':PO Discount Amount:O:N:') as `po_discount_amount`,
    concat(ifnull(`v`.`other_amount`, ''), ':Other Amount:O:N:') as `other_amount`,
    concat(ifnull(`v`.`taxable_total`, ''), ':Taxable Total:O:N:') as `taxable_total`,
    concat(ifnull(`v`.`cgst_total`, ''), ':CGST Total:O:N:') as `cgst_total`,
    concat(ifnull(`v`.`sgst_total`, ''), ':SGST Total:O:N:') as `sgst_total`,
    concat(ifnull(`v`.`igst_total`, ''), ':IGST Total:O:N:') as `igst_total`,
    concat(ifnull(`v`.`roundoff`, ''), ':RoundOff:O:N:') as `roundoff`,
    concat(ifnull(`v`.`grand_total`, ''), ':Grand Total:O:N:') as `grand_total`,
    concat(ifnull(`v`.`quotation_no`, ''), ':Quotation No:Y:T') as `quotation_no`,
    concat(ifnull(`v`.`quotation_date`, ''), ':Quotation Date:Y:D:') as `quotation_date`,
    concat(ifnull(`v`.`approved_by`, ''), ':Approved By:Y:C:cmv_employee_list:F') as `approved_by`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') as `expected_schedule_date`,
    concat(ifnull(`v`.`purchase_order_acceptance_status`, ''), ':Purchase Order Acceptance Status:Y:H:(Approved,Canceled,Pending)') as `purchase_order_acceptance_status_desc`,
    concat(ifnull(`v`.`purchase_process_entry_desc`, ''), ':Purchase Process Entry:Y:H:(Manual,Open)') as `purchase_process_entry_desc`,
    concat(ifnull(`v`.`purchase_order_mail_sent_status`, ''), ':Mail Sent Status:Y:H:(Email Sent,Email Failed,Email Pending)') as `purchase_order_mail_sent_status_desc`,
    concat(ifnull(`v`.`purchase_order_creation_type_desc`, ''), ':Purchase Order Creation Type:Y:H:(Manual,Auto)') as `purchase_order_creation_type_desc`,
    concat(ifnull(`v`.`purchase_order_life_desc`, ''), ':Purchase Order Life:Y:H:(Close,Open)') as `purchase_order_life_desc`,
    concat(ifnull(`v`.`status_remark`, ''), ':Status Remark:O:N:') as `status_remark`,
    concat(ifnull(`v`.`supplier_code`, ''), ':Supplier Code:O:N:') as `supplier_code`,
    concat(ifnull(`v`.`supplier_type`, ''), ':Supplier Type:O:N:') as `supplier_type`,
    concat(ifnull(`v`.`supplier_short_name`, ''), ':Supplier Short Name:O:N:') as `supplier_short_name`,
    concat(ifnull(`v`.`supp_branch_address1`, ''), ':Branch Address1:O:N:') as `supp_branch_address1`,
    concat(ifnull(`v`.`supp_branch_pincode`, ''), ':Branch Pincode:O:N:') as `supp_branch_pincode`,
    concat(ifnull(`v`.`city_name`, ''), ':City Name:O:N:') as `city_name`,
    concat(ifnull(`v`.`district_name`, ''), ':District Name:O:N:') as `district_name`,
    concat(ifnull(`v`.`state_name`, ''), ':State Name:O:N:') as `state_name`,
    concat(ifnull(`v`.`supp_branch_phone_no`, ''), ':Branch Phone No:O:N:') as `supp_branch_phone_no`,
    concat(ifnull(`v`.`supp_branch_EmailId`, ''), ':Branch EmailId:O:N:') as `supp_branch_EmailId`,
    concat(ifnull(`v`.`expected_branch_name`, ''), ':Branch Name:O:N:') as `expected_branch_name`,
    concat(ifnull(`v`.`expected_branch_short_name`, ''), ':Branch Short Name:O:N:') as `expected_branch_short_name`,
    concat(ifnull(`v`.`expected_branch_address1`, ''), ':Branch Address1:O:N:') as `expected_branch_address1`,
    concat(ifnull(`v`.`expected_branch_pincode`, ''), ':Branch Pincode:O:N:') as `expected_branch_pincode`,
    concat(ifnull(`v`.`expected_branch_city_name`, ''), ':Branch City Name:O:N:') as `expected_branch_city_name`,
    concat(ifnull(`v`.`expected_branch_district_name`, ''), ':Branch District Name:O:N:') as `expected_branch_district_name`,
    concat(ifnull(`v`.`expected_branch_state_name`, ''), ':Branch State Name:O:N:') as `expected_branch_state_name`,
    concat(ifnull(`v`.`expected_branch_phone_no`, ''), ':Branch Phone No:O:N:') as `expected_branch_phone_no`,
    concat(ifnull(`v`.`expected_branch_EmailId`, ''), ':Expected Branch EmailId:O:N:') as `expected_branch_EmailId`,
    concat(ifnull(`v`.`agent_name`, ''), ':Agent Name:Y:C:cmv_agent:F') as `agent_name`,
    concat(ifnull(`v`.`agent_percent`, ''), ':Agent Percent:O:N:') as `agent_percent`,
    concat(ifnull(`v`.`agent_paid_status`, ''), ':Agent Paid Status:Y:H:(Approved,Completed,Canceled,Pending)') as `agent_paid_status_desc`,
    concat(ifnull(`v`.`other_terms_conditions`, ''), ':Other Terms Conditions:O:N:') as `other_terms_conditions`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') as `product_type_short_name`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`hsn_sac_type`, ''), ':Hsn Sac Type:O:N:') as `hsn_sac_type`,
    concat(ifnull(`v`.`hsn_sac_code`, ''), ':Hsn Sac Code:O:N:') as `hsn_sac_code`,
    concat(ifnull(`v`.`hsn_sac_description`, ''), ':Hsn Sac Description:O:N:') as `hsn_sac_description`,
    concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Hsn Sac Rate:O:N:') as `hsn_sac_rate`,
    concat(ifnull(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ''), ':Active Status:Y:H:(Active,In Active)') as `Active`,
    concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`purchase_order_master_transaction_id`, ''), ':Purchase Order Master Transaction_id:O:N:') as `purchase_order_master_transaction_id`,
    concat(ifnull(`v`.`supplier_id`, ''), ':Supplier Id:N:N:') as `supplier_id`,
    concat(ifnull(`v`.`purchase_order_status`, ''), ':Purchase Order Status:O:N:') as `purchase_order_status`,
    concat(ifnull(`v`.`purchase_order_creation_type`, ''), ':Purchase Order Creation Type:N:N:') as `purchase_order_creation_type`,
    concat(ifnull(`v`.`purchase_order_life`, ''), ':Purchase Order Life:O:N:') as `purchase_order_life`,
    concat(ifnull(`v`.`purchase_process_entry`, ''), ':Purchase Process Entry:O:N:') as `purchase_process_entry`,
    concat(ifnull(`v`.`agent_paid_status`, ''), ':Agent Paid Status:N:N:') as `agent_paid_status`,
    concat(ifnull(`v`.`supplier_state_id`, ''), ':Supplier State Id:N:N:') as `supplier_state_id`,
    concat(ifnull(`v`.`supplier_city_id`, ''), ':Supplier City Id:N:N:') as `supplier_city_id`,
    concat(ifnull(`v`.`supplier_contacts_ids`, ''), ':Supplier Contacts Ids:N:N:') as `supplier_contacts_ids`,
    concat(ifnull(`v`.`expected_branch_id`, ''), ':Expected Branch Id:N:N:') as `expected_branch_id`,
    concat(ifnull(`v`.`expected_branch_state_id`, ''), ':Expected Branch State Id:N:N:') as `expected_branch_state_id`,
    concat(ifnull(`v`.`expected_branch_city_id`, ''), ':Expected Branch City Id:N:N:') as `expected_branch_city_id`,
    concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By Id:O:N:') as `approved_by_id`,
    concat(ifnull(`v`.`purchase_order_mail_sent_status`, ''), ':Purchase Order Mail Sent Status:N:N:') as `purchase_order_mail_sent_status`,
    concat(ifnull(`v`.`purchase_order_acceptance_status`, ''), ':Purchase Order Acceptance Status:N:N:') as `purchase_order_acceptance_status`,
    concat(ifnull(`v`.`agent_id`, ''), ':Agent Id:N:N:') as `agent_id`
from
    `ptv_purchase_order_master_summary` `v`
limit 1;




