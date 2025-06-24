
ALTER TABLE st_indent_material_issue_return_details ADD issue_return_date date DEFAULT NULL NULL AFTER financial_year;

create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_return_details` as
select
    `st`.`issue_return_details_transaction_id` as `issue_return_details_transaction_id`,
    `st`.`issue_return_master_transaction_id` as `issue_return_master_transaction_id`,
    `st`.`company_id` as `company_id`,
    `st`.`company_branch_id` as `company_branch_id`,
    `st`.`financial_year` as `financial_year`,
    `st`.`issue_return_date` as `issue_return_date`,
    `stm`.`issue_return_no` as `issue_return_no`,
    `st`.`product_material_unit_id` as `product_material_unit_id`,
    `p`.`product_rm_name` as `product_material_name`,
    `p`.`product_rm_code` as `product_material_code`,
    `st`.`product_material_id` as `product_material_id`,
    `st`.`issue_batch_no` as `issue_batch_no`,
    `st`.`material_batch_rate` as `material_batch_rate`,
    `st`.`product_material_issue_return_quantity` as `product_material_issue_return_quantity`,
    `st`.`product_material_issue_return_weight` as `product_material_issue_return_weight`,
    `st`.`product_material_issue_return_boxes` as `product_material_issue_return_boxes`,
    `st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
    `st`.`product_material_receipt_boxes` as `product_material_receipt_boxes`,
    `st`.`issue_return_item_status` as `issue_return_item_status`,
    case
        `st`.`issue_return_item_status` when 'R' then 'Returned'
        else 'Pending'
    end as `issue_return_item_status_desc`,
    `st`.`godown_id` as `godown_id`,
    `st`.`godown_section_id` as `godown_section_id`,
    `st`.`godown_section_beans_id` as `godown_section_beans_id`,
    `st`.`issue_return_remark` as `issue_return_remark`,
    `g`.`godown_name` as `godown_name`,
    `gs`.`godown_section_name` as `godown_section_name`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `std`.`product_material_indent_quantity` as `product_material_indent_quantity`,
    `std`.`product_material_indent_weight` as `product_material_indent_weight`,
    `std`.`product_material_approved_quantity` as `product_material_approved_quantity`,
    `std`.`product_material_approved_weight` as `product_material_approved_weight`,
    `std`.`product_material_issue_quantity` as `product_material_issue_quantity`,
    `std`.`product_material_issue_weight` as `product_material_issue_weight`,
    `std`.`product_material_issue_boxes` as `product_material_issue_boxes`,
    `std`.`closing_balance_quantity` as `closing_balance_quantity`,
    `std`.`closing_balance_weight` as `closing_balance_weight`,
    `st`.`product_category1_id` as `product_category1_id`,
    `st`.`product_category2_id` as `product_category2_id`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `st`.`is_active` as `is_active`,
    `st`.`is_delete` as `is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`deleted_on` as `deleted_on`,
    `st`.`cone_per_wt` as `cone_per_wt`,
    `st`.`goods_receipt_no` as `goods_receipt_no`,
    `st`.`supplier_id` as `supplier_id`,
    `st`.`creel_no` as `creel_no`,
    `stm`.`indent_issue_return_type_id` as `indent_issue_return_type_id`
from
    ((((((`st_indent_material_issue_return_details` `st`
left join `st_indent_material_issue_return_master` `stm` on
    (`stm`.`issue_return_master_transaction_id` = `st`.`issue_return_master_transaction_id`))
left join `stv_indent_material_issue_details` `std` on
    (`std`.`issue_no` = `stm`.`issue_no`
        and `std`.`product_material_id` = `st`.`product_material_id`
        and `st`.`issue_batch_no` = `std`.`issue_batch_no`
        and (`st`.`creel_no` is null
            or `st`.`creel_no` = `std`.`creel_no`)
        and `st`.`godown_id` = `std`.`godown_id`
        and `st`.`godown_section_id` = `std`.`godown_section_id`
        and `st`.`godown_section_beans_id` = `std`.`godown_section_beans_id`
        and `std`.`is_delete` = 0))
left join `cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `st`.`godown_section_beans_id`
        and `gsb`.`is_delete` = 0))
left join `cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `st`.`godown_section_id`
        and `gs`.`is_delete` = 0))
left join `cm_godown` `g` on
    (`g`.`godown_id` = `st`.`godown_id`
        and `g`.`is_delete` = 0))
left join `sm_product_rm` `p` on
    (`p`.`product_rm_id` = `st`.`product_material_id`
        and `p`.`is_delete` = 0))
where
    `st`.`is_delete` = 0;

