ALTER TABLE mt_dispatch_challan_sized_yarn_details CHANGE beam_no beam_id bigint(20) DEFAULT NULL NULL COMMENT 'data entry textbox';


create or replace
algorithm = UNDEFINED view `mtv_dispatch_challan_sized_yarn_details` as
select
    `mt`.`dispatch_challan_sized_yarn_details_id` as `dispatch_challan_sized_yarn_details_id`,
    `mt`.`dispatch_challan_sized_yarn_id` as `dispatch_challan_sized_yarn_id`,
    `mt`.`company_id` as `company_id`,
    `mt`.`company_branch_id` as `company_branch_id`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`set_no` as `set_no`,
    `mt`.`total_ends` as `total_ends`,
    `mt`.`beam_id` as `beam_id`,
    `mt`.`product_material_name` as `product_material_name`,
    `mt`.`dispatch_challan_type` as `dispatch_challan_type`,
    `mt`.`dispatch_challan_type_id` as `dispatch_challan_type_id`,
    `mt`.`dispatch_challan_creation_type` as `dispatch_challan_creation_type`,
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_status` as `dispatch_challan_status`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_name` as `customer_name`,
    `mt`.`dispatch_by_id` as `dispatch_by_id`,
    `mt`.`approved_by_id` as `approved_by_id`,
    `mt`.`approved_date` as `approved_date`,
    `mt`.`sizing_length` as `sizing_length`,
    `mt`.`gross_weight` as `gross_weight`,
    `mt`.`tare_weight` as `tare_weight`,
    `mt`.`net_weight` as `net_weight`,
    `mt`.`remark` as `remark`,
    `mmt`.`product_material_id` as `product_material_id`,
    `mmt`.`agent_id` as `agent_id`,
    `mmt`.`customer_contacts_ids` as `customer_contacts_ids`,
    `mmt`.`customer_state_id` as `customer_state_id`,
    `mmt`.`customer_city_id` as `customer_city_id`,
    `mmt`.`consignee_id` as `consignee_id`,
    `mmt`.`consignee_state_id` as `consignee_state_id`,
    `mmt`.`consignee_city_id` as `consignee_city_id`,
    `mmt`.`rate` as `rate`,
    `mmt`.`weight` as `weight`,
    `mmt`.`other_terms_conditions` as `other_terms_conditions`,
    `mmt`.`driver_name` as `driver_name`,
    `mmt`.`yarn_bill_no` as `yarn_bill_no`,
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
    `c`.`cust_branch_phone_no` as `customer_phone`,
    `c`.`cust_branch_EmailId` as `customer_email`,
    `ct`.`city_name` as `customer_city_name`,
    `st`.`state_name` as `customer_state_name`,
    `e`.`employee_name` as `dispatch_by_name`,
    `e1`.`employee_name` as `approved_by_name`,
    `v`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`
from
    ((((((((((`mt_dispatch_challan_sized_yarn_details` `mt`
left join `cmv_company` `v` on
    (`v`.`company_branch_id` = `mt`.`company_branch_id`
        and `v`.`company_id` = `mt`.`company_id`))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `mt`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `cmv_customer` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `mt_dispatch_challan_sized_yarn_master` `mmt` on
    (`mmt`.`dispatch_challan_sized_yarn_id` = `mt`.`dispatch_challan_sized_yarn_id`
        and `mmt`.`is_delete` = 0))
left join `cm_city` `ct` on
    (`ct`.`city_id` = `mmt`.`customer_city_id`))
left join `cm_city` `ct1` on
    (`ct1`.`city_id` = `mmt`.`consignee_city_id`))
left join `cm_state` `st` on
    (`st`.`state_id` = `mmt`.`customer_state_id`))
left join `cm_state` `st1` on
    (`st1`.`state_id` = `mmt`.`consignee_state_id`))
left join `cm_employee` `e` on
    (`e`.`company_branch_id` = `mt`.`company_branch_id`
        and `e`.`company_id` = `mt`.`company_id`
        and `e`.`employee_id` = `mt`.`dispatch_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`company_branch_id` = `mt`.`company_branch_id`
        and `e1`.`company_id` = `mt`.`company_id`
        and `e1`.`employee_id` = `mt`.`approved_by_id`))
where
    `mt`.`is_delete` = 0;