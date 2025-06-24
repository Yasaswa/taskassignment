-- inter_material_transfer_master_rpt source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `inter_material_transfer_master_rpt` AS
select
    concat(`st`.`inter_material_transfer_no`, ':Inter Material Transfer No:Y:T:') AS `inter_material_transfer_no`,
    concat(`st`.`inter_material_transfer_date`, ':Inter Material Transfer Date:Y:D:') AS `inter_material_transfer_date`,
    concat(`st`.`from_department_name`, ':From Department Name:Y:T:') AS `from_department_name`,
    concat(`st`.`from_sub_department_name`, ':From Sub Department Name:Y:T:') AS `from_sub_department_name`,
    concat(`st`.`to_department_name`, ':To Department Name:Y:T:') AS `to_department_name`,
    concat(`st`.`to_sub_department_name`, ':To Sub Department Name:Y:T:') AS `to_sub_department_name`,
    concat(`st`.`requested_by_name`, ':Requested By Name:Y:T:') AS `requested_by_name`,
--     concat(case when `st`.`inter_material_transfer_status` = 'R' then 'Received' else 'Pending' end, ':Inter Material Transfer Status:Y:H:(Received, Pending)') AS `inter_material_transfer_status_desc`,
    concat(case when `st`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
    concat(case when `st`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
    concat(`st`.`financial_year`, ':Financial Year:O:N:') AS `financial_year`,
--     concat(`st`.`inter_material_transfer_status`, ':Inter Material Transfer Status:O:N:') AS `inter_material_transfer_status`,
    concat(`st`.`inter_material_transfer_master_id`, ':Inter Material Transfer Master Id:N:N:') AS `inter_material_transfer_master_id`,
    concat(`st`.`company_id`, ':Company Id:N:N:') AS `company_id`,
    concat(`st`.`created_by`, ':Created By:O:N:') AS `created_by`,
    concat(`st`.`created_on`, ':Created On:O:N:') AS `created_on`,
    concat(`st`.`modified_by`, ':Modified By:O:N:') AS `modified_by`,
    concat(`st`.`modified_on`, ':Modified On:O:N:') AS `modified_on`,
    concat(`st`.`deleted_by`, ':Deleted By:O:N:') AS `deleted_by`,
    concat(`st`.`deleted_on`, ':Deleted On:O:N:') AS `deleted_on`,
    concat(`st`.`inter_material_transfer_no`, ':Field Name:N:N:') AS `field_name`,
    concat(`st`.`inter_material_transfer_master_id`, ':Field Id:N:N:') AS `field_id`
from
    `ptv_inter_material_transfer_master` `st`
limit 1;





-- inter_material_transfer_details_rpt source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `inter_material_transfer_details_rpt` AS
select
    concat(`pt`.`inter_material_transfer_no`, ':Inter Material Transfer No:Y:T') AS `inter_material_transfer_no`,
    concat(`pt`.`inter_material_transfer_date`, ':Inter Material Transfer Date:Y:D') AS `inter_material_transfer_date`,
    concat(`pt`.`product_code`, ':Material Code:Y:T') AS `product_code`,
    concat(`pt`.`product_material_name`, ':Material Name:Y:T') AS `product_material_name`,
    concat(`pt`.`batch_no`, ':Batch No:Y:T') AS `batch_no`,
    concat(`pt`.`inter_material_transfer_quantity`, ':Inter Material Transfer Quantity:O:N') AS `inter_material_transfer_quantity`,
    concat(`pt`.`inter_material_transfer_weight`, ':Inter Material Transfer Weight:O:N') AS `inter_material_transfer_weight`,
    concat(`pt`.`inter_material_transfer_boxes`, ':Inter Material Transfer Boxes:O:N') AS `inter_material_transfer_boxes`,    
--     concat(`pt`.`closing_balance_quantity`, ':Closing Balance Quantity:O:N') AS `closing_balance_quantity`,
--     concat(`pt`.`closing_balance_weight`, ':Closing Balance Weight:O:N') AS `closing_balance_weight`,
--     concat(`pt`.`closing_no_of_boxes`, ':Closing No Of Boxes:O:N') AS `closing_no_of_boxes`,
    concat(`pt`.`from_godown_name`, ':From Godown Name:Y:C:cmv_godown:F') AS `from_godown_name`,
    concat(`pt`.`from_godown_section_name`, ':From Godown Section Name:O:N:') AS `from_godown_section_name`,
    concat(`pt`.`from_godown_section_beans_name`, ':From Godown Section Beans Name:O:N:') AS `from_godown_section_beans_name`,
    concat(`pt`.`to_godown_name`, ':To Godown Name:Y:C:cmv_godown:F') AS `to_godown_name`,
    concat(`pt`.`to_godown_section_name`, ':To Godown Section Name:O:N:') AS `to_godown_section_name`,
    concat(`pt`.`to_godown_section_beans_name`, ':To Godown Section Beans Name:O:N:') AS `to_godown_section_beans_name`,
    concat(`pt`.`product_unit_name`, ':Material Unit Name:Y:C:sm_product_unit:F') AS `product_unit_name`,
    concat(`pt`.`internal_material_transfer_remark`, ':Internal Material Transfer Remark:O:N:') AS `internal_material_transfer_remark`,
    concat(`pt`.`created_by`, ':Created By:O:N:') AS `created_by`,
    concat(`pt`.`created_on`, ':Created On:O:N:') AS `created_on`,
    concat(`pt`.`modified_by`, ':Modified By:O:N:') AS `modified_by`,
    concat(`pt`.`modified_on`, ':Modified On:O:N:') AS `modified_on`,
    concat(`pt`.`deleted_by`, ':Deleted By:O:N:') AS `deleted_by`,
    concat(`pt`.`deleted_on`, ':Deleted On:O:N:') AS `deleted_on`,
    concat(case when `pt`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
    concat(`pt`.`financial_year`, ':Financial Year:O:N:') AS `financial_year`,
    concat(`pt`.`product_material_id`, ':Product Material Id:N:N:') AS `product_material_id`,
    concat(`pt`.`inter_material_transfer_details_id`, ':Inter Material Transfer Details Id:N:N:') AS `inter_material_transfer_details_id`,
    concat(`pt`.`inter_material_transfer_master_id`, ':Inter Material Transfer Master Id:N:N:') AS `inter_material_transfer_master_id`
from
    `ptv_inter_material_transfer_details` `pt`
limit 1;



-- stv_indent_material_issue_return_details source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_return_details` AS
select
    `st`.`issue_return_details_transaction_id` AS `issue_return_details_transaction_id`,
    `st`.`issue_return_master_transaction_id` AS `issue_return_master_transaction_id`,
    `st`.`company_id` AS `company_id`,
    `st`.`company_branch_id` AS `company_branch_id`,
    `st`.`financial_year` AS `financial_year`,
    `stm`.`issue_return_no` AS `issue_return_no`,
    `p`.`product_rm_name` AS `product_material_name`,
    `st`.`product_material_id` AS `product_material_id`,
    `st`.`issue_batch_no` AS `issue_batch_no`,
    `st`.`product_material_issue_return_quantity` AS `product_material_issue_return_quantity`,
    `st`.`product_material_issue_return_weight` AS `product_material_issue_return_weight`,
    `st`.`product_material_issue_return_boxes` AS `product_material_issue_return_boxes`,
    `st`.`product_material_receipt_quantity` AS `product_material_receipt_quantity`,
    `st`.`product_material_receipt_weight` AS `product_material_receipt_weight`,
    `st`.`product_material_receipt_boxes` AS `product_material_receipt_boxes`,
    `st`.`issue_return_item_status` AS `issue_return_item_status`,
    `st`.`godown_id` AS `godown_id`,
    `st`.`godown_section_id` AS `godown_section_id`,
    `st`.`godown_section_beans_id` AS `godown_section_beans_id`,
    `st`.`issue_return_remark` AS `issue_return_remark`,
    `gsb`.`godown_name` AS `godown_name`,
    `gsb`.`godown_section_name` AS `godown_section_name`,
    `gsb`.`godown_section_beans_name` AS `godown_section_beans_name`,
    `std`.`product_material_indent_quantity` AS `product_material_indent_quantity`,
    `std`.`product_material_indent_weight` AS `product_material_indent_weight`,
    `std`.`product_material_approved_quantity` AS `product_material_approved_quantity`,
    `std`.`product_material_approved_weight` AS `product_material_approved_weight`,
    `std`.`product_material_issue_quantity` AS `product_material_issue_quantity`,
    `std`.`product_material_issue_weight` AS `product_material_issue_weight`,
    `std`.`product_material_issue_boxes` AS `product_material_issue_boxes`,
    `std`.`closing_balance_quantity` AS `closing_balance_quantity`,
    `std`.`closing_balance_weight` AS `closing_balance_weight`,
    case
        when `st`.`is_active` = 1 then 'Active'
        else 'In Active'
    end AS `Active`,
    case
        when `st`.`is_delete` = 1 then 'Yes'
        else 'No'
    end AS `Deleted`,
    `st`.`is_active` AS `is_active`,
    `st`.`is_delete` AS `is_delete`,
    `st`.`created_by` AS `created_by`,
    `st`.`created_on` AS `created_on`,
    `st`.`modified_by` AS `modified_by`,
    `st`.`modified_on` AS `modified_on`,
    `st`.`deleted_by` AS `deleted_by`,
    `st`.`deleted_on` AS `deleted_on`,
    `st`.`cone_per_wt` AS `cone_per_wt`,
    `st`.`goods_receipt_no` AS `goods_receipt_no`,
    `st`.`supplier_id` AS `supplier_id`,
    `st`.`creel_no` AS `creel_no`
from
    ((((`st_indent_material_issue_return_details` `st`
left join `st_indent_material_issue_return_master` `stm` on
    (`stm`.`issue_return_master_transaction_id` = `st`.`issue_return_master_transaction_id`))
left join `stv_indent_material_issue_details` `std` on
    (`std`.`issue_no` = `stm`.`issue_no`
        and `std`.`product_material_id` = `st`.`product_material_id`
        and `st`.`issue_batch_no` = `std`.`issue_batch_no`
        and `st`.`creel_no` = `std`.`creel_no`
        and `st`.`godown_id` = `std`.`godown_id`
        and `st`.`godown_section_id` = `std`.`godown_section_id`
        and `st`.`godown_section_beans_id` = `std`.`godown_section_beans_id`
        and `std`.`is_delete` = 0))
left join `cmv_godown_section_beans` `gsb` on
    (`gsb`.`godown_section_beans_id` = `st`.`godown_section_beans_id`
    	and `gsb`.`is_delete` = 0))
left join `sm_product_rm` `p` on
    (`p`.`product_rm_id` = `st`.`product_material_id`
    and `p`.`is_delete` = 0))
where
    `st`.`is_delete` = 0;