create or REPLACE algorithm = UNDEFINED view `xtv_warping_production_order_rpt` as
select
    concat(ifnull(`v`.`warping_order_no`, ''), ':Warping Order No:Y:T:') as `warping_order_no`,
    concat(ifnull(`v`.`warping_plan_date`, ''), ':Plan Date:Y:D:') as `warping_plan_date`,
    concat(ifnull(`v`.`set_no`, ''), ':Set No:Y:T:') as `set_no`,
    concat(ifnull(`v`.`sort_no`, ''), ':Sort No:Y:T:') as `sort_no`,
    concat(ifnull(`v`.`customer_order_no`, ''), ':Customer Order No:Y:T:') as `customer_order_no`,
    concat(ifnull(`v`.`set_length`, ''), ':Set Length:O:N:') as `set_length`,
    concat(ifnull(`v`.`t_ends`, ''), ':Total Ends:O:N:') as `t_ends`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Product Material Name:Y:C:xtv_warping_production_order:O') as `product_material_name`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Product Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`schedule_quantity`, ''), ':Schedule Quantity:O:N:') as `schedule_quantity`,
    concat(ifnull(`v`.`warping_order_status_desc`, ''), ':Warping Order Status:Y:C:xtv_warping_production_order:O') as `warping_order_status_desc`,
    concat(ifnull(`v`.`warping_schedule_date`, ''), ':Schedule Date:Y:D:') as `warping_schedule_date`,
    concat(ifnull(`v`.`approved_by_name`, ''), ':Approved By:Y:C:xtv_warping_production_order:O:') as `approved_by_name`,
    concat(ifnull(`v`.`approved_date`, ''), ':Approved Date:Y:D:') as `approved_date`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`warping_production_order_id`, ''), ':Warping Production Order Id:O:N:') as `warping_production_order_id`
from
    `xtv_warping_production_order` `v`
limit 1;