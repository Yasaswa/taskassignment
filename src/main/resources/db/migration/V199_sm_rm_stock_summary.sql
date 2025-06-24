



ALTER TABLE sm_product_rm_stock_summary DROP COLUMN stock_date;
ALTER TABLE sm_product_rm_stock_summary DROP COLUMN day_closed;





-- smv_product_rm_stock_summary source

create or REPLACE algorithm = UNDEFINED view `smv_product_rm_stock_summary` as
select
    `sm`.`financial_year` as `financial_year`,
    `supp`.`supp_branch_name` as `supplier_name`,
    `cust`.`customer_name` as `customer_name`,
    `pt`.`product_type_name` as `product_type_name`,
    `sm`.`product_type_group` as `product_type_group`,
    `rm`.`product_material_code` as `product_rm_code`,
    `rm`.`product_material_name` as `product_rm_name`,
    `rm`.`product_material_category1_name` as `product_material_category1_name`,
    `rm`.`product_material_category2_name` as `product_material_category2_name`,
    `pu`.`product_unit_name` as `product_unit_name`,
    `pp`.`product_packing_name` as `product_packing_name`,
    `sm`.`order_quantity` as `order_quantity`,
    `sm`.`order_weight` as `order_weight`,
    `sm`.`pending_quantity` as `pending_quantity`,
    `sm`.`pending_weight` as `pending_weight`,
    `sm`.`opening_quantity` as `opening_quantity`,
    `sm`.`opening_weight` as `opening_weight`,
    `sm`.`closing_balance_quantity` as `closing_balance_quantity`,
    `sm`.`closing_balance_weight` as `closing_balance_weight`,
    `sm`.`reserve_quantity` as `reserve_quantity`,
    `sm`.`reserve_weight` as `reserve_weight`,
    `sm`.`excess_quantity` as `excess_quantity`,
    `sm`.`excess_weight` as `excess_weight`,
    `sm`.`pree_closed_quantity` as `pree_closed_quantity`,
    `sm`.`pree_closed_weight` as `pree_closed_weight`,
    `sm`.`purchase_quantity` as `purchase_quantity`,
    `sm`.`purchase_weight` as `purchase_weight`,
    `sm`.`purchase_return_quantity` as `purchase_return_quantity`,
    `sm`.`purchase_return_weight` as `purchase_return_weight`,
    `sm`.`purchase_rejection_quantity` as `purchase_rejection_quantity`,
    `sm`.`purchase_rejection_weight` as `purchase_rejection_weight`,
    `sm`.`jobcard_quantity` as `jobcard_quantity`,
    `sm`.`jobcard_weight` as `jobcard_weight`,
    `sm`.`production_issue_quantity` as `production_issue_quantity`,
    `sm`.`production_issue_weight` as `production_issue_weight`,
    `sm`.`production_issue_return_quantity` as `production_issue_return_quantity`,
    `sm`.`production_issue_return_weight` as `production_issue_return_weight`,
    `sm`.`production_issue_return_boxes` as `production_issue_return_boxes`,
    `sm`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
    `sm`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
    `sm`.`production_quantity` as `production_quantity`,
    `sm`.`production_weight` as `production_weight`,
    `sm`.`production_return_quantity` as `production_return_quantity`,
    `sm`.`production_return_weight` as `production_return_weight`,
    `sm`.`production_rejection_quantity` as `production_rejection_quantity`,
    `sm`.`production_rejection_weight` as `production_rejection_weight`,
    `sm`.`assembly_production_issue_quantity` as `assembly_production_issue_quantity`,
    `sm`.`assembly_production_issue_weight` as `assembly_production_issue_weight`,
    `sm`.`sales_quantity` as `sales_quantity`,
    `sm`.`sales_weight` as `sales_weight`,
    `sm`.`sales_return_quantity` as `sales_return_quantity`,
    `sm`.`sales_return_weight` as `sales_return_weight`,
    `sm`.`sales_rejection_quantity` as `sales_rejection_quantity`,
    `sm`.`sales_rejection_weight` as `sales_rejection_weight`,
    `sm`.`customer_receipt_quantity` as `customer_receipt_quantity`,
    `sm`.`customer_receipt_weight` as `customer_receipt_weight`,
    `sm`.`customer_return_quantity` as `customer_return_quantity`,
    `sm`.`customer_return_weight` as `customer_return_weight`,
    `sm`.`customer_rejection_weight` as `customer_rejection_weight`,
    `sm`.`customer_rejection_quantity` as `customer_rejection_quantity`,
    `sm`.`transfer_issue_quantity` as `transfer_issue_quantity`,
    `sm`.`transfer_issue_weight` as `transfer_issue_weight`,
    `sm`.`transfer_receipt_quantity` as `transfer_receipt_quantity`,
    `sm`.`transfer_receipt_weight` as `transfer_receipt_weight`,
    `sm`.`outsources_out_quantity` as `outsources_out_quantity`,
    `sm`.`outsources_out_weight` as `outsources_out_weight`,
    `sm`.`outsources_in_quantity` as `outsources_in_quantity`,
    `sm`.`outsources_in_weight` as `outsources_in_weight`,
    `sm`.`outsources_rejection_quantity` as `outsources_rejection_quantity`,
    `sm`.`outsources_rejection_weight` as `outsources_rejection_weight`,
    `sm`.`loan_receipt_quantity` as `loan_receipt_quantity`,
    `sm`.`loan_receipt_weight` as `loan_receipt_weight`,
    `sm`.`loan_issue_quantity` as `loan_issue_quantity`,
    `sm`.`loan_issue_weight` as `loan_issue_weight`,
    `sm`.`cancel_quantity` as `cancel_quantity`,
    `sm`.`cancel_weight` as `cancel_weight`,
    `sm`.`difference_quantity` as `difference_quantity`,
    `sm`.`difference_weight` as `difference_weight`,
    `sm`.`total_box_weight` as `total_box_weight`,
    `sm`.`total_quantity_in_box` as `total_quantity_in_box`,
    `sm`.`weight_per_box_item` as `weight_per_box_item`,
    `sm`.`opening_no_of_boxes` as `opening_no_of_boxes`,
    `sm`.`reserve_no_of_boxes` as `reserve_no_of_boxes`,
    `sm`.`purchase_no_of_boxes` as `purchase_no_of_boxes`,
    `sm`.`issue_no_of_boxes` as `issue_no_of_boxes`,
    `sm`.`closing_no_of_boxes` as `closing_no_of_boxes`,
    `sm`.`supplier_return_quantity` as `supplier_return_quantity`,
    `sm`.`supplier_return_weight` as `supplier_return_weight`,
    `sm`.`supplier_return_boxes` as `supplier_return_boxes`,
    `gd`.`godown_name` as `godown_name`,
    `gd`.`godown_short_name` as `godown_short_name`,
    `gd`.`godown_section_count` as `godown_section_count`,
    `gds`.`godown_section_name` as `godown_section_name`,
    `gds`.`godown_section_short_name` as `godown_section_short_name`,
    `gdsb`.`godown_section_beans_name` as `godown_section_beans_name`,
    `gdsb`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
    `sm`.`remark` as `remark`,
    `v`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `pt`.`product_type_short_name` as `product_type_short_name`,
    `pp`.`quantity_per_packing` as `quantity_per_packing`,
    `sm`.`material_rate` as `material_rate`,
    case
        `sm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `sm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `sm`.`is_active` as `is_active`,
    `sm`.`is_delete` as `is_delete`,
    `sm`.`created_by` as `created_by`,
    `sm`.`created_on` as `created_on`,
    `sm`.`modified_by` as `modified_by`,
    `sm`.`modified_on` as `modified_on`,
    `sm`.`deleted_by` as `deleted_by`,
    `sm`.`deleted_on` as `deleted_on`,
    `sm`.`company_id` as `company_id`,
    `sm`.`company_branch_id` as `company_branch_id`,
    `sm`.`stock_transaction_id` as `stock_transaction_id`,
    `sm`.`supplier_id` as `supplier_id`,
    `sm`.`customer_id` as `customer_id`,
    `sm`.`product_type_id` as `product_type_id`,
    `sm`.`product_rm_id` as `product_rm_id`,
    `sm`.`product_material_unit_id` as `product_material_unit_id`,
    `sm`.`product_material_packing_id` as `product_material_packing_id`,
    `sm`.`godown_id` as `godown_id`,
    `sm`.`godown_section_id` as `godown_section_id`,
    `sm`.`godown_section_beans_id` as `godown_section_beans_id`,
    `rm`.`product_material_category1_id` as `product_material_category1_id`,
    `rm`.`product_material_category2_id` as `product_material_category2_id`
