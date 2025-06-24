-- erp_development.xtv_weaving_production_warping_material source

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_material` as
select
    `xt`.`weaving_production_set_no` as `weaving_production_set_no`,
    `xt`.`warping_production_order_no` as `warping_production_order_no`,
    `xt`.`warping_production_code` as `warping_production_code`,
    `xt`.`warping_production_date` as `warping_production_date`,
    `wp`.`product_material_name` as `product_material_name`,
    `p`.`plant_name` as `plant_name`,
    `wp`.`product_material_stock_unit_name` as `product_material_unit_name`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    `xt`.`shift` as `shift`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `xt`.`product_material_quantity` as `product_material_quantity`,
    `xt`.`consumption_quantity` as `consumption_quantity`,
    ifnull((select sum(`st`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `st` where `st`.`product_rm_id` = `xt`.`product_material_id` and `st`.`company_id` = `xt`.`company_id` and `st`.`is_delete` = 0), 0) as `stock_quantity`,
    (
    select
        ifnull(`xt`.`product_material_quantity` - sum(`wp`.`consumption_quantity`), 0)
    from
        `xt_weaving_production_warping_material` `wp`
    where
        `wp`.`weaving_production_set_no` = `xt`.`weaving_production_set_no`
        and `wp`.`product_material_id` = `xt`.`product_material_id`
        and `wp`.`is_delete` = 0) as `product_material_balance_quantity`,
    case
        `xt`.`material_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'Z' then 'Canceled'
        when 'I' then 'Inprogress'
        else 'Partial'
    end as `material_status_desc`,
    `xt`.`material_status` as `material_status`,
    `xt`.`material_status_remark` as `material_status_remark`,
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
    `xt`.`weaving_production_warping_material_id` as `weaving_production_warping_material_id`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`product_material_unit_id` as `product_material_unit_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`weaving_production_warping_material_id` as `field_id`,
    `xt`.`warping_production_order_no` as `field_name`
from
    (((`xt_weaving_production_warping_material` `xt`
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `xmv_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `smv_product_rm_fg_sr` `wp` on
    (`wp`.`product_material_id` = `xt`.`product_material_id`))
where
    `xt`.`is_delete` = 0
order by
    `xt`.`weaving_production_warping_material_id` desc;
