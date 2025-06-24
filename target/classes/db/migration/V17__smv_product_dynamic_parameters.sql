CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_dynamic_parameters` AS
SELECT
    `rm_fg_sr`.`product_type_name` AS `product_type_name`,
    `rm_fg_sr`.`product_type_group` AS `product_type_group`,
    `rm_fg_sr`.`product_material_name` AS `product_material_name`,
    `rm_fg_sr`.`product_material_code` AS `product_material_code`,
    `b`.`product_parameter_name` AS `product_parameter_name`,

    CASE
        WHEN `d`.`control_type` = 'P' THEN (
            SELECT `pv`.`property_name`
            FROM `am_properties` `pv`
            WHERE `pv`.`property_id` = `b`.`product_parameter_value`
        ) ELSE ''
    END AS `product_parameter_value_name`,
    `b`.`product_parameter_value` AS `product_parameter_value`,
    `b`.`product_parameter_prefix` AS `product_parameter_prefix`,
    `b`.`product_parameter_sufix` AS `product_parameter_sufix`,
    `b`.`product_parameter_calculation_type` AS `product_parameter_calculation_type`,
    `b`.`product_parameter_from_value` AS `product_parameter_from_value`,
    `b`.`product_parameter_to_value` AS `product_parameter_to_value`,
    `b`.`product_parameter_formula` AS `product_parameter_formula`,
    `b`.`remark` AS `remark`,
    `d`.`control_master` AS `control_master`,
    `d`.`control_type` AS `control_type`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `d`.`display_sequence` AS `display_sequence`,

    CASE
        WHEN `b`.`is_active` = 1 THEN 'Active'
        ELSE 'In Active'
    END AS `Active`,

    CASE
        WHEN `b`.`is_delete` = 1 THEN 'Yes'
        ELSE 'No'
    END AS `Deleted`,

    `b`.`is_active` AS `is_active`,
    `b`.`is_delete` AS `is_delete`,
    `b`.`created_by` AS `created_by`,
    `b`.`created_on` AS `created_on`,
    `b`.`modified_by` AS `modified_by`,
    `b`.`modified_on` AS `modified_on`,
    `b`.`deleted_by` AS `deleted_by`,
    `b`.`deleted_on` AS `deleted_on`,
    `b`.`company_id` AS `company_id`,
    `b`.`company_branch_id` AS `company_branch_id`,
    `b`.`product_id` AS `product_id`,
    `b`.`product_type_id` AS `product_type_id`,
    `b`.`product_parameter_id` AS `product_parameter_id`,
    `b`.`product_type_dynamic_controls_id` AS `product_type_dynamic_controls_id`,
    `b`.`product_parameter_id` AS `field_id`,
    `b`.`product_parameter_name` AS `field_name`
FROM
       (((`sm_product_dynamic_parameters` `b`
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `b`.`company_id`
        and `v`.`company_branch_id` = `b`.`company_branch_id`))
left join `sm_product_type_dynamic_controls` `d` on
    (`d`.`product_type_dynamic_controls_id` = `b`.`product_type_dynamic_controls_id`))
left join `smv_product_rm_fg_sr` `rm_fg_sr` on
    (`rm_fg_sr`.`product_type_id` = `b`.`product_type_id`
        and `rm_fg_sr`.`product_material_id` = `b`.`product_id`))