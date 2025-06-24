--  xtv_weaving_production_warping_stoppage source
create or replace algorithm = UNDEFINED view  `xtv_weaving_production_warping_stoppage` as
select
    `sp`.`production_date` as `production_date`,
    `sp`.`warping_production_code` as `warping_production_code`,
    `sp`.`shift` as `shift`,
    `sp`.`prod_month` as `prod_month`,
    `sp`.`prod_year` as `prod_year`,
    `cm`.`machine_name` as `machine_name`,
    `cm`.`machine_short_name` as `machine_short_name`,
    `st`.`production_stoppage_reasons_name` as `production_stoppage_reasons_name`,
    `st`.`production_stoppage_reasons_type` as `production_stoppage_reasons_type`,
    `st`.`std_stoppage_minutes` as `std_stoppage_minutes`,
    `sp`.`std_stoppage_loss_per_hour` as `std_stoppage_loss_per_hour`,
    `sp`.`loss_type` as `loss_type`,
    `sp`.`stoppage_time` as `stoppage_time`,
    `sp`.`std_stoppage_loss_kg` as `std_stoppage_loss_kg`,
    `sp`.`stoppage_production_loss_kg` as `stoppage_production_loss_kg`,
    `sp`.`actual_production_loss_kg` as `actual_production_loss_kg`,
    `sp`.`production_set_no` as `production_set_no`,
    `sp`.`from_time` as `from_time`,
    `sp`.`to_time` as `to_time`,
    `sp`.`total_time` as `total_time`,
    `sub`.`production_sub_section_short_name` as `production_sub_section_short_name`,
    case
        `sp`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `sp`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `sp`.`is_active` as `is_active`,
    `sp`.`is_delete` as `is_delete`,
    `sp`.`created_by` as `created_by`,
    `sp`.`created_on` as `created_on`,
    `sp`.`modified_by` as `modified_by`,
    `sp`.`modified_on` as `modified_on`,
    `sp`.`deleted_by` as `deleted_by`,
    `sp`.`deleted_on` as `deleted_on`,
    `sp`.`financial_year` as `financial_year`,
    `sp`.`company_id` as `company_id`,
    `sp`.`company_branch_id` as `company_branch_id`,
    `sp`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `sp`.`weaving_production_stoppage_id` as `weaving_production_stoppage_id`,
    `sp`.`plant_id` as `plant_id`,
    `sp`.`section_id` as `section_id`,
    `sp`.`sub_section_id` as `sub_section_id`,
    `sp`.`production_supervisor_id` as `production_supervisor_id`,
    `sp`.`machine_id` as `machine_id`,
    `sp`.`production_stoppage_reasons_id` as `production_stoppage_reasons_id`,
    `sp`.`weaving_production_stoppage_id` as `field_id`,
    `sp`.`warping_production_code` as `field_name`
from
    ((( `xt_weaving_production_warping_stoppage` `sp`
left join  `cmv_machine` `cm` on
    (`sp`.`machine_id` = `cm`.`machine_id`
        and `sp`.`company_id` = `cm`.`company_id`))
left join  `xmv_production_stoppage_reasons` `st` on
    (`sp`.`production_stoppage_reasons_id` = `st`.`production_stoppage_reasons_id`
        and `sp`.`company_id` = `st`.`company_id`))
left join  `xm_production_sub_section` `sub` on
    (`sub`.`production_sub_section_id` = `sp`.`sub_section_id`
        and `sp`.`company_id` = `st`.`company_id`))
where
    `sp`.`is_delete` = 0
order by
    `sp`.`weaving_production_stoppage_id` desc;



-- xtv_weaving_production_warping_wastage source
create or replace algorithm = UNDEFINED view `xtv_weaving_production_warping_wastage` as
select
    `wp`.`warping_production_code` as `warping_production_code`,
    `wp`.`shift` as `shift`,
    `wp`.`prod_month` as `prod_month`,
    `wp`.`prod_year` as `prod_year`,
    `wp`.`production_date` as `production_date`,
    `s`.`godown_name` as `godown_name`,
    `cm`.`machine_name` as `machine_name`,
    `cm`.`machine_short_name` as `machine_short_name`,
    `wt`.`production_wastage_types_name` as `production_wastage_types_name`,
    `wp`.`production_wastage_types_type` as `production_wastage_types_type`,
    `wp`.`wastage_quantity` as `wastage_quantity`,
    `wp`.`production_set_no` as `production_set_no`,
    `sub`.`production_sub_section_short_name` as `production_sub_section_short_name`,
    case
        `wp`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `wp`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `wp`.`is_active` as `is_active`,
    `wp`.`is_delete` as `is_delete`,
    `wp`.`created_by` as `created_by`,
    `wp`.`created_on` as `created_on`,
    `wp`.`modified_by` as `modified_by`,
    `wp`.`modified_on` as `modified_on`,
    `wp`.`deleted_by` as `deleted_by`,
    `wp`.`deleted_on` as `deleted_on`,
    `wp`.`financial_year` as `financial_year`,
    `wp`.`company_id` as `company_id`,
    `wp`.`company_branch_id` as `company_branch_id`,
    `wp`.`weaving_production_warping_master_id` as `weaving_production_warping_master_id`,
    `wp`.`weaving_production_wastage_id` as `weaving_production_wastage_id`,
    `wp`.`plant_id` as `plant_id`,
    `wp`.`section_id` as `section_id`,
    `wp`.`godown_id` as `godown_id`,
    `wp`.`sub_section_id` as `sub_section_id`,
    `wp`.`machine_id` as `machine_id`,
    `wp`.`production_wastage_types_id` as `production_wastage_types_id`
