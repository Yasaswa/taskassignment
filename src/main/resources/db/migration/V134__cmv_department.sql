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
    `b`.`general_worker` as `general_worker`,
    `b`.`trainee_worker` as `trainee_worker`,
    `b`.`semi_skilled_worker` as `semi_skilled_worker`,
    `b`.`skilled_worker` as `skilled_worker`,
    `b`.`sr_semi_skilled_worker` as `sr_semi_skilled_worker`,
    `b`.`helper_worker` as `helper_worker`,
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
