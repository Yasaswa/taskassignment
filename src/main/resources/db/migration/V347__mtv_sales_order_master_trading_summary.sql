ALTER TABLE mt_sales_order_master_trading ADD lot_no BIGINT(50) NULL;
ALTER TABLE mt_sales_order_master_trading ADD supplier_branch_id BIGINT(20) NULL;

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
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Issue'
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
    `mt`.`supplier_branch_id`as`supplier_branch_id`,
    `mt`.`lot_no`as`lot_no`,
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
        and `e`.`company_id` = `mt`.`company_id`))
left join `cm_agent` `a` on
    (`a`.`agent_id` = `mt`.`agent_id`
        and `a`.`company_id` = `mt`.`company_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `mt`.`freight_hsn_code_id`))
left join `cm_supplier_branch` `csb` on
    (`csb`.`supp_branch_id` = `mt`.`supplier_branch_id`))
where
    `mt`.`is_delete` = 0;


create or replace
algorithm = UNDEFINED view `mtv_sales_order_master_trading_summary_rpt` as
select
    concat(ifnull(`v`.`sales_order_no`, ''), ':Sales Order No:Y:C:mtv_sales_order_master_trading_summary:O') as `sales_order_no`,
    concat(ifnull(`v`.`sales_order_version`, ''), ':Sales Order Version:O:N:') as `sales_order_version`,
    concat(ifnull(`v`.`sales_order_date`, ''), ':Sales Order Date:Y:D:') as `sales_order_date`,
    concat(ifnull(`v`.`sales_order_status`, ''), ':Sales Order Status:Y:H:(Approved,Completed,Canceled,Rejected,Partial Issue,PreeClosed,Pending)') as `sales_order_status_desc`,
    concat(ifnull(`v`.`sales_order_life`, ''), ':Sales Order Life:Y:H:(Closed,Open)') as `sales_order_life_desc`,
    concat(ifnull(`v`.`sales_order_creation_type`, ''), ':Sales Order Creation Type:Y:H:(Mannual,Auto,Reorder)') as `sales_order_creation_type_desc`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:O:C:cmv_customer_summary:O') as `customer_name`,
    concat(ifnull(`v`.`supp_branch_name`, ''), ':Supplier Name:Y:C:mtv_sales_order_master_trading_summary:O') as `supp_branch_name`,
    concat(ifnull(`v`.`lot_no`, ''), ':Lot No:Y:T:') as `lot_no`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:C:mtv_sales_order_master_trading_summary:O') as `customer_order_no`,
    concat(ifnull(`v`.`customer_order_Date`, ''), ':Customer Order Date:Y:D:') as `customer_order_Date`,
    concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') as `product_type_short_name`,
    concat(ifnull(`v`.`sales_quotation_no`, ''), ':Sales Quotation No:Y:C:mtv_sales_order_master_trading_summary:O') as `sales_quotation_no`,
    concat(ifnull(`v`.`sales_quotation_date`, ''), ':Sales Quotation Date:Y:D:') as `sales_quotation_date`,
    concat(ifnull(`v`.`hsn_sac_type`, ''), ':Hsn Sac Type:O:N:') as `hsn_sac_type`,
    concat(ifnull(`v`.`hsn_sac_code`, ''), ':Hsn Sac Code:O:N:') as `hsn_sac_code`,
    concat(ifnull(`v`.`hsn_sac_description`, ''), ':Hsn Sac Description:O:N:') as `hsn_sac_description`,
    concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Hsn Sac Rate:O:N:') as `hsn_sac_rate`,
    concat(ifnull(`v`.`customer_name`, ''), ':Consignee Name:Y:C:cmv_customer_summary:F') as `consignee_name`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') as `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`overall_schedule_date`, ''), ':Overall Schedule Date:Y:D:') as `overall_schedule_date`,
    concat(ifnull(`v`.`basic_total`, ''), ':Basic Total:O:N:') as `basic_total`,
    concat(ifnull(`v`.`transport_amount`, ''), ':Transport Amount:O:N:') as `transport_amount`,
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