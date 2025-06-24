


-- xmv_production_sub_section source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `xmv_production_sub_section` AS
select
    `b`.`production_sub_section_id` AS `production_sub_section_id`,
    `d1`.`department_name` AS `parent_department`,
    `b`.`production_section_name` AS `production_section_name`,
    `b`.`production_sub_section_name` AS `production_sub_section_name`,
    `b`.`plant_name` AS `plant_name`,
    `st`.`cost_center_name` AS `production_sub_section_cost_center`,
    `pt`.`profit_center_name` AS `production_sub_section_profit_center`,
    `e1`.`employee_name` AS `production_sub_section_head`,
    `e2`.`employee_name` AS `production_sub_section_subhead`,
    `b`.`production_section_std_staff_strength` AS `production_sub_section_std_staff_strength`,
    `b`.`production_section_std_worker_strength` AS `production_sub_section_std_worker_strength`,
    `b`.`production_sub_section_short_name` AS `production_sub_section_short_name`,
    `b`.`production_sub_section_short_type` AS `production_sub_section_short_type`,
    `st`.`cost_center_short_name` AS `cost_center_short_name`,
    `pt`.`profit_center_short_name` AS `profit_center_short_name`,
    case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `b`.`is_active` AS `is_active`,
    `b`.`is_delete` AS `is_delete`,
    `b`.`created_by` AS `created_by`,
    `b`.`created_on` AS `created_on`,
    `b`.`modified_by` AS `modified_by`,
    `b`.`modified_on` AS `modified_on`,
    `b`.`deleted_by` AS `deleted_by`,
    `b`.`deleted_on` AS `deleted_on`,
    `b`.`company_id` AS `company_id`,
    `b`.`company_branch_id` AS `company_branch_id`,
    `b`.`production_section_id` AS `production_section_id`,
    `b`.`parent_department_id` AS `parent_department_id`,
    `b`.`cost_center_id` AS `cost_center_id`,
    `b`.`profit_center_id` AS `profit_center_id`,
    `b`.`production_section_head_id` AS `production_section_head_id`,
    `b`.`production_section_subhead_id` AS `production_section_subhead_id`,
    `b`.`plant_id` AS `plant_id`,
    `b`.`production_sub_section_name` AS `field_name`,
    `b`.`production_sub_section_id` AS `field_id`
from
    ((((((`xm_production_sub_section` `b`
left join `cmv_company_summary` `v` on
    (`v`.`company_branch_id` = `b`.`company_branch_id`
        and `v`.`company_id` = `b`.`company_id`))
left join `fmv_cost_center` `st` on
    (`st`.`cost_center_id` = `b`.`cost_center_id`
        and `st`.`company_id` = `b`.`company_id`))
left join `fmv_profit_center` `pt` on
    (`pt`.`profit_center_id` = `b`.`profit_center_id`
        and `pt`.`company_id` = `b`.`company_id`))
left join `cmv_employee_list` `e1` on
    (`e1`.`company_branch_id` = `b`.`company_branch_id`
        and `e1`.`company_id` = `b`.`company_id`
        and `e1`.`employee_id` = `b`.`production_section_head_id`))
left join `cmv_employee_list` `e2` on
    (`e2`.`company_branch_id` = `b`.`company_branch_id`
        and `e2`.`company_id` = `b`.`company_id`
        and `e2`.`employee_id` = `b`.`production_section_subhead_id`))
left join `cm_department` `d1` on
    (`d1`.`company_branch_id` = `b`.`company_branch_id`
        and `d1`.`company_id` = `b`.`company_id`
        and `d1`.`department_id` = `b`.`parent_department_id`))
where
    `b`.`is_delete` = 0
order by
    `b`.`production_section_id`,
    `b`.`production_sub_section_id`;