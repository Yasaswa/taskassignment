create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_wastage_rpt` as
select
    concat(ifnull(`wp`.`shift`, 'N/A'), ':Shift:Y:C:') as `shift`,
    concat(ifnull(`wp`.`production_date`, 'N/A'), ':Production Date:Y:D:') as `production_date`,
    concat(ifnull(`wp`.`production_set_no`, 'N/A'), ':Production Set No:Y:T:') as `production_set_no`,
    concat(ifnull(`wp`.`wastage_quantity`, 0), ':Wastage Quantity:Y:N:') as `wastage_quantity`,
    concat(ifnull(`wp`.`production_wastage_types_name`, 'N/A'), ':Production Wastage Types Name:Y:T:') as `production_wastage_types_name`,
    concat(ifnull(`wp`.`godown_name`, 'N/A'), ':Godown Name:Y:T:') as `godown_name`,
    concat(ifnull(`wp`.`created_by`, 'N/A'), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`wp`.`created_on`, 'N/A'), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`wp`.`modified_by`, 'N/A'), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`wp`.`modified_on`, 'N/A'), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`wp`.`deleted_by`, 'N/A'), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`wp`.`deleted_on`, 'N/A'), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`wp`.`financial_year`, 'N/A'), ':Financial Year:Y:N:') as `financial_year`,
    concat(ifnull(`wp`.`company_id`, 0), ':Company Id:Y:N:') as `company_id`,
    concat(ifnull(`wp`.`company_branch_id`, 0), ':Company Branch Id:Y:N:') as `company_branch_id`,
    concat(ifnull(`wp`.`weaving_production_wastage_id`, 0), ':Weaving Production Wastage Id:Y:N:') as `weaving_production_wastage_id`,
    concat(ifnull(`wp`.`section_id`, 0), ':Section Id:Y:N:') as `section_id`,
    concat(ifnull(`wp`.`sub_section_id`, 0), ':Sub Section Id:Y:N:') as `sub_section_id`,
    concat(ifnull(`wp`.`machine_id`, 0), ':Machine Id:Y:N:') as `machine_id`,
    concat(ifnull(`wp`.`production_wastage_types_id`, 0), ':Production Wastage Types Id:Y:N:') as `production_wastage_types_id`
from
    `xtv_weaving_production_warping_wastage` `wp`
where
    `wp`.`is_delete` = 0
order by
    `wp`.`weaving_production_wastage_id` desc
limit 1;


-- erp_development.xtv_weaving_production_warping_stoppage_rpt source

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_stoppage_rpt` as
select
    concat(ifnull(`sp`.`production_date`, ''), ':Production Date:Y:D:') as `production_date`,
    concat(ifnull(`sp`.`shift`, ''), ':Shift:Y:C:') as `shift`,
    concat(ifnull(`sp`.`production_set_no`, ''), ':Production Set No:Y:C:') as `production_set_no`,
    concat(ifnull(`sp`.`machine_name`, ''), ':Machine Name:Y:C:') as `machine_name`,
    concat(ifnull(`sp`.`machine_short_name`, ''), ':Machine Short Name:Y:C:') as `machine_short_name`,
    concat(ifnull(`sp`.`production_stoppage_reasons_name`, ''), ':Stoppage Reason Name:Y:C:') as `production_stoppage_reasons_name`,
    concat(ifnull(`sp`.`production_stoppage_reasons_type`, ''), ':Stoppage Reason Type:Y:C:') as `production_stoppage_reasons_type`,
    concat(ifnull(`sp`.`std_stoppage_loss_per_hour`, 0), ':Standard Stoppage Loss per Hour:Y:N:') as `std_stoppage_loss_per_hour`,
    concat(ifnull(`sp`.`loss_type`, ''), ':Loss Type:Y:C:') as `loss_type`,
    concat(ifnull(`sp`.`std_stoppage_loss_kg`, 0), ':Standard Stoppage Loss (Kg):Y:N:') as `std_stoppage_loss_kg`,
    concat(ifnull(`sp`.`stoppage_production_loss_kg`, 0), ':Stoppage Production Loss (Kg):Y:N:') as `stoppage_production_loss_kg`,
    concat(ifnull(`sp`.`actual_production_loss_kg`, 0), ':Actual Production Loss (Kg):Y:N:') as `actual_production_loss_kg`,
    concat(ifnull(`sp`.`from_time`, ''), ':From Time:Y:T:') as `from_time`,
    concat(ifnull(`sp`.`to_time`, ''), ':To Time:Y:T:') as `to_time`,
    concat(ifnull(`sp`.`total_time`, 0), ':Total Time:Y:N:') as `total_time`,
    concat(ifnull(`sp`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`sp`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`sp`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`sp`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`sp`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`sp`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`sp`.`financial_year`, ''), ':Financial Year:Y:N:') as `financial_year`,
    concat(ifnull(`sp`.`company_id`, 0), ':Company Id:Y:N:') as `company_id`,
    concat(ifnull(`sp`.`company_branch_id`, 0), ':Company Branch Id:Y:N:') as `company_branch_id`,
    concat(ifnull(`sp`.`weaving_production_stoppage_id`, 0), ':Weaving Production Stoppage Id:Y:N:') as `weaving_production_stoppage_id`,
    concat(ifnull(`sp`.`section_id`, 0), ':Section Id:Y:N:') as `section_id`,
    concat(ifnull(`sp`.`sub_section_id`, 0), ':Sub Section Id:Y:N:') as `sub_section_id`,
    concat(ifnull(`sp`.`machine_id`, 0), ':Machine Id:Y:N:') as `machine_id`,
    concat(ifnull(`sp`.`production_stoppage_reasons_id`, 0), ':Production Stoppage Reason Id:Y:N:') as `production_stoppage_reasons_id`,
    concat(ifnull(`sp`.`weaving_production_stoppage_id`, 0), ':Field Id:O:N:') as `field_id`
from
    `xtv_weaving_production_warping_stoppage` `sp`
limit 1;

