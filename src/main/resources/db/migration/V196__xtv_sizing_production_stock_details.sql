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
algorithm = UNDEFINED view .`xtv_sizing_production_stock_details` as
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
	case
		when `xt`.`sized_beam_status` = 'A' then 'Available'
		else 'Dispatched'
	end as `sized_beam_status_desc`,
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
	((((.`xt_sizing_production_stock_details` `xt`
left join .`xtv_beam_inwards_table` `b` on
	(`b`.`beam_inwards_id` = `xt`.`beam_no`))
left join .`cm_customer` `c` on
	(`c`.`customer_id` = `xt`.`customer_id`))
left join .`sm_product_fg` `fg` on
	(`fg`.`product_fg_id` = `xt`.`product_material_id`))
left join .`xtv_warping_production_order_details` `wp` on
	(`wp`.`set_no` = `xt`.`set_no`))
where
	`xt`.`is_delete` = 0;