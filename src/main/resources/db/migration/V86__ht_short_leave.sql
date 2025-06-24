ALTER TABLE ht_short_leave ADD `month` BIGINT(20) NULL;

ALTER TABLE ht_short_leave ADD gate_pass_type varchar(100) NOT NULL;


ALTER TABLE ht_short_leave MODIFY COLUMN gate_pass_type varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE ht_short_leave DROP COLUMN machine_employee_code;

ALTER TABLE ht_short_leave DROP COLUMN department_id;
ALTER TABLE ht_short_leave DROP COLUMN sub_department_id;


-- erp_development.htv_short_leave source

create or replace
algorithm = UNDEFINED view `htv_short_leave` as
select
    `ht`.`leave_type` as `leave_type`,
    `ht`.`gate_pass_type` as `gate_pass_type`,
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
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `e2`.`department_name` as `department_name`,
    `e2`.`sub_department_name` as `sub_department_name`,
    `e2`.`employee_type_group` as `employee_type_group`,
    `e`.`employee_name` as `approved_by_name`,
     `e1`.`reporting_to` as `reporting_to`,
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
    `ht`.`short_leave_transaction_id` as `field_id`
from
    ((((`ht_short_leave` `ht`
left join `cmv_company` `v` on
    (`ht`.`company_id` = `v`.`company_id`))

left join `cm_employee` `e` on
    (`ht`.`approved_by` = `e`.`employee_id`))
left join `cmv_employee` `e1` on
    (`ht`.`employee_id` = `e1`.`employee_id`))
left join `cmv_employee` `e2` on
    (`ht`.`employee_id` = `e2`.`employee_id`
        and `ht`.`company_id` = `e2`.`company_id`))
where
    `ht`.`is_delete` = 0;








-- erp_development.htv_short_leave_rpt source

