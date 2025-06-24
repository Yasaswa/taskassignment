ALTER TABLE mt_dispatch_challan_sized_yarn_master ADD issued_weight DECIMAL(18,2) DEFAULT 0.00 NOT NULL;
ALTER TABLE mt_dispatch_challan_sized_yarn_master ADD issued_quantity DECIMAL(18,2) DEFAULT 0.00 NOT NULL;
ALTER TABLE mt_dispatch_challan_sized_yarn_master ADD net_weight DECIMAL(18,2) DEFAULT 0.00 NOT NULL;


CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `mtv_dispatch_challan_sized_yarn_master` AS
select
    `mt`.`dispatch_challan_sized_yarn_id` AS `dispatch_challan_sized_yarn_id`,
    `mt`.`company_id` AS `company_id`,
    `mt`.`company_branch_id` AS `company_branch_id`,
    `mt`.`financial_year` AS `financial_year`,
    `mt`.`job_type_id` AS `job_type_id`,
    `ap`.`property_name` AS `job_type_name`,
    `mt`.`set_no` AS `set_no`,
    `mt`.`yarn_count` AS `yarn_count`,
    `mt`.`product_material_id` AS `product_material_id`,
    `mt`.`product_material_name` AS `product_material_name`,
    `mt`.`sales_dispatch_type` AS `sales_dispatch_type`,
    `mt`.`dispatch_hsnTax_type` AS `dispatch_hsnTax_type`,
    `mt`.`dispatch_sales_type` AS `dispatch_sales_type`,
    `mt`.`dispatch_voucher_type` AS `dispatch_voucher_type`,
    `mt`.`total_ends` AS `total_ends`,
    `mt`.`dispatch_challan_type_id` AS `dispatch_challan_type_id`,
    `mt`.`dispatch_challan_type` AS `dispatch_challan_type`,
    `mt`.`dispatch_challan_creation_type` AS `dispatch_challan_creation_type`,
    `mt`.`dispatch_challan_no` AS `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` AS `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` AS `dispatch_challan_version`,
    `mt`.`customer_id` AS `customer_id`,
    `mt`.`customer_order_no` AS `customer_order_no`,
    `mt`.`agent_id` AS `agent_id`,
    `a`.`agent_name` AS `agent_name`,
    `a`.`agent_address1` AS `agent_address1`,
    `a`.`agent_cell_no` AS `agent_cell_no`,
    `mt`.`customer_contacts_ids` AS `customer_contacts_ids`,
    `mt`.`customer_state_id` AS `customer_state_id`,
    `mt`.`customer_city_id` AS `customer_city_id`,
    `mt`.`consignee_id` AS `consignee_id`,
    `mt`.`consignee_state_id` AS `consignee_state_id`,
    `mt`.`consignee_city_id` AS `consignee_city_id`,
    `mt`.`dispatch_date` AS `dispatch_date`,
    `mt`.`approved_by_id` AS `approved_by_id`,
    `mt`.`approved_date` AS `approved_date`,
    `mt`.`dispatch_challan_status` AS `dispatch_challan_status`,
    `mt`.`rate` AS `rate`,
    `mt`.`weight` AS `weight`,
    `mt`.`issued_weight` AS `issued_weight`,
    `mt`.`issued_quantity` AS `issued_quantity`,
    `mt`.`net_weight` AS `net_weight`,
    `mt`.`dispatch_payment_terms` AS `dispatch_payment_terms`,
    `mt`.`other_terms_conditions` AS `other_terms_conditions`,
    `mt`.`remark` AS `remark`,
    `mt`.`vehicle_no` AS `vehicle_no`,
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
    `mt`.`driver_name` AS `driver_name`,
    `mt`.`yarn_bill_no` AS `yarn_bill_no`,
    `c`.`customer_name` AS `customer_name`,
    `c`.`cust_branch_phone_no` AS `customer_phone`,
    `c`.`cust_branch_EmailId` AS `customer_email`,
    `c`.`cust_branch_address1` AS `cust_branch_address1`,
    `c`.`cust_branch_gst_no` AS `cust_branch_gst_no`,
    `ct`.`city_name` AS `customer_city_name`,
    `st`.`state_name` AS `customer_state_name`,
    `cc`.`customer_name` AS `consignee_name`,
    `cc`.`cust_branch_address1` AS `consignee_address`,
    `cc`.`cust_branch_EmailId` AS `consignee_email`,
    `st1`.`state_name` AS `consignee_state_name`,
    `ct1`.`city_name` AS `consignee_city_name`,
    `e1`.`employee_name` AS `approved_by_name`,
    `v`.`company_legal_name` AS `company_name`,
    `cb`.`company_branch_name` AS `company_branch_name`,
    `cb`.`branch_cell_no` AS `company_cell_no`,
    `cb`.`branch_EmailId` AS `company_emailId`,
    `cb`.`branch_website` AS `company_website`,
    `cb`.`branch_gst_no` AS `company_gst_no`,
    `cb`.`branch_pan_no` AS `company_pan_no`,
    `cb`.`branch_address1` AS `company_address`,
    `cb`.`branch_pincode` AS `company_pincode`
from
    (((((((((((`mt_dispatch_challan_sized_yarn_master` `mt`
left join `cm_company` `v` on
    (`v`.`company_id` = `mt`.`company_id`))
left join `am_properties` `ap` on
    (`ap`.`property_id` = `mt`.`job_type_id`
        and `ap`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `mt`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `cmv_customer` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `cmv_customer` `cc` on
    (`cc`.`company_id` = `mt`.`company_id`
        and `cc`.`customer_id` = `mt`.`consignee_id`))
left join `cm_agent` `a` on
    (`a`.`agent_id` = `mt`.`agent_id`
        and `a`.`is_delete` = 0))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `mt`.`customer_city_id`))
left join `cm_city` `ct1` on
    (`ct1`.`city_id` = `mt`.`consignee_city_id`))
left join `cm_state` `st` on
    (`st`.`state_id` = `mt`.`customer_state_id`))
left join `cm_state` `st1` on
    (`st1`.`state_id` = `mt`.`consignee_state_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_delete` = 0
        and `e1`.`employee_id` = `mt`.`approved_by_id`))
where
    `mt`.`is_delete` = 0;