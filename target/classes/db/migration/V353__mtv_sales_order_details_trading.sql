-- mtv_sales_order_details_trading source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `mtv_sales_order_details_trading` AS
select
    case
        `sotv`.`sales_order_life` when 'C' then 'Closed'
        when 'O' then 'Open'
    end AS `sales_order_life_desc`,
    case
        `sotv`.`sales_order_creation_type` when 'M' then 'Mannual'
        when 'A' then 'Auto'
        when 'R' then 'Reorder'
    end AS `sales_order_creation_type_desc`,
    `mt`.`sales_order_no` AS `sales_order_no`,
    `mt`.`sales_order_version` AS `sales_order_version`,
    `mt`.`sales_order_date` AS `sales_order_date`,
    `mt`.`sales_quotation_no` AS `sales_quotation_no`,
    case
        `sotv`.`sales_order_status` when 'A' then 'Approved'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Issue'
        when 'Z' then 'PreeClosed'
        else 'Pending'
    end AS `sales_order_status_desc`,
    case
        `mt`.`sales_order_item_status` when 'A' then 'Approved'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Issue'
        when 'Z' then 'PreeClosed'
        when 'O' then 'PO'
        when 'G' then 'GRN'
        else 'Pending'
    end AS `sales_order_item_status_desc`,
    case
        `sotv`.`sales_order_mail_sent_status` when 'S' then 'Email Sent'
        when 'F' then 'Email Failed'
        else 'Email Pending'
    end AS `sales_order_mail_sent_status_desc`,
    `mt`.`sales_quotation_date` AS `sales_quotation_date`,
    `vp`.`department_name` AS `department_name`,
    `cc`.`customer_name` AS `customer_name`,
    `mt`.`customer_order_no` AS `customer_order_no`,
    `mt`.`customer_order_Date` AS `customer_order_Date`,
    `ccs`.`state_name` AS `customer_state_name`,
    `csg`.`customer_name` AS `consignee_name`,
    `csgs`.`state_name` AS `consignee_state_name`,
    `sotv`.`approved_date` AS `approved_date`,
    `sotv`.`overall_schedule_date` AS `overall_schedule_date`,
    `mt`.`invoice_nos` AS `invoice_nos`,
    `mt`.`sales_quantity` AS `previous_dispatch_quantity`,
    `mt`.`sales_weight` AS `previous_dispatch_weight`,
    '0' AS `stock_quantity`,
    '0' AS `stock_weight`,
    `mt`.`product_type` AS `product_type`,
    `p`.`product_type_group` AS `product_type_group`,
    `p`.`product_type_short_name` AS `product_type_short_name`,
    `rmfgsr`.`product_material_name` AS `product_material_name`,
    `rmfgsr`.`product_material_code` AS `product_material_code`,
    `rmfgsr`.`product_material_tech_spect` AS `product_material_tech_spect`,
    `rmfgsr`.`product_material_stock_unit_name` AS `product_material_stock_unit_name`,
    `rmfgsr`.`product_material_std_weight` AS `product_material_std_weight`,
    `rmfgsr`.`product_material_technical_name` AS `product_material_technical_name`,
    `rmfgsr`.`product_material_make_name` AS `product_material_make_name`,
    `rmfgsr`.`product_material_type_name` AS `product_material_type_name`,
    `rmfgsr`.`product_material_grade_name` AS `product_material_grade_name`,
    `rmfgsr`.`product_material_shape_name` AS `product_material_shape_name`,
    `rmfgsr`.`product_material_oem_part_code` AS `product_material_oem_part_code`,
    `rmfgsr`.`product_material_our_part_code` AS `product_material_our_part_code`,
    `rmfgsr`.`product_material_packing_name` AS `product_material_packing_name`,
    `mt`.`product_material_print_name` AS `product_material_print_name`,
    `hsn`.`hsn_sac_code` AS `product_material_hsn_sac_code`,
    `hsn`.`hsn_sac_rate` AS `product_material_hsn_sac_rate`,
    `mt`.`sr_no` AS `sr_no`,
    `mt`.`so_sr_no` AS `so_sr_no`,
    `mt`.`material_quantity` AS `material_quantity`,
    `mt`.`material_weight` AS `material_weight`,
    `mt`.`quotation_rate` AS `quotation_rate`,
    `mt`.`material_rate` AS `material_rate`,
    `mt`.`material_basic_amount` AS `material_basic_amount`,
    `mt`.`material_discount_percent` AS `material_discount_percent`,
    `mt`.`material_discount_amount` AS `material_discount_amount`,
    `mt`.`so_rate` AS `so_rate`,
    `mt`.`material_taxable_amount` AS `material_taxable_amount`,
    `mt`.`material_cgst_percent` AS `material_cgst_percent`,
    `mt`.`material_cgst_total` AS `material_cgst_total`,
    `mt`.`material_sgst_percent` AS `material_sgst_percent`,
    `mt`.`material_sgst_total` AS `material_sgst_total`,
    `mt`.`material_igst_percent` AS `material_igst_percent`,
    `mt`.`material_igst_total` AS `material_igst_total`,
    `mt`.`material_freight_amount` AS `material_freight_amount`,
    `mt`.`material_total_amount` AS `material_total_amount`,
    `mt`.`material_schedule_date` AS `material_schedule_date`,
    `mt`.`product_recommendation` AS `product_recommendation`,
    `mt`.`special_remark` AS `special_remark`,
    `mt`.`epi_1` AS `epi_1`,
    `mt`.`ppi_1` AS `ppi_1`,
    `mt`.`width` AS `width`,
    `mt`.`warp_crimp` AS `warp_crimp`,
    `mt`.`weft_crimp` AS `weft_crimp`,
    `mt`.`warp_waste` AS `warp_waste`,
    `mt`.`weft_waste` AS `weft_waste`,
    `mt`.`warp_count_1__wc1__actual_count` AS `warp_count_1__wc1__actual_count`,
    `mt`.`warp_count_2__wc2__actual_count` AS `warp_count_2__wc2__actual_count`,
    `mt`.`warp_count_3__wc3__actual_count` AS `warp_count_3__wc3__actual_count`,
    `mt`.`warp_count_4__wc4__actual_count` AS `warp_count_4__wc4__actual_count`,
    `mt`.`weft_count_1__fc1__actual_count` AS `weft_count_1__fc1__actual_count`,
    `mt`.`weft_count_2__fc2__actual_count` AS `weft_count_2__fc2__actual_count`,
    `mt`.`weft_count_3__fc3__actual_count` AS `weft_count_3__fc3__actual_count`,
    `mt`.`weft_count_4__fc4__actual_count` AS `weft_count_4__fc4__actual_count`,
    case
        `mt`.`monogram_is_applicable` when 1 then 'Yes'
        else 'No'
    end AS `monogram_is_applicable_desc`,
    `mt`.`monogram_is_applicable` AS `monogram_is_applicable`,
    `mt`.`weft_color` AS `weft_color`,
    `mt`.`material_style` AS `material_style`,
    `mt`.`material_style_value` AS `material_style_value`,
    `mt`.`material_style_abbrevation` AS `material_style_abbrevation`,
    `mt`.`dispatch_note_nos` AS `dispatch_note_nos`,
    `mt`.`dispatch_challan_nos` AS `dispatch_challan_nos`,
    `mt`.`production_issue_quantity` AS `production_issue_quantity`,
    `mt`.`production_issue_weight` AS `production_issue_weight`,
    `mt`.`production_issue_return_quantity` AS `production_issue_return_quantity`,
    `mt`.`production_issue_return_weight` AS `production_issue_return_weight`,
    `mt`.`production_issue_rejection_quantity` AS `production_issue_rejection_quantity`,
    `mt`.`production_issue_rejection_weight` AS `production_issue_rejection_weight`,
    `sotv`.`sales_order_status` AS `sales_order_status`,
    `sotv`.`sales_order_life` AS `sales_order_life`,
    `sotv`.`sales_order_creation_type` AS `sales_order_creation_type`,
    `mt`.`sales_order_item_status` AS `sales_order_item_status`,
    `sotv`.`sales_order_mail_sent_status` AS `sales_order_mail_sent_status`,
    `ccb`.`cust_branch_EmailId` AS `customer_email`,
    `cccty`.`city_name` AS `customer_city_name`,
    `ccd`.`district_name` AS `customer_district_name`,
    `csgcty`.`city_name` AS `consignee_city_name`,
    `csgd`.`district_name` AS `consignee_district_name`,
    `e`.`employee_name` AS `approved_by_name`,
    `sotv`.`basic_total` AS `basic_total`,
    `sotv`.`transport_amount` AS `transport_amount`,
    `sotv`.`freight_amount` AS `freight_amount`,
    `sotv`.`packing_amount` AS `packing_amount`,
    `sotv`.`discount_percent` AS `discount_percent`,
    `sotv`.`discount_amount` AS `discount_amount`,
    `sotv`.`other_amount` AS `other_amount`,
    `sotv`.`taxable_total` AS `taxable_total`,
    `sotv`.`cgst_percent` AS `cgst_percent`,
    `sotv`.`cgst_total` AS `cgst_total`,
    `sotv`.`sgst_percent` AS `sgst_percent`,
    `sotv`.`sgst_total` AS `sgst_total`,
    `sotv`.`igst_percent` AS `igst_percent`,
    `sotv`.`igst_total` AS `igst_total`,
    `sotv`.`roundoff` AS `roundoff`,
    `sotv`.`grand_total` AS `grand_total`,
    `sotv`.`other_terms_conditions` AS `other_terms_conditions`,
    `mt`.`remark` AS `remark`,
    `v`.`company_legal_name` AS `company_name`,
    `vb`.`company_branch_name` AS `company_branch_name`,
    `vb`.`branch_address1` AS `company_address1`,
    `vb`.`branch_address2` AS `company_address2`,
    `vb`.`branch_phone_no` AS `company_phone_no`,
    `vb`.`branch_cell_no` AS `company_cell_no`,
    `vb`.`branch_EmailId` AS `company_EmailId`,
    `vb`.`branch_website` AS `company_website`,
    `vb`.`branch_gst_no` AS `company_gst_no`,
    `vb`.`branch_pan_no` AS `company_pan_no`,
    `vbs`.`state_name` AS `company_state`,
    `vb`.`branch_pincode` AS `company_pincode`,
    `mt`.`financial_year` AS `financial_year`,
    `mt`.`set_no` AS `set_no`,
    case
        `mt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `mt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `mt`.`is_active` AS `is_active`,
    `mt`.`is_delete` AS `is_delete`,
    `mt`.`created_by` AS `created_by`,
    `mt`.`created_on` AS `created_on`,
    `mt`.`modified_by` AS `modified_by`,
    `mt`.`modified_on` AS `modified_on`,
    `mt`.`deleted_by` AS `deleted_by`,
    `mt`.`deleted_on` AS `deleted_on`,
    `mt`.`company_id` AS `company_id`,
    `mt`.`sales_order_master_transaction_id` AS `sales_order_master_transaction_id`,
    `mt`.`sales_order_details_transaction_id` AS `sales_order_details_transaction_id`,
    `mt`.`company_branch_id` AS `company_branch_id`,
    `mt`.`product_type_id` AS `product_type_id`,
    `mt`.`product_material_id` AS `product_material_id`,
    `mt`.`product_material_unit_id` AS `product_material_unit_id`,
    `mt`.`product_material_packing_id` AS `product_material_packing_id`,
    `mt`.`product_material_hsn_code_id` AS `product_material_hsn_code_id`,
    `sotv`.`customer_id` AS `customer_id`,
    coalesce((select sum(`mtdispatch`.`actual_dispatch_quantity`) from `mt_dispatch_schedule_details_trading` `mtdispatch` where `mtdispatch`.`customer_order_no` = `mt`.`customer_order_no` and `mtdispatch`.`product_material_id` = `mt`.`product_material_id` and `mtdispatch`.`so_sr_no` = `mt`.`so_sr_no`), 0) AS `dispatched_quantity`,
    coalesce((select sum(`mtdispatch`.`actual_dispatch_weight`) from `mt_dispatch_schedule_details_trading` `mtdispatch` where `mtdispatch`.`customer_order_no` = `mt`.`customer_order_no` and `mtdispatch`.`product_material_id` = `mt`.`product_material_id` and `mtdispatch`.`so_sr_no` = `mt`.`so_sr_no`), 0) AS `dispatched_weight`
