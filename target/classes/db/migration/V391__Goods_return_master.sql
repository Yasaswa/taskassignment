ALTER TABLE pt_goods_return_master DROP COLUMN supplier_id;

ALTER TABLE xt_weaving_production_warping_bottom_details
MODIFY COLUMN material_status VARCHAR(25) NULL DEFAULT 'Pending';
UPDATE xt_weaving_production_warping_bottom_details
SET material_status = 'Pending'
WHERE material_status IS NULL;

-- pashupati_erp_qa.ptv_goods_return_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `ptv_goods_return_details` AS
select
    `pt`.`goods_receipt_no` AS `goods_receipt_no`,
    `grm`.`goods_return_date` AS `goods_return_date`,
    `grm`.`goods_version` AS `goods_version`,
    `grm`.`goods_return_no` AS `goods_return_no`,
    `grm`.`goods_return_master_id` AS `goods_return_master_id`,
    `pt`.`product_rm_id` AS `product_rm_id`,
    `pt`.`goods_return_quantity` AS `goods_return_quantity`,
    `pt`.`goods_return_weight` AS `goods_return_weight`,
    `pt`.`goods_return_boxes` AS `goods_return_boxes`,
    `pt`.`goods_return_details_id` AS `goods_return_details_id`,
    `pt`.`goods_return_remark` AS `goods_return_remark`,
    `grm`.`product_type_id` AS `product_type_id`,
    `grm`.`sales_type` AS `sales_type`,
    `pdt`.`product_type_name` AS `product_type_name`,
    `pt`.`goods_return_rate` AS `goods_return_rate`,
    `pt`.`issue_batch_no` AS `issue_batch_no`,
    `pt`.`cone_per_wt` AS `cone_per_wt`,
    `pt`.`financial_year` AS `financial_year`,
    `rm`.`product_rm_name` AS `product_material_name`,
    `rm`.`product_rm_code` AS `product_material_code`,
    `smv`.`closing_balance_quantity` AS `closing_balance_quantity`,
    `smv`.`closing_no_of_boxes` AS `closing_no_of_boxes`,
    `smv`.`closing_balance_weight` AS `closing_balance_weight`,
    `grm`.`company_id` AS `company_id`,
    `grm`.`company_branch_id` AS `company_branch_id`,
    `grm`.`goods_return_status` AS `goods_return_status`,
    `pt`.`godown_id` AS `godown_id`,
    `pt`.`godown_section_id` AS `godown_section_id`,
    `pt`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `pt`.`product_material_unit_id` AS `product_material_unit_id`,
    `pt`.`product_material_packing_id` AS `product_material_packing_id`,
    `pt`.`product_hsn_sac_code_id` AS `product_hsn_sac_code_id`,
    `pt`.`product_hsn_sac_rate` AS `product_hsn_sac_rate`,
    `pt`.`material_basic_amount` AS `material_basic_amount`,
    `pt`.`material_discount_percent` AS `material_discount_percent`,
    `pt`.`material_discount_amount` AS `material_discount_amount`,
    `pt`.`material_taxable_amount` AS `material_taxable_amount`,
    `pt`.`material_cgst_percent` AS `material_cgst_percent`,
    `pt`.`material_cgst_total` AS `material_cgst_total`,
    `pt`.`material_sgst_percent` AS `material_sgst_percent`,
    `pt`.`material_sgst_total` AS `material_sgst_total`,
    `pt`.`material_igst_percent` AS `material_igst_percent`,
    `pt`.`material_igst_total` AS `material_igst_total`,
    `pt`.`material_freight_amount` AS `material_freight_amount`,
    `pt`.`material_total_amount` AS `material_total_amount`,
    `gs`.`godown_name` AS `godown_name`,
    `g`.`godown_section_name` AS `godown_section_name`,
    `gsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `vb`.`company_legal_name` AS `company_name`,
    `vb`.`company_branch_name` AS `company_branch_name`,
    `vb`.`company_address1` AS `company_address1`,
    `vb`.`company_address2` AS `company_address2`,
    `vb`.`company_phone_no` AS `company_phone_no`,
    `vb`.`company_cell_no` AS `company_cell_no`,
    `vb`.`company_EmailId` AS `company_EmailId`,
    `vb`.`company_website` AS `company_website`,
    `vb`.`company_gst_no` AS `company_gst_no`,
    `vb`.`company_pan_no` AS `company_pan_no`,
    `vb`.`company_pincode` AS `company_pincode`,
    `cbnk`.`company_branch_bank_name` AS `company_branch_bank_name`,
    `cbnk`.`company_branch_bank_account_no` AS `company_branch_bank_account_no`,
    `cbnk`.`company_branch_bank_ifsc_code` AS `company_branch_bank_ifsc_code`,
    `hsn`.`hsn_sac_code` AS `product_hsn_sac_code`,
    `un`.`product_unit_name` AS `product_unit_name`,
    case
        when `grm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `grm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `grm`.`is_active` AS `is_active`,
    `grm`.`is_delete` AS `is_delete`,
    `grm`.`created_by` AS `created_by`,
    `grm`.`created_on` AS `created_on`,
    `grm`.`modified_by` AS `modified_by`,
    `grm`.`modified_on` AS `modified_on`,
    `grm`.`deleted_by` AS `deleted_by`,
    `grm`.`deleted_on` AS `deleted_on`
from
    (((((((((((`pt_goods_return_details` `pt`
left join `pt_goods_return_master` `grm` on
    (`grm`.`goods_return_master_id` = `pt`.`goods_return_master_id`
        and `grm`.`company_id` = `pt`.`company_id`))
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `grm`.`product_type_id`
        and `pdt`.`is_delete` = 0))
left join `sm_product_rm_stock_details` `smv` on
    (`pt`.`goods_receipt_no` = `smv`.`goods_receipt_no`
        and `pt`.`product_rm_id` = `smv`.`product_rm_id`
        and `smv`.`day_closed` = 0
        and `pt`.`is_delete` = 0))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `pt`.`product_rm_id`))
left join `cmv_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `pt`.`godown_section_beans_id`))
left join `cm_godown` `gs` on
    (`gs`.`godown_id` = `pt`.`godown_id`))
left join `cm_godown_section` `g` on
    (`g`.`godown_section_id` = `pt`.`godown_section_id`))
left join `cm_company_banks` `cbnk` on
    (`cbnk`.`company_id` = `pt`.`company_id`))
left join `cmv_company_summary` `vb` on
    (`vb`.`company_branch_id` = `pt`.`company_branch_id`
        and `vb`.`company_id` = `pt`.`company_id`))
left join `cm_hsn_sac` `hsn` on
    (`hsn`.`hsn_sac_id` = `pt`.`product_hsn_sac_code_id`
        and `hsn`.`is_delete` = 0))
left join `sm_product_unit` `un` on
    (`un`.`product_unit_id` = `pt`.`product_material_unit_id`
        and `un`.`is_delete` = 0))
where
    `pt`.`is_delete` = 0;