-- pashupati_erp_qa.xtv_weaving_production_warping_master source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `xtv_weaving_production_warping_master` AS
select
    `xt`.`set_no` AS `set_no`,
    `xt`.`warping_production_date` AS `warping_production_date`,
    `xt`.`warping_production_code` AS `warping_production_code`,
    `p`.`plant_name` AS `plant_name`,
    `xt`.`prod_month` AS `prod_month`,
    `xt`.`prod_year` AS `prod_year`,
    `e`.`employee_name` AS `production_supervisor_name`,
    `sb`.`production_section_name` AS `production_section_name`,
    `sb`.`production_sub_section_name` AS `production_sub_section_name`,
    case
        `xt`.`warping_production_master_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Partial Approved'
    end AS `warping_production_master_status_desc`,
    `xt`.`warping_production_master_status` AS `warping_production_master_status`,
    (
    select
        ifnull(sum(`cr`.`no_of_beams`), 0)
    from
        `xt_warping_production_order_creels` `cr`
    where
        `cr`.`set_no` = `xt`.`set_no`
        and `cr`.`is_delete` = 0) AS `no_of_beams`,
        
         case
        when `xt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `xt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `xt`.`is_active` AS `is_active`,
    `xt`.`is_delete` AS `is_delete`,
    `xt`.`created_by` AS `created_by`,
    `xt`.`created_on` AS `created_on`,
    `xt`.`modified_by` AS `modified_by`,
    `xt`.`modified_on` AS `modified_on`,
    `xt`.`deleted_by` AS `deleted_by`,
    `xt`.`deleted_on` AS `deleted_on`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `xt`.`financial_year` AS `financial_year`,
    `v`.`company_id` AS `company_id`,
    `v`.`company_branch_id` AS `company_branch_id`,
    `xt`.`weaving_production_warping_master_id` AS `weaving_production_warping_master_id`,
    `xt`.`plant_id` AS `plant_id`,
    `xt`.`section_id` AS `section_id`,
    `xt`.`sub_section_id` AS `sub_section_id`,
    `xt`.`production_supervisor_id` AS `production_supervisor_id`,
    `xt`.`weaving_production_warping_master_id` AS `field_id`,
    `xt`.`warping_production_code` AS `field_name`,
    `xt`.`calculative_bottom_kg` AS `calculative_bottom_kg`,
    `xt`.`calculative_bottom_percent` AS `calculative_bottom_percent`,
    `xt`.`actual_bottom_kg` AS `actual_bottom_kg`,
    `xt`.`actual_bottom_percent` AS `actual_bottom_percent`,
    `xt`.`difference_bottom_kg` AS `difference_bottom_kg`,
    `xt`.`difference_bottom_percent` AS `difference_bottom_percent`,
    `xt`.`warping_issue_kg` AS `warping_issue_kg`,
    `wo`.`warping_order_no` AS `warping_order_no`,
    `wo`.`product_material_id` AS `product_material_id`,
    `rm_fg`.`product_material_name` AS `product_material_name`,
    `wo`.`product_material_style` AS `product_material_style`,
    `wo`.`schedule_quantity` AS `schedule_quantity`,
    `wo`.`sort_no` AS `sort_no`,
    `wo`.`no_of_creels` AS `no_of_creels`,
    `wo`.`set_length` AS `set_length`
from
    ((((((`xt_weaving_production_warping_master` `xt`
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `xt_warping_production_order` `wo` on
    (`wo`.`set_no` = `xt`.`set_no`
        and `wo`.`company_id` = `xt`.`company_id`))
left join `smv_product_rm_fg_sr` `rm_fg` on
    (`rm_fg`.`product_material_id` = `wo`.`product_material_id`))
left join `xmv_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `xt`.`company_id`))
left join `cmv_employee_list` `e` on
    (`e`.`employee_id` = `xt`.`production_supervisor_id`
        and `e`.`company_id` = `xt`.`company_id`))
where
    `xt`.`is_delete` = 0;
    
    
    
    -- pashupati_erp_qa.xtv_weaving_production_warping_master_rpt source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `xtv_weaving_production_warping_master_rpt` AS
select
concat(ifnull(`v`.`set_no`, ''), ':Set No:O:N:') AS `set_no`,
    concat(ifnull(`v`.`warping_production_code`, ''), ':Warping Production Code:O:N:') AS `warping_production_code`,
    concat(ifnull(`v`.`warping_production_date`, ''), ':Warping Production Date:Y:D:') AS `warping_production_date`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:O:N:') AS `plant_name`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') AS `prod_year`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') AS `prod_month`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') AS `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N:') AS `production_sub_section_name`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:Y:C:cmv_employee_list:F') AS `production_supervisor_name`,
    concat(ifnull(`v`.`warping_production_master_status`, ''), ':Warping Production Master Status Desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') AS `warping_production_master_status_desc`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') AS `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch_summary:F') AS `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') AS `financial_year`,
    concat(ifnull(`v`.`warping_production_master_status`, ''), ':Warping Production Master Status:O:N:') AS `warping_production_master_status`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') AS `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') AS `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(ifnull(`v`.`weaving_production_warping_master_id`, ''), ':Weaving Production Warping Master Id:O:N:') AS `weaving_production_warping_master_id`,
    concat(ifnull(`v`.`sub_section_id`, ''), ':Sub Section Id:N:N:') AS `sub_section_id`,
    concat(ifnull(`v`.`section_id`, ''), ':Section Id:N:N:') AS `section_id`,
    concat(ifnull(`v`.`plant_id`, ''), ':Plant Id:N:N:') AS `plant_id`,
    concat(ifnull(`v`.`production_supervisor_id`, ''), ':Production Supervisor Id:N:N:') AS `production_supervisor_id`
from
    `xtv_weaving_production_warping_master` `v`
limit 1;

