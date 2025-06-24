-- erp_development.htv_salary_summary source

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
left join `ht_attendance_monthly` `am` on
    (`am`.`employee_id` = `hsals`.`employee_id`
        and `am`.`attendance_month` = `hsals`.`salary_month`
        and `am`.`company_id` = `hsals`.`company_id`))
where
    `hsals`.`is_delete` = 0;


update am_modules_forms
 set is_delete = 1
 where modules_forms_id = 648;


UPDATE am_modules_forms_user_access
SET is_delete = 1
WHERE modules_forms_id = 648;