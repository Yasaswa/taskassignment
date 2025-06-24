ALTER TABLE xt_weaving_production_warping_master ADD warping_issue_quantity decimal(18,4) DEFAULT NULL NULL;
ALTER TABLE xt_weaving_production_warping_details ADD product_rm_id varchar(100) NULL;
ALTER TABLE xt_weaving_production_warping_details MODIFY COLUMN weight_per_pkg decimal(18,4) DEFAULT 0.00 NULL COMMENT '*  grid data entry   text box  with validation    ';


create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_master` as
select
    `xt`.`set_no` as `set_no`,
    `xt`.`warping_production_date` as `warping_production_date`,
    `xt`.`warping_production_code` as `warping_production_code`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `e`.`employee_name` as `production_supervisor_name`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    case
        `xt`.`warping_production_master_status`
        when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Partial Approved'
    end as `warping_production_master_status_desc`,
    `xt`.`warping_production_master_status` as `warping_production_master_status`,
    (
        select ifnull(sum(`cr`.`no_of_beams`), 0)
        from `xt_warping_production_order_creels` `cr`
        where `cr`.`set_no` = `xt`.`set_no`
          and `cr`.`is_delete` = 0
    ) as `no_of_beams`,
    `xt`.`is_active` as `is_active`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`financial_year` as `financial_year`,
    `v`.`company_legal_name` as `company_name`,
    `vb`.`company_branch_name` as `company_branch_name`,
    `v`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`,
    `xt`.`weaving_production_warping_master_id` as `field_id`,
    `xt`.`warping_production_code` as `field_name`,
    `xt`.`calculative_bottom_kg` as `calculative_bottom_kg`,
    `xt`.`calculative_bottom_percent` as `calculative_bottom_percent`,
    `xt`.`actual_bottom_kg` as `actual_bottom_kg`,
    `xt`.`actual_bottom_percent` as `actual_bottom_percent`,
    `xt`.`difference_bottom_kg` as `difference_bottom_kg`,
    `xt`.`difference_bottom_percent` as `difference_bottom_percent`,
    simis.`warping_issue_kg` as `warping_issue_kg`,
    simis.`warping_issue_quantity` as `warping_issue_quantity`,
    `wo`.`warping_order_no` as `warping_order_no`,
    `wo`.`product_material_id` as `product_material_id`,
    case
        when `wo`.`production_order_type_id` in (2, 11, 12, 13) then `spr`.`product_rm_name`
        else `spf`.`product_fg_name`
    end as `product_material_name`,
    `wo`.`product_material_style` as `product_material_style`,
    `wo`.`schedule_quantity` as `schedule_quantity`,
    `wo`.`sort_no` as `sort_no`,
    `wo`.`no_of_creels` as `no_of_creels`,
    `wo`.`set_length` as `set_length`
