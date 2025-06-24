-- htv_salary_summary source

create or replace
algorithm = UNDEFINED view `htv_salary_summary` as
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
    `ew`.`job_type_id` AS `job_type_id`,
    `jt`.`skill_type` AS `skill_type`,
    `jt`.`job_type_rate` AS `job_type_rate`
from
    ((((((`ht_salary_summary` `hsals`
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
where
    `hsals`.`is_delete` = 0;




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
    left join `cm_employees_workprofile` `a` on
        (`a`.`employee_id` = `b`.`employee_id`
            and `a`.`company_id` = `b`.`company_id`))
    left join `cm_employees_salary` `es` on
        (`es`.`employee_id` = `b`.`employee_id`
            and `es`.`company_id` = `b`.`company_id`
            and `es`.`is_delete` = 0))
    left join `cm_city` `ct` on
        (`ct`.`city_id` = `b`.`city_id`))
    left join `hm_job_type` `j` on
        (`j`.`job_type_id` = `a`.`job_type_id`))
    left join `cm_destination` `d` on
        (`d`.`destination_id` = `b`.`destination_id`))
    left join `cm_designation` `dg` on
        (`dg`.`designation_id` = `a`.`designation_id`
            and `dg`.`company_id` = `a`.`company_id`))
    left join `cmv_company_branch_summary` `v` on
        (`v`.`company_branch_id` = `b`.`company_branch_id`
            and `v`.`company_id` = `b`.`company_id`))
    left join `cm_contractor` `cn` on
        (`cn`.`contractor_id` = `a`.`contractor_id`
            and `cn`.`company_branch_id` = `a`.`company_branch_id`
            and `cn`.`company_id` = `b`.`company_id`))
    left join `cmv_department` `vp` on
        (`vp`.`department_id` = `a`.`department_id`))
    left join `cmv_department` `vp1` on
        (`vp1`.`department_id` = `a`.`sub_department_id`))
    left join `cm_banks_List` `k1` on
        (`k1`.`bank_id` = `b`.`bank_id1`))
    left join `cm_banks_List` `k2` on
        (`k2`.`bank_id` = `b`.`bank_id2`))
    left join `cm_employee` `me1` on
        (`me1`.`employee_id` = `a`.`reporting_to`
            and `me1`.`is_delete` = 0))
    left join `cm_shift` `ms` on
        (`ms`.`shift_id` = `a`.`shift_id`
            and `ms`.`is_delete` = 0))
    left join `cm_shift` `mss` on
        (`mss`.`shift_id` = `a`.`current_shift_id`
            and `mss`.`is_delete` = 0))
    left join `hm_weeklyoff` `wk` on
        (`wk`.`weeklyoff_id` = `a`.`weeklyoff`))
    left join `cm_employee_band` `eb` on
        (`eb`.`employee_band_id` = `es`.`band_id`
            and `eb`.`company_id` = `es`.`company_id`))
    left join `cm_employee_grade` `eg` on
        (`eg`.`employee_grade_id` = `es`.`grade_id`))
    left join `fm_cost_center` `cc` on
        (`cc`.`cost_center_id` = `a`.`cost_center_id`
            and `cc`.`company_id` = `a`.`company_id`))
    left join `cm_district` `d1` on
        (`d1`.`district_id` = `b`.`district_id`))
    left join `cm_state` `s` on
        (`s`.`state_id` = `b`.`state_id`))
    where
        `b`.`is_delete` = 0;