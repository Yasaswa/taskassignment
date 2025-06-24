
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
        `xt`.`warping_production_master_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        else 'Partial Approved'
    end as `warping_production_master_status_desc`,
    `xt`.`warping_production_master_status` as `warping_production_master_status`,
    (
    select
        ifnull(sum(`cr`.`no_of_beams`), 0)
    from
        `xt_warping_production_order_creels` `cr`
    where
        `cr`.`set_no` = `xt`.`set_no`
        and `cr`.`is_delete` = 0) as `no_of_beams`,
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
    `cs`.`supplier_name` as `supplier_name`,
    `xt`.`supplier_id` as `supplier_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`,
    `xt`.`weaving_production_warping_master_id` as `field_id`,
    `xt`.`warping_production_code` as `field_name`,
    `xt`.`calculative_bottom_kg` as `calculative_bottom_kg`,
    `xt`.`calculative_bottom_percent` as `calculative_bottom_percent`,
    `xt`.`actual_bottom_kg` as `actual_bottom_kg`,
    `xt`.`actual_bottom_percent` as `actual_bottom_percent`,
    `xt`.`difference_bottom_kg` as `difference_bottom_kg`,
    `xt`.`difference_bottom_percent` as `difference_bottom_percent`,
    `simis`.`warping_issue_kg` as `warping_issue_kg`,
    `simis`.`warping_issue_quantity` as `warping_issue_quantity`,
    `simis`.`warping_issue_boxes` as `warping_issue_boxes`,
    `xt`.`batch_no` as `batch_no`,
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
    ((((((((((`xt_weaving_production_warping_master` `xt`
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `cm_supplier` `cs` on 
    (`cs`.`supplier_id` = `xt`.`supplier_id` and `cs`.`is_delete` = 0))
left join `xt_warping_production_order` `wo` on
    (`wo`.`set_no` = `xt`.`set_no`
        and `wo`.`company_id` = `xt`.`company_id`))
left join (
    select
        `st_indent_material_issue_details`.`set_no` as `set_no`,
        sum(`st_indent_material_issue_details`.`product_material_issue_weight`) as `warping_issue_kg`,
        sum(`st_indent_material_issue_details`.`product_material_issue_quantity`) as `warping_issue_quantity`,
        sum(`st_indent_material_issue_details`.`product_material_issue_boxes`) as `warping_issue_boxes`
    from
        `st_indent_material_issue_details`
    where
        `st_indent_material_issue_details`.`is_delete` = 0
    group by
        `st_indent_material_issue_details`.`set_no`) `simis` on
    (`simis`.`set_no` = `xt`.`set_no`))
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
    `xt`.`is_delete` = 0
order by
    `xt`.`weaving_production_warping_master_id` desc;