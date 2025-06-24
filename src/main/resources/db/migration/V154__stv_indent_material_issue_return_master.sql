
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
    `std` .`issue_date`,
    `stm`.`return_by_id` as `return_by_id`,
    `stm`.`issue_return_status` as `issue_return_status`,
       case
        `stm`.`issue_return_status`
        when 'R' then 'Received'
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
    `stm`.`deleted_on` as `deleted_on`
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


-- erp_development.stv_indent_material_issue_return_master_rpt source

create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_return_master_rpt` as
select
    concat(`v`.`issue_return_no`, ':Issue Return No:O:N:') as `issue_return_no`,
    concat(`v`.`issue_return_date`, ':Issue Return Date :Y:D:') as `issue_return_date`,
    concat(`v`.`issue_no`, ':Issue No:O:N:') as `issue_no`,
    concat(`v`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
    concat(`v`.`indent_issue_return_type`, ':Issue Return Type:O:N:') as `indent_issue_return_type`,
    concat(`v`.`issue_return_status`, ':Issue Return Status:O:N:') as `issue_return_status`,
    concat(`v`.`received_date`, ':Issue Received Date:Y:D:') as `received_date`,
    concat(`v`.`remark`, ':Remark:O:N:') as `remark`,
    concat(`v`.`issue_return_master_transaction_id`, ':Issue Return Master Id:O:N:') as `issue_return_master_transaction_id`,
    concat(`v`.`department_name`, ':Department Name:O:N:') as `department_name`,
    concat(`v`.`sub_department_name`, ':Sub Department Name:O:N:') as `sub_department_name`,
    concat(`v`.`received_by_name`, ':Received By Name:O:N:') as `received_by_name`,
    concat(`v`.`return_by_name`, ':Return By Name:O:N:') as `return_by_name`,
    concat(`v`.`issue_return_status_desc`, ':Issue Return Status Desc:O:N:') as `issue_return_status_desc`,
    concat(`v`.`return_by_id`, ':Issue Return By Id:O:N:') as `return_by_id`,
    concat(`v`.`department_id`, ':Department Id:O:N:') as `department_id`,
    concat(`v`.`sub_department_id`, ':Sub Department Id:O:N:') as `sub_department_id`,
    concat(`v`.`indent_issue_return_type_id`, ':Issue Return Type Id:O:N:') as `indent_issue_return_type_id`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`
from
    `stv_indent_material_issue_return_master` `v`
limit 1;
