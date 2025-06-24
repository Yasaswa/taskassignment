

create or replace
algorithm = UNDEFINED view `mtv_sales_order_master_trading_summary` as
select
    case
        `mt`.`sales_order_life` when 'C' then 'Closed'
        when 'O' then 'Open'
    end as `sales_order_life_desc`,
    case
        `mt`.`sales_order_creation_type` when 'M' then 'Mannual'
        when 'Q' then 'Quotation Based'
    end as `sales_order_creation_type_desc`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `mt`.`sales_order_version` as `sales_order_version`,
    `mt`.`sales_quotation_no` as `sales_quotation_no`,
    case
        `mt`.`sales_order_status` when 'A' then 'Approved'
        when 'C' then 'Dispatched'
        when 'X' then 'Canceled'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Dispatched'
        when 'Z' then 'PreeClosed'
        else 'Pending'
    end as `sales_order_status_desc`,
    `vp`.`department_name` as `department_name`,
    `sd`.`department_name` as `sub_department_name`,
    case
        `mt`.`sales_order_acceptance_status` when 'A' then 'Order Accepted'
        when 'X' then 'Order Cancelled'
        else 'Aceptance Pending'
    end as `sales_order_acceptance_status_desc`,
    case
        `mt`.`sales_order_mail_sent_status` when 'S' then 'Email Sent'
        when 'F' then 'Email Failed'
        else 'Email Pending'
    end as `sales_order_mail_sent_status_desc`,
    `mt`.`overall_schedule_date` as `overall_schedule_date`,
    `mt`.`supplier_branch_id` as `supplier_branch_id`,
    `mt`.`lot_no` as `lot_no`,
    `csb`.`supp_branch_name` as `supp_branch_name`,
    `c`.`customer_name` as `customer_name`,
    `cs`.`state_name` as `customer_state_name`,
    `cb`.`cust_branch_EmailId` as `customer_email`,
    `cct`.`city_name` as `customer_city_name`,
    `cd`.`district_name` as `customer_district_name`,
    `cb`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `cb`.`cust_branch_pan_no` as `cust_branch_pan_no`,
    `cb`.`cust_branch_phone_no` as `cust_branch_phone_no`,
    `cb`.`cust_branch_address1` as `cust_branch_address1`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `csg`.`customer_name` as `consignee_name`,
    `csgct`.`city_name` as `consignee_city_name`,
    `csgd`.`district_name` as `consignee_district_name`,
    `csgs`.`state_name` as `consignee_state_name`,
    `csgcb`.`cust_branch_gst_no` as `consignee_gst_no`,
    `csgcb`.`cust_branch_pan_no` as `consignee_pan_no`,
    `csgcb`.`cust_branch_phone_no` as `consignee_phone_no`,
    `csgcb`.`cust_branch_address1` as `consignee_address1`,
    `e`.`employee_name` as `approved_by_name`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`basic_total` as `basic_total`,
    `mt`.`transport_amount` as `transport_amount`,
    `mt`.`freight_amount` as `freight_amount`,
    `mt`.`packing_amount` as `packing_amount`,
    `mt`.`discount_percent` as `discount_percent`,
    `mt`.`discount_amount` as `discount_amount`,
    `mt`.`other_amount` as `other_amount`,
    `mt`.`taxable_total` as `taxable_total`,
    `mt`.`cgst_percent` as `cgst_percent`,
    `mt`.`cgst_total` as `cgst_total`,
    `mt`.`sgst_percent` as `sgst_percent`,
    `mt`.`sgst_total` as `sgst_total`,
    `mt`.`igst_percent` as `igst_percent`,
    `mt`.`igst_total` as `igst_total`,
    `mt`.`roundoff` as `roundoff`,
    `mt`.`grand_total` as `grand_total`,
    `a`.`agent_name` as `agent_name`,
    `mt`.`agent_percent` as `agent_percent`,
    case
        `mt`.`agent_paid_status` when 'A' then 'Approved'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Pending'
    end as `agent_paid_status_desc`,
    `mt`.`sales_order_type` as `sales_order_type`,
    `mt`.`sales_order_life` as `sales_order_life`,
    `mt`.`sales_order_creation_type` as `sales_order_creation_type`,
    `pt`.`product_type_short_name` as `product_type_short_name`,
    `mt`.`status_remark` as `status_remark`,
    `mt`.`sales_order_status` as `sales_order_status`,
    `mt`.`agent_paid_status` as `agent_paid_status`,
    `mt`.`other_terms_conditions` as `other_terms_conditions`,
    `hsn`.`hsn_sac_type` as `hsn_sac_type`,
    `hsn`.`hsn_sac_code` as `hsn_sac_code`,
    `hsn`.`hsn_sac_description` as `hsn_sac_description`,
    `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
    `mt`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `vb`.`branch_pincode` as `company_pincode`,
    `vb`.`branch_phone_no` as `company_phone_no`,
    `st`.`state_name` as `company_branch_state`,
    `vb`.`branch_cell_no` as `company_cell_no`,
    `vb`.`branch_EmailId` as `company_EmailId`,
    `vb`.`branch_website` as `company_website`,
    `vb`.`branch_gst_no` as `company_gst_no`,
    `vb`.`branch_pan_no` as `company_pan_no`,
    `vb`.`branch_address1` as `company_address1`,
    `vb`.`branch_address2` as `company_address2`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`job_type` as `job_type`,
    `pt`.`product_type_group` as `product_type_group`,
    `mt`.`is_freight_taxable` as `is_freight_taxable`,
    `mt`.`sales_quotation_date` as `sales_quotation_date`,
    `mt`.`sales_order_acceptance_status` as `sales_order_acceptance_status`,
    `mt`.`sales_order_mail_sent_status` as `sales_order_mail_sent_status`,
    `mt`.`sales_type` as `sales_type`,
    case
        when `mt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `mt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`sales_order_master_transaction_id` as `sales_order_master_transaction_id`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`sales_order_type_id` as `sales_order_type_id`,
    `mt`.`freight_hsn_code_id` as `freight_hsn_code_id`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`customer_state_id` as `customer_state_id`,
    `mt`.`customer_city_id` as `customer_city_id`,
    `mt`.`consignee_id` as `consignee_id`,
    `mt`.`consignee_state_id` as `consignee_state_id`,
    `mt`.`consignee_city_id` as `consignee_city_id`,
    `mt`.`department_id` as `department_id`,
    `mt`.`sub_department_id` as `sub_department_id`,
    `mt`.`approved_by_id` as `approved_by_id`,
    `mt`.`agent_id` as `agent_id`,
    `mt`.`customer_contacts_ids` as `customer_contacts_ids`
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


create or replace
algorithm = UNDEFINED view `mtv_sales_order_master_trading_summary_rpt` as
select
    concat(ifnull(`v`.`sales_order_no`, ''), ':Sales Order No:Y:T') as `sales_order_no`,
    concat(ifnull(`v`.`sales_order_date`, ''), ':Sales Order Date:Y:D:') as `sales_order_date`,
    concat(ifnull(`v`.`sales_order_status`, ''), ':Sales Order Status:Y:H:(Approved,Dispatched,Canceled,Rejected,Partial Dispatched,PreeClosed,Pending)') as `sales_order_status_desc`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:T') as `customer_name`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:T') as `customer_order_no`,
    concat(ifnull(`v`.`customer_order_Date`, ''), ':Customer Order Date:Y:D:') as `customer_order_Date`,
    concat(ifnull(`v`.`job_type`, ''), ':Job Type:Y:T') as `job_type`,
    concat(ifnull(`v`.`supp_branch_name`, ''), ':Supplier Name:Y:T') as `supp_branch_name`,
    concat(ifnull(`v`.`lot_no`, ''), ':Lot No:Y:T:') as `lot_no`,
    concat(ifnull(`v`.`customer_name`, ''), ':Consignee Name:Y:T') as `consignee_name`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') as `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:T') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`overall_schedule_date`, ''), ':Overall Schedule Date:Y:D:') as `overall_schedule_date`,
    concat(ifnull(`v`.`basic_total`, ''), ':Basic Total:O:N:') as `basic_total`,
    concat(ifnull(`v`.`transport_amount`, ''), ':Transport Amount:O:N:') as `transport_amount`,
    concat(ifnull(`v`.`hsn_sac_code`, ''), ':Freight Hsn Sac Code:O:N:') as `hsn_sac_code`,
    concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Freight Hsn Sac Rate:O:N:') as `hsn_sac_rate`,
    concat(ifnull(`v`.`freight_amount`, ''), ':Freight Amount:O:N:') as `freight_amount`,
    concat(ifnull(`v`.`packing_amount`, ''), ':Packing Amount:O:N:') as `packing_amount`,
    concat(ifnull(`v`.`discount_percent`, ''), ':Discount Percent:O:N:') as `discount_percent`,
    concat(ifnull(`v`.`discount_amount`, ''), ':Discount Amount:O:N:') as `discount_amount`,
    concat(ifnull(`v`.`other_amount`, ''), ':Other Amount:O:N:') as `other_amount`,
    concat(ifnull(`v`.`taxable_total`, ''), ':Taxable Total:O:N:') as `taxable_total`,
    concat(ifnull(`v`.`cgst_total`, ''), ':CGST Total:O:N:') as `cgst_total`,
    concat(ifnull(`v`.`sgst_total`, ''), ':SGST Total:O:N:') as `sgst_total`,
    concat(ifnull(`v`.`igst_total`, ''), ':IGST Total:O:N:') as `igst_total`,
    concat(ifnull(`v`.`grand_total`, ''), ':Grand Total:O:N:') as `grand_total`,
    concat(ifnull(`v`.`roundoff`, ''), ':RoundOff:O:N:') as `roundoff`,
    concat(ifnull(`v`.`agent_name`, ''), ':Agent Name:O:N:') as `agent_name`,
    concat(ifnull(`v`.`agent_percent`, ''), ':Agent Percent:O:N:') as `agent_percent`,
    concat(ifnull(`v`.`agent_paid_status`, ''), ':Agent Paid Status:Y:H:Approved,Completed,Canceled,Pending') as `agent_paid_status_desc`,
    concat(ifnull(`v`.`sales_order_acceptance_status`, ''), ':Sales Order Acceptance Status:Y:H:Approved,Completed,Canceled,Pending') as `sales_order_acceptance_status_desc`,
    concat(ifnull(`v`.`sales_order_mail_sent_status`, ''), ':Sales Order Mail Sent Status:O:N:Approved,Completed,Canceled,Pending') as `sales_order_mail_sent_status_desc`,
    concat(ifnull(`v`.`status_remark`, ''), ':Status Remark:O:N:') as `status_remark`,
    concat(ifnull(`v`.`sales_order_version`, ''), ':Sales Order Version:O:N:') as `sales_order_version`,
    concat(ifnull(`v`.`other_terms_conditions`, ''), ':Other Terms Conditions:O:N:') as `other_terms_conditions`,
    concat(ifnull(`v`.`sales_order_status`, ''), ':Sales Order Status:O:N:') as `sales_order_status`,
    concat(ifnull(`v`.`sales_order_mail_sent_status`, ''), ':Sales Order Mail Sent Status:O:N:') as `sales_order_mail_sent_status`,
    concat(ifnull(`v`.`sales_order_acceptance_status`, ''), ':Sales Order Acceptance Status:O:N:') as `sales_order_acceptance_status`,
    concat(ifnull(`v`.`agent_paid_status`, ''), ':Agent Paid Status:O:N:') as `agent_paid_status`,
    concat(ifnull(`v`.`sales_order_type`, ''), ':Sales Order Type:O:N:') as `sales_order_type`,
    concat(ifnull(`v`.`sales_order_creation_type`, ''), ':Sales Order Creation Type:O:N:') as `sales_order_creation_type`,
    concat(ifnull(`v`.`is_freight_taxable`, ''), ':Is Freight Taxable:O:N:') as `is_freight_taxable`,
    concat(ifnull(`v`.`customer_email`, ''), ':Customer Email:O:N:') as `customer_email`,
    concat(ifnull(`v`.`customer_state_name`, ''), ':Customer State:O:N:') as `customer_state_name`,
    concat(ifnull(`v`.`customer_district_name`, ''), ':Customer District:O:N:') as `customer_district_name`,
    concat(ifnull(`v`.`customer_city_name`, ''), ':Customer City:O:N:') as `customer_city_name`,
    concat(ifnull(`v`.`consignee_state_name`, ''), ':Consignee State:O:N:') as `consignee_state_name`,
    concat(ifnull(`v`.`consignee_district_name`, ''), ':Consignee District:O:N:') as `consignee_district_name`,
    concat(ifnull(`v`.`consignee_city_name`, ''), ':Consignee City:O:N:') as `consignee_city_name`,
    concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') as `product_type_short_name`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`sgst_percent`, ''), ':SGST Percent:N:N:') as `sgst_percent`,
    concat(ifnull(`v`.`igst_percent`, ''), ':IGST Percent:N:N:') as `igst_percent`,
    concat(ifnull(`v`.`cgst_percent`, ''), ':CGST Percent:N:N:') as `cgst_percent`,
    concat(ifnull(`v`.`sales_order_master_transaction_id`, ''), ':Sales Order Master Transaction Id:O:N:') as `sales_order_master_transaction_id`,
    concat(ifnull(`v`.`freight_hsn_code_id`, ''), ':Freight Hsn Code Id:N:N:') as `freight_hsn_code_id`,
    concat(ifnull(`v`.`sales_order_type_id`, ''), ':Sales Order Type Id:N:N:') as `sales_order_type_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') as `customer_id`,
    concat(ifnull(`v`.`customer_state_id`, ''), ':Customer State Id:N:N:') as `customer_state_id`,
    concat(ifnull(`v`.`customer_city_id`, ''), ':Customer City Id:N:N:') as `customer_city_id`,
    concat(ifnull(`v`.`consignee_id`, ''), ':Consignee Id:N:N:') as `consignee_id`,
    concat(ifnull(`v`.`consignee_state_id`, ''), ':Consignee State Id:N:N:') as `consignee_state_id`,
    concat(ifnull(`v`.`consignee_city_id`, ''), ':Consignee City Id:N:N:') as `consignee_city_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
    concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By Id:N:N:') as `approved_by_id`,
    concat(ifnull(`v`.`agent_id`, ''), ':Agent Id:N:N:') as `agent_id`