create or replace
algorithm = UNDEFINED view `htv_short_leave_rpt` as
select
    concat(ifnull(`htsl`.`short_leave_id`, ''), ':Short Leave Id:O:N:') as `short_leave_id`,
    concat(ifnull(`htsl`.`leave_type`, ''), ':Leave Type:O:N:') as `leave_type`,
    concat(ifnull(`htsl`.`employee_code`, ''), ':Employee Code:O:N:') as `employee_code`,
    concat(ifnull(`htsl`.`out_time`, ''), ':Out Time:O:N:') as `out_time`,
    concat(ifnull(`htsl`.`in_time`, ''), ':In Time:O:N:') as `in_time`,
    concat(ifnull(`htsl`.`total_hours`, ''), ':Total Hours:O:N:') as `total_hours`,
    concat(ifnull(`htsl`.`approved_by_name`, ''), ':Approved By Name:O:N:') as `approved_by_name`,
    concat(ifnull(`htsl`.`department_name`, ''), ':Department Name:O:N:') as `department_name`,
    concat(ifnull(`htsl`.`sub_department_name`, ''), ':Sub Department Name:O:N:') as `sub_department_name`,
    concat(ifnull(`htsl`.`leave_reason`, ''), ':Leave Reason:O:N:') as `leave_reason`,
    concat(ifnull(`htsl`.`approval_status`, ''), ':Approval Status:O:N:') as `approval_status`,
    concat(ifnull(`htsl`.`short_leave_date`, ''), ':Short Leave Date:O:N:') as `short_leave_date`,
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
    concat(ifnull(`htsl`.`company_id`, ''), ':Company Id:O:N:') as `company_id`,
    concat(ifnull(`htsl`.`company_branch_id`, ''), ':Company Branch Id:O:N:') as `company_branch_id`,

    concat(ifnull(`htsl`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(`htsl`.`company_name`, ''), ':Company Name Id:O:N:') as `company_name`
from
    `htv_short_leave` `htsl`
limit 1;



ALTER TABLE cm_department ADD department_req_std_staff_strength bigint(20) DEFAULT 1.00 NULL;
ALTER TABLE cm_department ADD department_req_std_worker_strength bigint(20) DEFAULT 1.00 NULL;

-- erp_development.cmv_department source

create or replace
algorithm = UNDEFINED view `cmv_department` as
select
    `b`.`department_id` as `department_id`,
    `b`.`department_group` as `department_group`,
    `st`.`cost_center_name` as `department_cost_center_name`,
    `pt`.`profit_center_name` as `department_profit_center_name`,
    `d1`.`department_name` as `parent_department`,
    `b`.`department_name` as `department_name`,
    `e1`.`employee_name` as `department_head`,
    `e2`.`employee_name` as `department_subhead`,
    `b`.`department_std_staff_strength` as `department_std_staff_strength`,
    `b`.`department_std_worker_strength` as `department_std_worker_strength`,
    `b`.`department_req_std_staff_strength` as `department_req_std_staff_strength`,
    `b`.`department_req_std_worker_strength` as `department_req_std_worker_strength`,
    `b`.`worker_perday_salary` as `worker_perday_salary`,
    `b`.`trainee_worker_perday_salary` as `trainee_worker_perday_salary`,
    `b`.`semiskillled_worker_perday_salary` as `semiskillled_worker_perday_salary`,
    `b`.`worker_perday_attendance_allowance` as `worker_perday_attendance_allowance`,
    `b`.`worker_perday_night_allowance` as `worker_perday_night_allowance`,
    `b`.`department_short_name` as `department_short_name`,
    case
        `b`.`department_type` when 'M' then 'Main'
        when 'S' then 'Sub'
    end as `department_type_desc`,
    case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `b`.`department_type` as `department_type`,
    `st`.`cost_center_short_name` as `cost_center_short_name`,
    `pt`.`profit_center_short_name` as `profit_center_short_name`,
    `b`.`is_active` as `is_active`,
    `b`.`is_delete` as `is_delete`,
    `b`.`created_by` as `created_by`,
    `b`.`created_on` as `created_on`,
    `b`.`modified_by` as `modified_by`,
    `b`.`modified_on` as `modified_on`,
    `b`.`deleted_by` as `deleted_by`,
    `b`.`deleted_on` as `deleted_on`,
    `b`.`company_id` as `company_id`,
    `b`.`company_branch_id` as `company_branch_id`,
    `b`.`parent_department_id` as `parent_department_id`,
    `b`.`cost_center_id` as `cost_center_id`,
    `b`.`profit_center_id` as `profit_center_id`,
    `b`.`department_head_id` as `department_head_id`,
    `b`.`department_subhead_id` as `department_subhead_id`,
    `b`.`department_name` as `field_name`,
    `b`.`department_id` as `field_id`
from
    ((((((`cm_department` `b`
left join `cmv_company_summary` `v` on
    (`v`.`company_branch_id` = `b`.`company_branch_id`
        and `v`.`company_id` = `b`.`company_id`))
left join `fmv_cost_center` `st` on
    (`st`.`cost_center_id` = `b`.`cost_center_id`
        and `st`.`company_id` = `b`.`company_id`))
left join `fmv_profit_center` `pt` on
    (`pt`.`profit_center_id` = `b`.`profit_center_id`
        and `pt`.`company_id` = `b`.`company_id`))
left join `cm_employee` `e1` on
    (`e1`.`company_branch_id` = `b`.`company_branch_id`
        and `e1`.`company_id` = `b`.`company_id`
        and `e1`.`employee_id` = `b`.`department_head_id`))
left join `cm_employee` `e2` on
    (`e2`.`company_branch_id` = `b`.`company_branch_id`
        and `e2`.`company_id` = `b`.`company_id`
        and `e2`.`employee_id` = `b`.`department_subhead_id`))
left join `cm_department` `d1` on
    (`d1`.`department_id` = `b`.`parent_department_id`))
where
    `b`.`is_delete` = 0
order by
    `b`.`department_group`,
    `d1`.`department_name`,
    `b`.`department_id`;



INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,8,6,36,'ManPower Register',8,'ManPower Register','Registers','<DptWiseManPowerReg compType=''Register'' />','import DptWiseManPowerReg from "./Transactions/THRPayroll/AttendanceRegisters/DptWiseManPowerReg";','THRPayroll/AttendanceRegisters/DptWiseManPowerReg/reg','<DptWiseManPowerReg compType=''Register'' />','import DptWiseManPowerReg from "./Transactions/THRPayroll/AttendanceRegisters/DptWiseManPowerReg";','THRPayroll/AttendanceRegisters/DptWiseManPowerReg/reg',NULL,'ManPower Register',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


INSERT INTO am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) VALUES
	 (1,'Resident Type',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);


INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (184,'Resident Type',2,'Resident','R',1,0,'9016569017','2024-09-28 10:45:37.000','9016569017','2024-09-28 10:46:41.000',NULL,NULL,'RT',''),
	 (184,'Resident Type',2,'Non-Resident','NR',1,0,'9016569017','2024-09-28 10:46:19.000','9016569017','2024-09-28 10:46:53.000',NULL,NULL,'RT','');


ALTER TABLE cm_employees_workprofile ADD resident_type varchar(100) DEFAULT 'resident' NULL;



-- cmv_employee source

create or replace
algorithm = UNDEFINED view `cmv_employee` as
select
    `b`.`employee_type_group` as `employee_type_group`,
    `b`.`employee_id` as `employee_id`,
    `b`.`employee_code` as `employee_code`,
    `b`.`old_employee_code` as `old_employee_code`,
    `b`.`employee_type` as `employee_type`,
    `b`.`salutation` as `salutation`,
    `b`.`employee_name` as `employee_name`,
    `b`.`employee_status` as `employee_status`,
    `b`.`left_suspended_date` as `left_suspended_date`,
    `vp`.`department_name` as `department_name`,
    `vp1`.`department_name` as `sub_department_name`,
    `dg`.`designation_name` as `designation_name`,
    `me1`.`employee_name` as `reporting_to_name`,
    `ms`.`shift_name` as `shift_name`,
    `mss`.`shift_name` as `current_shift_name`,
    concat(`ms`.`start_time`, '-', `ms`.`end_time`) as `shift_start_end_time`,
    `ms`.`halfday_hours` as `halfday_hours`,
    `ms`.`fullday_hours` as `fullday_hours`,
    `ms`.`grace_early_time` as `grace_early_time`,
    `ms`.`grace_late_time` as `grace_late_time`,
    `wk`.`weeklyoff_name` as `weeklyoff_name`,
    `b`.`email_id1` as `email_id1`,
    `b`.`cell_no1` as `cell_no1`,
    `a`.`date_joining` as `date_joining`,
    `a`.`date_exit` as `date_exit`,
    `cn`.`contractor_name` as `contractor_name`,
    `b`.`employee_name` as `field_name`,
    `b`.`employee_id` as `field_id`,
    `es`.`ctc` as `ctc`,
    `es`.`salary` as `salary`,
    case
        `b`.`employee_type` when 'Worker' then `vp`.`worker_perday_salary`
        else 0
    end as `worker_perday_salary`,
    case
        `b`.`employee_type` when 'Worker' then `vp`.`worker_perday_attendance_allowance`
        else 0
    end as `worker_perday_attendance_allowance`,
    case
        `b`.`employee_type` when 'Worker' then `vp`.`worker_perday_night_allowance`
        else 0
    end as `worker_perday_night_allowance`,
    `a`.`job_type_id` as `job_type_id`,
    `a`.`job_type_short_name` as `job_type_short_name`,
    `j`.`job_type_rate` as `job_type_rate`,
    `j`.`job_type_attendance_allowance` as `job_type_attendance_allowance`,
    `j`.`job_type_night_allowance` as `job_type_night_allowance`,
    `j`.`job_type_special_allowance` as `job_type_special_allowance`,
    `j`.`skill_type` as `skill_type`,
    `es`.`ot_flag` as `ot_flag`,
    `b`.`gender` as `gender`,
    `b`.`aadhar_card_no` as `aadhar_card_no`,
    `b`.`passport_no` as `passport_no`,
    `b`.`pan_no` as `pan_no`,
    `b`.`driving_licence` as `driving_licence`,
    `b`.`current_address` as `current_address`,
    `b`.`current_pincode` as `current_pincode`,
    `ct`.`city_name` as `city_name`,
    `d1`.`district_name` as `district_name`,
    `s`.`state_name` as `state_name`,
    `b`.`country` as `country`,
    `b`.`permanant_address` as `permanant_address`,
    `b`.`permanant_pincode` as `permanant_pincode`,
    `b`.`date_of_birth` as `date_of_birth`,
    `b`.`email_id2` as `email_id2`,
    `b`.`phone_no` as `phone_no`,
    `b`.`cell_no2` as `cell_no2`,
    `k1`.`bank_name` as `bank_name1`,
    `b`.`account_no1` as `account_no1`,
    `b`.`account_name1` as `account_name1`,
    `b`.`ifsc_code1` as `ifsc_code1`,
    `b`.`marital_status` as `marital_status`,
    `b`.`reference` as `reference`,
    `b`.`religion` as `religion`,
    `b`.`caste` as `employee_caste`,
    `b`.`category` as `employee_category`,
    `d`.`destination_name` as `destination_name`,
    `b`.`blood_group` as `blood_group`,
    `b`.`finance_account_no` as `finance_account_no`,
    `a`.`contract_startdate` as `contract_startdate`,
    `a`.`contract_enddate` as `contract_enddate`,
    `vp`.`department_group` as `department_group`,
    `eb`.`employee_band_name` as `employee_band_name`,
    `eg`.`employee_grade_name` as `employee_grade_name`,
    `cc`.`cost_center_name` as `cost_center_name`,
    `a`.`bond_applicable` as `bond_applicable`,
    `a`.`current_job` as `current_job`,
    `b`.`machine_employee_code` as `machine_employee_code`,
    `b`.`username` as `username`,
    `b`.`password` as `password`,
    `b`.`last_name` as `last_name`,
    `b`.`first_name` as `first_name`,
    `b`.`middle_name` as `middle_name`,
    `b`.`bank_id1` as `bank_id1`,
    `k2`.`bank_name` as `bank_name2`,
    `b`.`bank_id2` as `bank_id2`,
    `b`.`account_no2` as `account_no2`,
    `b`.`account_name2` as `account_name2`,
    `b`.`ifsc_code2` as `ifsc_code2`,
    `v`.`company_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `b`.`is_active` as `is_active`,
    `b`.`is_delete` as `is_delete`,
    `b`.`created_by` as `created_by`,
    `b`.`created_on` as `created_on`,
    `b`.`modified_by` as `modified_by`,
    `b`.`modified_on` as `modified_on`,
    `b`.`deleted_by` as `deleted_by`,
    `b`.`deleted_on` as `deleted_on`,
    `v`.`company_id` as `company_id`,
    `v`.`company_branch_id` as `company_branch_id`,
    `b`.`city_id` as `city_id`,
    `b`.`district_id` as `district_id`,
    `b`.`state_id` as `state_id`,
    `b`.`destination_id` as `destination_id`,
    `b`.`image_path` as `image_path`,
    `a`.`employee_workprofile_id` as `employee_workprofile_id`,
    `a`.`contractor_id` as `contractor_id`,
    `a`.`department_group_id` as `department_group_id`,
    `a`.`department_id` as `department_id`,
    `a`.`sub_department_id` as `sub_department_id`,
    `a`.`designation_id` as `designation_id`,
    `a`.`weeklyoff` as `weeklyoff`,
    `a`.`resident_type` as `resident_type`,
    `ms`.`two_day_shift` as `two_day_shift`,
    `a`.`shift_id` as `shift_id`,
    `a`.`current_shift_id` as `current_shift_id`,
    `a`.`cost_center_id` as `cost_center_id`,
    `a`.`profit_center_id` as `profit_center_id`,
    `a`.`reporting_to` as `reporting_to`,
    `a`.`region_id` as `region_id`,
    `es`.`employee_salary_id` as `employee_salary_id`,
    `es`.`band_id` as `band_id`,
    `es`.`grade_id` as `grade_id`,
    `es`.`ot_amount` as `ot_amount`,
    `es`.`gratuity_applicable` as `gratuity_applicable`,
    `es`.`pf_flag` as `pf_flag`,
    `es`.`uan_no` as `uan_no`,
    `es`.`pf_date` as `pf_date`,
    `es`.`esic_flag` as `esic_flag`,
    `es`.`esic_date` as `esic_date`,
    `es`.`mlwf_flag` as `mlwf_flag`,
    `es`.`mlwf_no` as `mlwf_no`,
    `b`.`father_dob` as `father_dob`,
    `b`.`father_name` as `father_name`,
    `b`.`mother_name` as `mother_name`,
    `b`.`mother_dob` as `mother_dob`,
    `b`.`spouse_name` as `spouse_name`,
    `b`.`spouse_dob` as `spouse_dob`,
    `b`.`son_name` as `son_name`,
    `b`.`son_dob` as `son_dob`,
    `b`.`daughter_name` as `daughter_name`,
    `b`.`daughter_dob` as `daughter_dob`,
    `ms`.`shift_grace_hours_min` as `shift_grace_hours_min`,
    `ms`.`shift_grace_hours_max` as `shift_grace_hours_max`,
    `a`.`attendance_exclude_flag` as `attendance_exclude_flag`
from
    (((((((((((((((((((((`cm_employee` `b`
left join`cm_employees_workprofile` `a` on
    (`a`.`employee_id` = `b`.`employee_id`
        and `a`.`company_id` = `b`.`company_id`))
left join`cm_employees_salary` `es` on
    (`es`.`employee_id` = `b`.`employee_id`
        and `es`.`company_id` = `b`.`company_id`
        and `es`.`is_delete` = 0))
left join`cm_city` `ct` on
    (`ct`.`city_id` = `b`.`city_id`))
left join`hm_job_type` `j` on
    (`j`.`job_type_id` = `a`.`job_type_id`))
left join`cm_destination` `d` on
    (`d`.`destination_id` = `b`.`destination_id`))
left join`cm_designation` `dg` on
    (`dg`.`designation_id` = `a`.`designation_id`
        and `dg`.`company_id` = `a`.`company_id`))
left join`cmv_company_branch_summary` `v` on
    (`v`.`company_branch_id` = `b`.`company_branch_id`
        and `v`.`company_id` = `b`.`company_id`))
left join`cm_contractor` `cn` on
    (`cn`.`contractor_id` = `a`.`contractor_id`
        and `cn`.`company_branch_id` = `a`.`company_branch_id`
        and `cn`.`company_id` = `b`.`company_id`))
left join`cmv_department` `vp` on
    (`vp`.`department_id` = `a`.`department_id`))
left join`cmv_department` `vp1` on
    (`vp1`.`department_id` = `a`.`sub_department_id`))
left join`cm_banks_List` `k1` on
    (`k1`.`bank_id` = `b`.`bank_id1`))
left join`cm_banks_List` `k2` on
    (`k2`.`bank_id` = `b`.`bank_id2`))
left join`cm_employee` `me1` on
    (`me1`.`employee_id` = `a`.`reporting_to`
        and `me1`.`is_delete` = 0))
left join`cm_shift` `ms` on
    (`ms`.`shift_id` = `a`.`shift_id`
        and `ms`.`is_delete` = 0))
left join`cm_shift` `mss` on
    (`mss`.`shift_id` = `a`.`current_shift_id`
        and `mss`.`is_delete` = 0))
left join`hm_weeklyoff` `wk` on
    (`wk`.`weeklyoff_id` = `a`.`weeklyoff`))
left join`cm_employee_band` `eb` on
    (`eb`.`employee_band_id` = `es`.`band_id`
        and `eb`.`company_id` = `es`.`company_id`))
left join`cm_employee_grade` `eg` on
    (`eg`.`employee_grade_id` = `es`.`grade_id`))
left join`fm_cost_center` `cc` on
    (`cc`.`cost_center_id` = `a`.`cost_center_id`
        and `cc`.`company_id` = `a`.`company_id`))
left join`cm_district` `d1` on
    (`d1`.`district_id` = `b`.`district_id`))
left join`cm_state` `s` on
    (`s`.`state_id` = `b`.`state_id`))
where
    `b`.`is_delete` = 0;



INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,3,1,5,'Contractor Commission',15,'Contractor Commission','Masters','<FrmContractorCommissionList />','import FrmContractorCommissionList from "./Masters/MContractorCommission/FrmContractorCommissionList";','/Masters/MContractorCommission/FrmContractorCommissionList','<FrmContractorCommissionEntry />','import FrmContractorCommissionEntry from "./Masters/MContractorCommission/FrmContractorCommissionEntry";','/Masters/MContractorCommission/FrmContractorCommissionEntry',NULL,'Contractor Commission',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


-- hm_commission_rate definition

CREATE TABLE `hm_commission_rate` (
  `company_id` varchar(100) NOT NULL DEFAULT '1' ,
  `commission_rate_id` bigint(20) NOT NULL AUTO_INCREMENT  ,
   `skill_type` varchar(50) default  NULL,
  `department_id` bigint(20) NOT null,
  `sub_department_id` bigint(20) NOT NULL ,
  `resident_type` varchar(50) default  NULL,
  `commission_type` varchar(50) default  NULL,
  `commission_rate` decimal(18,4) DEFAULT 0.0000 ,
  `is_active` bit(1) DEFAULT b'1',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT null,

  PRIMARY KEY (`commission_rate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




