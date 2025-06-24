INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,27,5,22,'Beam Inwards',9,'Beam Inwards','Production','<MBeamInwardsListing/>','import FrmBeamInwardsListing from "./Masters/MBeamInwards/MBeamInwardsListing";','/Masters/MBeamInwards/MBeamInwardsListing','<MBeamInwardsEntry/>','import FrmBeamInwardsEntry from "./Masters/MBeamInwards/MBeamInwardsEntry";','/Masters/MBeamInwards/MBeamInwardsEntry',NULL,'Beam Inwards',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


INSERT INTO am_properties (property_master_id,properties_master_name,company_id,property_name,property_value,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,property_group,remark) VALUES
	 (176,'SizedBeams',2,'RAPIER','RAPIER',1,0,'6260537025','2024-11-28 16:17:10.000',NULL,'2024-11-28 16:17:10.000',NULL,NULL,'BI',''),
	 (176,'SizedBeams',2,'AIR JET','AIR JET',1,0,'6260537025','2024-11-28 16:18:01.000',NULL,'2024-11-28 16:18:01.000',NULL,NULL,'BI',''),
	 (176,'SizedBeams',2,'T-190','T-190',1,0,'6260537025','2024-11-28 16:18:32.000',NULL,'2024-11-28 16:18:32.000',NULL,NULL,'BI',''),
	 (176,'SizedBeams',2,'T-110','T-110',1,0,'6260537025','2024-11-28 16:19:18.000',NULL,'2024-11-28 16:19:18.000',NULL,NULL,'BI','');

CREATE TABLE `xt_beam_inwards_table` (
  `beam_inwards_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '* read only back end auto generated *',
  `customer_id` bigint(20) NOT NULL COMMENT '* read only back end auto generated *',
  `company_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*backend from session  ',
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '*combo box with cmv_company_branch  and set default backend from session  *',
  `financial_year` varchar(20) NOT NULL,
  `beam_inwards_date` date NOT NULL COMMENT '* DTPpicker Data Entry',
  `section` varchar(500) DEFAULT NULL COMMENT 'P-Pending,A-Aprroved, R-Rejected,I-Partial Issue, C-Completed, X-Canceled Z-PreeClosed',
  `beam_type` varchar(500) DEFAULT NULL COMMENT 'P-Pending,A-Aprroved, R-Rejected,I-Partial Issue, C-Completed, X-Canceled Z-PreeClosed',
  `beam_no` varchar(500) DEFAULT NULL COMMENT 'P-Pending,A-Aprroved, R-Rejected,I-Partial Issue, C-Completed, X-Canceled Z-PreeClosed',
  `beam_width` decimal(18,4) DEFAULT 0.0000 COMMENT '*  text box with data entry and numeric validation',
  `beam_status` varchar(1) DEFAULT 'E' COMMENT '*  text box with data entry and numeric validation',
  `is_active` bit(1) DEFAULT b'1',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`beam_inwards_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;