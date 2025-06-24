INSERT INTO cm_godown (company_id,company_branch_id,product_type_id,godown_type,godown_name,godown_short_name,godown_area,godown_section_count,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
	 (2,3,9,'Both','Goods Return Godown','GRG',NULL,1,1,0,'dakshabhiadmin','2025-06-16 16:42:40.000',NULL,'2025-06-16 16:42:40.000',NULL,NULL);
INSERT INTO cm_godown_section (company_id,company_branch_id,godown_id,godown_section_name,godown_section_short_name,godown_section_area,section_beans_count,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
	 (2,3,20,'Goods Return Godown Section','GRGS',1.0000,1,1,0,NULL,'2025-06-16 16:42:40.000',NULL,'2025-06-16 16:42:40.000',NULL,NULL);

INSERT INTO cm_godown_section_beans (company_id,company_branch_id,godown_id,godown_section_id,godown_section_beans_name,godown_section_beans_short_name,godown_section_beans_area,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on) VALUES
	 (2,3,20,59,'Goods Return Godown Section Beans','GRGSB',1.0000,1,0,NULL,'2025-06-16 16:42:40.000',NULL,'2025-06-16 16:42:40.000',NULL,NULL);


INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,2,12,'Goods Return(Fabric)',4,'Goods Return(Fabric)','Purchase','<TGoodsReturnFabricListing />','import TGoodsReturnFabricListing from "Transactions/TGoodsReturnFabric/TGoodsReturnFabricListing"','/Transactions/TGoodsReturnFabric/TGoodsReturnFabricListing','<TGoodsReturnFabricEntry />','import TGoodsReturnFabricEntry from "Transactions/TGoodsReturnFabric/TGoodsReturnFabricEntry";','/Transactions/TGoodsReturnFabric/TGoodsReturnFabricEntry',NULL,'Goods Return(Fabric)',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


INSERT INTO am_properties_master (company_id,properties_master_name,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,remark) VALUES
	 (2,'GoodsPurchaseType',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'SD'),
	 (2,'GoodsPurchaseHSNTaxType',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'SD'),
	 (2,'GoodsPurchaseSalesType',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'SD'),
	 (2,'GoodsPurchaseVoucherType',1,0,NULL,NULL,NULL,NULL,NULL,NULL,'SD');




INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (207,'GoodsPurchaseType',2,'Consumer Good Purchase','Consumer Good Purchase',1,0,'dakshabhiadmin','2025-06-10 14:36:05.000',NULL,'2025-06-10 14:36:05.000',NULL,NULL,'Consumer Good Purchase',''),
	 (207,'GoodsPurchaseType',2,'Cotton Yarn Purchase','Cotton Yarn Purchase',1,0,'dakshabhiadmin','2025-06-10 14:36:51.000',NULL,'2025-06-10 14:36:51.000',NULL,NULL,'Cotton Yarn Purchase',''),
	 (207,'GoodsPurchaseType',2,'Export Purchase','Export Purchase',1,0,'dakshabhiadmin','2025-06-10 14:37:10.000',NULL,'2025-06-10 14:37:10.000',NULL,NULL,'Export Purchase',''),
	 (207,'GoodsPurchaseType',2,'Purchase Related Exp','Purchase Related Exp',1,0,'dakshabhiadmin','2025-06-10 14:37:22.000',NULL,'2025-06-10 14:37:22.000',NULL,NULL,'Purchase Related Exp',''),
	 (207,'GoodsPurchaseType',2,'Trading Purchase','Trading Purchase',1,0,'dakshabhiadmin','2025-06-10 14:37:40.000',NULL,'2025-06-10 14:37:40.000',NULL,NULL,'Trading Purchase',''),
	 (207,'GoodsPurchaseType',2,'Finish Fabric Purchase GST 5%','Finish Fabric Purchase GST 5%',1,0,'dakshabhiadmin','2025-06-10 14:37:58.000',NULL,'2025-06-10 14:37:58.000',NULL,NULL,'Finish Fabric Purchase GST 5%',''),
	 (207,'GoodsPurchaseType',2,'Grey Cloth Purchase -SGST','Grey Cloth Purchase -SGST',1,0,'dakshabhiadmin','2025-06-10 14:38:20.000',NULL,'2025-06-10 14:38:20.000',NULL,NULL,'Grey Cloth Purchase -SGST',''),
	 (207,'GoodsPurchaseType',2,'Job Work Expense-GST-5%','Job Work Expense-GST-5%',1,0,'dakshabhiadmin','2025-06-10 14:38:33.000',NULL,'2025-06-10 14:38:33.000',NULL,NULL,'Job Work Expense-GST-5%',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Machinery Spare parts & Maintenance','Machinery Spare parts & Maintenance',1,0,'dakshabhiadmin','2025-06-10 14:51:45.000',NULL,'2025-06-10 14:51:45.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Electrical Spare Parts-18% GST','Electrical Spare Parts-18% GST',1,0,'dakshabhiadmin','2025-06-10 14:53:02.000',NULL,'2025-06-10 14:53:02.000',NULL,NULL,'Consumer Good Purchase','');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (208,'GoodsPurchaseHSNTaxType',2,'Machinery Spare parts-IGST-18%','Machinery Spare parts-IGST-18%',1,0,'dakshabhiadmin','2025-06-10 14:53:18.000',NULL,'2025-06-10 14:53:18.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Machinery Spare parts-SGST-18%','Machinery Spare parts-SGST-18%',1,0,'dakshabhiadmin','2025-06-10 14:53:40.000',NULL,'2025-06-10 14:53:40.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Repairing and Maintenance (P& M)-IGST-18%','Repairing and Maintenance (P& M)-IGST-18%',1,0,'dakshabhiadmin','2025-06-10 14:55:25.000',NULL,'2025-06-10 14:55:25.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Repairing and Maintenance (P& M)-SGST-12%','Repairing and Maintenance (P& M)-SGST-12%',1,0,'dakshabhiadmin','2025-06-10 14:55:42.000',NULL,'2025-06-10 14:55:42.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Repairing and Maintenance (P& M)-SGST-18%','Repairing and Maintenance (P& M)-SGST-18%',1,0,'dakshabhiadmin','2025-06-10 14:55:59.000',NULL,'2025-06-10 14:55:59.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Repairs and Maintenance','Repairs and Maintenance',1,0,'dakshabhiadmin','2025-06-10 14:56:17.000',NULL,'2025-06-10 14:56:17.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Chemical Material-IGST-18%','Chemical Material-IGST-18%',1,0,'dakshabhiadmin','2025-06-10 14:56:33.000',NULL,'2025-06-10 14:56:33.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Chemical Material-SGST-12%','Chemical Material-SGST-12%',1,0,'dakshabhiadmin','2025-06-10 14:56:52.000',NULL,'2025-06-10 14:56:52.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Chemical Material-SGST-18%','Chemical Material-SGST-18%',1,0,'dakshabhiadmin','2025-06-10 14:57:08.000',NULL,'2025-06-10 14:57:08.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Chemical Material-SGST-5%','Chemical Material-SGST-5%',1,0,'dakshabhiadmin','2025-06-10 14:57:24.000',NULL,'2025-06-10 14:57:24.000',NULL,NULL,'Consumer Good Purchase','');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (208,'GoodsPurchaseHSNTaxType',2,'Firewood NIL','Firewood NIL',1,0,'dakshabhiadmin','2025-06-10 14:57:48.000',NULL,'2025-06-10 14:57:48.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Lubricants and Oil Purchase-SGST','Lubricants and Oil Purchase-SGST',1,0,'dakshabhiadmin','2025-06-10 14:58:09.000',NULL,'2025-06-10 14:58:09.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Packing Material-SGST-18%','Packing Material-SGST-18%',1,0,'dakshabhiadmin','2025-06-10 14:58:23.000',NULL,'2025-06-10 14:58:23.000',NULL,NULL,'Consumer Good Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Packing Material-SGST-5%','Packing Material-SGST-5%',1,0,'dakshabhiadmin','2025-06-10 14:58:39.000',NULL,'2025-06-10 14:58:39.000',NULL,NULL,'Consumer Good Purchase',''),
	 (209,'GoodsPurchaseSalesType',2,'Machinery Spare parts','Machinery Spare parts',1,0,'dakshabhiadmin','2025-06-10 15:24:09.000',NULL,'2025-06-10 15:24:09.000',NULL,NULL,'Consumer Good Purchase',''),
	 (209,'GoodsPurchaseSalesType',2,'Repairs and Maintenance','Repairs and Maintenance',1,0,'dakshabhiadmin','2025-06-10 15:24:40.000',NULL,'2025-06-10 15:24:40.000',NULL,NULL,'Consumer Good Purchase',''),
	 (209,'GoodsPurchaseSalesType',2,'Chemical Material','Chemical Material',1,0,'dakshabhiadmin','2025-06-10 15:25:20.000',NULL,'2025-06-10 15:25:20.000',NULL,NULL,'Consumer Good Purchase',''),
	 (209,'GoodsPurchaseSalesType',2,'Firewood NIL','Firewood NIL',1,0,'dakshabhiadmin','2025-06-10 15:25:49.000',NULL,'2025-06-10 15:25:49.000',NULL,NULL,'Consumer Good Purchase',''),
	 (210,'GoodsPurchaseVoucherType',2,'Purchase Register Expense','Purchase Register Expense',1,0,'dakshabhiadmin','2025-06-10 15:27:32.000',NULL,'2025-06-10 15:27:32.000',NULL,NULL,'Consumer Good Purchase','GP'),
	 (210,'GoodsPurchaseVoucherType',2,'Purchase Register Non-GST','Purchase Register Non-GST',1,0,'dakshabhiadmin','2025-06-10 15:28:07.000',NULL,'2025-06-10 15:28:07.000',NULL,NULL,'Consumer Good Purchase','CEX');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (208,'GoodsPurchaseHSNTaxType',2,'Cotton yarn Purchase-GST-12%','Cotton yarn Purchase-GST-12%',1,0,'dakshabhiadmin','2025-06-10 15:31:35.000',NULL,'2025-06-10 15:31:35.000',NULL,NULL,'Cotton Yarn Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Cotton yarn Purchase-GST-5%','Cotton yarn Purchase-GST-5%',1,0,'dakshabhiadmin','2025-06-10 15:31:48.000',NULL,'2025-06-10 15:31:48.000',NULL,NULL,'Cotton Yarn Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Cotton yarn Purchase-IGST-12%','Cotton yarn Purchase-IGST-12%',1,0,'dakshabhiadmin','2025-06-10 15:32:04.000',NULL,'2025-06-10 15:32:04.000',NULL,NULL,'Cotton Yarn Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Cotton yarn Purchase-IGST-5%','Cotton yarn Purchase-IGST-5%',1,0,'dakshabhiadmin','2025-06-10 15:32:21.000',NULL,'2025-06-10 15:32:21.000',NULL,NULL,'Cotton Yarn Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Cotton Yarn TFO-purchase GST','Cotton Yarn TFO-purchase GST',1,0,'dakshabhiadmin','2025-06-10 15:32:50.000',NULL,'2025-06-10 15:32:50.000',NULL,NULL,'Cotton Yarn Purchase',''),
	 (209,'GoodsPurchaseSalesType',2,'Yarn Cotton','Yarn Cotton',1,0,'dakshabhiadmin','2025-06-10 15:33:48.000',NULL,'2025-06-10 15:33:48.000',NULL,NULL,'Cotton Yarn Purchase',''),
	 (209,'GoodsPurchaseSalesType',2,'Yarn Cotton (Trading)','Yarn Cotton (Trading)',1,0,'dakshabhiadmin','2025-06-10 15:34:10.000',NULL,'2025-06-10 15:34:10.000',NULL,NULL,'Cotton Yarn Purchase',''),
	 (210,'GoodsPurchaseVoucherType',2,'purchase','purchase',1,0,'dakshabhiadmin','2025-06-10 15:34:39.000',NULL,'2025-06-10 15:34:39.000',NULL,NULL,'Cotton Yarn Purchase','RM'),
	 (210,'GoodsPurchaseVoucherType',2,'Purchase  Register Expense ','Purchase  Register Expense ',1,0,'dakshabhiadmin','2025-06-10 15:35:38.000',NULL,'2025-06-10 15:35:38.000',NULL,NULL,'Cotton Yarn Purchase','GP'),
	 (210,'GoodsPurchaseVoucherType',2,'Cotton Yarn Purchase','Cotton Yarn Purchase',1,0,'dakshabhiadmin','2025-06-10 15:36:02.000',NULL,'2025-06-10 15:36:02.000',NULL,NULL,'Cotton Yarn Purchase','YTX');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (208,'GoodsPurchaseHSNTaxType',2,'Cotton Waste Purchase-GST 5%','Cotton Waste Purchase-GST 5%',1,0,'dakshabhiadmin','2025-06-10 15:37:55.000',NULL,'2025-06-10 15:37:55.000',NULL,NULL,'Export Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Cotton Yarn Purchase-GST-5% (For Export)','Cotton Yarn Purchase-GST-5% (For Export)',1,0,'dakshabhiadmin','2025-06-10 15:38:11.000',NULL,'2025-06-10 15:38:11.000',NULL,NULL,'Export Purchase',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Cotton Yarn Purchase-IGST 5% (For Export)','Cotton Yarn Purchase-IGST 5% (For Export)',1,0,'dakshabhiadmin','2025-06-10 15:38:27.000',NULL,'2025-06-10 15:38:27.000',NULL,NULL,'Export Purchase',''),
	 (209,'GoodsPurchaseSalesType',2,'Cotton Combers','Cotton Combers',1,0,'dakshabhiadmin','2025-06-10 15:38:47.000',NULL,'2025-06-10 15:38:47.000',NULL,NULL,'Export Purchase',''),
	 (209,'GoodsPurchaseSalesType',2,'Yarn  Cotton (Trading)','Yarn  Cotton (Trading)',1,0,'dakshabhiadmin','2025-06-10 15:39:27.000',NULL,'2025-06-10 15:39:27.000',NULL,NULL,'Export Purchase',''),
	 (210,'GoodsPurchaseVoucherType',2,'Cotton Waste Purchase','Cotton Waste Purchase',1,0,'dakshabhiadmin','2025-06-10 15:39:55.000',NULL,'2025-06-10 15:39:55.000',NULL,NULL,'Export Purchase','WTX'),
	 (210,'GoodsPurchaseVoucherType',2,'Cotton Yarn  Purchase','Cotton Yarn  Purchase',1,0,'dakshabhiadmin','2025-06-10 15:40:28.000',NULL,'2025-06-10 15:40:28.000',NULL,NULL,'Export Purchase','YTX'),
	 (210,'GoodsPurchaseVoucherType',2,'Purchase Register  Expense','Purchase Register  Expense',1,0,'dakshabhiadmin','2025-06-10 15:42:58.000',NULL,'2025-06-10 15:42:58.000',NULL,NULL,'Export Purchase','GP'),
	 (208,'GoodsPurchaseHSNTaxType',2,'Cash Discount Allowance','Cash Discount Allowance',1,0,'dakshabhiadmin','2025-06-10 15:43:23.000',NULL,'2025-06-10 15:43:23.000',NULL,NULL,'Purchase Related Exp',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Cash Discount Allowance-Purchase-GST-5%','Cash Discount Allowance-Purchase-GST-5%',1,0,'dakshabhiadmin','2025-06-10 15:43:39.000',NULL,'2025-06-10 15:43:39.000',NULL,NULL,'Purchase Related Exp','');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (208,'GoodsPurchaseHSNTaxType',2,'Late Payment Charges- SGST','Late Payment Charges- SGST',1,0,'dakshabhiadmin','2025-06-10 15:43:51.000',NULL,'2025-06-10 15:43:51.000',NULL,NULL,'Purchase Related Exp',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Late Payment Expense-SGST-18%','Late Payment Expense-SGST-18%',1,0,'dakshabhiadmin','2025-06-10 15:44:13.000',NULL,'2025-06-10 15:44:13.000',NULL,NULL,'Purchase Related Exp',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Late Payment Interest on Purchase Gst-5%','Late Payment Interest on Purchase Gst-5%',1,0,'dakshabhiadmin','2025-06-10 15:44:29.000',NULL,'2025-06-10 15:44:29.000',NULL,NULL,'Purchase Related Exp',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Rate Difference Allowance on Pur-GST 5%','Rate Difference Allowance on Pur-GST 5%',1,0,'dakshabhiadmin','2025-06-10 15:44:46.000',NULL,'2025-06-10 15:44:46.000',NULL,NULL,'Purchase Related Exp',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Rate Difference Allowance-Purchase-SGST','Rate Difference Allowance-Purchase-SGST',1,0,'dakshabhiadmin','2025-06-10 15:45:39.000',NULL,'2025-06-10 15:45:39.000',NULL,NULL,'Purchase Related Exp',''),
	 (208,'GoodsPurchaseHSNTaxType',2,'Cash Discount Allowance-Purchase-SGST','Cash Discount Allowance-Purchase-SGST',1,0,'dakshabhiadmin','2025-06-10 15:46:44.000',NULL,'2025-06-10 15:46:44.000',NULL,NULL,'Purchase Related Exp',''),
	 (209,'GoodsPurchaseSalesType',2,'Purchase Related Exp','Purchase Related Exp',1,0,'dakshabhiadmin','2025-06-10 15:51:20.000',NULL,'2025-06-10 15:51:20.000',NULL,NULL,'Purchase Related Exp',''),
	 (210,'GoodsPurchaseVoucherType',2,'Purchase  Register-Non GST','Purchase  Register-Non GST',1,0,'dakshabhiadmin','2025-06-10 15:51:40.000','dakshabhiadmin','2025-06-10 15:51:49.000',NULL,NULL,'Purchase Related Exp','CEX'),
	 (210,'GoodsPurchaseVoucherType',2,'Debit Note-Purchase','Debit Note-Purchase',1,0,'dakshabhiadmin','2025-06-10 15:52:10.000',NULL,'2025-06-10 15:52:10.000',NULL,NULL,'Purchase Related Exp','DNP'),
	 (210,'GoodsPurchaseVoucherType',2,'Purchase  Register  Expense','Purchase  Register  Expense',1,0,'dakshabhiadmin','2025-06-10 15:52:40.000',NULL,'2025-06-10 15:52:40.000',NULL,NULL,'Purchase Related Exp','GP');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (210,'GoodsPurchaseVoucherType',2,'Credit Note - Purchase','Credit Note - Purchase',1,0,'dakshabhiadmin','2025-06-10 15:53:01.000',NULL,'2025-06-10 15:53:01.000',NULL,NULL,'Purchase Related Exp','CRP'),
	 (208,'GoodsPurchaseHSNTaxType',2,'Cotton Rui Purchase Bales-SGST','Cotton Rui Purchase Bales-SGST',1,0,'dakshabhiadmin','2025-06-10 15:53:30.000',NULL,'2025-06-10 15:53:30.000',NULL,NULL,'Trading Purchase',''),
	 (209,'GoodsPurchaseSalesType',2,'Shankar Rui Bales','Shankar Rui Bales',1,0,'dakshabhiadmin','2025-06-10 15:53:53.000',NULL,'2025-06-10 15:53:53.000',NULL,NULL,'Trading Purchase',''),
	 (210,'GoodsPurchaseVoucherType',2,'Bales Purchase','Bales Purchase',1,0,'dakshabhiadmin','2025-06-10 15:54:10.000',NULL,'2025-06-10 15:54:10.000',NULL,NULL,'Trading Purchase','BT'),
	 (208,'GoodsPurchaseHSNTaxType',2,'Finish Fabric Purchase GST 5%','Finish Fabric Purchase GST 5%',1,0,'dakshabhiadmin','2025-06-10 15:54:33.000',NULL,'2025-06-10 15:54:33.000',NULL,NULL,'Finish Fabric Purchase GST 5%',''),
	 (209,'GoodsPurchaseSalesType',2,'Finish Grey Cloth','Finish Grey Cloth',1,0,'dakshabhiadmin','2025-06-10 15:54:48.000',NULL,'2025-06-10 15:54:48.000',NULL,NULL,'Finish Fabric Purchase GST 5%',''),
	 (210,'GoodsPurchaseVoucherType',2,' Purchase ',' Purchase ',1,0,'dakshabhiadmin','2025-06-10 15:55:16.000',NULL,'2025-06-10 15:55:16.000',NULL,NULL,'Finish Fabric Purchase GST 5%','RM'),
	 (208,'GoodsPurchaseHSNTaxType',2,'Grey Cloth Purchase -SGST','Grey Cloth Purchase -SGST',1,0,'dakshabhiadmin','2025-06-10 15:55:30.000',NULL,'2025-06-10 15:55:30.000',NULL,NULL,'Grey Cloth Purchase -SGST',''),
	 (209,'GoodsPurchaseSalesType',2,'Finish Grey  Cloth','Finish Grey  Cloth',1,0,'dakshabhiadmin','2025-06-10 15:55:46.000',NULL,'2025-06-10 15:55:46.000',NULL,NULL,'Grey Cloth Purchase -SGST',''),
	 (210,'GoodsPurchaseVoucherType',2,'  Purchase ','  Purchase ',1,0,'dakshabhiadmin','2025-06-10 15:56:23.000',NULL,'2025-06-10 15:56:23.000',NULL,NULL,'Grey Cloth Purchase -SGST','RM');
INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (208,'GoodsPurchaseHSNTaxType',2,'Job Work Expense-GST-5%','Job Work Expense-GST-5%',1,0,'dakshabhiadmin','2025-06-10 15:56:39.000',NULL,'2025-06-10 15:56:39.000',NULL,NULL,'Job Work Expense-GST-5%',''),
	 (209,'GoodsPurchaseSalesType',2,'Sizzing - Jobwork','Sizzing - Jobwork',1,0,'dakshabhiadmin','2025-06-10 15:56:53.000',NULL,'2025-06-10 15:56:53.000',NULL,NULL,'Job Work Expense-GST-5%',''),
	 (210,'GoodsPurchaseVoucherType',2,' Purchase Register Expense',' Purchase Register Expense',1,0,'dakshabhiadmin','2025-06-10 15:57:18.000',NULL,'2025-06-10 15:57:18.000',NULL,NULL,'Job Work Expense-GST-5%','GP');
