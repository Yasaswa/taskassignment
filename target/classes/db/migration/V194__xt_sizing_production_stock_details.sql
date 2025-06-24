-- erp_development.xtv_warping_production_order source

create or replace
algorithm = UNDEFINED view `xtv_warping_production_order` as
select
    `xt`.`set_no` as `set_no`,
    `xt`.`warping_order_no` as `warping_order_no`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`customer_order_no` as `customer_order_no`,
    `mt`.`sales_order_no` as `sales_order_no`,
    `mt`.`sales_order_date` as `sales_order_date`,
    `mt`.`job_type` AS `job_type`,
    `xt`.`warping_order_status` as `warping_order_status`,
    `xt`.`warping_schedule_date` as `warping_schedule_date`,
    `xt`.`warping_plan_date` as `warping_plan_date`,
    `xt`.`schedule_quantity` as `schedule_quantity`,
    `xt`.`order_quantity` as `order_quantity`,
    `xt`.`other_terms_conditions` as `other_terms_conditions`,
    `xt`.`no_of_creels` as `no_of_creels`,
    `xt`.`set_length` as `set_length`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `xt`.`product_material_style` as `product_material_style`,
    `xt`.`t_ends` as `t_ends`,
    case
        `xt`.`warping_order_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'I' then 'Partial'
    end as `warping_order_status_desc`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`status_remark` as `status_remark`,
    `e`.`employee_name` as `approved_by_name`,
    `xt`.`approved_date` as `approved_date`,
    `xt`.`remark` as `remark`,
    case
        `xt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`warping_production_order_id` as `warping_production_order_id`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`production_order_type_id` as `production_order_type_id`,
    `xt`.`customer_id` as `customer_id`,
    `xt`.`approved_by_id` as `approved_by_id`,
    `xt`.`warping_production_order_id` as `field_id`
from
    (((`xt_warping_production_order` `xt`
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`approved_by_id`
        and `e`.`is_delete` = 0))
left join `mt_sales_order_master_trading` `mt` on
    (`mt`.`customer_order_no` = `xt`.`customer_order_no`
        and `mt`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `xt`.`product_material_id`))
where
    `xt`.`is_delete` = 0;



-- erp_development.xtv_weaving_production_warping_details source

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
left join `xmv_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cmv_employee_list` `e` on
    (`e`.`employee_id` = `xt`.`production_operator_id`
        and `e`.`company_id` = `xt`.`company_id`))
left join `cmv_employee_list` `e1` on
    (`e1`.`employee_id` = `xt`.`production_supervisor_id`
        and `e1`.`company_id` = `xt`.`company_id`))

left join `am_properties` `am` on
    (`am`.`property_id` = `xt`.`beam_no`
        and `am`.`is_delete` = 0))

where
    `xt`.`is_delete` = 0
order by
    `xt`.`weaving_production_warping_details_id` desc;



-- erp_development.xt_sizing_production_stock_details definition

