ALTER TABLE pt_material_gate_pass_details ADD pending_inward_quantity decimal(18,4) DEFAULT 0 NULL;
-- erp_development.ptv_material_gate_pass_return_summary source


-- erp_development.pt_material_gate_pass_return_summary definition

CREATE TABLE `pt_material_gate_pass_return_summary` (
  `material_gate_pass_return_summary_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `material_gate_pass_details_id` bigint(20) NOT NULL,
  `material_gate_pass_master_id` bigint(20) NOT NULL,
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
  PRIMARY KEY (`material_gate_pass_return_summary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


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
