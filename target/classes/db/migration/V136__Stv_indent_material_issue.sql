 alter table st_indent_material_issue_master
add column `sales_type` varchar(50) DEFAULT null;

ALTER TABLE st_indent_material_issue_master ADD godown_issuer_id bigint(20) NULL;


create or replace
algorithm = UNDEFINED view `smv_product_rm_fg_sr` as
select
    `rmt`.`product_type_group` as `product_type_group`,
    `rmt`.`product_type_name` as `product_type_name`,
    `rmt`.`product_category1_name` as `product_material_category1_name`,
    `rm`.`product_rm_id` as `product_material_id`,
    `rm`.`product_rm_code` as `product_material_code`,
    `rm`.`product_rm_name` as `product_material_name`,
    `rm`.`product_rm_short_name` as `product_material_short_name`,
    `rm`.`product_rm_tech_spect` as `product_material_tech_spect`,
    `u3`.`product_unit_name` as `product_material_stock_unit_name`,
    ifnull(null, 0) as `stock_quantity`,
    ifnull(null, 0) as `stock_weight`,
    ifnull(null, 0) as `customer_stock_quantity`,
    ifnull(null, 0) as `customer_stock_weight`,
    `rmc`.`lead_time` as `lead_time`,
    `rmc`.`product_rm_moq` as `product_material_moq`,
    `rmc`.`product_rm_mrp` as `product_material_mrp`,
    `rmc`.`product_rm_landed_price` as `product_material_landed_price`,
    `rmt`.`product_rm_technical_name` as `product_material_technical_name`,
    `rmt`.`product_type_short_name` as `product_material_type_short_name`,
    `rmt`.`product_category2_name` as `product_material_category2_name`,
    `rmt`.`product_category3_name` as `product_material_category3_name`,
    `rmt`.`product_category4_name` as `product_material_category4_name`,
    `rmt`.`product_category5_name` as `product_material_category5_name`,
    `p1`.`product_packing_name` as `product_material_packing_name`,
    `rmt`.`product_make_name` as `product_material_make_name`,
    `rmt`.`product_material_type_name` as `product_material_type_name`,
    `rmt`.`product_material_grade_name` as `product_material_grade_name`,
    `rmt`.`product_material_shape_name` as `product_material_shape_name`,
    `rm`.`product_rm_oem_part_code` as `product_material_oem_part_code`,
    `rm`.`product_rm_our_part_code` as `product_material_our_part_code`,
    `rmt`.`product_rm_drawing_no` as `product_material_drawing_no`,
    `rmc`.`product_rm_std_weight` as `product_material_std_weight`,
    1 as `product_material_std_hours`,
    1 as `process_duration`,
    0 as `product_std_profit_percent`,
    0 as `product_std_discount_percent`,
    case
        `rm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `rm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `g`.`godown_name` as `godown_name`,
    `g`.`godown_short_name` as `godown_short_name`,
    `g`.`godown_area` as `godown_area`,
    `gs`.`godown_section_name` as `godown_section_name`,
    `gs`.`godown_section_short_name` as `godown_section_short_name`,
    `gs`.`godown_section_area` as `godown_section_area`,
    `gsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `gsb`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    `gsb`.`godown_section_beans_area` as `godown_section_beans_area`,
    `h`.`hsn_sac_code` as `product_material_hsn_sac_code`,
    `h`.`hsn_sac_rate` as `product_material_hsn_sac_rate`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `rm`.`product_category1_id` as `product_material_category1_id`,
    `rm`.`product_type_id` as `product_type_id`,
    `rm`.`product_rm_stock_unit_id` as `product_material_stock_unit_id`,
    `rm`.`product_rm_packing_id` as `product_material_packing_id`,
    `rm`.`product_rm_hsn_sac_code_id` as `product_material_hsn_sac_code_id`,
    `rmt`.`product_category2_id` as `product_material_category2_id`,
    `rmt`.`product_category3_id` as `product_material_category3_id`,
    `rmt`.`product_category4_id` as `product_material_category4_id`,
    `rmt`.`product_category5_id` as `product_material_category5_id`,
    `rmt`.`product_make_id` as `product_material_make_id`,
    `rmt`.`product_material_type_id` as `product_material_type_id`,
    `rmt`.`product_material_grade_id` as `product_material_grade_id`,
    `rmt`.`product_material_shape_id` as `product_material_shape_id`,
    `rm`.`company_id` as `company_id`,
    `rm`.`company_branch_id` as `company_branch_id`,
    `rm`.`godown_id` as `godown_id`,
    `rm`.`godown_section_id` as `godown_section_id`,
    `rm`.`godown_section_beans_id` as `godown_section_beans_id`,
    'No' as `bom_applicable`,
    `rm`.`is_active` as `is_active`,
    `rm`.`is_delete` as `is_delete`,
    `rmc`.`cost_center_id` as `cost_center_id`,
    `rm`.`product_rm_name` as `field_name`,
    `rm`.`actual_count` as `actual_count`,
    `rm`.`product_rm_id` as `field_id`
from
    (((((((((`sm_product_rm` `rm`
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `rm`.`company_id`
        and `v`.`company_branch_id` = `rm`.`company_branch_id`))
left join `sm_product_unit` `u3` on
    (`u3`.`product_unit_id` = `rm`.`product_rm_stock_unit_id`
        and `u3`.`is_delete` = 0))
left join `sm_product_packing` `p1` on
    (`p1`.`product_packing_id` = `rm`.`product_rm_packing_id`
        and `p1`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `rm`.`product_rm_hsn_sac_code_id`
        and `h`.`is_delete` = 0))
left join `smv_product_rm_technical` `rmt` on
    (`rmt`.`is_delete` = 0
        and `rmt`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmt`.`is_delete` = 0))