create or replace
algorithm = UNDEFINED view `hmv_commission_rate` as
select
    `c1`.`company_legal_name` as `company_name`,
    `vp`.`department_name` as `department_name`,
    `vp1`.`department_name` as `sub_department_name`,
    `b`.`resident_type` as `resident_type`,
    `b`.`commission_type` as `commission_type`,
    `b`.`skill_type` as `skill_type`,
    `b`.`commission_rate` as `commission_rate`,

    case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `b`.`is_active` as `is_active`,
    `b`.`is_delete` as `is_delete`,
    `b`.`created_by` as `created_by`,
    `b`.`created_on` as `created_on`,
    `b`.`modified_by` as `modified_by`,
    `b`.`modified_on` as `modified_on`,
    `b`.`deleted_by` as `deleted_by`,
    `b`.`deleted_on` as `deleted_on`,
    `b`.`company_id` as `company_id`,
    `b`.`commission_rate_id` as `commission_rate_id`,
    `b`.`department_id` as `department_id`,
    `b`.`sub_department_id` as `sub_department_id`,
    `b`.`commission_rate_id` as `field_id`

from
    (((`hm_commission_rate` `b`
left join`cm_company` `c1` on
    (`c1`.`company_id` = `b`.`company_id`))
left join`cmv_department` `vp` on
    (`vp`.`department_id` = `b`.`department_id`))
left join`cmv_department` `vp1` on
    (`vp1`.`department_id` = `b`.`sub_department_id`))
