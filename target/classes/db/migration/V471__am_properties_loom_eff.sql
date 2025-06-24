INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,27,5,21,'Loom Efficiency',6,'Loom Efficiency','Production','<FrmLoomProductionListing/>','import FrmLoomEfficiencyMasterListing from "Masters/MLoomEfficiencyMaster/FrmLoomEfficiencyMasterListing";','/Masters/MLoomEfficiencyMaster/FrmLoomEfficiencyMasterListing','<FrmLoomEfficiencyMasterEntry/>','import FrmLoomEfficiencyMasterEntry from "Masters/MLoomEfficiencyMaster/FrmLoomEfficiencyMasterEntry";','/Masters/MLoomEfficiencyMaster/FrmLoomEfficiencyMasterEntry',NULL,'Loom Production',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');


CREATE TABLE `xt_loom_efficiency_master` (
  `company_id` int(11) DEFAULT 1 COMMENT '* read only back end will recieved from Session ',
  `company_branch_id` bigint(20) DEFAULT NULL COMMENT '* read only back end will recieved from Session ',
  `loom_efficiency_master_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '* read only back end auto generated *',
  `properties_master_name` varchar(500) NOT NULL COMMENT '* Text box Data Entry with alphanumeric validation ',
  `weave_design_id` bigint(20) DEFAULT NULL COMMENT '* read only back end will recieved from Session ',
  `weave_design_name` varchar(500) NOT NULL COMMENT '* Select Box Data Entry with alphanumeric validation ',
  `target_efficiency` decimal(18,4) DEFAULT 0.0000 COMMENT ' Text box Data Entry with numeric validation ',
  `target_speed` decimal(18,4) DEFAULT 0.0000 COMMENT '* Text box Data Entry with numeric validation ',
  `status_remark` varchar(500) NOT NULL COMMENT '* Text Area Data Entry with alphanumeric validation ',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`loom_efficiency_master_id`)
);


create or replace
algorithm = UNDEFINED view `xt_loom_efficiency_master_rpt` as
select
    concat(`v`.`properties_master_name`, ':Properties Master Name:O:N:') as `properties_master_name`,
    concat(`v`.`weave_design_name`, ':Weave Design Name:Y:T:') as `weave_design_name`,
    concat(`v`.`target_efficiency`, ':Target Efficiency:O:N:') as `target_efficiency`,
    concat(`v`.`target_speed`, ':Target Speed:O:N:') as `target_speed`,
    concat(`v`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`v`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`v`.`loom_efficiency_master_id`, ':Loom Efficiency Id:O:N:') as `loom_efficiency_master_id`
from
    `xt_loom_efficiency_master` `v`
limit 1;