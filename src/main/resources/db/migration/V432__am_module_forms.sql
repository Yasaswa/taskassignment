INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,17,6,32,'Loom Production Report',10,'Loom Production Report','Registers','<FrmLoomProductionReport/>','import FrmLoomProductionReport from "./Transactions/TLoomProductaion/LoomProductionReport";','/Transactions/TLoomProductaion/LoomProductionReport','','','',NULL,NULL,0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');



ALTER TABLE xt_weaving_production_loom_details ADD false_times decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE xt_weaving_production_loom_details ADD false_minutes decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE xt_weaving_production_loom_details ADD false_per_hour decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE xt_weaving_production_loom_details ADD false_per_day decimal(18,4) DEFAULT 0.0000 NULL;
ALTER TABLE xt_weaving_production_loom_details ADD false_per_cmpx decimal(18,4) DEFAULT 0.0000 NULL;


create or replace
algorithm = UNDEFINED view `xtv_weaving_production_loom_details` as
select
    `xt`.`loom_production_code` as `loom_production_code`,
    `xt`.`code` as `code`,
    `xt`.`loom_production_date` as `loom_production_date`,
    `xt`.`loom_production_shift` as `loom_production_shift`,
    `xt`.`prod_month` as `prod_month`,
    `xt`.`prod_year` as `prod_year`,
    `xt`.`set_no` as `set_no`,
    `xt`.`style` as `style`,
    `xt`.`sizing_beam_no` as `sizing_beam_no`,
    `xt`.`sort_no` as `sort_no`,
    `xt`.`epi` as `epi`,
    `xt`.`ppi` as `ppi`,
    `xt`.`width` as `width`,
    `m`.`machine_name` as `machine_name`,
    `p`.`plant_name` as `plant_name`,
    `e`.`employee_name` as `production_operator_name`,
    `e1`.`employee_name` as `production_supervisor_name`,
    `xt`.`target_rpm` as `target_rpm`,
    `xt`.`target_eff` as `target_eff`,
    `xt`.`weave` as `weave`,
    `xt`.`act_rmp` as `act_rmp`,
    `xt`.`act_eff` as `act_eff`,
    `xt`.`run_in_min` as `run_in_min`,
    `xt`.`stop_in_min` as `stop_in_min`,
    `xt`.`prodcut_1000pick` as `prodcut_1000pick`,
    `xt`.`product_in_meter` as `product_in_meter`,
    `xt`.`air_flow1` as `air_flow1`,
    `xt`.`air_flow2` as `air_flow2`,
    `xt`.`air_flow_cfm` as `air_flow_cfm`,
    `xt`.`warp_times` as `warp_times`,
    `xt`.`warp_minutes` as `warp_minutes`,
    `xt`.`warp_per_hour` as `warp_per_hour`,
    `xt`.`warp_per_day` as `warp_per_day`,
    `xt`.`warp_per_cmpx` as `warp_per_cmpx`,
    `xt`.`weft_times` as `weft_times`,
    `xt`.`weft_minutes` as `weft_minutes`,
    `xt`.`weft_per_hour` as `weft_per_hour`,
    `xt`.`weft_per_day` as `weft_per_day`,
    `xt`.`weft_per_cmpx` as `weft_per_cmpx`,
    `xt`.`leno_times` as `leno_times`,
    `xt`.`leno_minutes` as `leno_minutes`,
    `xt`.`leno_per_day` as `leno_per_day`,
    `xt`.`leno_per_hour` as `leno_per_hour`,
    `xt`.`leno_per_cmpx` as `leno_per_cmpx`,
    `xt`.`unselect_times` as `unselect_times`,
    `xt`.`unselect_minutes` as `unselect_minutes`,
    `xt`.`unselect2_times` as `unselect2_times`,
    `xt`.`unselect_per_hour` as `unselect_per_hour`,
    `xt`.`unselect_per_day` as `unselect_per_day`,
    `xt`.`unselect_per_cmpx` as `unselect_per_cmpx`,
    `xt`.`total_times` as `total_times`,
    `xt`.`total_minutes` as `total_minutes`,
    `s`.`godown_name` as `godown_name`,
    `xt`.`false_times` as `false_times`,
    `xt`.`false_minutes` as `false_minutes`,
    `xt`.`false_per_hour` as `false_per_hour`,
    `xt`.`false_per_day` as `false_per_day`,
    `xt`.`false_per_cmpx` as `false_per_cmpx`,
    `xt`.`total2_times` as `total2_times`,
    `xt`.`total_per_hour` as `total_per_hour`,
    `xt`.`total_per_day` as `total_per_day`,
    `xt`.`total_per_cmpx` as `total_per_cmpx`,
    `sb`.`production_section_name` as `production_section_name`,
    `sb`.`production_sub_section_name` as `production_sub_section_name`,
    `xt`.`remarks` as `remarks`,
    case
        `xt`.`is_active` when 1 then 'Active'
        else 'In Active'
    end as `Active`,
    case
        `xt`.`is_delete` when 1 then 'Yes'
        else 'No'
    end as `Deleted`,
    `xt`.`is_active` as `is_active`,
    `xt`.`is_delete` as `is_delete`,
    `xt`.`created_by` as `created_by`,
    `xt`.`created_on` as `created_on`,
    `xt`.`modified_by` as `modified_by`,
    `xt`.`modified_on` as `modified_on`,
    `xt`.`deleted_by` as `deleted_by`,
    `xt`.`deleted_on` as `deleted_on`,
    `xt`.`financial_year` as `financial_year`,
    `xt`.`company_id` as `company_id`,
    `xt`.`company_branch_id` as `company_branch_id`,
    `xt`.`godown_id` as `godown_id`,
    `xt`.`weaving_production_loom_details_id` as `weaving_production_loom_details_id`,
    `xt`.`weaving_production_loom_master_id` as `weaving_production_loom_master_id`,
    `xt`.`plant_id` as `plant_id`,
    `xt`.`section_id` as `section_id`,
    `xt`.`sub_section_id` as `sub_section_id`,
    `xt`.`production_operator_id` as `production_operator_id`,
    `xt`.`production_supervisor_id` as `production_supervisor_id`,
    `xt`.`machine_id` as `machine_id`,
    `wm`.`loom_production_master_status` as `loom_production_master_status`,
    `xt`.`weaving_production_loom_details_id` as `field_id`,
    `xt`.`loom_production_code` as `field_name`
