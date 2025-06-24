-- stv_indent_schedules source

create or REPLACE algorithm = UNDEFINED view `stv_indent_schedules` as
select
    `v`.`company_name` as `company_name`,
    `v`.`company_branch_name` as `company_branch_name`,
    `v`.`financial_year` as `financial_year`,
    `v`.`indent_no` as `indent_no`,
    `v`.`indent_date` as `indent_date`,
    `v`.`indent_version` as `indent_version`,
    `v`.`indent_type` as `indent_type`,
    `v`.`indent_source` as `indent_source`,
    `v`.`department_name` as `department_name`,
    `v`.`indented_by_name` as `indented_by_name`,
    `v`.`approved_by_name` as `approved_by_name`,
    `v`.`customer_name` as `customer_name`,
    `v`.`customer_order_no` as `customer_order_no`,
    `v`.`customer_order_date` as `customer_order_date`,
    `v`.`indent_status` as `indent_status`,
    `v`.`indent_status_desc` as `indent_status_desc`,
    `v`.`remark` as `remark`,
    `st`.`is_active` as `is_active`,
    `v`.`product_material_id` as `product_material_id`,
    `v`.`product_rm_name` as `product_rm_name`,
    `v`.`product_material_quantity` as `product_material_quantity`,
    `v`.`product_material_weight` as `product_material_weight`,
    `v`.`lead_time` as `lead_time`,
    `v`.`product_material_stock_quantity` as `product_material_stock_quantity`,
    `v`.`product_material_stock_weight` as `product_material_stock_weight`,
    `v`.`product_material_reserve_quantity` as `product_material_reserve_quantity`,
    `v`.`product_material_reserve_weight` as `product_material_reserve_weight`,
    `v`.`product_material_approved_quantity` as `product_material_approved_quantity`,
    `v`.`product_material_approved_weight` as `product_material_approved_weight`,
    `v`.`product_material_rejected_quantity` as `product_material_rejected_quantity`,
    `v`.`product_material_rejected_weight` as `product_material_rejected_weight`,
    `st`.`product_material_schedule_quantity` as `product_material_schedule_quantity`,
    `st`.`product_material_schedule_weight` as `product_material_schedule_weight`,
    `st`.`expected_schedule_date` as `expected_schedule_date`,
    `st`.`product_material_receipt_quantity` as `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` as `product_material_receipt_weight`,
    `st`.`material_receipt_date` as `material_receipt_date`,
    `st`.`material_quantity` as `material_quantity`,
    `st`.`material_weight` as `material_weight`,
    `st`.`product_material_unit_id` as `product_material_unit_id`,
    `st`.`so_sr_no` as `so_sr_no`,
    `st`.`sales_order_details_transaction_id` as `sales_order_details_transaction_id`,
    `v`.`issued_by_name` as `issued_by_name`,
    `v`.`issued_by_id` as `issued_by_id`,
    `v`.`issued_date` as `issued_date`,
    `st`.`indent_item_status` as `indent_item_status`,
    case
        `st`.`indent_item_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Cenceled'
        when 'A' then 'Approved'
        when 'I' then 'Partial Reciept'
        else 'Pending'
    end as `indent_item_status_desc`,
    `v`.`indent_no` as `field_name`,
    `v`.`indent_version` as `field_id`,
    `st`.`is_delete` as `is_delete`,
    `st`.`created_by` as `created_by`,
    `st`.`created_on` as `created_on`,
    `st`.`modified_by` as `modified_by`,
    `st`.`modified_on` as `modified_on`,
    `st`.`deleted_by` as `deleted_by`,
    `st`.`product_fg_id` as `product_fg_id`,
    `st`.`deleted_on` as `deleted_on`,
    `v`.`indent_master_id` as `indent_master_id`,
    `v`.`indent_details_id` as `indent_details_id`,
    `st`.`indent_schedule_id` as `indent_schedule_id`,
    `v`.`indent_type_id` as `indent_type_id`,
    `v`.`customer_id` as `customer_id`,
    `v`.`department_id` as `department_id`,
    `v`.`indented_by_id` as `indented_by_id`,
    `v`.`company_id` as `company_id`,
    `v`.`company_branch_id` as `company_branch_id`
from
    (`st_indent_schedules` `st`
left join `stv_indent_details` `v` on
    (`st`.`company_branch_id` = `v`.`company_branch_id`
        and `st`.`company_id` = `v`.`company_id`
        and `st`.`indent_no` = `v`.`indent_no`
        and `st`.`indent_version` = `v`.`indent_version`
        and `st`.`product_material_id` = `v`.`product_material_id`
        and `st`.`so_sr_no` = `v`.`so_sr_no`
        and `st`.`customer_order_no` = `v`.`customer_order_no`
        and `v`.`is_delete` = 0
        and (`st`.`product_fg_id` is null
            or `st`.`product_fg_id` = `v`.`product_fg_id`)))
where
    `st`.`is_delete` = 0;






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
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `smv_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `smv_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `product_material_stock_weight`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id`), 0) as `product_Rawmaterial_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id`), 0) as `product_Rawmaterial_stock_weight`,
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
        and `st`.`is_delete` = 0))
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