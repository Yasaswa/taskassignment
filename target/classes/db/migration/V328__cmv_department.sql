ALTER TABLE cm_department DROP COLUMN cost_center_id;
ALTER TABLE cm_department DROP COLUMN profit_center_id;
ALTER TABLE cm_department ADD cost_center_group varchar(100) NULL;

create or replace
	algorithm = UNDEFINED view `cmv_department` as
select
	`b`.`department_id` as `department_id`,
	`b`.`department_group` as `department_group`,
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
	`gd`.`godown_name` as `godown_name`,
	`gs`.`godown_section_name` as `godown_section_name`,
	`gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
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
	`b`.`cost_center_group` as `cost_center_group`,
	`b`.`department_head_id` as `department_head_id`,
	`b`.`department_subhead_id` as `department_subhead_id`,
	`b`.`godown_id` as `godown_id`,
	`b`.`godown_section_id` as `godown_section_id`,
	`b`.`godown_section_id` as `godown_section_beans_id`,
	`b`.`department_name` as `field_name`,
	`b`.`department_id` as `field_id`
from
	(((((((`cm_department` `b`
left join `cmv_company_summary` `v` on
	(`v`.`company_branch_id` = `b`.`company_branch_id`
		and `v`.`company_id` = `b`.`company_id`))
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
left join `cm_godown` `gd` on
	(`gd`.`godown_id` = `b`.`godown_id`
		and `gd`.`is_delete` = 0))
left join `cm_godown_section` `gs` on
	(`gs`.`godown_section_id` = `b`.`godown_section_id`
		and `gs`.`is_delete` = 0))
left join `cm_godown_section_beans` `gsb` on
	(`gsb`.`godown_section_beans_id` = `b`.`godown_section_beans_id`
		and `gsb`.`is_delete` = 0))
where
	`b`.`is_delete` = 0
order by
	`b`.`department_group`,
	`d1`.`department_name`,
	`b`.`department_id`;


create or REPLACE algorithm = UNDEFINED view `cmv_department_rpt` as
   		select
    	concat(ifnull(`v`.`department_type`, ''), ':Department Type Desc:Y:H:(Main,Sub)') as `department_type_desc`,
    	concat(ifnull(`v`.`department_name`, ''), ':Parent Department:Y:T:') as `parent_department`,
       	concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:T:') as `department_name`,
        concat(ifnull(`v`.`godown_name`, ''), ':Godown:Y:C:cm_godown:O') as `godown_name`,
        concat(ifnull(`v`.`godown_section_name`, ''), ':Godown Section:Y:C:cm_godown_section:O') as `godown_section_name`,
        concat(ifnull(`v`.`godown_section_beans_name`, ''), ':Godown Section Beans:Y:C:cm_godown_section_beans:O') as `godown_section_beans_name`,
        concat(ifnull(`v`.`department_group`, ''), ':Department Group:Y:P:DepartmentGroup') as `department_group`,
        concat(ifnull(`v`.`department_short_name`, ''), ':Department Short Name:O:N') as `department_short_name`,
        concat(ifnull(`v`.`cost_center_group`, ''), ':Cost Center Group:Y:T:') as `cost_center_group`,
        concat(ifnull(`v`.`department_head`, ''), ':Department Head:Y:C:cmv_employee_list:F') as `department_head`,
        concat(ifnull(`v`.`department_subhead`, ''), ':Department Subhead:Y:C:cmv_employee_list:F') as `department_subhead`,
        concat(ifnull(`v`.`department_std_staff_strength`, ''), ':Department Std Staff Strength:Y:T:') as `department_std_staff_strength`,
        concat(ifnull(`v`.`department_std_worker_strength`, ''), ':Department Std Worker Strength:Y:T:') as `department_std_worker_strength`,
        concat(ifnull(`v`.`worker_perday_salary`, ''), ':Worker PerDay Salary:Y:T:') as `worker_perday_salary`,
        concat(ifnull(`v`.`semiskillled_worker_perday_salary`, ''), ':Semi Skilled Worker PerDay Salary:Y:T:') as `semiskillled_worker_perday_salary`,
        concat(ifnull(`v`.`trainee_worker_perday_salary`, ''), ':Trainee Worker PerDay Salary:Y:T:') as `trainee_worker_perday_salary`,
        concat(ifnull(`v`.`worker_perday_attendance_allowance`, ''), ':Worker PerDay Attendace Allowance:Y:T:') as `worker_perday_attendance_allowance`,
        concat(ifnull(`v`.`worker_perday_night_allowance`, ''), ':Worker PerDay Night Allowance:Y:T:') as `worker_perday_night_allowance`,
        concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
        concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
        concat(ifnull(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ''), ':Active Status:Y:H:(Active, In Active)') as `Active`,
        concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
        concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
        concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
        concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
        concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
        concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
        concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
        concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
        concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
        concat(ifnull(`v`.`department_id`, ''), ':Department Id:O:N:') as `department_id`,
        concat(ifnull(`v`.`department_type`, ''), ':Department Type:N:N:') as `department_type`,
        concat(ifnull(`v`.`parent_department_id`, ''), ':Parent Department Id:N:N:') as `parent_department_id`,
        concat(ifnull(`v`.`department_head_id`, ''), ':Department Head Id:N:N:') as `department_head_id`,
        concat(ifnull(`v`.`department_subhead_id`, ''), ':Department Subhead Id:N:N:') as `department_subhead_id`,
        concat(ifnull(`v`.`department_name`, ''), ':Department Name:N:N:') as `field_name`,
        concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `field_id`

    from
        `cmv_department` `v`
    limit 1;