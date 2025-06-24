CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `fmv_profit_center` AS
select
    `b`.`profit_center_group` AS `profit_center_group`,
    `b`.`profit_center_name` AS `profit_center_name`,
    `b`.`profit_center_short_name` AS `profit_center_short_name`,
    `c`.`company_legal_name` AS `company_name`,
    `cb`.`company_branch_name` AS `company_branch_name`,
    case
        `b`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `b`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `b`.`is_active` AS `is_active`,
    `b`.`is_delete` AS `is_delete`,
    `b`.`created_by` AS `created_by`,
    `b`.`created_on` AS `created_on`,
    `b`.`modified_by` AS `modified_by`,
    `b`.`modified_on` AS `modified_on`,
    `b`.`deleted_by` AS `deleted_by`,
    `b`.`deleted_on` AS `deleted_on`,
    `b`.`profit_center_id` AS `profit_center_id`,
    `b`.`company_id` AS `company_id`,
    `b`.`company_branch_id` AS `company_branch_id`,
    `b`.`profit_center_name` AS `field_name`,
    `b`.`profit_center_id` AS `field_id`
from
    ((`fm_profit_center` `b`
-- left join `cmv_company_summary` `v` on
--     (`v`.`company_id` = `b`.`company_id`
--         and `v`.`company_branch_id` = `b`.`company_branch_id`))
    left join `cm_company` `c` on
    (`c`.`company_id` = `b`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `b`.`company_branch_id`
        and `cb`.`company_id` = `b`.`company_id`
        and `cb`.`is_delete` = 0))
where
    `b`.`is_delete` = 0;


