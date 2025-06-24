INSERT INTO am_modules_forms (company_id,company_branch_id,module_id,sub_module_id,modules_menu_id,modules_sub_menu_id,modules_forms_name,display_sequence,display_name,menu_type,listing_component_name,listing_component_import_path,listing_navigation_url,form_component_name,form_component_import_path,form_navigation_url,icon_class,page_header,is_selected,is_active,is_delete,created_by,created_on,modified_by,modified_on,deleted_by,deleted_on,is_protected,header,footer,url_parameter) VALUES
	 (1,1,1,16,2,71,'Return Material Gate Pass',4,'Return Material Gate Pass','Purchase','<FrmMaterialReturnGatePassListing />','import FrmMaterialReturnGatePassListing from "Transactions/TPurchaseOrder/MaterialReturnGatePass/FrmMaterialReturnGatePassListing";','/Transactions/TPurchaseOrder/MaterialReturnGatePass/FrmMaterialReturnGatePassListing','<FrmMaterialReturnGatePassEntry/>','import FrmMaterialReturnGatePassEntry from "Transactions/TPurchaseOrder/MaterialReturnGatePass/FrmMaterialReturnGatePassEntry";','/Transactions/TPurchaseOrder/MaterialReturnGatePass/FrmMaterialReturnGatePassEntry',NULL,'Return Material Gate Pass',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,1,1,'');

-- pt_material_return_gate_pass_details definition

CREATE TABLE `pt_material_return_gate_pass_details` (
  `material_return_gate_pass_details_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `material_return_gate_pass_master_id` bigint(20) NOT NULL,
  `company_id` bigint(20) NOT NULL,
  `company_branch_id` bigint(20) NOT NULL,
  `financial_year` varchar(20) NOT NULL,
  `return_gate_pass_no` varchar(50) NOT NULL,
  `return_gate_pass_date` date NOT NULL,
  `product_type_id` bigint(20) DEFAULT NULL,
  `product_type_name` varchar(80) DEFAULT NULL,
  `product_material_id` varchar(80) NOT NULL,
  `material_code` varchar(50) DEFAULT NULL,
  `material_name` varchar(250) DEFAULT NULL,
  `outward_quantity` decimal(18,4) DEFAULT 0.0000,
  `outward_weight` decimal(18,4) DEFAULT 0.0000,
  `inward_quantity` decimal(18,4) DEFAULT 0.0000,
  `inward_weight` decimal(18,4) DEFAULT 0.0000,
  `pending_inward_quantity` decimal(18,4) DEFAULT 0.0000,
  `return_item_status` varchar(3) DEFAULT NULL COMMENT '* default P(Pending), I(Partial-Return), R(Return) *',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `rate` decimal(18,4) DEFAULT 0.0000,
  `remark` varchar(250) DEFAULT NULL,
  `material_gate_pass_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`material_return_gate_pass_details_id`)
);


-- pt_material_return_gate_pass_master definition