from
    (((`xt_weaving_production_warping_wastage` `wp`
left join `xmv_production_sub_section_godown_mapping` `s` on
    (`s`.`production_sub_section_godown_id` = `wp`.`godown_id`
        and `s`.`company_id` = `wp`.`company_id`))
left join `cmv_machine` `cm` on
    (`wp`.`machine_id` = `cm`.`machine_id`
        and `wp`.`company_id` = `cm`.`company_id`))
left join `xmv_production_wastage_types` `wt` on
    (`wp`.`production_wastage_types_id` = `wt`.`production_wastage_types_id`
        and `wp`.`company_id` = `wt`.`company_id`)
 left join `xm_production_sub_section` `sub` on
    (`sub`.`production_sub_section_id` = `wp`.`sub_section_id`
        and `sub`.`company_id` = `wp`.`company_id`))
where
    `wp`.`is_delete` = 0
order by
    `wp`.`weaving_production_wastage_id` desc;



update am_modules_forms set modules_forms_name = 'Production Wastage (Weaving)' , display_name= 'Production Wastage (Weaving)' , page_header = 'Production Wastage (Weaving)',url_parameter = 'WVWV' where listing_component_name = '<FrmWastageListing/>';

	INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,27,5,21,'Production Wastage (Sizing)',8,'Production Wastage (Sizing)','Production','<FrmWastageListing/>','import FrmWastageListing from "Transactions/TWeavingStoppageWastageProduction/FrmWastageListing";','/Transactions/TWeavingStoppageWastageProduction/FrmWastageListing','<FrmWastageEntry/>','import FrmWastageEntry from "Transactions/TWeavingStoppageWastageProduction/FrmWastageEntry";','Transactions/TWeavingStoppageWastageProduction/FrmWastageEntry',NULL,'Production Wastage (Sizing)',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'WVSZ'),
	 (1,1,1,27,5,21,'Production Wastage (Warping)',8,'Production Wastage (Warping)','Production','<FrmWastageListing/>','import FrmWastageListing from "Transactions/TWeavingStoppageWastageProduction/FrmWastageListing";','/Transactions/TWeavingStoppageWastageProduction/FrmWastageListing','<FrmWastageEntry/>','import FrmWastageEntry from "Transactions/TWeavingStoppageWastageProduction/FrmWastageEntry";','Transactions/TWeavingStoppageWastageProduction/FrmWastageEntry',NULL,'Production Wastage (Warping)',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'WVWP');

	update am_modules_forms set modules_forms_name = 'Weaving Stoppage (Warping)' , display_name= 'Weaving Stoppage (Warping)' , page_header = 'Weaving Stoppage (Warping)',url_parameter = 'WVWP' where listing_component_name = '<FrmStoppageProductionListing/>';

	INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,27,5,21,'Weaving Stoppage (Sizing)',7,'Weaving Stoppage (Sizing)','Production','<FrmStoppageProductionListing/>','import FrmStoppageProductionListing from "Transactions/TWeavingStoppageWastageProduction/FrmStoppageListing";','/Transactions/TWeavingStoppageWastageProduction/FrmStoppageListing','<FrmStoppageEntry/>','import FrmWastageEntry from "Transactions/TWeavingStoppageWastageProduction/FrmWastageEntry";','/Transactions/TWeavingStoppageWastageProduction/FrmWastageEntry',NULL,'Weaving Stoppage (Sizing)',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'WVSZ'),
	 (1,1,1,27,5,21,'Weaving Stoppage (Weaving)',7,'Weaving Stoppage (Weaving)','Production','<FrmStoppageProductionListing/>','import FrmStoppageProductionListing from "Transactions/TWeavingStoppageWastageProduction/FrmStoppageListing";','/Transactions/TWeavingStoppageWastageProduction/FrmStoppageListing','<FrmStoppageEntry/>','import FrmWastageEntry from "Transactions/TWeavingStoppageWastageProduction/FrmWastageEntry";','/Transactions/TWeavingStoppageWastageProduction/FrmWastageEntry',NULL,'Weaving Stoppage (Weaving)',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'WVWV');