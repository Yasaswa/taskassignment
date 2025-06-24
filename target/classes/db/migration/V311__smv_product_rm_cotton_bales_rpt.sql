-- DB MIGRATION


create or replace
algorithm = UNDEFINED view `smv_product_rm_cotton_bales_rpt_details` as
select
    concat(ifnull(`rm`.`product_rm_name`, 'NA'), ':Material Name:Y:T') as `product_rm_name`,
        concat(ifnull(`rm`.`product_rm_short_name`, 'NA'), ':Material Short Name:O:N:') as `product_rm_short_name`,

    concat(ifnull(`rm`.`product_rm_code`, 'NA'), ':Material Code:Y:T') as `product_rm_code`,
    concat(ifnull(`rm`.`product_type_name`, 'NA'), ':Product Type:O:N:') as `product_type_name`,
    concat(ifnull(`rm`.`product_type_group`, 'NA'), ':Product Type Group:N:N:') as `product_type_group`,
    concat(ifnull(`rm`.`product_category1_name`, 'NA'), ':Material Category 1:O:N') as `product_category1_name`,
    concat(ifnull(`rmt`.`product_category2_name`, 'NA'), ':Material Category 2:O:N:') as `product_category2_name`,
    concat(ifnull(`rm`.`product_rm_hsn_sac_code`, 'NA'), ':HSN/SAC Code:Y:C:cmv_hsn_sac:F') as `product_rm_hsn_sac_code`,
    concat(ifnull(`rm`.`product_rm_hsn_sac_rate`, 'NA'), ':HSN/SAC Percent:Y:T') as `product_rm_hsn_sac_rate`,
    concat(ifnull(`rm`.`product_rm_purchase_unit_name`, 'NA'), ':Purchase Unit:Y:C:smv_product_unit:F') as `product_rm_purchase_unit_name`,
    concat(ifnull(`rm`.`product_rm_sales_unit_name`, 'NA'), ':Sales Unit:Y:C:smv_product_unit:F') as `product_rm_sales_unit_name`,
    concat(ifnull(`rm`.`product_rm_stock_unit_name`, 'NA'), ':Stock Unit:Y:C:smv_product_unit:F') as `product_rm_stock_unit_name`,
    concat(ifnull(`rmc`.`product_rm_std_weight`, 'NA'), ':Std. Weight:Y:T') as `product_rm_std_weight`,
    concat(ifnull(`rmc`.`profit_center_name`, 'NA'), ':Profit Center Name:Y:C:fmv_profit_center:F') as `profit_center_name`,
    concat(ifnull(`rmc`.`cost_center_name`, 'NA'), ':Cost Center Name:Y:C:fmv_cost_center:F') as `cost_center_name`,
    concat(ifnull(`rmc`.`routing_code`, 'NA'), ':Routing Code:O:N:') as `routing_code`,
    concat(ifnull(`rm`.`remark`, 'NA'), ':Remark:O:N:') as `remark`,
    concat(case when `rm`.`is_delete` = 1 then 'Yes' else 'No' end, ':Delete Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`rm`.`created_by`, 'NA'), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`rm`.`created_on`, 'NA'), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`rm`.`modified_by`, 'NA'), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`rm`.`modified_on`, 'NA'), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`rm`.`deleted_by`, 'NA'), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`rm`.`deleted_on`, 'NA'), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`rm`.`product_rm_id`, 'NA'), ':Material Id:O:N:') as `product_rm_id`
from
    ((`smv_product_rm` `rm`
left join `smv_product_rm_technical` `rmt` on
    (`rmt`.`company_id` = `rm`.`company_id`
        and `rmt`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmt`.`is_active` = 1))
left join `smv_product_rm_commercial` `rmc` on
    (`rmc`.`company_id` = `rm`.`company_id`
        and `rmc`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmc`.`is_active` = 1))
where
    `rm`.`is_delete` = 0
limit 1;




create or replace
algorithm = UNDEFINED view `smv_product_rm_cotton_bales_rpt_summary` as
select
    concat(ifnull(`rmc`.`product_rm_name`, ''), ':Material Name:Y:T') as `product_rm_name`,
        concat(ifnull(`rm`.`product_rm_short_name`, ''), ':Material Short Name:Y:T') as `product_rm_short_name`,
    concat(ifnull(`rm`.`product_rm_code`, ''), ':Material Code:Y:T') as `product_rm_code`,
    concat(ifnull(`rmt`.`product_type_name`, ''), ':Material Type:O:N:') as `product_type_name`,
    concat(ifnull(`rmt`.`product_type_group`, ''), ':Material Type Group:O:N:') as `product_type_group`,
    concat(ifnull(`rmt`.`product_category1_name`, ''), ':Category 1:O:N:') as `product_category1_name`,
    concat(ifnull(`rmt`.`product_category2_name`, ''), ':Category 2:O:N:') as `product_category2_name`,
    concat(ifnull(`rm`.`product_rm_hsn_sac_code`, ''), ':HSN_SAC Code:Y:C:smv_product_rm:O') as `product_rm_hsn_sac_code`,
    concat(ifnull(`rmc`.`product_rm_std_weight`, ''), ':Standard Wt.:Y:T') as `product_rm_std_weight`,
    concat(ifnull(case when `rm`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`rm`.`created_by`, ''), 'Created By:O:N:') as `created_by`,
    concat(ifnull(`rm`.`created_on`, ''), 'Modified On:O:N:') as `created_on`,
    concat(ifnull(`rm`.`modified_by`, ''), 'Modified By:O:N:') as `modified_by`,
    concat(ifnull(`rm`.`modified_on`, ''), 'Modified On:O:N:') as `modified_on`,
    concat(ifnull(`rm`.`deleted_by`, ''), 'Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`rm`.`deleted_on`, ''), 'Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`rm`.`product_rm_id`, ''), ':Material Id:O:N:') as `product_rm_id`
from
    ((`smv_product_rm` `rm`
left join `smv_product_rm_technical` `rmt` on
    (`rmt`.`company_id` = `rm`.`company_id`
        and `rmt`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmt`.`is_active` = 1))
left join `smv_product_rm_commercial` `rmc` on
    (`rmc`.`company_id` = `rm`.`company_id`
        and `rmc`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmc`.`is_active` = 1))
where
    `rm`.`is_delete` = 0
limit 1;



create or replace
algorithm = UNDEFINED view `smv_product_rm_rpt_details` as
select
    concat(ifnull(`rm`.`product_rm_name`, 'NA'), ':Raw Material Name:Y:T') as `product_rm_name`,
    concat(ifnull(`rm`.`product_rm_code`, 'NA'), ':Raw Material Code:Y:T') as `product_rm_code`,
    concat(ifnull(`rm`.`product_type_name`, 'NA'), ':Product Type:Y:C:smv_product_type:F') as `product_type_name`,
    concat(ifnull(`rm`.`godown_name`, ''), ':Godown Name:Y:C:cmv_godown:F') as `godown_name`,
    concat(ifnull(`rm`.`godown_section_name`, ''), ':Godown Section Name:Y:C:cmv_godown_section:F') as `godown_section_name`,
    concat(ifnull(`rm`.`godown_section_beans_name`, ''), ':Godown Section Beans Name:Y:C:cmv_godown_section_beans:F') as `godown_section_beans_name`,
    concat(ifnull(`rmt`.`product_rm_technical_name`, 'NA'), ':Tech. Spect.:O:N:') as `product_rm_technical_name`,
    concat(ifnull(`rm`.`product_type_group`, 'NA'), ':Product Type Group:N:N:') as `product_type_group`,
    concat(ifnull(`rm`.`product_category1_name`, 'NA'), ':Raw Material Category 1:Y:C:smv_product_category1:F') as `product_category1_name`,
    concat(ifnull(`rmt`.`product_category2_name`, 'NA'), ':Raw Material Category 2:Y:C:smv_product_category2:F') as `product_category2_name`,
    concat(ifnull(`rmt`.`product_category3_name`, 'NA'), ':Raw Material Category 3:N:N:') as `product_category3_name`,
    concat(ifnull(`rmt`.`product_category4_name`, 'NA'), ':Raw Material Category 4:N:N:') as `product_category4_name`,
    concat(ifnull(`rmt`.`product_category5_name`, 'NA'), ':Raw Material Category 5:N:N:') as `product_category5_name`,
    concat(ifnull(`rmt`.`product_make_name`, 'NA'), ':Raw Material Make:N:N:') as `product_make_name`,
    concat(ifnull(`rmt`.`product_material_type_name`, 'NA'), ':Raw Material Type:N:N:') as `product_material_type_name`,
    concat(ifnull(`rmt`.`product_material_grade_name`, 'NA'), ':Raw Material Grade:N:N:') as `product_material_grade_name`,
    concat(ifnull(`rmt`.`product_material_shape_name`, 'NA'), ':Raw Material Shape:N:N:') as `product_material_shape_name`,
    concat(ifnull(`rmt`.`product_rm_name`, 'NA'), ':Alternate Raw Material:N:N:') as `product_alternate_rm_name`,
    concat(ifnull(`rm`.`product_rm_tech_spect`, 'NA'), ':Tech Spect.:O:N:') as `product_rm_tech_spect`,
    concat(ifnull(`rm`.`product_rm_oem_part_code`, 'NA'), ':OEM Part Code:Y:T:') as `product_rm_oem_part_code`,
    concat(ifnull(`rm`.`product_rm_hsn_sac_code`, 'NA'), ':HSN/SAC Code:Y:C:cmv_hsn_sac:F') as `product_rm_hsn_sac_code`,
    concat(ifnull(`rm`.`product_rm_hsn_sac_rate`, 'NA'), ':HSN/SAC Percent:Y:T') as `product_rm_hsn_sac_rate`,
    concat(ifnull(`rm`.`product_rm_purchase_unit_name`, 'NA'), ':Purchase Unit:Y:C:smv_product_unit:F') as `product_rm_purchase_unit_name`,
    concat(ifnull(`rm`.`product_rm_sales_unit_name`, 'NA'), ':Sales Unit:Y:C:smv_product_unit:F') as `product_rm_sales_unit_name`,
    concat(ifnull(`rm`.`product_rm_stock_unit_name`, 'NA'), ':Stock Unit:Y:C:smv_product_unit:F') as `product_rm_stock_unit_name`,
    concat(ifnull(`rm`.`product_rm_packing_name`, 'NA'), ':Packing:Y:C:smv_product_packing:F') as `product_rm_packing_name`,
    concat(ifnull(`rm`.`product_rm_item_sr_no`, 'NA'), ':Item Sr. No:O:N:') as `product_rm_item_sr_no`,
    concat(ifnull(`rm`.`product_rm_drawing_no`, 'NA'), ':Drawing No:N:N:') as `product_rm_drawing_no`,
    concat(ifnull(`rm`.`product_rm_model_no`, 'NA'), ':Model No:O:N:') as `product_rm_model_no`,
    concat(ifnull(`rm`.`product_rm_bar_code`, 'NA'), ':Bar Code:O:N:') as `product_rm_bar_code`,
    concat(ifnull(`rm`.`product_rm_qr_code`, 'NA'), ':QR Code:O:N:') as `product_rm_qr_code`,
    concat(ifnull(`rm`.`product_consumption_mode`, 'NA'), ':Consumption Mode:Y:P:MaterialConsumptionMode') as `product_consumption_mode`,
    concat(ifnull(`rm`.`product_origin_type`, 'NA'), ':Origin Type:Y:P:MaterialOrigin') as `product_origin_type`,
    concat(ifnull(`rm`.`product_origin_country`, 'NA'), ':Origin Country:O:N:') as `product_origin_country`,
    concat(ifnull(`rm`.`product_rm_hsn_sac_is_exampted`, 'NA'), ':HSN Type Exampted:O:N:') as `product_rm_hsn_sac_is_exampted`,
    concat(ifnull(`rmt`.`product_material_colour`, 'NA'), ':Colour:N:N:') as `product_material_colour`,
    concat(ifnull(`rmt`.`assembly_scrap_percent`, 'NA'), ':Scrap Percent:O:N:') as `assembly_scrap_percent`,
    concat(ifnull(`rmc`.`product_rm_gross_weight`, 'NA'), ':Gross Weight:Y:T') as `product_rm_gross_weight`,
    concat(ifnull(`rmc`.`product_rm_std_weight`, 'NA'), ':Std. Weight:Y:T') as `product_rm_std_weight`,
    concat(ifnull(`rmc`.`product_rm_volume`, 'NA'), ':Volume:Y:T') as `product_rm_volume`,
    concat(ifnull(`rmc`.`product_rm_mrp`, 'NA'), ':MRP:Y:T') as `product_rm_mrp`,
    concat(ifnull(`rmc`.`product_rm_landed_price`, 'NA'), ':Landed Price:Y:T') as `product_rm_landed_price`,
    concat(ifnull(`rmc`.`product_rm_avg_price`, 'NA'), ':Avg. Price:Y:T') as `product_rm_avg_price`,
    concat(ifnull(`rmc`.`product_rm_std_profit_percent`, 'NA'), ': Std. Profit Percent:Y:T') as `product_rm_std_profit_percent`,
    concat(ifnull(`rmc`.`product_rm_std_discount_percent`, 'NA'), ':Std. Discount Percent:Y:T') as `product_rm_std_discount_percent`,
    concat(ifnull(`rmc`.`product_rm_moq`, 'NA'), ':MOQ:Y:T') as `product_rm_moq`,
    concat(ifnull(`rmc`.`product_rm_mpq`, 'NA'), ':MPQ:Y:T') as `product_rm_mpq`,
    concat(ifnull(`rmc`.`product_rm_mov`, 'NA'), ':MOV:Y:T') as `product_rm_mov`,
    concat(ifnull(`rmc`.`product_rm_eoq`, 'NA'), ':EOQ:Y:T') as `product_rm_eoq`,
    concat(ifnull(`rmc`.`product_rm_min_stock_level`, 'NA'), ':Minimum Stock:Y:T') as `product_rm_min_stock_level`,
    concat(ifnull(`rmc`.`product_rm_max_stock_level`, 'NA'), ':Maximum Stock:Y:T') as `product_rm_max_stock_level`,
    concat(ifnull(`rmc`.`product_rm_reorder_stock_level`, 'NA'), ':Reorder Stock:Y:T') as `product_rm_reorder_stock_level`,
    concat(ifnull(`rmc`.`purchase_tolerance_percent`, 'NA'), ': Purchase Tolerance Percent:Y:T') as `purchase_tolerance_percent`,
    concat(ifnull(`rmc`.`product_rm_price_type`, 'NA'), ':Price Type:Y:H:(Fixed,Moving Average)') as `product_rm_price_type`,
    concat(ifnull(`rmc`.`product_rm_abc_analysis`, 'NA'), ':ABC Category:Y:H:(A,B,C)') as `product_rm_abc_analysis`,
    concat(ifnull(`rm`.`product_rm_short_name`, 'NA'), ':Short Name:O:N:') as `product_rm_short_name`,
    concat(ifnull(`rmc`.`qa_required`, 'NA'), ':QA Required:Y:H:(Yes,No)') as `qa_required`,
    concat(ifnull(`rmc`.`test_certificate_required`, 'NA'), ':Test Certificate Required:Y:H:(Yes,No)') as `test_certificate_required`,
    concat(ifnull(`rmc`.`profit_center_name`, 'NA'), ':Profit Center Name:Y:C:fmv_profit_center:F') as `profit_center_name`,
    concat(ifnull(`rmc`.`cost_center_name`, 'NA'), ':Cost Center Name:Y:C:fmv_cost_center:F') as `cost_center_name`,
    concat(ifnull(`rmc`.`routing_code`, 'NA'), ':Routing Code:O:N:') as `routing_code`,
    concat(ifnull(`rm`.`product_rm_print_name`, 'NA'), ':Print Name:O:N:') as `product_rm_print_name`,
    concat(ifnull(`rm`.`remark`, 'NA'), ':Remark:O:N:') as `remark`,
    concat(case when `rm`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `rm`.`is_delete` = 1 then 'Yes' else 'No' end, ':Delete Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`rm`.`created_by`, 'NA'), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`rm`.`created_on`, 'NA'), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`rm`.`modified_by`, 'NA'), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`rm`.`modified_on`, 'NA'), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`rm`.`deleted_by`, 'NA'), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`rm`.`deleted_on`, 'NA'), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`rm`.`company_id`, 'NA'), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`rm`.`company_branch_id`, 'NA'), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`rm`.`product_type_id`, 'NA'), ':Product Type Id:N:N:') as `product_type_id`,
    concat(ifnull(`rm`.`product_category1_id`, 'NA'), ':Product Category Id 1:N:N:') as `product_category1_id`,
    concat(ifnull(`rm`.`product_rm_id`, 'NA'), ':Raw Material Id:O:N:') as `product_rm_id`,
    concat(ifnull(`rmc`.`profit_center_id`, ''), ':Profit Center Id:N:N:') as `profit_center_id`,
    concat(ifnull(`rmc`.`cost_center_id`, ''), ':Cost Center Id:N:N:') as `cost_center_id`
from
    ((`smv_product_rm` `rm`
left join `smv_product_rm_technical` `rmt` on
    (`rmt`.`company_id` = `rm`.`company_id`
        and `rmt`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmt`.`is_active` = 1))
left join `smv_product_rm_commercial` `rmc` on
    (`rmc`.`company_id` = `rm`.`company_id`
        and `rmc`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmc`.`is_active` = 1))
where
    `rm`.`is_delete` = 0
limit 1;