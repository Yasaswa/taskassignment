
create or replace
algorithm = UNDEFINED view `amv_users` as
select
    'employee' as `user_type`,
    `e1`.`employee_code` as `user_code`,
    `e1`.`employee_id` as `user_id`,
    `e1`.`employee_name` as `user`,
    `e1`.`username` as `username`,
    `e1`.`password` as `password`,
    `c`.`company_name` as `company_name`,
    `c`.`company_branch_name` as `company_branch_name`,
    concat(`c`.`branch_address1`, `c`.`branch_address2`) as `company_branch_address`,
    `c`.`branch_short_name` as `branch_short_name`,
    `e1`.`company_id` as `company_id`,
    `e1`.`company_branch_id` as `company_branch_id`,
    `a`.`department_id` as `department_id`,
    `dp`.`department_name` as `department_name`,
    ifnull((select `am`.`company_access` from `am_modules_forms_user_access` `am` where `am`.`is_delete` = 0 and `e1`.`employee_code` = `am`.`user_code` limit 1), `e1`.`company_id`) as `company_access`
from
    (((`cm_employee` `e1`
left join `cmv_company_branch_summary` `c` on
    (`c`.`company_id` = `e1`.`company_id`
        and `c`.`company_branch_id` = `e1`.`company_branch_id`))
left join `cm_employees_workprofile` `a` on
    (`a`.`employee_id` = `e1`.`employee_id`))
left join `cm_department` `dp` on
    (`dp`.`department_id` = `a`.`department_id`))
where
    `e1`.`is_active` = 1
    and `e1`.`is_delete` = 0
    or `e1`.`username` = 'dakshabhiadmin';