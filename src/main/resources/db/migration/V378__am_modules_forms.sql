INSERT INTO am_modules_menu (company_id,company_branch_id,modules_type,modules_id,sub_modules_id,modules_menu_name,display_sequence,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
	 (1,1,'T',1,2,'Help',8,1,0,NULL,NULL,NULL,NULL,NULL,NULL);

INSERT INTO am_modules_sub_menu (company_id,company_branch_id,modules_id,sub_modules_id,modules_type,modules_menu_id,modules_sub_menu_name,display_sequence,icon_class,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
     	 (1,1,1,2,'T',9,'Weaving',1,'dashboard',1,0,NULL,NULL,NULL,NULL,NULL,NULL);

INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,2,9,73,'Weaving/Sizing Help',1,'Weaving/Sizing Help','Purchase','<WeavingSizingHelp/>','import WeavingSizingHelp from "FrmGeneric/Help/WeavingSizingHelp";','/FrmGeneric/Help/WeavingSizingHelp','','','','1','Weaving/Sizing Help',0,1,0,'dakshabhiadmin','2025-03-13 15:45:08.000',NULL,'2025-03-13 15:45:08.000',NULL,NULL,0,1,1,'');


