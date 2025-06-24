
CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_return_master` as
select
    `stm`.`issue_return_master_transaction_id` AS `issue_return_master_transaction_id`,
    `stm`.`company_id` AS `company_id`,
    `stm`.`company_branch_id` AS `company_branch_id`,
    `stm`.`financial_year` AS `financial_year`,
    `stm`.`issue_return_no` AS `issue_return_no`,
    `stm`.`issue_return_date` AS `issue_return_date`,
    `stm`.`indent_issue_return_type_id` AS `indent_issue_return_type_id`,
    `stm`.`indent_issue_return_type` AS `indent_issue_return_type`,
    `dpt`.`department_name` AS `department_name`,
    `sdpt`.`department_name` AS `sub_department_name`,
    `e`.`employee_name` AS `received_by_name`,
    `e1`.`employee_name` AS `return_by_name`,
    `stm`.`department_id` AS `department_id`,
    `stm`.`sub_department_id` AS `sub_department_id`,
    `stm`.`issue_no` AS `issue_no`,
    `std`.`issue_date` AS `issue_date`,
    `stm`.`return_by_id` AS `return_by_id`,
    `stm`.`issue_return_status` AS `issue_return_status`,
    `c`.`company_legal_name` AS `company_name`,
    `cb`.`company_branch_name` AS `company_branch_name`,
    case
        `stm`.`issue_return_status` when 'R' then 'Received'
        else 'Pending'
    end AS `issue_return_status_desc`,
    `stm`.`received_by_id` AS `received_by_id`,
    `stm`.`received_date` AS `received_date`,
    `stm`.`remark` AS `remark`,
    case
        when `stm`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `stm`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `stm`.`is_active` AS `is_active`,
    `stm`.`is_delete` AS `is_delete`,
    `stm`.`created_by` AS `created_by`,
    `stm`.`created_on` AS `created_on`,
    `stm`.`modified_by` AS `modified_by`,
    `stm`.`modified_on` AS `modified_on`,
    `stm`.`deleted_by` AS `deleted_by`,
    `stm`.`deleted_on` AS `deleted_on`,
    `std`.`set_no` AS `set_no`
from
    (((((((`st_indent_material_issue_return_master` `stm`
left join `st_indent_material_issue_master` `std` on
    (`std`.`issue_no` = `stm`.`issue_no`
        and `std`.`is_delete` = 0))
left join `cmv_department` `dpt` on
    (`dpt`.`department_id` = `stm`.`department_id`))
left join `cmv_department` `sdpt` on
    (`sdpt`.`department_id` = `stm`.`sub_department_id`))
left join `cm_employee` `e` on
    (`e`.`is_delete` = 0
        and `e`.`employee_id` = `stm`.`received_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`is_delete` = 0
        and `e1`.`employee_id` = `stm`.`return_by_id`))
left join `cm_company` `c` on
    (`c`.`company_id` = `stm`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `stm`.`company_branch_id`
        and `cb`.`is_delete` = 0))
where
    `stm`.`is_delete` = 0;

    -- stv_indent_material_issue_return_details source
    
    CREATE OR REPLACE
    ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_return_details` AS
    select
        `st`.`issue_return_details_transaction_id` AS `issue_return_details_transaction_id`,
        `st`.`issue_return_master_transaction_id` AS `issue_return_master_transaction_id`,
        `st`.`company_id` AS `company_id`,
        `st`.`company_branch_id` AS `company_branch_id`,
        `st`.`financial_year` AS `financial_year`,
        `stm`.`issue_return_no` AS `issue_return_no`,
        `p`.`product_rm_name` AS `product_material_name`,
        `st`.`product_material_id` AS `product_material_id`,
        `st`.`issue_batch_no` AS `issue_batch_no`,
        `st`.`product_material_issue_return_quantity` AS `product_material_issue_return_quantity`,
        `st`.`product_material_issue_return_weight` AS `product_material_issue_return_weight`,
        `st`.`product_material_issue_return_boxes` AS `product_material_issue_return_boxes`,
        `st`.`product_material_receipt_quantity` AS `product_material_receipt_quantity`,
        `st`.`product_material_receipt_weight` AS `product_material_receipt_weight`,
        `st`.`product_material_receipt_boxes` AS `product_material_receipt_boxes`,
        `st`.`issue_return_item_status` AS `issue_return_item_status`,
        `st`.`godown_id` AS `godown_id`,
        `st`.`godown_section_id` AS `godown_section_id`,
        `st`.`godown_section_beans_id` AS `godown_section_beans_id`,
        `st`.`issue_return_remark` AS `issue_return_remark`,
        `gsb`.`godown_name` AS `godown_name`,
        `gsb`.`godown_section_name` AS `godown_section_name`,
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
        `st`.`creel_no` AS `creel_no`
    from
        ((((`st_indent_material_issue_return_details` `st`
    left join `st_indent_material_issue_return_master` `stm` on
        (`stm`.`issue_return_master_transaction_id` = `st`.`issue_return_master_transaction_id`))
    left join `stv_indent_material_issue_details` `std` on
        (`std`.`issue_no` = `stm`.`issue_no`
            and `std`.`product_material_id` = `st`.`product_material_id`
            and `st`.`issue_batch_no` = `std`.`issue_batch_no`
            and `st`.`creel_no` = `std`.`creel_no`
            and `st`.`godown_id` = `std`.`godown_id`
            and `st`.`godown_section_id` = `std`.`godown_section_id`
            and `st`.`godown_section_beans_id` = `std`.`godown_section_beans_id`
            and `std`.`is_delete` = 0))
    left join `cmv_godown_section_beans` `gsb` on
        (`gsb`.`godown_section_beans_id` = `st`.`godown_section_beans_id`
            and `gsb`.`is_delete` = 0))
    left join `sm_product_rm` `p` on
        (`p`.`product_rm_id` = `st`.`product_material_id`
            and `p`.`is_delete` = 0))
    
    where
        `st`.`is_delete` = 0;