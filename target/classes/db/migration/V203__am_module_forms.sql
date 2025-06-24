






update am_modules_forms set is_delete  = 1 where  modules_forms_name = 'Pending Indents';


INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,6,27,'Pending Indent (General Stores & Spares)',2,'Pending Indent (General Stores & Spares)','Registers','<PendingIndentListing compType=''''Register'''' />','import IndentListing from "/Transactions/TPurchaseOrder/PendingIndentReports/reg";','/Transactions/TPurchaseOrder/PendingIndentReports/reg','','','',NULL,'',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'RM'),
	 (1,1,1,16,6,27,'Pending Indent (Raw Materials)',3,'Pending Indent (Raw Materials)','Registers','<PendingIndentListing compType=''''Register'''' />','import IndentListing from "/Transactions/TPurchaseOrder/PendingIndentReports/reg";','/Transactions/TPurchaseOrder/PendingIndentReports/reg','','','',NULL,'',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'PRM');


