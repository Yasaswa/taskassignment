
CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `htv_salary_summary` AS
select
    `hsals`.`financial_year` AS `financial_year`,
    `hsals`.`salary_year` AS `salary_year`,
    `hsals`.`salary_month` AS `salary_month`,
    `empldep`.`department_name` AS `department_name`,
    `emplsubdep`.`department_name` AS `sub_department_name`,
    `empl`.`employee_type_group` AS `employee_type_group`,
    `hsals`.`employee_type` AS `employee_type`,
    `empl`.`employee_name` AS `employee_name`,
    `empl`.`employee_code` AS `employee_code`,
    `empl`.`old_employee_code` AS `old_employee_code`,
    `empldg`.`designation_name` AS `designation_name`,
    `hsals`.`month_days` AS `month_days`,
    `hsals`.`salary_days` AS `salary_days`,
    `hsals`.`monthly_salary` AS `monthly_salary`,
    `hsals`.`worked_hours` AS `worked_hours`,
    `hsals`.`salary_perday` AS `salary_perday`,
    `hsals`.`salary_perhour` AS `salary_perhour`,
    `hsals`.`gross_salary` AS `gross_salary`,
    `hsals`.`total_deduction` AS `total_deduction`,
    `hsals`.`net_salary` AS `net_salary`,
    case
        `hsals`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `hsals`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `hsals`.`is_active` AS `is_active`,
    `hsals`.`is_delete` AS `is_delete`,
    `hsals`.`created_by` AS `created_by`,
    `hsals`.`created_on` AS `created_on`,
    `hsals`.`modified_by` AS `modified_by`,
    `hsals`.`modified_on` AS `modified_on`,
    `hsals`.`deleted_by` AS `deleted_by`,
    `hsals`.`deleted_on` AS `deleted_on`,
    `hsals`.`salary_transaction_id` AS `salary_transaction_id`,
    `hsals`.`employee_id` AS `employee_id`,
    `hsals`.`department_id` AS `department_id`,
    `hsals`.`sub_department_id` AS `sub_department_id`,
    `hsals`.`designation_id` AS `designation_id`,
    `hsals`.`company_id` AS `company_id`,
    `hsals`.`company_branch_id` AS `company_branch_id`,
    `ew`.`job_type_id` AS `job_type_id`,
    `jt`.`skill_type` AS `skill_type`,
    `jt`.`job_type_rate` AS `job_type_rate`,
    `am`.`night_days` AS `night_days`,
    `am`.`ot_days` AS `ot_days`,
    `am`.`leaves_days` AS `leaves_days`,
    `am`.`od_days` AS `od_days`,
    `am`.`half_days` AS `half_days`,
    `am`.`holiday_days` AS `holiday_days`,
    `am`.`week_off_days` AS `week_off_days`,
    `am`.`coff_days` AS `coff_days`,
    `am`.`absent_days` AS `absent_days`,
    `am`.`total_salary_days` AS `total_salary_days`,
    `am`.`total_latemarks` AS `total_latemarks`,
    `am`.`total_latemarks_days` AS `total_latemarks_days`,
    `am`.`present_days` AS `present_days`
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
        and `am`.`attendance_year` = `hsals`.`salary_year`
        and `am`.`company_id` = `hsals`.`company_id`))
where
    `hsals`.`is_delete` = 0;