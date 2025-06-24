ALTER TABLE pt_material_gate_pass_master MODIFY COLUMN approved_by_id bigint(20) DEFAULT 0 NULL;
ALTER TABLE pt_material_gate_pass_master MODIFY COLUMN approved_by_name varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE pt_material_gate_pass_master ADD approved_date date NULL;


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