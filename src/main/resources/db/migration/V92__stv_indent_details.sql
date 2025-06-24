-- stv_indent_master_summary source

create or REPLACE algorithm = UNDEFINED view `stv_indent_master_summary` as
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
        `st`.`indent_status` when 'A' then 'Approved'
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



    -- stv_indent_master_summary_rpt source

    create or REPLACE algorithm = UNDEFINED view `stv_indent_master_summary_rpt` as
    select
        concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') as `indent_no`,
        concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') as `indent_date`,
        concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') as `indent_version`,
        concat(ifnull(`v`.`indent_priority_desc`, ''), ':Priority:Y:H:(Low-Priority,High-Priority)') as `indent_priority_desc`,
        concat(ifnull(`v`.`indent_type`, ''), ':Indent Type:Y:C:smv_product_type:F') as `indent_type`,
        concat(ifnull(`v`.`indent_status_desc`, ''), ':Indent Status:Y:H:(Pending,Approved,Rejected,Completed,Cenceled)') as `indent_status_desc`,
        concat(ifnull(`v`.`indent_source_desc`, ''), ':Indent Source:Y:H:(SO Based,Direct,Internal)') as `indent_source_desc`,
        concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') as `department_name`,
        concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
        concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer_summary:F') as `customer_name`,
        concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:C:stv_indent_master_summary:O') as `customer_order_no`,
        concat(ifnull(`v`.`customer_order_date`, ''), ':Customer Order Date:Y:D:') as `customer_order_date`,
        concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') as `expected_schedule_date`,
        concat(ifnull(`v`.`indented_by_name`, ''), ':Indent By:Y:T') as `indented_by_name`,
        concat(ifnull(`v`.`approved_by_name`, ''), ':Indent Approved By:Y:T') as `approved_by_name`,
        concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
        concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
        concat(ifnull(`v`.`company_name`, ''), ':Company Name:O:N:') as `company_name`,
        concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:O:N:') as `company_branch_name`,
        concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
        concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') as `Active`,
        concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
        concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
        concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
        concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
        concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
        concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
        concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
        concat(ifnull(`v`.`indent_source`, ''), ':Indent Source:O:N:') as `indent_source`,
        concat(ifnull(`v`.`indent_status`, ''), ':Indent Status:O:N:') as `indent_status`,
        concat(ifnull(`v`.`grn_status`, ''), ':GRN Status:O:N:') as `grn_status`,
        concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
        concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
        concat(ifnull(`v`.`indent_master_id`, ''), ':Indent Master Id:O:N:') as `indent_master_id`,
        concat(ifnull(`v`.`indent_type_id`, ''), ':Intdent Type Id:N:N:') as `indent_type_id`,
        concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') as `customer_id`,
        concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
        concat(ifnull(`v`.`indented_by_id`, ''), ':Indeneted By Id:N:N:') as `indented_by_id`,
        concat(ifnull(`v`.`indent_no`, ''), ':Indent No:N:N:') as `field_name`,
        concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:N:N:') as `field_id`
    from
        `stv_indent_master_summary` `v`
    limit 1;

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
    `child`.`product_material_category3_id` as `product_category3_id`,
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




-- stv_indent_details_rpt source

