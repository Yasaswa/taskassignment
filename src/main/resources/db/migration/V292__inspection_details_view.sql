ALTER TABLE xt_weaving_production_inspection_details ADD approved_date date DEFAULT NULL NULL;
ALTER TABLE xt_weaving_production_inspection_details ADD stock_status varchar(55) DEFAULT 'P' NULL;
ALTER TABLE xt_weaving_production_inspection_details ADD stock_status_description varchar(100) NULL;


create or replace
algorithm = UNDEFINED view `xtv_weaving_production_inspection_details` as
select
    `xt`.`inspection_production_code` as `inspection_production_code`,
    `msomt`.`sales_order_no` as `sales_order_no`,
    `xt`.`inspection_production_set_no` as `inspection_production_set_no`,
    `xt`.`inspection_order_no` as `inspection_order_no`,
    `xt`.`inspection_production_date` as `inspection_production_date`,
    `xt`.`machine_id` as `machine_name`,
    `s`.`godown_name` as `godown_name`,
    case
        `xt`.`inspection_production_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'Z' then 'Canceled'
        when 'I' then 'Inprogress'
        when 'DG' then 'Dispatch Note Generated'
        when 'PD' then 'Partial Dispatch'
        when 'D' then 'Dispatch Done'
        else 'Pending'
    end as `inspection_production_status_desc`,
    `xt`.`inspection_production_status` as `inspection_production_status`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `xt`.`sizing_beam_no` as `sizing_beam_no`,
    `e`.`employee_name` as `production_operator_name`,
    `xt`.`shift` as `shift`,
    `xt`.`product_in_meter` as `product_in_meter`,
    `xt`.`inspection_mtr` as `inspection_mtr`,
    `xt`.`product_pick` as `product_pick`,
    `xt`.`difference` as `difference`,
    `xt`.`width` as `width`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`style` as `style`,
    `xt`.`roll_no` as `roll_no`,
    `xt`.`average` as `average`,
    `xt`.`weight` as `weight`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`product_material_name` as `product_material_name`,
    (
    select
        ifnull(sum(`wpi`.`product_in_meter`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi`
    where
        `wpi`.`inspection_production_status` = 'A'
        and `wpi`.`is_delete` = 0) as `total_product_in_meter`,
    (
    select
        ifnull(sum(`wpi1`.`inspection_mtr`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi1`
    where
        `wpi1`.`inspection_production_status` = 'A'
        and `wpi1`.`is_delete` = 0) as `total_inspection_mtr`,
    `xt`.`product_in_meter` - `xt`.`inspection_mtr` as `balance_product_in_meter`,
    (
    select
        ifnull(sum(`wpi2`.`weight`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi2`
    where
        `wpi2`.`inspection_production_status` = 'A'
        and `wpi2`.`is_delete` = 0) as `total_weight`,
    `wm`.`inspection_production_master_status` as `inspection_production_master_status_desc`,
    `wm`.`inspection_production_master_status` as `inspection_production_master_status`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    `xt`.`status_remark` as `status_remark`,
    `xt`.`dispatch_quantity` as `dispatch_quantity`,
    `xt`.`dispatch_weight` as `dispatch_weight`,
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
    `xt`.`financial_year` as `financial_year`,
    `v`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_inspection_details_id` as `weaving_production_inspection_details_id`,
    `xt`.`weaving_production_inspection_master_id` as `weaving_production_inspection_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `spt`.`product_type_group` as `product_type_group`,
    `spf`.`product_fg_packing_id` as `product_material_packing_id`,
    `spf`.`product_type_id` as `product_type_id`,
    `spf`.`product_fg_stock_unit_id` as `product_material_stock_unit_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`godown_id` as `godown_id`,
    `xt`.`godown_section_id` as `godown_section_id`,
    `xt`.`godown_section_beans_id` as `godown_section_beans_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`production_operator_id` as `production_operator_id`,
    `xt`.`machine_id` as `machine_id`,
    `xwpo`.`t_ends` as `total_ends`,
    `xt`.`dispatch_date` as `dispatch_date`,
    `xt`.`stock_status` as `stock_status`,
    case
        `xt`.`stock_status` when 'P' then 'Pending'
        when 'A' then 'In-Stock'
        when 'D' then 'Dispatched'
        else 'Pending'
    end as `stock_status_description`,
    `xt`.`approved_date` as `approved_date`
from
    ((((((((((`xt_weaving_production_inspection_details` `xt`
left join `xt_weaving_production_inspection_master` `wm` on
    (`wm`.`weaving_production_inspection_master_id` = `xt`.`weaving_production_inspection_master_id`
        and `wm`.`company_id` = `xt`.`company_id`))
left join `cm_godown` `s` on
    (`s`.`godown_id` = `xt`.`godown_id`
        and `s`.`company_id` = `xt`.`company_id`))
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cm_company` `v` on
    (`v`.`company_id` = `xt`.`company_id` and `v`.`is_delete` = 0))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`production_operator_id`
        and `e`.`company_id` = `xt`.`company_id`))
left join `sm_product_fg` `spf` on
    (`spf`.`product_fg_id` = `xt`.`product_material_id`
        and `spf`.`is_delete` = 0))
left join `sm_product_type` `spt` on
    (`spt`.`product_type_id` = `spf`.`product_type_id`
        and `spt`.`is_delete` = 0))
left join `xt_warping_production_order` `xwpo` on
    (`xwpo`.`set_no` = `xt`.`inspection_production_set_no`
        and `xwpo`.`is_delete` = 0))
left join `mt_sales_order_master_trading` `msomt` on
    (`msomt`.`customer_order_no` = `xwpo`.`customer_order_no`
        and `msomt`.`is_delete` = 0
        and `msomt`.`company_id` = `xwpo`.`company_id`))
where
    `xt`.`is_delete` = 0;