


ALTER TABLE st_indent_material_issue_return_details ADD issue_return_no varchar(100) NULL COMMENT 'transaction no';
ALTER TABLE st_indent_material_issue_return_details CHANGE issue_return_no issue_return_no varchar(100) NULL COMMENT 'transaction no' AFTER issue_return_master_transaction_id;
ALTER TABLE st_indent_material_issue_return_details ADD material_type varchar(3) DEFAULT 'UR' NULL COMMENT 'material types used or unused materials';
ALTER TABLE st_indent_material_issue_return_details CHANGE material_type material_type varchar(3) DEFAULT 'UR' NULL COMMENT 'material types used or unused materials' AFTER issue_return_no;




-- stv_indent_material_issue_return_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_return_details` AS
select
    `st`.`issue_return_details_transaction_id` AS `issue_return_details_transaction_id`,
    `st`.`issue_return_master_transaction_id` AS `issue_return_master_transaction_id`,
    `st`.`company_id` AS `company_id`,
    `st`.`company_branch_id` AS `company_branch_id`,
    `st`.`financial_year` AS `financial_year`,
    `st`.`material_type` AS `material_type`,
    `st`.`issue_return_no` AS `issue_return_no`,
    `st`.`issue_return_date` AS `issue_return_date`,
    `st`.`product_material_unit_id` AS `product_material_unit_id`,
    `p`.`product_rm_name` AS `product_material_name`,
    `p`.`product_rm_code` AS `product_material_code`,
    `st`.`product_material_id` AS `product_material_id`,
    `st`.`issue_batch_no` AS `issue_batch_no`,
    `st`.`material_batch_rate` AS `material_batch_rate`,
    `st`.`product_material_issue_return_quantity` AS `product_material_issue_return_quantity`,
    `st`.`product_material_issue_return_weight` AS `product_material_issue_return_weight`,
    `st`.`product_material_issue_return_boxes` AS `product_material_issue_return_boxes`,
    `st`.`product_material_receipt_quantity` AS `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` AS `product_material_receipt_weight`,
    `st`.`product_material_receipt_boxes` AS `product_material_receipt_boxes`,
    `st`.`issue_return_item_status` AS `issue_return_item_status`,
    case
        `st`.`issue_return_item_status` when 'R' then 'Returned'
        else 'Pending'
    end AS `issue_return_item_status_desc`,
    `st`.`godown_id` AS `godown_id`,
    `st`.`godown_section_id` AS `godown_section_id`,
    `st`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `st`.`issue_return_remark` AS `issue_return_remark`,
    `g`.`godown_name` AS `godown_name`,
    `gs`.`godown_section_name` AS `godown_section_name`,
    `gsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `std`.`product_material_indent_quantity` AS `product_material_indent_quantity`,
    `std`.`product_material_indent_weight` AS `product_material_indent_weight`,
    `std`.`product_material_approved_quantity` AS `product_material_approved_quantity`,
    `std`.`product_material_approved_weight` AS `product_material_approved_weight`,
    `std`.`product_material_issue_quantity` AS `product_material_issue_quantity`,
    `std`.`product_material_issue_weight` AS `product_material_issue_weight`,
    `std`.`product_material_issue_boxes` AS `product_material_issue_boxes`,
    `std`.`closing_balance_quantity` AS `closing_balance_quantity`,
    `std`.`closing_balance_weight` AS `closing_balance_weight`,
    `st`.`product_category1_id` AS `product_category1_id`,
    `st`.`product_category2_id` AS `product_category2_id`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `st`.`is_active` AS `is_active`,
    `st`.`is_delete` AS `is_delete`,
    `st`.`created_by` AS `created_by`,
    `st`.`created_on` AS `created_on`,
    `st`.`modified_by` AS `modified_by`,
    `st`.`modified_on` AS `modified_on`,
    `st`.`deleted_by` AS `deleted_by`,
    `st`.`deleted_on` AS `deleted_on`,
    `st`.`cone_per_wt` AS `cone_per_wt`,
    `st`.`goods_receipt_no` AS `goods_receipt_no`,
    `st`.`supplier_id` AS `supplier_id`,
    `st`.`creel_no` AS `creel_no`,
    `stm`.`indent_issue_return_type_id` AS `indent_issue_return_type_id`
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