from
    (((((((((`xt_weaving_production_warping_master` `xt`
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `xt_warping_production_order` `wo` on
    (`wo`.`set_no` = `xt`.`set_no`
        and `wo`.`company_id` = `xt`.`company_id`))
left join (
    select
        `set_no`,
        sum(`product_material_issue_weight`) as `warping_issue_kg`,
        sum(`product_material_indent_quantity`) as `warping_issue_quantity`
    from
        `st_indent_material_issue_details`
    where
        `is_delete` = 0
    group by
        `set_no`
) `simis` on `simis`.`set_no` = `xt`.`set_no`)
left join `sm_product_rm` `spr` on
    (`spr`.`product_rm_id` = `wo`.`product_material_id`
        and `wo`.`production_order_type_id` in (2, 11, 12, 13)
            and `spr`.`is_delete` = 0))
left join `sm_product_fg` `spf` on
    (`spf`.`product_fg_id` = `wo`.`product_material_id`
        and `wo`.`production_order_type_id` not in (2, 11, 12, 13)
            and `spf`.`is_delete` = 0))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cm_company` `v` on
    (`v`.`company_id` = `xt`.`company_id`))
left join `cm_company_branch` `vb` on
    (`vb`.`company_branch_id` = `xt`.`company_branch_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`production_supervisor_id`
        and `e`.`company_id` = `xt`.`company_id`))
where
    `xt`.`is_delete` = 0;


create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_master_rpt` as
select
    concat(ifnull(`v`.`warping_production_code`, ''), ':Warping Production Code:O:N:') as `warping_production_code`,
    concat(ifnull(`v`.`warping_production_date`, ''), ':Warping Production Date:Y:D:') as `warping_production_date`,
        concat(ifnull(`v`.`set_no`, ''), ':Set No:O:N:') as `set_no`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:O:N:') as `plant_name`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N:') as `production_sub_section_name`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:Y:C:cmv_employee_list:F') as `production_supervisor_name`,
    concat(ifnull(`v`.`warping_production_master_status`, ''), ':Warping Production Master Status Desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') as `warping_production_master_status_desc`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`warping_production_master_status`, ''), ':Warping Production Master Status:O:N:') as `warping_production_master_status`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`weaving_production_warping_master_id`, ''), ':Weaving Production Warping Master Id:O:N:') as `weaving_production_warping_master_id`,
    concat(ifnull(`v`.`sub_section_id`, ''), ':Sub Section Id:N:N:') as `sub_section_id`,
    concat(ifnull(`v`.`section_id`, ''), ':Section Id:N:N:') as `section_id`,
    concat(ifnull(`v`.`plant_id`, ''), ':Plant Id:N:N:') as `plant_id`,
    concat(ifnull(`v`.`production_supervisor_id`, ''), ':Production Supervisor Id:N:N:') as `production_supervisor_id`
from
    `xtv_weaving_production_warping_master` `v`
limit 1;



create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_details` as
select
    `xt`.`warping_production_date` as `warping_production_date`,
    `xt`.`weaving_production_set_no` as `weaving_production_set_no`,
    `xt`.`warping_order_no` as `warping_order_no`,
    `xt`.`warping_production_code` as `warping_production_code`,
    `m`.`machine_name` as `machine_name`,
    `s`.`godown_name` as `godown_name`,
    `po`.`product_material_name` as `product_material_name`,
    case
        `xt`.`warping_production_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'Z' then 'Canceled'
        when 'I' then 'Inprogress'
        else 'Partial'
    end as `warping_production_status_desc`,
    `xt`.`warping_production_status` as `warping_production_status`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `xt`.`actual_count` as `actual_count`,
    `e`.`employee_name` as `production_operator_name`,
    `e1`.`employee_name` as `production_supervisor_name`,
    `xt`.`shift` as `shift`,
    `xt`.`creel_ends` as `creel_ends`,
    `xt`.`weight_per_pkg` as `weight_per_pkg`,
    `xt`.`t_ends` as `t_ends`,
    `xt`.`length` as `length`,
    `xt`.`breaks_per_million` as `breaks_per_million`,
    `po`.`set_no` as `set_no`,
    `po`.`product_material_id` as `product_material_id`,
    `wm`.`warping_production_master_status` as `warping_production_master_status_desc`,
    `wm`.`warping_production_master_status` as `warping_production_master_status`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    `xt`.`status_remark` as `status_remark`,
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
    `xt`.`financial_year` as `financial_year`,
    `xt`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_warping_details_id` as `weaving_production_warping_details_id`,
    `xt`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`godown_id` as `godown_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`product_rm_id` as `product_rm_id`,
    `xt`.`yarn_count` as `yarn_count`,
    `xt`.`production_operator_id` as `production_operator_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`,
    `xt`.`machine_id` as `machine_id`,
    `xt`.`weaving_production_warping_details_id` as `field_id`,
    `xt`.`warping_order_no` as `field_name`,
    `po`.`no_of_creels` as `warping_creels`,
    `po`.`set_length` as `warping_set_length`,
    `xt`.`beam_no` as `beam_no`,
    `xt`.`speed` as `speed`,
    `xt`.`cut_cones` as `cut_cones`,
    `xt`.`guccha` as `guccha`,
    `xt`.`thin_places` as `thin_places`,
    `xt`.`week_places` as `week_places`,
    `xt`.`week_splice` as `week_splice`,
    `xt`.`sluff_off` as `sluff_off`,
    `xt`.`slub_yarn` as `slub_yarn`,
    `xt`.`total_breaks` as `total_breaks`,
    `po`.`customer_id` as `customer_id`,
    `po`.`customer_order_no` as `customer_order_no`,
    `po`.`job_type` as `job_type`,
    `am`.`property_name` as `beam_name`
from
    (((((((((`xt_weaving_production_warping_details` `xt`
left join `xt_weaving_production_warping_master` `wm` on
    (`wm`.`weaving_production_warping_master_id` = `xt`.`weaving_production_warping_master_id`
        and `wm`.`company_id` = `xt`.`company_id`
        and `wm`.`is_delete` = 0))
left join `cm_godown` `s` on
    (`s`.`godown_id` = `xt`.`godown_id`
        and `s`.`company_id` = `xt`.`company_id`))
left join `xtv_warping_production_order` `po` on
    (`po`.`set_no` = `xt`.`weaving_production_set_no`
        and `po`.`company_id` = `xt`.`company_id`
        and `po`.`is_delete` = 0))
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`
        and `p`.`is_delete` = 0))
left join `cm_machine` `m` on
    (`m`.`machine_id` = `xt`.`machine_id`
        and `m`.`company_id` = `xt`.`company_id`))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`production_operator_id`
        and `e`.`company_id` = `xt`.`company_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `xt`.`production_supervisor_id`
        and `e1`.`company_id` = `xt`.`company_id`))
left join `am_properties` `am` on
    (`am`.`property_id` = `xt`.`beam_no`
        and `am`.`is_delete` = 0))
where
    `xt`.`is_delete` = 0
order by
    `xt`.`weaving_production_warping_details_id` desc;

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_details_rpt` as
select
    concat(ifnull(`v`.`machine_name`, ''), ':Machine Name:O:N:') as `machine_name`,
    concat(ifnull(`v`.`warping_production_date`, ''), ':Warping Production Date:Y:D:') as `warping_production_date`,
    concat(ifnull(`v`.`warping_production_code`, ''), ':Warping Production Code:O:N:') as `warping_production_code`,
    concat(ifnull(`v`.`weaving_production_set_no`, ''), ':Set No:O:N:') as `weaving_production_set_no`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:Y:C:cmv_plant:F') as `plant_name`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N') as `production_sub_section_name`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:O:N:') as `production_supervisor_name`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`warping_production_status`, ''), ':Warping Production Status desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Inprogress,Partial)') as `warping_production_status_desc`,
    concat(ifnull(`v`.`actual_count`, ''), ':Actual Count:O:N:') as `actual_count`,
    concat(ifnull(`v`.`shift`, ''), ':Shift:Y:C:xtv_weaving_production_warping_details:O') as `shift`,
    concat(ifnull(`v`.`creel_ends`, ''), ':Creel Ends:O:N:') as `creel_ends`,
    concat(ifnull(`v`.`weight_per_pkg`, ''), ':Weight Per Pkg:O:N:') as `weight_per_pkg`,
    concat(ifnull(`v`.`t_ends`, ''), ':T-Ends:O:N:') as `t_ends`,
    concat(ifnull(`v`.`length`, ''), ':Length:O:N:') as `length`,
    concat(ifnull(`v`.`breaks_per_million`, ''), ':Breaks Per Million:O:N:') as `breaks_per_million`,
    concat(ifnull(`v`.`warping_production_status`, ''), ':Warping Production Status:O:N:') as `warping_production_status`,
    concat(ifnull(`v`.`warping_production_master_status`, ''), ':Warping Production Master Status:O:N:') as `warping_production_master_status`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`weaving_production_warping_details_id`, ''), ':Weaving Production Warping Details Id:N:N:') as `weaving_production_warping_details_id`,
    concat(ifnull(`v`.`weaving_production_warping_master_id`, ''), ':Warping Master Id:O:N:') as `weaving_production_warping_master_id`,
    concat(ifnull(`v`.`production_operator_id`, ''), ':Production Operator Id:N:N:') as `production_operator_id`,
    concat(ifnull(`v`.`production_supervisor_id`, ''), ':Production Supervisor Id:N:N:') as `production_supervisor_id`,
    concat(ifnull(`v`.`section_id`, ''), ':Section Id:N:N:') as `section_id`,
    concat(ifnull(`v`.`sub_section_id`, ''), ':Sub Section Id:N:N:') as `sub_section_id`,
    concat(ifnull(`v`.`machine_id`, ''), ':Plant Id:N:N:') as `machine_id`,
    concat(ifnull(`v`.`plant_id`, ''), ':Plant Id:N:N:') as `plant_id`
from
    `xtv_weaving_production_warping_details` `v`
limit 1;