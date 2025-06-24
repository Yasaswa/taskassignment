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
    `grm`.`supplier_id` AS `supplier_id`,
    `sup`.`supp_branch_name` AS `supplier_name`,
    `grm`.`sales_type` AS `sales_type`,
    `pdt`.`product_type_name` AS `product_type_name`,
    `ptv`.`remark` AS `remark`,
    `pt`.`goods_return_rate` AS `goods_return_rate`,
    `pt`.`issue_batch_no` AS `issue_batch_no`,
    `pt`.`cone_per_wt` AS `cone_per_wt`,
    `pt`.`financial_year` AS `financial_year`,
    `rm`.`product_rm_name` AS `product_material_name`,
    `ptv`.`batch_no` AS `batch_no`,
    `ptv`.`product_material_grn_accepted_quantity` AS `product_material_grn_accepted_quantity`,
    `ptv`.`product_material_grn_accepted_weight` AS `product_material_grn_accepted_weight`,
    `ptv`.`no_of_boxes` AS `no_of_boxes`,
    `ptv`.`total_box_weight` AS `total_box_weight`,
    `ptv`.`total_quantity_in_box` AS `total_quantity_in_box`,
    `ptv`.`weight_per_box_item` AS `weight_per_box_item`,
    `ptv`.`material_rate` AS `material_rate`,
    `smv`.`closing_balance_quantity` AS `closing_balance_quantity`,
    `smv`.`closing_no_of_boxes` AS `closing_no_of_boxes`,
    `smv`.`closing_balance_weight` AS `closing_balance_weight`,
    `v`.`company_legal_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `grm`.`company_id` AS `company_id`,
    `grm`.`company_branch_id` AS `company_branch_id`,
    `grm`.`goods_return_status` AS `goods_return_status`,
    `pt`.`godown_id` AS `godown_id`,
    `pt`.`godown_section_id` AS `godown_section_id`,
    `pt`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `gsb`.`godown_name` AS `godown_name`,
    `gsb`.`godown_section_name` AS `godown_section_name`,
    `gsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
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
    ((((((((`pt_goods_return_details` `pt`
left join `pt_goods_return_master` `grm` on
    (`grm`.`goods_return_master_id` = `pt`.`goods_return_master_id`
        and `grm`.`company_id` = `pt`.`company_id`))
left join `sm_product_type` `pdt` on
    (`pdt`.`product_type_id` = `grm`.`product_type_id`
        and `pdt`.`is_delete` = 0))
left join `pt_goods_receipt_details` `ptv` on
    (`pt`.`goods_receipt_no` = `ptv`.`goods_receipt_no`
        and `pt`.`product_rm_id` = `ptv`.`product_material_id`
        and `ptv`.`is_delete` = 0))
left join `sm_product_rm_stock_details` `smv` on
    (`pt`.`goods_receipt_no` = `smv`.`goods_receipt_no`
        and `pt`.`product_rm_id` = `smv`.`product_rm_id`
        and `smv`.`day_closed` = 0
        and `pt`.`is_delete` = 0))
left join `sm_product_rm` `rm` on
    (`rm`.`product_rm_id` = `pt`.`product_rm_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `pt`.`company_id`))
left join `cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `grm`.`supplier_id`
        and `pt`.`is_delete` = 0))
left join `cmv_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `pt`.`godown_section_beans_id`))
where
    `pt`.`is_delete` = 0;
