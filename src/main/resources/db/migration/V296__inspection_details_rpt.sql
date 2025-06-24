ALTER TABLE mt_sales_order_details_trading MODIFY COLUMN set_no varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 0 NULL;


create or replace
algorithm = UNDEFINED view `xtv_weaving_production_inspection_details_rpt` as
select
    concat(ifnull(`v`.`inspection_production_date`, ''), ':Production Date:Y:D:') as `inspection_production_date`,
    concat(ifnull(`v`.`inspection_production_code`, ''), ':Production Code:Y:T:') as `inspection_production_code`,
    concat(ifnull(`v`.`inspection_production_status`, ''), ':Roll Status:Y:H:(Pending, Approved, Rejected, Completed, Canceled, Partial Approved)') as `inspection_production_status_desc`,
    concat(ifnull(`v`.`sort_no`, ''), ':Sort No:Y:T:') as `sort_no`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Product Name:Y:T:') as `product_material_name`,
    concat(ifnull(`v`.`inspection_production_set_no`, ''), ':Set No:Y:T:') as `inspection_production_set_no`,
    concat(ifnull(`v`.`sizing_beam_no`, ''), ':Sizing Beam No:Y:T:') as `sizing_beam_no`,
    concat(ifnull(`v`.`machine_name`, ''), ':Machine Name:Y:T:') as `machine_name`,
    concat(ifnull(`v`.`roll_no`, ''), ':Roll No:Y:T:') as `roll_no`,
    concat(ifnull(`v`.`shift`, ''), ':Shift:Y:H:(I,II)') as `shift`,
    concat(ifnull(`v`.`width`, ''), ':Width:O:N:') as `width`,
    concat(ifnull(`v`.`product_pick`, ''), ':Product Pick:O:N:') as `product_pick`,
    concat(ifnull(`v`.`product_in_meter`, ''), ':Product In Meter:O:N:') as `product_in_meter`,
    concat(ifnull(`v`.`inspection_mtr`, ''), ':Inspection Meter:O:N:') as `inspection_mtr`,
    concat(ifnull(`v`.`difference`, ''), ':Difference:O:N:') as `difference`,
    concat(ifnull(`v`.`weight`, ''), ':Weight:O:N:') as `weight`,
    concat(ifnull(`v`.`average`, ''), ':Average:O:N:') as `average`,
    concat(ifnull(`v`.`godown_name`, ''), ':Godown Name:O:N:') as `godown_name`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:Y:T:') as `prod_month`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Section Name:O:N:') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Sub Section Name:O:N') as `production_sub_section_name`,
    concat(ifnull(`v`.`status_remark`, ''), ':Status Remark:O:N:)') as `status_remark`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`weaving_production_inspection_details_id`, ''), ':Inspection Details Id:O:N:') as `weaving_production_inspection_details_id`,
    concat(ifnull(`v`.`weaving_production_inspection_master_id`, ''), ':Inspection Master Id:O:N:') as `weaving_production_inspection_master_id`
from
    `xtv_weaving_production_inspection_details` `v`
limit 1;