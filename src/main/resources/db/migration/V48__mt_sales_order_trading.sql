-- Changes related to Sales Order (Added Sales Type)


-- Table :- 'am_properties_master'
INSERT INTO am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) VALUES
	 (1,'Sales Type',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

-- Table :- 'am_properties'
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (180,'Sales Type',1,'Domestic','Domestic',1,0,'9016569017','2024-09-07 10:28:55.000',NULL,'2024-09-07 10:28:55.000',NULL,NULL,'',''),
	 (180,'Sales Type',1,'Exports','Exports',1,0,'9016569017','2024-09-07 10:29:14.000',NULL,'2024-09-07 10:29:14.000',NULL,NULL,'','');


-- Table :- 'mt_sales_order_master_trading'
ALTER TABLE mt_sales_order_master_trading ADD sales_type bigint(20) DEFAULT NULL NULL;

-- mtv_sales_order_master_trading_summary source

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
    `c`.`customer_name` as `customer_name`,
    `c`.`state_name` as `customer_state_name`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `c`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `c`.`cust_branch_pan_no` as `cust_branch_pan_no`,
    `c`.`cust_branch_phone_no` as `cust_branch_phone_no`,
    `c`.`cust_branch_address1` as `cust_branch_address1`,
    `cc`.`customer_name` as `consignee_name`,
    `cc`.`state_name` as `consignee_state_name`,
    `cc`.`cust_branch_gst_no` as `consignee_gst_no`,
    `cc`.`cust_branch_pan_no` as `consignee_pan_no`,
    `cc`.`cust_branch_phone_no` as `consignee_phone_no`,
    `cc`.`cust_branch_address1` as `consignee_address1`,
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
    `v`.`company_branch_name` as `company_branch_name`,
    `v`.`company_pincode` as `company_pincode`,
    `v`.`company_phone_no` as `company_phone_no`,
    `v`.`state_name` as `company_branch_state`,
    `v`.`company_cell_no` as `company_cell_no`,
    `v`.`company_EmailId` as `company_EmailId`,
    `v`.`company_website` as `company_website`,
    `v`.`company_gst_no` as `company_gst_no`,
    `v`.`company_pan_no` as `company_pan_no`,
    `v`.`company_address1` as `company_address1`,
    `v`.`company_address2` as `company_address2`,
    `mt`.`financial_year` as `financial_year`,
    `c`.`cust_branch_EmailId` as `customer_email`,
    `c`.`city_name` as `customer_city_name`,
    `c`.`district_name` as `customer_district_name`,
    `cc`.`city_name` as `consignee_city_name`,
    `cc`.`district_name` as `consignee_district_name`,
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
    (((((((((`mt_sales_order_master_trading` `mt`
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `mt`.`company_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
left join `sm_product_type` `pt` on
    (`mt`.`sales_order_type_id` = `pt`.`product_type_id`
        and `pt`.`is_delete` = 0))
left join `cmv_customer_summary` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `cmv_customer_summary` `cc` on
    (`cc`.`company_id` = `mt`.`company_id`
        and `cc`.`customer_id` = `mt`.`consignee_id`))
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
where
    `mt`.`is_delete` = 0;