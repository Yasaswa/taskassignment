-- ERP_PASHUPATI_PROD_1_0.stv_indent_master_summary_rpt source

create or replace
ALGORITHM = UNDEFINED VIEW `stv_indent_master_summary_rpt` AS
select
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') AS `indent_no`,
    concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') AS `indent_date`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') AS `approved_date`,
    concat(ifnull(`v`.`indent_priority_desc`, ''), ':Priority:Y:H:(Low-Priority,High-Priority)') AS `indent_priority_desc`,
    concat(ifnull(`v`.`indent_status_desc`, ''), ':Indent Status:Y:H:(Pending,Approved,Rejected,Completed,Cenceled)') AS `indent_status_desc`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') AS `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') AS `sub_department_name`,
    concat(ifnull(`v`.`indent_type`, ''), ':Indent Type:Y:C:smv_product_type:F') AS `indent_type`,
    concat(ifnull(`v`.`indent_source_desc`, ''), ':Indent Source:Y:H:(SO Based,Direct,Internal)') AS `indent_source_desc`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer_summary:F') AS `customer_name`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:C:stv_indent_master_summary:O') AS `customer_order_no`,
    concat(ifnull(`v`.`customer_order_date`, ''), ':Customer Order Date:Y:D:') AS `customer_order_date`,
    concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') AS `expected_schedule_date`,
    concat(ifnull(`v`.`indented_by_name`, ''), ':Indent By:Y:T') AS `indented_by_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Indent Approved By:Y:T') AS `approved_by_name`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') AS `remark`,
    concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') AS `indent_version`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:O:N:') AS `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:O:N:') AS `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') AS `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') AS `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') AS `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
    concat(ifnull(`v`.`indent_source`, ''), ':Indent Source:O:N:') AS `indent_source`,
    concat(ifnull(`v`.`indent_status`, ''), ':Indent Status:O:N:') AS `indent_status`,
    concat(ifnull(`v`.`grn_status`, ''), ':GRN Status:O:N:') AS `grn_status`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') AS `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(ifnull(`v`.`indent_master_id`, ''), ':Indent Master Id:O:N:') AS `indent_master_id`,
    concat(ifnull(`v`.`indent_type_id`, ''), ':Intdent Type Id:N:N:') AS `indent_type_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') AS `customer_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') AS `department_id`,
    concat(ifnull(`v`.`indented_by_id`, ''), ':Indeneted By Id:N:N:') AS `indented_by_id`,
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:N:N:') AS `field_name`,
    concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:N:N:') AS `field_id`
from
    `stv_indent_master_summary` `v`
limit 1;


-- ERP_PASHUPATI_PROD_1_0.stv_indent_details source

