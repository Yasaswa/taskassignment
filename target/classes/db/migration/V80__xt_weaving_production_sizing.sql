ALTER TABLE xt_weaving_production_sizing_details ADD speed bigint(20) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD calculative_net_weight decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD rf decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD visc decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD creel_a_tension decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD creel_b_tension decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD sq_pres_max decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD sq_pres_min decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD saw_box_a_temp decimal(18,2)NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD saw_box_b_temp decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD strech decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD moisture decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD after_waxing decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD leasing_tension decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD t_1 decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD t_2 decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD t_3 decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD t_4 decimal(18,2)NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD winding_tension decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD beam_pressing_tension decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD lappers decimal(18,2) NULL;
ALTER TABLE xt_weaving_production_sizing_details ADD comb_breaks decimal(18,2) NULL;



-- erp_development.xtv_weaving_production_sizing_details source

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_sizing_details` as
select
    `xt`.`weaving_production_set_no` as `weaving_production_set_no`,
    `xt`.`sizing_production_order_no` as `sizing_production_order_no`,
    `xt`.`sizing_production_code` as `sizing_production_code`,
    `xt`.`sizing_production_date` as `sizing_production_date`,
    `xt`.`yarn_count` as `yarn_count`,
    `m`.`machine_name` as `machine_name`,
    `p`.`plant_name` as `plant_name`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `xt`.`size_waste` as `size_waste`,
    `xt`.`unsize_waste` as `unsize_waste`,
    `xt`.`creel_waste` as `creel_waste`,
    `s`.`godown_name` as `godown_name`,
    `xt`.`status_remark` as `status_remark`,
    `xt`.`speed` as `speed`,
    `xt`.`calculative_net_weight` as `calculative_net_weight`,
    `xt`.`rf` as `rf`,
    `xt`.`visc` as `visc`,
    `xt`.`creel_a_tension` as `creel_a_tension`,
    `xt`.`creel_b_tension` as `creel_b_tension`,
    `xt`.`sq_pres_max` as `sq_pres_max`,
    `xt`.`sq_pres_max` as `sq_pres_min`,
    `xt`.`saw_box_a_temp` as `saw_box_a_temp`,
    `xt`.`saw_box_b_temp` as `saw_box_b_temp`,
    `xt`.`strech` as `strech`,
    `xt`.`moisture` as `moisture`,
    `xt`.`after_waxing` as `after_waxing`,
    `xt`.`leasing_tension` as `leasing_tension`,
    `xt`.`t_1` as `t_1`,
    `xt`.`t_2` as `t_2`,
    `xt`.`t_3` as `t_3`,
    `xt`.`t_4` as `t_4`,
    `xt`.`winding_tension` as `winding_tension`,
    `xt`.`beam_pressing_tension` as `beam_pressing_tension`,
    `xt`.`lappers` as `lappers`,
    `xt`.`comb_breaks` as `comb_breaks`,
    case
        `xt`.`sizing_production_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'Z' then 'Canceled'
        when 'I' then 'Inprogress'
        else 'Partial'
    end as `sizing_production_status_desc`,
    `xt`.`sizing_production_status` as `sizing_production_status`,
    `xt`.`actual_count` as `actual_count`,
    `e`.`employee_name` as `production_operator_name`,
    `e1`.`employee_name` as `production_supervisor_name`,
    `xt`.`shift` as `shift`,
    `xt`.`beam_no` as `beam_no`,
    `xt`.`sizing_beam_no` as `sizing_beam_no`,
    `xt`.`warping_length` as `warping_length`,
    `xt`.`sizing_length` as `sizing_length`,
    (
    select
        ifnull(sum(`sp`.`sizing_length`), 0)
    from
        `xt_weaving_production_sizing_details` `sp`
    where
        `sp`.`shift` = `xt`.`shift`
        and `sp`.`sizing_production_date` = `xt`.`sizing_production_date`
        and `sp`.`sizing_production_status` = 'A'
        and `sp`.`is_delete` = 0) as `sizing_total_length`,
    (
    select
        ifnull(sum(`sp`.`net_weight`), 0)
    from
        `xt_weaving_production_sizing_details` `sp`
    where
        `sp`.`shift` = `xt`.`shift`
        and `sp`.`sizing_production_date` = `xt`.`sizing_production_date`
        and `sp`.`sizing_production_status` = 'A'
        and `sp`.`is_delete` = 0) as `net_total_weight`,
    (
    select
        ifnull(sum(`sp`.`size_waste`), 0)
    from
        `xt_weaving_production_sizing_details` `sp`
    where
        `sp`.`shift` = `xt`.`shift`
        and `sp`.`sizing_production_date` = `xt`.`sizing_production_date`
        and `sp`.`sizing_production_status` = 'A'
        and `sp`.`is_delete` = 0) as `size_total_waste`,
    (
    select
        ifnull(sum(`sp`.`unsize_waste`), 0)
    from
        `xt_weaving_production_sizing_details` `sp`
    where
        `sp`.`shift` = `xt`.`shift`
        and `sp`.`sizing_production_date` = `xt`.`sizing_production_date`
        and `sp`.`sizing_production_status` = 'A'
        and `sp`.`is_delete` = 0) as `unsize_total_waste`,
    (
    select
        ifnull(sum(`sp`.`creel_waste`), 0)
    from
        `xt_weaving_production_sizing_details` `sp`
    where
        `sp`.`shift` = `xt`.`shift`
        and `sp`.`sizing_production_date` = `xt`.`sizing_production_date`
        and `sp`.`sizing_production_status` = 'A'
        and `sp`.`is_delete` = 0) as `creel_total_waste`,
    `xt`.`net_weight` as `net_weight`,
    `xt`.`gross_weight` as `gross_weight`,
    `xt`.`tare_weight` as `tare_weight`,
    `xt`.`total_ends` as `total_ends`,
    `xt`.`unsize_beam_weight` as `unsize_beam_weight`,
    `xt`.`size_pickup` as `size_pickup`,
    `xt`.`sizing_rs` as `sizing_rs`,
    `xt`.`rate` as `rate`,
    `xt`.`amount` as `amount`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    case
        `po`.`order_type` when 'J' then 'Job Work'
        else 'SO-Based'
    end as `order_type_desc`,
    `po`.`order_type` as `order_type`,
    `xts`.`sales_order_no` as `sales_order_no`,
    `xts`.`customer_name` as `customer_name`,
    `po`.`set_no` as `set_no`,
    `po`.`product_material_name` as `so_item_name`,
    `sm`.`sizing_production_master_status` as `sizing_production_master_status_desc`,
    `sm`.`sizing_production_master_status` as `sizing_production_master_status`,
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
    `xt`.`weaving_production_sizing_details_id` as `weaving_production_sizing_details_id`,
    `xt`.`weaving_production_sizing_master_id` as `weaving_production_sizing_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`godown_id` as `godown_id`,
    `xt`.`production_operator_id` as `production_operator_id`,
    `po`.`product_material_id` as `product_material_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`,
    `xt`.`machine_id` as `machine_id`,
    `xt`.`weaving_production_sizing_details_id` as `field_id`,
    `xt`.`sizing_production_order_no` as `field_name`
from
    ((((((((((`xt_weaving_production_sizing_details` `xt`
left join `xt_weaving_production_sizing_master` `sm` on
    (`sm`.`weaving_production_sizing_master_id` = `xt`.`weaving_production_sizing_master_id`
        and `sm`.`company_id` = `xt`.`company_id`))
left join `xmv_production_sub_section_godown_mapping` `s` on
    (`s`.`godown_id` = `xt`.`godown_id`
        and `s`.`company_id` = `xt`.`company_id`))
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`))
left join `cm_machine` `m` on
    (`m`.`machine_id` = `xt`.`machine_id`
        and `m`.`company_id` = `xt`.`company_id`))
left join `xmv_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `xt`.`company_id`))
left join `cmv_employee_list` `e` on
    (`e`.`employee_id` = `xt`.`production_operator_id`
        and `e`.`company_id` = `xt`.`company_id`))
left join `cmv_employee_list` `e1` on
    (`e1`.`employee_id` = `xt`.`production_supervisor_id`
        and `e1`.`company_id` = `xt`.`company_id`))
left join `xtv_weaving_production_order` `po` on
    (`po`.`set_no` = `xt`.`weaving_production_set_no`
        and `po`.`company_id` = `xt`.`company_id`))
left join `xtv_weaving_production_order_style_details` `xts` on
    (`xts`.`set_no` = `xt`.`weaving_production_set_no`
        and `xts`.`company_id` = `xt`.`company_id`))
where
    `xt`.`is_delete` = 0
order by
    `xt`.`weaving_production_sizing_details_id` desc;



