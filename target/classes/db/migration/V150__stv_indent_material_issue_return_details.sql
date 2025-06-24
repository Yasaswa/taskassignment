-- erp_development.stv_indent_material_issue_return_details source

create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_return_details` as
select
    `st`.`issue_return_details_transaction_id` as `issue_return_details_transaction_id`,
    `st`.`issue_return_master_transaction_id` as `issue_return_master_transaction_id`,
    `st`.`company_id` as `company_id`,
    `st`.`company_branch_id` as `company_branch_id`,
    `st`.`financial_year` as `financial_year`,
    `stm`.`issue_return_no` as `issue_return_no`,
    `p`.`product_rm_name` as `product_material_name`,
    `st`.`product_material_id` as `product_material_id`,
    `st`.`product_material_issue_return_quantity` as `product_material_issue_return_quantity`,
    `st`.`product_material_issue_return_weight` as `product_material_issue_return_weight`,
    `st`.`product_material_issue_return_boxes` as `product_material_issue_return_boxes`,
    `st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
    `st`.`product_material_receipt_boxes` as `product_material_receipt_boxes`,
    `st`.`issue_return_item_status` as `issue_return_item_status`,
    `st`.`godown_id` as `godown_id`,
    `st`.`godown_section_id` as `godown_section_id`,
    `st`.`godown_section_beans_id` as `godown_section_beans_id`,
    `st`.`issue_return_remark` as `issue_return_remark`,
    `gsb`.`godown_name` as `godown_name`,
    `gsb`.`godown_section_name` as `godown_section_name`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `std`.`product_material_indent_quantity` as `product_material_indent_quantity`,
    `std`.`product_material_indent_weight` as `product_material_indent_weight`,
    `std`.`product_material_approved_quantity` as `product_material_approved_quantity`,
    `std`.`product_material_approved_weight` as `product_material_approved_weight`,
    `std`.`product_material_issue_quantity` as `product_material_issue_quantity`,
    `std`.`product_material_issue_weight` as `product_material_issue_weight`,
    `std`.`product_material_issue_boxes` as `product_material_issue_boxes`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `st`.`is_active`,
    `st`.`is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`deleted_on` as `deleted_on`
from
    ((((`st_indent_material_issue_return_details` `st`
left join `st_indent_material_issue_return_master` `stm` on
    (`stm`.`issue_return_master_transaction_id` = `st`.`issue_return_master_transaction_id`))
left join `st_indent_material_issue_details` `std` on
    (`std`.`product_material_id` = `st`.`product_material_id` and
    `std`.`issue_no`=`stm`.`issue_no`))
left join `cmv_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `st`.`godown_section_beans_id`))
left join `sm_product_rm` `p` on
    (`p`.`product_rm_id` = `st`.`product_material_id`))
where
    `st`.`is_delete` = 0;
