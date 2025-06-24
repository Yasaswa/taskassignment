INSERT INTO am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) VALUES
	 (2,'BottomYarnTareType1',0,0,NULL,NULL,NULL,NULL,NULL,NULL,'BYTT'),
	 (2,'BottomYarnTareType2',0,0,NULL,NULL,NULL,NULL,NULL,NULL,'BYTT'),
	 (2,'BottomYarnTareType3',0,0,NULL,NULL,NULL,NULL,NULL,NULL,'BYTT'),
	 (2,'BottomYarnTareType4',0,0,NULL,NULL,NULL,NULL,NULL,NULL,'BYTT'),
	 (2,'BottomYarnTareType5',0,0,NULL,NULL,NULL,NULL,NULL,NULL,'BYTT');


INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (185,'BottomYarnTareType1',2,'Paper Cone','0.043',1,0,'6260537025','2024-12-30 10:17:30.000',NULL,'2024-12-30 10:17:30.000',NULL,NULL,'BYTT',''),
	 (185,'BottomYarnTareType1',2,'Plastic Cone','0.05',1,0,'6260537025','2024-12-30 10:18:56.000',NULL,'2024-12-30 10:18:56.000',NULL,NULL,'BYTT',''),
	 (185,'BottomYarnTareType1',2,'Paper Tube','0.065',1,0,'6260537025','2024-12-30 10:20:12.000',NULL,'2024-12-30 10:20:12.000',NULL,NULL,'BYTT',''),
	 (186,'BottomYarnTareType2',2,'HDPE Bag','0.2',1,0,'6260537025','2024-12-30 10:21:07.000',NULL,'2024-12-30 10:21:07.000',NULL,NULL,'BYTT',''),
	 (186,'BottomYarnTareType2',2,'Corrugated Box','1.5',1,0,'6260537025','2024-12-30 10:22:15.000',NULL,'2024-12-30 10:22:15.000',NULL,NULL,'BYTT','');
