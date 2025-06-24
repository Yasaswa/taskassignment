-- erp_development.hmv_leaves_applications source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `hmv_leaves_applications` AS
select
    `la`.`leaves_applications_id` AS `leaves_applications_id`,
    `la`.`leaves_application_date` AS `leaves_application_date`,
    `la`.`financial_year` AS `financial_year`,
    `la`.`employee_type` AS `employee_type`,
    `la`.`employee_code` AS `employee_code`,
    `la`.`punch_code` AS `punch_code`,
    `e1`.`employee_name` AS `employee_name`,
    `e1`.`designation_name` AS `designation_name`,
    `e1`.`department_name` AS `department_name`,
    `e1`.`sub_department_name` AS `sub_department_name`,
    `lt`.`leave_type_name` AS `leave_type_name`,
    `lt`.`leave_type_code` AS `leave_type_code`,
    `lt`.`leave_type_paid_flag` AS `leave_type_paid_flag`,
    `la`.`leaves_requested_from_date` AS `leaves_requested_from_date`,
    `la`.`leaves_requested_to_date` AS `leaves_requested_to_date`,
    `la`.`leaves_approved_from_date` AS `leaves_approved_from_date`,
    `la`.`leaves_approved_to_date` AS `leaves_approved_to_date`,
    `la`.`leave_sandwich` AS `leave_sandwich`,
    `la`.`leaves_applied_days` AS `leaves_applied_days`,
    `la`.`leaves_sanction_days` AS `leaves_sanction_days`,
    `la`.`leaves_rejection_days` AS `leaves_rejection_days`,
    `la`.`leave_status` AS `leave_status`,
    `e2`.`employee_name` AS `entered_by_name`,
    `e4`.`employee_name` AS `work_handover_to`,
    `e3`.`employee_name` AS `approved_by_name`,
    `e5`.`employee_name` AS `reporting_to_name`,
    `la`.`approved_date` AS `approved_date`,
    `la`.`leave_reason` AS `leave_reason`,
    `la`.`leave_approve_remark` AS `leave_approve_remark`,
    `la`.`leaves_transaction_id` AS `leaves_transaction_id`,
    `la`.`employee_id` AS `employee_id`,
    `la`.`leave_type_id` AS `leave_type_id`,
    `la`.`work_handover_id` AS `work_handover_id`,
    `la`.`reporting_to` AS `reporting_to`,
    `la`.`sub_department_id` AS `sub_department_id`,
    `la`.`department_id` AS `department_id`,
    case
        `la`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `la`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `la`.`is_active` AS `is_active`,
    `la`.`is_delete` AS `is_delete`,
    `la`.`created_by` AS `created_by`,
    `la`.`created_on` AS `created_on`,
    `la`.`modified_by` AS `modified_by`,
    `la`.`modified_on` AS `modified_on`,
    `la`.`deleted_by` AS `deleted_by`,
    `la`.`deleted_on` AS `deleted_on`,
    `la`.`company_id` AS `company_id`,
    `la`.`applied_by_id` as `applied_by_id`,
    `la`.`leaves_transaction_id` AS `field_id`,
    `la`.`leaves_applications_id` AS `field_name`
from
    (((((((`hm_leaves_applications` `la`
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `la`.`company_id`))
left join `hmv_leave_type` `lt` on
    (`la`.`leave_type_id` = `lt`.`leave_type_id`))
left join `cmv_employee` `e1` on
    (`e1`.`company_id` = `la`.`company_id`
        and `e1`.`employee_code` = `la`.`employee_code`))
left join `cmv_employee_list` `e2` on
    (`e2`.`company_id` = `la`.`company_id`
        and `e2`.`employee_id` = `la`.`applied_by_id`))
left join `cmv_employee_list` `e3` on
    (`e3`.`employee_id` = `la`.`approved_by_id`))
left join `cmv_employee_list` `e4` on
    (`e4`.`company_id` = `la`.`company_id`
        and `e4`.`employee_id` = `la`.`work_handover_id`))
left join `cmv_employee_list` `e5` on
    (`e5`.`company_id` = `la`.`company_id`
        and `e5`.`employee_id` = `la`.`reporting_to`))
where
    `la`.`is_delete` = 0;




-- erp_development.hmv_leaves_applications_rpt source

