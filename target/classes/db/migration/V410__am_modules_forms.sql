ALTER TABLE ht_short_leave ADD half_day_type varchar(100) NULL;
ALTER TABLE ht_short_leave ADD leave_type_id bigint(20) NULL;

UPDATE am_modules_forms
SET
    modules_forms_name = "Short / Half Day Leaves",
    display_name = "Short / Half Day Leaves",
    page_header = "Short / Half Day Leaves"
WHERE
    modules_forms_id = 605;

update am_modules_forms set display_name = "Beam (Loading/Unloading)" where display_name = "Beam Issue Slip";


-- erp_development.htv_short_leave source

create or replace
algorithm = UNDEFINED view `htv_short_leave` as
select
    `ht`.`leave_type` as `leave_type`,
    `ht`.`gate_pass_type` as `gate_pass_type`,
    `ht`.`half_day_type` as `half_day_type`,
    `lv`.`leave_type_name` as `leave_type_name`,
    `ht`.`employee_id` as `employee_id`,
    `ht`.`employee_code` as `employee_code`,
    `ht`.`out_time` as `out_time`,
    `ht`.`in_time` as `in_time`,
    `ht`.`total_hours` as `total_hours`,
    `ht`.`leave_reason` as `leave_reason`,
    `ht`.`approved_by` as `approved_by`,
    `ht`.`approval_status` as `approval_status`,
    `ht`.`short_leave_date` as `short_leave_date`,
    `ht`.`financial_year` as `financial_year`,
    `ht`.`month` as `month`,
    `ht`.`punch_code` as `punch_code`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `e1`.`department_name` as `department_name`,
    `e1`.`sub_department_name` as `sub_department_name`,
    `e1`.`employee_type_group` as `employee_type_group`,
    `e`.`employee_name` as `approved_by_name`,
    `e1`.`reporting_to` as `reporting_to`,
    `e1`.`employee_name` as `employee_name`,
    `e1`.`shift_name` as `shift_name`,
    `e1`.`shift_start_end_time` as `shift_start_end_time`,
    `e1`.`reporting_to_name` as `reporting_to_name`,
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
    `ht`.`leave_type_id` as `leave_type_id`,
    `ht`.`company_branch_id` as `company_branch_id`,
    `ht`.`short_leave_transaction_id` as `short_leave_transaction_id`,
    `ht`.`short_leave_id` as `short_leave_id`,
    `ht`.`short_leave_transaction_id` as `field_id`
from
    ((((`ht_short_leave` `ht`
left join `cmv_company` `v` on
    (`ht`.`company_id` = `v`.`company_id`))
left join `cm_employee` `e` on
    (`ht`.`approved_by` = `e`.`employee_id`))
left join `cmv_employee` `e1` on
    (`ht`.`employee_id` = `e1`.`employee_id`))
left join `hm_leave_type` `lv` on
    (`ht`.`leave_type_id` = `lv`.`leave_type_id`
        and `lv`.`is_delete` = 0))
where
    `ht`.`is_delete` = 0;


-- erp_development.htv_short_leave_rpt source

create or replace
algorithm = UNDEFINED view `htv_short_leave_rpt` as
select
    concat(ifnull(`htsl`.`short_leave_id`, ''), ':Short Leave Id:Y:T:') as `short_leave_id`,
    concat(ifnull(`htsl`.`short_leave_date`, ''), ':Leave Date:Y:D:') as `short_leave_date`,
    concat(ifnull(`htsl`.`leave_type`, ''), ':Leave Type:Y:T:') as `leave_type`,
    concat(ifnull(`htsl`.`employee_code`, ''), ':Employee Code:Y:T:') as `employee_code`,
    concat(ifnull(`htsl`.`employee_name`, ''), ':Employee Name:Y:T:') as `employee_name`,
    concat(ifnull(`htsl`.`approval_status`, ''), ':Approval Status:Y:T:') as `approval_status`,
    concat(ifnull(`htsl`.`out_time`, ''), ':Out Time:O:N:') as `out_time`,
    concat(ifnull(`htsl`.`in_time`, ''), ':In Time:O:N:') as `in_time`,
    concat(ifnull(`htsl`.`total_hours`, ''), ':Total Hours:O:N:') as `total_hours`,
    concat(ifnull(`htsl`.`approved_by_name`, ''), ':Approved By Name:Y:T:') as `approved_by_name`,
    concat(ifnull(`htsl`.`shift_name`, ''), ':Shift Name:Y:T:') as `shift_name`,
    concat(ifnull(`htsl`.`shift_start_end_time`, ''), ':Shift Time:O:N:') as `shift_start_end_time`,
    concat(ifnull(`htsl`.`reporting_to_name`, ''), ':Reporting Name:O:N:') as `reporting_to_name`,
    concat(ifnull(`htsl`.`department_name`, ''), ':Department Name:O:N:') as `department_name`,
    concat(ifnull(`htsl`.`sub_department_name`, ''), ':Sub Department Name:O:N:') as `sub_department_name`,
    concat(ifnull(`htsl`.`leave_reason`, ''), ':Leave Reason:O:N:') as `leave_reason`,
    concat(ifnull(`htsl`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(`htsl`.`company_name`, ''), ':Company Name:O:N:') as `company_name`,
    concat(ifnull(`htsl`.`approved_by`, ''), ':Approved By:O:N:') as `approved_by`,
    concat(ifnull(`htsl`.`reporting_to`, ''), ':Reporting To:O:N:') as `reporting_to`,
    concat(ifnull(`htsl`.`employee_id`, ''), ':Employee Id:O:N:') as `employee_id`,
    concat(ifnull(case when `htsl`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`htsl`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`htsl`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`htsl`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`htsl`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`htsl`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`htsl`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`htsl`.`short_leave_transaction_id`, ''), ':Short Leave Transaction Id Id:O:N:') as `short_leave_transaction_id`,
    concat(ifnull(`htsl`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`htsl`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`

from
    `htv_short_leave` `htsl`
limit 1;