create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_details` as
select
    `st`.`issue_no` as `issue_no`,
    `st`.`issue_date` as `issue_date`,
    `st`.`issue_version` as `issue_version`,
    `v`.`department_name` as `department_name`,
    `v`.`sub_department_name` as `sub_department_name`,
    case
        `st`.`issue_item_status` when 'MI' then 'Material Issue'
        when 'C' then 'Completed'
        when 'I' then 'Partial Issue'
        when 'AC' then 'Accepted'
        else 'Pending'
    end as `issue_item_status_desc`,
    `e`.`employee_name` as `indented_by_name`,
    `v`.`customer_name` as `customer_name`,
    `v`.`expected_schedule_date` as `expected_schedule_date`,
    `st`.`customer_order_no` as `customer_order_no`,
    `rmfgsr`.`product_type_group` as `product_type_group`,
    `st`.`product_material_id` as `product_material_id`,
    `rmfgsr`.`product_material_name` as `product_material_name`,
    `rmfgsr`.`actual_count` as `actual_count`,
    `st`.`product_material_indent_quantity` as `product_material_indent_quantity`,
    `st`.`product_material_indent_weight` as `product_material_indent_weight`,
    `st`.`product_material_issue_quantity` as `product_material_issue_quantity`,
    `st`.`product_material_issue_weight` as `product_material_issue_weight`,
    `st`.`product_material_issue_boxes` as `product_material_issue_boxes`,
    `st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
    `rmfgsr`.`product_material_name` as `product_rm_name`,
    `rmfgsr`.`product_material_drawing_no` as `product_rm_drawing_no`,
    `rmfgsr`.`product_material_tech_spect` as `product_rm_tech_spect`,
    `st`.`product_material_issue_return_quantity` as `product_material_issue_return_quantity`,
    `rmfgsr`.`product_type_name` as `product_type_name`,
    `rmfgsr`.`product_material_make_name` as `product_make_name`,
    `rmfgsr`.`product_material_type_name` as `product_material_type_name`,
    `rmfgsr`.`product_material_stock_unit_name` as `product_material_unit_name`,
    `rmfgsr`.`lead_time` as `product_lead_time`,
    `ps`.`product_material_stock_quantity` as `product_material_stock_quantity`,
    `ps`.`product_material_stock_weight` as `product_material_stock_weight`,
    `ps`.`reserve_quantity` as `reserve_quantity`,
    `ps`.`reserve_weight` as `reserve_weight`,
    ifnull(`sd`.`closing_balance_quantity`, 0) as `closing_balance_quantity`,
    ifnull(`sd`.`closing_balance_weight`, 0) as `closing_balance_weight`,
    ifnull(`st`.`cone_per_wt`, 0) as `cone_per_wt`,
    ifnull(`sd`.`closing_no_of_boxes`, 0) as `closing_no_of_boxes`,
    `st`.`product_material_approved_quantity` as `product_material_approved_quantity`,
    `st`.`product_material_approved_weight` as `product_material_approved_weight`,
    `st`.`product_material_rejected_quantity` as `product_material_rejected_quantity`,
    `st`.`product_material_rejected_weight` as `product_material_rejected_weight`,
    `st`.`product_material_issue_return_weight` as `product_material_issue_return_weight`,
    `v`.`customer_state_name` as `customer_state_name`,
    `v`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `st`.`product_std_weight` as `product_std_weight`,
    `st`.`indent_no` as `indent_no`,
    `st`.`indent_date` as `indent_date`,
    `st`.`indent_version` as `indent_version`,
    `st`.`issue_item_status` as `issue_item_status`,
    `st`.`issue_batch_no` as `issue_batch_no`,
    `st`.`material_batch_rate` as `material_batch_rate`,
    `st`.`product_material_issue_quantity` * `st`.`material_batch_rate` as `material_issue_amount`,
    `fmp`.`profit_center_name` as `profit_center_name`,
    `fm`.`cost_center_name` as `cost_center_name`,
    `st`.`routing_code` as `routing_code`,
    `v`.`company_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `st`.`financial_year` as `financial_year`,
    `st`.`issue_remark` as `issue_remark`,
    `gd`.`godown_name` as `godown_name`,
    `gds`.`godown_section_name` as `godown_section_name`,
    `gdsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `st`.`godown_id` as `godown_id`,
    `st`.`godown_section_id` as `godown_section_id`,
    `st`.`godown_section_beans_id` as `godown_section_beans_id`,
    `st`.`cost_center_id` as `cost_center_id`,
    `st`.`goods_receipt_no` as `goods_receipt_no`,
    `st`.`profit_center_id` as `profit_center_id`,
    `st`.`creel_no` as `creel_no`,
    `st`.`set_no` as `set_no`,
    `sd`.`supplier_id` as `supplier_id`,
    `st`.`issue_requisition_type` as `issue_requisition_type`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'Inactive'
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
    `v`.`customer_id` as `customer_id`,
    `v`.`department_id` as `department_id`,
    `st`.`indented_by_id` as `indented_by_id`,
    `v`.`issued_by_id` as `issued_by_id`,
    `v`.`company_id` as `company_id`,
    `v`.`company_branch_id` as `company_branch_id`,
    `v`.`indent_issue_type_id` as `product_type_id`,
    `rmfgsr`.`product_material_type_id` as `product_material_type_id`,
    `rmfgsr`.`product_material_grade_id` as `product_material_grade_id`,
    `rmfgsr`.`product_material_packing_id` as `product_material_packing_id`,
    `st`.`product_material_unit_id` as `product_material_unit_id`,
    `st`.`issue_details_transaction_id` as `issue_details_transaction_id`,
    `st`.`issue_master_transaction_id` as `issue_master_transaction_id`,
    `st`.`indent_details_id` as `indent_details_id`,
    `v`.`sub_department_id` as `sub_department_id`,
    `v`.`issue_no` as `field_name`,
    `v`.`issue_version` as `field_id`
