create or replace
algorithm = UNDEFINED view `mtv_dispatch_inspection_details_trading` as
select
    `mt`.`dispatch_challan_no` as `dispatch_challan_no`,
    `mt`.`dispatch_challan_date` as `dispatch_challan_date`,
    `mt`.`dispatch_challan_version` as `dispatch_challan_version`,
    `c`.`customer_name` as `customer_name`,
    `mt`.`customer_order_no` as `customer_order_no`,
    `mt`.`customer_order_Date` as `customer_order_Date`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_Date` as `sales_order_Date`,
    `mt`.`sales_order_version` as `sales_order_version`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `rmfgsr`.`product_type_group` as `product_type_group`,
    `rmfgsr`.`product_material_std_weight` as `product_material_std_weight`,
    `rmfgsr`.`product_material_tech_spect` as `product_material_tech_spect`,
    `u`.`product_unit_name` as `product_unit_name`,
    `mt`.`inspection_production_code` as `inspection_production_code`,
    `mt`.`inspection_order_no` as `inspection_order_no`,
    `mt`.`inspection_production_date` as `inspection_production_date`,
    `mt`.`inspection_production_set_no` as `inspection_production_set_no`,
    `mt`.`inspection_mtr` as `inspection_mtr`,
    `mt`.`dispatch_quantity` as `dispatch_quantity`,
    `mt`.`sort_no` as `sort_no`,
    `mt`.`roll_no` as `roll_no`,
    `mt`.`remark` as `remark`,
    `mt`.`dispatch_challan_remark` as `dispatch_challan_remark`,
    `v`.`company_name` as `company_name`,
    `mt`.`financial_year` as `financial_year`,
    `mt`.`is_active` as `is_active`,
    `mt`.`is_delete` as `is_delete`,
    `mt`.`created_by` as `created_by`,
    `mt`.`created_on` as `created_on`,
    `mt`.`modified_by` as `modified_by`,
    `mt`.`modified_on` as `modified_on`,
    `mt`.`deleted_by` as `deleted_by`,
    `mt`.`deleted_on` as `deleted_on`,
    `mt`.`company_id` as `company_id`,
    `mt`.`product_material_id` as `product_material_id`,
    `mt`.`product_material_unit_id` as `product_material_unit_id`,
    `mt`.`dispatch_challan_master_transaction_id` as `dispatch_challan_master_transaction_id`,
    `mt`.`customer_id` as `customer_id`,
    `mt`.`godown_id` as `godown_id`,
    `mt`.`dispatch_inspection_details_transaction_id` as `dispatch_inspection_details_transaction_id`,
    `mt`.`weaving_production_inspection_details_id` as `weaving_production_inspection_details_id`,
    `mt`.`dispatch_weight` as `dispatch_weight`
from
    ((((`mt_dispatch_inspection_details_trading` `mt`
left join `cm_customer` `c` on
    (`c`.`company_id` = `mt`.`company_id`
        and `c`.`customer_id` = `mt`.`customer_id`))
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `mt`.`product_material_id`
        and `rmfgsr`.`company_id` = `mt`.`company_id`))
left join `sm_product_unit` `u` on
    (`u`.`product_unit_id` = `mt`.`product_material_unit_id`
        and `u`.`company_id` = `mt`.`company_id`))
left join `cmv_company` `v` on
    (`v`.`company_id` = `mt`.`company_id`))
where
    `mt`.`is_delete` = 0;



	-- erp_development.xtv_weaving_production_inspection_details source
create or replace
algorithm = UNDEFINED view `xtv_weaving_production_inspection_details` as
select
    `xt`.`inspection_production_code` as `inspection_production_code`,
    `xt`.`inspection_production_set_no` as `inspection_production_set_no`,
    `xt`.`inspection_order_no` as `inspection_order_no`,
    `xt`.`inspection_production_date` as `inspection_production_date`,
    `m`.`machine_name` as `machine_name`,
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
        else 'Partial'
    end as `inspection_production_status_desc`,
    `xt`.`inspection_production_status` as `inspection_production_status`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `xt`.`sizing_beam_no` as `sizing_beam_no`,
    `e`.`employee_name` as `production_operator_name`,
    `e1`.`employee_name` as `production_supervisor_name`,
    `xt`.`shift` as `shift`,
    `xt`.`product_in_meter` as `product_in_meter`,
    `xt`.`inspection_mtr` as `inspection_mtr`,
    `xt`.`product_pick` as `product_pick`,
    `xt`.`difference` as `difference`,
    `xt`.`width` as `width`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`roll_no` as `roll_no`,
    `xt`.`average` as `average`,
    `xt`.`weight` as `weight`,
    `po`.`product_material_id` as `product_material_id`,
    `po`.`product_material_name` as `product_material_name`,
    (
    select
        ifnull(sum(`wpi`.`product_in_meter`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi`
    where
        `wpi`.`shift` = `xt`.`shift`
        and `wpi`.`inspection_production_date` = `xt`.`inspection_production_date`
        and `wpi`.`inspection_production_status` = 'A'
        and `wpi`.`is_delete` = 0) as `total_product_in_meter`,
    (
    select
        ifnull(sum(`wpi1`.`inspection_mtr`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi1`
    where
        `wpi1`.`shift` = `xt`.`shift`
        and `wpi1`.`inspection_production_date` = `xt`.`inspection_production_date`
        and `wpi1`.`inspection_production_status` = 'A'
        and `wpi1`.`is_delete` = 0) as `total_inspection_mtr`,
    (
    select
        ifnull(`wi`.`product_in_meter` - sum(`wi`.`inspection_mtr`), 0)
    from
        `xt_weaving_production_inspection_details` `wi`
    where
        `wi`.`inspection_production_set_no` = `xt`.`inspection_production_set_no`
        and `wi`.`is_delete` = 0) as `balance_product_in_meter`,
    (
    select
        ifnull(sum(`wpi2`.`weight`), 0)
    from
        `xt_weaving_production_inspection_details` `wpi2`
    where
        `wpi2`.`shift` = `xt`.`shift`
        and `wpi2`.`inspection_production_date` = `xt`.`inspection_production_date`
        and `wpi2`.`inspection_production_status` = 'A'
        and `wpi2`.`is_delete` = 0) as `total_weight`,
    `wm`.`inspection_production_master_status` as `inspection_production_master_status_desc`,
    `wm`.`inspection_production_master_status` as `inspection_production_master_status`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    `po`.`order_type` as `order_type`,
    `xts`.`sales_order_no` as `sales_order_no`,
    `xts`.`customer_name` as `customer_name`,
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
    `v`.`company_branch_name` as `company_branch_name`,
    `xt`.`financial_year` as `financial_year`,
    `v`.`company_id` as `company_id`,
    `v`.`company_branch_id` as `company_branch_id`,
    `xt`.`weaving_production_inspection_details_id` as `weaving_production_inspection_details_id`,
    `xt`.`weaving_production_inspection_master_id` as `weaving_production_inspection_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`product_id` as `product_id`,
    `rm`.`product_type_group` as `product_type_group`,
    `rm`.`product_material_packing_id` as `product_material_packing_id`,
    `rm`.`product_type_id` as `product_type_id`,
    `rm`.`product_material_stock_unit_id` as `product_material_stock_unit_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`godown_id` as `godown_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`production_operator_id` as `production_operator_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`,
    `xt`.`machine_id` as `machine_id`
from
    (((((((((((`xt_weaving_production_inspection_details` `xt`
left join `xt_weaving_production_inspection_master` `wm` on
    (`wm`.`weaving_production_inspection_master_id` = `xt`.`weaving_production_inspection_master_id`
        and `wm`.`company_id` = `xt`.`company_id`))
left join `xmv_production_sub_section_godown_mapping` `s` on
    (`s`.`production_sub_section_godown_id` = `xt`.`godown_id`
        and `s`.`company_id` = `xt`.`company_id`))
left join `xtv_weaving_production_order` `po` on
    (`po`.`set_no` = `xt`.`inspection_production_set_no`
        and `po`.`company_id` = `xt`.`company_id`))
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `cm_machine` `m` on
    (`m`.`machine_id` = `xt`.`machine_id`
        and `m`.`company_id` = `xt`.`company_id`))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `xt`.`company_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`production_operator_id`
        and `e`.`company_id` = `xt`.`company_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `xt`.`production_supervisor_id`
        and `e1`.`company_id` = `xt`.`company_id`))
left join `smv_product_rm_fg_sr` `rm` on
    (`rm`.`product_material_id` = `xt`.`product_id`))
left join `xtv_weaving_production_order_style_details` `xts` on
    (`xts`.`set_no` = `xt`.`inspection_production_set_no`
        and `xts`.`company_id` = `xt`.`company_id`))
where
    `xt`.`is_delete` = 0;
