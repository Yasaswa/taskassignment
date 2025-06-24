-- stv_indent_material_issue_return_master_rpt source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_return_master_rpt` AS
select
    concat(`v`.`issue_return_no`, ':Issue Return No:Y:T:') AS `issue_return_no`,
    concat(`v`.`issue_return_date`, ':Issue Return Date :Y:D:') AS `issue_return_date`,
     concat(`v`.`department_name`, ':Department:Y:T:') AS `department_name`,
    concat(`v`.`sub_department_name`, ':Sub Department:Y:T:') AS `sub_department_name`,
    concat(`v`.`issue_no`, ':Issue No:Y:T:') AS `issue_no`,  
    concat(`v`.`issue_return_status_desc`, ':Return Status:Y:H:(Pending,Received)') AS `issue_return_status_desc`,
    concat(`v`.`received_date`, ':Issue Received Date:Y:D:') AS `received_date`,
    concat(`v`.`indent_issue_return_type`, ':Issue Return Type:O:N:') AS `indent_issue_return_type`,
    concat(`v`.`return_by_name`, ':Return By:Y:T:') AS `return_by_name`,
    concat(`v`.`received_by_name`, ':Received By:Y:T:') AS `received_by_name`,
    concat(`v`.`remark`, ':Remark:O:N:') AS `remark`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
    concat(`v`.`financial_year`, ':Financial Year:O:N:') AS `financial_year`,
    concat(`v`.`created_by`, ':Created By:O:N:') AS `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') AS `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') AS `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') AS `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') AS `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') AS `deleted_on`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(`v`.`issue_return_status`, ':Issue Return Status:O:N:') AS `issue_return_status`,
    concat(`v`.`issue_return_master_transaction_id`, ':Issue Return Master Id:O:N:') AS `issue_return_master_transaction_id`,
    concat(`v`.`return_by_id`, ':Issue Return By Id:O:N:') AS `return_by_id`,
    concat(`v`.`indent_issue_return_type_id`, ':Issue Return Type Id:O:N:') AS `indent_issue_return_type_id`,
    concat(`v`.`department_id`, ':Department Id:O:N:') AS `department_id`,
    concat(`v`.`sub_department_id`, ':Sub Department Id:O:N:') AS `sub_department_id`,
    concat(`v`.`company_id`, ':Company Id:N:N:') AS `company_id`
from
    `stv_indent_material_issue_return_master` `v`
limit 1;



-- stv_indent_material_issue_return_details_rpt source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `stv_indent_material_issue_return_details_rpt` AS
select
    concat(`v`.`issue_return_no`, ':Issue Return No:Y:T:') AS `issue_return_no`,
    concat(`v`.`product_material_name`, ':Product Material Name:Y:T:') AS `product_material_name`,
    concat(`v`.`creel_no`, ':Creel No:Y:T:') AS `creel_no`,
    concat(`v`.`product_material_indent_quantity`, ':Requisition Quantity:O:N:') AS `product_material_indent_quantity`,
    concat(`v`.`product_material_indent_weight`, ':Requisition Weight:O:N:') AS `product_material_indent_weight`,
    concat(`v`.`product_material_approved_quantity`, ':Approved Quantity:O:N:') AS `product_material_approved_quantity`,
    concat(`v`.`product_material_approved_weight`, ':Approved Weight:O:N:') AS `product_material_approved_weight`,
    concat(`v`.`product_material_issue_quantity`, ':Issue Quantity:O:N:') AS `product_material_issue_quantity`,
    concat(`v`.`product_material_issue_weight`, ': Isuue Weight:O:N:') AS `product_material_issue_weight`,
    concat(`v`.`product_material_issue_boxes`, ': Isuue Boxes:O:N:') AS `product_material_issue_boxes`,
    concat(`v`.`product_material_issue_return_quantity`, ':Return Quantity :O:N:') AS `product_material_issue_return_quantity`,
    concat(`v`.`product_material_issue_return_weight`, ':Return Weight:O:N:') AS `product_material_issue_return_weight`,
    concat(`v`.`product_material_issue_return_boxes`, ':Return Boxes:O:N:') AS `product_material_issue_return_boxes`,
    concat(`v`.`product_material_receipt_quantity`, ': Receipt Quantity :O:N:') AS `product_material_receipt_quantity`,
    concat(`v`.`product_material_receipt_weight`, ': Receipt Weight:O:N:') AS `product_material_receipt_weight`,
    concat(`v`.`product_material_receipt_boxes`, ': Receipt Boxes:O:N:') AS `product_material_receipt_boxes`,
    concat(`v`.`godown_name`, ':Godown :Y:T:') AS `godown_name`,
    concat(`v`.`godown_section_name`, ':Godown Section :Y:T:') AS `godown_section_name`,
    concat(`v`.`godown_section_beans_name`, ':Godown Section Beans :Y:T:') AS `godown_section_beans_name`,
    concat(`v`.`issue_return_remark`, ':Remark:O:N:') AS `issue_return_remark`,
    concat(case when `v`.`is_active` = 1 then 'Active' else 'In Active' end, ':Active Status:Y:H:(Active, In Active)') AS `Active`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') AS `Deleted`,
    concat(`v`.`financial_year`, ':Financial Year:O:N:') AS `financial_year`,
    concat(`v`.`created_by`, ':Created By:O:N:') AS `created_by`,
    concat(`v`.`created_on`, ':Created On:O:N:') AS `created_on`,
    concat(`v`.`modified_by`, ':Modified By:O:N:') AS `modified_by`,
    concat(`v`.`modified_on`, ':Modified On:O:N:') AS `modified_on`,
    concat(`v`.`deleted_by`, ':Deleted By:O:N:') AS `deleted_by`,
    concat(`v`.`deleted_on`, ':Deleted On:O:N:') AS `deleted_on`,
    concat(`v`.`issue_return_item_status`, ':Return status:O:N:') AS `issue_return_item_status`,
    concat(`v`.`company_branch_id`, ':Company Branch Id:N:N:') AS `company_branch_id`,
    concat(`v`.`issue_return_master_transaction_id`, ':Issue Return Master Id:O:N:') AS `issue_return_master_transaction_id`,
    concat(`v`.`issue_return_details_transaction_id`, ':Issue Return Details Id:O:N:') AS `issue_return_details_transaction_id`,
    concat(`v`.`product_material_id`, ':Product Material Id:O:N:') AS `product_material_id`,
    concat(`v`.`godown_id`, ':Godown Id:O:N:') AS `godown_id`,
    concat(`v`.`godown_section_id`, ':Godown Section Id:O:N:') AS `godown_section_id`,
    concat(`v`.`godown_section_beans_id`, ':Godown Section Beans Id:O:N:') AS `godown_section_beans_id`,
    concat(`v`.`company_id`, ':Company Id:N:N:') AS `company_id`
from
    `stv_indent_material_issue_return_details` `v`
limit 1;