from
    ((((((((((`st_indent_material_issue_details` `st`
join `stv_indent_material_issue_summary` `v` on
    (`v`.`company_branch_id` = `st`.`company_branch_id`
        and `v`.`company_id` = `st`.`company_id`
        and `v`.`issue_master_transaction_id` = `st`.`issue_master_transaction_id`
        and `v`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `st`.`product_material_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `st`.`indented_by_id`
        and `e`.`is_delete` = 0))
left join `cm_godown` `gd` on
    (`gd`.`godown_id` = `st`.`godown_id`
        and `gd`.`is_delete` = 0))
left join `cm_godown_section` `gds` on
    (`gds`.`godown_section_id` = `st`.`godown_section_id`
        and `gds`.`is_delete` = 0))
left join `cm_godown_section_beans` `gdsb` on
    (`gdsb`.`godown_section_beans_id` = `st`.`godown_section_beans_id`
        and `gdsb`.`is_delete` = 0))
left join `fm_cost_center` `fm` on
    (`fm`.`cost_center_id` = `st`.`cost_center_id`
        and `fm`.`is_delete` = 0))
left join `fm_profit_center` `fmp` on
    (`fmp`.`profit_center_id` = `st`.`profit_center_id`
        and `fmp`.`is_delete` = 0
        and `fmp`.`is_active` = 1))
left join (
    select
        `sm_product_rm_stock_summary`.`product_rm_id` as `product_rm_id`,
        `sm_product_rm_stock_summary`.`godown_id` as `godown_id`,
        `sm_product_rm_stock_summary`.`company_id` as `company_id`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_quantity`) as `product_material_stock_quantity`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_weight`) as `product_material_stock_weight`,
        sum(`sm_product_rm_stock_summary`.`reserve_quantity`) as `reserve_quantity`,
        sum(`sm_product_rm_stock_summary`.`reserve_weight`) as `reserve_weight`
    from
        `sm_product_rm_stock_summary`
    group by
        `sm_product_rm_stock_summary`.`product_rm_id`,
        `sm_product_rm_stock_summary`.`godown_id`,
        `sm_product_rm_stock_summary`.`company_id`) `ps` on
    (`ps`.`product_rm_id` = `st`.`product_material_id`
        and `ps`.`godown_id` = `st`.`godown_id`
        and `ps`.`company_id` = `st`.`company_id`))
left join (
    select
        `sm_product_rm_stock_details`.`product_rm_id` as `product_rm_id`,
        `sm_product_rm_stock_details`.`batch_no` as `batch_no`,
        `sm_product_rm_stock_details`.`day_closed` as `day_closed`,
        `sm_product_rm_stock_details`.`godown_id` as `godown_id`,
        `sm_product_rm_stock_details`.`godown_section_id` as `godown_section_id`,
        `sm_product_rm_stock_details`.`godown_section_beans_id` as `godown_section_beans_id`,
        `sm_product_rm_stock_details`.`supplier_id` as `supplier_id`,
        sum(`sm_product_rm_stock_details`.`closing_no_of_boxes`) as `closing_no_of_boxes`,
        sum(`sm_product_rm_stock_details`.`closing_balance_quantity`) as `closing_balance_quantity`,
        sum(`sm_product_rm_stock_details`.`closing_balance_weight`) as `closing_balance_weight`
    from
        `sm_product_rm_stock_details`
    where
        `sm_product_rm_stock_details`.`day_closed` = 0
        and `sm_product_rm_stock_details`.`is_delete` = 0
    group by
        `sm_product_rm_stock_details`.`company_id`,
        `sm_product_rm_stock_details`.`product_rm_id`,
        `sm_product_rm_stock_details`.`batch_no`,
        `sm_product_rm_stock_details`.`godown_id`,
        `sm_product_rm_stock_details`.`godown_section_id`,
        `sm_product_rm_stock_details`.`godown_section_beans_id`) `sd` on
    (`sd`.`product_rm_id` = `st`.`product_material_id`
        and `sd`.`batch_no` = `st`.`issue_batch_no`
        and `sd`.`godown_id` = `st`.`godown_id`
        and `sd`.`godown_section_id` = `st`.`godown_section_id`
        and `sd`.`godown_section_beans_id` = `st`.`godown_section_beans_id`))
where
    `st`.`is_delete` = 0;
