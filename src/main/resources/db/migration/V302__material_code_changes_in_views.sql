-- erp.stv_indent_details source

create or replace
algorithm = UNDEFINED view `stv_indent_details` as
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
        when 'P' then 'Pending'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'PreClosed'
        else 'Pending'
    end as `indent_item_status_desc`,
    case
        `st`.`po_item_status` when 'POA' then 'Purchse Order Approved'
        when 'O' then 'Purchse Order Genreated'
        when 'IPO' then 'Partial PO Genreated'
        else 'Pending'
    end as `po_item_status_desc`,
    case
        `st`.`grn_item_status` when 'G' then 'Goods Receipts'
        when 'IG' then 'Partial Goods Receipts'
        else 'Pending'
    end as `grn_item_status_desc`,
    case
        `st`.`issue_item_status` when 'I' then 'Partial Issue'
        else 'Pending'
    end as `issue_item_status_desc`,
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
        when 'P' then 'Pending'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'PreClosed'
        when 'O' then 'Purchse Order Genreated'
        when 'IPO' then 'Partial PO Genreated'
        when 'POA' then 'Purchse Order Approved'
        when 'G' then 'Goods Receipts'
        when 'IG' then 'Partial Goods Receipts'
        when 'I' then 'Partial Issue'
        else 'Pending'
    end as `indent_status_desc`,
    case
        `v`.`po_status` when 'A' then 'Approved'
        when 'P' then 'Pending'
        when 'R' then 'Rejected'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'PreClosed'
        when 'O' then 'Purchse Order Genreated'
        when 'IPO' then 'Partial PO Genreated'
        when 'POA' then 'Purchse Order Approved'
        when 'G' then 'Goods Receipts'
        when 'IG' then 'Partial Goods Receipts'
        when 'I' then 'Partial Issue'
        else 'Pending'
    end as `po_status_desc`,
    `st`.`customer_order_no` as `customer_order_no`,
    `st`.`so_sr_no` as `so_sr_no`,
    `st`.`sales_order_details_transaction_id` as `sales_order_details_transaction_id`,
    `st`.`remark` as `remark`,
    `st`.`preclosed_remark` as `preclosed_remark`,
    `st`.`product_fg_material_quantity` as `product_fg_material_quantity`,
    `st`.`product_fg_material_weight` as `product_fg_material_weight`,
    `child`.`product_rm_code` as `product_material_code`,
    `child`.`product_rm_name` as `product_rm_name`,
    `child`.`product_rm_tech_spect` as `product_rm_tech_spect`,
    `u`.`product_unit_name` as `product_rm_stock_unit_name`,
    `cat1`.`product_category1_name` as `product_category1_name`,
    `cat2`.`product_category2_name` as `product_category2_name`,
    `child`.`product_rm_oem_part_code` as `product_rm_oem_part_code`,
    `child`.`product_rm_our_part_code` as `product_rm_our_part_code`,
    `comm`.`product_rm_std_weight` as `product_rm_std_weight`,
    `pck`.`product_packing_name` as `product_rm_packing_name`,
    `st`.`product_material_quantity` as `product_material_quantity`,
    `st`.`product_material_weight` as `product_material_weight`,
    `st`.`lead_time` as `lead_time`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`company_id` = `st`.`company_id` and `ptdetails`.`is_delete` = 0), 0) as `product_material_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`company_id` = `st`.`company_id` and `ptdetails`.`is_delete` = 0), 0) as `product_material_stock_weight`,
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
    `st`.`product_material_preclosed_quantity` as `product_material_preclosed_quantity`,
    `st`.`product_material_preclosed_weight` as `product_material_preclosed_weight`,
    `st`.`approval_remark` as `approval_remark`,
    `e3`.`employee_name` as `issued_by_name`,
    `pty`.`product_type_short_name` as `indent_type_short_name`,
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
    `v`.`Indent_type` as `indent_type`,
    `v`.`indent_status` as `indent_status`,
    `v`.`po_status` as `po_status`,
    `v`.`grn_status` as `grn_status`,
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
    `child`.`product_rm_hsn_sac_code_id` as `product_rm_hsn_sac_code_id`,
    `child`.`product_rm_packing_id` as `product_rm_packing_id`,
    `child`.`product_category1_id` as `product_category1_id`,
    `tech`.`product_category2_id` as `product_category2_id`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_quantity`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `st`.`indent_no` and `ptdetails`.`product_material_id` = `st`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `prev_po_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_weight`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `st`.`indent_no` and `ptdetails`.`product_material_id` = `st`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `st`.`company_id`), 0) as `prev_po_weight`
