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
    `v`.`company_address1` AS `company_address1`,
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



-- ERP_PASHUPATI_PROD_1_0.cmv_employee source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `cmv_employee` AS
select
    `b`.`employee_type_group` AS `employee_type_group`,
    `b`.`employee_id` AS `employee_id`,
    `b`.`employee_code` AS `employee_code`,
    `b`.`old_employee_code` AS `old_employee_code`,
    `b`.`employee_type` AS `employee_type`,
    `b`.`salutation` AS `salutation`,
    `b`.`employee_name` AS `employee_name`,
    `b`.`employee_status` AS `employee_status`,
    `b`.`left_suspended_date` AS `left_suspended_date`,
    `vp`.`department_name` AS `department_name`,
    `vp1`.`department_name` AS `sub_department_name`,
    `dg`.`designation_name` AS `designation_name`,
    `me1`.`employee_name` AS `reporting_to_name`,
    `ms`.`shift_name` AS `shift_name`,
    `mss`.`shift_name` AS `current_shift_name`,
    concat(`ms`.`start_time`, '-', `ms`.`end_time`) AS `shift_start_end_time`,
    `ms`.`halfday_hours` AS `halfday_hours`,
    `ms`.`fullday_hours` AS `fullday_hours`,
    `ms`.`grace_early_time` AS `grace_early_time`,
    `ms`.`grace_late_time` AS `grace_late_time`,
    `wk`.`weeklyoff_name` AS `weeklyoff_name`,
    `b`.`email_id1` AS `email_id1`,
    `b`.`cell_no1` AS `cell_no1`,
    `a`.`date_joining` AS `date_joining`,
    `a`.`date_exit` AS `date_exit`,
    `cn`.`contractor_name` AS `contractor_name`,
    `b`.`employee_name` AS `field_name`,
    `b`.`employee_id` AS `field_id`,
    `es`.`ctc` AS `ctc`,
    `es`.`salary` AS `salary`,
    case
        `b`.`employee_type` when 'Worker' then `vp`.`worker_perday_salary`
        else 0
    end AS `worker_perday_salary`,
    case
        `b`.`employee_type` when 'Worker' then `vp`.`worker_perday_attendance_allowance`
        else 0
    end AS `worker_perday_attendance_allowance`,
    case
        `b`.`employee_type` when 'Worker' then `vp`.`worker_perday_night_allowance`
        else 0
    end AS `worker_perday_night_allowance`,
    `a`.`job_type_id` AS `job_type_id`,
    `a`.`job_type_short_name` AS `job_type_short_name`,
    `j`.`job_type_rate` AS `job_type_rate`,
    `j`.`job_type_attendance_allowance` AS `job_type_attendance_allowance`,
    `j`.`job_type_night_allowance` AS `job_type_night_allowance`,
    `j`.`job_type_special_allowance` AS `job_type_special_allowance`,
    `es`.`ot_flag` AS `ot_flag`,
    `b`.`gender` AS `gender`,
    `b`.`aadhar_card_no` AS `aadhar_card_no`,
    `b`.`passport_no` AS `passport_no`,
    `b`.`pan_no` AS `pan_no`,
    `b`.`driving_licence` AS `driving_licence`,
    `b`.`current_address` AS `current_address`,
    `b`.`current_pincode` AS `current_pincode`,
    `ct`.`city_name` AS `city_name`,
    `d1`.`district_name` AS `district_name`,
    `s`.`state_name` AS `state_name`,
    `b`.`country` AS `country`,
    `b`.`permanant_address` AS `permanant_address`,
    `b`.`permanant_pincode` AS `permanant_pincode`,
    `b`.`date_of_birth` AS `date_of_birth`,
    `b`.`email_id2` AS `email_id2`,
    `b`.`phone_no` AS `phone_no`,
    `b`.`cell_no2` AS `cell_no2`,
    `k1`.`bank_name` AS `bank_name1`,
    `b`.`account_no1` AS `account_no1`,
    `b`.`account_name1` AS `account_name1`,
    `b`.`ifsc_code1` AS `ifsc_code1`,
    `b`.`marital_status` AS `marital_status`,
    `b`.`reference` AS `reference`,
    `b`.`religion` AS `religion`,
    `b`.`caste` AS `employee_caste`,
    `b`.`category` AS `employee_category`,
    `d`.`destination_name` AS `destination_name`,
    `b`.`blood_group` AS `blood_group`,
    `b`.`finance_account_no` AS `finance_account_no`,
    `a`.`contract_startdate` AS `contract_startdate`,
    `a`.`contract_enddate` AS `contract_enddate`,
    `vp`.`department_group` AS `department_group`,
    `eb`.`employee_band_name` AS `employee_band_name`,
    `eg`.`employee_grade_name` AS `employee_grade_name`,
    `cc`.`cost_center_name` AS `cost_center_name`,
    `a`.`bond_applicable` AS `bond_applicable`,
    `a`.`current_job` AS `current_job`,
    `b`.`machine_employee_code` AS `machine_employee_code`,
    `b`.`username` AS `username`,
    `b`.`password` AS `password`,
    `b`.`last_name` AS `last_name`,
    `b`.`first_name` AS `first_name`,
    `b`.`middle_name` AS `middle_name`,
    `b`.`bank_id1` AS `bank_id1`,
    `k2`.`bank_name` AS `bank_name2`,
    `b`.`bank_id2` AS `bank_id2`,
    `b`.`account_no2` AS `account_no2`,
    `b`.`account_name2` AS `account_name2`,
    `b`.`ifsc_code2` AS `ifsc_code2`,
    `v`.`company_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `b`.`is_active` AS `is_active`,
    `b`.`is_delete` AS `is_delete`,
    `b`.`created_by` AS `created_by`,
    `b`.`created_on` AS `created_on`,
    `b`.`modified_by` AS `modified_by`,
    `b`.`modified_on` AS `modified_on`,
    `b`.`deleted_by` AS `deleted_by`,
    `b`.`deleted_on` AS `deleted_on`,
    `v`.`company_id` AS `company_id`,
    `v`.`company_branch_id` AS `company_branch_id`,
    `b`.`city_id` AS `city_id`,
    `b`.`district_id` AS `district_id`,
    `b`.`state_id` AS `state_id`,
    `b`.`destination_id` AS `destination_id`,
    `b`.`image_path` AS `image_path`,
    `a`.`employee_workprofile_id` AS `employee_workprofile_id`,
    `a`.`contractor_id` AS `contractor_id`,
    `a`.`department_group_id` AS `department_group_id`,
    `a`.`department_id` AS `department_id`,
    `a`.`sub_department_id` AS `sub_department_id`,
    `a`.`designation_id` AS `designation_id`,
    `a`.`weeklyoff` AS `weeklyoff`,
    `ms`.`two_day_shift` AS `two_day_shift`,
    `a`.`shift_id` AS `shift_id`,
    `a`.`current_shift_id` AS `current_shift_id`,
    `a`.`cost_center_id` AS `cost_center_id`,
    `a`.`profit_center_id` AS `profit_center_id`,
    `a`.`reporting_to` AS `reporting_to`,
    `a`.`region_id` AS `region_id`,
    `es`.`employee_salary_id` AS `employee_salary_id`,
    `es`.`band_id` AS `band_id`,
    `es`.`grade_id` AS `grade_id`,
    `es`.`ot_amount` AS `ot_amount`,
    `es`.`gratuity_applicable` AS `gratuity_applicable`,
    `es`.`pf_flag` AS `pf_flag`,
    `es`.`uan_no` AS `uan_no`,
    `es`.`pf_date` AS `pf_date`,
    `es`.`esic_flag` AS `esic_flag`,
    `es`.`esic_date` AS `esic_date`,
    `es`.`mlwf_flag` AS `mlwf_flag`,
    `es`.`mlwf_no` AS `mlwf_no`,
    `b`.`father_dob` AS `father_dob`,
    `b`.`father_name` AS `father_name`,
    `b`.`mother_name` AS `mother_name`,
    `b`.`mother_dob` AS `mother_dob`,
    `b`.`spouse_name` AS `spouse_name`,
    `b`.`spouse_dob` AS `spouse_dob`,
    `b`.`son_name` AS `son_name`,
    `b`.`son_dob` AS `son_dob`,
    `b`.`daughter_name` AS `daughter_name`,
    `b`.`daughter_dob` AS `daughter_dob`,
    `ms`.`shift_grace_hours_min` AS `shift_grace_hours_min`,
    `ms`.`shift_grace_hours_max` AS `shift_grace_hours_max`,
    `a`.`attendance_exclude_flag` AS `attendance_exclude_flag`
from
    (((((((((((((((((((((`cm_employee` `b`
left join `cm_employees_workprofile` `a` on
    (`a`.`employee_id` = `b`.`employee_id`
        and `a`.`company_id` = `b`.`company_id`))
left join `cm_employees_salary` `es` on
    (`es`.`employee_id` = `b`.`employee_id`
        and `es`.`company_id` = `b`.`company_id`
        and `es`.`is_delete` = 0))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `b`.`city_id`))
left join `hm_job_type` `j` on
    (`j`.`job_type_id` = `a`.`job_type_id`))
left join `cm_destination` `d` on
    (`d`.`destination_id` = `b`.`destination_id`))
left join `cm_designation` `dg` on
    (`dg`.`designation_id` = `a`.`designation_id`
        and `dg`.`company_id` = `a`.`company_id`))
left join `cmv_company_branch_summary` `v` on
    (`v`.`company_branch_id` = `b`.`company_branch_id`
        and `v`.`company_id` = `b`.`company_id`))
left join `cm_contractor` `cn` on
    (`cn`.`contractor_id` = `a`.`contractor_id`
        and `cn`.`company_branch_id` = `a`.`company_branch_id`
        and `cn`.`company_id` = `b`.`company_id`))
left join `cmv_department` `vp` on
    (`vp`.`department_id` = `a`.`department_id`))
left join `cmv_department` `vp1` on
    (`vp1`.`department_id` = `a`.`sub_department_id`))
left join `cm_banks_List` `k1` on
    (`k1`.`bank_id` = `b`.`bank_id1`))
left join `cm_banks_List` `k2` on
    (`k2`.`bank_id` = `b`.`bank_id2`))
left join `cm_employee` `me1` on
    (`me1`.`employee_id` = `a`.`reporting_to`
        and `me1`.`is_delete` = 0))
left join `cm_shift` `ms` on
    (`ms`.`shift_id` = `a`.`shift_id`
        and `ms`.`is_delete` = 0))
left join `cm_shift` `mss` on
    (`mss`.`shift_id` = `a`.`current_shift_id`
        and `mss`.`is_delete` = 0))
left join `hm_weeklyoff` `wk` on
    (`wk`.`weeklyoff_id` = `a`.`weeklyoff`))
left join `cm_employee_band` `eb` on
    (`eb`.`employee_band_id` = `es`.`band_id`
        and `eb`.`company_id` = `es`.`company_id`))
left join `cm_employee_grade` `eg` on
    (`eg`.`employee_grade_id` = `es`.`grade_id`))
left join `fm_cost_center` `cc` on
    (`cc`.`cost_center_id` = `a`.`cost_center_id`
        and `cc`.`company_id` = `a`.`company_id`))
left join `cm_district` `d1` on
    (`d1`.`district_id` = `b`.`district_id`))
left join `cm_state` `s` on
    (`s`.`state_id` = `b`.`state_id`))
where
    `b`.`is_delete` = 0;