

ALTER TABLE pt_material_gate_pass_details ADD purpose varchar(250) DEFAULT NULL;
ALTER TABLE pt_material_return_gate_pass_details ADD purpose varchar(250) DEFAULT NULL;
ALTER TABLE pt_material_gate_pass_return_summary ADD purpose varchar(250) DEFAULT NULL;





create or replace
algorithm = UNDEFINED view `ptv_material_gate_pass_details` as
select
    `pmgpd`.`material_gate_pass_details_id` as `material_gate_pass_details_id`,
    `pmgpd`.`material_gate_pass_master_id` as `material_gate_pass_master_id`,
    `pmgpd`.`company_id` as `company_id`,
    `pmgpd`.`financial_year` as `financial_year`,
    `pmgpd`.`company_branch_id` as `company_branch_id`,
    `pmgpd`.`gate_pass_date` as `gate_pass_date`,
    `pmgpd`.`gate_pass_no` as `gate_pass_no`,
    `pmgpd`.`product_type_id` as `product_type_id`,
    `pmgpd`.`product_type_name` as `product_type_name`,
    `pmgpd`.`product_material_id` as `product_material_id`,
    `pmgpd`.`material_code` as `material_code`,
    `pmgpd`.`cost_center_id` as `cost_center_id`,
    `pmgpd`.`material_name` as `material_name`,
    `pmgpd`.`outward_quantity` as `outward_quantity`,
    `pmgpd`.`outward_weight` as `outward_weight`,
    `pmgpd`.`inward_quantity` as `inward_quantity`,
    `pmgpd`.`inward_weight` as `inward_weight`,
    `pmgpd`.`pending_inward_quantity` as `pending_inward_quantity`,
    `pmgpd`.`gate_pass_item_status` as `gate_pass_item_status`,
    `pmgpd`.`return_item_status` as `return_item_status`,
    `pmgpm`.`supp_branch_name` as `supp_branch_name`,
    `pmgpm`.`supp_contact_no` as `supp_contact_no`,
    `pmgpm`.`department_name` as `department_name`,
    `pmgpm`.`sub_department_name` as `sub_department_name`,
    `pmgpm`.`material_type` as `material_type`,
    case
        `pmgpd`.`return_item_status` when 'P' then 'Pending'
        when 'R' then 'Returned'
        when 'I' then 'Partial-Returned'
        else 'Pending'
    end as `return_item_status_desc`,
    case
        `pmgpm`.`material_type` when 'RGP' then 'Returnable'
        else 'Non-Returnable'
    end as `material_type_desc`,
    case
        `pmgpd`.`gate_pass_item_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Returned'
        when 'I' then 'Partial-Returned'
        else 'Pending'
    end as `gate_pass_item_status_desc`,
    `pmgpd`.`rate` as `rate`,
    `pmgpd`.`remark` as `remark`,
    `pmgpd`.`purpose` as `purpose`,
    `pmgpd`.`is_delete` as `is_delete`,
    `pmgpd`.`created_by` as `created_by`,
    `pmgpd`.`created_on` as `created_on`,
    `pmgpd`.`modified_by` as `modified_by`,
    `pmgpd`.`modified_on` as `modified_on`,
    `pmgpd`.`deleted_by` as `deleted_by`,
    `pmgpd`.`deleted_on` as `deleted_on`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `cb`.`branch_address1` as `company_address`,
    `cb`.`branch_pincode` as `company_pincode`,
    `cb`.`branch_phone_no` as `company_phone_no`,
    `cb`.`branch_EmailId` as `company_EmailId`,
    `cb`.`branch_website` as `company_website`,
    `cb`.`branch_gst_no` as `company_gst_no`,
    `cb`.`branch_pan_no` as `company_pan_no`,
    `fcc`.`cost_center_name` as `cost_center_name`
from
    ((((`pt_material_gate_pass_details` `pmgpd`
left join `ptv_material_gate_pass_master` `pmgpm` on
    (`pmgpm`.`material_gate_pass_master_id` = `pmgpd`.`material_gate_pass_master_id`
        and `pmgpm`.`is_delete` = 0))
left join `cm_company` `c` on
    (`pmgpd`.`company_id` = `c`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pmgpd`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `fm_cost_center` `fcc` on
    (`fcc`.`cost_center_id` = `pmgpd`.`cost_center_id` and `fcc`.`is_delete` = 0))
where
    `pmgpd`.`is_delete` = 0;



-- ptv_material_gate_pass_return_summary source

