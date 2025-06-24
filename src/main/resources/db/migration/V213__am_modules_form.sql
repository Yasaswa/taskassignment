INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,6,27,'Stock Aging Report',8,'Stock Aging Report','Registers','<FrmStockAgingReport />','import FrmStockAgingReport from "./Transactions/TPurchaseReports/StockAgingReport";','/Transactions/TPurchaseReports/StockAgingReport','','','',NULL,'Stock Aging Report',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');




INSERT INTO am_modules_forms_user_access (company_id,company_branch_id,user_type,user_id,modules_forms_id,module_id,sub_module_id,all_access,read_access,add_access,modify_access,delete_access,approve_access,special_access,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,access_control,user_code,company_access) VALUES
	 (1,1,'Administrators','1',676,1,3,1,1,1,1,1,1,1,1,0,'admin','2024-05-23 13:53:43.000',NULL,'2024-05-23 13:53:43.000',NULL,NULL,'Y:Y:Y:Y:Y:Y:Y','S0000','0'),
	 (1,1,'Administrators','1',671,1,3,1,1,1,1,1,1,1,1,0,'admin','2024-05-23 13:53:43.000',NULL,'2024-05-23 13:53:43.000',NULL,NULL,'Y:Y:Y:Y:Y:Y:Y','S0000','0'),
	 (1,1,'Administrators','1',669,1,3,1,1,1,1,1,1,1,1,0,'admin','2024-05-23 13:53:43.000',NULL,'2024-05-23 13:53:43.000',NULL,NULL,'Y:Y:Y:Y:Y:Y:Y','S0000','0');
