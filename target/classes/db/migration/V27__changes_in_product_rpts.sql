-- ERP_PASHUPATI_PROD_1_0.smv_product_rm_rpt_summary source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_rm_rpt_summary` AS
select
    concat(ifnull(`rmc`.`product_rm_name`, ''), ':Product Name:Y:T') AS `product_rm_name`,
    concat(ifnull(`rm`.`product_rm_code`, ''), ':Product Code:Y:T') AS `product_rm_code`,
    concat(ifnull(`rmt`.`product_type_name`, ''), ':Product Type:Y:C:smv_product_type:F') AS `product_type_name`,
    concat(ifnull(`rm`.`godown_name`, ''), ':Godown Name:Y:C:cmv_godown:F') AS `godown_name`,
    concat(ifnull(`rm`.`godown_section_name`, ''), ':Godown Section Name:Y:C:cmv_godown_section:F') AS `godown_section_name`,
    concat(ifnull(`rm`.`godown_section_beans_name`, ''), ':Godown Section Beans Name:Y:C:cmv_godown_section_beans:F') AS `godown_section_beans_name`,
    concat(ifnull(`rmt`.`product_rm_technical_name`, ''), ':Technical Name:N:N:') AS `product_rm_technical_name`,
    concat(ifnull(`rmt`.`product_type_group`, ''), ':Product Type Group:O:N:') AS `product_type_group`,
    concat(ifnull(`rmt`.`product_category1_name`, ''), ':Category 1:Y:C:smv_product_category1:F') AS `product_category1_name`,
    concat(ifnull(`rmt`.`product_category2_name`, ''), ':Category 2:Y:C:smv_product_category2:F') AS `product_category2_name`,
    concat(ifnull(`rmt`.`product_category3_name`, ''), ':Category 3:N:N:') AS `product_category3_name`,
    concat(ifnull(`rmt`.`product_category4_name`, ''), ':Category 4:N:N:') AS `product_category4_name`,
    concat(ifnull(`rmt`.`product_category5_name`, ''), ':Category 5:N:N:') AS `product_category5_name`,
    concat(ifnull(`rmt`.`product_make_name`, ''), ':Make Name:N:N:') AS `product_make_name`,
    concat(ifnull(`rmt`.`product_material_type_name`, ''), ':Material Type:Y:C:smv_product_material_type:F') AS `product_material_type_name`,
    concat(ifnull(`rmt`.`product_material_grade_name`, ''), ':Material Grade:N:N:') AS `product_material_grade_name`,
    concat(ifnull(`rmt`.`product_material_shape_name`, ''), ':Material Shape:N:N:') AS `product_material_shape_name`,
    concat(ifnull(`rm`.`product_rm_hsn_sac_code`, ''), ':HSN_SAC Code:Y:C:smv_product_rm:O') AS `product_rm_hsn_sac_code`,
    concat(ifnull(`rm`.`product_rm_oem_part_code`, ''), ':OEM Part Code:Y:C:smv_product_rm:O') AS `product_rm_oem_part_code`,
    concat(ifnull(`rm`.`product_rm_our_part_code`, ''), ':OUR Part Code:N:N:') AS `product_rm_our_part_code`,
    concat(ifnull(`rmt`.`product_rm_drawing_no`, ''), ':Drawing No:N:N:') AS `product_rm_drawing_no`,
    concat(ifnull(`rmc`.`product_rm_std_weight`, ''), ':Standard Wt.:Y:T') AS `product_rm_std_weight`,
    concat(ifnull(`rmc`.`product_rm_mrp`, ''), ':MRP:Y:T') AS `product_rm_mrp`,
    concat(ifnull(case when `rm`.`is_active` = 1 then 'Active' else 'In Active' end, ''), ':Is Active:Y:H:(Active, In Active)') AS `Active`,
    concat(ifnull(case when `rm`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Is Deleted:Y:H:(Yes, No)') AS `Deleted`,
    concat(ifnull(`rm`.`created_by`, ''), 'Created By:O:N:') AS `created_by`,
    concat(ifnull(`rm`.`created_on`, ''), 'Modified On:O:N:') AS `created_on`,
    concat(ifnull(`rm`.`modified_by`, ''), 'Modified By:O:N:') AS `modified_by`,
    concat(ifnull(`rm`.`modified_on`, ''), 'Modified On:O:N:') AS `modified_on`,
    concat(ifnull(`rm`.`deleted_by`, ''), 'Deleted By:O:N:') AS `deleted_by`,
    concat(ifnull(`rm`.`deleted_on`, ''), 'Deleted On:O:N:') AS `deleted_on`,
    concat(ifnull(`rm`.`product_rm_id`, ''), ':Material Id:O:N:') AS `product_rm_id`
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



-- ERP_PASHUPATI_PROD_1_0.smv_product_rm_rpt_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_rm_rpt_details` AS
select
    concat(ifnull(`rm`.`product_rm_name`, 'NA'), ':Raw Material Name:Y:T') AS `product_rm_name`,
    concat(ifnull(`rm`.`product_rm_code`, 'NA'), ':Raw Material Code:Y:T') AS `product_rm_code`,
    concat(ifnull(`rm`.`product_type_name`, 'NA'), ':Product Type:Y:C:smv_product_type:F') AS `product_type_name`,
    concat(ifnull(`rm`.`godown_name`, ''), ':Godown Name:Y:C:cmv_godown:F') AS `godown_name`,
    concat(ifnull(`rm`.`godown_section_name`, ''), ':Godown Section Name:Y:C:cmv_godown_section:F') AS `godown_section_name`,
    concat(ifnull(`rm`.`godown_section_beans_name`, ''), ':Godown Section Beans Name:Y:C:cmv_godown_section_beans:F') AS `godown_section_beans_name`,
    concat(ifnull(`rmt`.`product_rm_technical_name`, 'NA'), ':Tech. Spect.:O:N:') AS `product_rm_technical_name`,
    concat(ifnull(`rm`.`product_type_group`, 'NA'), ':Product Type Group:N:N:') AS `product_type_group`,
    concat(ifnull(`rm`.`product_category1_name`, 'NA'), ':Raw Material Category 1:Y:C:smv_product_category1:F') AS `product_category1_name`,
    concat(ifnull(`rmt`.`product_category2_name`, 'NA'), ':Raw Material Category 2:Y:C:smv_product_category2:F') AS `product_category2_name`,
    concat(ifnull(`rmt`.`product_category3_name`, 'NA'), ':Raw Material Category 3:N:N:') AS `product_category3_name`,
    concat(ifnull(`rmt`.`product_category4_name`, 'NA'), ':Raw Material Category 4:N:N:') AS `product_category4_name`,
    concat(ifnull(`rmt`.`product_category5_name`, 'NA'), ':Raw Material Category 5:N:N:') AS `product_category5_name`,
    concat(ifnull(`rmt`.`product_make_name`, 'NA'), ':Raw Material Make:N:N:') AS `product_make_name`,
    concat(ifnull(`rmt`.`product_material_type_name`, 'NA'), ':Raw Material Type:N:N:') AS `product_material_type_name`,
    concat(ifnull(`rmt`.`product_material_grade_name`, 'NA'), ':Raw Material Grade:N:N:') AS `product_material_grade_name`,
    concat(ifnull(`rmt`.`product_material_shape_name`, 'NA'), ':Raw Material Shape:N:N:') AS `product_material_shape_name`,
    concat(ifnull(`rmt`.`product_rm_name`, 'NA'), ':Alternate Raw Material:N:N:') AS `product_alternate_rm_name`,
    concat(ifnull(`rm`.`product_rm_tech_spect`, 'NA'), ':Tech Spect.:O:N:') AS `product_rm_tech_spect`,
    concat(ifnull(`rm`.`product_rm_oem_part_code`, 'NA'), ':OEM Part Code:Y:T:') AS `product_rm_oem_part_code`,
    concat(ifnull(`rm`.`product_rm_hsn_sac_code`, 'NA'), ':HSN/SAC Code:Y:C:cmv_hsn_sac:F') AS `product_rm_hsn_sac_code`,
    concat(ifnull(`rm`.`product_rm_hsn_sac_rate`, 'NA'), ':HSN/SAC Percent:Y:T') AS `product_rm_hsn_sac_rate`,
    concat(ifnull(`rm`.`product_rm_purchase_unit_name`, 'NA'), ':Purchase Unit:Y:C:smv_product_unit:F') AS `product_rm_purchase_unit_name`,
    concat(ifnull(`rm`.`product_rm_sales_unit_name`, 'NA'), ':Sales Unit:Y:C:smv_product_unit:F') AS `product_rm_sales_unit_name`,
    concat(ifnull(`rm`.`product_rm_stock_unit_name`, 'NA'), ':Stock Unit:Y:C:smv_product_unit:F') AS `product_rm_stock_unit_name`,
    concat(ifnull(`rm`.`product_rm_packing_name`, 'NA'), ':Packing:Y:C:smv_product_packing:F') AS `product_rm_packing_name`,
    concat(ifnull(`rm`.`product_rm_item_sr_no`, 'NA'), ':Item Sr. No:O:N:') AS `product_rm_item_sr_no`,
    concat(ifnull(`rm`.`product_rm_drawing_no`, 'NA'), ':Drawing No:N:N:') AS `product_rm_drawing_no`,
    concat(ifnull(`rm`.`product_rm_model_no`, 'NA'), ':Model No:O:N:') AS `product_rm_model_no`,
    concat(ifnull(`rm`.`product_rm_bar_code`, 'NA'), ':Bar Code:O:N:') AS `product_rm_bar_code`,
    concat(ifnull(`rm`.`product_rm_qr_code`, 'NA'), ':QR Code:O:N:') AS `product_rm_qr_code`,
    concat(ifnull(`rm`.`product_consumption_mode`, 'NA'), ':Consumption Mode:Y:P:MaterialConsumptionMode') AS `product_consumption_mode`,
    concat(ifnull(`rm`.`product_origin_type`, 'NA'), ':Origin Type:Y:P:MaterialOrigin') AS `product_origin_type`,
    concat(ifnull(`rm`.`product_origin_country`, 'NA'), ':Origin Country:o:N:') AS `product_origin_country`,
    concat(ifnull(`rm`.`product_rm_hsn_sac_is_exampted`, 'NA'), ':HSN Type Exampted:O:N:') AS `product_rm_hsn_sac_is_exampted`,
    concat(ifnull(`rmt`.`product_material_colour`, 'NA'), ':Colour:N:N:') AS `product_material_colour`,
    concat(ifnull(`rmt`.`assembly_scrap_percent`, 'NA'), ':Scrap Percent:O:N:') AS `assembly_scrap_percent`,
    concat(ifnull(`rmc`.`product_rm_gross_weight`, 'NA'), ':Gross Weight:Y:T') AS `product_rm_gross_weight`,
    concat(ifnull(`rmc`.`product_rm_std_weight`, 'NA'), ':Std. Weight:Y:T') AS `product_rm_std_weight`,
    concat(ifnull(`rmc`.`product_rm_volume`, 'NA'), ':Volume:Y:T') AS `product_rm_volume`,
    concat(ifnull(`rmc`.`product_rm_mrp`, 'NA'), ':MRP:Y:T') AS `product_rm_mrp`,
    concat(ifnull(`rmc`.`product_rm_landed_price`, 'NA'), ':Landed Price:Y:T') AS `product_rm_landed_price`,
    concat(ifnull(`rmc`.`product_rm_avg_price`, 'NA'), ':Avg. Price:Y:T') AS `product_rm_avg_price`,
    concat(ifnull(`rmc`.`product_rm_std_profit_percent`, 'NA'), ': Std. Profit Percent:Y:T') AS `product_rm_std_profit_percent`,
    concat(ifnull(`rmc`.`product_rm_std_discount_percent`, 'NA'), ':Std. Discount Percent:Y:T') AS `product_rm_std_discount_percent`,
    concat(ifnull(`rmc`.`product_rm_moq`, 'NA'), ':MOQ:Y:T') AS `product_rm_moq`,
    concat(ifnull(`rmc`.`product_rm_mpq`, 'NA'), ':MPQ:Y:T') AS `product_rm_mpq`,
    concat(ifnull(`rmc`.`product_rm_mov`, 'NA'), ':MOV:Y:T') AS `product_rm_mov`,
    concat(ifnull(`rmc`.`product_rm_eoq`, 'NA'), ':EOQ:Y:T') AS `product_rm_eoq`,
    concat(ifnull(`rmc`.`product_rm_min_stock_level`, 'NA'), ':Minimum Stock:Y:T') AS `product_rm_min_stock_level`,
    concat(ifnull(`rmc`.`product_rm_max_stock_level`, 'NA'), ':Maximum Stock:Y:T') AS `product_rm_max_stock_level`,
    concat(ifnull(`rmc`.`product_rm_reorder_stock_level`, 'NA'), ':Reorder Stock:Y:T') AS `product_rm_reorder_stock_level`,
    concat(ifnull(`rmc`.`purchase_tolerance_percent`, 'NA'), ': Purchase Tolerance Percent:Y:T') AS `purchase_tolerance_percent`,
    concat(ifnull(`rmc`.`product_rm_price_type`, 'NA'), ':Price Type:Y:H:(Fixed,Moving Average)') AS `product_rm_price_type`,
    concat(ifnull(`rmc`.`product_rm_abc_analysis`, 'NA'), ':ABC Category:Y:H:(A,B,C)') AS `product_rm_abc_analysis`,
    concat(ifnull(`rm`.`product_rm_short_name`, 'NA'), ':Short Name:o:N:') AS `product_rm_short_name`,
    concat(ifnull(`rmc`.`qa_required`, 'NA'), ':QA Required:Y:H:(Yes,No)') AS `qa_required`,
    concat(ifnull(`rmc`.`test_certificate_required`, 'NA'), ':Test Certificate Required:Y:H:(Yes,No)') AS `test_certificate_required`,
    concat(ifnull(`rmc`.`profit_center_name`, 'NA'), ':Profit Center Name:Y:C:fmv_profit_center:F') AS `profit_center_name`,
    concat(ifnull(`rmc`.`cost_center_name`, 'NA'), ':Cost Center Name:Y:C:fmv_cost_center:F') AS `cost_center_name`,
    concat(ifnull(`rmc`.`routing_code`, 'NA'), ':Routing Code:o:N:') AS `routing_code`,
    concat(ifnull(`rm`.`product_rm_print_name`, 'NA'), ':Print Name:o:N:') AS `product_rm_print_name`,
    concat(ifnull(`rm`.`remark`, 'NA'), ':Remark:O:N:') AS `remark`,
    concat(case when `rm`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
    concat(case when `rm`.`is_delete` = 1 then 'Yes' else 'No' end, ':Delete Status:Y:H:(Yes, No)') AS `Deleted`,
    concat(ifnull(`rm`.`created_by`, 'NA'), ':Created By:o:N:') AS `created_by`,
    concat(ifnull(`rm`.`created_on`, 'NA'), ':Created On:o:N:') AS `created_on`,
    concat(ifnull(`rm`.`modified_by`, 'NA'), ':Modified By:o:N:') AS `modified_by`,
    concat(ifnull(`rm`.`modified_on`, 'NA'), ':Modified On:o:N:') AS `modified_on`,
    concat(ifnull(`rm`.`deleted_by`, 'NA'), ':Delete By:o:N:') AS `deleted_by`,
    concat(ifnull(`rm`.`deleted_on`, 'NA'), ':Deleted On:o:N:') AS `deleted_on`,
    concat(ifnull(`rm`.`company_id`, 'NA'), ':Company Id:N:N:') AS `company_id`,
    concat(ifnull(`rm`.`company_branch_id`, 'NA'), ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(ifnull(`rm`.`product_type_id`, 'NA'), ':Product Type Id:N:N:') AS `product_type_id`,
    concat(ifnull(`rm`.`product_category1_id`, 'NA'), ':Product Category Id 1:N:N:') AS `product_category1_id`,
    concat(ifnull(`rm`.`product_rm_id`, 'NA'), ':Raw Material Id:o:N:') AS `product_rm_id`,
    concat(ifnull(`rmc`.`profit_center_id`, ''), ':Profit Center Id:N:N:') AS `profit_center_id`,
    concat(ifnull(`rmc`.`cost_center_id`, ''), ':Cost Center Id:N:N:') AS `cost_center_id`
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




CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_fg_rpt_summary` AS
SELECT
    CONCAT(IFNULL(`v`.`product_fg_name`, ''), ':Product Name:Y:C:smv_product_fg:F') AS `product_fg_name`,
    CONCAT(IFNULL(`v`.`product_fg_technical_name`, ''), ':Technical Name:Y:C:smv_product_fg_technical:F') AS `product_fg_technical_name`,
    CONCAT(IFNULL(`v`.`product_fg_code`, ''), ':Product Code:O:N') AS `product_fg_code`,
    CONCAT(IFNULL(`v`.`product_fg_drawing_no`, ''), ':Drawing No:N:N') AS `product_fg_drawing_no`,
    CONCAT(IFNULL(`v`.`product_type_name`, ''), ':Product Type:Y:C:smv_product_type:F') AS `product_type_name`,
    CONCAT(IFNULL(`v`.`product_fg_oem_part_code`, ''), ':OEM Part Code:Y:C:smv_product_fg:F') AS `product_fg_oem_part_code`,
    CONCAT(IFNULL(`v`.`product_fg_our_part_code`, ''), ':Our Part Code:Y:C:smv_product_fg:F') AS `product_fg_our_part_code`,
    CONCAT(IFNULL(`v`.`product_fg_tech_spect`, ''), ':TEch. Spect.:Y:C:smv_product_fg:F') AS `product_fg_tech_spect`,
    CONCAT(IFNULL(`v`.`product_fg_hsn_sac_code`, ''), ':HSN_SAC Code :Y:C:cmv_hsn_sac:F') AS `product_fg_hsn_sac_code`,
    CONCAT(IFNULL(`v`.`product_fg_stock_unit_name`, ''), ':Unit:Y:C:smv_product_unit:F') AS `product_fg_stock_unit_name`,
    CONCAT(IFNULL(`v`.`product_category1_name`, ''), ':Category1:Y:C:smv_product_category1:F') AS `product_category1_name`,
    CONCAT(IFNULL(`v`.`product_category2_name`, ''), ':Category 2:Y:C:smv_product_category2:F') AS `product_category2_name`,
    CONCAT(IFNULL(`v`.`product_category3_name`, ''), ':Category 3:N:N') AS `product_category3_name`,
    CONCAT(IFNULL(`v`.`product_category4_name`, ''), ':Category 4:N:N') AS `product_category4_name`,
    CONCAT(IFNULL(`v`.`product_category5_name`, ''), ':Category 5:N:N') AS `product_category5_name`,
    CONCAT(IFNULL(`v`.`product_material_type_name`, ''), ':Material Type::N:N') AS `product_material_type_name`,
    CONCAT(IFNULL(`v`.`product_material_grade_name`, ''), ':Grade:N:N') AS `product_material_grade_name`,
    CONCAT(IFNULL(`v`.`product_material_shape_name`, ''), ':Shape:N:N') AS `product_material_shape_name`,
    CONCAT(IFNULL(`v`.`company_name`, ''), ':Company Name::N:N') AS `company_name`,
    CONCAT(IFNULL(`v`.`company_branch_name`, ''), ':Company Branch::N:N') AS `company_branch_name`,
    CONCAT(IFNULL(CASE WHEN `v`.`is_active` = 1 THEN 'Active' ELSE 'In Active' END, ''), ':Is Active:Y:H:(Active, In Active)') AS `Active`,
    CONCAT(IFNULL(CASE WHEN `v`.`is_delete` = 1 THEN 'Yes' ELSE 'No' END, ''), ':Is Deleted:Y:H:(Yes, No)') AS `Deleted`,
    CONCAT(IFNULL(`v`.`created_by`, ''), ':Created By:N:N:') AS `created_by`,
    CONCAT(IFNULL(`v`.`created_on`, ''), ':Modified On:N:N:') AS `created_on`,
    CONCAT(IFNULL(`v`.`modified_by`, ''), ':Modified By:N:N:') AS `modified_by`,
    CONCAT(IFNULL(`v`.`modified_on`, ''), ':Modified On:N:N:') AS `modified_on`,
    CONCAT(IFNULL(`v`.`deleted_by`, ''), ':Deleted By:N:N:') AS `deleted_by`,
    CONCAT(IFNULL(`v`.`deleted_on`, ''), ':Deleted On:N:N:') AS `deleted_on`,
    CONCAT(IFNULL(`v`.`product_fg_id`, ''), ':Material Id:O:N:') AS `product_fg_id`
FROM
    `smv_product_fg_summary` `v`
LIMIT 1;




CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_fg_rpt_details` AS
SELECT
    CONCAT(IFNULL(`v`.`product_fg_name`, ''), ':Product Name:Y:C:smv_product_fg:F') AS `product_fg_name`,
    CONCAT(IFNULL(`v`.`product_fg_code`, ''), ':Product Code:O:N:') AS `product_fg_code`,
    CONCAT(IFNULL(`v`.`product_fg_short_name`, ''), ':Short Name:O:N:') AS `product_fg_short_name`,
    CONCAT(IFNULL(`v`.`product_fg_tech_spect`, ''), ':Tech. Spect:O:N:') AS `product_fg_tech_spect`,
    CONCAT(IFNULL(`v`.`product_fg_oem_part_code`, ''), ':OEM Part Code:Y:T:') AS `product_fg_oem_part_code`,
    CONCAT(IFNULL(`v`.`product_fg_our_part_code`, ''), ':Our Part Code:Y:T:') AS `product_fg_our_part_code`,
    CONCAT(IFNULL(`v`.`product_fg_item_sr_no`, ''), ':Item Sr. No.:N:N:') AS `product_fg_item_sr_no`,
    CONCAT(IFNULL(`v`.`product_fg_drawing_no`, ''), ':Drawing No:N:N:') AS `product_fg_drawing_no`,
    CONCAT(IFNULL(`v`.`product_fg_model_no`, ''), ':Model No:N:N:') AS `product_fg_model_no`,
    CONCAT(IFNULL(`v`.`product_fg_bar_code`, ''), ':Bar Code:O:N:') AS `product_fg_bar_code`,
    CONCAT(IFNULL(`v`.`product_fg_qr_code`, ''), ':QR Code:O:N:') AS `product_fg_qr_code`,
    CONCAT(IFNULL(`v`.`product_consumption_mode`, ''), ':Consumption:Y:P:MaterialConsumptionMode') AS `product_consumption_mode`,
    CONCAT(IFNULL(`v`.`product_origin_type`, ''), ':Origin:Y:P:MaterialOrigin') AS `product_origin_type`,
    CONCAT(IFNULL(`v`.`product_origin_country`, ''), ':Origin Country:Y:C:smv_product_fg') AS `product_origin_country`,
    CONCAT(IFNULL(`v`.`product_type_name`, ''), ':Product Type:Y:C:smv_product_type') AS `product_type_name`,
    CONCAT(IFNULL(`v`.`product_fg_hsn_sac_code`, ''), ':HSN Code:Y:C:cmv_hsn_sac:F') AS `product_fg_hsn_sac_code`,
    CONCAT(IFNULL(`v`.`product_fg_hsn_sac_rate`, ''), ':HSN %:Y:T') AS `product_fg_hsn_sac_rate`,
    CONCAT(IFNULL(`v`.`product_fg_purchase_unit_name`, ''), ':Purchase Unit:Y:C:smv_product_unit:F') AS `product_fg_purchase_unit_name`,
    CONCAT(IFNULL(`v`.`product_fg_sales_unit_name`, ''), ':Sales Unit:Y:C:smv_product_unit:F') AS `product_fg_sales_unit_name`,
    CONCAT(IFNULL(`v`.`product_fg_packing_name`, ''), ':Packing:Y:C:smv_product_packing:F') AS `product_fg_packing_name`,
    CONCAT(IFNULL(`v`.`product_fg_technical_name`, ''), ':Tech. Name:Y:C:smv_product_fg_technical:F') AS `product_fg_technical_name`,
    CONCAT(IFNULL(`v`.`assembly_scrap_percent`, ''), ':Scrap %:O:N:') AS `assembly_scrap_percent`,
    CONCAT(IFNULL(`v`.`product_category1_name`, ''), ':Category 1:Y:C:smv_product_category1:F') AS `product_category1_name`,
    CONCAT(IFNULL(`v`.`product_category2_name`, ''), ':Category 2:Y:C:smv_product_category2:F') AS `product_category2_name`,
    CONCAT(IFNULL(`v`.`product_category3_name`, ''), ':Category 3:N:N:') AS `product_category3_name`,
    CONCAT(IFNULL(`v`.`product_category4_name`, ''), ':Category 4:N:N:') AS `product_category4_name`,
    CONCAT(IFNULL(`v`.`product_category5_name`, ''), ':Category 5:N:N:') AS `product_category5_name`,
    CONCAT(IFNULL(`v`.`product_material_type_name`, ''), ':Material Type:N:N:') AS `product_material_type_name`,
    CONCAT(IFNULL(`v`.`product_material_grade_name`, ''), ':Grade:N:N:') AS `product_material_grade_name`,
    CONCAT(IFNULL(`v`.`product_material_shape_name`, ''), ':Shape:N:N:') AS `product_material_shape_name`,
    CONCAT(IFNULL(`v`.`product_fg_gross_weight`, ''), ':Gross Weight:Y:T') AS `product_fg_gross_weight`,
    CONCAT(IFNULL(`v`.`product_fg_net_weight`, ''), ':Net Weight:Y:T') AS `product_fg_net_weight`,
    CONCAT(IFNULL(`v`.`product_fg_std_weight`, ''), ':Std. Weight:Y:T') AS `product_fg_std_weight`,
    CONCAT(IFNULL(`v`.`product_fg_volume`, ''), ':Volume:Y:T') AS `product_fg_volume`,
    CONCAT(IFNULL(`v`.`product_fg_mrp`, ''), ':MRP:Y:T') AS `product_fg_mrp`,
    CONCAT(IFNULL(`v`.`product_fg_landed_price`, ''), ':Landed Price:Y:T') AS `product_fg_landed_price`,
    CONCAT(IFNULL(`v`.`product_fg_avg_price`, ''), ':Avg. Price:Y:T') AS `product_fg_avg_price`,
    CONCAT(IFNULL(`v`.`product_fg_std_profit_percent`, ''), ':Std. Profit %:Y:T') AS `product_fg_std_profit_percent`,
    CONCAT(IFNULL(`v`.`product_fg_std_discount_percent`, ''), ':Std. Discount %:Y:T') AS `product_fg_std_discount_percent`,
    CONCAT(IFNULL(`v`.`product_fg_moq`, ''), ':MOQ:Y:T') AS `product_fg_moq`,
    CONCAT(IFNULL(`v`.`product_fg_mpq`, ''), ':MPQ:Y:T') AS `product_fg_mpq`,
    CONCAT(IFNULL(`v`.`product_fg_mov`, ''), ':MOV:Y:T') AS `product_fg_mov`,
    CONCAT(IFNULL(`v`.`product_fg_eoq`, ''), ':EOQ:Y:T') AS `product_fg_eoq`,
    CONCAT(IFNULL(`v`.`product_fg_min_stock_level`, ''), ':Minimum Level:Y:T') AS `product_fg_min_stock_level`,
    CONCAT(IFNULL(`v`.`product_fg_max_stock_level`, ''), ':Maximum Level:Y:T') AS `product_fg_max_stock_level`,
    CONCAT(IFNULL(`v`.`product_fg_reorder_stock_level`, ''), ':Reorder Level:Y:T') AS `product_fg_reorder_stock_level`,
    CONCAT(IFNULL(`v`.`product_fg_depriciation_percent`, ''), ':Depriciation %:Y:T') AS `product_fg_depriciation_percent`,
    CONCAT(IFNULL(`v`.`sales_tolerance_percent`, ''), ':Tolerance %:Y:T') AS `sales_tolerance_percent`,
    CONCAT(IFNULL(`v`.`product_fg_abc_analysis`, ''), ':ABC Analysis:Y:H:(A,B,C)') AS `product_fg_abc_analysis`,
    CONCAT(IFNULL(`v`.`qa_required`, ''), ':QA Required:Y:H:(Yes,No)') AS `qa_required`,
    CONCAT(IFNULL(`v`.`test_certificate_required`, ''), ':Test Certificate:Y:H:(Yes,No)') AS `test_certificate_required`,
    CONCAT(IFNULL(`v`.`remark`, ''), ':Remark:O:N:') AS `remark`,
    CONCAT(IFNULL(CASE WHEN `v`.`is_active` = 1 THEN 'Active' ELSE 'In Active' END, ''), ':Is Active:Y:H:(Active, In Active)') AS `Active`,
    CONCAT(IFNULL(CASE WHEN `v`.`is_delete` = 1 THEN 'Yes' ELSE 'No' END, ''), ':Is Deleted:Y:H:(Yes, No)') AS `Deleted`,
    CONCAT(IFNULL(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') AS `company_name`,
    CONCAT(IFNULL(`v`.`company_branch_name`, ''), ':Branch Name:Y:C:cmv_company_branch_summary:F') AS `company_branch_name`,
    CONCAT(IFNULL(`v`.`product_fg_print_name`, ''), ':Print Name:O:N:') AS `product_fg_print_name`,
    CONCAT(IFNULL(`v`.`product_fg_hsn_sac_is_exampted`, ''), ':HSN Exempted:O:N:') AS `product_fg_hsn_sac_is_exampted`,
    CONCAT(IFNULL(`v`.`product_fg_stock_unit_name`, ''), ':Stock Unit:Y:C:smv_product_unit:F') AS `product_fg_stock_unit_name`,
    CONCAT(IFNULL(`v`.`product_fg_name`, ''), ':Alt. Product:Y:C:smv_product_fg') AS `product_alternate_fg_name`,
    CONCAT(IFNULL(`v`.`product_fg_quantity_per_packing`, ''), ':Qty Per Packing:O:N:') AS `product_fg_quantity_per_packing`,
    CONCAT(IFNULL(`v`.`product_fg_weight_per_packing`, ''), ':weight Per Packing:O:N:') AS `product_fg_weight_per_packing`,
    CONCAT(IFNULL(`v`.`product_material_colour`, ''), ':Colour:N:N:') AS `product_material_colour`,
    CONCAT(IFNULL(`v`.`product_fg_id`, ''), ':Material Id:O:N:') AS `product_fg_id`
FROM
    `smv_product_fg_details` `v`
LIMIT 1;

