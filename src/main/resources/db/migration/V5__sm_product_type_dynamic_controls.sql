DELETE FROM  sm_product_type_dynamic_controls WHERE product_type_dynamic_controls_id=78;
DELETE FROM  sm_product_type_dynamic_controls WHERE product_type_dynamic_controls_id=76;

ALTER TABLE xt_production_settings ADD COLUMN warping_plan_product_constant decimal(18,4) DEFAULT '0.0';

INSERT INTO xt_production_settings (company_id,approx_warping_production_bottom,warping_plan_product_constant) VALUES
	 (1,1500.0000,1630);

ALTER TABLE sm_product_fg ADD qa_remark varchar(255) DEFAULT NULL;

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_fg` AS
select
    `b`.`product_fg_id` AS `product_fg_id`,
    `b`.`product_fg_code` AS `product_fg_code`,
    `b`.`product_fg_name` AS `product_fg_name`,
    `c1`.`product_type_name` AS `product_type_name`,
    `c1`.`product_category1_name` AS `product_category1_name`,
    `b`.`product_fg_short_name` AS `product_fg_short_name`,
    `b`.`product_fg_print_name` AS `product_fg_print_name`,
    `b`.`product_fg_tech_spect` AS `product_fg_tech_spect`,
    `b`.`product_fg_oem_part_code` AS `product_fg_oem_part_code`,
    `b`.`product_fg_our_part_code` AS `product_fg_our_part_code`,
    `h`.`hsn_sac_code` AS `product_fg_hsn_sac_code`,
    `h`.`hsn_sac_rate` AS `product_fg_hsn_sac_rate`,
    `u1`.`product_unit_name` AS `product_fg_purchase_unit_name`,
    `u2`.`product_unit_name` AS `product_fg_sales_unit_name`,
    `u3`.`product_unit_name` AS `product_fg_stock_unit_name`,
    `p`.`product_packing_name` AS `product_fg_packing_name`,
    `b`.`product_fg_item_sr_no` AS `product_fg_item_sr_no`,
    `b`.`product_fg_drawing_no` AS `product_fg_drawing_no`,
    `b`.`product_fg_model_no` AS `product_fg_model_no`,
    `b`.`product_fg_bar_code` AS `product_fg_bar_code`,
    `b`.`product_fg_qr_code` AS `product_fg_qr_code`,
    `g`.`godown_name` AS `godown_name`,
    `b`.`product_consumption_mode` AS `product_consumption_mode`,
    `b`.`product_origin_type` AS `product_origin_type`,
    `b`.`product_origin_country` AS `product_origin_country`,
    `c1`.`product_category1_short_name` AS `product_category1_short_name`,
    `h`.`hsn_sac_type` AS `product_fg_hsn_sac_type`,
    `h`.`is_exampted` AS `product_fg_hsn_sac_is_exampted`,
    `p`.`quantity_per_packing` AS `product_fg_quantity_per_packing`,
    `p`.`weight_per_packing` AS `product_fg_weight_per_packing`,
    `g`.`godown_short_name` AS `godown_short_name`,
    `gs`.`godown_section_name` AS `godown_section_name`,
    `gsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `b`.`bom_applicable` AS `bom_applicable`,
    `b`.`remark` AS `remark`,
    `b`.`qa_remark` AS `qa_remark`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `c1`.`product_type_group` AS `product_type_group`,
    `c1`.`product_type_short_name` AS `product_type_short_name`,
    `b`.`is_active` AS `is_active`,
    `b`.`is_delete` AS `is_delete`,
    `b`.`created_by` AS `created_by`,
    `b`.`created_on` AS `created_on`,
    `b`.`modified_by` AS `modified_by`,
    `b`.`modified_on` AS `modified_on`,
    `b`.`deleted_by` AS `deleted_by`,
    `b`.`deleted_on` AS `deleted_on`,
    `b`.`company_id` AS `company_id`,
    `v`.`company_branch_id` AS `company_branch_id`,
    `b`.`product_type_id` AS `product_type_id`,
    `b`.`product_category1_id` AS `product_category1_id`,
    `b`.`product_fg_hsn_sac_code_id` AS `product_fg_hsn_sac_code_id`,
    `b`.`product_fg_purchase_unit_id` AS `product_fg_purchase_unit_id`,
    `b`.`product_fg_sales_unit_id` AS `product_fg_sales_unit_id`,
    `b`.`product_fg_stock_unit_id` AS `product_fg_stock_unit_id`,
    `b`.`product_fg_packing_id` AS `product_fg_packing_id`,
    `b`.`godown_id` AS `godown_id`,
    `b`.`godown_section_id` AS `godown_section_id`,
    `b`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `b`.`product_id` AS `product_id`,
    `b`.`product_fg_name` AS `field_name`,
    `b`.`product_fg_id` AS `field_id`
from
    ((((((((((`sm_product_fg` `b`
left join `cmv_company` `v` on
    (`v`.`company_id` = `b`.`company_id`))
left join `sm_product_unit` `u1` on
    (`u1`.`product_unit_id` = `b`.`product_fg_purchase_unit_id`
        and `u1`.`is_delete` = 0))
left join `sm_product_unit` `u2` on
    (`u2`.`product_unit_id` = `b`.`product_fg_sales_unit_id`
        and `u2`.`is_delete` = 0))
left join `sm_product_unit` `u3` on
    (`u3`.`product_unit_id` = `b`.`product_fg_stock_unit_id`
        and `u3`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `b`.`product_fg_hsn_sac_code_id`
        and `h`.`is_delete` = 0))
left join `smv_product_category1` `c1` on
    (`c1`.`is_delete` = 0
        and `c1`.`product_category1_id` = `b`.`product_category1_id`))
left join `sm_product_packing` `p` on
    (`p`.`product_packing_id` = `b`.`product_fg_packing_id`))
left join `cm_godown` `g` on
    (`g`.`godown_id` = `b`.`godown_id`))
left join `cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `b`.`godown_section_id`))
left join `cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `b`.`godown_section_beans_id`))
where
    `b`.`is_delete` = 0;