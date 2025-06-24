update sm_product_rm_stock_details set supplier_return_quantity = 0, supplier_return_weight = 0, supplier_return_boxes = 0;

update sm_product_rm_stock_summary  set supplier_return_quantity = 0, supplier_return_weight = 0, supplier_return_boxes = 0;


-- erp_development.ptv_goods_return_details source

create or replace
algorithm = UNDEFINED view `ptv_goods_return_details` as
select
    `pt`.`goods_receipt_no` as `goods_receipt_no`,
    `grm`.`goods_return_date` as `goods_return_date`,
    `grm`.`goods_version` as `goods_version`,
    `grm`.`goods_return_no` as `goods_return_no`,
    `grm`.`goods_return_master_id` as `goods_return_master_id`,
    `pt`.`product_rm_id` as `product_rm_id`,
    `pt`.`goods_return_quantity` as `goods_return_quantity`,
    `pt`.`goods_return_weight` as `goods_return_weight`,
    `pt`.`goods_return_boxes` as `goods_return_boxes`,
    `pt`.`goods_return_details_id` as `goods_return_details_id`,
    `pt`.`goods_return_remark` as `goods_return_remark`,
    `grm`.`product_type_id` as `product_type_id`,
    `grm`.`supplier_id` as `supplier_id`,
    `sup`.`supp_branch_name` as `supplier_name`,
    `grm`.`sales_type` as `sales_type`,
    `pdt`.`product_type_name` as `product_type_name`,
    `ptv`.`remark` as `remark`,
    `pt`.`goods_return_rate` as `goods_return_rate`,
    `pt`.`issue_batch_no` as `issue_batch_no`,
    `pt`.`cone_per_wt` as `cone_per_wt`,
    `pt`.`financial_year` as `financial_year`,
    `rm`.`product_rm_name` as `product_material_name`,
    `ptv`.`batch_no` as `batch_no`,
    `ptv`.`product_material_grn_accepted_quantity` as `product_material_grn_accepted_quantity`,
    `ptv`.`product_material_grn_accepted_weight` as `product_material_grn_accepted_weight`,
    `ptv`.`no_of_boxes` as `no_of_boxes`,
    `ptv`.`total_box_weight` as `total_box_weight`,
    `ptv`.`total_quantity_in_box` as `total_quantity_in_box`,
    `ptv`.`weight_per_box_item` as `weight_per_box_item`,
    `ptv`.`material_rate` as `material_rate`,
    `smv`.`closing_balance_quantity` as `closing_balance_quantity`,
    `smv`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `smv`.`closing_balance_weight` as `closing_balance_weight`,
    `grm`.`company_id` as `company_id`,
    `grm`.`company_branch_id` as `company_branch_id`,
    `grm`.`goods_return_status` as `goods_return_status`,
    `pt`.`godown_id` as `godown_id`,
    `pt`.`godown_section_id` as `godown_section_id`,
    `pt`.`godown_section_beans_id` as `godown_section_beans_id`,
    `gs`.`godown_name` as `godown_name`,
    `g`.`godown_section_name` as `godown_section_name`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    case
        when `grm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `grm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `grm`.`is_active` as `is_active`,
    `grm`.`is_delete` as `is_delete`,
    `grm`.`created_by` as `created_by`,
    `grm`.`created_on` as `created_on`,
    `grm`.`modified_by` as `modified_by`,
    `grm`.`modified_on` as `modified_on`,
    `grm`.`deleted_by` as `deleted_by`,
    `grm`.`deleted_on` as `deleted_on`
from
    (((((((((`pt_goods_return_details` `pt`
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
left join `cm_supplier_branch` `sup` on
    (`sup`.`supp_branch_id` = `grm`.`supplier_id`
        and `pt`.`is_delete` = 0))
left join `cmv_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `pt`.`godown_section_beans_id`))
 left join `cm_godown` `gs` on
    (`gs`.`godown_id` = `pt`.`godown_id`))
 left join `cm_godown_section` `g` on
    (`g`.`godown_section_id` = `pt`.`godown_section_id`))
where
    `pt`.`is_delete` = 0;
