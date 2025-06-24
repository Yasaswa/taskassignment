

-- erp_development.hmv_leaves_applications_rpt source

create or replace
algorithm = UNDEFINED view `hmv_leaves_applications_rpt` as
select
    concat(`la`.`leaves_applications_id`, ':Leaves Application Id:O:N:') as `leaves_applications_id`,
    concat(`la`.`leaves_application_date`, ':Leaves Application Date :Y:D:') as `leaves_application_date`,
    concat(`la`.`leave_status`, ':Leave Status:Y:H:(Pending,Approved,Rejected,Cancelled )') as `leave_status`,
    concat(`la`.`employee_type`, ':Employee Type:O:N:') as `employee_type`,
    concat(`la`.`employee_code`, ':Employee code:O:N:') as `employee_code`,
    concat(`la`.`punch_code`, ':Punch code:O:N:') as `punch_code`,
    concat(`la`.`employee_name`, ':Employee Name:Y:C:cmv_employee_list:F') as `employee_name`,
    concat(`la`.`designation_name`, ':Designation Name:Y:C:cmv_designation:F') as `designation_name`,
    concat(`la`.`department_name`, ':Department Name:Y:C:cmv_department:F') as `department_name`,
    concat(`la`.`leave_type_name`, ':Leave Type Name:Y:C:hmv_leave_type:F') as `leave_type_name`,
    concat(`la`.`leave_type_code`, ':Leave Type Code:O:N:') as `leave_type_code`,
    concat(`la`.`leave_type_paid_flag`, ':Leave Paid Flag:Y:H:(Yes, No)') as `leave_type_paid_flag`,
    concat(`la`.`leaves_requested_from_date`, ':Leaves Requested From Date:Y:D:') as `leaves_requested_from_date`,
    concat(`la`.`leaves_requested_to_date`, ':Leaves Requested To Date:Y:D:') as `leaves_requested_to_date`,
    concat(`la`.`leaves_applied_days`, ':Leaves Applied Days:Y:T:') as `leaves_applied_days`,
    concat(`la`.`leaves_approved_from_date`, ':Leaves Approved From Date:Y:D:') as `leaves_approved_from_date`,
    concat(`la`.`leaves_approved_to_date`, ':Leaves Approved To Date:Y:D:') as `leaves_approved_to_date`,
    concat(`la`.`leaves_sanction_days`, ':Leaves Sanction Days:Y:T:') as `leaves_sanction_days`,
    concat(`la`.`leaves_rejection_days`, ':Leaves Rejection Days:Y:T:') as `leaves_rejection_days`,
    concat(`la`.`leave_sandwich`, ':Leaves Sandwich Status:Y:H:(Yes, No)') as `leave_sandwich`,
    concat(`la`.`work_handover_to`, ':Work Handover To:Y:C:cmv_employee_list:F') as `work_handover_to`,
    concat(`la`.`reporting_to_name`, ':Reporting To Name :Y:C:cmv_employee_list:F') as `reporting_to_name`,
    concat(`la`.`approved_by_name`, ':Leaves Approved By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(`la`.`entered_by_name`, ':Entered By Name:Y:C:hmv_employee_list:F') as `entered_by_name`,
    concat(`la`.`approved_date`, ':Approved Date:Y:D:') as `approved_date`,
    concat(`la`.`leave_reason`, ':Leave Reason:O:N:') as `leave_reason`,
    concat(`la`.`leave_approve_remark`, ':Remark:O:N:') as `leave_approve_remark`,
    concat(`la`.`leave_type_id`, ':Leave Type Id:O:N:') as `leave_type_id`,
    concat(`la`.`work_handover_id`, ':Work Handover Id:O:N:') as `work_handover_id`,
    concat(`la`.`reporting_to`, ':Reporting To:O:N:') as `reporting_to`,
    concat(`la`.`department_id`, ':Department Id:O:N:') as `department_id`,
    concat(`la`.`employee_id`, ':Employee Id:O:N:') as `employee_id`,
    concat(`la`.`is_active`, ':Created By:O:N:') as `is_active`,
    concat(`la`.`is_delete`, ':Created By:O:N:') as `is_delete`,
    concat(case when `la`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `la`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`la`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`la`.`created_on`, ':Modified On:O:N:') as `created_on`,
    concat(`la`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`la`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`la`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`la`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`la`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
    concat(`la`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`la`.`leaves_transaction_id`, ':Leaves Transaction Id:O:N:') as `leaves_transaction_id`,
    concat(`la`.`field_id`, ':Field Id:N:N:') as `field_id`,
    concat(`la`.`field_name`, ':Field Name:N:N:') as `field_name`
from
    `hmv_leaves_applications` `la`
where
    `la`.`is_delete` = 0
limit 1;