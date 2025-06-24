create or replace
algorithm = UNDEFINED view `inter_material_transfer_details_rpt` as
select
    concat(`pt`.`inter_material_transfer_no`, ':Inter Material Transfer No:Y:C:ptv_inter_material_transfer_details:F') as `inter_material_transfer_no`,
    concat(`pt`.`inter_material_transfer_date`, ':Inter Material Transfer Date:Y:C:Date:F') as `inter_material_transfer_date`,
    concat(`pt`.`product_material_name`, ':Product Material Name:Y:C:smv_product_rm:F') as `product_material_name`,
    concat(`pt`.`inter_material_transfer_quantity`, ':Inter Material Transfer Quantity:Y:C:Decimal:F') as `inter_material_transfer_quantity`,
    concat(`pt`.`inter_material_transfer_weight`, ':Inter Material Transfer Weight:Y:C:Decimal:F') as `inter_material_transfer_weight`,
    concat(`pt`.`inter_material_transfer_boxes`, ':Inter Material Transfer Boxes:Y:C:Int:F') as `inter_material_transfer_boxes`,
    concat(`pt`.`batch_no`, ':Batch No:O:N:') as `batch_no`,
    concat(`pt`.`product_code`, ':Product Code:O:N:') as `product_code`,
    concat(`pt`.`from_godown_id`, ':From Godown Id:N:N:') as `from_godown_id`,
    concat(`pt`.`from_godown_section_id`, ':From Godown Section Id:N:N:') as `from_godown_section_id`,
    concat(`pt`.`from_godown_section_beans_id`, ':From Godown Section Beans Id:N:N:') as `from_godown_section_beans_id`,
    concat(`pt`.`to_godown_id`, ':To Godown Id:N:N:') as `to_godown_id`,
    concat(`pt`.`to_godown_section_id`, ':To Godown Section Id:N:N:') as `to_godown_section_id`,
    concat(`pt`.`to_godown_section_beans_id`, ':To Godown Section Beans Id:N:N:') as `to_godown_section_beans_id`,
    concat(`pt`.`closing_balance_quantity`, ':Closing Balance Quantity:Y:C:Decimal:F') as `closing_balance_quantity`,
    concat(`pt`.`closing_balance_weight`, ':Closing Balance Weight:Y:C:Decimal:F') as `closing_balance_weight`,
    concat(`pt`.`closing_no_of_boxes`, ':Closing No Of Boxes:Y:C:Int:F') as `closing_no_of_boxes`,
     concat(`pt`.`from_godown_name`, ':From Godown Name:Y:C:cmv_godown:F') as `from_godown_name`,
     concat(`pt`.`from_godown_section_name`, ':From Godown Section Name:O:N:') as `from_godown_section_name`,
     concat(`pt`.`from_godown_section_beans_name`, ':From Godown Section Beans Name:O:N:') as `from_godown_section_beans_name`,
     concat(`pt`.`to_godown_name`, ':To Godown Name:Y:C:cmv_godown:F') as `to_godown_name`,
     concat(`pt`.`to_godown_section_name`, ':To Godown Section Name:O:N:') as `to_godown_section_name`,
     concat(`pt`.`to_godown_section_beans_name`, ':To Godown Section Beans Name:O:N:') as `to_godown_section_beans_name`,
    concat(`pt`.`product_unit_name`, ':Product Unit Name:Y:C:sm_product_unit:F') as `product_unit_name`,
    concat(`pt`.`internal_material_transfer_remark`, ':Internal Material Transfer Remark:O:N:') as `internal_material_transfer_remark`,

    concat(case when `pt`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`pt`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`pt`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`pt`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`pt`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`pt`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`pt`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`pt`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
    concat(`pt`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`pt`.`company_branch_id`, ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(`pt`.`product_material_id`, ':Product Material Id:N:N:') as `product_material_id`,
    concat(`pt`.`inter_material_transfer_details_id`, ':Inter Material Transfer Details Id:N:N:') as `inter_material_transfer_details_id`,
    concat(`pt`.`inter_material_transfer_master_id`, ':Inter Material Transfer Master Id:N:N:') as `inter_material_transfer_master_id`,
    concat(`pt`.`from_department_id`, ':From Department Id:N:N:') as `from_department_id`,
    concat(`pt`.`from_sub_department_id`, ':From sub Department Id:N:N:') as `from_sub_department_id`,
    concat(`pt`.`to_department_id`, ':To Department Id:N:N:') as `to_department_id`,
    concat(`pt`.`to_sub_department_id`, ':To sub Department Id:N:N:') as `to_sub_department_id`
from
    `ptv_inter_material_transfer_details` `pt`
limit 1;






create or replace
algorithm = UNDEFINED view `inter_material_transfer_master_rpt` as
select
    concat(`st`.`inter_material_transfer_no`, ':Inter Material Transfer No:Y:C:ptv_inter_material_transfer_master:F') as `inter_material_transfer_no`,
    concat(`st`.`inter_material_transfer_date`, ':Inter Material Transfer Date:Y:C:Date:F') as `inter_material_transfer_date`,
    concat(`st`.`from_department_name`, ':From Department Name:Y:C:cm_department:F') as `from_department_name`,
    concat(`st`.`from_sub_department_name`, ':From Sub Department Name:O:N:') as `from_sub_department_name`,
    concat(`st`.`to_department_name`, ':To Department Name:Y:C:cm_department:F') as `to_department_name`,
    concat(`st`.`to_sub_department_name`, ':To Sub Department Name:O:N:') as `to_sub_department_name`,
    concat(`st`.`requested_by_name`, ':Requested By Name:Y:C:cm_employee:F') as `requested_by_name`,
    concat(case when `st`.`inter_material_transfer_status` = 'R' then 'Received' else 'Pending' end, ':Inter Material Transfer Status:Y:H:(Received, Pending)') as `inter_material_transfer_status_desc`,
    concat(case when `st`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') as `Active`,
    concat(case when `st`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `Deleted`,
    concat(`st`.`financial_year`, ':Financial Year:O:N:') as `financial_year`,
    concat(`st`.`inter_material_transfer_status`, ':Inter Material Transfer Status:O:N:') as `inter_material_transfer_status`,
    concat(`st`.`from_department_id`, ':From Department Id:N:N:') as `from_department_id`,
    concat(`st`.`from_sub_department_id`, ':From Sub Department Id:N:N:') as `from_sub_department_id`,
    concat(`st`.`requested_by_id`, ':Requested By Id:N:N:') as `requested_by_id`,
    concat(`st`.`to_department_id`, ':To Department Id:N:N:') as `to_department_id`,
    concat(`st`.`to_sub_department_id`, ':To Sub Department Id:N:N:') as `to_sub_department_id`,
    concat(`st`.`inter_material_transfer_master_id`, ':Inter Material Transfer Master Id:N:N:') as `inter_material_transfer_master_id`,
    concat(`st`.`company_id`, ':Company Id:N:N:') as `company_id`,
    concat(`st`.`created_by`, ':Created By:O:N:') as `created_by`,
    concat(`st`.`created_on`, ':Created On:O:N:') as `created_on`,
    concat(`st`.`modified_by`, ':Modified By:O:N:') as `modified_by`,
    concat(`st`.`modified_on`, ':Modified On:O:N:') as `modified_on`,
    concat(`st`.`deleted_by`, ':Deleted By:O:N:') as `deleted_by`,
    concat(`st`.`deleted_on`, ':Deleted On:O:N:') as `deleted_on`,
    concat(`st`.`inter_material_transfer_no`, ':Field Name:N:N:') as `field_name`,
    concat(`st`.`inter_material_transfer_master_id`, ':Field Id:N:N:') as `field_id`
from
    `ptv_inter_material_transfer_master` `st`
limit 1;