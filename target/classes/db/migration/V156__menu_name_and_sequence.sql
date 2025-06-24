UPDATE am_modules_sub_menu SET company_id=1, company_branch_id=1, modules_id=1, sub_modules_id=16, modules_type='T', modules_menu_id=2, modules_sub_menu_name='Material Issue / Return', display_sequence=6, icon_class='dashboard', is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL WHERE modules_sub_menu_id=12;

INSERT INTO am_modules_sub_menu (company_id,company_branch_id,modules_id,sub_modules_id,modules_type,modules_menu_id,modules_sub_menu_name,display_sequence,icon_class,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
	 (1,1,1,16,'T',2,'Material Requisition',5,'dashboard',1,0,NULL,NULL,NULL,NULL,NULL,NULL);

UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=2, modules_sub_menu_id=10, modules_forms_name='Goods Receipt Notes(General Stores & Spares)', display_sequence=1, display_name='Goods Receipt Notes(General Stores & Spares)', menu_type='Purchase', listing_component_name='<FrmGoodsReceiptNoteListing />', listing_component_import_path='import FrmGoodsReceiptNoteListing from "Transactions/TPurchaseOrder/GoodsReceiptNote/FrmGoodsReceiptNoteListing";', listing_navigation_url='/Transactions/TPurchaseOrder/GoodsReceiptNote/GoodsReceiptNoteListing', form_component_name='<FrmGoodsReceiptNoteEntry />', form_component_import_path='import FrmGoodsReceiptNoteEntry from "Transactions/TPurchaseOrder/GoodsReceiptNote/FrmGoodsReceiptNoteEntry";', form_navigation_url='/Transactions/TPurchaseOrder/GoodsReceiptNote/GoodsReceiptNoteEntry', icon_class=NULL, page_header='Goods Receipt Notes', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='RM' WHERE modules_forms_id=106;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=2, modules_sub_menu_id=71, modules_forms_name='General Stores & Spares Issue Slip Requisition Slip', display_sequence=1, display_name='Material Request Slip', menu_type='Purchase', listing_component_name='<FrmMaterialRequisitionLIsting/>', listing_component_import_path='import FrmMaterialRequisitionLIsting from "Transactions/TMaterialRequisition/FrmMaterialRequisitionLIsting";', listing_navigation_url='/Transactions/TMaterialRequisition/FrmMaterialRequisitionLIsting', form_component_name='<FrmMaterialRequisitionEntry/>', form_component_import_path='import FrmMaterialRequisitionEntry from "Transactions/TMaterialRequisition/FrmMaterialRequisitionEntry";', form_navigation_url='/Transactions/TMaterialRequisition/FrmMaterialRequisitionEntry', icon_class=NULL, page_header='Material Requisition', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='MR' WHERE modules_forms_id=298;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=2, modules_sub_menu_id=12, modules_forms_name='General Stores & Spares Issue Slip', display_sequence=1, display_name='General Stores & Spares Issue Slip', menu_type='Purchase', listing_component_name='<FrmMaterialRequisitionLIsting/>', listing_component_import_path='import FrmMaterialRequisitionLIsting from "Transactions/TMaterialRequisition/FrmMaterialRequisitionLIsting";', listing_navigation_url='/Transactions/TMaterialRequisition/FrmMaterialRequisitionLIsting', form_component_name='<FrmMaterialRequisitionEntry/>', form_component_import_path='import FrmMaterialRequisitionEntry from "Transactions/TMaterialRequisition/FrmMaterialRequisitionEntry";', form_navigation_url='/Transactions/TMaterialRequisition/FrmMaterialRequisitionEntry', icon_class=NULL, page_header='General Stores & Spares Issue Slip', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='MI' WHERE modules_forms_id=616;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=2, modules_sub_menu_id=10, modules_forms_name='Goods Receipt Notes(Product Raw Materials)', display_sequence=1, display_name='Goods Receipt Notes(Product Raw Materials)', menu_type='Purchase', listing_component_name='<FrmGoodsReceiptNoteListing />', listing_component_import_path='import FrmGoodsReceiptNoteListing from "Transactions/TPurchaseOrder/GoodsReceiptNote/FrmGoodsReceiptNoteListing";', listing_navigation_url='/Transactions/TPurchaseOrder/GoodsReceiptNote/GoodsReceiptNoteListing', form_component_name='<FrmGoodsReceiptNoteEntry />', form_component_import_path='import FrmGoodsReceiptNoteEntry from "Transactions/TPurchaseOrder/GoodsReceiptNote/FrmGoodsReceiptNoteEntry";', form_navigation_url='/Transactions/TPurchaseOrder/GoodsReceiptNote/GoodsReceiptNoteEntry', icon_class=NULL, page_header='Goods Receipt Notes', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='PRM' WHERE modules_forms_id=659;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=2, modules_sub_menu_id=12, modules_forms_name='Yarn Issue Slip', display_sequence=1, display_name='Yarn Issue Slip', menu_type='Purchase', listing_component_name='<FrmRawMaterialRequisitionListing />', listing_component_import_path='import FrmRawMaterialRequisitionListing from "./Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionListing";', listing_navigation_url='/Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionListing', form_component_name='<FrmRawMaterialRequisitionEntry />', form_component_import_path='import FrmRawMaterialRequisitionEntry from "./Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionEntry";', form_navigation_url='/Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionEntry', icon_class=NULL, page_header='Yarn Issue Slip', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='RMI' WHERE modules_forms_id=669;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=2, modules_sub_menu_id=12, modules_forms_name='Yarn Return From Dept. To Godown', display_sequence=1, display_name='Yarn Return From Dept. To Godown', menu_type='Purchase', listing_component_name='<FrmRawMaterialReturnListing />', listing_component_import_path='import FrmRawMaterialReturnListing from "./Transactions/TRawMaterialReturn/FrmRawMaterialReturnListing";', listing_navigation_url='/Transactions/RawMaterialReturnListing', form_component_name='<FrmRawMaterialReturnEntry />', form_component_import_path='import FrmRawMaterialRequisitionEntry from import FrmRawMaterialReturnEntry from "./Transactions/TRawMaterialReturn/FrmRawMaterialReturnEntry";', form_navigation_url='/Transactions/RawMaterialReturnEntry', icon_class=NULL, page_header='Yarn Return From Dept. To Godown', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='' WHERE modules_forms_id=670;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=2, modules_sub_menu_id=71, modules_forms_name='Yarn Requisition Slip', display_sequence=1, display_name='Yarn Requisition Slip', menu_type='Purchase', listing_component_name='<FrmRawMaterialRequisitionListing />', listing_component_import_path='import FrmRawMaterialRequisitionListing from "./Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionListing";', listing_navigation_url='/Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionListing', form_component_name='<FrmRawMaterialRequisitionEntry />', form_component_import_path='import FrmRawMaterialRequisitionEntry from "./Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionEntry";', form_navigation_url='/Transactions/TRawMaterialRequisition/FrmRawMaterialRequisitionEntry', icon_class=NULL, page_header='Yarn Requisition Slip', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='RMR' WHERE modules_forms_id=671;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=2, modules_sub_menu_id=71, modules_forms_name='General Stores & Spares Issue Slip Requisition Slip', display_sequence=1, display_name='General Stores & Spares Issue Slip Requisition Slip', menu_type='Purchase', listing_component_name='<FrmMaterialRequisitionLIsting/>', listing_component_import_path='import FrmMaterialRequisitionLIsting from "Transactions/TMaterialRequisition/FrmMaterialRequisitionLIsting";', listing_navigation_url='/Transactions/TMaterialRequisition/FrmMaterialRequisitionLIsting', form_component_name='<FrmMaterialRequisitionEntry/>', form_component_import_path='import FrmMaterialRequisitionEntry from "Transactions/TMaterialRequisition/FrmMaterialRequisitionEntry";', form_navigation_url='/Transactions/TMaterialRequisition/FrmMaterialRequisitionEntry', icon_class=NULL, page_header='Material Requisition', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='MR' WHERE modules_forms_id=298;
UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=2, modules_sub_menu_id=71, modules_forms_name='General Stores & Spares Requisition Slip', display_sequence=1, display_name='General Stores & Spares Requisition Slip', menu_type='Purchase', listing_component_name='<FrmMaterialRequisitionLIsting/>', listing_component_import_path='import FrmMaterialRequisitionLIsting from "Transactions/TMaterialRequisition/FrmMaterialRequisitionLIsting";', listing_navigation_url='/Transactions/TMaterialRequisition/FrmMaterialRequisitionLIsting', form_component_name='<FrmMaterialRequisitionEntry/>', form_component_import_path='import FrmMaterialRequisitionEntry from "Transactions/TMaterialRequisition/FrmMaterialRequisitionEntry";', form_navigation_url='/Transactions/TMaterialRequisition/FrmMaterialRequisitionEntry', icon_class=NULL, page_header='General Stores & Spares Requisition Slip', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='MR' WHERE modules_forms_id=298;


-- ERP_PASHUPATI_PROD_1_0.stv_indent_material_issue_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_details` AS
select
    `st`.`issue_no` AS `issue_no`,
    `st`.`issue_date` AS `issue_date`,
    `st`.`issue_version` AS `issue_version`,
    `v`.`department_name` AS `department_name`,
    `v`.`sub_department_name` AS `sub_department_name`,
    case
        `st`.`issue_item_status` when 'MI' then 'Material Issue'
        when 'C' then 'Completed'
        when 'I' then 'Partial Issue'
        when 'AC' then 'Accepted'
        else 'Pending'
    end AS `issue_item_status_desc`,
    `e`.`employee_name` AS `indented_by_name`,
    `v`.`customer_name` AS `customer_name`,
    `v`.`expected_schedule_date` AS `expected_schedule_date`,
    `st`.`customer_order_no` AS `customer_order_no`,
    `rmfgsr`.`product_type_group` AS `product_type_group`,
    `st`.`product_material_id` AS `product_material_id`,
    `rmfgsr`.`product_material_name` AS `product_material_name`,
    `rmfgsr`.`actual_count` AS `actual_count`,
    `st`.`product_material_indent_quantity` AS `product_material_indent_quantity`,
    `st`.`product_material_indent_weight` AS `product_material_indent_weight`,
    `st`.`product_material_issue_quantity` AS `product_material_issue_quantity`,
    `st`.`product_material_issue_weight` AS `product_material_issue_weight`,
    `st`.`product_material_issue_boxes` AS `product_material_issue_boxes`,
    `st`.`product_material_receipt_quantity` AS `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` AS `product_material_receipt_weight`,
    `rmfgsr`.`product_material_name` AS `product_rm_name`,
    `rmfgsr`.`product_material_drawing_no` AS `product_rm_drawing_no`,
    `rmfgsr`.`product_material_tech_spect` AS `product_rm_tech_spect`,
    `st`.`product_material_issue_return_quantity` AS `product_material_issue_return_quantity`,
    `rmfgsr`.`product_type_name` AS `product_type_name`,
    `rmfgsr`.`product_material_make_name` AS `product_make_name`,
    `rmfgsr`.`product_material_type_name` AS `product_material_type_name`,
    `rmfgsr`.`product_material_stock_unit_name` AS `product_material_unit_name`,
    `rmfgsr`.`lead_time` AS `product_lead_time`,
    `ps`.`product_material_stock_quantity` AS `product_material_stock_quantity`,
    `ps`.`product_material_stock_weight` AS `product_material_stock_weight`,
    `ps`.`product_Rawmaterial_stock_quantity` AS `product_Rawmaterial_stock_quantity`,
    `ps`.`product_Rawmaterial_stock_weight` AS `product_Rawmaterial_stock_weight`,
    `ps`.`reserve_quantity` AS `reserve_quantity`,
    `ps`.`reserve_weight` AS `reserve_weight`,
    ifnull(`sd`.`closing_balance_quantity`, 0) AS `closing_balance_quantity`,
    ifnull(`sd`.`closing_balance_weight`, 0) AS `closing_balance_weight`,
    ifnull(`sd`.`weight_per_box_item`, 0) AS `cone_per_wt`,
    `st`.`product_material_approved_quantity` AS `product_material_approved_quantity`,
    `st`.`product_material_approved_weight` AS `product_material_approved_weight`,
    `st`.`product_material_rejected_quantity` AS `product_material_rejected_quantity`,
    `st`.`product_material_rejected_weight` AS `product_material_rejected_weight`,
    `st`.`product_material_issue_return_weight` AS `product_material_issue_return_weight`,
    `v`.`customer_state_name` AS `customer_state_name`,
    `v`.`cust_branch_gst_no` AS `cust_branch_gst_no`,
    `st`.`product_std_weight` AS `product_std_weight`,
    `st`.`indent_no` AS `indent_no`,
    `st`.`indent_date` AS `indent_date`,
    `st`.`indent_version` AS `indent_version`,
    `st`.`issue_item_status` AS `issue_item_status`,
    `st`.`issue_batch_no` AS `issue_batch_no`,
    `st`.`material_batch_rate` AS `material_batch_rate`,
    `st`.`product_material_issue_quantity` * `st`.`material_batch_rate` AS `material_issue_amount`,
    `fmp`.`profit_center_name` AS `profit_center_name`,
    `fm`.`cost_center_name` AS `cost_center_name`,
    `st`.`routing_code` AS `routing_code`,
    `v`.`company_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `st`.`financial_year` AS `financial_year`,
    `st`.`issue_remark` AS `issue_remark`,
    `gd`.`godown_name` AS `godown_name`,
    `gds`.`godown_section_name` AS `godown_section_name`,
    `gdsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `st`.`godown_id` AS `godown_id`,
    `st`.`godown_section_id` AS `godown_section_id`,
    `st`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `st`.`cost_center_id` AS `cost_center_id`,
    `st`.`goods_receipt_no` AS `goods_receipt_no`,
    `st`.`profit_center_id` AS `profit_center_id`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'Inactive'
    end AS `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `st`.`is_active` AS `is_active`,
    `st`.`is_delete` AS `is_delete`,
    `st`.`created_by` AS `created_by`,
    `st`.`created_on` AS `created_on`,
    `st`.`modified_by` AS `modified_by`,
    `st`.`modified_on` AS `modified_on`,
    `st`.`deleted_by` AS `deleted_by`,
    `st`.`deleted_on` AS `deleted_on`,
    `v`.`customer_id` AS `customer_id`,
    `v`.`department_id` AS `department_id`,
    `st`.`indented_by_id` AS `indented_by_id`,
    `v`.`issued_by_id` AS `issued_by_id`,
    `v`.`company_id` AS `company_id`,
    `v`.`company_branch_id` AS `company_branch_id`,
    `v`.`indent_issue_type_id` AS `product_type_id`,
    `rmfgsr`.`product_material_type_id` AS `product_material_type_id`,
    `rmfgsr`.`product_material_grade_id` AS `product_material_grade_id`,
    `rmfgsr`.`product_material_packing_id` AS `product_material_packing_id`,
    `st`.`product_material_unit_id` AS `product_material_unit_id`,
    `st`.`issue_details_transaction_id` AS `issue_details_transaction_id`,
    `st`.`issue_master_transaction_id` AS `issue_master_transaction_id`,
    `st`.`indent_details_id` AS `indent_details_id`,
    `v`.`sub_department_id` AS `sub_department_id`,
    `v`.`issue_no` AS `field_name`,
    `v`.`issue_version` AS `field_id`
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
        `sm_product_rm_stock_summary`.`product_rm_id` AS `product_rm_id`,
        `sm_product_rm_stock_summary`.`godown_id` AS `godown_id`,
        `sm_product_rm_stock_summary`.`company_id` AS `company_id`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_quantity`) AS `product_material_stock_quantity`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_weight`) AS `product_material_stock_weight`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_quantity`) AS `product_Rawmaterial_stock_quantity`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_weight`) AS `product_Rawmaterial_stock_weight`,
        sum(`sm_product_rm_stock_summary`.`reserve_quantity`) AS `reserve_quantity`,
        sum(`sm_product_rm_stock_summary`.`reserve_weight`) AS `reserve_weight`
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
        `sm_product_rm_stock_details`.`goods_receipt_no` AS `goods_receipt_no`,
        `sm_product_rm_stock_details`.`batch_no` AS `batch_no`,
        `sm_product_rm_stock_details`.`day_closed` AS `day_closed`,
        `sm_product_rm_stock_details`.`closing_balance_quantity` AS `closing_balance_quantity`,
        `sm_product_rm_stock_details`.`closing_balance_weight` AS `closing_balance_weight`,
        `sm_product_rm_stock_details`.`weight_per_box_item` AS `weight_per_box_item`
    from
        `sm_product_rm_stock_details`
    where
        `sm_product_rm_stock_details`.`day_closed` = 0) `sd` on
    (`sd`.`goods_receipt_no` = `st`.`goods_receipt_no`
        and `sd`.`batch_no` = `st`.`issue_batch_no`))
