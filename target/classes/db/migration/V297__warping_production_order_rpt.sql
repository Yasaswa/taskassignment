
create or replace
algorithm = UNDEFINED view `xtv_warping_production_order_rpt` as
select
    concat(ifnull(`v`.`warping_order_no`, ''), ':Warping Order No:Y:C:xtv_warping_production_order:O') as `warping_order_no`,
    concat(ifnull(`v`.`set_no`, ''), ':Set No:Y:C:xtv_warping_production_order:O') as `set_no`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Product Material Name:O:N:') as `product_material_name`,
    concat(ifnull(`v`.`warping_order_status_desc`, ''), ':Warping Order Status Desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial)') as `warping_order_status_desc`,
    concat(ifnull(`v`.`sort_no`, ''), ':Sort No:O:N:') as `sort_no`,
    concat(ifnull(`v`.`set_length`, ''), ':Set Length:O:N:') as `set_length`,
    concat(ifnull(`v`.`warping_schedule_date`, ''), ':Schedule Date:O:N:') as `warping_schedule_date`,
    concat(ifnull(`v`.`schedule_quantity`, ''), ':Schedule Quantity:O:N:') as `schedule_quantity`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:O:N:') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`warping_production_order_id`, ''), ':Warping Production Order Id:O:N:') as `warping_production_order_id`,
    concat(ifnull(`v`.`approved_by_id`, ''), ':Approved By Id:N:N:') as `approved_by_id`,
    concat(ifnull(`v`.`warping_order_status`, ''), ':Warping Order Status:O:N:') as `warping_order_status`

from
    `xtv_warping_production_order` `v`
limit 1;
