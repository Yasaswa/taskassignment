ALTER TABLE xt_weaving_production_warping_stoppage MODIFY COLUMN production_set_no varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;

create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_sized_yarn_master` as
select
    `mt`.`dispatch_challan_sized_yarn_id` as `dispatch_challan_sized_yarn_id`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`set_no` as `set_no`,
    `mt`.`yarn_count` as `yarn_count`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_name` as `product_material_name`,
    `mt`.`total_ends` as `total_ends`,
    `mt`.`dispatch_challan_type_id` as `dispatch_challan_type_id`,
    `mt`.`dispatch_challan_type` as `dispatch_challan_type`,
    `mt`.`dispatch_challan_creation_type` as `dispatch_challan_creation_type`,
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`agent_id` as `agent_id`,
    `a`.`agent_name` as `agent_name`,
    `a`.`agent_address1` as `agent_address1`,
    `a`.`agent_cell_no` as `agent_cell_no`,
    `mt`.`customer_contacts_ids` as `customer_contacts_ids`,
    `mt`.`customer_state_id` as `customer_state_id`,
    `mt`.`customer_city_id` as `customer_city_id`,
    `mt`.`consignee_id` as `consignee_id`,
    `mt`.`consignee_state_id` as `consignee_state_id`,
    `mt`.`consignee_city_id` as `consignee_city_id`,
    `mt`.`dispatch_date` as `dispatch_date`,
    `mt`.`approved_by_id` as `approved_by_id`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`dispatch_challan_status` as `dispatch_challan_status`,
    `mt`.`rate` as `rate`,
    `mt`.`weight` as `weight`,
    `mt`.`dispatch_payment_terms` as `dispatch_payment_terms`,
    `mt`.`other_terms_conditions` as `other_terms_conditions`,
    `mt`.`remark` as `remark`,
    `mt`.`vehicle_no` as `vehicle_no`,
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
    `mt`.`driver_name` as `driver_name`,
    `mt`.`yarn_bill_no` as `yarn_bill_no`,
    `c`.`customer_name` as `customer_name`,
    `c`.`cust_branch_phone_no` as `customer_phone`,
    `c`.`cust_branch_EmailId` as `customer_email`,
    `c`.`cust_branch_address1` as `cust_branch_address1`,
    `c`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `ct`.`city_name` as `customer_city_name`,
    `st`.`state_name` as `customer_state_name`,
    `cc`.`customer_name` as `consignee_name`,
    `cc`.`cust_branch_address1` as `consignee_address`,
    `cc`.`cust_branch_EmailId` as `consignee_email`,
    `st1`.`state_name` as `consignee_state_name`,
    `ct1`.`city_name` as `consignee_city_name`,
    `e1`.`employee_name` as `approved_by_name`,
    `v`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `cb`.`branch_cell_no` as `company_cell_no`,
    `cb`.`branch_EmailId` as `company_emailId`,
    `cb`.`branch_website` as `company_website`,
    `cb`.`branch_gst_no` as `company_gst_no`,
    `cb`.`branch_pan_no` as `company_pan_no`,
    `cb`.`branch_address1` as `company_address`,
    `cb`.`branch_pincode` as `company_pincode`
from
    ((((((((((`mt_dispatch_challan_sized_yarn_master` `mt`
left join `cm_company` `v` on
    (`v`.`company_id` = `mt`.`company_id`))
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