from
    `mtv_sales_order_master_trading_summary` `v`
limit 1;



create or replace
algorithm = UNDEFINED view `mtv_sales_order_details_tracking_trading` as
select
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `mt`.`sales_order_version` as `sales_order_version`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `mt`.`product_type` as `product_type`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `rmfgsr`.`product_material_tech_spect` as `product_material_tech_spect`,
    `rmfgsr`.`product_material_stock_unit_name` as `product_material_stock_unit_name`,
    `rmfgsr`.`product_material_std_weight` as `product_material_std_weight`,
    `rmfgsr`.`product_material_technical_name` as `product_material_technical_name`,
    `rmfgsr`.`product_material_make_name` as `product_material_make_name`,
    `rmfgsr`.`product_material_type_name` as `product_material_type_name`,
    `rmfgsr`.`product_material_grade_name` as `product_material_grade_name`,
    `rmfgsr`.`product_material_shape_name` as `product_material_shape_name`,
    `mt`.`product_material_print_name` as `product_material_print_name`,
    `mt`.`sr_no` as `sr_no`,
    `mt`.`so_sr_no` as `so_sr_no`,
    `mt`.`product_material_unit_id` as `product_material_unit_id`,
    `mt`.`product_material_packing_id` as `product_material_packing_id`,
    `mt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `mt`.`material_quantity` as `material_quantity`,
    `mt`.`material_weight` as `material_weight`,
    `mt`.`quotation_rate` as `quotation_rate`,
    `mt`.`material_rate` as `material_rate`,
    `mt`.`material_schedule_date` as `material_schedule_date`,
    case
        `mt`.`sales_order_item_status` when 'A' then 'Approved'
        when 'C' then 'Dispatched'
        when 'X' then 'Canceled'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Dispatched'
        when 'Z' then 'PreeClosed'
        when 'O' then 'PO'
        when 'G' then 'GRN'
        else 'Pending'
    end as `sales_order_item_status_desc`,
    `mt`.`sales_order_item_status` as `sales_order_item_status`,
    `mt`.`pending_quantity` as `pending_quantity`,
    `mt`.`pending_weight` as `pending_weight`,
    `mt`.`opening_quantity` as `opening_quantity`,
    `mt`.`opening_weight` as `opening_weight`,
    `mt`.`reserve_quantity` as `reserve_quantity`,
    `mt`.`reserve_weight` as `reserve_weight`,
    `mt`.`excess_quantity` as `excess_quantity`,
    `mt`.`excess_weight` as `excess_weight`,
    `mt`.`pree_closed_quantity` as `pree_closed_quantity`,
    `mt`.`pree_closed_weight` as `pree_closed_weight`,
    `mt`.`purchase_quantity` as `purchase_quantity`,
    `mt`.`purchase_weight` as `purchase_weight`,
    `mt`.`purchase_return_quantity` as `purchase_return_quantity`,
    `mt`.`purchase_return_weight` as `purchase_return_weight`,
    `mt`.`purchase_rejection_quantity` as `purchase_rejection_quantity`,
    `mt`.`purchase_rejection_weight` as `purchase_rejection_weight`,
    `mt`.`jobcard_quantity` as `jobcard_quantity`,
    `mt`.`jobcard_weight` as `jobcard_weight`,
    `mt`.`production_issue_quantity` as `production_issue_quantity`,
    `mt`.`production_issue_weight` as `production_issue_weight`,
    `mt`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `mt`.`production_issue_return_weight` as `production_issue_return_weight`,
    `mt`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `mt`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `mt`.`production_quantity` as `production_quantity`,
    `mt`.`production_weight` as `production_weight`,
    `mt`.`production_return_quantity` as `production_return_quantity`,
    `mt`.`production_return_weight` as `production_return_weight`,
    `mt`.`production_rejection_quantity` as `production_rejection_quantity`,
    `mt`.`production_rejection_weight` as `production_rejection_weight`,
    `mt`.`assembly_production_issue_quantity` as `assembly_production_issue_quantity`,
    `mt`.`assembly_production_issue_weight` as `assembly_production_issue_weight`,
    `mt`.`sales_quantity` as `sales_quantity`,
    `mt`.`sales_weight` as `sales_weight`,
    `mt`.`sales_return_quantity` as `sales_return_quantity`,
    `mt`.`sales_return_weight` as `sales_return_weight`,
    `mt`.`sales_rejection_quantity` as `sales_rejection_quantity`,
    `mt`.`sales_rejection_weight` as `sales_rejection_weight`,
    `mt`.`transfer_issue_quantity` as `transfer_issue_quantity`,
    `mt`.`transfer_issue_weight` as `transfer_issue_weight`,
    `mt`.`transfer_receipt_quantity` as `transfer_receipt_quantity`,
    `mt`.`transfer_receipt_weight` as `transfer_receipt_weight`,
    `mt`.`outsources_out_quantity` as `outsources_out_quantity`,
    `mt`.`outsources_out_weight` as `outsources_out_weight`,
    `mt`.`outsources_in_quantity` as `outsources_in_quantity`,
    `mt`.`outsources_in_weight` as `outsources_in_weight`,
    `mt`.`outsources_rejection_quantity` as `outsources_rejection_quantity`,
    `mt`.`outsources_rejection_weight` as `outsources_rejection_weight`,
    `mt`.`loan_receipt_quantity` as `loan_receipt_quantity`,
    `mt`.`loan_receipt_weight` as `loan_receipt_weight`,
    `mt`.`loan_issue_quantity` as `loan_issue_quantity`,
    `mt`.`loan_issue_weight` as `loan_issue_weight`,
    `mt`.`cancel_quantity` as `cancel_quantity`,
    `mt`.`cancel_weight` as `cancel_weight`,
    `mt`.`difference_quantity` as `difference_quantity`,
    `mt`.`difference_weight` as `difference_weight`,
    `mt`.`remark` as `remark`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`sales_order_master_transaction_id` as `sales_order_master_transaction_id`,
    `mt`.`sales_order_details_transaction_id` as `sales_order_details_transaction_id`,
    `mt`.`product_type_id` as `product_type_id`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`