from
    (((((((((((((((((((`mt_sales_order_details_trading` `mt`
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `mt`.`product_material_id`))
left join `mt_sales_order_master_trading` `sotv` on
    (`sotv`.`company_branch_id` = `mt`.`company_branch_id`
        and `sotv`.`company_id` = `mt`.`company_id`
        and `sotv`.`sales_order_master_transaction_id` = `mt`.`sales_order_master_transaction_id`))
left join `cm_department` `vp` on
    (`vp`.`department_id` = `sotv`.`department_id`
        and `vp`.`is_delete` = 0))
left join `cm_customer` `cc` on
    ( `cc`.`customer_id` = `sotv`.`customer_id`
        and `cc`.`is_delete` = 0))
left join `cm_customer_branch` `ccb` on
    (`ccb`.`company_id` = `mt`.`company_id`
        and `ccb`.`customer_id` = `sotv`.`customer_id`))
left join `cm_state` `ccs` on
    (`ccs`.`state_id` = `ccb`.`cust_branch_state_id`))
left join `cm_city` `cccty` on
    (`cccty`.`city_id` = `ccb`.`cust_branch_city_id`))
left join `cm_district` `ccd` on
    (`ccd`.`district_id` = `ccb`.`cust_branch_district_id`))
left join `cm_customer` `csg` on
    (`csg`.`company_id` = `sotv`.`company_id`
        and `csg`.`customer_id` = `sotv`.`customer_id`
        and `csg`.`is_delete` = 0))
left join `cm_customer_branch` `csgcb` on
    (`csgcb`.`company_id` = `mt`.`company_id`
        and `csgcb`.`customer_id` = `sotv`.`customer_id`))
left join `cm_state` `csgs` on
    (`csgs`.`state_id` = `csgcb`.`cust_branch_state_id`))
left join `cm_city` `csgcty` on
    (`csgcty`.`city_id` = `csgcb`.`cust_branch_city_id`))
left join `cm_district` `csgd` on
    (`csgd`.`district_id` = `csgcb`.`cust_branch_district_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `sotv`.`approved_by_id`
        and `e`.`is_delete` = 0))
left join `cm_company` `v` on
    (`v`.`company_id` = `mt`.`company_id`))
left join `cm_company_branch` `vb` on
    (`vb`.`company_branch_id` = `mt`.`company_branch_id`
        and `vb`.`company_id` = `mt`.`company_id`))
left join `cm_state` `vbs` on
    (`vbs`.`state_id` = `vb`.`branch_state_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `mt`.`product_material_hsn_code_id`
        and `hsn`.`is_delete` = 0))
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `mt`.`product_type_id`
        and `p`.`is_delete` = 0))
where
    `mt`.`is_delete` = 0
order by
    `mt`.`sales_order_details_transaction_id` desc;
    
    
    
    
    
    
    
    
    -- mtv_sales_order_details_trading_rpt source
    
    CREATE OR REPLACE
    ALGORITHM = UNDEFINED VIEW `mtv_sales_order_details_trading_rpt` AS
    select
        concat(`v`.`sales_order_no`, ':Sales Order No:Y:T') AS `sales_order_no`, 
        concat(`v`.`sales_order_date`, ':Sales Order Date:Y:D:') AS `sales_order_date`,
        concat(`v`.`sales_order_status`, ':Sales Order Status:Y:H:(Approved,Completed,Canceled,Rejected,Partial Issue,PreeClosed,Pending)') AS `sales_order_status_desc`,   
        concat(`v`.`customer_name`, ':Customer Name:Y:T') AS `customer_name`,
        concat(`v`.`customer_order_no`, ':Customer Order No:Y:T') AS `customer_order_no`,
        concat(`v`.`customer_order_Date`, ':Customer Order Date:Y:D:') AS `customer_order_Date`,
        concat(`v`.`department_name`, ':Department Name:Y:T') AS `department_name`,
        concat(`v`.`consignee_name`, ':Consignee Name:Y:T:') AS `consignee_name`,
        concat(`v`.`approved_by_name`, ':Approved By:Y:C:cmv_employee_list:F') AS `approved_by_name`,
        concat(`v`.`approved_date`, ':Approved Date:Y:D:') AS `approved_date`,
        concat(`v`.`product_type`, ':Product Type:O:N:') AS `product_type`,
        concat(`v`.`product_material_name`, ':Material Name:O:N:') AS `product_material_name`,   
        concat(`v`.`product_material_stock_unit_name`, ':Material Stock Unit Name:O:N:') AS `product_material_stock_unit_name`,
        concat(`v`.`product_material_std_weight`, ':Material STD Weight:O:N:') AS `product_material_std_weight`, 
        concat(`v`.`sales_order_item_status`, ':Sales Order Item Status Desc:O:N:(Approved,Completed,Canceled,Rejected,Partial Issue,PreeClosed,PO,GRN,Pending)') AS `sales_order_item_status_desc`,
        concat(`v`.`sales_order_mail_sent_status`, ':Sales Order Mail Sent Status:O:N:') AS `sales_order_mail_sent_status`,
        concat(`v`.`material_quantity`, ':Material Quantity:O:N:') AS `material_quantity`,
        concat(`v`.`material_weight`, ':Material Weight:O:N:') AS `material_weight`,
        concat(`v`.`material_rate`, ':Material Rate:O:N:') AS `material_rate`,
        concat(`v`.`material_basic_amount`, ':Material Basic Amount:O:N:') AS `material_basic_amount`,
        concat(`v`.`material_discount_percent`, ':Material Discount Percent:O:N:') AS `material_discount_percent`,
        concat(`v`.`material_discount_amount`, ':Material Discount Amount:O:N:') AS `material_discount_amount`,
        concat(`v`.`material_taxable_amount`, ':Material Taxable Amount:O:N:') AS `material_taxable_amount`,
        concat(`v`.`material_cgst_percent`, ':Material Cgst Percent:O:N:') AS `material_cgst_percent`,
        concat(`v`.`material_cgst_total`, ':Material Cgst Total:O:N:') AS `material_cgst_total`,
        concat(`v`.`material_sgst_percent`, ':Material Sgst Percent:O:N:') AS `material_sgst_percent`,
        concat(`v`.`material_sgst_total`, ':Material SGST Total:O:N:') AS `material_sgst_total`,
        concat(`v`.`material_igst_percent`, ':Material IGST Percent:O:N:') AS `material_igst_percent`,
        concat(`v`.`material_igst_total`, ':Material IGST Total:O:N:') AS `material_igst_total`,
        concat(`v`.`material_total_amount`, ':Material Total Amount:O:N:') AS `material_total_amount`,
        concat(`v`.`dispatch_note_nos`, ':Dispatch Note Nos:O:N:') AS `dispatch_note_nos`,
        concat(`v`.`dispatch_challan_nos`, ':Dispatch Challan Nos:O:N:') AS `dispatch_challan_nos`,
        concat(`v`.`invoice_nos`, ':Invoice Nos:O:N:(Closed,Open)') AS `invoice_nos`,
        '0' AS `previous_dispatch_quantity`,
        '0' AS `previous_dispatch_weight`,
        '0' AS `stock_quantity`,
        '0' AS `stock_weight`,
        concat(`v`.`dispatched_quantity`, ':Dispatched Quantity:O:N:') AS `dispatched_quantity`,
        concat(`v`.`dispatched_weight`, ':Dispatched Weight:O:N:') AS `dispatched_weight`,
        concat(`v`.`production_issue_quantity`, ':Issue Quantity:O:N:') AS `production_issue_quantity`,
        concat(`v`.`production_issue_weight`, ':Issue Weight:O:N:') AS `production_issue_weight`,
        concat(`v`.`production_issue_return_quantity`, ':Issue Return Quantity:O:N:') AS `production_issue_return_quantity`,
        concat(`v`.`production_issue_return_weight`, ':Issue Return Weight:O:N:') AS `production_issue_return_weight`,
        concat(`v`.`production_issue_rejection_quantity`, ':Issue Rejection Quantity:O:N:') AS `production_issue_rejection_quantity`,
        concat(`v`.`production_issue_rejection_weight`, ':Issue Rejection Weight:O:N:') AS `production_issue_rejection_weight`,
        concat(`v`.`product_material_hsn_sac_code`, ':Hsn Sac Code:O:N:') AS `product_material_hsn_sac_code`,
        concat(`v`.`product_material_hsn_sac_rate`, ':Hsn Sac Rate:O:N:') AS `product_material_hsn_sac_rate`,
        concat(`v`.`customer_email`, ':Customer Email:O:N:') AS `customer_email`,
        concat(`v`.`customer_state_name`, ':Customer State:O:N:') AS `customer_state_name`,
        concat(`v`.`customer_district_name`, ':Customer District:O:N:') AS `customer_district_name`,
        concat(`v`.`customer_city_name`, ':Customer City:O:N:') AS `customer_city_name`,
        concat(`v`.`consignee_state_name`, ':Consignee State:O:N:') AS `consignee_state_name`,
        concat(`v`.`consignee_district_name`, ':Consignee District:O:N:') AS `consignee_district_name`,
        concat(`v`.`consignee_city_name`, ':Consignee City:O:N:') AS `consignee_city_name`,
        concat(`v`.`basic_total`, ':Basic Total:O:N:') AS `basic_total`,
        concat(`v`.`transport_amount`, ':Transport Amount:O:N:') AS `transport_amount`,
        concat(`v`.`material_freight_amount`, ':Freight Amount:O:N:') AS `material_freight_amount`,
        concat(`v`.`freight_amount`, ':Freight Amount:O:N:') AS `freight_amount`,
        concat(`v`.`packing_amount`, ':Packing Amount:O:N:') AS `packing_amount`,
        concat(`v`.`discount_percent`, ':Discount Percent:O:N:') AS `discount_percent`,
        concat(`v`.`discount_amount`, ':Discount Amount:O:N:') AS `discount_amount`,
        concat(`v`.`other_amount`, ':Other Amount:O:N:') AS `other_amount`,
        concat(`v`.`taxable_total`, ':Taxable Total:O:N:') AS `taxable_total`,
        concat(`v`.`cgst_percent`, ':CGST Percent:O:N:') AS `cgst_percent`,
        concat(`v`.`cgst_total`, ':CGST Total:O:N:') AS `cgst_total`,
        concat(`v`.`sgst_percent`, ':SGST Percent:O:N:') AS `sgst_percent`,
        concat(`v`.`sgst_total`, ':SGST Total:O:N:') AS `sgst_total`,
        concat(`v`.`igst_percent`, ':IGST Percent:O:N:') AS `igst_percent`,
        concat(`v`.`igst_total`, ':IGST Total:O:N:') AS `igst_total`,
        concat(`v`.`grand_total`, ':Grand Total:O:N:') AS `grand_total`,
        concat(`v`.`roundoff`, ':RoundOff:O:N:') AS `roundoff`,
        concat(`v`.`sales_order_version`, ':Sales Order Version:O:N:') AS `sales_order_version`,
         concat(`v`.`overall_schedule_date`, ':Overall Schedule Date:Y:D:') AS `overall_schedule_date`,
        concat(`v`.`other_terms_conditions`, ':Other Terms Conditions:O:N:') AS `other_terms_conditions`,
        concat(`v`.`product_type_short_name`, ':Product Type Short Name:O:N:') AS `product_type_short_name`,
        concat(`v`.`sales_order_creation_type`, ':Sales Order Creation Type:O:N:') AS `sales_order_creation_type`,
        concat(`v`.`company_name`, ':Company Name:Y:C:cmv_company_summary:F') AS `company_name`,
        concat(`v`.`company_branch_name`, ':Company Branch:Y:C:cmv_company_branch_summary:F') AS `company_branch_name`,
        concat(`v`.`financial_year`, ':Financial Year:Y:C:amv_financial_year:F') AS `financial_year`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
        concat(`v`.`created_by`, ':Created By:O:N:') AS `created_by`,
        concat(`v`.`created_on`, ':Modified On:O:N:') AS `created_on`,
        concat(`v`.`modified_by`, ':Modified By:O:N:') AS `modified_by`,
        concat(`v`.`modified_on`, ':Modified On:O:N:') AS `modified_on`,
        concat(`v`.`deleted_by`, ':Deleted By:O:N:') AS `deleted_by`,
        concat(`v`.`deleted_on`, ':Deleted On:O:N:') AS `deleted_on`,
        concat(`v`.`sales_order_item_status`, ':Sales Order Item Status:O:N:') AS `sales_order_item_status`,
        concat(`v`.`sales_order_status`, ':Sales Order Status:O:N:') AS `sales_order_status`,
    	concat(`v`.`company_id`, ':Company Id:N:N:') AS `company_id`,
        concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') AS `company_branch_id`,
        concat(`v`.`sales_order_master_transaction_id`, ':Sales Order Master Transaction Id:O:N:') AS `sales_order_master_transaction_id`,
        concat(`v`.`sales_order_details_transaction_id`, ':Sales Order Details Transaction Id:N:N:') AS `sales_order_details_transaction_id`,
        concat(`v`.`product_type_id`, ':Product Type Id:N:N:') AS `product_type_id`,
        concat(`v`.`product_material_id`, ':Product Material Id:N:N:') AS `product_material_id`,
        concat(`v`.`product_material_unit_id`, ':Product Material Unit Id:N:N:') AS `product_material_unit_id`,
        concat(`v`.`product_material_packing_id`, ':Product Material Packing Id:N:N:') AS `product_material_packing_id`,
        concat(`v`.`product_material_hsn_code_id`, ':Product Material Hsn Code Id:N:N:') AS `product_material_hsn_code_id`
    from
        `mtv_sales_order_details_trading` `v`
    limit 1;