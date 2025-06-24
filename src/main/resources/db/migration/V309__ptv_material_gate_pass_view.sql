-- erp_development.ptv_material_gate_pass_details source

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
    `pmgpd`.`material_name` as `material_name`,
    `pmgpd`.`outward_quantity` as `outward_quantity`,
    `pmgpd`.`outward_weight` as `outward_weight`,
    `pmgpd`.`inward_quantity` as `inward_quantity`,
    `pmgpd`.`inward_weight` as `inward_weight`,
    `pmgpd`.`pending_inward_quantity` as `pending_inward_quantity`,
    `pmgpd`.`gate_pass_item_status` as `gate_pass_item_status`,
    `pmgpm`.`material_type` as `material_type`,
    case
        `pmgpm`.`material_type` when 'R' then 'Returnable'
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
    `cb`.`branch_pan_no` as `company_pan_no`
from
    (((`pt_material_gate_pass_details` `pmgpd`
left join `pt_material_gate_pass_master` `pmgpm` on
    (`pmgpm`.`material_gate_pass_master_id` = `pmgpd`.`material_gate_pass_master_id`
        and `pmgpm`.`is_delete` = 0))
left join `cm_company` `c` on
    (`pmgpd`.`company_id` = `c`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pmgpd`.`company_branch_id`
        and `cb`.`is_delete` = 0))
where
    `pmgpd`.`is_delete` = 0;



-- erp_development.ptv_material_gate_pass_master source

create or replace
algorithm = UNDEFINED view `ptv_material_gate_pass_master` as
select
    `pmgpm`.`material_gate_pass_master_id` as `material_gate_pass_master_id`,
    `pmgpm`.`company_id` as `company_id`,
    `pmgpm`.`financial_year` as `financial_year`,
    `pmgpm`.`company_branch_id` as `company_branch_id`,
    `pmgpm`.`gate_pass_date` as `gate_pass_date`,
    `pmgpm`.`gate_pass_no` as `gate_pass_no`,
    `pmgpm`.`product_type_id` as `product_type_id`,
    `pmgpm`.`product_type_name` as `product_type_name`,
    `pmgpm`.`material_type` as `material_type`,
    `pmgpm`.`vehicle_no` as `vehicle_no`,
    `pmgpm`.`service_provider_name` as `service_provider_name`,
    `pmgpm`.`service_provider_add` as `service_provider_add`,
    `pmgpm`.`checker_by_id` as `checker_by_id`,
    `pmgpm`.`checker_by_name` as `checker_by_name`,
    `pmgpm`.`approved_by_id` as `approved_by_id`,
    `pmgpm`.`approved_by_name` as `approved_by_name`,
    `pmgpm`.`approved_date` as `approved_date`,
    `pmgpm`.`gate_pass_status` as `gate_pass_status`,
    case
        `pmgpm`.`gate_pass_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Returned'
        when 'I' then 'Partial-Returned'
        else 'Pending'
    end as `gate_pass_status_desc`,
    case
        when `pmgpm`.`material_type` = 'R' then 'Returnable'
        else 'Non-Returnable'
    end as `material_type_desc`,
    `pmgpm`.`is_delete` as `is_delete`,
    `pmgpm`.`created_by` as `created_by`,
    `pmgpm`.`created_on` as `created_on`,
    `pmgpm`.`modified_by` as `modified_by`,
    `pmgpm`.`modified_on` as `modified_on`,
    `pmgpm`.`deleted_by` as `deleted_by`,
    `pmgpm`.`deleted_on` as `deleted_on`,
    `c`.`company_legal_name` as `company_name`,
    `cb`.`company_branch_name` as `company_branch_name`,
    `cb`.`branch_address1` as `company_address`,
    `cb`.`branch_pincode` as `company_pincode`,
    `cb`.`branch_phone_no` as `company_phone_no`,
    `cb`.`branch_EmailId` as `company_EmailId`,
    `cb`.`branch_website` as `company_website`,
    `cb`.`branch_gst_no` as `company_gst_no`,
    `cb`.`branch_pan_no` as `company_pan_no`
from
    ((`pt_material_gate_pass_master` `pmgpm`
left join `cm_company` `c` on
    (`pmgpm`.`company_id` = `c`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pmgpm`.`company_branch_id`
        and `cb`.`is_delete` = 0))
where
    `pmgpm`.`is_delete` = 0;


-- erp_development.ptv_material_gate_pass_return_summary source

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