from
    ((((((((((((((((((`st_indent_details` `st`
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
left join `sm_product_rm` `child` on
    (`child`.`product_rm_id` = `st`.`product_material_id`
        and `child`.`is_delete` = 0))
left join `sm_product_unit` `u` on
    (`child`.`product_rm_id` = `st`.`product_material_id`
        and `u`.`product_unit_id` = `child`.`product_rm_stock_unit_id`
        and `u`.`is_delete` = 0))
left join `sm_product_category1` `cat1` on
    (`child`.`product_rm_id` = `st`.`product_material_id`
        and `cat1`.`product_category1_id` = `child`.`product_category1_id`
        and `cat1`.`is_delete` = 0))
left join `sm_product_rm_commercial` `comm` on
    (`comm`.`product_rm_id` = `st`.`product_material_id`
        and `comm`.`is_delete` = 0))
left join `sm_product_rm_technical` `tech` on
    (`tech`.`product_rm_id` = `st`.`product_material_id`
        and `tech`.`is_delete` = 0))
left join `sm_product_category2` `cat2` on
    (`tech`.`product_rm_id` = `st`.`product_material_id`
        and `cat2`.`product_category2_id` = `tech`.`product_category2_id`
        and `cat2`.`is_delete` = 0))
left join `sm_product_packing` `pck` on
    (`child`.`product_rm_id` = `st`.`product_material_id`
        and `pck`.`product_packing_id` = `child`.`product_rm_packing_id`
        and `pck`.`is_delete` = 0))
left join `sm_product_type` `pty` on
    (`child`.`product_rm_id` = `st`.`product_material_id`
        and `pty`.`product_type_id` = `child`.`product_type_id`
        and `pty`.`is_delete` = 0))
left join `fm_cost_center` `fm` on
    (`fm`.`company_id` = `st`.`company_id`
        and `fm`.`cost_center_id` = `st`.`cost_center_id`))
left join `cm_customer` `cs` on
    (`cs`.`customer_id` = `v`.`customer_id`))
where
    `st`.`is_delete` = 0;


    -- erp.stv_indent_details_rpt source

    create or replace
    algorithm = UNDEFINED view `stv_indent_details_rpt` as
    select
        concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') as `indent_no`,
        concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') as `indent_date`,
        concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
        concat(ifnull(`v`.`indent_priority_desc`, ''), ':Priority:Y:H:(Low-Priority,High-Priority)') as `indent_priority_desc`,
        concat(ifnull(`v`.`indent_item_status_desc`, ''), ':Indent Item Status:Y:H:(Pending,Approved,Canceled,Rejected,Completed,Cancel)') as `indent_item_status_desc`,
        concat(ifnull(`v`.`indent_status_desc`, ''), ':Indent Status:Y:H:(Pending,Approved,Canceled,Rejected,Completed,Cancel)') as `indent_status_desc`,
        concat(ifnull(`v`.`po_item_status_desc`, ''), ':PO item Status:Y:H:(Pending,Purchse Order Genreated,Partial PO Genreated,Purchse Order Approved,Cancel)') as `po_item_status_desc`,
        concat(ifnull(`v`.`po_status_desc`, ''), ':PO Status:Y:H:(Pending,Purchse Order Genreated,Partial PO Genreated,Purchse Order Approved,Cancel)') as `po_status_desc`,
        concat(ifnull(`v`.`grn_item_status_desc`, ''), ':GRN item Status:Y:H:(Pending, Goods Receipts,Partial Receipt,GRN Done)') as `grn_item_status_desc`,
        concat(ifnull(`v`.`department_name`, ''), ':Department:Y:C:cmv_department:F') as `department_name`,
        concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') as `sub_department_name`,
        concat(ifnull(`v`.`indent_type`, ''), ':Indent Type:Y:C:smv_product_type:F') as `indent_type`,
        concat(ifnull(`v`.`product_rm_name`, ''), ':Material name:Y:T') as `product_rm_name`,
        concat(ifnull(`v`.`product_material_code`, ''), ':Material Code:Y:T') as `product_material_code`,
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
        concat(ifnull(`v`.`product_material_preclosed_quantity`, ''), ':Material PreClose Weight:O:N:') as `product_material_preclosed_quantity`,
        concat(ifnull(`v`.`product_material_preclosed_weight`, ''), ':Material PreClose Weight:O:N:') as `product_material_preclosed_weight`,
        concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer_summary:F') as `customer_name`,
        concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:C:stv_indent_master_summary:O') as `customer_order_no`,
        concat(ifnull(`v`.`customer_order_date`, ''), ':Customer Order Date:Y:D:') as `customer_order_date`,
        concat(ifnull(`v`.`indented_by_name`, ''), ':Indent By:Y:T') as `indented_by_name`,
        concat(ifnull(`v`.`approved_by_name`, ''), ':Indent Approved By:Y:T') as `approved_by_name`,
        concat(ifnull(`v`.`product_rm_tech_spect`, ''), ':Material Tech spec:O:N:') as `product_rm_tech_spect`,
        concat(ifnull(`v`.`product_rm_oem_part_code`, ''), ':Material Oem Part Code:O:N:') as `product_rm_oem_part_code`,
        concat(ifnull(`v`.`product_rm_our_part_code`, ''), ':Material Oem Part Code:O:N:') as `product_rm_our_part_code`,
        concat(ifnull(`v`.`product_category1_name`, ''), ':Product Category1 Name:O:N:') as `product_category1_name`,
        concat(ifnull(`v`.`product_category2_name`, ''), ':Product Category2 Name:O:N:') as `product_category2_name`,
        concat(ifnull(`v`.`lead_time`, ''), ':Lead Time:O:N:') as `lead_time`,
        concat(ifnull(`v`.`issued_by_name`, ''), ':Issued By:Y:T') as `issued_by_name`,
        concat(ifnull(`v`.`issued_date`, ''), ':Issued Date:Y:D:') as `issued_date`,
        concat(ifnull(`v`.`indent_item_status`, ''), ':Indent Item Status:O:N:') as `indent_item_status`,
        concat(ifnull(`v`.`po_item_status`, ''), ':GRN Item Status:O:N:') as `po_item_status`,
        concat(ifnull(`v`.`grn_item_status`, ''), ':GRN Item Status:O:N:') as `grn_item_status`,
        concat(ifnull(`v`.`indent_status`, ''), ':Indent Status:O:N:') as `indent_status`,
        concat(ifnull(`v`.`po_status`, ''), ':Indent Status:O:N:') as `po_status`,
        concat(ifnull(`v`.`grn_status`, ''), ':Indent Status:O:N:') as `grn_status`,
        concat(ifnull(`v`.`indent_source`, ''), ':Indent Source:O:N:') as `indent_source`,
        concat(ifnull(`v`.`remark`, ''), 'remark:O:N:') as `remark`,
        concat(ifnull(`v`.`preclosed_remark`, ''), 'preclosed_remark:O:N:') as `preclosed_remark`,
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
        concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') as `indent_version`,
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
        `rmfgsr`.`product_material_code` as `product_material_code`,
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



-- pashupati_erp_qa.ptv_goods_receipt_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `ptv_goods_receipt_details` AS
select
    `pt`.`goods_receipt_no` AS `goods_receipt_no`,
    `pt`.`goods_receipt_date` AS `goods_receipt_date`,
    `pt`.`goods_receipt_version` AS `goods_receipt_version`,
    case
        `pt`.`grn_item_status` when 'G' then 'GRN Done'
        when 'Q' then 'QC Done'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        when 'B' then 'Bill Booked'
        else 'GRN'
    end AS `grn_item_status_desc`,
    `d`.`department_name` AS `department_name`,
    `pt`.`purchase_order_no` AS `purchase_order_no`,
    `pt`.`purchase_order_date` AS `purchase_order_date`,
    `pt`.`purchase_order_version` AS `purchase_order_version`,
    case
        `po`.`purchase_order_item_status` when 'A' then 'Approved'
        when 'R' then 'Rejected'
        when 'I' then 'Partial Receipt'
        when 'C' then 'Completed'
        when 'X' then 'Canceled'
        when 'Z' then 'Pree Closed'
        else 'Pending'
    end AS `purchase_order_item_status_desc`,
    `cu`.`customer_name` AS `customer_name`,
    `pt`.`customer_order_no` AS `customer_order_no`,
    `pt`.`customer_order_Date` AS `customer_order_Date`,
    `grm`.`purchase_order_life` AS `purchase_order_life`,
    `grm`.`supplier_challan_no` AS `supplier_challan_no`,
    `grm`.`supplier_challan_date` AS `supplier_challan_date`,
    `sup`.`supp_branch_name` AS `supplier_name`,
    `ct`.`city_name` AS `supplier_city_name`,
    `ss`.`state_name` AS `supplier_state_name`,
    `pt`.`item_qa_by_id` AS `item_qa_by_id`,
    `pt`.`item_qa_date` AS `item_qa_date`,
    `pt`.`goods_receipt_type` AS `goods_receipt_type`,
    `pt`.`product_material_tech_spect` AS `product_material_tech_spect`,
    `pt`.`lead_time` AS `lead_time`,
    `pt`.`sr_no` AS `sr_no`,
    `pt`.`batch_no` AS `batch_no`,
    `po`.`purchase_order_item_status` AS `purchase_order_item_status`,
    `rm_fg`.`product_material_code` AS `product_material_code`,
    `rm_fg`.`product_material_name` AS `product_material_name`,
    `u`.`product_unit_name` AS `product_material_stock_unit_name`,
    `rm_fg`.`product_material_std_weight` AS `product_material_std_weight`,
    `rm_fg`.`product_material_category1_name` AS `product_category1_name`,
    `rm_fg`.`product_material_category2_name` AS `product_category2_name`,
    `pk`.`product_packing_name` AS `product_packing_name`,
    `hsn`.`hsn_sac_code` AS `hsn_sac_code`,
    `hsn`.`hsn_sac_rate` AS `hsn_sac_rate`,
    `pt`.`product_material_po_approved_quantity` AS `product_material_po_approved_quantity`,
    `pt`.`product_material_po_approved_weight` AS `product_material_po_approved_weight`,
    `pt`.`product_material_grn_quantity` AS `product_material_grn_quantity`,
    `pt`.`product_material_grn_weight` AS `product_material_grn_weight`,
    `pt`.`product_material_conversion_factor` AS `product_material_conversion_factor`,
    `pt`.`product_material_grn_rejected_quantity` AS `product_material_grn_rejected_quantity`,
    `pt`.`product_material_grn_rejected_weight` AS `product_material_grn_rejected_weight`,
    `rp`.`product_rejection_type` AS `product_rejection_type`,
    `rp`.`product_rejection_parameters_name` AS `product_rejection_parameters_name`,
    `rp`.`product_rejection_parameters_short_name` AS `product_rejection_parameters_short_name`,
    `pt`.`product_material_grn_accepted_quantity` AS `product_material_grn_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` AS `product_material_grn_accepted_weight`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_quantity`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) AS `prev_grn_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_grn_accepted_weight`) from `pt_goods_receipt_details` `ptdetails` where `ptdetails`.`purchase_order_no` = `pt`.`purchase_order_no` and `ptdetails`.`product_material_id` = `pt`.`product_material_id` and `ptdetails`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id` and `ptdetails`.`is_delete` = 0), 0) AS `prev_grn_weight`,
    `so`.`sales_order_no` AS `sales_order_no`,
    `pt`.`product_material_grn_accepted_quantity` AS `product_material_prev_accepted_quantity`,
    `pt`.`product_material_grn_accepted_weight` AS `product_material_prev_accepted_weight`,
    `pt`.`material_rate` AS `material_rate`,
    `pt`.`material_basic_amount` AS `material_basic_amount`,
    `pt`.`material_freight_amount` AS `material_freight_amount`,
    `pt`.`material_discount_percent` AS `material_discount_percent`,
    `pt`.`material_discount_amount` AS `material_discount_amount`,
    `pt`.`material_taxable_amount` AS `material_taxable_amount`,
    `pt`.`material_cgst_percent` AS `material_cgst_percent`,
    `pt`.`material_cgst_total` AS `material_cgst_total`,
    `pt`.`material_sgst_percent` AS `material_sgst_percent`,
    `pt`.`material_sgst_total` AS `material_sgst_total`,
    `pt`.`material_igst_percent` AS `material_igst_percent`,
    `pt`.`material_igst_total` AS `material_igst_total`,
    `pt`.`material_total_amount` AS `material_total_amount`,
    `pt`.`material_schedule_date` AS `material_schedule_date`,
    `pt`.`grn_item_status` AS `grn_item_status`,
    `pt`.`excess_quantity` AS `excess_quantity`,
    `pt`.`excess_weight` AS `excess_weight`,
    `pt`.`pree_closed_grn_quantity` AS `pree_closed_grn_quantity`,
    `pt`.`pree_closed_grn_weight` AS `pree_closed_grn_weight`,
    `pt`.`purchase_return_quantity` AS `purchase_return_quantity`,
    `pt`.`purchase_return_weight` AS `purchase_return_weight`,
    `pt`.`production_issue_quantity` AS `production_issue_quantity`,
    `pt`.`production_issue_weight` AS `production_issue_weight`,
    `pt`.`production_issue_return_quantity` AS `production_issue_return_quantity`,
    `pt`.`production_issue_return_weight` AS `production_issue_return_weight`,
    `pt`.`production_issue_rejection_quantity` AS `production_issue_rejection_quantity`,
    `pt`.`production_issue_rejection_weight` AS `production_issue_rejection_weight`,
    `pt`.`assembly_production_issue_quantity` AS `assembly_production_issue_quantity`,
    `pt`.`assembly_production_issue_weight` AS `assembly_production_issue_weight`,
    `pt`.`sales_quantity` AS `sales_quantity`,
    `pt`.`sales_weight` AS `sales_weight`,
    `pt`.`sales_return_quantity` AS `sales_return_quantity`,
    `pt`.`sales_return_weight` AS `sales_return_weight`,
    `pt`.`sales_rejection_quantity` AS `sales_rejection_quantity`,
    `pt`.`sales_rejection_weight` AS `sales_rejection_weight`,
    `pt`.`transfer_issue_weight` AS `transfer_issue_weight`,
    `pt`.`transfer_receipt_quantity` AS `transfer_receipt_quantity`,
    `pt`.`transfer_receipt_weight` AS `transfer_receipt_weight`,
    `pt`.`outsources_out_quantity` AS `outsources_out_quantity`,
    `pt`.`outsources_out_weight` AS `outsources_out_weight`,
    `pt`.`outsources_in_quantity` AS `outsources_in_quantity`,
    `pt`.`outsources_in_weight` AS `outsources_in_weight`,
    `pt`.`outsources_rejection_quantity` AS `outsources_rejection_quantity`,
    `pt`.`outsources_rejection_weight` AS `outsources_rejection_weight`,
    `pt`.`loan_receipt_quantity` AS `loan_receipt_quantity`,
    `pt`.`loan_receipt_weight` AS `loan_receipt_weight`,
    `pt`.`loan_issue_quantity` AS `loan_issue_quantity`,
    `pt`.`loan_issue_weight` AS `loan_issue_weight`,
    `pt`.`cancel_quantity` AS `cancel_quantity`,
    `pt`.`cancel_weight` AS `cancel_weight`,
    `pt`.`difference_quantity` AS `difference_quantity`,
    `pt`.`difference_weight` AS `difference_weight`,
    `pt`.`total_box_weight` AS `total_box_weight`,
    `pt`.`total_quantity_in_box` AS `total_quantity_in_box`,
    `pt`.`weight_per_box_item` AS `weight_per_box_item`,
    `pt`.`no_of_boxes` AS `no_of_boxes`,
    `pt`.`remark` AS `remark`,
    `c`.`company_legal_name` AS `company_name`,
    `cb`.`company_branch_name` AS `company_branch_name`,
    `pt`.`financial_year` AS `financial_year`,
    `pt`.`expiry_date` AS `expiry_date`,
    `po`.`indent_no` AS `indent_no`,
    `e1`.`employee_name` AS `indented_by_name`,
    `e2`.`employee_name` AS `approved_by_name`,
    `g`.`godown_name` AS `godown_name`,
    `gs`.`godown_section_name` AS `godown_section_name`,
    `gsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `pdt`.`product_type_group` AS `product_material_type_group`,
    `pdt`.`product_type_name` AS `product_type_name`,
    `pdt`.`product_type_short_name` AS `product_type_short_name`,
    `sdp`.`department_name` AS `sub_department_name`,
    `fpc`.`cost_center_name` AS `cost_center_name`,
    case
        when `pt`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `pt`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `pt`.`is_active` AS `is_active`,
    `pt`.`is_delete` AS `is_delete`,
    `pt`.`created_by` AS `created_by`,
    `pt`.`created_on` AS `created_on`,
    `pt`.`modified_by` AS `modified_by`,
    `pt`.`modified_on` AS `modified_on`,
    `pt`.`deleted_by` AS `deleted_by`,
    `pt`.`deleted_on` AS `deleted_on`,
    `pt`.`company_id` AS `company_id`,
    `pt`.`company_branch_id` AS `company_branch_id`,
    `pt`.`goods_receipt_details_transaction_id` AS `goods_receipt_details_transaction_id`,
    `pt`.`goods_receipt_master_transaction_id` AS `goods_receipt_master_transaction_id`,
    `pt`.`purchase_order_details_transaction_id` AS `purchase_order_details_transaction_id`,
    `pt`.`department_id` AS `department_id`,
    `pt`.`product_material_unit_id` AS `product_material_unit_id`,
    `pt`.`product_material_packing_id` AS `product_material_packing_id`,
    `pt`.`product_material_hsn_code_id` AS `product_material_hsn_code_id`,
    `pt`.`goods_receipt_type_id` AS `goods_receipt_type_id`,
    `pt`.`customer_id` AS `customer_id`,
    `pt`.`product_material_rejection_reason_id` AS `product_material_rejection_reason_id`,
    `grm`.`goods_receipt_status` AS `goods_receipt_status`,
    `grm`.`supplier_id` AS `supplier_id`,
    `grm`.`approved_by_id` AS `approved_by_id`,
    `grm`.`supplier_state_id` AS `supplier_state_id`,
    `grm`.`supplier_city_id` AS `supplier_city_id`,
    `grm`.`supplier_contacts_ids` AS `supplier_contacts_ids`,
    `grm`.`expected_branch_id` AS `expected_branch_id`,
    `grm`.`expected_branch_state_id` AS `expected_branch_state_id`,
    `grm`.`expected_branch_city_id` AS `expected_branch_city_id`,
    `sup`.`supp_branch_payment_term_id` AS `payment_term_id`,
    `grm`.`agent_id` AS `agent_id`,
    `pt`.`product_material_id` AS `product_material_id`,
    `pt`.`godown_id` AS `godown_id`,
    `pt`.`godown_section_id` AS `godown_section_id`,
    `pt`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `po`.`purchase_order_type_id` AS `product_type_id`,
    `rm_fg`.`product_material_category1_id` AS `product_category1_id`,
    `rm_fg`.`product_material_category2_id` AS `product_category2_id`,
    `po`.`indented_by_id` AS `indent_by_id`
    from
        (((((((((((((((((((((((`pt_goods_receipt_details` `pt`
    left join `cm_company` `c` on
        (`c`.`company_id` = `pt`.`company_id`
            and `pt`.`is_delete` = 0))
    left join `cm_company_branch` `cb` on
        (`cb`.`company_branch_id` = `pt`.`company_branch_id`
            and `cb`.`company_id` = `pt`.`company_id`
            and `pt`.`is_delete` = 0))
    left join `pt_goods_receipt_master` `grm` on
        (`grm`.`goods_receipt_master_transaction_id` = `pt`.`goods_receipt_master_transaction_id`
            and `grm`.`company_id` = `pt`.`company_id`
            and `grm`.`company_branch_id` = `pt`.`company_branch_id`))
    left join `cm_supplier_branch` `sup` on
        (`sup`.`supp_branch_id` = `grm`.`supplier_id`
            and `pt`.`is_delete` = 0))
    left join `cm_city` `ct` on
        (`ct`.`city_id` = `grm`.`supplier_city_id`))
    left join `cm_state` `ss` on
        (`ss`.`state_id` = `grm`.`supplier_state_id`))
    left join `pt_purchase_order_details` `po` on
        (`po`.`company_branch_id` = `pt`.`company_branch_id`
            and `po`.`company_id` = `pt`.`company_id`
            and `po`.`product_material_id` = `pt`.`product_material_id`
            and `po`.`purchase_order_no` = `pt`.`purchase_order_no`
            and `po`.`purchase_order_version` = `pt`.`purchase_order_version`
            and `po`.`purchase_order_details_transaction_id` = `pt`.`purchase_order_details_transaction_id`))
    left join `sm_product_unit` `u` on
        (`u`.`product_unit_id` = `pt`.`product_material_unit_id`
            and `pt`.`is_delete` = 0))
    left join `smv_product_rm_fg_sr` `rm_fg` on
        (`rm_fg`.`product_material_id` = `pt`.`product_material_id`
            and `rm_fg`.`is_delete` = 0))
    left join `sm_product_type` `pdt` on
        (`pdt`.`product_type_id` = `po`.`purchase_order_type_id`
            and `pdt`.`is_delete` = 0))
    left join `sm_product_packing` `pk` on
        (`pk`.`product_packing_id` = `pt`.`product_material_packing_id`
            and `pk`.`is_delete` = 0))
    left join `cm_hsn_sac` `hsn` on
        (`hsn`.`hsn_sac_id` = `pt`.`product_material_hsn_code_id`
            and `hsn`.`is_delete` = 0))
    left join `cm_employee` `e1` on
        (`e1`.`employee_id` = `po`.`indented_by_id`
            and `e1`.`is_delete` = 0))
    left join `cm_employee` `e2` on
        (`e2`.`employee_id` = `grm`.`approved_by_id`
            and `e2`.`is_delete` = 0))
    left join `cm_customer` `cu` on
        (`cu`.`customer_id` = `po`.`customer_id`
            and `cu`.`is_delete` = 0))
    left join `cm_department` `d` on
        (`d`.`department_id` = `pt`.`department_id`
            and `d`.`is_delete` = 0))
    left join `sm_product_rejection_parameters` `rp` on
        (`rp`.`company_id` = `pt`.`company_id`
            and `rp`.`product_rejection_parameters_id` = `pt`.`product_material_rejection_reason_id`))
    left join `mt_sales_order_master_trading` `so` on
        (`so`.`company_id` = `pt`.`company_id`
            and `so`.`customer_order_no` = `pt`.`customer_order_no`
            and `so`.`is_delete` = 0))
    left join `cm_godown` `g` on
        (`g`.`godown_id` = `pt`.`godown_id`))
    left join `cm_godown_section` `gs` on
        (`gs`.`godown_section_id` = `pt`.`godown_section_id`))
    left join `cm_godown_section_beans` `gsb` on
        (`gsb`.`godown_section_beans_id` = `pt`.`godown_section_beans_id`))
    left join `cm_department` `sdp` on
        (`sdp`.`department_id` = `po`.`sub_department_id`))
    left join `fm_cost_center` `fpc` on
        (`fpc`.`cost_center_id` = `po`.`cost_center_id`))
    where
        `pt`.`is_delete` = 0;