from
    (((((((((((`sm_product_rm_stock_summary` `sm`
left join `cm_company` `v` on
    (`v`.`company_id` = `sm`.`company_id`
        and `v`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_id` = `sm`.`company_id`
        and `cb`.`company_branch_id` = `sm`.`company_branch_id`
        and `v`.`is_delete` = 0))
left join `cm_supplier_branch` `supp` on
    (`supp`.`is_delete` = 0
        and `supp`.`supp_branch_id` = `sm`.`supplier_id`))
left join `cm_godown` `gd` on
    (`gd`.`godown_id` = `sm`.`godown_id`
        and `gd`.`is_delete` = 0))
left join `cm_godown_section` `gds` on
    (`gds`.`godown_section_id` = `sm`.`godown_section_id`
        and `gds`.`is_delete` = 0))
left join `cm_godown_section_beans` `gdsb` on
    (`gdsb`.`godown_section_beans_id` = `sm`.`godown_section_beans_id`
        and `gdsb`.`is_delete` = 0))
left join `cm_customer` `cust` on
    (`cust`.`company_branch_id` = `sm`.`company_branch_id`
        and `cust`.`company_id` = `sm`.`company_id`
        and `cust`.`customer_id` = `sm`.`customer_id`
        and `cust`.`is_delete` = 0))
left join `sm_product_type` `pt` on
    (`pt`.`product_type_id` = `sm`.`product_type_id`
        and `pt`.`is_delete` = 0))
left join `smv_product_rm_fg_sr` `rm` on
    (`rm`.`product_material_id` = `sm`.`product_rm_id`
        and `rm`.`product_type_id` = `sm`.`product_type_id`))
left join `sm_product_unit` `pu` on
    (`pu`.`product_unit_id` = `sm`.`product_material_unit_id`
        and `pu`.`is_delete` = 0))
left join `sm_product_packing` `pp` on
    (`pp`.`is_delete` = 0
        and `pp`.`product_packing_id` = `sm`.`product_material_packing_id`))
where
`sm`.`is_delete` = 0;




-- stv_indent_details source

create or REPLACE algorithm = UNDEFINED view `stv_indent_details` as
select
    `st`.`indent_no` as `indent_no`,
    `st`.`indent_date` as `indent_date`,
    `st`.`indent_version` as `indent_version`,
    case
        `v`.`indent_priority` when 'H' then 'High-Priority'
        else 'Low-Priority'
    end as `indent_priority_desc`,
    case
        `st`.`indent_item_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Cenceled'
        when 'O' then 'Purchse Order Genreated'
        when 'IPO' then 'Partial PO Genreated'
        when 'POA' then 'Purchse Order Approved'
        when 'G' then 'Goods Receipts'
        when 'IG' then 'Partial Goods Receipts'
        when 'I' then 'Partial Issue'
        else 'Pending'
    end as `indent_item_status_desc`,
    case
        `v`.`indent_source` when 'S' then 'SO Based'
        when 'O' then 'Direct'
        else 'Internal'
    end as `indent_source_desc`,
    `d`.`department_name` as `department_name`,
    `sd`.`department_name` as `sub_department_name`,
    `e1`.`employee_name` as `indented_by_name`,
    `e2`.`employee_name` as `approved_by_name`,
    `cs`.`customer_name` as `customer_name`,
    `v`.`customer_order_Date` as `customer_order_date`,
    `v`.`expected_schedule_date` as `expected_schedule_date`,
    case
        `v`.`indent_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Cenceled'
        when 'O' then 'Purchse Order Genreated'
        when 'IPO' then 'Partial PO Genreated'
        when 'POA' then 'Purchse Order Approved'
        when 'G' then 'Goods Receipts'
        when 'IG' then 'Partial Goods Receipts'
        when 'I' then 'Partial Issue'
        else 'Pending'
    end as `indent_status_desc`,
    `st`.`customer_order_no` as `customer_order_no`,
    `st`.`so_sr_no` as `so_sr_no`,
    `st`.`sales_order_details_transaction_id` as `sales_order_details_transaction_id`,
    `st`.`remark` as `remark`,
    `st`.`product_fg_material_quantity` as `product_fg_material_quantity`,
    `st`.`product_fg_material_weight` as `product_fg_material_weight`,
    `child`.`product_material_name` as `product_rm_name`,
    `child`.`product_material_drawing_no` as `product_rm_drawing_no`,
    `child`.`product_material_tech_spect` as `product_rm_tech_spect`,
    `child`.`product_material_stock_unit_name` as `product_rm_stock_unit_name`,
    `child`.`product_type_name` as `product_type_name`,
    `child`.`product_material_category1_name` as `product_category1_name`,
    `child`.`product_material_oem_part_code` as `product_rm_oem_part_code`,
    `child`.`product_material_our_part_code` as `product_rm_our_part_code`,
    `child`.`product_material_category2_name` as `product_category2_name`,
    `child`.`product_material_std_weight` as `product_rm_std_weight`,
    `child`.`product_material_packing_name` as `product_rm_packing_name`,
    `child`.`product_material_hsn_sac_code` as `product_rm_hsn_sac_code`,
    `child`.`product_material_hsn_sac_rate` as `product_rm_hsn_sac_rate`,
    `st`.`product_material_quantity` as `product_material_quantity`,
    `st`.`product_material_weight` as `product_material_weight`,
    `st`.`lead_time` as `lead_time`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `smv_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`company_id` = `st`.`company_id` ), 0) as `product_material_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `smv_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_weight`,
    `st`.`product_material_reserve_quantity` as `product_material_reserve_quantity`,
    `st`.`product_material_reserve_weight` as `product_material_reserve_weight`,
    `st`.`product_material_approved_quantity` as `product_material_approved_quantity`,
    `st`.`product_material_approved_weight` as `product_material_approved_weight`,
    `st`.`product_material_issue_quantity` as `product_material_issue_quantity`,
    `st`.`product_material_issue_weight` as `product_material_issue_weight`,
    `st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
    `st`.`product_material_rejected_quantity` as `product_material_rejected_quantity`,
    `st`.`product_material_rejected_weight` as `product_material_rejected_weight`,
    `st`.`product_material_return_quantity` as `product_material_return_quantity`,
    `st`.`product_material_return_weight` as `product_material_return_weight`,
    `st`.`product_material_grn_accepted_quantity` as `product_material_grn_accepted_quantity`,
    `st`.`product_material_grn_accepted_weight` as `product_material_grn_accepted_weight`,
    `st`.`product_child_bom_quantity` as `product_child_bom_quantity`,
    `st`.`product_child_bom_weight` as `product_child_bom_weight`,
    `st`.`approval_remark` as `approval_remark`,
    `e3`.`employee_name` as `issued_by_name`,
    `st`.`issued_by_id` as `issued_by_id`,
    `st`.`issued_date` as `issued_date`,
    `st`.`indent_item_status` as `indent_item_status`,
    `st`.`po_item_status` as `po_item_status`,
    `st`.`grn_item_status` as `grn_item_status`,
    `st`.`issue_item_status` as `issue_item_status`,
    `v`.`indent_source` as `indent_source`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `st`.`financial_year` as `financial_year`,
    `child`.`product_type_group` as `product_type_group`,
    `child`.`product_material_type_short_name` as `indent_type_short_name`,
    `v`.`Indent_type` as `indent_type`,
    `v`.`indent_status` as `indent_status`,
    `st`.`indent_no` as `field_name`,
    `st`.`indent_version` as `field_id`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `v`.`is_active` as `is_active`,
    `st`.`is_delete` as `is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`deleted_on` as `deleted_on`,
    `st`.`indent_details_id` as `indent_details_id`,
    `st`.`product_fg_id` as `product_fg_id`,
    `st`.`product_material_id` as `product_material_id`,
    `st`.`product_material_unit_id` as `product_material_unit_id`,
    `st`.`cost_center_id` as `cost_center_id`,
    `fm`.`cost_center_name` as `cost_center_name`,
    `v`.`indent_transaction_type` as `indent_transaction_type`,
    `v`.`indent_master_id` as `indent_master_id`,
    `v`.`indent_type_id` as `indent_type_id`,
    `v`.`customer_id` as `customer_id`,
    `v`.`department_id` as `department_id`,
    `v`.`sub_department_id` as `sub_department_id`,
    `v`.`indented_by_id` as `indented_by_id`,
    `st`.`company_id` as `company_id`,
    `v`.`approved_date` as `approved_date`,
    `st`.`company_branch_id` as `company_branch_id`,
    `child`.`product_type_id` as `product_type_id`,
    `child`.`product_material_hsn_sac_code_id` as `product_rm_hsn_sac_code_id`,
    `child`.`product_material_packing_id` as `product_rm_packing_id`,
    `child`.`product_material_category1_id` as `product_category1_id`,
    `child`.`product_material_category2_id` as `product_category2_id`,
    `child`.`product_material_make_id` as `product_make_id`,
    `child`.`product_material_type_id` as `product_material_type_id`,
    `child`.`product_material_grade_id` as `product_material_grade_id`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_quantity`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `st`.`indent_no` and `ptdetails`.`product_material_id` = `st`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `prev_po_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_weight`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `st`.`indent_no` and `ptdetails`.`product_material_id` = `st`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `prev_po_weight`
from
    (((((((((((`st_indent_details` `st`
left join `cm_company` `c` on
    (`c`.`company_id` = `st`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `st`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `st_indent_master` `v` on
    (`st`.`company_branch_id` = `v`.`company_branch_id`
        and `st`.`company_id` = `v`.`company_id`
        and `st`.`indent_no` = `v`.`indent_no`
        and `v`.`is_delete` = 0))
left join `cm_department` `d` on
    (`d`.`department_id` = `v`.`department_id`))
left join `cm_department` `sd` on
    (`sd`.`department_id` = `v`.`sub_department_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `v`.`indented_by_id`))
left join `cm_employee` `e2` on
    (`e2`.`employee_id` = `v`.`approved_by_id`))
left join `cm_employee` `e3` on
    (`e3`.`employee_id` = `st`.`issued_by_id`
        and `e1`.`company_id` = `st`.`company_id`))
left join `smv_product_rm_fg_sr` `child` on
    (`child`.`product_material_id` = `st`.`product_material_id`))
left join `fm_cost_center` `fm` on
    (`fm`.`company_id` = `st`.`company_id`
        and `fm`.`cost_center_id` = `st`.`cost_center_id`))
left join `cm_customer` `cs` on
    (`cs`.`customer_id` = `v`.`customer_id`))
where
    `st`.`is_delete` = 0;



    -- stv_indent_material_issue_details source

    create or REPLACE algorithm = UNDEFINED view `stv_indent_material_issue_details` as
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
        ifnull(`sd`.`weight_per_box_item`, 0) as `cone_per_wt`,
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
            and `fmp`.`is_delete` = 0))
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
            `sm_product_rm_stock_details`.`goods_receipt_no` as `goods_receipt_no`,
            `sm_product_rm_stock_details`.`batch_no` as `batch_no`,
            `sm_product_rm_stock_details`.`day_closed` as `day_closed`,
            `sm_product_rm_stock_details`.`closing_balance_quantity` as `closing_balance_quantity`,
            `sm_product_rm_stock_details`.`closing_balance_weight` as `closing_balance_weight`,
            `sm_product_rm_stock_details`.`weight_per_box_item` as `weight_per_box_item`,
            `sm_product_rm_stock_details`.`closing_no_of_boxes` as `closing_no_of_boxes`
        from
            `sm_product_rm_stock_details`
        where
            `sm_product_rm_stock_details`.`day_closed` = 0) `sd` on
        (`sd`.`goods_receipt_no` = `st`.`goods_receipt_no`
            and `sd`.`batch_no` = `st`.`issue_batch_no`))
    where
        `st`.`is_delete` = 0;



        -- ptv_purchase_order_details source

        create or REPLACE algorithm = UNDEFINED view `ptv_purchase_order_details` as
        select
            `pt`.`purchase_order_type` as `purchase_order_type`,
            `pt`.`purchase_order_no` as `purchase_order_no`,
            `pt`.`purchase_order_date` as `purchase_order_date`,
            `pt`.`purchase_order_version` as `purchase_order_version`,
            `pt`.`indent_no` as `indent_no`,
            `pt`.`indent_date` as `indent_date`,
            `pt`.`indent_version` as `indent_version`,
            case
                `pom`.`purchase_order_status` when 'A' then 'Approved'
                when 'R' then 'Rejected'
                when 'X' then 'Canceled'
                else 'Pending'
            end as `purchase_order_status_desc`,
            `vp`.`department_name` as `department_name`,
            `sdp`.`department_name` as `sub_department_name`,
            case
                `pt`.`purchase_order_item_status` when 'A' then 'Approved'
                when 'R' then 'Rejected'
                when 'I' then 'Partial Receipt'
                when 'C' then 'Completed'
                when 'X' then 'Canceled'
                when 'Z' then 'Pree Closed'
                when 'B' then 'Bill Booked'
                when 'Z' then 'Pree Closed'
                else 'Pending'
            end as `purchase_order_item_status_desc`,
            case
                `pom`.`purchase_order_mail_sent_status` when 'S' then 'Email Sent'
                when 'F' then 'Email Failed'
                else 'Email Pending'
            end as `purchase_order_mail_sent_status_desc`,
            `pom`.`supplier_name` as `supplier_name`,
            `pom`.`state_name` as `supplier_state_name`,
            `c`.`customer_name` as `customer_name`,
            `e1`.`employee_name` as `indented_by_name`,
            `e`.`employee_name` as `approved_by_name`,
            `pt`.`sr_no` as `sr_no`,
            `pt`.`so_sr_no` as `so_sr_no`,
            `p`.`product_type_name` as `product_type_name`,
            `p`.`product_type_short_name` as `product_type_short_name`,
            `p`.`product_type_group` as `product_type_group`,
            `rmfgsr`.`product_material_code` as `product_material_code`,
            `rmfgsr`.`product_material_name` as `product_material_name`,
            `pt`.`product_material_print_name` as `product_material_print_name`,
            `pt`.`product_material_tech_spect` as `product_material_tech_spect`,
            `pt`.`lead_time` as `lead_time`,
            coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id` and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `available_stock_quantity`,
            coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `pt`.`product_material_id` and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `available_stock_weight`,
            `rmfgsr`.`product_material_std_weight` as `product_material_std_weight`,
            `rmfgsr`.`product_material_stock_unit_name` as `product_material_stock_unit_name`,
            `pp`.`product_packing_name` as `product_packing_name`,
            `pp`.`quantity_per_packing` as `quantity_per_packing`,
            `hsn`.`hsn_sac_type` as `hsn_sac_type`,
            `hsn`.`hsn_sac_code` as `hsn_sac_code`,
            `hsn`.`hsn_sac_description` as `hsn_sac_description`,
            `hsn`.`hsn_sac_rate` as `hsn_sac_rate`,
            `rmfgsr`.`product_material_make_name` as `product_make_name`,
            `rmfgsr`.`product_material_category1_name` as `product_category1_name`,
            `rmfgsr`.`product_material_category2_name` as `product_category2_name`,
            `rmfgsr`.`product_material_category3_name` as `product_category3_name`,
            `rmfgsr`.`product_material_category4_name` as `product_category4_name`,
            `rmfgsr`.`product_material_category5_name` as `product_category5_name`,
            `rmfgsr`.`product_material_type_name` as `product_material_type_name`,
            `rmfgsr`.`product_material_grade_name` as `product_material_grade_name`,
            `rmfgsr`.`product_material_shape_name` as `product_material_shape_name`,
            `rmfgsr`.`product_material_oem_part_code` as `product_material_oem_part_code`,
            `rmfgsr`.`product_material_our_part_code` as `product_material_our_part_code`,
            `rmfgsr`.`product_material_drawing_no` as `product_material_drawing_no`,
            `rmfgsr`.`product_material_hsn_sac_code` as `product_material_hsn_sac_code`,
            `rmfgsr`.`godown_name` as `godown_name`,
            `rmfgsr`.`godown_short_name` as `godown_short_name`,
            `rmfgsr`.`godown_area` as `godown_area`,
            `rmfgsr`.`godown_section_name` as `godown_section_name`,
            `rmfgsr`.`godown_section_short_name` as `godown_section_short_name`,
            `rmfgsr`.`godown_section_area` as `godown_section_area`,
            `rmfgsr`.`godown_section_beans_name` as `godown_section_beans_name`,
            `rmfgsr`.`godown_section_beans_short_name` as `godown_section_beans_short_name`,
            `rmfgsr`.`godown_section_beans_area` as `godown_section_beans_area`,
            `pt`.`product_material_po_quantity_qunital` as `product_material_po_quantity_qunital`,
            `pt`.`product_material_po_quantity` as `product_material_po_quantity`,
            `pt`.`product_material_conversion_factor` as `product_material_conversion_factor`,
            `pt`.`product_material_po_weight` as `product_material_po_weight`,
            `pt`.`product_material_po_approved_quantity` as `product_material_po_approved_quantity`,
            `pt`.`product_material_po_approved_weight` as `product_material_po_approved_weight`,
            `pt`.`product_material_po_rejected_quantity` as `product_material_po_rejected_quantity`,
            `pt`.`product_material_po_rejected_weight` as `product_material_po_rejected_weight`,
            `pt`.`customer_id` as `customer_id`,
            `pt`.`customer_order_no` as `customer_order_no`,
            `pt`.`customer_order_Date` as `customer_order_Date`,
            `pt`.`pree_closed_quantity` as `pree_closed_quantity`,
            `pt`.`pree_closed_weight` as `pree_closed_weight`,
            `pt`.`pre_closed_remark` as `pre_closed_remark`,
            `pt`.`purchase_return_quantity` as `purchase_return_quantity`,
            `pt`.`purchase_return_weight` as `purchase_return_weight`,
            `pt`.`material_rate` as `material_rate`,
            `pt`.`material_basic_amount` as `material_basic_amount`,
            `pt`.`material_discount_percent` as `material_discount_percent`,
            `pt`.`material_discount_amount` as `material_discount_amount`,
            `pt`.`material_taxable_amount` as `material_taxable_amount`,
            `pt`.`material_cgst_percent` as `material_cgst_percent`,
            `pt`.`material_cgst_total` as `material_cgst_total`,
            `pt`.`material_sgst_percent` as `material_sgst_percent`,
            `pt`.`material_sgst_total` as `material_sgst_total`,
            `pt`.`material_igst_percent` as `material_igst_percent`,
            `pt`.`material_igst_total` as `material_igst_total`,
            `pt`.`material_total_amount` as `material_total_amount`,
            `pt`.`material_schedule_date` as `material_schedule_date`,
            `pt`.`material_freight_amount` as `material_freight_amount`,
            `pt`.`pending_quantity` as `pending_quantity`,
            `pt`.`pending_weight` as `pending_weight`,
            `pt`.`excess_quantity` as `excess_quantity`,
            `pt`.`excess_weight` as `excess_weight`,
            `pt`.`production_issue_quantity` as `production_issue_quantity`,
            `pt`.`production_issue_weight` as `production_issue_weight`,
            `pt`.`production_issue_return_quantity` as `production_issue_return_quantity`,
            `pt`.`production_issue_return_weight` as `production_issue_return_weight`,
            `pt`.`production_issue_rejection_quantity` as `production_issue_rejection_quantity`,
            `pt`.`production_issue_rejection_weight` as `production_issue_rejection_weight`,
            `pt`.`assembly_production_issue_quantity` as `assembly_production_issue_quantity`,
            `pt`.`assembly_production_issue_weight` as `assembly_production_issue_weight`,
            `pt`.`sales_quantity` as `sales_quantity`,
            `pt`.`sales_weight` as `sales_weight`,
            `pt`.`sales_return_quantity` as `sales_return_quantity`,
            `pt`.`sales_return_weight` as `sales_return_weight`,
            `pt`.`sales_rejection_quantity` as `sales_rejection_quantity`,
            `pt`.`sales_rejection_weight` as `sales_rejection_weight`,
            `pt`.`transfer_issue_quantity` as `transfer_issue_quantity`,
            `pt`.`transfer_issue_weight` as `transfer_issue_weight`,
            `pt`.`transfer_receipt_quantity` as `transfer_receipt_quantity`,
            `pt`.`transfer_receipt_weight` as `transfer_receipt_weight`,
            `pt`.`outsources_out_quantity` as `outsources_out_quantity`,
            `pt`.`outsources_out_weight` as `outsources_out_weight`,
            `pt`.`outsources_in_quantity` as `outsources_in_quantity`,
            `pt`.`outsources_in_weight` as `outsources_in_weight`,
            `pt`.`outsources_rejection_quantity` as `outsources_rejection_quantity`,
            `pt`.`outsources_rejection_weight` as `outsources_rejection_weight`,
            `pt`.`loan_receipt_quantity` as `loan_receipt_quantity`,
            `pt`.`loan_receipt_weight` as `loan_receipt_weight`,
            `pt`.`loan_issue_quantity` as `loan_issue_quantity`,
            `pt`.`loan_issue_weight` as `loan_issue_weight`,
            `pt`.`cancel_quantity` as `cancel_quantity`,
            `pt`.`cancel_weight` as `cancel_weight`,
            `pt`.`difference_quantity` as `difference_quantity`,
            `pt`.`difference_weight` as `difference_weight`,
            `pt`.`material_po_approval_remark` as `material_po_approval_remark`,
            `pom`.`purchase_order_status` as `purchase_order_status`,
            `pt`.`purchase_order_item_status` as `purchase_order_item_status`,
            `pt`.`grn_item_status` as `grn_item_status`,
            `pom`.`purchase_order_mail_sent_status` as `purchase_order_mail_sent_status`,
            `pt`.`approved_date` as `approved_date`,
            `pt`.`remark` as `remark`,
            `cm`.`company_legal_name` as `company_name`,
            `cb`.`company_branch_name` as `company_branch_name`,
            `cb`.`branch_cell_no` as `company_cell_no`,
            `cb`.`branch_phone_no` as `company_phone_no`,
            `cb`.`branch_EmailId` as `company_EmailId`,
            `cb`.`branch_website` as `company_website`,
            `cb`.`branch_gst_no` as `company_gst_no`,
            `cb`.`branch_pan_no` as `company_pan_no`,
            `cs`.`state_name` as `company_state`,
            `cb`.`branch_pincode` as `company_pincode`,
            `cb`.`branch_address1` as `company_address1`,
            `pt`.`financial_year` as `financial_year`,
            `pom`.`city_name` as `city_name`,
            case
                `pt`.`is_active` when 1 then 'Active'
                else 'In Active'
            end as `Active`,
            case
                `pt`.`is_delete` when 1 then 'Yes'
                else 'No'
            end as `Deleted`,
            `pt`.`is_active` as `is_active`,
            `pt`.`is_delete` as `is_delete`,
            `pt`.`created_by` as `created_by`,
            `pt`.`created_on` as `created_on`,
            `pt`.`modified_by` as `modified_by`,
            `pt`.`modified_on` as `modified_on`,
            `pt`.`deleted_by` as `deleted_by`,
            `pt`.`deleted_on` as `deleted_on`,
            `pt`.`company_id` as `company_id`,
            `pt`.`company_branch_id` as `company_branch_id`,
            `pt`.`purchase_order_type_id` as `purchase_order_type_id`,
            `pt`.`purchase_order_master_transaction_id` as `purchase_order_master_transaction_id`,
            `pt`.`purchase_order_details_transaction_id` as `purchase_order_details_transaction_id`,
            `pom`.`supplier_id` as `supplier_id`,
            `pom`.`supplier_state_id` as `supplier_state_id`,
            `pom`.`supplier_city_id` as `supplier_city_id`,
            `pom`.`supplier_contacts_ids` as `supplier_contacts_ids`,
            `pom`.`expected_branch_id` as `expected_branch_id`,
            `pom`.`expected_branch_state_id` as `expected_branch_state_id`,
            `pom`.`expected_branch_city_id` as `expected_branch_city_id`,
            `pt`.`cost_center_id` as `cost_center_id`,
            `rmfgsr`.`product_material_type_id` as `product_material_type_id`,
            `rmfgsr`.`product_material_grade_id` as `product_material_grade_id`,
            `rmfgsr`.`product_material_shape_id` as `product_material_shape_id`,
            `rmfgsr`.`product_material_stock_unit_id` as `product_material_stock_unit_id`,
            `rmfgsr`.`product_material_category1_id` as `product_category1_id`,
            `rmfgsr`.`product_material_category2_id` as `product_category2_id`,
            `rmfgsr`.`product_material_category3_id` as `product_category3_id`,
            `rmfgsr`.`product_material_category4_id` as `product_category4_id`,
            `rmfgsr`.`product_material_category5_id` as `product_category5_id`,
            `rmfgsr`.`godown_id` as `godown_id`,
            `rmfgsr`.`godown_section_id` as `godown_section_id`,
            `rmfgsr`.`godown_section_beans_id` as `godown_section_beans_id`,
            `pt`.`product_material_id` as `product_material_id`,
            `pt`.`product_material_unit_id` as `product_material_unit_id`,
            `pt`.`product_material_packing_id` as `product_material_packing_id`,
            `pt`.`product_material_hsn_code_id` as `product_material_hsn_code_id`,
            `pt`.`department_id` as `department_id`,
            `pt`.`sub_department_id` as `sub_department_id`,
            `pt`.`indented_by_id` as `indented_by_id`,
            `pt`.`approved_by_id` as `approved_by_id`,
            coalesce((select sum(`ptdetails`.`product_material_po_approved_quantity`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `pt`.`indent_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id` and `pt`.`indent_no` <> ''), 0) as `prev_po_quantity`,
            coalesce((select sum(`ptdetails`.`product_material_po_approved_weight`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `pt`.`indent_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id` and `pt`.`indent_no` <> ''), 0) as `prev_po_weight`,
            coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `prev_grn_quantity`,
            coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `pt`.`company_id`), 0) as `prev_grn_weight`
        from
            (((((((((((((`pt_purchase_order_details` `pt`
        left join `ptv_purchase_order_master_summary` `pom` on
            (`pom`.`company_branch_id` = `pt`.`company_branch_id`
                and `pom`.`company_id` = `pt`.`company_id`
                and `pom`.`purchase_order_no` = `pt`.`purchase_order_no`
                and `pom`.`purchase_order_version` = `pt`.`purchase_order_version`
                and `pom`.`is_delete` = 0))
        left join `cm_company` `cm` on
            (`cm`.`company_id` = `pt`.`company_id`
                and `cm`.`is_delete` = 0))
        left join `cm_company_branch` `cb` on
            (`cb`.`company_branch_id` = `pt`.`company_branch_id`
                and `cb`.`is_delete` = 0))
        left join `cm_state` `cs` on
            (`cs`.`state_id` = `cb`.`branch_state_id`
                and `cs`.`state_id` = 0))
        left join `sm_product_type` `p` on
            (`p`.`product_type_id` = `pt`.`purchase_order_type_id`
                and `p`.`is_delete` = 0))
        left join `smv_product_rm_fg_sr` `rmfgsr` on
            (`rmfgsr`.`product_material_id` = `pt`.`product_material_id`))
        left join `sm_product_packing` `pp` on
            (`pp`.`product_packing_id` = `pt`.`product_material_packing_id`))
        left join `cm_hsn_sac` `hsn` on
            (`hsn`.`hsn_sac_id` = `pt`.`product_material_hsn_code_id`))
        left join `cm_department` `vp` on
            (`vp`.`department_id` = `pt`.`department_id`))
        left join `cm_employee` `e` on
            (`e`.`employee_id` = `pt`.`approved_by_id`))
        left join `cm_employee` `e1` on
            (`e1`.`employee_id` = `pt`.`indented_by_id`
                and `e1`.`company_id` = `pt`.`company_id`))
        left join `cm_customer` `c` on
            (`c`.`customer_id` = `pt`.`customer_id`))
        left join `cm_department` `sdp` on
            (`sdp`.`department_id` = `pt`.`sub_department_id`))
        where
            `pt`.`is_delete` = 0;



INSERT INTO am_modules_forms_user_access (company_id,company_branch_id,user_type,user_id,modules_forms_id,module_id,sub_module_id,all_access,read_access,add_access,modify_access,delete_access,approve_access,special_access,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,access_control,user_code,company_access) VALUES
	 (1,1,'Employees','1',678,1,8,1,1,1,1,1,1,1,1,0,'dakshabhiadmin','2024-11-25 07:12:28',NULL,'2024-11-25 07:12:28',NULL,NULL,'Y:Y:Y:Y:Y:Y:Y','S10077','0');