left join `sm_product_rm_commercial` `rmc` on
    (`rmc`.`is_delete` = 0
        and `rmc`.`product_rm_id` = `rm`.`product_rm_id`
        and `rmc`.`is_delete` = 0))
left join `cm_godown` `g` on
    (`g`.`godown_id` = `rm`.`godown_id`))
left join `cm_godown_section` `gs` on
    (`gs`.`godown_section_id` = `rm`.`godown_section_id`))
left join `cm_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `rm`.`godown_section_beans_id`))
where
    `rm`.`is_delete` = 0
union all
select
    `fgt`.`product_type_group` as `product_type_group`,
    `fgt`.`product_type_name` as `product_type_name`,
    `fgt`.`product_category1_name` as `product_material_category1_name`,
    `fg`.`product_fg_id` as `product_material_id`,
    `fg`.`product_fg_code` as `product_material_code`,
    `fg`.`product_fg_name` as `product_material_name`,
    `fg`.`product_fg_short_name` as `product_material_short_name`,
    `fg`.`product_fg_tech_spect` as `product_material_tech_spect`,
    `u3`.`product_unit_name` as `product_material_stock_unit_name`,
    ifnull(null, 0) as `stock_quantity`,
    ifnull(null, 0) as `stock_weight`,
    ifnull(null, 0) as `customer_stock_quantity`,
    ifnull(null, 0) as `customer_stock_weight`,
    0 as `actual_count`,
    0 as `lead_time`,
    0 as `product_material_moq`,
    0 as `product_material_mrp`,
    0 as `product_material_landed_price`,
    `fgt`.`product_fg_technical_name` as `product_material_technical_name`,
    `fgt`.`product_type_short_name` as `product_material_type_short_name`,
    `fgt`.`product_category2_name` as `product_material_category2_name`,
    `fgt`.`product_category3_name` as `product_material_category3_name`,
    `fgt`.`product_category4_name` as `product_material_category4_name`,
    `fgt`.`product_category5_name` as `product_material_category5_name`,
    `p`.`product_packing_name` as `product_material_packing_name`,
    'NA' as `product_material_make_name`,
    `fgt`.`product_material_type_name` as `product_material_type_name`,
    `fgt`.`product_material_grade_name` as `product_material_grade_name`,
    `fgt`.`product_material_shape_name` as `product_material_shape_name`,
    `fg`.`product_fg_oem_part_code` as `product_material_oem_part_code`,
    `fg`.`product_fg_our_part_code` as `product_material_our_part_code`,
    `fgt`.`product_fg_drawing_no` as `product_material_drawing_no`,
    `fgc`.`product_fg_std_weight` as `product_material_std_weight`,
    1 as `product_material_std_hours`,
    1 as `process_duration`,
    0 as `product_std_profit_percent`,
    0 as `product_std_discount_percent`,
    case
        `fg`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `fg`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    'NA' as `godown_name`,
    'NA' as `godown_short_name`,
    'NA' as `godown_area`,
    'NA' as `godown_section_name`,
    'NA' as `godown_section_short_name`,
    'NA' as `godown_section_area`,
    'NA' as `godown_section_beans_name`,
    'NA' as `godown_section_beans_short_name`,
    'NA' as `godown_section_beans_area`,
    `h`.`hsn_sac_code` as `product_material_hsn_sac_code`,
    `h`.`hsn_sac_rate` as `product_material_hsn_sac_rate`,
    `v`.`company_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `fgt`.`product_category1_id` as `product_material_category1_id`,
    `fgt`.`product_type_id` as `product_type_id`,
    `fg`.`product_fg_stock_unit_id` as `product_material_stock_unit_id`,
    1 as `product_material_packing_id`,
    `fg`.`product_fg_hsn_sac_code_id` as `product_material_hsn_sac_code_id`,
    `fgt`.`product_category2_id` as `product_material_category2_id`,
    `fgt`.`product_category3_id` as `product_material_category3_id`,
    `fgt`.`product_category4_id` as `product_material_category4_id`,
    `fgt`.`product_category5_id` as `product_material_category5_id`,
    1 as `product_material_make_id`,
    `fgt`.`product_material_type_id` as `product_material_type_id`,
    `fgt`.`product_material_grade_id` as `product_material_grade_id`,
    `fgt`.`product_material_shape_id` as `product_material_shape_id`,
    `fg`.`company_id` as `company_id`,
    `fg`.`company_branch_id` as `company_branch_id`,
    '1' as `godown_id`,
    '1' as `godown_section_id`,
    '1' as `godown_section_beans_id`,
    `fg`.`bom_applicable` as `bom_applicable`,
    `fg`.`is_active` as `is_active`,
    `fg`.`is_delete` as `is_delete`,
    `fgc`.`cost_center_id` as `cost_center_id`,
    `fg`.`product_fg_name` as `field_name`,
    `fg`.`product_fg_id` as `field_id`
