INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,2,12,'Raw Material Return',1,'Raw Material Return','Purchase','<FrmRawMaterialReturnListing />','import FrmRawMaterialReturnListing from "./Transactions/TRawMaterialReturn/FrmRawMaterialReturnListing";','/Transactions/RawMaterialReturnListing','<FrmRawMaterialReturnEntry />','import FrmRawMaterialRequisitionEntry from import FrmRawMaterialReturnEntry from "./Transactions/TRawMaterialReturn/FrmRawMaterialReturnEntry";','/Transactions/RawMaterialReturnEntry',NULL,'Raw Material Return',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


-- erp_dev_temp.smv_product_rm_stock_summary_rpt source

create or replace
algorithm = UNDEFINED view `smv_product_rm_stock_summary_rpt` as
select
    concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:Y:C:smv_product_type:F') as `product_type_name`,
    concat(ifnull(`v`.`product_rm_name`, ''), ':Material Name:Y:T') as `product_rm_name`,
    concat(ifnull(`v`.`product_rm_code`, ''), ':Material Code:Y:T') as `product_rm_code`,
    concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:Y:C:cmv_godown:F') as `godown_name`,
    concat(ifnull(`v`.`godown_section_name`, ''), ':Godown Section Name:Y:C:cmv_godown_section:F') as `godown_section_name`,
    concat(ifnull(`v`.`godown_section_beans_name`, ''), ':Godown Section Beans Name:Y:C:cmv_godown_section_beans:F') as `godown_section_beans_name`,
    concat(ifnull(`v`.`product_material_category1_name`, ''), ':Catergory1 Name:Y:C:smv_product_category1:F') as `product_material_category1_name`,
    concat(ifnull(`v`.`product_material_category2_name`, ''), ':Catergory2 Name:Y:C:smv_product_category2:F') as `product_material_category2_name`,
    concat(ifnull(`v`.`company_name`, ''), ':Company Name:Y:C:cmv_company:F') as `company_name`,
    concat(ifnull(`v`.`product_type_group`, ''), ':Product Type Group:O:N:') as `product_type_group`,
    concat(ifnull(`v`.`order_quantity`, ''), ':Order Quantity:O:N:') as `order_quantity`,
    concat(ifnull(`v`.`order_weight`, ''), ':Order Weight:O:N:') as `order_weight`,
    concat(ifnull(`v`.`supplier_name`, ''), ':Supplier Name:Y:C:cmv_supplier:F') as `supplier_name`,
    concat(ifnull(`v`.`customer_name`, ''), ':Customer:Y:C:cmv_customer:F') as `customer_name`,
    concat(ifnull(`v`.`pending_quantity`, ''), ':Pending Quantity:O:N:') as `pending_quantity`,
    concat(ifnull(`v`.`pending_weight`, ''), ':Pending Weight:O:N:') as `pending_weight`,
    concat(ifnull(`v`.`opening_quantity`, ''), ':Opening Quantity:O:N:') as `opening_quantity`,
    concat(ifnull(`v`.`opening_weight`, ''), ':Opening Weight:O:N:') as `opening_weight`,
    concat(ifnull(`v`.`reserve_quantity`, ''), ':Reserve Quantity:O:N:') as `reserve_quantity`,
    concat(ifnull(`v`.`reserve_weight`, ''), ':Reserve Weight:O:N:') as `reserve_weight`,
    concat(ifnull(`v`.`excess_quantity`, ''), ':Excess Quantity:O:N:') as `excess_quantity`,
    concat(ifnull(`v`.`excess_weight`, ''), ':Excess Weight:O:N:') as `excess_weight`,
    concat(ifnull(`v`.`pree_closed_quantity`, ''), ':Pree Closed Quantity:O:N:') as `pree_closed_quantity`,
    concat(ifnull(`v`.`pree_closed_weight`, ''), ':Pree Closed Weight:O:N:') as `pree_closed_weight`,
    concat(ifnull(`v`.`purchase_quantity`, ''), ':Purchase Quantity:O:N:') as `purchase_quantity`,
    concat(ifnull(`v`.`purchase_weight`, ''), ':Purchase Weight:O:N:') as `purchase_weight`,
    concat(ifnull(`v`.`purchase_return_quantity`, ''), ':Purchase Return Quantity:O:N:') as `purchase_return_quantity`,
    concat(ifnull(`v`.`purchase_return_weight`, ''), ':Purchase Return Weight:O:N:') as `purchase_return_weight`,
    concat(ifnull(`v`.`purchase_rejection_quantity`, ''), ':Purchase Rejection Quantity:O:N:') as `purchase_rejection_quantity`,
    concat(ifnull(`v`.`purchase_rejection_weight`, ''), ':Purchase Rejection Weight:O:N:') as `purchase_rejection_weight`,
    concat(ifnull(`v`.`jobcard_quantity`, ''), ':Jobcard Quantity:O:N:') as `jobcard_quantity`,
    concat(ifnull(`v`.`jobcard_weight`, ''), ':Jobcard Weight:O:N:') as `jobcard_weight`,
    concat(ifnull(`v`.`production_issue_quantity`, ''), ':Issue Quantity:O:N:') as `production_issue_quantity`,
    concat(ifnull(`v`.`production_issue_weight`, ''), ':Issue Weight:O:N:') as `production_issue_weight`,
    concat(ifnull(`v`.`production_issue_return_quantity`, ''), ':Issue Return Quantity:O:N:') as `production_issue_return_quantity`,
    concat(ifnull(`v`.`production_issue_return_weight`, ''), ':Issue Return Weight:O:N:') as `production_issue_return_weight`,
    concat(ifnull(`v`.`production_issue_rejection_quantity`, ''), ':Issue Rejection Quantity:O:N:') as `production_issue_rejection_quantity`,
    concat(ifnull(`v`.`production_issue_rejection_weight`, ''), ':Issue Rejection Weight:O:N:') as `production_issue_rejection_weight`,
    concat(ifnull(`v`.`production_quantity`, ''), ':Production Quantity:O:N:') as `production_quantity`,
    concat(ifnull(`v`.`production_weight`, ''), ':Production Weight:O:N:') as `production_weight`,
    concat(ifnull(`v`.`production_return_quantity`, ''), ':Production Return Quantity:O:N:') as `production_return_quantity`,
    concat(ifnull(`v`.`production_return_weight`, ''), ':Production Return Weight:O:N:') as `production_return_weight`,
    concat(ifnull(`v`.`production_rejection_quantity`, ''), ':Production Rejection Quantity:O:N:') as `production_rejection_quantity`,
    concat(ifnull(`v`.`production_rejection_weight`, ''), ':Production Rejection Weight:O:N:') as `production_rejection_weight`,
    concat(ifnull(`v`.`assembly_production_issue_quantity`, ''), ':Assembly Production Issue Quantity:O:N:') as `assembly_production_issue_quantity`,
    concat(ifnull(`v`.`assembly_production_issue_weight`, ''), ':Assembly Production Issue Weight:O:N:') as `assembly_production_issue_weight`,
    concat(ifnull(`v`.`customer_receipt_quantity`, ''), ':Customer Receipt Quantity:O:N:') as `customer_receipt_quantity`,
    concat(ifnull(`v`.`customer_receipt_weight`, ''), ':Customer Receipt Weight:O:N:') as `customer_receipt_weight`,
    concat(ifnull(`v`.`customer_return_quantity`, ''), ':Customer Return Quantity:O:N:') as `customer_return_quantity`,
    concat(ifnull(`v`.`customer_return_weight`, ''), ':Customer Return Weight:O:N:') as `customer_return_weight`,
    concat(ifnull(`v`.`customer_rejection_weight`, ''), ':Customer Rejection Weight:O:N:') as `customer_rejection_weight`,
    concat(ifnull(`v`.`customer_rejection_quantity`, ''), ':Customer Rejection Quantity:O:N:') as `customer_rejection_quantity`,
    concat(ifnull(`v`.`sales_quantity`, ''), ':Sales Quantity:O:N:') as `sales_quantity`,
    concat(ifnull(`v`.`sales_weight`, ''), ':Sales Weight:O:N:') as `sales_weight`,
    concat(ifnull(`v`.`sales_return_quantity`, ''), ':Sales Return Quantity:O:N:') as `sales_return_quantity`,
    concat(ifnull(`v`.`sales_return_weight`, ''), ':Sales Return Weight:O:N:') as `sales_return_weight`,
    concat(ifnull(`v`.`sales_rejection_quantity`, ''), ':Sales Rejection Quantity:O:N:') as `sales_rejection_quantity`,
    concat(ifnull(`v`.`sales_rejection_weight`, ''), ':Sales Rejection Weight:O:N:') as `sales_rejection_weight`,
    concat(ifnull(`v`.`transfer_issue_quantity`, ''), ':Transfer Issue Quantity:O:N:') as `transfer_issue_quantity`,
    concat(ifnull(`v`.`transfer_issue_weight`, ''), ':Transfer Issue Weight:O:N:') as `transfer_issue_weight`,
    concat(ifnull(`v`.`transfer_receipt_quantity`, ''), ':Transfer Receipt Quantity:O:N:') as `transfer_receipt_quantity`,
    concat(ifnull(`v`.`transfer_receipt_weight`, ''), ':Transfer Receipt Weight:O:N:') as `transfer_receipt_weight`,
    concat(ifnull(`v`.`outsources_out_quantity`, ''), ':Outsources Out Quantity:O:N:') as `outsources_out_quantity`,
    concat(ifnull(`v`.`outsources_out_weight`, ''), ':Outsources Out Weight:O:N:') as `outsources_out_weight`,
    concat(ifnull(`v`.`outsources_in_quantity`, ''), ':Outsources In Quantity:O:N:') as `outsources_in_quantity`,
    concat(ifnull(`v`.`outsources_in_weight`, ''), ':Outsources In Weight:O:N:') as `outsources_in_weight`,
    concat(ifnull(`v`.`outsources_rejection_quantity`, ''), ':Outsources Rejection Quantity:O:N:') as `outsources_rejection_quantity`,
    concat(ifnull(`v`.`outsources_rejection_weight`, ''), ':Outsources Rejection Weight:O:N:') as `outsources_rejection_weight`,
    concat(ifnull(`v`.`loan_receipt_quantity`, ''), ':Loan Receipt Quantity:O:N:') as `loan_receipt_quantity`,
    concat(ifnull(`v`.`loan_receipt_weight`, ''), ':Loan Receipt Weight:O:N:') as `loan_receipt_weight`,
    concat(ifnull(`v`.`loan_issue_quantity`, ''), ':Loan Issue Quantity:O:N:') as `loan_issue_quantity`,
    concat(ifnull(`v`.`loan_issue_weight`, ''), ':Loan Issue Weight:O:N:') as `loan_issue_weight`,
    concat(ifnull(`v`.`cancel_quantity`, ''), ':Cancel Quantity:O:N:') as `cancel_quantity`,
    concat(ifnull(`v`.`cancel_weight`, ''), ':Cancel Weight:O:N:') as `cancel_weight`,
    concat(ifnull(`v`.`difference_quantity`, ''), ':Difference Quantity:O:N:') as `difference_quantity`,
    concat(ifnull(`v`.`difference_weight`, ''), ':Difference Weight:O:N:') as `difference_weight`,
    concat(ifnull(`v`.`closing_balance_quantity`, ''), ':Closing Balance Quantity:O:N:') as `closing_balance_quantity`,
    concat(ifnull(`v`.`closing_balance_weight`, ''), ':Closing Balance Weight:O:N:') as `closing_balance_weight`,
    concat(ifnull(`v`.`total_box_weight`, ''), ':Gross Weight:O:N:') as `total_box_weight`,
    concat(ifnull(`v`.`total_quantity_in_box`, ''), ':No. Of Package:O:N:') as `total_quantity_in_box`,
    concat(ifnull(`v`.`weight_per_box_item`, ''), ':Weight Per Package:O:N:') as `weight_per_box_item`,
    concat(ifnull(`v`.`product_type_short_name`, ''), ':Product Type Short Name:O:N:') as `product_type_short_name`,
    concat(ifnull(`v`.`product_packing_name`, ''), ':Product Packing Name:Y:C:smv_product_packing') as `product_packing_name`,
    concat(ifnull(`v`.`quantity_per_packing`, ''), ':Quantity Per Packing:O:N:') as `quantity_per_packing`,
    concat(ifnull(`v`.`product_unit_name`, ''), ':Product Unit Name:Y:C:smv_product_unit:F') as `product_unit_name`,
    concat(ifnull(`v`.`financial_year`, ''), ':Financial Year:O:N:') as `financial_year`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`company_branch_name`, ''), ':Company Branch:Y:C:cmv_company_branch:F') as `company_branch_name`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`stock_transaction_id`, ''), ':Stock Transaction Id:O:N:') as `stock_transaction_id`,
    concat(ifnull(`v`.`product_type_id`, ''), ':Product Type Id:N:N:') as `product_type_id`,
    concat(ifnull(`v`.`product_rm_id`, ''), ':Product Rm Id:O:N:') as `product_rm_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Product Material Unit Id:N:N:') as `product_material_unit_id`,
    concat(ifnull(`v`.`product_material_packing_id`, ''), ':Product Material Packing Id:N:N:') as `product_material_packing_id`,
    concat(ifnull(`v`.`godown_id`, ''), ':Godown Id:N:N:') as `godown_id`,
    concat(ifnull(`v`.`godown_section_id`, ''), ':Godown Section Id:N:N:') as `godown_section_id`,
    concat(ifnull(`v`.`godown_section_beans_id`, ''), ':Godown Section Beans Id:N:N:') as `godown_section_beans_id`
from
    `smv_product_rm_stock_summary` `v`
limit 1;


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


 update am_modules_forms set is_delete = 1 where modules_forms_id in (104, 149, 150, 152, 153, 158, 159, 160, 161, 162, 169, 273, 615);
 update am_modules_forms_user_access set is_delete = 1 where modules_forms_id in (104, 149, 150, 158, 159, 160, 161, 162, 169, 273, 615);
