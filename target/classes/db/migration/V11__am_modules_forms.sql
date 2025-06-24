-- Yasaswa
INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
(1,1,1,27,5,21,'Weaving Daily Production Report',6,'Weaving Daily Production Report','Production','<FrmWeavingDailyProductionReportListing/>','import FrmWeavingDailyProductionReportListing from "./Transactions/TWeavingDailyProductionReport/TWeavingDailyProductionReportListing";','./Transactions/TWeavingDailyProductionReport/TWeavingDailyProductionReportListing','<FrmWeavingDailyProductionReportEntry/>','import FrmWeavingDailyProductionReportEntry from "./Transactions/TWeavingDailyProductionReport/TWeavingDailyProductionReportEntry";','/Transactions/TWeavingDailyProductionReport/TWeavingDailyProductionReportEntry',NULL,'Weaving Daily Production Report',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');

INSERT INTO am_modules_forms_user_access (company_id,company_branch_id,user_type,user_id,modules_forms_id,module_id,sub_module_id,all_access,read_access,add_access,modify_access,delete_access,approve_access,special_access,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,access_control,user_code,company_access) VALUES
	 (1,1,'Administrators','1',642,1,27,1,1,1,1,1,1,1,1,0,'admin','2024-05-24 17:38:14.000',NULL,'2024-05-24 17:38:14.000',NULL,NULL,'Y:Y:Y:Y:Y:Y:Y','S0000','0');

-- Changes in 'xt_weaving_production_inspection_details'
ALTER TABLE xt_weaving_production_inspection_details MODIFY COLUMN weaving_production_inspection_master_id bigint(20) NULL;
ALTER TABLE xt_weaving_production_inspection_details MODIFY COLUMN inspection_production_code varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE xt_weaving_production_inspection_details MODIFY COLUMN plant_id bigint(20) NULL;
ALTER TABLE xt_weaving_production_inspection_details MODIFY COLUMN inspection_production_status varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'P' NULL COMMENT '''P-Pending,A-Aprroved, R-Rejected, C-Completed, X-Canceled,I-Partial'', D-Dispatch Done, DG-Dispatch Note Generated';
ALTER TABLE xt_weaving_production_inspection_details MODIFY COLUMN product_id varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE xt_weaving_production_inspection_details MODIFY COLUMN section_id bigint(20) NULL;
ALTER TABLE xt_weaving_production_inspection_details MODIFY COLUMN godown_id bigint(20) NULL;
ALTER TABLE xt_weaving_production_inspection_details MODIFY COLUMN sub_section_id bigint(20) NULL;