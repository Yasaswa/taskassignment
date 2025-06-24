

INSERT INTO am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) VALUES
	 (2,'NoOfWarpCounts',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'');

INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (204,'NoOfWarpCounts',2,'SINGLE COUNT','1',1,0,'7436002841','2024-10-15 14:59:02.000','7436002841','2024-10-15 15:00:05.000',NULL,NULL,'',''),
	 (204,'NoOfWarpCounts',2,'DOUBLE COUNT','2',1,0,'7436002841','2024-10-15 14:59:02.000','7436002841','2024-10-15 15:00:05.000',NULL,NULL,'','');

INSERT INTO sm_product_type_dynamic_controls (company_id,product_type_id,control_name,control_type,control_master,remark,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,display_sequence,product_category_id) VALUES
	 (2,9,'NO.OF WARP COUNTS','P','NoOfWarpCounts','',1,0,'dakshabhiadmin','2025-05-05 18:42:09.000',NULL,'2025-05-05 18:42:09.000',NULL,NULL,NULL,NULL);

