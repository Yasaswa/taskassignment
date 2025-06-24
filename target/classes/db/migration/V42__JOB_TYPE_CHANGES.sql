
create or replace
algorithm = UNDEFINED view `hmv_job_type_rpt` as
select
    concat('', ':Job Type Name:Y:C:hmv_job_type:F') as `job_type_name`,
    concat(`v`.`job_type_short_name`, ':Job Type Short Name:O:N:') as `job_type_short_name`,
    concat(`v`.`job_type_rate`, ':Job Type Rate:Y:T:') as `job_type_rate`,
    concat(`v`.`company_name`, ':N:N:') as `company_name`,
    concat(`v`.`department_name`, ':Department Name:Y:C:cmv_department:F') as `department_name`,
    concat(`v`.`sub_department_name`, ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
    concat(`v`.`job_type_category`, ':Job Type Category:Y:H:(Standard Rates,MachineWise Rates )') as `job_type_category`,
    concat(`v`.`job_type_attendance_allowance`, ':Job Type Attend. Allowance:Y:T:') as `job_type_attendance_allowance`,
    concat(`v`.`job_type_night_allowance`, ':Job Type Night Allowance:Y:T:') as `job_type_night_allowance`,
    concat(`v`.`job_type_special_allowance`, ':Job Type Special Allowance:Y:T:') as `job_type_special_allowance`,
    concat(`v`.`skill_type`, ':Skill Type:Y:P:SkillLevels') as `skill_type`,
    concat(`v`.`hours_month`, ':Hours Month:O:N:') as `hours_month`,
    concat(`v`.`job_type_rate_group`, ':Job Type Rate Group:Y:T:') as `job_type_rate_group`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:N:N:') as `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:N:N:') as `deleted_on`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`v`.`job_type_id`, ':Job Type id:O:N:') as `job_type_id`,
    concat(`v`.`department_id`, ':Job Type Department_id:N:N:') as `department_id`,
    concat(`v`.`sub_department_id`, ':Job Type Sub Department_id:N:N:') as `sub_department_id`,
    concat(`v`.`job_type_id`, ':Job Type Id:N:N:') as `field_id`,
    concat(`v`.`job_type_name`, ':Job Type Name:N:N:') as `field_name`
from
    `hmv_job_type` `v`
limit 1;