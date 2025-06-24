ALTER TABLE cm_supplier MODIFY COLUMN username varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;
ALTER TABLE cm_supplier MODIFY COLUMN password varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL;


-- erp_development.xtv_weaving_production_warping_material_rpt source

create or replace
algorithm = UNDEFINED view `xtv_weaving_production_warping_material_rpt` as
select
    concat(ifnull(`v`.`weaving_production_set_no`, ''), ':Weaving Production Set No:Y:C:xtv_weaving_production_warping_material:O') as `weaving_production_set_no`,
    concat(ifnull(`v`.`warping_production_order_no`, ''), ':Warping Production Order No:Y:C:xtv_weaving_production_warping_material:O') as `warping_production_order_no`,
    concat(ifnull(`v`.`warping_production_code`, ''), ':Warping Production Code:O:N:') as `warping_production_code`,
    concat(ifnull(`v`.`warping_production_date`, ''), ':Warping Production Date:Y:D:') as `warping_production_date`,
    concat(ifnull(`v`.`product_material_name`, ''), ':Product Material Name:O:N:') as `product_material_name`,
    concat(ifnull(`v`.`product_material_unit_name`, ''), ':Material Unit Name:O:N:') as `product_material_unit_name`,
    concat(ifnull(`v`.`plant_name`, ''), ':Plant Name:Y:C:xmv_production_section:F') as `plant_name`,
    concat(ifnull(`v`.`production_section_name`, ''), ':Section Name:Y:C:xmv_production_section:F') as `production_section_name`,
    concat(ifnull(`v`.`production_sub_section_name`, ''), ':Sub Section Name:Y:C:xmv_production_sub_section:F') as `production_sub_section_name`,
    concat(ifnull(`v`.`material_status`, ''), ':Material Status Desc:Y:H:(Pending,Approved,Rejected,Completed,Canceled,Partial Approved)') as `material_status_desc`,
    concat(ifnull(`v`.`shift`, ''), ':Shift:Y:C:xtv_weaving_production_warping_details:O') as `shift`,
    concat(ifnull(`v`.`prod_year`, ''), ':Production Year:O:N:') as `prod_year`,
    concat(ifnull(`v`.`prod_month`, ''), ':Production Month:O:N:') as `prod_month`,
    concat(ifnull(`v`.`product_material_quantity`, ''), ':Product Material Quantity:O:N:') as `product_material_quantity`,
    concat(ifnull(`v`.`consumption_quantity`, ''), ':Consumption Quantity:O:N:') as `consumption_quantity`,
    concat(ifnull(`v`.`material_status_remark`, ''), ':Material Status Remark:O:N:') as `material_status_remark`,
    concat(ifnull(`v`.`financial_year`, ''), ':Finance Year:O:N:amv_financial_year:F') as `financial_year`,
    concat(ifnull(`v`.`material_status`, ''), ':Material Status:O:N:') as `material_status`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:O:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`weaving_production_warping_material_id`, ''), ':Weaving Production Warping Material Id:O:N:') as `weaving_production_warping_material_id`,
    concat(ifnull(`v`.`weaving_production_warping_details_id`, ''), ':Weaving Production Warping Details Id:N:N:') as `weaving_production_warping_details_id`,
    concat(ifnull(`v`.`weaving_production_warping_master_id`, ''), ':Weaving Production Warping Master Id:N:N:') as `weaving_production_warping_master_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`product_material_unit_id`, ''), ':Material Unit Id:N:N:') as `product_material_unit_id`,
    concat(ifnull(`v`.`section_id`, ''), ':Section Id:N:N:') as `section_id`,
    concat(ifnull(`v`.`sub_section_id`, ''), ': Sub Section Id:N:N:') as `sub_section_id`,
    concat(ifnull(`v`.`plant_id`, ''), ':Plant Id:N:N:') as `plant_id`
from
    `xtv_weaving_production_warping_material` `v`
limit 1;
