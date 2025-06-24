INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,8,6,36,'Daily Manpower Register',8,'Daily Manpower Register','Registers','<DailyManPowerRegister compType=''Register'' />','import DailyManPowerRegister from "./Transactions/THRPayroll/AttendanceRegisters/DailyManPowerRegister";','/THRPayroll/AttendanceRegisters/DailyManPowerRegister/reg','<DailyManPowerRegister compType=''Register'' />','import DailyManPowerRegister from "./Transactions/THRPayroll/AttendanceRegisters/DailyManPowerRegister";','/THRPayroll/AttendanceRegisters/DailyManPowerRegister/reg',NULL,'Daily Manpower Register',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


	 ALTER TABLE cm_department ADD general_worker bigint(20) DEFAULT 0 NULL;
     ALTER TABLE cm_department ADD trainee_worker bigint(20) DEFAULT 0 NULL;
     ALTER TABLE cm_department ADD semi_skilled_worker bigint(20) DEFAULT 0 NULL;
     ALTER TABLE cm_department ADD skilled_worker bigint(20) DEFAULT 0 NULL;
     ALTER TABLE cm_department ADD sr_semi_skilled_worker bigint(20) DEFAULT 0 NULL;
     ALTER TABLE cm_department ADD helper_worker bigint(20) DEFAULT 0 NULL;