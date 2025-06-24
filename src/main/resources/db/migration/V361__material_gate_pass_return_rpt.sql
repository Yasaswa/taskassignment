-- ptv_material_return_gate_pass_details_rpt source

create or replace
algorithm = UNDEFINED view `ptv_material_return_gate_pass_details_rpt` as
select
    concat(ifnull(`v`.`return_gate_pass_no`, ''), ':Return Gate Pass No:O:N:') as `return_gate_pass_no`,
    concat(ifnull(`v`.`return_gate_pass_date`, ''), ':Return Gate Pass Date:Y:D:') as `return_gate_pass_date`,
    concat(ifnull(`v`.`material_code`, ''), ':Material Code:Y:T:') as `material_code`,
    concat(ifnull(`v`.`material_name`, ''), ':Material Name:Y:T:') as `material_name`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`challan_no`, ''), ':Challan No:Y:T:') as `challan_no`,
    concat(ifnull(`v`.`challan_date`, ''), ':Challan Date:Y:D:') as `challan_date`,
    concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:Y:T:') as `product_type_name`,
    concat(ifnull(`v`.`return_item_status_desc`, ''), ':Return Item Status:Y:H:(Returned,Partial-Returned,Pending)') as `return_item_status_desc`,
    concat(ifnull(`v`.`supp_branch_name`, ''), ':Supplier Name:Y:T:') as `supp_branch_name`,
    concat(ifnull(`v`.`supp_contact_no`, ''), ':Supplier No:Y:T:') as `supp_contact_no`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:T:') as `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:T:') as `sub_department_name`,
    concat(ifnull(`v`.`outward_quantity`, ''), ':Outward Qty. :O:N:') as `outward_quantity`,
    concat(ifnull(`v`.`inward_quantity`, ''), ':Inward Qty.:O:N:') as `inward_quantity`,
    concat(ifnull(`v`.`pending_inward_quantity`, ''), ':Pending Inward Qty.:O:N:') as `pending_inward_quantity`,
    concat(ifnull(`v`.`rate`, ''), ':Rate:O:N:') as `rate`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`material_return_gate_pass_master_id`, ''), ':Return Gate Pass Id:O:N:') as `material_return_gate_pass_master_id`
    
from
    `ptv_material_return_gate_pass_details` `v`
limit 1;