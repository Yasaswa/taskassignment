
   -- stv_indent_master_summary source

create or replace
algorithm = UNDEFINED view `stv_indent_master_summary` as
select
    `st`.`indent_no` as `indent_no`,
    `st`.`indent_date` as `indent_date`,
    `st`.`indent_version` as `indent_version`,
    case
        `st`.`indent_priority` when 'H' then 'High-Priority'
        else 'Low-Priority'
    end as `indent_priority_desc`,
    case
        `st`.`indent_source` when 'S' then 'SO Based'
        when 'O' then 'Direct'
        else 'Internal'
    end as `indent_source_desc`,
    `vp`.`department_name` as `department_name`,
    `sd`.`department_name` as `sub_department_name`,
    `e`.`employee_name` as `indented_by_name`,
    `e1`.`employee_name` as `approved_by_name`,
    `e2`.`employee_name` as `accepted_by_name`,
    `st`.`approved_date` as `approved_date`,
    case
        `st`.`indent_status`
        when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Cenceled'
        when 'Z' then 'PreClosed'
        else 'Pending'
    end as `indent_status_desc`,
    case
        `st`.`po_status` when 'POA' then 'Purchse Order Approved'
        when 'O' then 'Purchse Order Genreated'
        when 'IPO' then 'Partial PO Genreated'
        else 'Pending'
    end as `po_status_desc`,
    case
        `st`.`grn_status` when 'G' then 'Goods Receipts'
        when 'IG' then 'Partial Goods Receipts'
        else 'Pending'
    end as `grn_status_desc`,
    case
        `st`.`issue_status` when 'I' then 'Partial Issue'
        else 'Pending'
    end as `issue_status_desc`,
    `c`.`customer_name` as `customer_name`,
    `st`.`customer_order_no` as `customer_order_no`,
    `st`.`customer_order_Date` as `customer_order_date`,
    `st`.`expected_schedule_date` as `expected_schedule_date`,
    `fc`.`cost_center_name` as `cost_center_name`,
    `e`.`email_id1` as `indented_by_email`,
    `e1`.`email_id1` as `approved_by_email`,
    case
        `st`.`indent_transaction_type` when 'A' then 'BOM Based'
        else 'Manual'
    end as `indent_transaction_type_desc`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `st`.`remark` as `remark`,
    `st`.`indent_no` as `field_name`,
    `st`.`indent_version` as `field_id`,
    `st`.`is_active` as `is_active`,
    `st`.`is_delete` as `is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`deleted_on` as `deleted_on`,
    `v`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `st`.`financial_year` as `financial_year`,
    `st`.`indent_master_id` as `indent_master_id`,
    `st`.`indent_type_id` as `indent_type_id`,
    `st`.`customer_id` as `customer_id`,
    `st`.`department_id` as `department_id`,
    `st`.`sub_department_id` as `sub_department_id`,
    `st`.`indented_by_id` as `indented_by_id`,
    `st`.`approved_by_id` as `approved_by_id`,
    `st`.`accepted_by_id` as `accepted_by_id`,
    `st`.`cost_center_id` as `cost_center_id`,
    `st`.`indent_status` as `indent_status`,
    `st`.`po_status` as `po_status`,
    `st`.`grn_status` as `grn_status`,
    `st`.`issue_status` as `issue_status`,
    `st`.`company_id` as `company_id`,
    `st`.`company_branch_id` as `company_branch_id`,
    `st`.`indent_transaction_type` as `indent_transaction_type`,
    `st`.`Indent_type` as `indent_type`,
    `p`.`product_type_short_name` as `indent_type_short_name`,
    `st`.`indent_source` as `indent_source`,
    `st`.`indent_priority` as `indent_priority`
from
    ((((((((((`st_indent_master` `st`
left join `cm_company` `v` on
    (`v`.`company_id` = `st`.`company_id`
        and `v`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `st`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `cm_customer` `c` on
    (`c`.`customer_id` = `st`.`customer_id`))
left join `cm_department` `vp` on
    (`vp`.`department_id` = `st`.`department_id`))
left join `cm_department` `sd` on
    (`sd`.`department_id` = `st`.`sub_department_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `st`.`indented_by_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `st`.`approved_by_id`))
left join `cm_employee` `e2` on
    (`e2`.`employee_id` = `st`.`accepted_by_id`))
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `st`.`indent_type_id`))
left join `fm_cost_center` `fc` on
    (`fc`.`cost_center_id` = `st`.`cost_center_id`
        and `fc`.`company_id` = `st`.`company_id`))
where
    `st`.`is_delete` = 0
order by
    `st`.`indent_no`,
    `st`.`indent_version`;