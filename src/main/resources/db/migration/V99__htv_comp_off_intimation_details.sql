-- erp_development.htv_comp_off_intimation_details source

create or replace
algorithm = UNDEFINED view `htv_comp_off_intimation_details` as
select
    `comp`.`company_id` as `company_id`,
    `comp`.`company_branch_id` as `company_branch_id`,
    `comp`.`comp_off_intimation_details_id` as `comp_off_intimation_details_id`,
    `comp`.`employee_id` as `employee_id`,
    `comp`.`punch_code` as `punch_code`,
    `comp`.`employee_code` as `employee_code`,
    `comp`.`att_date_time` as `att_date_time`,
    `comp`.`status` as `status`,
    `comp`.`weeklyoff_name` as `weeklyoff_name`,
    `comp`.`remark` as `remark`,
    `comp`.`approval_remark` as `approval_remark`,
    `comp`.`approved_by_id` as `approved_by_id`,
    `emp`.`employee_name` as `employee_name`,
    `emp`.`reporting_to` as `reporting_to`,
    `emp`.`reporting_to_name` as `reporting_to_name`,
    `empl`.`employee_name` as `approved_by`,
    case
        `comp`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `is_delete`,
    `comp`.`created_by` as `created_by`,
    `comp`.`created_on` as `created_on`,
    `comp`.`modified_by` as `modified_by`,
    `comp`.`modified_on` as `modified_on`,
    `comp`.`deleted_by` as `deleted_by`,
    `comp`.`deleted_on` as `deleted_on`,
    `comp`.`employee_type` as `employee_type`,
    `comp`.`approved_date` as `approved_date`
from
    ((`ht_comp_off_intimation_details` `comp`
left join `cmv_employee` `emp` on
    (`comp`.`employee_id` = `emp`.`employee_id`
        and `comp`.`company_id` = `emp`.`company_id`))
        left join `cmv_employee` `empl` on
    (`comp`.`approved_by_id` = `empl`.`employee_id`
        and `comp`.`company_id` = `empl`.`company_id`))
where
    `comp`.`is_delete` = 0;



-- erp_development.htv_comp_off_intimation_details_rpt source

create or replace
algorithm = UNDEFINED view `htv_comp_off_intimation_details_rpt` as
select
    concat(`comp`.`employee_code`, ':Employee Code:Y:T:') as `employee_code`,
    concat(`comp`.`punch_code`, ':Punch Code:O:N:') as `punch_code`,
    concat(`comp`.`employee_name`, ':Employee Name:Y:N:') as `employee_name`,
    concat(`comp`.`employee_id`, ':Employee ID:Y:N:') as `employee_id`,
    concat(`comp`.`reporting_to_name`, ':Reporting To:Y:N:') as `reporting_to_name`,
    concat(`comp`.`approved_by`, ':Approved By:Y:N:') as `approved_by`,
    concat(`comp`.`att_date_time`, ':Attendance Date:Y:D:') as `att_date_time`,
    concat(`comp`.`status`, ':Status:Y:H:(Pending, Approved, Rejected)') as `status`,
    concat(`comp`.`weeklyoff_name`, ':Weekly Off Name:O:N:') as `weeklyoff_name`,
    concat(`comp`.`remark`, ':Remark:Y:T:') as `remark`,
    concat(`comp`.`approval_remark`, ':Approval Remark:O:N:') as `approval_remark`,
    concat(`comp`.`approved_by_id`, ':Approved By ID:O:N:') as `approved_by_id`,
    concat(`comp`.`approved_date`, ':Approved Date:O:N:') as `approved_date`,
    concat(ifnull(case when `comp`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
    concat(`comp`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`comp`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`comp`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`comp`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`comp`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`comp`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`comp`.`company_id`, ':Company ID:N:N:') as `company_id`,
    concat(`comp`.`company_branch_id`, ':Company Branch ID:N:N:') as `company_branch_id`,
    concat(`comp`.`comp_off_intimation_details_id`, ':Comp Off Intimation ID:O:N:') as `comp_off_intimation_details_id`,
    concat(`comp`.`reporting_to`, ':Reporting To:Y:N:') as `reporting_to`,
    concat(`comp`.`employee_type`, ':Employee Type:O:N:') as `employee_type`
from
    `htv_comp_off_intimation_details` `comp`
limit 1;