from
    (((`mt_sales_order_details_trading` `mt`
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `mt`.`product_material_id`))
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `mt`.`company_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
left join `mtv_sales_order_master_trading_summary` `sotv` on
    (`sotv`.`company_branch_id` = `mt`.`company_branch_id`
        and `sotv`.`company_id` = `mt`.`company_id`
        and `sotv`.`sales_order_master_transaction_id` = `mt`.`sales_order_master_transaction_id`))
where
    `mt`.`is_delete` = 0;

-- erp_new.mtv_sales_order_details_trading source

create or replace
algorithm = UNDEFINED view `mtv_sales_order_details_trading` as
select
    case
        `sotv`.`sales_order_life` when 'C' then 'Closed'
        when 'O' then 'Open'
    end as `sales_order_life_desc`,
    case
        `sotv`.`sales_order_creation_type` when 'M' then 'Mannual'
        when 'A' then 'Auto'
        when 'R' then 'Reorder'
    end as `sales_order_creation_type_desc`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_version` as `sales_order_version`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `mt`.`sales_quotation_no` as `sales_quotation_no`,
    case
        `sotv`.`sales_order_status` when 'A' then 'Approved'
        when 'C' then 'Dispatched'
        when 'X' then 'Canceled'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Dispatched'
        when 'Z' then 'PreeClosed'
        else 'Pending'
    end as `sales_order_status_desc`,
    case
        `mt`.`sales_order_item_status` when 'A' then 'Approved'
        when 'C' then 'Dispatched'
        when 'X' then 'Canceled'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Dispatched'
        when 'Z' then 'PreeClosed'
        when 'O' then 'PO'
        when 'G' then 'GRN'
        else 'Pending'
    end as `sales_order_item_status_desc`,
    case
        `sotv`.`sales_order_mail_sent_status` when 'S' then 'Email Sent'
        when 'F' then 'Email Failed'
        else 'Email Pending'
    end as `sales_order_mail_sent_status_desc`,
    `mt`.`sales_quotation_date` as `sales_quotation_date`,
    `vp`.`department_name` as `department_name`,
    `cc`.`customer_name` as `customer_name`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `ccs`.`state_name` as `customer_state_name`,
    `csg`.`customer_name` as `consignee_name`,
    `csgs`.`state_name` as `consignee_state_name`,
    `sotv`.`approved_date` as `approved_date`,
    `sotv`.`overall_schedule_date` as `overall_schedule_date`,
    `mt`.`invoice_nos` as `invoice_nos`,
    `mt`.`sales_quantity` as `previous_dispatch_quantity`,
    `mt`.`sales_weight` as `previous_dispatch_weight`,
    `mt`.`cancel_quantity` as `cancel_quantity`,
    `mt`.`cancel_weight` as `cancel_weight`,
    '0' as `stock_quantity`,
    '0' as `stock_weight`,
    `mt`.`product_type` as `product_type`,
    `p`.`product_type_group` as `product_type_group`,
    `p`.`product_type_short_name` as `product_type_short_name`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `rmfgsr`.`product_material_code` as `product_material_code`,
    `rmfgsr`.`product_material_tech_spect` as `product_material_tech_spect`,
    `rmfgsr`.`product_material_stock_unit_name` as `product_material_stock_unit_name`,
    `rmfgsr`.`product_material_std_weight` as `product_material_std_weight`,
    `rmfgsr`.`product_material_technical_name` as `product_material_technical_name`,
    `rmfgsr`.`product_material_make_name` as `product_material_make_name`,
    `rmfgsr`.`product_material_type_name` as `product_material_type_name`,
    `rmfgsr`.`product_material_grade_name` as `product_material_grade_name`,
    `rmfgsr`.`product_material_shape_name` as `product_material_shape_name`,
    `rmfgsr`.`product_material_oem_part_code` as `product_material_oem_part_code`,
    `rmfgsr`.`product_material_our_part_code` as `product_material_our_part_code`,
    `rmfgsr`.`product_material_packing_name` as `product_material_packing_name`,
    `mt`.`product_material_print_name` as `product_material_print_name`,
    `hsn`.`hsn_sac_code` as `product_material_hsn_sac_code`,
    `hsn`.`hsn_sac_rate` as `product_material_hsn_sac_rate`,
    `mt`.`sr_no` as `sr_no`,
    `mt`.`so_sr_no` as `so_sr_no`,
    `mt`.`material_quantity` as `material_quantity`,
    `mt`.`material_weight` as `material_weight`,
    `mt`.`quotation_rate` as `quotation_rate`,
    `mt`.`material_rate` as `material_rate`,
    `mt`.`material_basic_amount` as `material_basic_amount`,
    `mt`.`material_discount_percent` as `material_discount_percent`,
    `mt`.`material_discount_amount` as `material_discount_amount`,
    `mt`.`so_rate` as `so_rate`,
    `mt`.`material_taxable_amount` as `material_taxable_amount`,
    `mt`.`material_cgst_percent` as `material_cgst_percent`,
    `mt`.`material_cgst_total` as `material_cgst_total`,
    `mt`.`material_sgst_percent` as `material_sgst_percent`,
    `mt`.`material_sgst_total` as `material_sgst_total`,
    `mt`.`material_igst_percent` as `material_igst_percent`,
    `mt`.`material_igst_total` as `material_igst_total`,
    `mt`.`material_freight_amount` as `material_freight_amount`,
    `mt`.`material_total_amount` as `material_total_amount`,
    `mt`.`material_schedule_date` as `material_schedule_date`,
    `mt`.`product_recommendation` as `product_recommendation`,
    `mt`.`special_remark` as `special_remark`,
    `mt`.`epi_1` as `epi_1`,
    `mt`.`ppi_1` as `ppi_1`,
    `mt`.`width` as `width`,
    `mt`.`warp_crimp` as `warp_crimp`,
    `mt`.`weft_crimp` as `weft_crimp`,
    `mt`.`warp_waste` as `warp_waste`,
    `mt`.`weft_waste` as `weft_waste`,
    `mt`.`warp_count_1__wc1__actual_count` as `warp_count_1__wc1__actual_count`,
    `mt`.`warp_count_2__wc2__actual_count` as `warp_count_2__wc2__actual_count`,
    `mt`.`warp_count_3__wc3__actual_count` as `warp_count_3__wc3__actual_count`,
    `mt`.`warp_count_4__wc4__actual_count` as `warp_count_4__wc4__actual_count`,
    `mt`.`weft_count_1__fc1__actual_count` as `weft_count_1__fc1__actual_count`,
    `mt`.`weft_count_2__fc2__actual_count` as `weft_count_2__fc2__actual_count`,
    `mt`.`weft_count_3__fc3__actual_count` as `weft_count_3__fc3__actual_count`,
    `mt`.`weft_count_4__fc4__actual_count` as `weft_count_4__fc4__actual_count`,
    case
        `mt`.`monogram_is_applicable` when 1 then 'Yes'
        else 'No'
    end as `monogram_is_applicable_desc`,
    `mt`.`monogram_is_applicable` as `monogram_is_applicable`,
    `mt`.`weft_color` as `weft_color`,
    `mt`.`material_style` as `material_style`,
    `mt`.`material_style_value` as `material_style_value`,
    `mt`.`material_style_abbrevation` as `material_style_abbrevation`,
    `mt`.`dispatch_note_nos` as `dispatch_note_nos`,
    `mt`.`dispatch_challan_nos` as `dispatch_challan_nos`,
    `mt`.`production_issue_quantity` as `production_issue_quantity`,
    `mt`.`production_issue_weight` as `production_issue_weight`,
    `mt`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `mt`.`production_issue_return_weight` as `production_issue_return_weight`,
    `mt`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `mt`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `sotv`.`sales_order_status` as `sales_order_status`,
    `sotv`.`sales_order_life` as `sales_order_life`,
    `sotv`.`sales_order_creation_type` as `sales_order_creation_type`,
    `mt`.`sales_order_item_status` as `sales_order_item_status`,
    `sotv`.`sales_order_mail_sent_status` as `sales_order_mail_sent_status`,
    `ccb`.`cust_branch_EmailId` as `customer_email`,
    `cccty`.`city_name` as `customer_city_name`,
    `ccd`.`district_name` as `customer_district_name`,
    `csgcty`.`city_name` as `consignee_city_name`,
    `csgd`.`district_name` as `consignee_district_name`,
    `e`.`employee_name` as `approved_by_name`,
    `sotv`.`basic_total` as `basic_total`,
    `sotv`.`transport_amount` as `transport_amount`,
    `sotv`.`freight_amount` as `freight_amount`,
    `sotv`.`packing_amount` as `packing_amount`,
    `sotv`.`discount_percent` as `discount_percent`,
    `sotv`.`discount_amount` as `discount_amount`,
    `sotv`.`other_amount` as `other_amount`,
    `sotv`.`taxable_total` as `taxable_total`,
    `sotv`.`cgst_percent` as `cgst_percent`,
    `sotv`.`cgst_total` as `cgst_total`,
    `sotv`.`sgst_percent` as `sgst_percent`,
    `sotv`.`sgst_total` as `sgst_total`,
    `sotv`.`igst_percent` as `igst_percent`,
    `sotv`.`igst_total` as `igst_total`,
    `sotv`.`roundoff` as `roundoff`,
    `sotv`.`grand_total` as `grand_total`,
    `sotv`.`other_terms_conditions` as `other_terms_conditions`,
    `mt`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `vb`.`branch_address1` as `company_address1`,
    `vb`.`branch_address2` as `company_address2`,
    `vb`.`branch_phone_no` as `company_phone_no`,
    `vb`.`branch_cell_no` as `company_cell_no`,
    `vb`.`branch_EmailId` as `company_EmailId`,
    `vb`.`branch_website` as `company_website`,
    `vb`.`branch_gst_no` as `company_gst_no`,
    `vb`.`branch_pan_no` as `company_pan_no`,
    `vbs`.`state_name` as `company_state`,
    `vb`.`branch_pincode` as `company_pincode`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`set_no` as `set_no`,
    case
        `mt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `mt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`company_id` as `company_id`,
    `mt`.`sales_order_master_transaction_id` as `sales_order_master_transaction_id`,
    `mt`.`sales_order_details_transaction_id` as `sales_order_details_transaction_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`product_type_id` as `product_type_id`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_unit_id` as `product_material_unit_id`,
    `mt`.`product_material_packing_id` as `product_material_packing_id`,
    `mt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
    `sotv`.`customer_id` as `customer_id`,
    coalesce((select sum(`mtdispatch`.`actual_dispatch_quantity`) from `mt_dispatch_schedule_details_trading` `mtdispatch` where `mtdispatch`.`customer_order_no` = `mt`.`customer_order_no` and `mtdispatch`.`product_material_id` = `mt`.`product_material_id` and `mtdispatch`.`so_sr_no` = `mt`.`so_sr_no`), 0) as `dispatched_quantity`,
    coalesce((select sum(`mtdispatch`.`actual_dispatch_weight`) from `mt_dispatch_schedule_details_trading` `mtdispatch` where `mtdispatch`.`customer_order_no` = `mt`.`customer_order_no` and `mtdispatch`.`product_material_id` = `mt`.`product_material_id` and `mtdispatch`.`so_sr_no` = `mt`.`so_sr_no`), 0) as `dispatched_weight`
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
    (`cc`.`customer_id` = `sotv`.`customer_id`
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



create or replace
algorithm = UNDEFINED view `mtv_sales_order_details_trading_rpt` as
select
    concat(`v`.`sales_order_no`, ':Sales Order No:Y:T') as `sales_order_no`,
    concat(`v`.`sales_order_date`, ':Sales Order Date:Y:D:') as `sales_order_date`,
    concat(`v`.`sales_order_status`, ':Sales Order Status:Y:H:(Approved,Dispatched,Canceled,Rejected,Partial Dispatched,PreeClosed,Pending)') as `sales_order_status_desc`,
    concat(`v`.`customer_name`, ':Customer Name:Y:T') as `customer_name`,
    concat(`v`.`customer_order_no`, ':Customer Order No:Y:T') as `customer_order_no`,
    concat(`v`.`customer_order_Date`, ':Customer Order Date:Y:D:') as `customer_order_Date`,
    concat(`v`.`department_name`, ':Department Name:Y:T') as `department_name`,
    concat(`v`.`consignee_name`, ':Consignee Name:Y:T:') as `consignee_name`,
    concat(`v`.`approved_by_name`, ':Approved By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(`v`.`approved_date`, ':Approved Date:Y:D:') as `approved_date`,
    concat(`v`.`product_type`, ':Product Type:O:N:') as `product_type`,
    concat(`v`.`product_material_name`, ':Material Name:Y:T:') as `product_material_name`,
    concat(`v`.`product_material_code`, ':Material Code:Y:T:') as `product_material_code`,
    concat(`v`.`product_material_stock_unit_name`, ':Material Stock Unit Name:O:N:') as `product_material_stock_unit_name`,
    concat(`v`.`product_material_std_weight`, ':Material STD Weight:O:N:') as `product_material_std_weight`,
    concat(`v`.`sales_order_item_status`, ':Sales Order Item Status Desc:O:N:(Approved,Dispatched,Canceled,Rejected,Partial Dispatched,PreeClosed,PO,GRN,Pending)') as `sales_order_item_status_desc`,
    concat(`v`.`sales_order_mail_sent_status`, ':Sales Order Mail Sent Status:O:N:') as `sales_order_mail_sent_status`,
    concat(`v`.`material_quantity`, ':Material Quantity:O:N:') as `material_quantity`,
    concat(`v`.`material_weight`, ':Material Weight:O:N:') as `material_weight`,
    concat(`v`.`material_rate`, ':Material Rate:O:N:') as `material_rate`,
    concat(`v`.`material_basic_amount`, ':Material Basic Amount:O:N:') as `material_basic_amount`,
    concat(`v`.`material_discount_percent`, ':Material Discount Percent:O:N:') as `material_discount_percent`,
    concat(`v`.`material_discount_amount`, ':Material Discount Amount:O:N:') as `material_discount_amount`,
    concat(`v`.`material_taxable_amount`, ':Material Taxable Amount:O:N:') as `material_taxable_amount`,
    concat(`v`.`material_cgst_percent`, ':Material Cgst Percent:O:N:') as `material_cgst_percent`,
    concat(`v`.`material_cgst_total`, ':Material Cgst Total:O:N:') as `material_cgst_total`,
    concat(`v`.`material_sgst_percent`, ':Material Sgst Percent:O:N:') as `material_sgst_percent`,
    concat(`v`.`material_sgst_total`, ':Material SGST Total:O:N:') as `material_sgst_total`,
    concat(`v`.`material_igst_percent`, ':Material IGST Percent:O:N:') as `material_igst_percent`,
    concat(`v`.`material_igst_total`, ':Material IGST Total:O:N:') as `material_igst_total`,
    concat(`v`.`material_total_amount`, ':Material Total Amount:O:N:') as `material_total_amount`,
    concat(`v`.`dispatch_note_nos`, ':Dispatch Note Nos:O:N:') as `dispatch_note_nos`,
    concat(`v`.`dispatch_challan_nos`, ':Dispatch Challan Nos:O:N:') as `dispatch_challan_nos`,
    concat(`v`.`invoice_nos`, ':Invoice Nos:O:N:(Closed,Open)') as `invoice_nos`,
    '0' as `previous_dispatch_quantity`,
    '0' as `previous_dispatch_weight`,
    '0' as `stock_quantity`,
    '0' as `stock_weight`,
    concat(`v`.`dispatched_quantity`, ':Dispatched Quantity:O:N:') as `dispatched_quantity`,
    concat(`v`.`dispatched_weight`, ':Dispatched Weight:O:N:') as `dispatched_weight`,
    concat(`v`.`production_issue_quantity`, ':Issue Quantity:O:N:') as `production_issue_quantity`,
    concat(`v`.`production_issue_weight`, ':Issue Weight:O:N:') as `production_issue_weight`,
    concat(`v`.`production_issue_return_quantity`, ':Issue Return Quantity:O:N:') as `production_issue_return_quantity`,
    concat(`v`.`production_issue_return_weight`, ':Issue Return Weight:O:N:') as `production_issue_return_weight`,
    concat(`v`.`production_issue_rejection_quantity`, ':Issue Rejection Quantity:O:N:') as `production_issue_rejection_quantity`,
    concat(`v`.`production_issue_rejection_weight`, ':Issue Rejection Weight:O:N:') as `production_issue_rejection_weight`,
    concat(`v`.`product_material_hsn_sac_code`, ':Hsn Sac Code:O:N:') as `product_material_hsn_sac_code`,
    concat(`v`.`product_material_hsn_sac_rate`, ':Hsn Sac Rate:O:N:') as `product_material_hsn_sac_rate`,
    concat(`v`.`customer_email`, ':Customer Email:O:N:') as `customer_email`,
    concat(`v`.`customer_state_name`, ':Customer State:O:N:') as `customer_state_name`,
    concat(`v`.`customer_district_name`, ':Customer District:O:N:') as `customer_district_name`,
    concat(`v`.`customer_city_name`, ':Customer City:O:N:') as `customer_city_name`,
    concat(`v`.`consignee_state_name`, ':Consignee State:O:N:') as `consignee_state_name`,
    concat(`v`.`consignee_district_name`, ':Consignee District:O:N:') as `consignee_district_name`,
    concat(`v`.`consignee_city_name`, ':Consignee City:O:N:') as `consignee_city_name`,
    concat(`v`.`basic_total`, ':Basic Total:O:N:') as `basic_total`,
    concat(`v`.`transport_amount`, ':Transport Amount:O:N:') as `transport_amount`,
    concat(`v`.`material_freight_amount`, ':Freight Amount:O:N:') as `material_freight_amount`,
    concat(`v`.`freight_amount`, ':Freight Amount:O:N:') as `freight_amount`,
    concat(`v`.`packing_amount`, ':Packing Amount:O:N:') as `packing_amount`,
    concat(`v`.`discount_percent`, ':Discount Percent:O:N:') as `discount_percent`,
    concat(`v`.`discount_amount`, ':Discount Amount:O:N:') as `discount_amount`,
    concat(`v`.`other_amount`, ':Other Amount:O:N:') as `other_amount`,
    concat(`v`.`taxable_total`, ':Taxable Total:O:N:') as `taxable_total`,
    concat(`v`.`cgst_percent`, ':CGST Percent:O:N:') as `cgst_percent`,
    concat(`v`.`cgst_total`, ':CGST Total:O:N:') as `cgst_total`,
    concat(`v`.`sgst_percent`, ':SGST Percent:O:N:') as `sgst_percent`,
    concat(`v`.`sgst_total`, ':SGST Total:O:N:') as `sgst_total`,
    concat(`v`.`igst_percent`, ':IGST Percent:O:N:') as `igst_percent`,
    concat(`v`.`igst_total`, ':IGST Total:O:N:') as `igst_total`,
    concat(`v`.`grand_total`, ':Grand Total:O:N:') as `grand_total`,
    concat(`v`.`roundoff`, ':RoundOff:O:N:') as `roundoff`,
    concat(`v`.`sales_order_version`, ':Sales Order Version:O:N:') as `sales_order_version`,
    concat(`v`.`overall_schedule_date`, ':Overall Schedule Date:Y:D:') as `overall_schedule_date`,
    concat(`v`.`other_terms_conditions`, ':Other Terms Conditions:O:N:') as `other_terms_conditions`,
    concat(`v`.`product_type_short_name`, ':Product Type Short Name:O:N:') as `product_type_short_name`,
    concat(`v`.`sales_order_creation_type`, ':Sales Order Creation Type:O:N:') as `sales_order_creation_type`,
    concat(`v`.`company_name`, ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(`v`.`company_branch_name`, ':Company Branch:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(`v`.`financial_year`, ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Modified On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`v`.`sales_order_item_status`, ':Sales Order Item Status:O:N:') as `sales_order_item_status`,
    concat(`v`.`sales_order_status`, ':Sales Order Status:O:N:') as `sales_order_status`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`v`.`sales_order_master_transaction_id`, ':Sales Order Master Transaction Id:O:N:') as `sales_order_master_transaction_id`,
    concat(`v`.`sales_order_details_transaction_id`, ':Sales Order Details Transaction Id:N:N:') as `sales_order_details_transaction_id`,
    concat(`v`.`product_type_id`, ':Product Type Id:N:N:') as `product_type_id`,
    concat(`v`.`product_material_id`, ':Product Material Id:N:N:') as `product_material_id`,
    concat(`v`.`product_material_unit_id`, ':Product Material Unit Id:N:N:') as `product_material_unit_id`,
    concat(`v`.`product_material_packing_id`, ':Product Material Packing Id:N:N:') as `product_material_packing_id`,
    concat(`v`.`product_material_hsn_code_id`, ':Product Material Hsn Code Id:N:N:') as `product_material_hsn_code_id`
from
    `mtv_sales_order_details_trading` `v`
limit 1;




create or replace
algorithm = UNDEFINED view `mtv_sales_order_schedules_trading` as
select
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `mt`.`sales_order_version` as `sales_order_version`,
    case
        `mt`.`sales_order_schedules_trading_item_status` when 'P' then 'Pending'
        when 'A' then 'Aprroved'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Dispatched'
        when 'C' then 'Dispatched'
        when 'X' then 'Canceled'
        when 'O' then 'PO'
        when 'Z' then 'PreeClosed'
        else 'GRN'
    end as `sales_order_schedules_trading_item_status_desc`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `mt`.`product_type` as `product_type`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `rmfgsr`.`product_material_code` as `product_material_code`,
    `rmfgsr`.`product_material_tech_spect` as `product_material_tech_spect`,
    `rmfgsr`.`product_material_stock_unit_name` as `product_material_stock_unit_name`,
    `rmfgsr`.`product_material_std_weight` as `product_material_std_weight`,
    `rmfgsr`.`product_material_technical_name` as `product_material_technical_name`,
    `rmfgsr`.`product_material_make_name` as `product_material_make_name`,
    `rmfgsr`.`product_material_type_name` as `product_material_type_name`,
    `rmfgsr`.`product_material_grade_name` as `product_material_grade_name`,
    `rmfgsr`.`product_material_shape_name` as `product_material_shape_name`,
    `rmfgsr`.`product_material_packing_name` as `product_material_packing_name`,
    `mt`.`material_quantity` as `material_quantity`,
    `mt`.`material_weight` as `material_weight`,
    `mt`.`product_material_schedule_quantity` as `product_material_schedule_quantity`,
    `mt`.`product_material_schedule_weight` as `product_material_schedule_weight`,
    `mt`.`expected_schedule_date` as `expected_schedule_date`,
    `mt`.`product_material_issue_quantity` as `product_material_issue_quantity`,
    `mt`.`product_material_issue_weight` as `product_material_issue_weight`,
    `mt`.`material_issue_date` as `material_issue_date`,
    `mt`.`sales_order_schedules_trading_item_status` as `sales_order_schedules_trading_item_status`,
    `mt`.`so_sr_no` as `so_sr_no`,
    `mt`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`sales_order_schedules_transaction_id` as `sales_order_schedules_transaction_id`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`sales_order_master_transaction_id` as `sales_order_master_transaction_id`,
    `mt`.`sales_order_details_transaction_id` as `sales_order_details_transaction_id`,
    `mt`.`product_type_id` as `product_type_id`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_unit_id` as `product_material_unit_id`
from
    ((`mt_sales_order_schedules_trading` `mt`
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `mt`.`product_material_id`))
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `mt`.`company_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
where
    `mt`.`is_delete` = 0;