create or replace
algorithm = UNDEFINED view `ptv_material_gate_pass_return_summary` as
select
    `pmgpd`.`material_gate_pass_return_summary_id` as `material_gate_pass_return_summary_id`,
    `pmgpd`.`material_gate_pass_details_id` as `material_gate_pass_details_id`,
    `pmgpd`.`material_gate_pass_master_id` as `material_gate_pass_master_id`,
    `pmgpd`.`company_id` as `company_id`,
    `pmgpd`.`financial_year` as `financial_year`,
    `pmgpd`.`company_branch_id` as `company_branch_id`,
    `pmgpd`.`inward_date` as `inward_date`,
    `pmgpd`.`gate_pass_no` as `gate_pass_no`,
    `pmgpd`.`product_material_id` as `product_material_id`,
    `pmgpd`.`material_code` as `material_code`,
    `pmgpd`.`material_name` as `material_name`,
    `pmgpd`.`outward_quantity` as `outward_quantity`,
    `pmgpd`.`inward_quantity` as `inward_quantity`,
    `pmgpd`.`pending_inward_quantity` as `pending_inward_quantity`,
    `pmgpm`.`gate_pass_item_status` as `gate_pass_item_status`,
    case
        `pmgpd`.`gate_pass_item_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Returned'
        when 'I' then 'Partial-Returned'
        else 'Pending'
    end as `gate_pass_item_status_desc`,
    `pmgpd`.`inward_remark` as `inward_remark`,
     `pmgpd`.`purpose` as `purpose`,
    `pmgpd`.`is_delete` as `is_delete`,
    `pmgpd`.`created_by` as `created_by`,
    `pmgpd`.`created_on` as `created_on`,
    `pmgpd`.`modified_by` as `modified_by`,
    `pmgpd`.`modified_on` as `modified_on`,
    `pmgpd`.`deleted_by` as `deleted_by`,
    `pmgpd`.`deleted_on` as `deleted_on`
from
    (`pt_material_gate_pass_return_summary` `pmgpd`
left join `pt_material_gate_pass_details` `pmgpm` on
    (`pmgpm`.`material_gate_pass_master_id` = `pmgpd`.`material_gate_pass_master_id`
        and `pmgpm`.`material_gate_pass_details_id` = `pmgpd`.`material_gate_pass_details_id`
        and `pmgpm`.`is_delete` = 0))
where
    `pmgpd`.`is_delete` = 0;



-- ptv_material_return_gate_pass_details source

create or replace
algorithm = UNDEFINED view `ptv_material_return_gate_pass_details` as
select
    `pmgpd`.`material_return_gate_pass_details_id` as `material_return_gate_pass_details_id`,
    `pmgpd`.`material_return_gate_pass_master_id` as `material_return_gate_pass_master_id`,
    `pmgpd`.`company_id` as `company_id`,
    `pmgpd`.`financial_year` as `financial_year`,
    `pmgpd`.`company_branch_id` as `company_branch_id`,
    `pmgpd`.`return_gate_pass_date` as `return_gate_pass_date`,
    `pmgpd`.`return_gate_pass_no` as `return_gate_pass_no`,
    `pmgpd`.`product_type_id` as `product_type_id`,
    `pmgpd`.`product_type_name` as `product_type_name`,
    `pmgpd`.`product_material_id` as `product_material_id`,
    `pmgpd`.`material_code` as `material_code`,
    `pmgpd`.`material_name` as `material_name`,
    `pmgpd`.`outward_quantity` as `outward_quantity`,
    `pmgpd`.`outward_weight` as `outward_weight`,
    `pmgpd`.`inward_quantity` as `inward_quantity`,
    `pmgpd`.`inward_weight` as `inward_weight`,
    `pmgpd`.`pending_inward_quantity` as `pending_inward_quantity`,
    `pmgpd`.`return_item_status` as `return_item_status`,
    `pmgpm`.`supp_branch_name` as `supp_branch_name`,
    `pmgpm`.`supp_contact_no` as `supp_contact_no`,
    `pmgpm`.`department_name` as `department_name`,
    `pmgpm`.`sub_department_name` as `sub_department_name`,
    `pmgpm`.`material_type` as `material_type`,
    `pmgpm`.`challan_no` as `challan_no`,
    `pmgpm`.`challan_date` as `challan_date`,
    case
        `pmgpd`.`return_item_status` when 'P' then 'Pending'
        when 'R' then 'Returned'
        when 'I' then 'Partial-Returned'
        else 'Pending'
    end as `return_item_status_desc`,
    case
        `pmgpm`.`material_type` when 'RGP' then 'Returnable'
        else 'Non-Returnable'
    end as `material_type_desc`,
    `pmgpd`.`rate` as `rate`,
    `pmgpd`.`remark` as `remark`,
    `pmgpd`.`is_delete` as `is_delete`,
    `pmgpd`.`created_by` as `created_by`,
    `pmgpd`.`created_on` as `created_on`,
    `pmgpd`.`modified_by` as `modified_by`,
    `pmgpd`.`modified_on` as `modified_on`,
    `pmgpd`.`deleted_by` as `deleted_by`,
    `pmgpd`.`deleted_on` as `deleted_on`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `cb`.`branch_address1` as `company_address`,
    `cb`.`branch_pincode` as `company_pincode`,
    `cb`.`branch_phone_no` as `company_phone_no`,
    `cb`.`branch_EmailId` as `company_EmailId`,
    `cb`.`branch_website` as `company_website`,
    `cb`.`branch_gst_no` as `company_gst_no`,
    `cb`.`branch_pan_no` as `company_pan_no`,
    `fcc`.`cost_center_name` as `cost_center_name`,
    `pmgpd`.`cost_center_id` as `cost_center_id`,
    `pmgpd`.`purpose` as `purpose`
from
    ((((`pt_material_return_gate_pass_details` `pmgpd`
left join `ptv_material_return_gate_pass_master` `pmgpm` on
    (`pmgpm`.`material_return_gate_pass_master_id` = `pmgpd`.`material_return_gate_pass_master_id`
        and `pmgpm`.`is_delete` = 0))
left join `cm_company` `c` on
    (`pmgpd`.`company_id` = `c`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pmgpd`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `fm_cost_center` `fcc` on
    (`fcc`.`cost_center_id` = `pmgpd`.`cost_center_id`
        and `fcc`.`is_delete` = 0))
