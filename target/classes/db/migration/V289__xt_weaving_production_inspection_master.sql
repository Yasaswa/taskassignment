ALTER TABLE xt_weaving_production_inspection_master ADD approved_by_id BIGINT(20) DEFAULT 0 NULL COMMENT 'approve by id will store after approve';
ALTER TABLE xt_weaving_production_inspection_master CHANGE approved_by_id approved_by_id BIGINT(20) DEFAULT 0 NULL COMMENT 'approve by id will store after approve' AFTER production_supervisor_id;
ALTER TABLE xt_weaving_production_inspection_master ADD approved_date DATE NULL COMMENT 'approve date after approve record';
ALTER TABLE xt_weaving_production_inspection_master CHANGE approved_date approved_date DATE NULL COMMENT 'approve date after approve record' AFTER approved_by_id;



-- xtv_weaving_production_inspection_master source

create or REPLACE algorithm = UNDEFINED view `xtv_weaving_production_inspection_master` as
select
    `xt`.`inspection_production_date` as `inspection_production_date`,
    `xt`.`inspection_production_code` as `inspection_production_code`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `xt`.`inspection_production_set_no` as `inspection_production_set_no`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    case
        `xt`.`inspection_production_master_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Partial Approved'
    end as `inspection_production_master_status_desc`,
    `xt`.`inspection_production_master_status` as `inspection_production_master_status`,
    `e`.`employee_name` as `approved_by_name`,
    case
        `xt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `xt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `xt`.`is_active` as `is_active`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `v`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `xt`.`financial_year` as `financial_year`,
    `v`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_inspection_master_id` as `weaving_production_inspection_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`
from
    (((((`xt_weaving_production_inspection_master` `xt`
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cm_company` `v` on
    (`v`.`company_id` = `xt`.`company_id`
        and `v`.`is_delete` = 0))
left join `cm_company_branch` `vb` on
    (`vb`.`company_id` = `xt`.`company_id`
        and `vb`.`company_branch_id` = `xt`.`company_branch_id`
        and `vb`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`approved_by_id`
        and `e`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0;