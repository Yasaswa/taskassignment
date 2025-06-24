-- stv_indent_material_issue_summary source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_summary` AS
select
    `st`.`issue_no` AS `issue_no`,
    `st`.`issue_date` AS `issue_date`,
    `st`.`issue_version` AS `issue_version`,
    `st`.`issue_group_type` AS `issue_group_type`,
    `st`.`indent_issue_type` AS `indent_issue_type`,
    `st`.`indent_no` AS `indent_no`,
    `st`.`indent_date` AS `indent_date`,
    `st`.`indent_version` AS `indent_version`,
    case
        `st`.`issue_status` when 'MI' then 'Material Issue'
        when 'C' then 'Completed'
        when 'I' then 'Partial Issue'
        when 'AC' then 'Accepted'
        when 'A' then 'Approved'
        else 'Pending'
    end AS `issue_status_desc`,
    case
        `st`.`issue_source` when 'S' then 'SO Based'
        when 'O' then 'Direct'
        else 'Internal'
    end AS `indent_source_desc`,
    `vp`.`department_name` AS `department_name`,
    `sd`.`department_name` AS `sub_department_name`,
    `re`.`employee_name` AS `requisition_by_name`,
    `ae`.`employee_name` AS `approved_by_name`,
    `ie`.`employee_name` AS `issued_by_name`,
    `c`.`customer_name` AS `customer_name`,
    `st`.`customer_order_no` AS `customer_order_no`,
    `st`.`customer_order_date` AS `customer_order_date`,
    `c`.`state_name` AS `customer_state_name`,
    `st`.`expected_schedule_date` AS `expected_schedule_date`,
    `fm`.`cost_center_name` AS `cost_center_name`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `c`.`cust_branch_gst_no` AS `cust_branch_gst_no`,
    `st`.`is_active` AS `is_active`,
    `st`.`is_delete` AS `is_delete`,
    `st`.`created_by` AS `created_by`,
    `st`.`created_on` AS `created_on`,
    `st`.`modified_by` AS `modified_by`,
    `st`.`modified_on` AS `modified_on`,
    `st`.`deleted_by` AS `deleted_by`,
    `st`.`deleted_on` AS `deleted_on`,
    `st`.`remark` AS `remark`,
    `st`.`indent_issue_type_id` AS `indent_issue_type_id`,
    `st`.`customer_id` AS `customer_id`,
    `st`.`department_id` AS `department_id`,
    `st`.`sub_department_id` AS `sub_department_id`,
    `st`.`issued_by_id` AS `issued_by_id`,
    `st`.`approved_by_id` AS `approved_by_id`,
    `st`.`requisition_by_id` AS `requisition_by_id`,
    `st`.`requisition_date` AS `requisition_date`,
    `st`.`approved_date` AS `approved_date`,
    `st`.`company_id` AS `company_id`,
    `st`.`company_branch_id` AS `company_branch_id`,
    `st`.`financial_year` AS `financial_year`,
    `st`.`issue_master_transaction_id` AS `issue_master_transaction_id`,
    `st`.`cost_center_id` AS `cost_center_id`,
    `st`.`issue_status` AS `issue_status`,
    `st`.`issue_source` AS `issue_source`,
    `st`.`issue_no` AS `field_name`,
    `st`.`issue_version` AS `field_id`
from
    ((((((((`st_indent_material_issue_master` `st`
left join `cmv_company_summary` `v` on
    (`v`.`company_branch_id` = `st`.`company_branch_id`
        and `v`.`company_id` = `st`.`company_id`))
left join `cm_department` `vp` on
    (`vp`.`department_id` = `st`.`department_id`))
left join `cm_department` `sd` on
    (`sd`.`department_id` = `st`.`sub_department_id`))
left join `cmv_customer_summary` `c` on
    (`c`.`customer_id` = `st`.`customer_id`))
left join `cm_employee` `re` on
    (`re`.`employee_id` = `st`.`requisition_by_id`))
left join `cm_employee` `ae` on
    (`ae`.`employee_id` = `st`.`approved_by_id`))
left join `cm_employee` `ie` on
    (`ie`.`employee_id` = `st`.`issued_by_id`))
left join `fm_cost_center` `fm` on
    (`fm`.`cost_center_id` = `st`.`cost_center_id`))
where
    `st`.`is_delete` = 0
order by
    `st`.`issue_no`,
    `st`.`issue_version`;