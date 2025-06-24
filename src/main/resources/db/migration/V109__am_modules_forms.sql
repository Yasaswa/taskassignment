INSERT INTO am_modules_forms (company_id, company_branch_id, module_id, sub_module_id, modules_menu_id, modules_sub_menu_id,
 modules_forms_name, display_sequence, display_name, menu_type, listing_component_name, listing_component_import_path, listing_navigation_url,
form_component_name, form_component_import_path, form_navigation_url, icon_class, page_header, is_selected, is_active, is_delete, created_by, created_on,
modified_by, modified_on, deleted_by, deleted_on, is_protected, header, footer, url_parameter)
VALUES( 1, 1, 1, 4, 7, 33, 'Daily Shift Management New', 2, 'Daily Shift Management New', 'HR Payroll', '<TFrmDaillyShiftManagementNew />',
'import TFrmDaillyShiftManagementNew from "Transactions/THRPayroll/TDailyShiftManagementNew"', '/ShiftMangementNew', '', '', '',
NULL, 'Daily Shift Management New', 0, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, '');

INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
 (1,1,1,8,6,36,'Attendance Register New',3,'Attendance Register New','Registers','<DateWiseAttendanceNew compType=''Register'' />','import DateWiseAttendanceNew from "Transactions/THRPayroll/AttendanceRegisters/DateWiseAttendanceNew";','/HRPayroll/Attendancenew/reg','','','',NULL,'Attendance Register New',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');

INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,9,6,35,'Worker Salary Register New',3,'Worker Salary Register New','Registers','<WorkerMonthlySalaryRegisterNew compType=''Register'' />','import WorkerMonthlySalaryRegisterNew from "Transactions/THRPayroll/SalaryRegisters/WorkerMonthlySalaryRegisterNew"','/HRPayroll/WorkerMonthlySalaryNew/reg','','','',NULL,'Worker Salary Register New',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


ALTER TABLE `ht_attendance_daily_new`
ADD COLUMN `current_day_salary` decimal(18,4) DEFAULT NULL,
ADD COLUMN `job_type_code_id` int(11) DEFAULT NULL,
ADD COLUMN `job_type_code_short_name` varchar(100) DEFAULT NULL;

ALTER TABLE ht_attendance_monthly_new
ADD COLUMN total_latemarks decimal(18,4) DEFAULT 0.0000 COMMENT 'count of the latemark & earlygo from the daily shift management.';

ALTER TABLE ht_attendance_monthly_new
ADD COLUMN total_latemarks_days decimal(18,4) DEFAULT 0.0000 COMMENT 'count of the latemark & earlygo from the daily shift management.';



CREATE TABLE `ht_attendance_monthly_job_type_new` (
  `company_id` bigint(20) NOT NULL DEFAULT 1,
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1,
  `attendance_job_type_wise_process_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attendance_process_id` bigint(20) DEFAULT NULL,
  `process_date` datetime NOT NULL,
  `employee_type` varchar(255) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `employee_code` varchar(255) NOT NULL,
  `attendance_month` bigint(20) NOT NULL,
  `attendance_year` bigint(20) NOT NULL,
  `department_id` bigint(20) NOT NULL,
  `sub_department_id` bigint(20) NOT NULL,
  `job_type_id` bigint(20) DEFAULT 10 COMMENT '* Job Type combo box fill with hm_job_type  save job_type_id   salary for workers  ',
  `job_type_short_name` varchar(4) DEFAULT NULL COMMENT '* Data Entry Text box with Validation Captial  alpha  No duplicate  ',
  `job_type_total_days` decimal(18,4) DEFAULT 0.0000 COMMENT '* calculated from Job code wise ',
  `job_type_night_total_days` decimal(18,4) DEFAULT 0.0000 COMMENT '* calculated from Job code wise ',
  `job_type_attendance_total_days` decimal(18,4) DEFAULT 0.0000 COMMENT '* calculated from Job code wise ',
  `job_type_attendance_total_hours` decimal(18,4) DEFAULT 0.0000 COMMENT '* calculated from Job code wise ',
  `job_type_rate` decimal(18,4) DEFAULT 0.0000 COMMENT '* calculated from Job code wise ',
  `job_type_night_allowance` decimal(18,4) DEFAULT 0.0000 COMMENT '* data will come from cmv_job_type for selected job code  ',
  `job_type_attendance_allowance` decimal(18,4) DEFAULT 0.0000 COMMENT '* data will come from cmv_job_type for selected job code  ',
  `is_active` bit(1) DEFAULT b'1',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `job_type_total_salary` decimal(18,4) DEFAULT NULL,
  PRIMARY KEY (`attendance_job_type_wise_process_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO am_modules_forms (company_id, company_branch_id, module_id, sub_module_id, modules_menu_id, modules_sub_menu_id, modules_forms_name, display_sequence, display_name, menu_type, listing_component_name, listing_component_import_path, listing_navigation_url, form_component_name, form_component_import_path, form_navigation_url, icon_class, page_header, is_selected, is_active, is_delete, created_by, created_on, modified_by, modified_on, deleted_by, deleted_on, is_protected, header, footer, url_parameter) VALUES( 1, 1, 1, 4, 7, 34, 'Salary Processing New', 2, 'Salary Processing New', 'HR Payroll', '<TFrmSalaryProcessingNew />', 'import TFrmSalaryProcessingNew from "Transactions/THRPayroll/TSalaryProcessingNew";', '/HRPayroll/TSalaryProcessingNew', '<TFrmSalaryProcessing/>', 'import TFrmSalaryProcessing from "Transactions/THRPayroll/TSalaryProcessing";', '/HRPayroll/TSalaryProcessing', NULL, 'Salary Processing New', 0, 1, 0, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1, 1, '');

create or replace
algorithm = UNDEFINED view `htv_salary_summary_new` as
select
    `hsals`.`financial_year` as `financial_year`,
    `hsals`.`salary_year` as `salary_year`,
    `hsals`.`salary_month` as `salary_month`,
    `empldep`.`department_name` as `department_name`,
    `emplsubdep`.`department_name` as `sub_department_name`,
    `empl`.`employee_type_group` as `employee_type_group`,
    `hsals`.`employee_type` as `employee_type`,
    `empl`.`employee_name` as `employee_name`,
    `empl`.`employee_code` as `employee_code`,
    `empl`.`old_employee_code` as `old_employee_code`,
    `empldg`.`designation_name` as `designation_name`,
    `hsals`.`month_days` as `month_days`,
    `hsals`.`salary_days` as `salary_days`,
    `hsals`.`monthly_salary` as `monthly_salary`,
    `hsals`.`worked_hours` as `worked_hours`,
    `hsals`.`salary_perday` as `salary_perday`,
    `hsals`.`salary_perhour` as `salary_perhour`,
    `hsals`.`gross_salary` as `gross_salary`,
    `hsals`.`total_deduction` as `total_deduction`,
    `hsals`.`net_salary` as `net_salary`,
    case
        `hsals`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `hsals`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `hsals`.`is_active` as `is_active`,
    `hsals`.`is_delete` as `is_delete`,
    `hsals`.`created_by` as `created_by`,
    `hsals`.`created_on` as `created_on`,
    `hsals`.`modified_by` as `modified_by`,
    `hsals`.`modified_on` as `modified_on`,
    `hsals`.`deleted_by` as `deleted_by`,
    `hsals`.`deleted_on` as `deleted_on`,
    `hsals`.`salary_transaction_id` as `salary_transaction_id`,
    `hsals`.`employee_id` as `employee_id`,
    `hsals`.`department_id` as `department_id`,
    `hsals`.`sub_department_id` as `sub_department_id`,
    `hsals`.`designation_id` as `designation_id`,
    `hsals`.`company_id` as `company_id`,
    `hsals`.`company_branch_id` as `company_branch_id`,
    `ew`.`job_type_id` as `job_type_id`,
    `jt`.`skill_type` as `skill_type`,
    `jt`.`job_type_rate` as `job_type_rate`,
    `am`.`night_days` as `night_days`,
    `am`.`ot_days` as `ot_days`,
    `am`.`leaves_days` as `leaves_days`,
    `am`.`od_days` as `od_days`,
    `am`.`half_days` as `half_days`,
    `am`.`holiday_days` as `holiday_days`,
    `am`.`week_off_days` as `week_off_days`,
    `am`.`coff_days` as `coff_days`,
    `am`.`absent_days` as `absent_days`,
    `am`.`present_days` as `present_days`
from
    (((((((`ht_salary_summary` `hsals`
left join `cm_employee` `empl` on
    (`hsals`.`employee_id` = `empl`.`employee_id`
        and `hsals`.`company_id` = `empl`.`company_id`))
left join `cm_department` `empldep` on
    (`hsals`.`department_id` = `empldep`.`department_id`))
left join `cm_department` `emplsubdep` on
    (`hsals`.`sub_department_id` = `emplsubdep`.`department_id`))
left join `cm_designation` `empldg` on
    (`hsals`.`designation_id` = `empldg`.`designation_id`
        and `hsals`.`company_id` = `empldg`.`company_id`))
left join `cm_employees_workprofile` `ew` on
    (`ew`.`employee_id` = `hsals`.`employee_id`
        and `ew`.`company_id` = `hsals`.`company_id`))
left join `hm_job_type` `jt` on
    (`jt`.`job_type_id` = `ew`.`job_type_id`
        and `jt`.`company_id` = `ew`.`company_id`))
left join `ht_attendance_monthly_new` `am` on
    (`am`.`employee_id` = `hsals`.`employee_id`
        and `am`.`attendance_month` = `hsals`.`salary_month`
        and `am`.`company_id` = `hsals`.`company_id`))
where
    `hsals`.`is_delete` = 0;

ALTER TABLE cm_customer_branch MODIFY COLUMN cust_branch_gst_no varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE cm_customer_branch MODIFY COLUMN cust_branch_pan_no varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;

ALTER TABLE cm_agent MODIFY COLUMN agent_gst_no varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE cm_agent MODIFY COLUMN agent_pan_no varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;

