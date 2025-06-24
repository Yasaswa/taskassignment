-- erp_development.htv_short_leave source

create or replace
algorithm = UNDEFINED view `htv_short_leave` as
select
    `ht`.`leave_type` as `leave_type`,
    `ht`.`employee_id` as `employee_id`,
    `ht`.`employee_code` as `employee_code`,
    `ht`.`machine_employee_code` as `machine_employee_code`,
    `ht`.`out_time` as `out_time`,
    `ht`.`in_time` as `in_time`,
    `ht`.`total_hours` as `total_hours`,
    `ht`.`leave_reason` as `leave_reason`,
    `ht`.`approved_by` as `approved_by`,
    `ht`.`approval_status` as `approval_status`,
    `ht`.`short_leave_date` as `short_leave_date`,
    `ht`.`financial_year` as `financial_year`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `e2`.`department_name` as `department_name`,
    `e2`.`sub_department_name` as `sub_department_name`,
    `e3`.`employee_type_group` as `employee_type_group`,
    `e`.`employee_name` as `approved_by_name`,
    `e1`.`employee_name` as `employee_name`,
    case
        `ht`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `ht`.`is_delete` as `is_delete`,
    `ht`.`created_by` as `created_by`,
    `ht`.`created_on` as `created_on`,
    `ht`.`modified_by` as `modified_by`,
    `ht`.`modified_on` as `modified_on`,
    `ht`.`deleted_by` as `deleted_by`,
    `ht`.`deleted_on` as `deleted_on`,
    `ht`.`company_id` as `company_id`,
    `ht`.`company_branch_id` as `company_branch_id`,
    `ht`.`short_leave_transaction_id` as `short_leave_transaction_id`,
    `ht`.`short_leave_id` as `short_leave_id`,
    `ht`.`department_id` as `department_id`,
    `ht`.`sub_department_id` as `sub_department_id`,
    `ht`.`short_leave_transaction_id` as `field_id`
from
    ((((((`ht_short_leave` `ht`
left join `cmv_company` `v` on
    (`ht`.`company_id` = `v`.`company_id`))
left join `cm_department` `d` on
    (`ht`.`company_id` = `d`.`company_id`
        and `ht`.`department_id` = `d`.`department_id`))
left join `cm_employee` `e` on
    (`ht`.`approved_by` = `e`.`employee_id`))
left join `cmv_employee` `e1` on
    (`ht`.`employee_id` = `e1`.`employee_id`))
left join `cmv_employee` `e2` on
    (`ht`.`employee_id` = `e2`.`employee_id`
        and `ht`.`company_id` = `e2`.`company_id`))
left join `cmv_employee` `e3` on
    (`ht`.`employee_code` = `e3`.`employee_code`
        and `ht`.`company_id` = `e3`.`company_id`))
where
    `ht`.`is_delete` = 0;