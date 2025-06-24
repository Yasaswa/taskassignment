-- erp_dev_temp_1.stv_indent_material_issue_return_master source

create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_return_master` as
select
    `stm`.`issue_return_master_transaction_id` as `issue_return_master_transaction_id`,
    `stm`.`company_id` as `company_id`,
    `stm`.`company_branch_id` as `company_branch_id`,
    `stm`.`financial_year` as `financial_year`,
    `stm`.`issue_return_no` as `issue_return_no`,
    `stm`.`issue_return_date` as `issue_return_date`,
    `stm`.`indent_issue_return_type_id` as `indent_issue_return_type_id`,
    `stm`.`indent_issue_return_type` as `indent_issue_return_type`,
    `dpt`.`department_name` as `department_name`,
    `sdpt`.`department_name` as `sub_department_name`,
    `e`.`employee_name` as `received_by_name`,
    `e1`.`employee_name` as `return_by_name`,
    `stm`.`department_id` as `department_id`,
    `stm`.`sub_department_id` as `sub_department_id`,
    `stm`.`issue_no` as `issue_no`,
    `std`.`issue_date` as `issue_date`,
    `stm`.`return_by_id` as `return_by_id`,
    `stm`.`issue_return_status` as `issue_return_status`,
    case
        `stm`.`issue_return_status` when 'R' then 'Received'
        else 'Pending'
    end as `issue_return_status_desc`,
    `stm`.`received_by_id` as `received_by_id`,
    `stm`.`received_date` as `received_date`,
    `stm`.`remark` as `remark`,
    case
        when `stm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `stm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `stm`.`is_active` as `is_active`,
    `stm`.`is_delete` as `is_delete`,
    `stm`.`created_by` as `created_by`,
    `stm`.`created_on` as `created_on`,
    `stm`.`modified_by` as `modified_by`,
    `stm`.`modified_on` as `modified_on`,
    `stm`.`deleted_by` as `deleted_by`,
    `stm`.`deleted_on` as `deleted_on`,
    `std`.`set_no` as `set_no`
from
    (((((`st_indent_material_issue_return_master` `stm`
left join `st_indent_material_issue_master` `std` on
    (`std`.`issue_no` = `stm`.`issue_no`
        and `std`.`is_delete` = 0))
left join `cmv_department` `dpt` on
    (`dpt`.`department_id` = `stm`.`department_id`))
left join `cmv_department` `sdpt` on
    (`sdpt`.`department_id` = `stm`.`sub_department_id`))
left join `cm_employee` `e` on
    (`e`.`is_delete` = 0
        and `e`.`employee_id` = `stm`.`received_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_delete` = 0
        and `e1`.`employee_id` = `stm`.`return_by_id`))
where
    `stm`.`is_delete` = 0;