create or REPLACE algorithm = UNDEFINED view `stv_indent_details_rpt` as
select
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') as `indent_no`,
    concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') as `indent_date`,
    concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') as `indent_version`,
    concat(ifnull(`v`.`indent_priority_desc`, ''), ':Priority:Y:H:(Low-Priority,High-Priority)') as `indent_priority_desc`,
    concat(ifnull(`v`.`indent_type`, ''), ':Indent Type:Y:C:smv_product_type:F') as `indent_type`,
    concat(ifnull(`v`.`indent_item_status_desc`, ''), ':Indent Item Status:Y:H:(Pending,Approved,Rejected,Completed,Cancel)') as `indent_item_status_desc`,
    concat(ifnull(`v`.`department_name`, ''), ':Department:Y:C:cmv_department:F') as `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
    concat(ifnull(`v`.`product_rm_name`, ''), ':Material name:Y:T') as `product_rm_name`,
    concat(ifnull(`v`.`product_rm_stock_unit_name`, ''), ':Material Stock Unit Name:O:N:') as `product_rm_stock_unit_name`,
    concat(ifnull(`v`.`product_material_quantity`, ''), ':Material Quantity:O:N:') as `product_material_quantity`,
    concat(ifnull(`v`.`product_material_weight`, ''), ':Material Weight:O:N:') as `product_material_weight`,
    concat(ifnull(`v`.`product_material_stock_quantity`, ''), ':Material Stock Quantity:O:N:') as `product_material_stock_quantity`,
    concat(ifnull(`v`.`product_material_stock_weight`, ''), ':Material Stock Weight:O:N:') as `product_material_stock_weight`,
    concat(ifnull(`v`.`product_material_reserve_quantity`, ''), ':Material Reserve Quantity:O:N:') as `product_material_reserve_quantity`,
    concat(ifnull(`v`.`product_material_reserve_weight`, ''), ':Material Reserve Weight:O:N:') as `product_material_reserve_weight`,
    concat(ifnull(`v`.`product_material_approved_quantity`, ''), ':Material Approved Quantity:O:N:') as `product_material_approved_quantity`,
    concat(ifnull(`v`.`product_material_approved_weight`, ''), ':Material Approved Weight:O:N:') as `product_material_approved_weight`,
    concat(ifnull(`v`.`product_material_rejected_quantity`, ''), ':Material Rejected Quantity:O:N:') as `product_material_rejected_quantity`,
    concat(ifnull(`v`.`product_material_rejected_weight`, ''), ':Material Rejected Weight:O:N:') as `product_material_rejected_weight`,
    concat(ifnull(`v`.`product_material_return_quantity`, ''), ':Material Return Quantity:O:N:') as `product_material_return_quantity`,
    concat(ifnull(`v`.`product_material_return_weight`, ''), ':Material Return Weight:O:N:') as `product_material_return_weight`,
    concat(ifnull(`v`.`product_material_issue_quantity`, ''), ':Material Issue Quantity:O:N:') as `product_material_issue_quantity`,
    concat(ifnull(`v`.`product_material_issue_weight`, ''), ':Material Issue Weight:O:N:') as `product_material_issue_weight`,
    concat(ifnull(`v`.`product_material_grn_accepted_quantity`, ''), ':GRN Accepted Quantity:O:N:') as `product_material_grn_accepted_quantity`,
    concat(ifnull(`v`.`product_material_grn_accepted_weight`, ''), ':GRN Accepted Weight:O:N:') as `product_material_grn_accepted_weight`,
    concat(ifnull(`v`.`product_child_bom_quantity`, ''), ':Product Child Bom Quantity:O:N:') as `product_child_bom_quantity`,
    concat(ifnull(`v`.`product_child_bom_weight`, ''), ':Product Child Bom Weight:O:N:') as `product_child_bom_weight`,
    concat(ifnull(`v`.`product_fg_material_quantity`, ''), ':Fg Material Quantity:O:N:') as `product_fg_material_quantity`,
    concat(ifnull(`v`.`product_fg_material_weight`, ''), ':Fg Material Weight:O:N:') as `product_fg_material_weight`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer_summary:F') as `customer_name`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:C:stv_indent_master_summary:O') as `customer_order_no`,
    concat(ifnull(`v`.`customer_order_date`, ''), ':Customer Order Date:Y:D:') as `customer_order_date`,
    concat(ifnull(`v`.`indented_by_name`, ''), ':Indent By:Y:T') as `indented_by_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Indent Approved By:Y:T') as `approved_by_name`,
    concat(ifnull(`v`.`product_rm_tech_spect`, ''), ':Material Tech spec:O:N:') as `product_rm_tech_spect`,
    concat(ifnull(`v`.`product_rm_drawing_no`, ''), ':Material Drawing No:O:N:') as `product_rm_drawing_no`,
    concat(ifnull(`v`.`product_rm_oem_part_code`, ''), ':Material Oem Part Code:O:N:') as `product_rm_oem_part_code`,
    concat(ifnull(`v`.`product_rm_our_part_code`, ''), ':Material Oem Part Code:O:N:') as `product_rm_our_part_code`,
    concat(ifnull(`v`.`product_category1_name`, ''), ':Product Category1 Name:O:N:') as `product_category1_name`,
    concat(ifnull(`v`.`product_category2_name`, ''), ':Product Category2 Name:O:N:') as `product_category2_name`,
    concat(ifnull(`v`.`lead_time`, ''), ':Lead Time:O:N:') as `lead_time`,
    concat(ifnull(`v`.`issued_by_name`, ''), ':Issued By:Y:T') as `issued_by_name`,
    concat(ifnull(`v`.`issued_date`, ''), ':Issued Date:Y:D:') as `issued_date`,
    concat(ifnull(`v`.`indent_item_status`, ''), ':Indent Item Status:O:N:') as `indent_item_status`,
    concat(ifnull(`v`.`grn_item_status`, ''), ':GRN Item Status:O:N:') as `grn_item_status`,
    concat(ifnull(`v`.`indent_status`, ''), ':Indent Status:O:N:') as `indent_status`,
    concat(ifnull(`v`.`indent_source`, ''), ':Indent Source:O:N:') as `indent_source`,
    concat(ifnull(`v`.`remark`, ''), 'remark:O:N:') as `remark`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') as `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') as `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') as `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`product_fg_id`, ''), ':Product Fg Id:N:N:') as `product_fg_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Material Unit Id:N:N:') as `product_material_unit_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`product_type_id`, ''), ':Product Type Id:N:N:') as `product_type_id`,
    concat(ifnull(`v`.`product_category1_id`, ''), ':Product Category1 Id:N:N:') as `product_category1_id`,
    concat(ifnull(`v`.`product_category2_id`, ''), ':Product Category2 Id:N:N:') as `product_category2_id`,
    concat(ifnull(`v`.`product_make_id`, ''), ':Product Make Id:N:N:') as `product_make_id`,
    concat(ifnull(`v`.`product_material_type_id`, ''), ':Product Material Type Id:N:N:') as `product_material_type_id`,
    concat(ifnull(`v`.`product_material_grade_id`, ''), ':Product Material Grade Id:N:N:') as `product_material_grade_id`,
    concat(ifnull(`v`.`indent_master_id`, ''), ':Indent Master Id:O:N:') as `indent_master_id`,
    concat(ifnull(`v`.`indent_details_id`, ''), ':Indent Details Id:N:N:') as `indent_details_id`,
    concat(ifnull(`v`.`indent_type_id`, ''), ':Indent Type Id:N:N:') as `indent_type_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') as `customer_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') as `department_id`,
    concat(ifnull(`v`.`indented_by_id`, ''), ':Indented By Id:N:N:') as `indented_by_id`,
    concat(ifnull(`v`.`issued_by_id`, ''), ':Issued By Id:N:N:') as `issued_by_id`
from
    `stv_indent_details` `v`
limit 1;