create or replace
ALGORITHM = UNDEFINED VIEW `stv_indent_details` AS
select
    `st`.`indent_no` AS `indent_no`,
    `st`.`indent_date` AS `indent_date`,
    `st`.`indent_version` AS `indent_version`,
    case
        `v`.`indent_priority` when 'H' then 'High-Priority'
        else 'Low-Priority'
    end AS `indent_priority_desc`,
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
    end AS `indent_item_status_desc`,
    case
        `v`.`indent_source` when 'S' then 'SO Based'
        when 'O' then 'Direct'
        else 'Internal'
    end AS `indent_source_desc`,
    `d`.`department_name` AS `department_name`,
    `sd`.`department_name` AS `sub_department_name`,
    `e1`.`employee_name` AS `indented_by_name`,
    `e2`.`employee_name` AS `approved_by_name`,
    `cs`.`customer_name` AS `customer_name`,
    `v`.`customer_order_Date` AS `customer_order_date`,
    `v`.`expected_schedule_date` AS `expected_schedule_date`,
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
    end AS `indent_status_desc`,
    `st`.`customer_order_no` AS `customer_order_no`,
    `st`.`so_sr_no` AS `so_sr_no`,
    `st`.`sales_order_details_transaction_id` AS `sales_order_details_transaction_id`,
    `st`.`remark` AS `remark`,
    `st`.`product_fg_material_quantity` AS `product_fg_material_quantity`,
    `st`.`product_fg_material_weight` AS `product_fg_material_weight`,
    `child`.`product_material_name` AS `product_rm_name`,
    `child`.`product_material_drawing_no` AS `product_rm_drawing_no`,
    `child`.`product_material_tech_spect` AS `product_rm_tech_spect`,
    `child`.`product_material_stock_unit_name` AS `product_rm_stock_unit_name`,
    `child`.`product_type_name` AS `product_type_name`,
    `child`.`product_material_category1_name` AS `product_category1_name`,
    `child`.`product_material_oem_part_code` AS `product_rm_oem_part_code`,
    `child`.`product_material_our_part_code` AS `product_rm_our_part_code`,
    `child`.`product_material_category2_name` AS `product_category2_name`,
    `child`.`product_material_std_weight` AS `product_rm_std_weight`,
    `child`.`product_material_packing_name` AS `product_rm_packing_name`,
    `child`.`product_material_hsn_sac_code` AS `product_rm_hsn_sac_code`,
    `child`.`product_material_hsn_sac_rate` AS `product_rm_hsn_sac_rate`,
    `st`.`product_material_quantity` AS `product_material_quantity`,
    `st`.`product_material_weight` AS `product_material_weight`,
    `st`.`lead_time` AS `lead_time`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `smv_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) AS `product_material_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `smv_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id` and `ptdetails`.`company_id` = `st`.`company_id`), 0) AS `product_material_stock_weight`,
    coalesce((select sum(`ptdetails`.`closing_balance_quantity`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id`), 0) AS `product_Rawmaterial_stock_quantity`,
    coalesce((select sum(`ptdetails`.`closing_balance_weight`) from `sm_product_rm_stock_summary` `ptdetails` where `ptdetails`.`product_rm_id` = `st`.`product_material_id`), 0) AS `product_Rawmaterial_stock_weight`,
    `st`.`product_material_reserve_quantity` AS `product_material_reserve_quantity`,
    `st`.`product_material_reserve_weight` AS `product_material_reserve_weight`,
    `st`.`product_material_approved_quantity` AS `product_material_approved_quantity`,
    `st`.`product_material_approved_weight` AS `product_material_approved_weight`,
    `st`.`product_material_issue_quantity` AS `product_material_issue_quantity`,
    `st`.`product_material_issue_weight` AS `product_material_issue_weight`,
    `st`.`product_material_receipt_quantity` AS `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` AS `product_material_receipt_weight`,
    `st`.`product_material_rejected_quantity` AS `product_material_rejected_quantity`,
    `st`.`product_material_rejected_weight` AS `product_material_rejected_weight`,
    `st`.`product_material_return_quantity` AS `product_material_return_quantity`,
    `st`.`product_material_return_weight` AS `product_material_return_weight`,
    `st`.`product_material_grn_accepted_quantity` AS `product_material_grn_accepted_quantity`,
    `st`.`product_material_grn_accepted_weight` AS `product_material_grn_accepted_weight`,
    `st`.`product_child_bom_quantity` AS `product_child_bom_quantity`,
    `st`.`product_child_bom_weight` AS `product_child_bom_weight`,
    `st`.`approval_remark` AS `approval_remark`,
    `e3`.`employee_name` AS `issued_by_name`,
    `st`.`issued_by_id` AS `issued_by_id`,
    `st`.`issued_date` AS `issued_date`,
    `st`.`indent_item_status` AS `indent_item_status`,
    `st`.`po_item_status` AS `po_item_status`,
    `st`.`grn_item_status` AS `grn_item_status`,
    `st`.`issue_item_status` AS `issue_item_status`,
    `v`.`indent_source` AS `indent_source`,
    `c`.`company_legal_name` AS `company_name`,
    `cb`.`company_branch_name` AS `company_branch_name`,
    `st`.`financial_year` AS `financial_year`,
    `child`.`product_type_group` AS `product_type_group`,
    `child`.`product_material_type_short_name` AS `indent_type_short_name`,
    `v`.`Indent_type` AS `indent_type`,
    `v`.`indent_status` AS `indent_status`,
    `st`.`indent_no` AS `field_name`,
    `st`.`indent_version` AS `field_id`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `v`.`is_active` AS `is_active`,
    `st`.`is_delete` AS `is_delete`,
    `st`.`created_by` AS `created_by`,
    `st`.`created_on` AS `created_on`,
    `st`.`modified_by` AS `modified_by`,
    `st`.`modified_on` AS `modified_on`,
    `st`.`deleted_by` AS `deleted_by`,
    `st`.`deleted_on` AS `deleted_on`,
    `st`.`indent_details_id` AS `indent_details_id`,
    `st`.`product_fg_id` AS `product_fg_id`,
    `st`.`product_material_id` AS `product_material_id`,
    `st`.`product_material_unit_id` AS `product_material_unit_id`,
    `st`.`cost_center_id` AS `cost_center_id`,
    `fm`.`cost_center_name` AS `cost_center_name`,
    `v`.`indent_transaction_type` AS `indent_transaction_type`,
    `v`.`indent_master_id` AS `indent_master_id`,
    `v`.`indent_type_id` AS `indent_type_id`,
    `v`.`customer_id` AS `customer_id`,
    `v`.`department_id` AS `department_id`,
    `v`.`sub_department_id` AS `sub_department_id`,
    `v`.`indented_by_id` AS `indented_by_id`,
    `st`.`company_id` AS `company_id`,
     `v`.`approved_date` AS `approved_date`,
    `st`.`company_branch_id` AS `company_branch_id`,
    `child`.`product_type_id` AS `product_type_id`,
    `child`.`product_material_hsn_sac_code_id` AS `product_rm_hsn_sac_code_id`,
    `child`.`product_material_packing_id` AS `product_rm_packing_id`,
    `child`.`product_material_category1_id` AS `product_category1_id`,
    `child`.`product_material_category2_id` AS `product_category2_id`,
    `child`.`product_material_make_id` AS `product_make_id`,
    `child`.`product_material_type_id` AS `product_material_type_id`,
    `child`.`product_material_grade_id` AS `product_material_grade_id`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_quantity`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `st`.`indent_no` and `ptdetails`.`product_material_id` = `st`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `st`.`company_id`), 0) AS `prev_po_quantity`,
    coalesce((select sum(`ptdetails`.`product_material_po_approved_weight`) from `pt_purchase_order_details` `ptdetails` where `ptdetails`.`indent_no` = `st`.`indent_no` and `ptdetails`.`product_material_id` = `st`.`product_material_id` and `ptdetails`.`is_active` = 1 and `ptdetails`.`is_delete` = 0 and `ptdetails`.`company_id` = `st`.`company_id`), 0) AS `prev_po_weight`
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


-- ERP_PASHUPATI_PROD_1_0.stv_indent_details_rpt source

create or replace
ALGORITHM = UNDEFINED VIEW `stv_indent_details_rpt` AS
select
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') AS `indent_no`,
    concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') AS `indent_date`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') AS `approved_date`,
    concat(ifnull(`v`.`indent_priority_desc`, ''), ':Priority:Y:H:(Low-Priority,High-Priority)') AS `indent_priority_desc`,
    concat(ifnull(`v`.`indent_item_status_desc`, ''), ':Indent Item Status:Y:H:(Pending,Approved,Rejected,Completed,Cancel)') AS `indent_item_status_desc`,
    concat(ifnull(`v`.`department_name`, ''), ':Department:Y:C:cmv_department:F') AS `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') AS `sub_department_name`,
    concat(ifnull(`v`.`indent_type`, ''), ':Indent Type:Y:C:smv_product_type:F') AS `indent_type`,
	concat(ifnull(`v`.`product_rm_name`, ''), ':Material name:Y:T') AS `product_rm_name`,
    concat(ifnull(`v`.`product_rm_stock_unit_name`, ''), ':Material Stock Unit Name:O:N:') AS `product_rm_stock_unit_name`,
    concat(ifnull(`v`.`product_material_quantity`, ''), ':Material Quantity:O:N:') AS `product_material_quantity`,
    concat(ifnull(`v`.`product_material_weight`, ''), ':Material Weight:O:N:') AS `product_material_weight`,
    concat(ifnull(`v`.`product_material_stock_quantity`, ''), ':Material Stock Quantity:O:N:') AS `product_material_stock_quantity`,
    concat(ifnull(`v`.`product_material_stock_weight`, ''), ':Material Stock Weight:O:N:') AS `product_material_stock_weight`,
    concat(ifnull(`v`.`product_material_reserve_quantity`, ''), ':Material Reserve Quantity:O:N:') AS `product_material_reserve_quantity`,
    concat(ifnull(`v`.`product_material_reserve_weight`, ''), ':Material Reserve Weight:O:N:') AS `product_material_reserve_weight`,
    concat(ifnull(`v`.`product_material_approved_quantity`, ''), ':Material Approved Quantity:O:N:') AS `product_material_approved_quantity`,
    concat(ifnull(`v`.`product_material_approved_weight`, ''), ':Material Approved Weight:O:N:') AS `product_material_approved_weight`,
    concat(ifnull(`v`.`product_material_rejected_quantity`, ''), ':Material Rejected Quantity:O:N:') AS `product_material_rejected_quantity`,
    concat(ifnull(`v`.`product_material_rejected_weight`, ''), ':Material Rejected Weight:O:N:') AS `product_material_rejected_weight`,
    concat(ifnull(`v`.`product_material_return_quantity`, ''), ':Material Return Quantity:O:N:') AS `product_material_return_quantity`,
    concat(ifnull(`v`.`product_material_return_weight`, ''), ':Material Return Weight:O:N:') AS `product_material_return_weight`,
    concat(ifnull(`v`.`product_material_issue_quantity`, ''), ':Material Issue Quantity:O:N:') AS `product_material_issue_quantity`,
    concat(ifnull(`v`.`product_material_issue_weight`, ''), ':Material Issue Weight:O:N:') AS `product_material_issue_weight`,
    concat(ifnull(`v`.`product_material_grn_accepted_quantity`, ''), ':GRN Accepted Quantity:O:N:') AS `product_material_grn_accepted_quantity`,
    concat(ifnull(`v`.`product_material_grn_accepted_weight`, ''), ':GRN Accepted Weight:O:N:') AS `product_material_grn_accepted_weight`,
    concat(ifnull(`v`.`product_child_bom_quantity`, ''), ':Product Child Bom Quantity:O:N:') AS `product_child_bom_quantity`,
    concat(ifnull(`v`.`product_child_bom_weight`, ''), ':Product Child Bom Weight:O:N:') AS `product_child_bom_weight`,
    concat(ifnull(`v`.`product_fg_material_quantity`, ''), ':Fg Material Quantity:O:N:') AS `product_fg_material_quantity`,
    concat(ifnull(`v`.`product_fg_material_weight`, ''), ':Fg Material Weight:O:N:') AS `product_fg_material_weight`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:Y:C:cmv_customer_summary:F') AS `customer_name`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:C:stv_indent_master_summary:O') AS `customer_order_no`,
    concat(ifnull(`v`.`customer_order_date`, ''), ':Customer Order Date:Y:D:') AS `customer_order_date`,
    concat(ifnull(`v`.`indented_by_name`, ''), ':Indent By:Y:T') AS `indented_by_name`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Indent Approved By:Y:T') AS `approved_by_name`,
    concat(ifnull(`v`.`product_rm_tech_spect`, ''), ':Material Tech spec:O:N:') AS `product_rm_tech_spect`,
    concat(ifnull(`v`.`product_rm_drawing_no`, ''), ':Material Drawing No:O:N:') AS `product_rm_drawing_no`,
    concat(ifnull(`v`.`product_rm_oem_part_code`, ''), ':Material Oem Part Code:O:N:') AS `product_rm_oem_part_code`,
    concat(ifnull(`v`.`product_rm_our_part_code`, ''), ':Material Oem Part Code:O:N:') AS `product_rm_our_part_code`,
    concat(ifnull(`v`.`product_category1_name`, ''), ':Product Category1 Name:O:N:') AS `product_category1_name`,
    concat(ifnull(`v`.`product_category2_name`, ''), ':Product Category2 Name:O:N:') AS `product_category2_name`,
    concat(ifnull(`v`.`lead_time`, ''), ':Lead Time:O:N:') AS `lead_time`,
    concat(ifnull(`v`.`issued_by_name`, ''), ':Issued By:Y:T') AS `issued_by_name`,
    concat(ifnull(`v`.`issued_date`, ''), ':Issued Date:Y:D:') AS `issued_date`,
    concat(ifnull(`v`.`indent_item_status`, ''), ':Indent Item Status:O:N:') AS `indent_item_status`,
    concat(ifnull(`v`.`grn_item_status`, ''), ':GRN Item Status:O:N:') AS `grn_item_status`,
    concat(ifnull(`v`.`indent_status`, ''), ':Indent Status:O:N:') AS `indent_status`,
    concat(ifnull(`v`.`indent_source`, ''), ':Indent Source:O:N:') AS `indent_source`,
    concat(ifnull(`v`.`remark`, ''), 'remark:O:N:') AS `remark`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') AS `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') AS `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') AS `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') AS `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') AS `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') AS `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(ifnull(`v`.`product_fg_id`, ''), ':Product Fg Id:N:N:') AS `product_fg_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Material Unit Id:N:N:') AS `product_material_unit_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') AS `product_material_id`,
    concat(ifnull(`v`.`product_type_id`, ''), ':Product Type Id:N:N:') AS `product_type_id`,
    concat(ifnull(`v`.`product_category1_id`, ''), ':Product Category1 Id:N:N:') AS `product_category1_id`,
    concat(ifnull(`v`.`product_category2_id`, ''), ':Product Category2 Id:N:N:') AS `product_category2_id`,
    concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') AS `indent_version`,
    concat(ifnull(`v`.`product_make_id`, ''), ':Product Make Id:N:N:') AS `product_make_id`,
    concat(ifnull(`v`.`product_material_type_id`, ''), ':Product Material Type Id:N:N:') AS `product_material_type_id`,
    concat(ifnull(`v`.`product_material_grade_id`, ''), ':Product Material Grade Id:N:N:') AS `product_material_grade_id`,
    concat(ifnull(`v`.`indent_master_id`, ''), ':Indent Master Id:O:N:') AS `indent_master_id`,
    concat(ifnull(`v`.`indent_details_id`, ''), ':Indent Details Id:N:N:') AS `indent_details_id`,
    concat(ifnull(`v`.`indent_type_id`, ''), ':Indent Type Id:N:N:') AS `indent_type_id`,
    concat(ifnull(`v`.`customer_id`, ''), ':Customer Id:N:N:') AS `customer_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') AS `department_id`,
    concat(ifnull(`v`.`indented_by_id`, ''), ':Indented By Id:N:N:') AS `indented_by_id`,
    concat(ifnull(`v`.`issued_by_id`, ''), ':Issued By Id:N:N:') AS `issued_by_id`
from
    `stv_indent_details` `v`
limit 1;


-- ERP_PASHUPATI_PROD_1_0.ptv_purchase_order_master_summary_rpt source

create or replace
ALGORITHM = UNDEFINED VIEW `ptv_purchase_order_master_summary_rpt` AS
select
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') AS `purchase_order_no`,
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') AS `purchase_order_date`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') AS `approved_date`,
    concat(ifnull(`v`.`purchase_order_status`, ''), ':Purchase Order Status:Y:H:(Approved,Rejected,Partial Receipt,Completed,Canceled,Pree Closed,Pending)') AS `purchase_order_status_desc`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:T') AS `supplier_name`,
    concat(ifnull(`v`.`basic_total`, ''), ':Basic Total:O:N:') AS `basic_total`,
    concat(ifnull(`v`.`transport_amount`, ''), ':Transport Amount:O:N:') AS `transport_amount`,
    concat(ifnull(`v`.`freight_amount`, ''), ':Freight Amount:O:N:') AS `freight_amount`,
    concat(ifnull(`v`.`is_freight_taxable`, ''), ':Is Freight Taxable:O:N:') AS `is_freight_taxable`,
    concat(ifnull(`v`.`packing_amount`, ''), ':Packing Amount:O:N:') AS `packing_amount`,
    concat(ifnull(`v`.`po_discount_percent`, ''), ':PO Discount Percent:O:N:') AS `po_discount_percent`,
    concat(ifnull(`v`.`po_discount_amount`, ''), ':PO Discount Amount:O:N:') AS `po_discount_amount`,
    concat(ifnull(`v`.`other_amount`, ''), ':Other Amount:O:N:') AS `other_amount`,
    concat(ifnull(`v`.`taxable_total`, ''), ':Taxable Total:O:N:') AS `taxable_total`,
    concat(ifnull(`v`.`cgst_total`, ''), ':CGST Total:O:N:') AS `cgst_total`,
    concat(ifnull(`v`.`sgst_total`, ''), ':SGST Total:O:N:') AS `sgst_total`,
    concat(ifnull(`v`.`igst_total`, ''), ':IGST Total:O:N:') AS `igst_total`,
    concat(ifnull(`v`.`roundoff`, ''), ':RoundOff:O:N:') AS `roundoff`,
    concat(ifnull(`v`.`grand_total`, ''), ':Grand Total:O:N:') AS `grand_total`,
    concat(ifnull(`v`.`quotation_no`, ''), ':Quotation No:Y:T') AS `quotation_no`,
    concat(ifnull(`v`.`quotation_date`, ''), ':Quotation Date:Y:D:') AS `quotation_date`,
    concat(ifnull(`v`.`approved_by`, ''), ':Approved By:Y:C:cmv_employee_list:F') AS `approved_by`,
    concat(ifnull(`v`.`expected_schedule_date`, ''), ':Expected Schedule Date:Y:D:') AS `expected_schedule_date`,
    concat(ifnull(`v`.`purchase_order_acceptance_status`, ''), ':Purchase Order Acceptance Status:Y:H:(Approved,Canceled,Pending)') AS `purchase_order_acceptance_status_desc`,
    concat(ifnull(`v`.`purchase_order_mail_sent_status`, ''), ':Mail Sent Status:Y:H:(Email Sent,Email Failed,Email Pending)') AS `purchase_order_mail_sent_status_desc`,
    concat(ifnull(`v`.`purchase_order_creation_type_desc`, ''), ':Purchase Order Creation Type:Y:H:(Manual,Auto)') AS `purchase_order_creation_type_desc`,
    concat(ifnull(`v`.`purchase_order_life_desc`, ''), ':Purchase Order Life:Y:H:(Close,Open)') AS `purchase_order_life_desc`,
    concat(ifnull(`v`.`status_remark`, ''), ':Status Remark:O:N:') AS `status_remark`,
    concat(ifnull(`v`.`supplier_type`, ''), ':Supplier Type:O:N:') AS `supplier_type`,
    concat(ifnull(`v`.`supp_branch_address1`, ''), ':Supplier Branch Address1:O:N:') AS `supp_branch_address1`,
    concat(ifnull(`v`.`supp_branch_pincode`, ''), ':Supplier Branch Pincode:O:N:') AS `supp_branch_pincode`,
    concat(ifnull(`v`.`city_name`, ''), ':Supplier City Name:O:N:') AS `city_name`,
    concat(ifnull(`v`.`state_name`, ''), ':Supplier State Name:O:N:') AS `state_name`,
    concat(ifnull(`v`.`supp_branch_phone_no`, ''), ':Supplier Branch Phone No:O:N:') AS `supp_branch_phone_no`,
    concat(ifnull(`v`.`supp_branch_EmailId`, ''), ':Supplier Branch EmailId:O:N:') AS `supp_branch_EmailId`,
    concat(ifnull(`v`.`expected_branch_name`, ''), ':Company Branch Name:O:N:') AS `expected_branch_name`,
    concat(ifnull(`v`.`expected_branch_short_name`, ''), ':Company Branch Short Name:O:N:') AS `expected_branch_short_name`,
    concat(ifnull(`v`.`expected_branch_address1`, ''), ':Company Branch Address1:O:N:') AS `expected_branch_address1`,
    concat(ifnull(`v`.`expected_branch_pincode`, ''), ':Company Branch Pincode:O:N:') AS `expected_branch_pincode`,
    concat(ifnull(`v`.`expected_branch_city_name`, ''), ':Company Branch City Name:O:N:') AS `expected_branch_city_name`,
    concat(ifnull(`v`.`expected_branch_state_name`, ''), ':Company Branch State Name:O:N:') AS `expected_branch_state_name`,
    concat(ifnull(`v`.`expected_branch_phone_no`, ''), ':Company Branch Phone No:O:N:') AS `expected_branch_phone_no`,
    concat(ifnull(`v`.`expected_branch_EmailId`, ''), ':Company Expected Branch EmailId:O:N:') AS `expected_branch_EmailId`,
    concat(ifnull(`v`.`agent_name`, ''), ':Agent Name:Y:C:cmv_agent:F') AS `agent_name`,
    concat(ifnull(`v`.`agent_percent`, ''), ':Agent Percent:O:N:') AS `agent_percent`,
    concat(ifnull(`v`.`agent_paid_status`, ''), ':Agent Paid Status:Y:H:(Approved,Completed,Canceled,Pending)') AS `agent_paid_status_desc`,
    concat(ifnull(`v`.`other_terms_conditions`, ''), ':Other Terms Conditions:O:N:') AS `other_terms_conditions`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') AS `remark`,
    concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') AS `product_type_short_name`,
    concat(ifnull(`v`.`hsn_sac_code`, ''), ':Freight Hsn Sac Code:O:N:') AS `hsn_sac_code`,
    concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Freight Hsn Sac Rate:O:N:') AS `hsn_sac_rate`,
    concat(ifnull(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ''), ':Active Status:Y:H:(Active,In Active)') AS `Active`,
    concat(ifnull(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ''), ':Deleted Status:Y:H:(Yes,No)') AS `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') AS `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
    concat(ifnull(`v`.`purchase_order_master_transaction_id`, ''), ':Purchase Order Master Transaction_id:O:N:') AS `purchase_order_master_transaction_id`,
    concat(ifnull(`v`.`supplier_id`, ''), ':Supplier Id:N:N:') AS `supplier_id`,
    concat(ifnull(`v`.`purchase_order_status`, ''), ':Purchase Order Status:O:N:') AS `purchase_order_status`,
    concat(ifnull(`v`.`grn_status`, ''), ':GRN Status:O:N:') AS `grn_status`,
    concat(ifnull(`v`.`purchase_order_creation_type`, ''), ':Purchase Order Creation Type:N:N:') AS `purchase_order_creation_type`,
    concat(ifnull(`v`.`purchase_order_life`, ''), ':Purchase Order Life:O:N:') AS `purchase_order_life`,
    concat(ifnull(`v`.`agent_paid_status`, ''), ':Agent Paid Status:N:N:') AS `agent_paid_status`,
    concat(ifnull(`v`.`supplier_state_id`, ''), ':Supplier State Id:N:N:') AS `supplier_state_id`,
    concat(ifnull(`v`.`supplier_city_id`, ''), ':Supplier City Id:N:N:') AS `supplier_city_id`,
    concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By Id:O:N:') AS `approved_by_id`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') AS `company_id`,
    concat(ifnull(`v`.`purchase_order_version`, ''), ':Purchase Order Version:O:N:') AS `purchase_order_version`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year :O:N:') AS `financial_year`