CREATE TABLE `pt_material_return_gate_pass_master` (
  `material_return_gate_pass_master_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) NOT NULL DEFAULT 1,
  `company_branch_id` bigint(20) NOT NULL DEFAULT 1,
  `financial_year` varchar(20) NOT NULL,
  `return_gate_pass_no` varchar(50) NOT NULL,
  `gate_pass_no` varchar(50) NOT NULL,
  `return_gate_pass_date` date NOT NULL,
  `product_type_id` bigint(20) DEFAULT NULL,
  `product_type_name` varchar(80) DEFAULT NULL,
  `material_type` varchar(3) NOT NULL DEFAULT 'R' COMMENT '*radio button returnable , non-returnable  *',
  `vehicle_no` varchar(100) DEFAULT NULL,
  `supplier_branch_address` varchar(350) DEFAULT NULL,
  `checker_by_id` bigint(20) NOT NULL,
  `checker_by_name` varchar(50) NOT NULL,
  `returned_by_id` bigint(20) DEFAULT 0,
  `returned_by_name` varchar(50) DEFAULT NULL,
  `return_status` varchar(3) DEFAULT NULL COMMENT '* default P(Pending), I(Partial-Returned), R(Return) *',
  `supplier_branch_id` bigint(20) DEFAULT NULL,
  `supplier_branch_state_id` bigint(20) DEFAULT NULL,
  `supplier_branch_city_id` bigint(20) DEFAULT NULL,
  `supplier_branch_contacts_id` bigint(20) DEFAULT NULL,
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  PRIMARY KEY (`material_return_gate_pass_master_id`)
);




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
    `pmgpm`.`material_type` as `material_type`,
    case
        `pmgpd`.`return_item_status` when 'P' then 'Pending'
        when 'R' then 'Returned'
        when 'I' then 'Partial-Returned'
        else 'Pending'
    end as `return_item_status_desc`,
    case
        `pmgpm`.`material_type` when 'R' then 'Returnable'
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
    `cb`.`branch_pan_no` as `company_pan_no`
from
    (((`pt_material_return_gate_pass_details` `pmgpd`
left join `pt_material_return_gate_pass_master` `pmgpm` on
    (`pmgpm`.`material_return_gate_pass_master_id` = `pmgpd`.`material_return_gate_pass_master_id`
        and `pmgpm`.`is_delete` = 0))
left join `cm_company` `c` on
    (`pmgpd`.`company_id` = `c`.`company_id`
        and `c`.`is_delete` = 0))
left join `cm_company_branch` `cb` on
    (`cb`.`company_branch_id` = `pmgpd`.`company_branch_id`
        and `cb`.`is_delete` = 0))
where
    `pmgpd`.`is_delete` = 0;


-- ptv_material_return_gate_pass_master source

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
    `pmgpm`.`checker_by_id` as `checker_by_id`,
    `pmgpm`.`checker_by_name` as `checker_by_name`,
    `pmgpm`.`returned_by_id` as `returned_by_id`,
    `pmgpm`.`returned_by_name` as `returned_by_name`,
    `pmgpm`.`supplier_branch_id` as `supplier_branch_id`,
    `pmgpm`.`supplier_branch_state_id` as `supplier_branch_state_id`,
    `pmgpm`.`supplier_branch_city_id` as `supplier_branch_city_id`,
    `pmgpm`.`supplier_branch_contacts_id` as `supplier_branch_contacts_id`,
    `pmgpm`.`return_status` as `return_status`,
    case
        `pmgpm`.`return_status` when 'P' then 'Pending'
        when 'R' then 'Returned'
        when 'I' then 'Partial-Returned'
        else 'Pending'
    end as `return_status_desc`,
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
    ((((`pt_material_return_gate_pass_master` `pmgpm`
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
where
    `pmgpm`.`is_delete` = 0;



-- ptv_material_return_gate_pass_master_rpt source

create or replace
algorithm = UNDEFINED view `ptv_material_return_gate_pass_master_rpt` as
select
    concat(ifnull(`v`.`return_gate_pass_no`, ''), 'Return Gate Pass No:O:N:') as `return_gate_pass_no`,
    concat(ifnull(`v`.`gate_pass_no`, ''), ':Gate Pass No:O:N:') as `gate_pass_no`,
    concat(ifnull(`v`.`return_gate_pass_date`, ''), ':Return Gate Pass Date:Y:D:') as `return_gate_pass_date`,
    concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:Y:T:') as `product_type_name`,
    concat(ifnull(`v`.`material_type_desc`, ''), ':Material Type:Y:T:') as `material_type_desc`,
    concat(ifnull(`v`.`return_status`, ''), ':Return Status:O:N:') as `return_status`,
    concat(ifnull(`v`.`vehicle_no`, ''), ':Vehicle No:Y:T:') as `vehicle_no`,
    concat(ifnull(`v`.`supplier_branch_address`, ''), ':Supplier Address:Y:T:') as `supplier_branch_address`,
    concat(ifnull(`v`.`supp_branch_name`, ''), ':Supplier Name :Y:T:') as `supp_branch_name`,
    concat(ifnull(`v`.`state_name`, ''), ':State Name:Y:T:') as `state_name`,
    concat(ifnull(`v`.`city_name`, ''), ':City Name :Y:T:') as `city_name`,
    concat(ifnull(`v`.`supp_contact_person`, ''), ':Supplier Contact Name:Y:T:') as `supp_contact_person`,
    concat(ifnull(`v`.`supp_contact_no`, ''), ':Supplier Contact No :Y:T:') as `supp_contact_no`,
    concat(ifnull(`v`.`checker_by_name`, ''), ':Checker Name:Y:T:') as `checker_by_name`,
    concat(ifnull(`v`.`returned_by_name`, ''), ':Returned Name:Y:T:') as `returned_by_name`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`material_return_gate_pass_master_id`, ''), ':Return Gate Pass Id:O:N:') as `material_return_gate_pass_master_id`
from
    `ptv_material_return_gate_pass_master` `v`
limit 1;


-- ptv_material_return_gate_pass_details_rpt source

create or replace
algorithm = UNDEFINED view `ptv_material_return_gate_pass_details_rpt` as
select
    concat(ifnull(`v`.`return_gate_pass_no`, ''), ':Return Gate Pass No:O:N:') as `return_gate_pass_no`,
    concat(ifnull(`v`.`return_gate_pass_date`, ''), ':Return Gate Pass Date:Y:D:') as `return_gate_pass_date`,
    concat(ifnull(`v`.`material_type_desc`, ''), ':Material Type Status:Y:H:(Returnable,Non-Returnable)') as `material_type_desc`,
    concat(ifnull(`v`.`product_type_name`, ''), ':Product Type Name:Y:T:') as `product_type_name`,
    concat(ifnull(`v`.`product_material_id`, ''), ':Material Id:O:N:') as `product_material_id`,
    concat(ifnull(`v`.`material_code`, ''), ':Material Code:Y:T:') as `material_code`,
    concat(ifnull(`v`.`material_name`, ''), ':Material Name:Y:T:') as `material_name`,
    concat(ifnull(`v`.`return_item_status_desc`, ''), ':Return Item Status:Y:H:(Returned,Partial-Returned,Pending)') as `return_item_status_desc`,
    concat(ifnull(`v`.`outward_quantity`, ''), ':Outward Qty. :O:N:') as `outward_quantity`,
    concat(ifnull(`v`.`inward_quantity`, ''), ':Inward Qty.:O:N:') as `inward_quantity`,
    concat(ifnull(`v`.`pending_inward_quantity`, ''), ':Pending Inward Qty.:O:N:') as `pending_inward_quantity`,
    concat(ifnull(`v`.`rate`, ''), ':Rate:O:N:') as `rate`,
    concat(ifnull(`v`.`remark`, ''), ':Remark:O:N:') as `remark`,
    concat(case when `v`.`is_delete` = 1 then 'Yes' else 'No' end, ':Deleted Status:Y:H:(Yes, No)') as `is_delete`,
    concat(ifnull(`v`.`created_by`, ''), ':Created By:O:N:') as `created_by`,
    concat(ifnull(`v`.`created_on`, ''), ':Created On:O:N:') as `created_on`,
    concat(ifnull(`v`.`modified_by`, ''), ':Modified By:O:N:') as `modified_by`,
    concat(ifnull(`v`.`modified_on`, ''), ':Modified On:O:N:') as `modified_on`,
    concat(ifnull(`v`.`deleted_by`, ''), ':Deleted By:O:N:') as `deleted_by`,
    concat(ifnull(`v`.`deleted_on`, ''), ':Deleted On:O:N:') as `deleted_on`,
    concat(ifnull(`v`.`company_id`, ''), ':Company Id:N:N:') as `company_id`,
    concat(ifnull(`v`.`company_branch_id`, ''), ':Company Branch Id:N:N:') as `company_branch_id`,
    concat(ifnull(`v`.`material_return_gate_pass_master_id`, ''), ':Return Gate Pass Id:O:N:') as `material_return_gate_pass_master_id`
from
    `ptv_material_return_gate_pass_details` `v`
limit 1;



-- pt_material_gate_pass_return_summary definition

CREATE TABLE `pt_material_gate_pass_return_summary` (
  `material_gate_pass_return_summary_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `material_gate_pass_details_id` bigint(20) NOT NULL,
  `material_gate_pass_master_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  `company_branch_id` bigint(20) NOT NULL,
  `financial_year` varchar(20) NOT NULL,
  `gate_pass_no` varchar(50) NOT NULL,
  `inward_date` date NOT NULL,
  `product_material_id` varchar(80) NOT NULL,
  `material_code` varchar(50) DEFAULT NULL,
  `material_name` varchar(250) DEFAULT NULL,
  `outward_quantity` decimal(18,4) DEFAULT 0.0000,
  `inward_quantity` decimal(18,4) DEFAULT 0.0000,
  `pending_inward_quantity` decimal(18,4) DEFAULT 0.0000,
  `inward_remark` varchar(250) DEFAULT NULL,
  `gate_pass_item_status` varchar(3) DEFAULT NULL COMMENT '* default P(Pending), A(Approved), I(Partial-Return), R(Return) *',
  `is_delete` bit(1) DEFAULT b'0',
  `created_by` varchar(255) DEFAULT '1',
  `created_on` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `deleted_by` varchar(255) DEFAULT NULL,
  `deleted_on` datetime DEFAULT NULL,
  `return_gate_pass_no` varchar(50) NOT NULL,
  `material_return_gate_pass_master_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`material_gate_pass_return_summary_id`)
)