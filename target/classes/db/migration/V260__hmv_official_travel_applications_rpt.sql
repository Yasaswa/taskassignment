-- hmv_leaves_applications_rpt source

create or replace
algorithm = UNDEFINED view `hmv_official_travel_applications_rpt` as
select
    concat(ifnull(`la`.`leaves_applications_id`, ''), ': Application Id:O:N:') as `leaves_applications_id`,
    concat(ifnull(`la`.`leaves_application_date`, ''), ':Journey Application Date :Y:D:') as `leaves_application_date`,
    concat(ifnull(`la`.`leave_status`, ''), ':Journey Status:Y:H:(Pending,Approved,Rejected,Cancelled)') as `leave_status`,
    concat(ifnull(`la`.`employee_type`, ''), ':Employee Type:O:N:') as `employee_type`,
    concat(ifnull(`la`.`employee_code`, ''), ':Employee Code:O:N:') as `employee_code`,
    concat(ifnull(`la`.`punch_code`, ''), ':Punch Code:O:N:') as `punch_code`,
    concat(ifnull(`la`.`employee_name`, ''), ':Employee Name:Y:C:cmv_employee_list:F') as `employee_name`,
    concat(ifnull(`la`.`designation_name`, ''), ':Designation Name:Y:C:cmv_designation:F') as `designation_name`,
    concat(ifnull(`la`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') as `department_name`,
    concat(ifnull(`la`.`leave_type_name`, ''), ':Journey Type Name:Y:C:hmv_leave_type:F') as `leave_type_name`,
    concat(ifnull(`la`.`leave_type_code`, ''), ':Journey Type Code:O:N:') as `leave_type_code`,
    concat(ifnull(`la`.`leave_type_paid_flag`, ''), ':Journey Paid Flag:Y:H:(Yes, No)') as `leave_type_paid_flag`,
    concat(ifnull(`la`.`leaves_requested_from_date`, ''), ':Journey Requested From Date:Y:D:') as `leaves_requested_from_date`,
    concat(ifnull(`la`.`leaves_requested_to_date`, ''), ':Journey Requested To Date:Y:D:') as `leaves_requested_to_date`,
    concat(ifnull(`la`.`leaves_applied_days`, ''), ':Journey Applied Days:Y:T:') as `leaves_applied_days`,
    concat(ifnull(`la`.`leaves_approved_from_date`, ''), ':Journey Approved From Date:Y:D:') as `leaves_approved_from_date`,
    concat(ifnull(`la`.`leaves_approved_to_date`, ''), ':Journey Approved To Date:Y:D:') as `leaves_approved_to_date`,
    concat(ifnull(`la`.`leaves_sanction_days`, ''), ':Journey Sanction Days:Y:T:') as `leaves_sanction_days`,
    concat(ifnull(`la`.`leaves_rejection_days`, ''), ':Journey Rejection Days:Y:T:') as `leaves_rejection_days`,
    concat(ifnull(`la`.`leave_sandwich`, ''), ':Journey Sandwich Status:Y:H:(Yes, No)') as `leave_sandwich`,
    concat(ifnull(`la`.`work_handover_to`, ''), ':Work Handover To:Y:C:cmv_employee_list:F') as `work_handover_to`,
    concat(ifnull(`la`.`reporting_to_name`, ''), ':Reporting To Name:Y:C:cmv_employee_list:F') as `reporting_to_name`,
    concat(ifnull(`la`.`approved_by_name`, ''), ':Journey Approved By:Y:C:cmv_employee_list:F') as `approved_by_name`,
    concat(ifnull(`la`.`entered_by_name`, ''), ':Entered By Name:Y:C:hmv_employee_list:F') as `entered_by_name`,
    concat(ifnull(`la`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`la`.`leave_reason`, ''), ':Journey Reason:O:N:') as `leave_reason`,
    concat(ifnull(`la`.`leave_approve_remark`, ''), ':Remark:O:N:') as `leave_approve_remark`,
    concat(ifnull(`la`.`leave_type_id`, ''), ':Journey Type Id:O:N:') as `leave_type_id`,
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


INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,4,7,33,'Official Journey Application',5,'Official Journey Application','HR Payroll','<FrmBusiessTravelListing />','import FrmBusiessTravelListing from Masters/MBusinessTravelApplication/FrmBusiessTravelListing;','/Masters/MBusinessTravelApplication/FrmBusiessTravelListing','<FrmBusiessTravelEntry />','import FrmBusiessTravelEntry from Masters/MBusinessTravelApplication/FrmBusinessTravelEntry;','/Masters/MBusinessTravelApplication/FrmBusinessTravelEntry',NULL,'Official Journey Application',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');