where
    `b`.`is_delete` = 0;



 create or replace
algorithm = UNDEFINED view `hmv_commission_rate_rpt` as
select

     concat(`b`.`company_name`, ':N:N:') as `company_name`,
    concat(`b`.`department_name`, ':Department Name:Y:C:cmv_department:F') as `department_name`,
    concat(`b`.`sub_department_name`, ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
    concat(`b`.`resident_type`, ':Resident Type:Y:T:') as `resident_type`,
    concat(`b`.`commission_type`, ':Commission Type:Y:C:') as `commission_type`,
    concat(`b`.`skill_type`, ':Skill Type:Y:P:SkillLevels') as `skill_type`,
    concat(`b`.`commission_rate`, ':Commission Rate:Y:T:') as `commission_rate`,

    concat(case when `b`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `b`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,

    concat(`b`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`b`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`b`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`b`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`b`.`deleted_by`, ':Deleted By:N:N:') as `deleted_by`,
    concat(`b`.`deleted_on`, ':Deleted On:N:N:') as `deleted_on`,

    concat(`b`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`b`.`commission_rate_id`, ':Commission Rate Id:O:N:') as `commission_rate_id`,
    concat(`b`.`department_id`, ':Department Id:N:N:') as `department_id`,
    concat(`b`.`sub_department_id`, ':Sub Department Id:N:N:') as `sub_department_id`,
    concat(`b`.`commission_rate_id`, ':Field Id:N:N:') as `field_id`

from
   `hmv_commission_rate` `b`
limit 1;



INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,9,6,35,'Contractor Commission Register',11,'Contractor Commission Register','Registers',' <ContractorCommissionReg compType=''Register'' />','import ContractorCommissionReg from "./Transactions/THRPayroll/SalaryRegisters/ContractorCommissionReg";','/THRPayroll/ContractorCommissionReg/reg','','','',NULL,'Contractor Commission Register',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


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
    (`ht_comp_off_intimation_details` `comp`
left join `cmv_employee` `emp` on
    (`comp`.`employee_id` = `emp`.`employee_id`
        and `comp`.`company_id` = `emp`.`company_id`))
where
    `comp`.`is_delete` = 0;


-- erp_development.htv_comp_off_intimation_details_rpt source

create or replace
algorithm = UNDEFINED view `htv_comp_off_intimation_details_rpt` as
select
    concat(`comp`.`employee_code`, ':Employee Code:Y:T:') as `employee_code`,
    concat(`comp`.`employee_name`, ':Employee Name:Y:N:') as `employee_name`,
    concat(`comp`.`employee_id`, ':Employee ID:Y:N:') as `employee_id`,
    concat(`comp`.`punch_code`, ':Punch Code:O:N:') as `punch_code`,
    concat(`comp`.`reporting_to_name`, ':Reporting To:Y:N:') as `reporting_to_name`,
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
