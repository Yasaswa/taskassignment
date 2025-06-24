INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
     (33,'CustomerTypes',1,'YARN SALES','YS',1,0,'dakshabhiadmin','2024-08-26 14:27:00.000',NULL,'2024-08-26 14:27:00.000',NULL,NULL,'',''),
     (33,'CustomerTypes',1,'JOB WORK','JW',1,0,'dakshabhiadmin','2024-08-26 14:27:41.000',NULL,'2024-08-26 14:27:41.000',NULL,NULL,'',''),
     (33,'CustomerTypes',1,'FABRIC SALES','FS',1,0,'dakshabhiadmin','2024-08-26 14:28:08.000',NULL,'2024-08-26 14:28:08.000',NULL,NULL,'','');

UPDATE am_properties
SET is_delete = 1
WHERE property_id IN (337, 338, 339, 340, 341, 343, 563, 564, 565, 566, 567);