from
    (((((((`xt_weaving_production_loom_details` `xt`
left join `xt_weaving_production_loom_master` `wm` on
    (`wm`.`weaving_production_loom_master_id` = `xt`.`weaving_production_loom_master_id`
        and `wm`.`company_id` = `xt`.`company_id`))
left join `cm_godown` `s` on
    (`s`.`godown_id` = `xt`.`godown_id`
        and `s`.`company_id` = `xt`.`company_id`))
left join `cm_plant` `p` on
    (`p`.`plant_id` = `xt`.`plant_id`
        and `p`.`company_id` = `xt`.`company_id`))
left join `cm_machine` `m` on
    (`m`.`machine_id` = `xt`.`machine_id`
        and `m`.`company_id` = `xt`.`company_id`))
left join `xm_production_sub_section` `sb` on
    (`sb`.`production_sub_section_id` = `xt`.`sub_section_id`
        and `sb`.`company_id` = `xt`.`company_id`))
left join `cm_employee` `e` on
    (`e`.`employee_id` = `xt`.`production_operator_id`
        and `e`.`company_id` = `xt`.`company_id`))
left join `cm_employee` `e1` on
    (`e1`.`employee_id` = `xt`.`production_supervisor_id`
        and `e1`.`company_id` = `xt`.`company_id`))
where
    `xt`.`is_delete` = 0;