where
    `st`.`is_delete` = 0;



UPDATE am_modules_forms SET company_id=1, company_branch_id=1, module_id=1, sub_module_id=16, modules_menu_id=6, modules_sub_menu_id=27, modules_forms_name='General Strores And Spares Issue Report', display_sequence=8, display_name='General Strores And Spares Issue Report', menu_type='Registers', listing_component_name='<FrmIssueReportEntry />', listing_component_import_path='import FrmIssueReportEntry from "./Transactions/TPurchaseReports/IssueReport";', listing_navigation_url='/Transactions/TPurchaseReports/IssueReport', form_component_name='', form_component_import_path='', form_navigation_url='', icon_class=NULL, page_header='General Strores And Spares Issue Report', is_selected=0, is_active=1, is_delete=0, created_by=NULL, created_on=NULL, modified_by=NULL, modified_on=NULL, deleted_by=NULL, deleted_on=NULL, is_protected=0, header=1, footer=1, url_parameter='RM' WHERE modules_forms_id=647;
INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,6,27,'Yarn Issue Report',8,'Yarn Issue Report','Registers','<FrmIssueReportEntry />','import FrmIssueReportEntry from "./Transactions/TPurchaseReports/IssueReport";','/Transactions/TPurchaseReports/IssueReport','','','',NULL,'Yarn Issue Report',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'PRM');


