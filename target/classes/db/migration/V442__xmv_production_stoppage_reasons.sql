-- ERP_PASHUPATI_PROD_1_0.xmv_production_stoppage_reasons source repeated

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `xmv_production_stoppage_reasons` AS
select
    `x`.`production_stoppage_reasons_name` AS `production_stoppage_reasons_name`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `x`.`production_stoppage_reasons_type` AS `production_stoppage_reasons_type`,
    `d`.`department_name` AS `department_name`,
    `d1`.`department_name` AS `parent_department`,
    `ps`.`production_section_name` AS `section_name`,
    `ps`.`production_sub_section_name` AS `sub_section_name`,
    `x`.`std_stoppage_loss_per_hour` AS `std_stoppage_loss_per_hour`,
    `x`.`loss_type` AS `loss_type`,
    `x`.`std_stoppage_minutes` AS `std_stoppage_minutes`,
    case
        `x`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `x`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `x`.`is_active` AS `is_active`,
    `x`.`is_delete` AS `is_delete`,
    `x`.`created_by` AS `created_by`,
    `x`.`created_on` AS `created_on`,
    `x`.`modified_by` AS `modified_by`,
    `x`.`modified_on` AS `modified_on`,
    `x`.`deleted_by` AS `deleted_by`,
    `x`.`deleted_on` AS `deleted_on`,
    `x`.`company_id` AS `company_id`,
    `x`.`company_branch_id` AS `company_branch_id`,
    `x`.`production_department_id` AS `production_department_id`,
    `x`.`production_sub_department_id` AS `production_sub_department_id`,
    `x`.`section_id` AS `section_id`,
    `x`.`sub_section_id` AS `sub_section_id`,
    `x`.`production_stoppage_reasons_id` AS `production_stoppage_reasons_id`,
    `x`.`production_stoppage_reasons_name` AS `field_name`,
    `x`.`production_stoppage_reasons_id` AS `field_id`
from
    ((((`xm_production_stoppage_reasons` `x`
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `x`.`company_id`
        and `v`.`company_branch_id` = `x`.`company_branch_id`))
left join `cm_department` `d` on
    (`d`.`is_delete` = 0
        and `d`.`department_id` = `x`.`production_sub_department_id`))
left join `cm_department` `d1` on
    (`d1`.`is_delete` = 0
        and `d1`.`department_id` = `x`.`production_department_id`))
left join `xm_production_sub_section` `ps` on
    ( `ps`.`is_delete` = 0
        and `ps`.`production_section_id` = `x`.`section_id`
        and `ps`.`production_sub_section_id` = `x`.`sub_section_id`))
where
    `x`.`is_delete` = 0;




    -- ERP_PASHUPATI_PROD_1_0.xmv_production_stoppage_reasons_rpt source

    CREATE OR REPLACE
    ALGORITHM = UNDEFINED VIEW `xmv_production_stoppage_reasons_rpt` AS
    select
        concat(`v`.`production_stoppage_reasons_name`, ':Loss Type:Y:T:') AS `production_stoppage_reasons_name`,
        concat(`v`.`production_stoppage_reasons_type`, ':Reasons Type:Y:T:') AS `production_stoppage_reasons_type`,
        concat(`v`.`department_name`, ':parent Department:N:N:') AS `parent_department`,
        concat(`v`.`department_name`, ':Department Name:N:N:') AS `department_name`,
        concat(`v`.`section_name`, ':Section Name:Y:C:xmv_production_section:F') AS `section_name`,
        concat(`v`.`sub_section_name`, ':Sub-Section Name:Y:C:xmv_production_sub_section:F') AS `sub_section_name`,
        concat(`v`.`std_stoppage_loss_per_hour`, ':No. of Activity Per Day:O:N:') AS `std_stoppage_loss_per_hour`,
        concat(`v`.`std_stoppage_minutes`, ':Std Stoppage Minutes:O:N:') AS `std_stoppage_minutes`,
        concat(`v`.`company_name`, ':Company Name:O:N:') AS `company_name`,
        concat(`v`.`company_branch_name`, ':Company Branch Name:O:N:') AS `company_branch_name`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
        concat(`v`.`created_by`, ':Created By:O:N:') AS `created_by`,
        concat(`v`.`created_on`, ':Created On:O:N:') AS `created_on`,
        concat(`v`.`modified_by`, ':Modified By:O:N:') AS `modified_by`,
        concat(`v`.`modified_on`, ':Modified On:O:N:') AS `modified_on`,
        concat(`v`.`deleted_by`, ':Deleted By:O:N:') AS `deleted_by`,
        concat(`v`.`deleted_on`, ':Deleted On:O:N:') AS `deleted_on`,
        concat(`v`.`production_stoppage_reasons_id`, ':Production Stoppage Reasons Id:O:N:') AS `production_stoppage_reasons_id`
    from
        `xmv_production_stoppage_reasons` `v`
    limit 1;