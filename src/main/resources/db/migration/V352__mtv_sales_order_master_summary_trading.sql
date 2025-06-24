-- mtv_sales_order_master_trading_summary source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `mtv_sales_order_master_trading_summary` AS
select
    case
        `mt`.`sales_order_life` when 'C' then 'Closed'
        when 'O' then 'Open'
    end AS `sales_order_life_desc`,
    case
        `mt`.`sales_order_creation_type` when 'M' then 'Mannual'
        when 'Q' then 'Quotation Based'
    end AS `sales_order_creation_type_desc`,
    `mt`.`sales_order_no` AS `sales_order_no`,
    `mt`.`sales_order_date` AS `sales_order_date`,
    `mt`.`sales_order_version` AS `sales_order_version`,
    `mt`.`sales_quotation_no` AS `sales_quotation_no`,
    case
        `mt`.`sales_order_status` when 'A' then 'Approved'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Issue'
        when 'Z' then 'PreeClosed'
        else 'Pending'
    end AS `sales_order_status_desc`,
    `vp`.`department_name` AS `department_name`,
    `sd`.`department_name` AS `sub_department_name`,
    case
        `mt`.`sales_order_acceptance_status` when 'A' then 'Order Accepted'
        when 'X' then 'Order Cancelled'
        else 'Aceptance Pending'
    end AS `sales_order_acceptance_status_desc`,
    case
        `mt`.`sales_order_mail_sent_status` when 'S' then 'Email Sent'
        when 'F' then 'Email Failed'
        else 'Email Pending'
    end AS `sales_order_mail_sent_status_desc`,
    `mt`.`overall_schedule_date` AS `overall_schedule_date`,
    `mt`.`supplier_branch_id` AS `supplier_branch_id`,
    `mt`.`lot_no` AS `lot_no`,
    `csb`.`supp_branch_name` AS `supp_branch_name`,
    `c`.`customer_name` AS `customer_name`,
    `cs`.`state_name` AS `customer_state_name`,
    `cb`.`cust_branch_EmailId` AS `customer_email`,
    `cct`.`city_name` AS `customer_city_name`,
    `cd`.`district_name` AS `customer_district_name`,
    `cb`.`cust_branch_gst_no` AS `cust_branch_gst_no`,
    `cb`.`cust_branch_pan_no` AS `cust_branch_pan_no`,
    `cb`.`cust_branch_phone_no` AS `cust_branch_phone_no`,
    `cb`.`cust_branch_address1` AS `cust_branch_address1`,
    `mt`.`customer_order_no` AS `customer_order_no`,
    `mt`.`customer_order_Date` AS `customer_order_Date`,
    `csg`.`customer_name` AS `consignee_name`,
    `csgct`.`city_name` AS `consignee_city_name`,
    `csgd`.`district_name` AS `consignee_district_name`,
    `csgs`.`state_name` AS `consignee_state_name`,
    `csgcb`.`cust_branch_gst_no` AS `consignee_gst_no`,
    `csgcb`.`cust_branch_pan_no` AS `consignee_pan_no`,
    `csgcb`.`cust_branch_phone_no` AS `consignee_phone_no`,
    `csgcb`.`cust_branch_address1` AS `consignee_address1`,
    `e`.`employee_name` AS `approved_by_name`,
    `mt`.`approved_date` AS `approved_date`,
    `mt`.`basic_total` AS `basic_total`,
    `mt`.`transport_amount` AS `transport_amount`,
    `mt`.`freight_amount` AS `freight_amount`,
    `mt`.`packing_amount` AS `packing_amount`,
    `mt`.`discount_percent` AS `discount_percent`,
    `mt`.`discount_amount` AS `discount_amount`,
    `mt`.`other_amount` AS `other_amount`,
    `mt`.`taxable_total` AS `taxable_total`,
    `mt`.`cgst_percent` AS `cgst_percent`,
    `mt`.`cgst_total` AS `cgst_total`,
    `mt`.`sgst_percent` AS `sgst_percent`,
    `mt`.`sgst_total` AS `sgst_total`,
    `mt`.`igst_percent` AS `igst_percent`,
    `mt`.`igst_total` AS `igst_total`,
    `mt`.`roundoff` AS `roundoff`,
    `mt`.`grand_total` AS `grand_total`,
    `a`.`agent_name` AS `agent_name`,
    `mt`.`agent_percent` AS `agent_percent`,
    case
        `mt`.`agent_paid_status` when 'A' then 'Approved'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Pending'
    end AS `agent_paid_status_desc`,
    `mt`.`sales_order_type` AS `sales_order_type`,
    `mt`.`sales_order_life` AS `sales_order_life`,
    `mt`.`sales_order_creation_type` AS `sales_order_creation_type`,
    `pt`.`product_type_short_name` AS `product_type_short_name`,
    `mt`.`status_remark` AS `status_remark`,
    `mt`.`sales_order_status` AS `sales_order_status`,
    `mt`.`agent_paid_status` AS `agent_paid_status`,
    `mt`.`other_terms_conditions` AS `other_terms_conditions`,
    `hsn`.`hsn_sac_type` AS `hsn_sac_type`,
    `hsn`.`hsn_sac_code` AS `hsn_sac_code`,
    `hsn`.`hsn_sac_description` AS `hsn_sac_description`,
    `hsn`.`hsn_sac_rate` AS `hsn_sac_rate`,
    `mt`.`remark` AS `remark`,
    `v`.`company_legal_name` AS `company_name`,
    `vb`.`company_branch_name` AS `company_branch_name`,
    `vb`.`branch_pincode` AS `company_pincode`,
    `vb`.`branch_phone_no` AS `company_phone_no`,
    `st`.`state_name` AS `company_branch_state`,
    `vb`.`branch_cell_no` AS `company_cell_no`,
    `vb`.`branch_EmailId` AS `company_EmailId`,
    `vb`.`branch_website` AS `company_website`,
    `vb`.`branch_gst_no` AS `company_gst_no`,
    `vb`.`branch_pan_no` AS `company_pan_no`,
    `vb`.`branch_address1` AS `company_address1`,
    `vb`.`branch_address2` AS `company_address2`,
    `mt`.`financial_year` AS `financial_year`,
    `mt`.`job_type` AS `job_type`,
    `pt`.`product_type_group` AS `product_type_group`,
    `mt`.`is_freight_taxable` AS `is_freight_taxable`,
    `mt`.`sales_quotation_date` AS `sales_quotation_date`,
    `mt`.`sales_order_acceptance_status` AS `sales_order_acceptance_status`,
    `mt`.`sales_order_mail_sent_status` AS `sales_order_mail_sent_status`,
    `mt`.`sales_type` AS `sales_type`,
    case
        when `mt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `mt`.`is_delete` = 1 then 'Yes'
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
    `mt`.`sales_order_master_transaction_id` AS `sales_order_master_transaction_id`,
    `mt`.`company_id` AS `company_id`,
    `mt`.`company_branch_id` AS `company_branch_id`,
    `mt`.`sales_order_type_id` AS `sales_order_type_id`,
    `mt`.`freight_hsn_code_id` AS `freight_hsn_code_id`,
    `mt`.`customer_id` AS `customer_id`,
    `mt`.`customer_state_id` AS `customer_state_id`,
    `mt`.`customer_city_id` AS `customer_city_id`,
    `mt`.`consignee_id` AS `consignee_id`,
    `mt`.`consignee_state_id` AS `consignee_state_id`,
    `mt`.`consignee_city_id` AS `consignee_city_id`,
    `mt`.`department_id` AS `department_id`,
    `mt`.`sub_department_id` AS `sub_department_id`,
    `mt`.`approved_by_id` AS `approved_by_id`,
    `mt`.`agent_id` AS `agent_id`,
    `mt`.`customer_contacts_ids` AS `customer_contacts_ids`
from
    ((((((((((((((((((((`mt_sales_order_master_trading` `mt`
left join `cm_company` `v` on
    (`v`.`company_id` = `mt`.`company_id`))
left join `cm_company_branch` `vb` on
    (`vb`.`company_branch_id` = `mt`.`company_branch_id`
        and `vb`.`company_id` = `mt`.`company_id`))
left join `cm_state` `st` on
    (`st`.`state_id` = `vb`.`branch_state_id`))
left join `sm_product_type` `pt` on
    (`mt`.`sales_order_type_id` = `pt`.`product_type_id`
        and `pt`.`is_delete` = 0))
left join `cm_customer` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `cm_customer_branch` `cb` on
    (`cb`.`company_id` = `mt`.`company_id`
        and `cb`.`customer_id` = `mt`.`customer_id`))
left join `cm_state` `cs` on
    (`cs`.`state_id` = `cb`.`cust_branch_state_id`))
left join `cm_district` `cd` on
    (`cd`.`district_id` = `cb`.`cust_branch_district_id`))
left join `cm_city` `cct` on
    (`cct`.`city_id` = `cb`.`cust_branch_city_id`))
left join `cm_customer` `csg` on
    (`csg`.`company_id` = `mt`.`company_id`
        and `csg`.`customer_id` = `mt`.`consignee_id`))
left join `cm_customer_branch` `csgcb` on
    (`csgcb`.`company_id` = `mt`.`company_id`
        and `csgcb`.`customer_id` = `mt`.`consignee_id`))
left join `cm_state` `csgs` on
    (`csgs`.`state_id` = `csgcb`.`cust_branch_state_id`))
left join `cm_district` `csgd` on
    (`csgd`.`district_id` = `csgcb`.`cust_branch_district_id`))
left join `cm_city` `csgct` on
    (`csgct`.`city_id` = `csgcb`.`cust_branch_city_id`))
left join `cm_department` `vp` on
    (`vp`.`department_id` = `mt`.`department_id`
        and `vp`.`is_delete` = 0))
left join `cm_department` `sd` on
    (`sd`.`department_id` = `mt`.`sub_department_id`
        and `sd`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `mt`.`approved_by_id`
        and `e`.`is_delete` = 0))
left join `cm_agent` `a` on
    (`a`.`agent_id` = `mt`.`agent_id`
        and `a`.`is_delete` = 0))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `mt`.`freight_hsn_code_id`))
left join `cm_supplier_branch` `csb` on
    (`csb`.`supp_branch_id` = `mt`.`supplier_branch_id`))
where
    `mt`.`is_delete` = 0;
    

    -- mtv_sales_order_master_trading_summary_rpt source
    
    CREATE OR REPLACE
    ALGORITHM = UNDEFINED VIEW `mtv_sales_order_master_trading_summary_rpt` AS
    select
        concat(ifnull(`v`.`sales_order_no`, ''), ':Sales Order No:Y:T') AS `sales_order_no`, 
        concat(ifnull(`v`.`sales_order_date`, ''), ':Sales Order Date:Y:D:') AS `sales_order_date`,
        concat(ifnull(`v`.`sales_order_status`, ''), ':Sales Order Status:Y:H:(Approved,Completed,Canceled,Rejected,Partial Issue,PreeClosed,Pending)') AS `sales_order_status_desc`,
        concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:T') AS `customer_name`,
        concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:T') AS `customer_order_no`,
        concat(ifnull(`v`.`customer_order_Date`, ''), ':Customer Order Date:Y:D:') AS `customer_order_Date`,
        concat(ifnull(`v`.`supp_branch_name`, ''), ':Supplier Name:Y:T') AS `supp_branch_name`,
        concat(ifnull(`v`.`lot_no`, ''), ':Lot No:Y:T:') AS `lot_no`, 
        concat(ifnull(`v`.`customer_name`, ''), ':Consignee Name:Y:T') AS `consignee_name`,
        concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') AS `department_name`,
        concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') AS `sub_department_name`,
        concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:T') AS `approved_by_name`,
        concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') AS `approved_date`,
        concat(ifnull(`v`.`overall_schedule_date`, ''), ':Overall Schedule Date:Y:D:') AS `overall_schedule_date`,
        concat(ifnull(`v`.`basic_total`, ''), ':Basic Total:O:N:') AS `basic_total`,
        concat(ifnull(`v`.`transport_amount`, ''), ':Transport Amount:O:N:') AS `transport_amount`,
        concat(ifnull(`v`.`hsn_sac_code`, ''), ':Freight Hsn Sac Code:O:N:') AS `hsn_sac_code`,
        concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Freight Hsn Sac Rate:O:N:') AS `hsn_sac_rate`,
        concat(ifnull(`v`.`freight_amount`, ''), ':Freight Amount:O:N:') AS `freight_amount`,
        concat(ifnull(`v`.`packing_amount`, ''), ':Packing Amount:O:N:') AS `packing_amount`,
        concat(ifnull(`v`.`discount_percent`, ''), ':Discount Percent:O:N:') AS `discount_percent`,
        concat(ifnull(`v`.`discount_amount`, ''), ':Discount Amount:O:N:') AS `discount_amount`,
        concat(ifnull(`v`.`other_amount`, ''), ':Other Amount:O:N:') AS `other_amount`,
        concat(ifnull(`v`.`taxable_total`, ''), ':Taxable Total:O:N:') AS `taxable_total`,
        concat(ifnull(`v`.`cgst_total`, ''), ':CGST Total:O:N:') AS `cgst_total`,
        concat(ifnull(`v`.`sgst_total`, ''), ':SGST Total:O:N:') AS `sgst_total`,
        concat(ifnull(`v`.`igst_total`, ''), ':IGST Total:O:N:') AS `igst_total`,
        concat(ifnull(`v`.`grand_total`, ''), ':Grand Total:O:N:') AS `grand_total`,
        concat(ifnull(`v`.`roundoff`, ''), ':RoundOff:O:N:') AS `roundoff`,
        concat(ifnull(`v`.`agent_name`, ''), ':Agent Name:O:N:') AS `agent_name`,
        concat(ifnull(`v`.`agent_percent`, ''), ':Agent Percent:O:N:') AS `agent_percent`,
        concat(ifnull(`v`.`agent_paid_status`, ''), ':Agent Paid Status:Y:H:Approved,Completed,Canceled,Pending') AS `agent_paid_status_desc`,
        concat(ifnull(`v`.`sales_order_acceptance_status`, ''), ':Sales Order Acceptance Status:Y:H:Approved,Completed,Canceled,Pending') AS `sales_order_acceptance_status_desc`,
        concat(ifnull(`v`.`sales_order_mail_sent_status`, ''), ':Sales Order Mail Sent Status:O:N:Approved,Completed,Canceled,Pending') AS `sales_order_mail_sent_status_desc`,
        concat(ifnull(`v`.`status_remark`, ''), ':Status Remark:O:N:') AS `status_remark`,
        concat(ifnull(`v`.`sales_order_version`, ''), ':Sales Order Version:O:N:') AS `sales_order_version`,
        concat(ifnull(`v`.`other_terms_conditions`, ''), ':Other Terms Conditions:O:N:') AS `other_terms_conditions`,
        concat(ifnull(`v`.`sales_order_status`, ''), ':Sales Order Status:O:N:') AS `sales_order_status`,
        concat(ifnull(`v`.`sales_order_mail_sent_status`, ''), ':Sales Order Mail Sent Status:O:N:') AS `sales_order_mail_sent_status`,
        concat(ifnull(`v`.`sales_order_acceptance_status`, ''), ':Sales Order Acceptance Status:O:N:') AS `sales_order_acceptance_status`,
        concat(ifnull(`v`.`agent_paid_status`, ''), ':Agent Paid Status:O:N:') AS `agent_paid_status`,
        concat(ifnull(`v`.`sales_order_type`, ''), ':Sales Order Type:O:N:') AS `sales_order_type`,
        concat(ifnull(`v`.`sales_order_creation_type`, ''), ':Sales Order Creation Type:O:N:') AS `sales_order_creation_type`,
        concat(ifnull(`v`.`is_freight_taxable`, ''), ':Is Freight Taxable:O:N:') AS `is_freight_taxable`,
        concat(ifnull(`v`.`customer_email`, ''), ':Customer Email:O:N:') AS `customer_email`,
        concat(ifnull(`v`.`customer_state_name`, ''), ':Customer State:O:N:') AS `customer_state_name`,
        concat(ifnull(`v`.`customer_district_name`, ''), ':Customer District:O:N:') AS `customer_district_name`,
        concat(ifnull(`v`.`customer_city_name`, ''), ':Customer City:O:N:') AS `customer_city_name`,
        concat(ifnull(`v`.`consignee_state_name`, ''), ':Consignee State:O:N:') AS `consignee_state_name`,
        concat(ifnull(`v`.`consignee_district_name`, ''), ':Consignee District:O:N:') AS `consignee_district_name`,
        concat(ifnull(`v`.`consignee_city_name`, ''), ':Consignee City:O:N:') AS `consignee_city_name`,
        concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') AS `product_type_short_name`,
        concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') AS `remark`,
        concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') AS `company_name`,
        concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') AS `company_branch_name`,
        concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') AS `financial_year`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
        concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
        concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') AS `created_on`,
        concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
        concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
        concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
        concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
        concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') AS `company_id`,
        concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') AS `company_branch_id`,
        concat(ifnull(`v`.`sgst_percent`, ''), ':SGST Percent:N:N:') AS `sgst_percent`,
        concat(ifnull(`v`.`igst_percent`, ''), ':IGST Percent:N:N:') AS `igst_percent`,
        concat(ifnull(`v`.`cgst_percent`, ''), ':CGST Percent:N:N:') AS `cgst_percent`,
        concat(ifnull(`v`.`sales_order_master_transaction_id`, ''), ':Sales Order Master Transaction Id:O:N:') AS `sales_order_master_transaction_id`,
        concat(ifnull(`v`.`freight_hsn_code_id`, ''), ':Freight Hsn Code Id:N:N:') AS `freight_hsn_code_id`,
        concat(ifnull(`v`.`sales_order_type_id`, ''), ':Sales Order Type Id:N:N:') AS `sales_order_type_id`,
        concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') AS `customer_id`,
        concat(ifnull(`v`.`customer_state_id`, ''), ':Customer State Id:N:N:') AS `customer_state_id`,
        concat(ifnull(`v`.`customer_city_id`, ''), ':Customer City Id:N:N:') AS `customer_city_id`,
        concat(ifnull(`v`.`consignee_id`, ''), ':Consignee Id:N:N:') AS `consignee_id`,
        concat(ifnull(`v`.`consignee_state_id`, ''), ':Consignee State Id:N:N:') AS `consignee_state_id`,
        concat(ifnull(`v`.`consignee_city_id`, ''), ':Consignee City Id:N:N:') AS `consignee_city_id`,
        concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') AS `department_id`,
        concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By Id:N:N:') AS `approved_by_id`,
        concat(ifnull(`v`.`agent_id`, ''), ':Agent Id:N:N:') AS `agent_id`
    from
        `mtv_sales_order_master_trading_summary` `v`
    limit 1;
    
    
    
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