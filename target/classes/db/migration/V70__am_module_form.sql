-- erp_development.ht_comp_off_intimation_details definition

CREATE TABLE `ht_comp_off_intimation_details` (
  `company_id` bigint(20) NOT NULL DEFAULT 1,
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1,
  `comp_off_intimation_details_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) NOT NULL,
  `punch_code` varchar(100) DEFAULT NULL,
  `employee_code` varchar(255) NOT NULL,
  `att_date_time` date NOT NULL,
  `status` varchar(255) NOT NULL DEFAULT 'Pending',
  `weeklyoff_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) NOT NULL,
  `approval_remark` varchar(255) DEFAULT NULL,
  `approved_by_id` bigint(20) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `employee_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`comp_off_intimation_details_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO am_modules_forms
     (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,
      modules_forms_name,display_sequence,display_name,menu_type,
      listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,
      form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,
      created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,4,7,33,'Comp. Leave Request Form',6,'Comp. Leave Request Form','HR Payroll','Comp. Leave Request Form',
	 'import CompLeaveRequestForm from "./Transactions/THRPayroll/CompLeaveRequestForm";','/Transactions/THRPayroll/CompLeaveRequestForm',
	 'Comp. Leave Request Form','import CompLeaveRequestForm from "./Transactions/THRPayroll/CompLeaveRequestForm";','/Transactions/THRPayroll/CompLeaveRequestForm',NULL,'Comp. Leave Request Form',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


	 INSERT INTO am_modules_forms_user_access (company_id,company_branch_id,user_type,user_id,modules_forms_id,module_id,sub_module_id,all_access,read_access,add_access,modify_access,delete_access,approve_access,special_access,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,access_control,user_code,company_access) VALUES
     	 (1,1,'Administrators','1',647,1,16,1,1,1,1,1,1,1,1,0,'admin','2024-05-23 13:53:43.000',NULL,'2024-05-23 13:53:43.000',NULL,NULL,'Y:Y:Y:Y:Y:Y:Y','S0000','0');