ALTER TABLE hm_job_type ADD job_type_attendance_allow_24_days decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE hm_job_type ADD job_type_attendance_allow_26_days decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE hm_job_type ADD on_time_allowance decimal(18,4) DEFAULT 0.0000 NULL;




-- erp_development.hmv_job_type source

create or replace
algorithm = UNDEFINED view `hmv_job_type` as
select
    `c1`.`company_legal_name` as `company_name`,
    `vp`.`department_name` as `department_name`,
    `vp1`.`department_name` as `sub_department_name`,
    `b`.`job_type_category` as `job_type_category`,
    `b`.`job_type_name` as `job_type_name`,
    `b`.`job_type_short_name` as `job_type_short_name`,
    `b`.`job_type_rate` as `job_type_rate`,
    `b`.`skill_type` as `skill_type`,
    `b`.`job_type_attendance_allowance` as `job_type_attendance_allowance`,
    `b`.`job_type_night_allowance` as `job_type_night_allowance`,
    `b`.`job_type_special_allowance` as `job_type_special_allowance`,
    `b`.`job_type_rate_group` as `job_type_rate_group`,
    `b`.`job_type_attendance_allow_24_days` as `job_type_attendance_allow_24_days`,
    `b`.`job_type_attendance_allow_26_days` as `job_type_attendance_allow_26_days`,
    `b`.`on_time_allowance` as `on_time_allowance`,
    `b`.`hours_month` as `hours_month`,
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
    `b`.`job_type_id` as `job_type_id`,
    `b`.`department_id` as `department_id`,
    `b`.`sub_department_id` as `sub_department_id`,
    `b`.`job_type_id` as `field_id`,
    `b`.`job_type_name` as `field_name`
from
    (((`hm_job_type` `b`
left join `cm_company` `c1` on
    (`c1`.`company_id` = `b`.`company_id`))
left join `cmv_department` `vp` on
    (`vp`.`department_id` = `b`.`department_id`))
left join `cmv_department` `vp1` on
    (`vp1`.`department_id` = `b`.`sub_department_id`))
where
    `b`.`is_delete` = 0;



ALTER TABLE hm_hrpayroll_settings ADD attendance_min_allow_days decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE hm_hrpayroll_settings ADD attendance_max_allow_days decimal(18,4) DEFAULT 0.0000 NULL;
