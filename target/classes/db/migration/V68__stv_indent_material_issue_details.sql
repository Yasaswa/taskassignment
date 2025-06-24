


ALTER TABLE st_indent_material_issue_details ADD profit_center_id BIGINT(20) NOT NULL COMMENT 'profit center combobox from fmv_profit_centre';
ALTER TABLE st_indent_material_issue_details CHANGE profit_center_id profit_center_id BIGINT(20) NOT NULL COMMENT 'profit center combobox from fmv_profit_centre' AFTER cost_center_id;
ALTER TABLE st_indent_material_issue_details ADD routing_code varchar(100) NULL COMMENT 'combination of profit and cost center short names';
ALTER TABLE st_indent_material_issue_details CHANGE routing_code routing_code varchar(100) NULL COMMENT 'combination of profit and cost center short names' AFTER profit_center_id;
ALTER TABLE st_indent_material_issue_details CHANGE cost_center_id cost_center_id bigint(20) NOT NULL AFTER material_batch_rate;
ALTER TABLE st_indent_material_issue_details CHANGE customer_order_no customer_order_no varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL NULL AFTER issue_remark;
ALTER TABLE st_indent_material_issue_details CHANGE issue_batch_no issue_batch_no varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL NULL AFTER customer_order_no;
ALTER TABLE st_indent_material_issue_details CHANGE material_batch_rate material_batch_rate double(18,4) DEFAULT 0.0000 NULL COMMENT 'fill when material get issue update rate while issue from stock table' AFTER issue_batch_no;
ALTER TABLE st_indent_material_issue_details CHANGE indent_details_id indent_details_id bigint(20) DEFAULT 0 NULL COMMENT 'indent details transaction id' AFTER routing_code;



-- stv_indent_material_issue_details source

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
    `st`.`product_material_indent_quantity` as `product_material_indent_quantity`,
    `st`.`product_material_indent_weight` as `product_material_indent_weight`,
    `st`.`product_material_issue_quantity` as `product_material_issue_quantity`,
    `st`.`product_material_issue_weight` as `product_material_issue_weight`,
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
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_weight`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `product_Rawmaterial_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `product_Rawmaterial_stock_weight`,
    coalesce((select sum(`ptdetails`.`reserve_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `reserve_quantity`,
    coalesce((select sum(`ptdetails`.`reserve_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `reserve_weight`,
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
    `fmp`.`profit_center_name` as `profit_center_name`,
    `fm`.`cost_center_name` as `cost_center_name`,
    `st`.`routing_code` as `routing_code`,
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
    ((((((((`st_indent_material_issue_details` `st`
left join `stv_indent_material_issue_summary` `v` on
    (`v`.`company_branch_id` = `st`.`company_branch_id`
        and `v`.`company_id` = `st`.`company_id`
        and `v`.`issue_master_transaction_id` = `st`.`issue_master_transaction_id`
        and `v`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rmfgsr` on
    (`rmfgsr`.`product_material_id` = `st`.`product_material_id`))
left join `cm_employee` `e` on
    (`e`.`is_delete` = 0
        and `e`.`employee_id` = `st`.`indented_by_id`))
left join `cm_godown` `gd` on
    (`gd`.`is_delete` = 0
        and `gd`.`godown_id` = `st`.`godown_id`))
left join `cm_godown_section` `gds` on
    (`gds`.`is_delete` = 0
        and `gds`.`godown_section_id` = `st`.`godown_section_id`))
left join `cm_godown_section_beans` `gdsb` on
    (`gdsb`.`is_delete` = 0
        and `gdsb`.`godown_section_beans_id` = `st`.`godown_section_beans_id`))
left join `fm_cost_center` `fm` on
    (`fm`.`is_delete` = 0
        and `fm`.`cost_center_id` = `st`.`cost_center_id`))
left join `fm_profit_center` `fmp` on
    (`fmp`.`is_delete` = 0
        and `fmp`.`profit_center_id` = `st`.`profit_center_id`))
where
    `st`.`is_delete` = 0;