where
    `pmgpd`.`is_delete` = 0;



create or replace
algorithm = UNDEFINED view `ptv_material_gate_pass_details_rpt` as
select
    concat(ifnull(`v`.`gate_pass_no`, ''), ':Gate Pass No:O:N:') as `gate_pass_no`,
    concat(ifnull(`v`.`gate_pass_date`, ''), ':Gate Pass Date:Y:D:') as `gate_pass_date`,
    concat(ifnull(`v`.`material_code`, ''), ':Material Code:Y:T:') as `material_code`,
    concat(ifnull(`v`.`material_name`, ''), ':Material Name:Y:T:') as `material_name`,
    concat(ifnull(`v`.`purpose`, ''), ':Purpose:Y:T:') as `purpose`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:Y:T:') as `remark`,
    concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:Y:T:') as `product_type_name`,
    concat(ifnull(`v`.`material_type_desc`, ''), ':Material Type:Y:T:') as `material_type_desc`,
    concat(ifnull(`v`.`gate_pass_item_status_desc`, ''), ':Item Status:Y:T:') as `gate_pass_item_status_desc`,
    concat(ifnull(`v`.`return_item_status_desc`, ''), ':Return Item Status:Y:T:') as `return_item_status_desc`,
    concat(ifnull(`v`.`supp_branch_name`, ''), ':Supplier Name:Y:T:') as `supp_branch_name`,
    concat(ifnull(`v`.`supp_contact_no`, ''), ':Supplier No:Y:T:') as `supp_contact_no`,
    concat(ifnull(`v`.`department_name`, ''), ':Department Name:Y:T:') as `department_name`,
    concat(ifnull(`v`.`sub_department_name`, ''), ':Sub Department Name:Y:T:') as `sub_department_name`,
    concat(ifnull(`v`.`outward_quantity`, ''), ':Outward Qty. :O:N:') as `outward_quantity`,
    concat(ifnull(`v`.`inward_quantity`, ''), ':Inward Qty.:O:N:') as `inward_quantity`,
    concat(ifnull(`v`.`pending_inward_quantity`, ''), ':Inward Pending Qty.:O:N:') as `pending_inward_quantity`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Material Id:N:N:') as `product_material_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`material_gate_pass_master_id`, ''), ':Gate Pass Id:O:N:') as `material_gate_pass_master_id`
from
    `ptv_material_gate_pass_details` `v`
limit 1;


-- ptv_material_return_gate_pass_details_rpt source

create or replace
algorithm = UNDEFINED view `ptv_material_return_gate_pass_details_rpt` as
select
    concat(ifnull(`v`.`return_gate_pass_no`, ''), ':Return Gate Pass No:O:N:') as `return_gate_pass_no`,
    concat(ifnull(`v`.`return_gate_pass_date`, ''), ':Return Gate Pass Date:Y:D:') as `return_gate_pass_date`,
    concat(ifnull(`v`.`material_code`, ''), ':Material Code:Y:T:') as `material_code`,
    concat(ifnull(`v`.`material_name`, ''), ':Material Name:Y:T:') as `material_name`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(ifnull(`v`.`purpose`, ''), ':Purpose:O:N:') as `purpose`,
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