from
    `ptv_purchase_order_master_summary` `v`
limit 1;


-- ERP_PASHUPATI_PROD_1_0.ptv_purchase_order_details_rpt source

create or replace
ALGORITHM = UNDEFINED VIEW `ptv_purchase_order_details_rpt` AS
select
    concat(ifnull(`v`.`purchase_order_no`, ''), ':Purchase Order No:Y:T') AS `purchase_order_no`,
    concat(ifnull(`v`.`purchase_order_date`, ''), ':Purchase Order Date:Y:D:') AS `purchase_order_date`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') AS `approved_date`,
    concat(ifnull(`v`.`purchase_order_item_status`, ''), ':Purchase Order Item Status:Y:H:(Approved,Rejected,Partial Receipt,Completed,Canceled,Pree Closed,Bill Booked)') AS `purchase_order_item_status_desc`,
    concat(ifnull(`v`.`purchase_order_type`, ''), ':Purchase Order Type:O:N:') AS `purchase_order_type`,
    concat(ifnull(`v`.`product_material_code`, ''), ':Material Code:Y:T') AS `product_material_code`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Material Name:Y:T') AS `product_material_name`,
    concat(ifnull(`v`.`indent_no`, ''), ':Indent No:Y:T') AS `indent_no`,
    concat(ifnull(`v`.`indent_date`, ''), ':Indent Date:Y:D:') AS `indent_date`,
    concat(ifnull(`v`.`product_material_stock_unit_name`, ''), ':Material Unit Name:O:N:') AS `product_material_stock_unit_name`,
    concat(ifnull(`v`.`product_material_po_quantity`, ''), ':Material Po Quantity:O:N:') AS `product_material_po_quantity`,
    concat(ifnull(`v`.`product_material_po_weight`, ''), ':Material Po Weight:O:N:') AS `product_material_po_weight`,
    concat(ifnull(`v`.`material_rate`, ''), ':Material Rate:O:N:') AS `material_rate`,
    concat(ifnull(`v`.`material_basic_amount`, ''), ':Material Basic Amount:O:N:') AS `material_basic_amount`,
    concat(ifnull(`v`.`material_discount_percent`, ''), ':Material Discount Percent:O:N:') AS `material_discount_percent`,
    concat(ifnull(`v`.`material_discount_amount`, ''), ':Material Discount Amount:O:N:') AS `material_discount_amount`,
    concat(ifnull(`v`.`material_taxable_amount`, ''), ':Material Taxable Amount:O:N:') AS `material_taxable_amount`,
    concat(ifnull(`v`.`material_cgst_percent`, ''), ':Material Cgst Percent:O:N:') AS `material_cgst_percent`,
    concat(ifnull(`v`.`material_cgst_total`, ''), ':Material Cgst Total:O:N:') AS `material_cgst_total`,
    concat(ifnull(`v`.`material_sgst_percent`, ''), ':Material Sgst Percent:O:N:') AS `material_sgst_percent`,
    concat(ifnull(`v`.`material_sgst_total`, ''), ':Material Sgst Total:O:N:') AS `material_sgst_total`,
    concat(ifnull(`v`.`material_igst_percent`, ''), ':Material Igst Percent:O:N:') AS `material_igst_percent`,
    concat(ifnull(`v`.`material_igst_total`, ''), ':Material Igst Total:O:N:') AS `material_igst_total`,
    concat(ifnull(`v`.`material_total_amount`, ''), ':Material Total Amount:O:N:') AS `material_total_amount`,
    concat(ifnull(`v`.`product_material_po_approved_quantity`, ''), ':Material Po Approved Quantity:O:N:') AS `product_material_po_approved_quantity`,
    concat(ifnull(`v`.`product_material_po_approved_weight`, ''), ':Material Po Approved Weight:O:N:') AS `product_material_po_approved_weight`,
    concat(ifnull(`v`.`product_material_po_rejected_quantity`, ''), ':Material Po Rejected Quantity:O:N:') AS `product_material_po_rejected_quantity`,
    concat(ifnull(`v`.`product_material_po_rejected_weight`, ''), ':Material Po Rejected Weight:O:N:') AS `product_material_po_rejected_weight`,
    concat(ifnull(`v`.`material_po_approval_remark`, ''), ':Material Po Approval Remark:O:N:') AS `material_po_approval_remark`,
    concat(ifnull(`v`.`purchase_order_mail_sent_status`, ''), ':Purchase Order Mail Sent Status:Y:H:(Email Sent,Email Failed,Email Pending)') AS `purchase_order_mail_sent_status_desc`,
    concat(ifnull(`v`.`material_schedule_date`, ''), ':Material Schedule Date:Y:D:') AS `material_schedule_date`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:T') AS `approved_by_name`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:C:cmv_department:F') AS `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:C:cmv_department:F') AS `sub_department_name`,
    concat(ifnull(`v`.`hsn_sac_code`, ''), ':Hsn Sac Code:O:N:') AS `hsn_sac_code`,
    concat(ifnull(`v`.`hsn_sac_rate`, ''), ':Hsn Sac Rate:O:N:') AS `hsn_sac_rate`,
    concat(ifnull(`v`.`pree_closed_quantity`, ''), ':Pree-Closed Quantity:O:N:') AS `pree_closed_quantity`,
    concat(ifnull(`v`.`pree_closed_weight`, ''), ':Pree-Closed Weight:O:N:') AS `pree_closed_weight`,
    concat(ifnull(`v`.`pre_closed_remark`, ''), ':Pree-Closed Remark:O:N:') AS `pre_closed_remark`,
    concat(ifnull(`v`.`purchase_return_quantity`, ''), ':Purchase Return Quantity:O:N:') AS `purchase_return_quantity`,
    concat(ifnull(`v`.`purchase_return_weight`, ''), ':Purchase Return Weight:O:N:') AS `purchase_return_weight`,
    concat(ifnull(`v`.`pending_quantity`, ''), ':Pending Quantity:O:N:') AS `pending_quantity`,
    concat(ifnull(`v`.`pending_weight`, ''), ':Pending Weight:O:N:') AS `pending_weight`,
    concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') AS `excess_quantity`,
    concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') AS `excess_weight`,
    concat(ifnull(`v`.`production_issue_quantity`, ''), ':Issue Quantity:O:N:') AS `production_issue_quantity`,
    concat(ifnull(`v`.`production_issue_weight`, ''), ':Issue Weight:O:N:') AS `production_issue_weight`,
    concat(ifnull(`v`.`production_issue_return_quantity`, ''), ':Issue Return Quantity:O:N:') AS `production_issue_return_quantity`,
    concat(ifnull(`v`.`production_issue_return_weight`, ''), ':Issue Return Weight:O:N:') AS `production_issue_return_weight`,
    concat(ifnull(`v`.`production_issue_rejection_quantity`, ''), ':Issue Rejection Quantity:O:N:') AS `production_issue_rejection_quantity`,
    concat(ifnull(`v`.`production_issue_rejection_weight`, ''), ':Issue Rejection Weight:O:N:') AS `production_issue_rejection_weight`,
    concat(ifnull(`v`.`assembly_production_issue_quantity`, ''), ':Assembly Production Issue Quantity:O:N:') AS `assembly_production_issue_quantity`,
    concat(ifnull(`v`.`assembly_production_issue_weight`, ''), ':Assembly Production Issue Weight:O:N:') AS `assembly_production_issue_weight`,
    concat(ifnull(`v`.`sales_quantity`, ''), ':Sales Quantity:O:N:') AS `sales_quantity`,
    concat(ifnull(`v`.`sales_weight`, ''), ':Sales Weight:O:N:') AS `sales_weight`,
    concat(ifnull(`v`.`sales_return_quantity`, ''), ':Sales Return Quantity:O:N:') AS `sales_return_quantity`,
    concat(ifnull(`v`.`sales_return_weight`, ''), ':Sales Return Weight:O:N:') AS `sales_return_weight`,
    concat(ifnull(`v`.`sales_rejection_quantity`, ''), ':Sales Rejection Quantity:O:N:') AS `sales_rejection_quantity`,
    concat(ifnull(`v`.`sales_rejection_weight`, ''), ':Sales Rejection Weight:O:N:') AS `sales_rejection_weight`,
    concat(ifnull(`v`.`transfer_issue_quantity`, ''), ':Transfer Issue Quantity:O:N:') AS `transfer_issue_quantity`,
    concat(ifnull(`v`.`transfer_issue_weight`, ''), ':Transfer Issue Weight:O:N:') AS `transfer_issue_weight`,
    concat(ifnull(`v`.`transfer_receipt_quantity`, ''), ':Transfer Receipt Quantity:O:N:') AS `transfer_receipt_quantity`,
    concat(ifnull(`v`.`transfer_receipt_weight`, ''), ':Transfer Receipt Weight:O:N:') AS `transfer_receipt_weight`,
    concat(ifnull(`v`.`outsources_out_quantity`, ''), ':Outsources Out Quantity:O:N:') AS `outsources_out_quantity`,
    concat(ifnull(`v`.`outsources_out_weight`, ''), ':Outsourcess Out Weight:O:N:') AS `outsources_out_weight`,
    concat(ifnull(`v`.`outsources_in_quantity`, ''), ':Outsources In Quantity:O:N:') AS `outsources_in_quantity`,
    concat(ifnull(`v`.`outsources_in_weight`, ''), ':Outsources In Weight:O:N:') AS `outsources_in_weight`,
    concat(ifnull(`v`.`outsources_rejection_quantity`, ''), ':Outsources Rejection Quantity:O:N:') AS `outsources_rejection_quantity`,
    concat(ifnull(`v`.`outsources_rejection_weight`, ''), ':Outsources Rejection Weight:O:N:') AS `outsources_rejection_weight`,
    concat(ifnull(`v`.`loan_receipt_quantity`, ''), ':Loan Receipt Quantity:O:N:') AS `loan_receipt_quantity`,
    concat(ifnull(`v`.`loan_receipt_weight`, ''), ':Loan Receipt Weight:O:N:') AS `loan_receipt_weight`,
    concat(ifnull(`v`.`loan_issue_quantity`, ''), ':Loan Issue Quantity:O:N:') AS `loan_issue_quantity`,
    concat(ifnull(`v`.`loan_issue_weight`, ''), ':Loan Issue Weight:O:N:') AS `loan_issue_weight`,
    concat(ifnull(`v`.`cancel_quantity`, ''), ':Cancel Quantity:O:N:') AS `cancel_quantity`,
    concat(ifnull(`v`.`cancel_weight`, ''), ':Cancel Weight:O:N:') AS `cancel_weight`,
    concat(ifnull(`v`.`prev_grn_quantity`, ''), ':Prev GRN Quantity:O:N:') AS `prev_grn_quantity`,
    concat(ifnull(`v`.`prev_grn_weight`, ''), ':Prev GRN Quantity:O:N:') AS `prev_grn_weight`,
    concat(ifnull(`v`.`difference_quantity`, ''), ':Difference Quantity:O:N:') AS `difference_quantity`,
    concat(ifnull(`v`.`difference_weight`, ''), ':Difference Weight:O:N:') AS `difference_weight`,
    concat(ifnull(`v`.`lead_time`, ''), ':Lead Time:O:N:') AS `lead_time`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:T') AS `supplier_name`,
    concat(ifnull(`v`.`supplier_state_name`, ''), ':Supplier State Name:O:N:') AS `supplier_state_name`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer Name:O:N:') AS `customer_name`,
    concat(ifnull(`v`.`indent_version`, ''), ':Indent Version:O:N:') AS `indent_version`,
    concat(ifnull(`v`.`indented_by_name`, ''), ':Indented By:Y:T') AS `indented_by_name`,
    concat(ifnull(`v`.`sr_no`, ''), ':Sr No:O:N:') AS `sr_no`,
    concat(ifnull(`v`.`so_sr_no`, ''), ':So Sr No:O:N:') AS `so_sr_no`,
    concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') AS `product_type_short_name`,
    concat(ifnull(`v`.`product_type_group`, ''), ':Product Type Group:O:N:') AS `product_type_group`,
    concat(ifnull(`v`.`product_material_print_name`, ''), ':Material Print Name:O:N:') AS `product_material_print_name`,
    concat(ifnull(`v`.`product_material_tech_spect`, ''), ':Material Tech Spect:O:N:') AS `product_material_tech_spect`,
    concat(ifnull(`v`.`product_packing_name`, ''), ':Product Packing Name:O:N:') AS `product_packing_name`,
    concat(ifnull(`v`.`hsn_sac_type`, ''), ':Hsn Sac Type:O:N:') AS `hsn_sac_type`,
    concat(ifnull(`v`.`hsn_sac_description`, ''), ':Hsn Sac Description:O:N:') AS `hsn_sac_description`,
    concat(ifnull(`v`.`purchase_order_status`, ''), ':Purchase Order Status:O:N:') AS `purchase_order_status`,
    concat(ifnull(`v`.`purchase_order_item_status`, ''), ':Purchase Order Item Status:O:N:') AS `purchase_order_item_status`,
    concat(ifnull(`v`.`grn_item_status`, ''), ':GRN Item Status:O:N:') AS `grn_item_status`,
    concat(ifnull(`v`.`purchase_order_mail_sent_status`, ''), ':Purchase Order Mail Sent Status:O:N:') AS `purchase_order_mail_sent_status`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') AS `remark`,
    concat(ifnull(`v`.`city_name`, ''), ':City Name:O:N:') AS `city_name`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company_summary:F') AS `company_name`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch_summary:F') AS `company_branch_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:Y:C:amv_financial_year:F') AS `financial_year`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active,In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes,No)') AS `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') AS `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Modified On:O:N:') AS `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') AS `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') AS `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') AS `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') AS `deleted_on`,
    concat(ifnull(`v`.`supplier_id`, ''), ':Supplier Id:N:N:') AS `supplier_id`,
    concat(ifnull(`v`.`supplier_state_id`, ''), ':Supplier State Id:N:N:') AS `supplier_state_id`,
    concat(ifnull(`v`.`supplier_city_id`, ''), ':Supplier City Id:N:N:') AS `supplier_city_id`,
        concat(ifnull(`v`.`purchase_order_version`, ''), ':Purchase Order Version:O:N:') AS `purchase_order_version`,

    concat(ifnull(`v`.`supplier_contacts_ids`, ''), ':Supplier Contacts Ids:N:N:') AS `supplier_contacts_ids`,
    concat(ifnull(`v`.`purchase_order_type_id`, ''), ':Purchase Order Type Id:N:N:') AS `purchase_order_type_id`,
    concat(ifnull(`v`.`purchase_order_master_transaction_id`, ''), ':Purchase Order Master Transaction Id:O:N:') AS `purchase_order_master_transaction_id`,
    concat(ifnull(`v`.`purchase_order_details_transaction_id`, ''), ':Purchase Order Details Transaction_id:N:N:') AS `purchase_order_details_transaction_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') AS `product_material_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Product Material Unit Id:N:N:') AS `product_material_unit_id`,
    concat(ifnull(`v`.`product_material_packing_id`, ''), ':Product Material Packing Id:N:N:') AS `product_material_packing_id`,
    concat(ifnull(`v`.`product_material_hsn_code_id`, ''), ':Product Material Hsn Code Id:N:N:') AS `product_material_hsn_code_id`,
    concat(ifnull(`v`.`department_id`, ''), ':Department Id:N:N:') AS `department_id`,
    concat(ifnull(`v`.`indented_by_id`, ''), ':Indented By Id:N:N:') AS `indented_by_id`,
    concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By Id:O:N:') AS `approved_by_id`
from
    `ptv_purchase_order_details` `v`
limit 1;


ALTER TABLE cm_city MODIFY COLUMN city_short_name varchar(255) DEFAULT NULL NULL;
ALTER TABLE cm_agent MODIFY COLUMN password varchar(500) NOT NULL;