CREATE TABLE `xt_sizing_production_stock_details` (
  `sizing_stock_details_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '*read only back end auto generated*',
  `weaving_production_sizing_details_id` bigint(20) NOT NULL,
  `weaving_production_sizing_master_id` bigint(20) NOT NULL,
  `sizing_production_code` varchar(255) NOT NULL,
  `set_no` varchar(100) DEFAULT NULL,
  `job_type` varchar(100) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session*',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session*',
  `product_material_id` varchar(20) NOT NULL,
  `beam_no` varchar(50) NOT NULL,
  `total_ends` decimal(18,4) DEFAULT 0.0000,
  `sizing_length` decimal(18,4) DEFAULT 0.0000,
  `customer_id` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `rate` decimal(18,4) DEFAULT 0.0000,
  `sizing_production_date` date NOT NULL,
  `godown_id` bigint(20) NOT NULL,
  `section_id` bigint(20) NOT NULL,
  `sub_section_id` bigint(20) NOT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `financial_year` varchar(20) NOT NULL COMMENT '*  Header part will come from Session ',
  `sized_beam_status` varchar(2) DEFAULT 'A' COMMENT 'A - Availabel, D - Dispatch',
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`sizing_stock_details_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- erp_development.xtv_beam_inwards_table source

create or replace
algorithm = UNDEFINED view `xtv_beam_inwards_table` as
select
    `xbi`.`beam_inwards_id` as `beam_inwards_id`,
    `xbi`.`company_id` as `company_id`,
    `xbi`.`financial_year` as `financial_year`,
    `xbi`.`company_branch_id` as `company_branch_id`,
    `xbi`.`beam_inwards_date` as `beam_inwards_date`,
    `xbi`.`customer_id` as `customer_id`,
    `xbi`.`section` as `section`,
    `xbi`.`beam_type` as `beam_type`,
    `xbi`.`beam_no` as `beam_no`,
    `xbi`.`beam_width` as `beam_width`,
    `xbi`.`beam_status` as `beam_status`,
    `xbi`.`beam_inward_type` as `beam_inward_type`,
    case
        when `xbi`.`beam_status` = 'E' then 'Empty'
        else 'Completed'
    end as `beam_status_desc`,
    case
        when `xbi`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `xbi`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `xbi`.`is_active` as `is_active`,
    `xbi`.`is_delete` as `is_delete`,
    `xbi`.`created_by` as `created_by`,
    `xbi`.`created_on` as `created_on`,
    `xbi`.`modified_by` as `modified_by`,
    `xbi`.`modified_on` as `modified_on`,
    `xbi`.`deleted_by` as `deleted_by`,
    `xbi`.`deleted_on` as `deleted_on`,
    `cc`.`customer_name` as `customer_name`,
    `cc`.`customer_short_name` as `customer_short_name`,
    `amp`.`property_group` as `property_group`,
    `amp`.`property_value` as `property_value`
from
    ((`xt_beam_inwards_table` `xbi`
left join `cm_customer` `cc` on
    (`xbi`.`customer_id` = `cc`.`customer_id`
        and `cc`.`is_delete` = 0))
left join `am_properties` `amp` on
    (`amp`.`property_id` = `xbi`.`beam_type`
        and `amp`.`is_delete` = 0))
where
    `xbi`.`is_delete` = 0;


-- erp_development.xtv_sizing_production_stock_details source

create or replace
algorithm = UNDEFINED view `xtv_sizing_production_stock_details` as
select
    `xt`.`sizing_stock_details_id` as `sizing_stock_details_id`,
    `xt`.`weaving_production_sizing_details_id` as `weaving_production_sizing_details_id`,
    `xt`.`weaving_production_sizing_master_id` as `weaving_production_sizing_master_id`,
    `xt`.`sizing_production_code` as `sizing_production_code`,
    `xt`.`set_no` as `set_no`,
    `xt`.`job_type` as `job_type`,
    `xt`.`product_material_id` as `product_material_id`,
    `xt`.`beam_no` as `beam_no`,
    `xt`.`total_ends` as `total_ends`,
    `xt`.`sizing_length` as `sizing_length`,
    `xt`.`customer_id` as `customer_id`,
    `xt`.`amount` as `amount`,
    `xt`.`rate` as `rate`,
    `xt`.`sizing_production_date` as `sizing_production_date`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`sized_beam_status` as `sized_beam_status`,
    `xt`.`godown_id` as `godown_id`,
    `b`.`beam_inward_type` as `beam_inward_type`,
    `b`.`beam_status` as `beam_status`,
    `b`.`property_group` as `property_group`,
    `c`.`customer_name` as `customer_name`,
    `fg`.`product_fg_name` as `product_fg_name`,
    `fg`.`product_fg_code` as `product_fg_code`,
    `wp`.`production_count_name` as `actual_count`,
    `wp`.`product_material_name` as `product_material_name`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`

from
    ((((`xt_sizing_production_stock_details` `xt`
left join `xtv_beam_inwards_table` `b` on
    (`b`.`beam_inwards_id` = `xt`.`beam_no` ))
left join `cm_customer` `c` on
    (`c`.`customer_id` = `xt`.`customer_id`))
left join `sm_product_fg` `fg` on
    (`fg`.`product_fg_id` = `xt`.`product_material_id`))
 left join `xtv_warping_production_order_details` `wp` on
    (`wp`.`set_no` = `xt`.`set_no`))
where
    `xt`.`is_delete` = 0;


ALTER TABLE xt_weaving_production_sizing_details DROP COLUMN beam_no;


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
    `xt`.`no_of_creels` as `no_of_creels`,
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


-- erp_development.xtv_weaving_production_sizing_details_rpt source

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_sizing_details_rpt` as
select
    concat(ifnull(`v`.`order_type`, ''), ':Order Type:Y:H:(So-Based,Job Work)') as `order_type_desc`,
    concat(ifnull(`v`.`sales_order_no`, ''), ':Sales Order No:Y:C:xtv_weaving_production_order:F') as `sales_order_no`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:O:N:') as `customer_name`,
    concat(ifnull(`v`.`weaving_production_set_no`, ''), ':Weaving Production Set No:Y:C:xtv_weaving_production_sizing_details:O') as `weaving_production_set_no`,
    concat(ifnull(`v`.`sizing_production_date`, ''), ':sizing Production Date:Y:D:') as `sizing_production_date`,
    concat(ifnull(`v`.`so_item_name`, ''), ':SO Item Name:O:N:') as `so_item_name`,
    concat(ifnull(`v`.`warping_length`, ''), ':Warping Length:Y:T:') as `warping_length`,
    concat(ifnull(`v`.`sizing_rs`, ''), ':Sizing RS:O:N:') as `sizing_rs`,
    concat(ifnull(`v`.`sizing_beam_no`, ''), ':Sizing Beam No:O:N:') as `sizing_beam_no`,
    concat(ifnull(`v`.`sizing_length`, ''), ':Sizing Length:Y:T:') as `sizing_length`,
    concat(ifnull(`v`.`yarn_count`, ''), ':Yarn Count:Y:T:') as `yarn_count`,
    concat(ifnull(`v`.`actual_count`, ''), ':Actual Count:Y:T:') as `actual_count`,
    concat(ifnull(`v`.`total_ends`, ''), ':Total Ends:O:N:') as `total_ends`,
    concat(ifnull(`v`.`gross_weight`, ''), ':Gross Weight:Y:T:') as `gross_weight`,
    concat(ifnull(`v`.`tare_weight`, ''), ':Tare Weight:Y:T:') as `tare_weight`,
    concat(ifnull(`v`.`net_weight`, ''), ':Net Weight:Y:T:') as `net_weight`,
    concat(ifnull(`v`.`unsize_beam_weight`, ''), ':Unsize Beam Weight:Y:T:') as `unsize_beam_weight`,
    concat(ifnull(`v`.`size_pickup`, ''), ':Size Pickup:O:N:') as `size_pickup`,
    concat(ifnull(`v`.`production_operator_name`, ''), ':Production Operator Name:O:N:') as `production_operator_name`,
    concat(ifnull(`v`.`shift`, ''), ':Shift:Y:C:xtv_weaving_production_warping_details:O') as `shift`,
    concat(ifnull(`v`.`size_waste`, ''), ':Size Waste:O:N:') as `size_waste`,
    concat(ifnull(`v`.`unsize_waste`, ''), ':Unsize Waste:O:N:') as `unsize_waste`,
    concat(ifnull(`v`.`creel_waste`, ''), ':Creel Waste:O:N:') as `creel_waste`,
    concat(ifnull(`v`.`rate`, ''), ':Rate:Y:T:') as `rate`,
    concat(ifnull(`v`.`amount`, ''), ':Amount:Y:T:') as `amount`,
    concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:O:N:') as `godown_name`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:Y:C:cmv_plant:F') as `plant_name`,
    concat(ifnull(`v`.`machine_name`, ''), ':Machine Name:O:N:') as `machine_name`,
    concat(ifnull(`v`.`production_supervisor_name`, ''), ':Production Supervisor Name:O:N:') as `production_supervisor_name`,
    concat(ifnull(`v`.`sizing_production_order_no`, ''), ':Sizing Production Order No:Y:C:xtv_weaving_production_sizing_details:O') as `sizing_production_order_no`,
    concat(ifnull(`v`.`sizing_production_code`, ''), ':Sizing Production Code:O:N:') as `sizing_production_code`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Production Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Production Sub Section Name:O:N') as `production_sub_section_name`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`sizing_production_status`, ''), ':Warping Production Status Desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial)') as `sizing_production_status_desc`,
    concat(ifnull(`v`.`sizing_production_master_status`, ''), ':Sizing Production Master Status:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') as      `sizing_production_master_status_desc`,
    concat(ifnull(`v`.`sizing_production_status`, ''), ':Sizing Production Status:O:N:)') as `sizing_production_status`,
    concat(ifnull(`v`.`sizing_production_master_status`, ''), ':Sizing Production Master Status:O:N:') as `sizing_production_master_status`,
    concat(ifnull(`v`.`status_remark`, ''), ':Status Remark:O:N:)') as `status_remark`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch Name:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`is_active`, ''), ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(ifnull(`v`.`is_delete`, ''), ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`order_type`, ''), ':Order Type Desc:N:N:') as `order_type`,
    concat(ifnull(`v`.`weaving_production_sizing_master_id`, ''), ':Weaving Production Sizing Master Id:O:N:') as `weaving_production_sizing_master_id`,
    concat(ifnull(`v`.`weaving_production_sizing_details_id`, ''), ':Weaving Production Sizing Details Id:N:N:') as `weaving_production_sizing_details_id`,
    concat(ifnull(`v`.`production_operator_id`, ''), ':Production Operator Id:N:N:') as `production_operator_id`,
    concat(ifnull(`v`.`production_supervisor_id`, ''), ':Production Supervisor Id:N:N:') as `production_supervisor_id`,
    concat(ifnull(`v`.`section_id`, ''), ':Section Id:N:N:') as `section_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`godown_id`, ''), ':Godown Id:N:N:') as `godown_id`,
    concat(ifnull(`v`.`sub_section_id`, ''), ':Sub Section Id:N:N:') as `sub_section_id`,
    concat(ifnull(`v`.`machine_id`, ''), ':Plant Id:N:N:') as `machine_id`,
    concat(ifnull(`v`.`plant_id`, ''), ':Plant Id:N:N:') as `plant_id`
from
    `xtv_weaving_production_sizing_details` `v`
limit 1;

