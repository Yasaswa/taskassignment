INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,2,9,'Purchase Order (Cotton Bales)',5,'Purchase Order (Cotton Bales)','Purchase','<FrmCottonBalesPOListing />','import FrmCottonBalesPOListing from "./Transactions/TPurchaseOrder/ManualPO/CottonBalesPO/FrmCottonBalesListing"','/Transactions/TPurchaseOrder/ManualPO/CottonBalesPO/FrmCottonBalesListing','<FrmCottonBalesPOEntry />','import FrmCottonBalesPOEntry from "./Transactions/TPurchaseOrder/ManualPO/CottonBalesPO/FrmCottonBalesEntry";','/Transactions/TPurchaseOrder/ManualPO/CottonBalesPO/FrmCottonBalesEntry',NULL,'Purchase (Cotton Bales)',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


ALTER TABLE pt_purchase_order_details ADD product_rm_purchase_unit_id bigint(20) DEFAULT 0 NULL;

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
    `pt`.`product_rm_purchase_unit_id` as `product_rm_purchase_unit_id`,
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

ALTER TABLE pt_purchase_order_tax_summary ADD product_material_id varchar(100) NULL;

create or replace
algorithm = UNDEFINED view `ptv_purchase_order_tax_summary` as
select
    `pt`.`purchase_order_type_id` as `purchase_order_type_id`,
    `pt`.`purchase_order_type` as `purchase_order_type`,
    `pt`.`purchase_order_master_transaction_id` as `purchase_order_master_transaction_id`,
    `s`.`supplier_type` as `supplier_type`,
    `s`.`supplier_code` as `supplier_code`,
    `s`.`supplier_name` as `supplier_name`,
    `s`.`supplier_short_name` as `supplier_short_name`,
    `s`.`supp_branch_address1` as `supp_branch_address1`,
    `s`.`supp_branch_pincode` as `supp_branch_pincode`,
    `s`.`city_name` as `supplier_city_name`,
    `s`.`district_name` as `supplier_district_name`,
    `s`.`state_name` as `supplier_state_name`,
    `pt`.`product_material_id` as `product_material_id`,
    `s`.`country_name` as `supplier_country_name`,
    `s`.`supp_branch_phone_no` as `supp_branch_phone_no`,
    `s`.`supp_branch_EmailId` as `supp_branch_EmailId`,
    `cb`.`company_branch_name` as `expected_branch_name`,
    `cb`.`branch_short_name` as `expected_branch_short_name`,
    `cb`.`branch_address1` as `expected_branch_address1`,
    `cb`.`branch_pincode` as `expected_branch_pincode`,
    `cb`.`city_name` as `expected_branch_city_name`,
    `cb`.`district_name` as `expected_branch_district_name`,
    `cb`.`state_name` as `expected_branch_state_name`,
    `cb`.`country_name` as `expected_branch_country_name`,
    `cb`.`branch_phone_no` as `expected_branch_phone_no`,
    `cb`.`branch_EmailId` as `expected_branch_EmailId`,
    `pt`.`purchase_order_no` as `purchase_order_no`,
    `pt`.`purchase_order_date` as `purchase_order_date`,
    `pt`.`purchase_order_version` as `purchase_order_version`,
    `hsn`.`hsn_sac_type` as `hsn_sac_type`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `hsn`.`hsn_sac_description` as `hsn_sac_description`,
    `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
    `pt`.`summary_taxable_amount` as `summary_taxable_amount`,
    `pt`.`summary_cgst_percent` as `summary_cgst_percent`,
    `pt`.`summary_cgst_total` as `summary_cgst_total`,
    `pt`.`summary_sgst_percent` as `summary_sgst_percent`,
    `pt`.`summary_sgst_total` as `summary_sgst_total`,
    `pt`.`summary_igst_percent` as `summary_igst_percent`,
    `pt`.`summary_igst_total` as `summary_igst_total`,
    `pt`.`summary_total_amount` as `summary_total_amount`,
    `pt`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `pt`.`financial_year` as `financial_year`,
    `pt`.`is_active` as `is_active`,
    `pt`.`is_delete` as `is_delete`,
    `pt`.`created_by` as `created_by`,
    `pt`.`created_on` as `created_on`,
    `pt`.`modified_by` as `modified_by`,
    `pt`.`modified_on` as `modified_on`,
    `pt`.`deleted_by` as `deleted_by`,
    `pt`.`deleted_on` as `deleted_on`,
    `pt`.`purchase_order_tax_summary_transaction_id` as `purchase_order_tax_summary_transaction_id`,
    `pt`.`company_id` as `company_id`,
    `pt`.`company_branch_id` as `company_branch_id`,
    `pt`.`supplier_id` as `supplier_id`,
    `pt`.`supplier_state_id` as `supplier_state_id`,
    `pt`.`supplier_city_id` as `supplier_city_id`,
    `pt`.`supplier_contacts_ids` as `supplier_contacts_ids`,
    `pt`.`expected_branch_id` as `expected_branch_id`,
    `pt`.`expected_branch_state_id` as `expected_branch_state_id`,
    `pt`.`expected_branch_city_id` as `expected_branch_city_id`,
    `pt`.`hsn_code_id` as `hsn_code_id`
from
    ((((`pt_purchase_order_tax_summary` `pt`
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `pt`.`company_branch_id`
        and `v`.`company_id` = `pt`.`company_id`))
left join `cmv_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pt`.`company_branch_id`
        and `cb`.`company_id` = `pt`.`company_id`))
left join `cmv_supplier` `s` on
    (`s`.`supplier_id` = `pt`.`supplier_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`hsn_code_id`))
where
    `pt`.`is_delete` = 0;


