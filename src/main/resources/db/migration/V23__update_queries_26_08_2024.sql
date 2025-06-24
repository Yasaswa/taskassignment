-- ERP_PASHUPATI_PROD_1_0.smv_product_type_dynamic_controls source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `smv_product_type_dynamic_controls` AS
select
    `sm`.`control_master` AS `control_master`,
    `sm`.`control_name` AS `control_name`,
    `sm`.`control_type` AS `control_type`,
    `p`.`product_type_name` AS `product_type_name`,
    `p`.`product_type_group` AS `product_type_group`,
    `p`.`product_type_short_name` AS `product_type_short_name`,
    `v`.`company_legal_name` AS `company_name`,
    `sm`.`display_sequence` AS `display_sequence`,
    `sm`.`remark` AS `remark`,
    case
        `sm`.`is_active` when 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        `sm`.`is_delete` when 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `sm`.`is_active` AS `is_active`,
    `sm`.`is_delete` AS `is_delete`,
    `sm`.`created_by` AS `created_by`,
    `sm`.`created_on` AS `created_on`,
    `sm`.`modified_by` AS `modified_by`,
    `sm`.`modified_on` AS `modified_on`,
    `sm`.`deleted_by` AS `deleted_by`,
    `sm`.`deleted_on` AS `deleted_on`,
    `sm`.`company_id` AS `company_id`,
    `sm`.`product_type_id` AS `product_type_id`,
    `sm`.`product_type_dynamic_controls_id` AS `product_type_dynamic_controls_id`,
    `sm`.`product_category_id` AS `product_category_id`,
    `sm`.`product_type_dynamic_controls_id` AS `field_id`,
    `sm`.`control_name` AS `field_name`
from
    ((`sm_product_type_dynamic_controls` `sm`
left join `sm_product_type` `p` on
    (`p`.`product_type_id` = `sm`.`product_type_id`))
left join `cmv_company_summary` `v` on
    (`v`.`company_id` = `sm`.`company_id`))
where
    `sm`.`is_delete` = 0;

INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,19,3,17,'Daily Dispatch Challan Report',8,'Daily Dispatch Challan Report','Sales','<TDailyDispatchChallanReportListing />','import FrmDailyDispatchChallanReportListing from "./Transactions/TDailyDispatchChallanReport/TDailyDispatchChallanReportListing";','/Transactions/TDailyDispatchChallanReport/TDailyDispatchChallanReportListing','<TDailyDipatchChallanReportEntry />','import FrmDailyDispatchChallanReportEntry from "./Transactions/TDailyDispatchChallanReport/TDailyDipatchChallanReportEntry";',' ./Transactions/TDailyDispatchChallanReport/TDailyDipatchChallanReportEntry',NULL,'',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


INSERT INTO am_modules_forms_user_access (company_id,company_branch_id,user_type,user_id,modules_forms_id,module_id,sub_module_id,all_access,read_access,add_access,modify_access,delete_access,approve_access,special_access,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,access_control,user_code,company_access) VALUES
	 (1,1,'Administrators','1',645,1,3,1,1,1,1,1,1,1,1,0,'admin','2024-05-23 13:53:43.000',NULL,'2024-05-23 13:53:43.000',NULL,NULL,'Y:Y:Y:Y:Y:Y:Y','S0000','0');

ALTER TABLE mt_dispatch_challan_master_trading MODIFY COLUMN expected_branch_id bigint(20) DEFAULT 1 NULL COMMENT 'Header combo box with  cmv_company_branch show address, Contact person list ,emails, company branch state combo,customer city Combo   ';
