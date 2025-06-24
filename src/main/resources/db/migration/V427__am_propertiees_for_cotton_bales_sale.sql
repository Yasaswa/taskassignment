INSERT INTO am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) VALUES
	 (2,'CottonBalesSalesDispatch',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'CSD'),
	 (2,'CottonBalesSalesHSNTaxType',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'CSD'),
	 (2,'CottonBalesSalesType',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'CSD'),
	 (2,'CottonBalesSalesVoucherType',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'CSD');


INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (200,'CottonBalesSalesDispatch',1,'Cotton Rui Sales','Cotton Rui Sales',1,0,'6260537025','2025-04-21 17:34:53.000','6260537025','2025-04-21 17:44:11.000',NULL,NULL,'Cotton Rui Sales','BT'),
	 (200,'CottonBalesSalesDispatch',1,'Cotton Waste','Cotton Waste',1,0,'6260537025','2025-04-21 17:45:12.000',NULL,'2025-04-21 17:45:12.000',NULL,NULL,'Cotton Waste','CW'),
	 (200,'CottonBalesSalesDispatch',1,'Cotton Yarn Sales','Cotton Yarn Sales',1,0,'6260537025','2025-04-21 17:45:56.000',NULL,'2025-04-21 17:45:56.000',NULL,NULL,'Cotton Yarn Sales','CYS'),
	 (201,'CottonBalesSalesHSNTaxType',1,'Cotton Rui Sales Bales-SGST','Cotton Rui Sales Bales-SGST',1,0,'6260537025','2025-04-21 18:01:36.000',NULL,'2025-04-21 18:01:36.000',NULL,NULL,'Cotton Rui Sales','BT'),
	 (202,'CottonBalesSalesType',1,'Shankar Rui (Cotton Bales)','Shankar Rui (Cotton Bales)',1,0,'6260537025','2025-04-21 18:03:17.000',NULL,'2025-04-21 18:03:17.000',NULL,NULL,'Cotton Rui Sales','BT'),
	 (203,'CottonBalesSalesVoucherType',1,'Bales Sales','Bales Sales',1,0,'6260537025','2025-04-21 18:04:01.000',NULL,'2025-04-21 18:04:01.000',NULL,NULL,'Cotton Rui Sales','BT'),
	 (201,'CottonBalesSalesHSNTaxType',1,'Cotton Waste Sales-IGST','Cotton Waste Sales-IGST',1,0,'6260537025','2025-04-21 18:13:57.000',NULL,'2025-04-21 18:13:57.000',NULL,NULL,'Cotton Waste','WTX'),
	 (201,'CottonBalesSalesHSNTaxType',1,'Cotton Waste Sales-SGST','Cotton Waste Sales-SGST',1,0,'6260537025','2025-04-21 18:15:27.000',NULL,'2025-04-21 18:15:27.000',NULL,NULL,'Cotton Waste','WTX'),
	 (201,'CottonBalesSalesHSNTaxType',1,'Scrap Sales @ 18%','Scrap Sales @ 18%',1,0,'6260537025','2025-04-21 18:16:47.000',NULL,'2025-04-21 18:16:47.000',NULL,NULL,'Cotton Waste','TX'),
	 (201,'CottonBalesSalesHSNTaxType',1,'Scrap Sales @ 5%','Scrap Sales @ 5%',1,0,'6260537025','2025-04-21 18:17:25.000',NULL,'2025-04-21 18:17:25.000',NULL,NULL,'Cotton Waste','TX');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (202,'CottonBalesSalesType',1,'Comber Cotton Waste-520290','Comber Cotton Waste-520290',1,0,'6260537025','2025-04-21 18:18:31.000',NULL,'2025-04-21 18:18:31.000',NULL,NULL,'Cotton Waste','WTX'),
	 (202,'CottonBalesSalesType',1,'Lickrin Cotton Waste, Hard Combo','Lickrin Cotton Waste, Hard Combo',1,0,'6260537025','2025-04-21 18:19:28.000',NULL,'2025-04-21 18:19:28.000',NULL,NULL,'Cotton Waste','WTX'),
	 (202,'CottonBalesSalesType',1,'Scrap Sales @ 18%','Scrap Sales @ 18%',1,0,'6260537025','2025-04-21 18:20:10.000',NULL,'2025-04-21 18:20:10.000',NULL,NULL,'Cotton Waste','TX'),
	 (202,'CottonBalesSalesType',1,'Scrap Sales @ 5%','Scrap Sales @ 5%',1,0,'6260537025','2025-04-21 18:20:46.000',NULL,'2025-04-21 18:20:46.000',NULL,NULL,'Cotton Waste','TX'),
	 (203,'CottonBalesSalesVoucherType',1,'Cotton Waste Sales','Cotton Waste Sales',1,0,'6260537025','2025-04-21 18:21:39.000',NULL,'2025-04-21 18:21:39.000',NULL,NULL,'Cotton Waste','WTX'),
	 (203,'CottonBalesSalesVoucherType',1,'Scrap Sale ','Scrap Sale ',1,0,'6260537025','2025-04-21 18:22:30.000',NULL,'2025-04-21 18:22:30.000',NULL,NULL,'Cotton Waste','TX'),
	 (201,'CottonBalesSalesHSNTaxType',1,'Cotton Yarn Sales - 5%','Cotton Yarn Sales - 5%',1,0,'6260537025','2025-04-21 18:23:41.000',NULL,'2025-04-21 18:23:41.000',NULL,NULL,'Cotton Yarn Sales','YTX'),
	 (201,'CottonBalesSalesHSNTaxType',1,'Cotton Yarn Sales - Return -SGST','Cotton Yarn Sales - Return -SGST',1,0,'6260537025','2025-04-21 18:24:45.000',NULL,'2025-04-21 18:24:45.000',NULL,NULL,'Cotton Yarn Sales','CRS'),
	 (201,'CottonBalesSalesHSNTaxType',1,'Cotton Yarn TFO Sales - 5%','Cotton Yarn TFO Sales - 5%',1,0,'6260537025','2025-04-21 18:25:28.000',NULL,'2025-04-21 18:25:28.000',NULL,NULL,'Cotton Yarn Sales','YTX'),
	 (202,'CottonBalesSalesType',1,'Cotton Yarn Combed - 2210','Cotton Yarn Combed - 2210',1,0,'6260537025','2025-04-21 18:26:42.000',NULL,'2025-04-21 18:26:42.000',NULL,NULL,'Cotton Yarn Sales','YTX');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (202,'CottonBalesSalesType',1,'Cotton Yarn Carded - 1210','Cotton Yarn Carded - 1210',1,0,'6260537025','2025-04-21 18:27:30.000',NULL,'2025-04-21 18:27:30.000',NULL,NULL,'Cotton Yarn Sales','CRS'),
	 (202,'CottonBalesSalesType',1,'Cotton Yarn TFO Combed - 4210','Cotton Yarn TFO Combed - 4210',1,0,'6260537025','2025-04-21 18:28:15.000',NULL,'2025-04-21 18:28:15.000',NULL,NULL,'Cotton Yarn Sales','YTX'),
	 (203,'CottonBalesSalesVoucherType',1,'Cotton Yarn Sales','Cotton Yarn Sales',1,0,'6260537025','2025-04-21 18:29:08.000',NULL,'2025-04-21 18:29:08.000',NULL,NULL,'Cotton Yarn Sales','YTX'),
	 (203,'CottonBalesSalesVoucherType',1,'Credit Note - Sales','Credit Note - Sales',1,0,'6260537025','2025-04-21 18:29:43.000',NULL,'2025-04-21 18:29:43.000',NULL,NULL,'Cotton Yarn Sales','CRS');