create or replace
algorithm = UNDEFINED view `hmv_leaves_applications_rpt` as
select
    concat(ifnull(`la`.`leaves_applications_id`, ''), ':Leaves Application Id:O:N:') as `leaves_applications_id`,
    concat(ifnull(`la`.`leaves_application_date`, ''), ':Leaves Application Date :Y:D:') as `leaves_application_date`,
    concat(ifnull(`la`.`leave_status`, ''), ':Leave Status:Y:H:(Pending,Approved,Rejected,Cancelled)') as `leave_status`,
    concat(ifnull(`la`.`employee_type`, ''), ':Employee Type:O:N:') as `employee_type`,
    concat(ifnull(`la`.`employee_code`, ''), ':Employee Code:O:N:') as `employee_code`,
    concat(ifnull(`la`.`punch_code`, ''), ':Punch Code:O:N:') as `punch_code`,
    concat(ifnull(`la`.`employee_name`, ''), ':Employee Name:Y:C:cmv_employee_list:F') as `employee_name`,
    concat(ifnull(`la`.`designation_name`, ''), ':Designation Name:Y:C:cmv_designation:F') as `designation_name`,
    concat(ifnull(`la`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') as `department_name`,
    concat(ifnull(`la`.`leave_type_name`, ''), ':Leave Type Name:Y:C:hmv_leave_type:F') as `leave_type_name`,
    concat(ifnull(`la`.`leave_type_code`, ''), ':Leave Type Code:O:N:') as `leave_type_code`,
    concat(ifnull(`la`.`leave_type_paid_flag`, ''), ':Leave Paid Flag:Y:H:(Yes, No)') as `leave_type_paid_flag`,
    concat(ifnull(`la`.`leaves_requested_from_date`, ''), ':Leaves Requested From Date:Y:D:') as `leaves_requested_from_date`,
    concat(ifnull(`la`.`leaves_requested_to_date`, ''), ':Leaves Requested To Date:Y:D:') as `leaves_requested_to_date`,
    concat(ifnull(`la`.`leaves_applied_days`, ''), ':Leaves Applied Days:Y:T:') as `leaves_applied_days`,
    concat(ifnull(`la`.`leaves_approved_from_date`, ''), ':Leaves Approved From Date:Y:D:') as `leaves_approved_from_date`,
    concat(ifnull(`la`.`leaves_approved_to_date`, ''), ':Leaves Approved To Date:Y:D:') as `leaves_approved_to_date`,
    concat(ifnull(`la`.`leaves_sanction_days`, ''), ':Leaves Sanction Days:Y:T:') as `leaves_sanction_days`,
    concat(ifnull(`la`.`leaves_rejection_days`, ''), ':Leaves Rejection Days:Y:T:') as `leaves_rejection_days`,
    concat(ifnull(`la`.`leave_sandwich`, ''), ':Leaves Sandwich Status:Y:H:(Yes, No)') as `leave_sandwich`,
    concat(ifnull(`la`.`work_handover_to`, ''), ':Work Handover To:Y:C:cmv_employee_list:F') as `work_handover_to`,
    concat(ifnull(`la`.`reporting_to_name`, ''), ':Reporting To Name:Y:C:cmv_employee_list:F') as `reporting_to_name`,
    concat(ifnull(`la`.`approved_by_name`, ''), ':Leaves Approved By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(ifnull(`la`.`entered_by_name`, ''), ':Entered By Name:Y:C:hmv_employee_list:F') as `entered_by_name`,
    concat(ifnull(`la`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`la`.`leave_reason`, ''), ':Leave Reason:O:N:') as `leave_reason`,
    concat(ifnull(`la`.`leave_approve_remark`, ''), ':Remark:O:N:') as `leave_approve_remark`,
    concat(ifnull(`la`.`leave_type_id`, ''), ':Leave Type Id:O:N:') as `leave_type_id`,
    concat(ifnull(`la`.`work_handover_id`, ''), ':Work Handover Id:O:N:') as `work_handover_id`,
    concat(ifnull(`la`.`reporting_to`, ''), ':Reporting To:O:N:') as `reporting_to`,
    concat(ifnull(`la`.`department_id`, ''), ':Department Id:O:N:') as `department_id`,
    concat(ifnull(`la`.`employee_id`, ''), ':Employee Id:O:N:') as `employee_id`,
    concat(ifnull(`la`.`applied_by_id`, ''), ':Applied Id:O:N:') as `applied_by_id`,
    concat(ifnull(`la`.`is_active`, ''), ':Is Active:O:N:') as `is_active`,
    concat(ifnull(`la`.`is_delete`, ''), ':Is Delete:O:N:') as `is_delete`,
    concat(case when `la`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `la`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`la`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`la`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`la`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`la`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`la`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`la`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`la`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(`la`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`la`.`leaves_transaction_id`, ''), ':Leaves Transaction Id:O:N:') as `leaves_transaction_id`,
    concat(ifnull(`la`.`field_id`, ''), ':Field Id:N:N:') as `field_id`,
    concat(ifnull(`la`.`field_name`, ''), ':Field Name:N:N:') as `field_name`
from
    `hmv_leaves_applications` `la`
where
    `la`.`is_delete` = 0
limit 1;