-- ERP_PASHUPATI_PROD_1_0.stv_indent_material_issue_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_details` AS
select
    `st`.`issue_no` AS `issue_no`,
    `st`.`issue_date` AS `issue_date`,
    `st`.`issue_version` AS `issue_version`,
    `v`.`department_name` AS `department_name`,
    `v`.`sub_department_name` AS `sub_department_name`,
    case
        `st`.`issue_item_status` when 'MI' then 'Material Issue'
        when 'C' then 'Completed'
        when 'I' then 'Partial Issue'
        when 'AC' then 'Accepted'
        else 'Pending'
    end AS `issue_item_status_desc`,
    `e`.`employee_name` AS `indented_by_name`,
    `v`.`customer_name` AS `customer_name`,
    `v`.`expected_schedule_date` AS `expected_schedule_date`,
    `st`.`customer_order_no` AS `customer_order_no`,
    `rmfgsr`.`product_type_group` AS `product_type_group`,
    `st`.`product_material_id` AS `product_material_id`,
    `rmfgsr`.`product_material_name` AS `product_material_name`,
    `rmfgsr`.`actual_count` AS `actual_count`,
    `st`.`product_material_indent_quantity` AS `product_material_indent_quantity`,
    `st`.`product_material_indent_weight` AS `product_material_indent_weight`,
    `st`.`product_material_issue_quantity` AS `product_material_issue_quantity`,
    `st`.`product_material_issue_weight` AS `product_material_issue_weight`,
    `st`.`product_material_issue_boxes` AS `product_material_issue_boxes`,
    `st`.`product_material_receipt_quantity` AS `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` AS `product_material_receipt_weight`,
    `rmfgsr`.`product_material_name` AS `product_rm_name`,
    `rmfgsr`.`product_material_drawing_no` AS `product_rm_drawing_no`,
    `rmfgsr`.`product_material_tech_spect` AS `product_rm_tech_spect`,
    `st`.`product_material_issue_return_quantity` AS `product_material_issue_return_quantity`,
    `rmfgsr`.`product_type_name` AS `product_type_name`,
    `rmfgsr`.`product_material_make_name` AS `product_make_name`,
    `rmfgsr`.`product_material_type_name` AS `product_material_type_name`,
    `rmfgsr`.`product_material_stock_unit_name` AS `product_material_unit_name`,
    `rmfgsr`.`lead_time` AS `product_lead_time`,
    `ps`.`product_material_stock_quantity` AS `product_material_stock_quantity`,
    `ps`.`product_material_stock_weight` AS `product_material_stock_weight`,
    `ps`.`product_Rawmaterial_stock_quantity` AS `product_Rawmaterial_stock_quantity`,
    `ps`.`product_Rawmaterial_stock_weight` AS `product_Rawmaterial_stock_weight`,
    `ps`.`reserve_quantity` AS `reserve_quantity`,
    `ps`.`reserve_weight` AS `reserve_weight`,
    ifnull(`sd`.`closing_balance_quantity`, 0) AS `closing_balance_quantity`,
    ifnull(`sd`.`closing_balance_weight`, 0) AS `closing_balance_weight`,
    ifnull(`sd`.`weight_per_box_item`, 0) AS `cone_per_wt`,
    `st`.`product_material_approved_quantity` AS `product_material_approved_quantity`,
    `st`.`product_material_approved_weight` AS `product_material_approved_weight`,
    `st`.`product_material_rejected_quantity` AS `product_material_rejected_quantity`,
    `st`.`product_material_rejected_weight` AS `product_material_rejected_weight`,
    `st`.`product_material_issue_return_weight` AS `product_material_issue_return_weight`,
    `v`.`customer_state_name` AS `customer_state_name`,
    `v`.`cust_branch_gst_no` AS `cust_branch_gst_no`,
    `st`.`product_std_weight` AS `product_std_weight`,
    `st`.`indent_no` AS `indent_no`,
    `st`.`indent_date` AS `indent_date`,
    `st`.`indent_version` AS `indent_version`,
    `st`.`issue_item_status` AS `issue_item_status`,
    `st`.`issue_batch_no` AS `issue_batch_no`,
    `st`.`material_batch_rate` AS `material_batch_rate`,
    `st`.`product_material_issue_quantity` * `st`.`material_batch_rate` AS `material_issue_amount`,
    `fmp`.`profit_center_name` AS `profit_center_name`,
    `fm`.`cost_center_name` AS `cost_center_name`,
    `st`.`routing_code` AS `routing_code`,
    `v`.`company_name` AS `company_name`,
    `v`.`company_branch_name` AS `company_branch_name`,
    `st`.`financial_year` AS `financial_year`,
    `st`.`issue_remark` AS `issue_remark`,
    `gd`.`godown_name` AS `godown_name`,
    `gds`.`godown_section_name` AS `godown_section_name`,
    `gdsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `st`.`godown_id` AS `godown_id`,
    `st`.`godown_section_id` AS `godown_section_id`,
    `st`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `st`.`cost_center_id` AS `cost_center_id`,
    `st`.`goods_receipt_no` AS `goods_receipt_no`,
    `st`.`profit_center_id` AS `profit_center_id`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'Inactive'
    end AS `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `st`.`is_active` AS `is_active`,
    `st`.`is_delete` AS `is_delete`,
    `st`.`created_by` AS `created_by`,
    `st`.`created_on` AS `created_on`,
    `st`.`modified_by` AS `modified_by`,
    `st`.`modified_on` AS `modified_on`,
    `st`.`deleted_by` AS `deleted_by`,
    `st`.`deleted_on` AS `deleted_on`,
    `v`.`customer_id` AS `customer_id`,
    `v`.`department_id` AS `department_id`,
    `st`.`indented_by_id` AS `indented_by_id`,
    `v`.`issued_by_id` AS `issued_by_id`,
    `v`.`company_id` AS `company_id`,
    `v`.`company_branch_id` AS `company_branch_id`,
    `v`.`indent_issue_type_id` AS `product_type_id`,
    `rmfgsr`.`product_material_type_id` AS `product_material_type_id`,
    `rmfgsr`.`product_material_grade_id` AS `product_material_grade_id`,
    `rmfgsr`.`product_material_packing_id` AS `product_material_packing_id`,
    `st`.`product_material_unit_id` AS `product_material_unit_id`,
    `st`.`issue_details_transaction_id` AS `issue_details_transaction_id`,
    `st`.`issue_master_transaction_id` AS `issue_master_transaction_id`,
    `st`.`indent_details_id` AS `indent_details_id`,
    `v`.`sub_department_id` AS `sub_department_id`,
    `v`.`issue_no` AS `field_name`,
    `v`.`issue_version` AS `field_id`
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
        `sm_product_rm_stock_summary`.`product_rm_id` AS `product_rm_id`,
        `sm_product_rm_stock_summary`.`godown_id` AS `godown_id`,
        `sm_product_rm_stock_summary`.`company_id` AS `company_id`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_quantity`) AS `product_material_stock_quantity`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_weight`) AS `product_material_stock_weight`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_quantity`) AS `product_Rawmaterial_stock_quantity`,
        sum(`sm_product_rm_stock_summary`.`closing_balance_weight`) AS `product_Rawmaterial_stock_weight`,
        sum(`sm_product_rm_stock_summary`.`reserve_quantity`) AS `reserve_quantity`,
        sum(`sm_product_rm_stock_summary`.`reserve_weight`) AS `reserve_weight`
    from
        `sm_product_rm_stock_summary`
    group by
        `sm_product_rm_stock_summary`.`product_rm_id`,
        `sm_product_rm_stock_summary`.`godown_id`,
        `sm_product_rm_stock_summary`.`company_id`) `ps` on
    (`ps`.`product_rm_id` = `st`.`product_material_id`
        and `ps`.`godown_id` = `st`.`godown_id`
        and (`v`.`indent_issue_type_id` <> 12
            or `ps`.`company_id` = `st`.`company_id`)))
left join (
    select
        `sm_product_rm_stock_details`.`goods_receipt_no` AS `goods_receipt_no`,
        `sm_product_rm_stock_details`.`batch_no` AS `batch_no`,
        `sm_product_rm_stock_details`.`day_closed` AS `day_closed`,
        `sm_product_rm_stock_details`.`closing_balance_quantity` AS `closing_balance_quantity`,
        `sm_product_rm_stock_details`.`closing_balance_weight` AS `closing_balance_weight`,
        `sm_product_rm_stock_details`.`weight_per_box_item` AS `weight_per_box_item`
    from
        `sm_product_rm_stock_details`
    where
        `sm_product_rm_stock_details`.`day_closed` = 0) `sd` on
    (`sd`.`goods_receipt_no` = `st`.`goods_receipt_no`
        and `sd`.`batch_no` = `st`.`issue_batch_no`))
where
    `st`.`is_delete` = 0;