from
    ((((((`sm_product_fg` `fg`
left join `cmv_company` `v` on
    (`v`.`company_id` = `fg`.`company_id`))
left join `sm_product_unit` `u3` on
    (`u3`.`product_unit_id` = `fg`.`product_fg_stock_unit_id`
        and `u3`.`is_delete` = 0))
left join `cm_hsn_sac` `h` on
    (`h`.`hsn_sac_id` = `fg`.`product_fg_hsn_sac_code_id`
        and `h`.`is_delete` = 0))
left join `sm_product_packing` `p` on
    (`p`.`product_packing_id` = `fg`.`product_fg_packing_id`))
left join `smv_product_fg_technical` `fgt` on
    (`fgt`.`company_id` = `fg`.`company_id`
        and `fgt`.`product_fg_id` = `fg`.`product_fg_id`))
left join `sm_product_fg_commercial` `fgc` on
    (`fgc`.`product_fg_id` = `fg`.`product_fg_id`
        and `fgc`.`is_delete` = 0))
where
    `fg`.`is_delete` = 0;


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
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_weight`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `product_Rawmaterial_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `product_Rawmaterial_stock_weight`,
    coalesce((select sum(`ptdetails`.`reserve_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `reserve_quantity`,
    coalesce((select sum(`ptdetails`.`reserve_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`godown_id` = `st`.`godown_id`), 0) as `reserve_weight`,
    ifnull((select `ptdetails`.`closing_balance_quantity` from `sm_product_rm_stock_details` `ptdetails` where `ptdetails`.`goods_receipt_no` = `st`.`goods_receipt_no` and `ptdetails`.`batch_no` = `st`.`issue_batch_no` and `ptdetails`.`day_closed` = 0), 0) as `closing_balance_quantity`,
    ifnull((select `ptdetails`.`closing_balance_weight` from `sm_product_rm_stock_details` `ptdetails` where `ptdetails`.`goods_receipt_no` = `st`.`goods_receipt_no` and `ptdetails`.`batch_no` = `st`.`issue_batch_no` and `ptdetails`.`day_closed` = 0), 0) as `closing_balance_weight`,
    ifnull((select `ptdetails`.`weight_per_box_item` from `sm_product_rm_stock_details` `ptdetails` where `ptdetails`.`goods_receipt_no` = `st`.`goods_receipt_no` and `ptdetails`.`batch_no` = `st`.`issue_batch_no` and `ptdetails`.`day_closed` = 0), 0) as `cone_per_wt`,
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
    `rmfgsr`.`actual_count` as `actual_count`,

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





-- stv_indent_material_issue_summary source

create or replace
algorithm = UNDEFINED view `stv_indent_material_issue_summary` as
select
    `st`.`issue_no` as `issue_no`,
    `st`.`issue_date` as `issue_date`,
    `st`.`issue_version` as `issue_version`,
    `st`.`issue_group_type` as `issue_group_type`,
    `st`.`indent_issue_type` as `indent_issue_type`,
    `st`.`indent_no` as `indent_no`,
    `st`.`indent_date` as `indent_date`,
    `st`.`indent_version` as `indent_version`,
    `st`.`set_no` as `set_no`,
    `st`.`sales_type` as `sales_type`,
    `st`.`godown_issuer_id` as `godown_issuer_id`,
    `e`.`employee_name` as `godown_issuer_name`,
    case
        `st`.`issue_status` when 'MI' then 'Material Issue'
        when 'C' then 'Completed'
        when 'I' then 'Partial Issue'
        when 'AC' then 'Accepted'
        when 'A' then 'Approved'
        else 'Pending'
    end as `issue_status_desc`,
    case
        `st`.`issue_source` when 'S' then 'SO Based'
        when 'O' then 'Direct'
        else 'Internal'
    end as `indent_source_desc`,
    `vp`.`department_name` as `department_name`,
    `sd`.`department_name` as `sub_department_name`,
    `re`.`employee_name` as `requisition_by_name`,
    `ae`.`employee_name` as `approved_by_name`,
    `ie`.`employee_name` as `issued_by_name`,
    `c`.`customer_name` as `customer_name`,
    `st`.`customer_order_no` as `customer_order_no`,
    `st`.`customer_order_date` as `customer_order_date`,
    `c`.`state_name` as `customer_state_name`,
    `st`.`expected_schedule_date` as `expected_schedule_date`,
    `fm`.`cost_center_name` as `cost_center_name`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `v`.`company_legal_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `c`.`cust_branch_gst_no` as `cust_branch_gst_no`,
    `st`.`is_active` as `is_active`,
    `st`.`is_delete` as `is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`deleted_on` as `deleted_on`,
    `st`.`remark` as `remark`,
    `st`.`indent_issue_type_id` as `indent_issue_type_id`,
    `st`.`customer_id` as `customer_id`,
    `st`.`department_id` as `department_id`,
    `st`.`sub_department_id` as `sub_department_id`,
    `st`.`issued_by_id` as `issued_by_id`,
    `st`.`approved_by_id` as `approved_by_id`,
    `st`.`requisition_by_id` as `requisition_by_id`,
    `st`.`requisition_date` as `requisition_date`,
    `st`.`approved_date` as `approved_date`,
    `st`.`company_id` as `company_id`,
    `st`.`company_branch_id` as `company_branch_id`,
    `st`.`financial_year` as `financial_year`,
    `st`.`issue_master_transaction_id` as `issue_master_transaction_id`,
    `st`.`cost_center_id` as `cost_center_id`,
    `st`.`issue_status` as `issue_status`,
    `st`.`issue_source` as `issue_source`,
    `st`.`issue_no` as `field_name`,
    `st`.`issue_version` as `field_id`
from
    (((((((((`st_indent_material_issue_master` `st`
left join `cmv_company_summary` `v` on
    (`v`.`company_branch_id` = `st`.`company_branch_id`
        and `v`.`company_id` = `st`.`company_id`))
left join `cm_department` `vp` on
    (`vp`.`department_id` = `st`.`department_id`))
left join `cm_department` `sd` on
    (`sd`.`department_id` = `st`.`sub_department_id`))
left join `cmv_customer_summary` `c` on
    (`c`.`customer_id` = `st`.`customer_id`))
left join `cm_employee` `re` on
    (`re`.`employee_id` = `st`.`requisition_by_id`))
left join `cm_employee` `ae` on
    (`ae`.`employee_id` = `st`.`approved_by_id`))
left join `cm_employee` `ie` on
    (`ie`.`employee_id` = `st`.`issued_by_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `st`.`godown_issuer_id`))
left join `fm_cost_center` `fm` on
    (`fm`.`cost_center_id` = `st`.`cost_center_id`))
where
    `st`.`is_delete` = 0
order by
    `st`.`issue_no`,
    `st`.`issue_version`;

update am_modules_forms set url_parameter ="RMI" where modules_forms_id =669;

INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,2,12,'Raw Material Request Slip',1,'Raw Material Request Slip','Purchase','<FrmRawMaterialRequisitionListing />','import FrmRawMaterialRequisitionListing from "./Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionListing";','/Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionListing','<FrmRawMaterialRequisitionEntry />','import FrmRawMaterialRequisitionEntry from "./Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionEntry";','/Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionEntry',NULL,'Raw Material Request Slip',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'RMR');
