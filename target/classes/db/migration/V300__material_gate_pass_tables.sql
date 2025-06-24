CREATE TABLE `pt_material_gate_pass_master` (
  `material_gate_pass_master_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL DEFAULT 1,
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1,
  `financial_year` varchar(20) NOT NULL,
  `gate_pass_no` varchar(50) NOT NULL,
  `gate_pass_date` date NOT NULL,
  `product_type_id` bigint(20) DEFAULT NULL,
  `product_type_name` varchar(80) DEFAULT NULL,
  `material_type` varchar(3) NOT NULL DEFAULT 'R' COMMENT '*radio button returnable , non-returnable  *',
  `vehicle_no` varchar(100) DEFAULT NULL,
  `service_provider_name` varchar(100) DEFAULT NULL,
  `service_provider_add` varchar(350) DEFAULT NULL,
  `checker_by_id` bigint(20) NOT NULL,
  `checker_by_name` varchar(50) NOT NULL,
  `approved_by_id` bigint(20) NOT NULL,
  `approved_by_name` varchar(50) NOT NULL,
  `gate_pass_status` varchar(3) DEFAULT NULL COMMENT '* default P(Pending), A(Approved), I(Partial-Returned), R(Return) *',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`material_gate_pass_master_id`)
);



CREATE TABLE `pt_material_gate_pass_details` (
  `material_gate_pass_details_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `material_gate_pass_master_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `company_branch_id` bigint(20) NOT NULL,
  `financial_year` varchar(20) NOT NULL,
  `gate_pass_no` varchar(50) NOT NULL,
  `gate_pass_date` date NOT NULL,
  `product_type_id` bigint(20) DEFAULT NULL,
  `product_type_name` varchar(80) DEFAULT NULL,
  `product_material_id` varchar(80) NOT NULL,
  `material_code` varchar(50) DEFAULT NULL,
  `material_name` varchar(250) DEFAULT NULL,
  `outward_quantity` decimal(18,4) DEFAULT 0.0000,
  `outward_weight` decimal(18,4) DEFAULT 0.0000,
  `inward_quantity` decimal(18,4) DEFAULT 0.0000,
  `inward_weight` decimal(18,4) DEFAULT 0.0000,
  `gate_pass_item_status` varchar(3) DEFAULT NULL COMMENT '* default P(Pending), A(Approved), I(Partial-Return), R(Return) *',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `rate` decimal(18,4) DEFAULT 0.0000,
  `remark` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`material_gate_pass_details_id`)
);


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



        
        create or replace
        algorithm = UNDEFINED view `ptv_material_gate_pass_master_rpt` as
        select
            concat(ifnull(`v`.`gate_pass_no`, ''), ':Gate Pass No:O:N:') as `gate_pass_no`,
            concat(ifnull(`v`.`gate_pass_date`, ''), ':Gate Pass Date:Y:D:') as `gate_pass_date`,
            concat(ifnull(`v`.`gate_pass_status_desc`, ''), ':Gate Pass Status:O:N:') as `gate_pass_status_desc`,
            concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:Y:T:') as `product_type_name`,
            concat(ifnull(`v`.`material_type_desc`, ''), ':Material Type:Y:T:') as `material_type_desc`,
            concat(ifnull(`v`.`vehicle_no`, ''), ':Vehicle No:Y:T:') as `vehicle_no`,
            concat(ifnull(`v`.`service_provider_name`, ''), ':Service Provider Name:Y:T:') as `service_provider_name`,
            concat(ifnull(`v`.`service_provider_add`, ''), ':Serive Provider Add :Y:T:') as `service_provider_add`,
            concat(ifnull(`v`.`checker_by_name`, ''), ':Checker Name:Y:T:') as `checker_by_name`,
            concat(ifnull(`v`.`approved_by_name`, ''), ':Approver Name:Y:T:') as `approved_by_name`,
            concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
            concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
            concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
            concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
            concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
            concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
            concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
            concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
            concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
            concat(ifnull(`v`.`material_gate_pass_master_id`, ''), ':Gate Pass Id:O:N:') as `material_gate_pass_master_id`
        from
            `ptv_material_gate_pass_master` `v`
        limit 1;


        -- erp_development.ptv_material_gate_pass_details_rpt source
        
        create or replace
        algorithm = UNDEFINED view `ptv_material_gate_pass_details_rpt` as
        select
            concat(ifnull(`v`.`gate_pass_no`, ''), ':Gate Pass No:O:N:') as `gate_pass_no`,
            concat(ifnull(`v`.`gate_pass_date`, ''), ':Gate Pass Date:Y:D:') as `gate_pass_date`,
            concat(ifnull(`v`.`gate_pass_item_status_desc`, ''), ':Gate Pass Item Status:Y:T:') as `gate_pass_item_status_desc`,
            concat(ifnull(`v`.`material_type_desc`, ''), ':Material Type Status:Y:T:') as `material_type_desc`,
            concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:Y:T:') as `product_type_name`,
            concat(ifnull(`v`.`product_material_id`, ''), ':Material Id:O:N:') as `product_material_id`,
            concat(ifnull(`v`.`material_code`, ''), ':Material Code:Y:T:') as `material_code`,
            concat(ifnull(`v`.`material_name`, ''), ':Material Name:Y:T:') as `material_name`,
            concat(ifnull(`v`.`outward_quantity`, ''), ':Outward Qty. :O:N:') as `outward_quantity`,
            concat(ifnull(`v`.`outward_weight`, ''), ':Outward Wt.:O:N:') as `outward_weight`,
            concat(ifnull(`v`.`inward_quantity`, ''), ':Inward Qty.:O:N:') as `inward_quantity`,
            concat(ifnull(`v`.`inward_weight`, ''), ':Inward Wt..:O:N:') as `inward_weight`,
            concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
            concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
            concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
            concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
            concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
            concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
            concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
            concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
            concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
            concat(ifnull(`v`.`material_gate_pass_master_id`, ''), ':Gate Pass Id:O:N:') as `material_gate_pass_master_id`
        from
            `ptv_material_gate_pass_details` `v`
        limit 1;