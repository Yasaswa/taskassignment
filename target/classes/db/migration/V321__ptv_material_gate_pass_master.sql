ALTER TABLE pt_material_gate_pass_master MODIFY COLUMN supplier_branch_contacts_id varchar(1000) DEFAULT NULL NULL;

ALTER TABLE pt_material_gate_pass_master MODIFY COLUMN material_type varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'RGP' NOT NULL;

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
    `pmgpm`.`supplier_branch_address` as `supplier_branch_address`,
    `pmgpm`.`checker_by_id` as `checker_by_id`,
    `pmgpm`.`checker_by_name` as `checker_by_name`,
    `pmgpm`.`approved_by_id` as `approved_by_id`,
    `pmgpm`.`approved_by_name` as `approved_by_name`,
    `pmgpm`.`approved_date` as `approved_date`,
    `pmgpm`.`gate_pass_status` as `gate_pass_status`,
    `pmgpm`.`supplier_branch_id` as `supplier_branch_id`,
    `pmgpm`.`supplier_branch_state_id` as `supplier_branch_state_id`,
    `pmgpm`.`supplier_branch_city_id` as `supplier_branch_city_id`,
    `pmgpm`.`supplier_branch_contacts_id` as `supplier_branch_contacts_id`,
    `supp`.`supp_branch_name` as `supp_branch_name`,
    `supp`.`city_name` as `city_name`,
    `supp`.`state_name` as `state_name`,
    `con`.`supp_contact_person` as `supp_contact_person`,
    `con`.`supp_contact_no` as `supp_contact_no`,
    `dept`.`department_name` as `department_name`,
    `subdpt`.`department_name` as `sub_department_name`,
    `pmgpm`.`return_status` as `return_status`,
    case
        `pmgpm`.`return_status` when 'P' then 'Pending'
        when 'R' then 'Returned'
        when 'I' then 'Partial-Returned'
        else 'Pending'
    end as `return_status_desc`,
    case
        `pmgpm`.`gate_pass_status` when 'P' then 'Pending'
        when 'A' then 'Approved'
        when 'R' then 'Returned'
        when 'I' then 'Partial-Returned'
        else 'Pending'
    end as `gate_pass_status_desc`,
    case
        when `pmgpm`.`material_type` = 'RGP' then 'Returnable'
        else 'Non-Returnable'
    end as `material_type_desc`,
    `pmgpm`.`department_id` as `department_id`,
    `pmgpm`.`sub_department_id` as `sub_department_id`,
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
    ((((((`pt_material_gate_pass_master` `pmgpm`
left join `cm_company` `c` on
    (`pmgpm`.`company_id` = `c`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pmgpm`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `cmv_supplier_branch` `supp` on
    (`supp`.`supp_branch_id` = `pmgpm`.`supplier_branch_id`
        and `supp`.`is_delete` = 0))
left join `cmv_supplier_contacts` `con` on
    (`con`.`supplier_contact_id` = `pmgpm`.`supplier_branch_contacts_id`
        and `con`.`is_delete` = 0))
left join `cm_department` `dept` on
    (`dept`.`department_id` = `pmgpm`.`department_id`
        and `con`.`is_delete` = 0))
left join `cm_department` `subdpt` on
    (`subdpt`.`department_id` = `pmgpm`.`sub_department_id`
        and `con`.`is_delete` = 0))
where
    `pmgpm`.`is_delete` = 0;



create or replace
algorithm = UNDEFINED view `ptv_material_return_gate_pass_master` as
select
    `pmgpm`.`material_return_gate_pass_master_id` as `material_return_gate_pass_master_id`,
    `pmgpm`.`company_id` as `company_id`,
    `pmgpm`.`financial_year` as `financial_year`,
    `pmgpm`.`company_branch_id` as `company_branch_id`,
    `pmgpm`.`return_gate_pass_date` as `return_gate_pass_date`,
    `pmgpm`.`gate_pass_no` as `gate_pass_no`,
    `pmgpm`.`return_gate_pass_no` as `return_gate_pass_no`,
    `pmgpm`.`product_type_id` as `product_type_id`,
    `pmgpm`.`product_type_name` as `product_type_name`,
    `pmgpm`.`material_type` as `material_type`,
    `pmgpm`.`vehicle_no` as `vehicle_no`,
    `pmgpm`.`supplier_branch_address` as `supplier_branch_address`,
    `supp`.`supp_branch_name` as `supp_branch_name`,
    `supp`.`city_name` as `city_name`,
    `supp`.`state_name` as `state_name`,
    `con`.`supp_contact_person` as `supp_contact_person`,
    `con`.`supp_contact_no` as `supp_contact_no`,
    `dept`.`department_name` as `department_name`,
    `subdpt`.`department_name` as `sub_department_name`,
    `pmgpm`.`checker_by_id` as `checker_by_id`,
    `pmgpm`.`checker_by_name` as `checker_by_name`,
    `pmgpm`.`returned_by_id` as `returned_by_id`,
    `pmgpm`.`returned_by_name` as `returned_by_name`,
    `pmgpm`.`return_status` as `return_status`,
    case
        `pmgpm`.`return_status` when 'P' then 'Pending'
        when 'R' then 'Returned'
        when 'I' then 'Partial-Returned'
        else 'Pending'
    end as `return_status_desc`,
    case
        when `pmgpm`.`material_type` = 'RGP' then 'Returnable'
        else 'Non-Returnable'
    end as `material_type_desc`,
    `pmgpm`.`supplier_branch_id` as `supplier_branch_id`,
    `pmgpm`.`supplier_branch_state_id` as `supplier_branch_state_id`,
    `pmgpm`.`supplier_branch_city_id` as `supplier_branch_city_id`,
    `pmgpm`.`supplier_branch_contacts_id` as `supplier_branch_contacts_id`,
    `pmgpm`.`department_id` as `department_id`,
    `pmgpm`.`sub_department_id` as `sub_department_id`,
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
    ((((((`pt_material_return_gate_pass_master` `pmgpm`
left join `cm_company` `c` on
    (`pmgpm`.`company_id` = `c`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pmgpm`.`company_branch_id`
        and `cb`.`is_delete` = 0))
left join `cmv_supplier_branch` `supp` on
    (`supp`.`supp_branch_id` = `pmgpm`.`supplier_branch_id`
        and `supp`.`is_delete` = 0))
left join `cmv_supplier_contacts` `con` on
    (`con`.`supplier_contact_id` = `pmgpm`.`supplier_branch_contacts_id`
        and `con`.`is_delete` = 0))
left join `cm_department` `dept` on
    (`dept`.`department_id` = `pmgpm`.`department_id`
        and `con`.`is_delete` = 0))
left join `cm_department` `subdpt` on
    (`subdpt`.`department_id` = `pmgpm`.`sub_department_id`
        and `con`.`is_delete` = 0))
where
    `pmgpm`.`is_delete` = 0;
